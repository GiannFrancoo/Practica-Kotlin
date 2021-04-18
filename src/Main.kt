import java.util.*

fun main(args: Array<String>){
    //holaMundo()
    //println("¿Es par?" + numeroPar(8))
    //println("¿Es bisiesto?" + esBisiesto(2020))
    //nEnteros(3)
    //nMultiplosIJ(n = 17,j = 5, i = 3) //Di vuelta los parametros
    //tablaMultiplicacion12(n = 2);
    //println("es primo? ${esPrimo(n = 13)}")
    //fizzBuzz();
    //println("${dividirSinIFWHEN(n = 9, m = 3)}")
    //println("Cadena: ${Caesar("abc", 1)}");
    //createPyramid(rows = 4) Wiki
    //println("Lista => ${joinOptions(["a","b","c","d"])}")

    //15
    //val myString = "Hello Everyone"
    //val result = myString.removeFirstLastChar()
    //println("Resultado: $result")

    //16
    //val myNum = 24556
    //val result = myNum.removeFirstLastDigit()
    //println("Resultado: $result")

    //17
    //println("Resultado: ${4 veces 2}")

    //Lista
    //val lista = ArrayList<String>()
    //lista.add("dsadeeeeeee")
    //lista.add("abceeedsd")
    //lista.add("xyzex")

    //20
    //ordenarListaAlfabetico(lista)
    //println("${lista.toString()}")

    //21
    //ordenarListaLongitud(lista)
    //println("${lista.toString()}")

    //22
    //ordenarListaCaracterE(lista)
    //println("${lista.toString()}")

    //23
    //println("es primo? ${esPrimoLista(n = 13)}")

    //24
    /*
    val lista = ArrayList<String>()
    lista.add("Hello")
    lista.add("world")
    lista.add("in")
    lista.add("a")
    lista.add("frame")
    mostrarFrame(lista)
    */

    //25
    //Class book?


    //28
    //val conjuntoBichos = MutableSet<String>("Panda", "Rabbit", "Bear", "Penguin", "Kangaroo")
    //whoTookTheCookie(conjuntoBichos)

    //29
    data class A(val b: String? = null, val c: Int? = null, val d: List<Boolean?>? = null)

    fun testNullables() {
        val a1 = A("B1")
        val a2 = A("", 2)
        val a3 = A(null, null, listOf(null, false, true, null))
        val a4 = A()
        val listA = listOf(a1, a2, a3, a4)
        listA.forEach {

            //println(it.b?.toString())

            if (it.b != null) {
                println(it.b)
            } else if (it.c != null) {
                println(it.c)
            } else if (it.d != null) {
                it.d.forEach {
                    if (it != null) {
                        print(it)
                    }
                }
                println()
            } else {
                println("all null")
            }
        }
    }





}

fun whoTookTheCookie(conjunto: MutableSet<String>){

    conjunto.shuffled()
    var bicho = conjunto.first()
}


fun ordenarListaAlfabetico(lista: ArrayList<String>) = lista.sort()
fun ordenarListaLongitud(lista: ArrayList<String>) = lista.sortBy{it.length}

//it listaString >> it string >> it char
fun ordenarListaCaracterE(lista: ArrayList<String>) = lista.sortBy{ it.count{ it == 'e' } }

//Preguntar
fun esPrimoLista(n:Int): Boolean{

    //listOf(Int).divide({it / num})

    for (i in 2..((n/2) + 1)){
        println("$i")
        if ((n % i) == 0) {
            return false
            break
        }
    }
    return true
}

fun mostrarFrame(lista:ArrayList<String>){

    val masLargo = lista.maxOf { it.length }
    var relleno = 0

    for(i in 0 until masLargo + 4) print("*")

    println()

    for(i in 0 until lista.size){
        relleno = masLargo - lista[i].length
        print("* ${lista[i]}")
        for(j in 0 until relleno){
            print(" ")
        }
        println(" *")
    }

    for(i in 0 until masLargo + 4) print("*")

}


fun holaMundo() = println("Hello Word")
fun numeroPar(n:Int) = (n % 2) == 0
fun esBisiesto(n:Int) = (((n % 4 == 0) && (n % 100 != 0)) || (n % 400 == 0))
fun nEnteros(n:Int) {
    var suma = 0
    for (i in 1..n) {
        suma += i
    }
    println("Resultado de sumar los $n enteros " + suma )
}
fun nMultiplosIJ(n:Int, i:Int, j:Int){
    for(x in 1..n){
        if ((x % i == 0) || (x % j == 0))
            print("$x ")
    }
}
fun tablaMultiplicacion12(n:Int){
    for (i in 0..12){
        println("$n x $i = ${n*i}")
    }
}
fun esPrimo(n:Int): Boolean{

    for (i in 2..((n/2) + 1)){
        println("$i")
        if ((n % i) == 0) {
            return false
            break
        }
    }
    return true
}
fun fizzBuzz(){

    var salida = ""

    for (i in 1..100){
        if (i % 3 == 0) salida += "Fizz"
        if (i % 5 == 0) salida += "Buzz"
        if (salida == "") println("$i") else println("$salida")
        salida = ""
    }
    //la idea era usar el when.
}
fun dividirSinIFWHEN(n:Int,m:Int): Float
    = try {
        ((n / m).toFloat())
    }
    catch (e: ArithmeticException ) {
        Float.NaN
    }
fun Caesar(texto:String, shift:Int): String{
    val resultado = StringBuilder()

    for(i in 0 until texto.length){

        if (texto[i].isUpperCase()){
            var ch = ((texto[i] + shift - 65).toInt() % 26) + 65
            resultado.append(ch.toChar())
        }
        else{
            var ch = ((texto[i] + shift - 97).toInt() % 26) + 97
            resultado.append(ch.toChar())
        }
    }

    return resultado.toString()
}
//fun String.Caesar(texto:String, shift: Int) = Caesar(texto, shift) //Terminar
fun encode(s: String, key: Int): String {

    var encoded = ""

    val sUpper = s.toUpperCase()

    for (i in 0 until s.length)
        encoded +=
            when {
                sUpper[i] !in 'A'..'Z' -> sUpper[i]
                else -> {
                    val c: Char = sUpper[i] + key
                    if (c > 'Z')
                        'A' + (c - 'Z') - 1
                    else
                        c
                }
            }
    return encoded

} //Del profe
fun joinOptions(array: Array<String>): String = array.joinToString(",","[","]")
fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.toUpperCase() else name) + number
fun String.removeFirstLastChar() = this.drop(1).dropLast(1)
fun Int.removeFirstLastDigit() = this.toString().removeFirstLastChar()
infix fun Int.veces(num:Int): Int = this * num