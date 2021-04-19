package Punto19

abstract class FileSystemElement(val name:String) {

    val root:Directory = RootDirectory

    abstract fun ls()

    abstract fun ls(level:Int)

    abstract fun listarArchivo()

    abstract fun listarArchivoNombre(subtermino:String)

}