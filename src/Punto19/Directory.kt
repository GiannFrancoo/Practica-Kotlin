package Punto19

import java.util.*

open class Directory(name:String):FileSystemElement(name) {

    val ListaElementos = ArrayList<FileSystemElement>() //Coleccion de elementos

    fun add(obj:FileSystemElement) = ListaElementos.add(obj)

    override fun ls() {
        ls(1)
    }

    override fun ls(level: Int) {
        for (i in 0 until level){
            print("- ")
        }
        println("$name/")
        ListaElementos.forEach { it.ls(level + 1) }
    }

    override fun listarArchivo(){
        for(i in 0 until ListaElementos.size){
            ListaElementos[i].listarArchivo()
        }
    }

    override fun listarArchivoNombre(subtermino: String) {
        for(i in 0 until ListaElementos.size){
            ListaElementos[i].listarArchivoNombre(subtermino)
        }
    }

}