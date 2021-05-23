package Punto19

class File(name:String):FileSystemElement(name) {

    override fun ls(level: Int) {
        for (i in 0 until level){
            print("- ")
        }
        println("$name")
    }

    override fun listarArchivo() = println("$name")

    override fun listarArchivoNombre(subtermino: String) {
        if (subtermino in name) println("$name")
        //if (name.contains(subtermino)) println("$name")
    }
}