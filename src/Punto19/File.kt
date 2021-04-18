package Punto19

class File(name:String):FileSystemElement(name) {

    override fun ls() {
        println("$name")
    }

    override fun ls(level: Int) {
        for (i in 0 until level){
            print("- ")
        }
        this.ls()
    }

}