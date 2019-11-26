package com.pmm.metro.compiler

import com.google.auto.service.AutoService
import com.pmm.metro.annotatoin.Station
import com.squareup.kotlinpoet.*
import java.io.File
import java.util.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic
import kotlin.collections.ArrayList

/**
 * Author:你需要一台永动机
 * Date:2019-11-25 11:44
 * Description:
 */
@AutoService(Processor::class)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class MetroProcessor : AbstractProcessor() {

    private var elementUtils: Elements? = null
    private var messager: Messager? = null
    private val rootMap: ArrayList<Pair<String, TypeElement>> = ArrayList()
    private lateinit var moduleName: String

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(Station::class.java.canonicalName)
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return super.getSupportedSourceVersion()
    }

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        elementUtils = processingEnv?.elementUtils
        // 保存 messager 对象
        this.messager = processingEnv?.messager

        moduleName = processingEnv?.options?.get("metroModuleName") ?: "${UUID.randomUUID()}".replace("-","")
    }


    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {

        val fileBuilder = FileSpec.builder(
            packageName = "com.pmm.metro.route",
            fileName = "MetroRoute_$moduleName"
        ).addImport("com.pmm.metro", "MetroMap")

        if (roundEnv == null) return true
        val elements = roundEnv.getElementsAnnotatedWith(Station::class.java)

        val loadFunBuilder = FunSpec.builder("loadRouter")
            .addAnnotation(JvmStatic::class.java)

        elements.forEach {
            val te = it as TypeElement
            val station: Station = it.getAnnotation(Station::class.java)
            rootMap.add(Pair(station.path, te))

            this.messager?.printMessage(
                Diagnostic.Kind.WARNING,
                "=============> 路径=${station.path} 类=${te.asClassName()}}"
            )
        }

        for ((path, te) in rootMap) {
            fileBuilder.addImport(getPackageName(te), te.asClassName().simpleName)
            loadFunBuilder.addStatement(
                "MetroMap.addStation(%S,%N::class.java)",
                path,
                te.simpleName
            )
        }

        //方法
        val initFun = loadFunBuilder.build()

        //类
        val metroRoutes = TypeSpec.objectBuilder("MetroRoute_$moduleName")
            .addFunction(initFun)
            .build()

        //创建一个Metro$$Map的类
        val file = fileBuilder
            .addType(metroRoutes).build()
        file.writeFile()
        return true
    }

    private fun getPackageName(type: TypeElement): String {
        return elementUtils!!.getPackageOf(type).qualifiedName.toString()
    }

    private fun FileSpec.writeFile() {
        val kaptKotlinGeneratedDir = processingEnv.options["kapt.kotlin.generated"]
        val outputFile = File(kaptKotlinGeneratedDir).apply {
            mkdirs()
        }
        writeTo(outputFile.toPath())
    }

}