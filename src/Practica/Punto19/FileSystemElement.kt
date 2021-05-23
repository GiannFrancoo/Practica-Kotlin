package Punto19

abstract class FileSystemElement(val name:String) {

    val root:Directory = RootDirectory

    abstract fun ls(level:Int = 1)

    abstract fun listarArchivo()

    abstract fun listarArchivoNombre(subtermino:String)

}