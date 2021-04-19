import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>){
    //1
    //holaMundo()

    //2
    //println("¿Es par?" + numeroPar(8))

    //3
    //println("¿Es bisiesto?" + esBisiesto(2020))

    //4
    //nEnteros(3)

    //5
    //nMultiplosIJ(n = 17,j = 5, i = 3) //Di vuelta los parametros

    //6
    //tablaMultiplicacion12(n = 2);

    //7
    //println("es primo? ${esPrimo(n = 13)}")

    //8
    //fizzBuzz();

    //9
    //println("${dividirSinIFWHEN(n = 9, m = 3)}")

    //10
    //println("Cadena: ${Caesar("abc", 1)}");

    //11
    //createPyramid(rows = 4)

    //12
    //println("Lista => ${joinOptions(["a","b","c","d"])}")

    //13 foo no tiene llamado

    //14
    //val oracion = "abc"
    //println("${oracion.CaesarExtension(4)}")

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

    //18 Clase piramide con infija
    //val p = Piramide()
    //p.createP(9)

    //19 Separado FileSystem

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

    //23 - PREGUNTAR
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
    //Class book
    /*
    val b1 = Book("Thursday Next", listOf("Jasper Fforde"))
    val b2 = Book("Mort", listOf("Terry Pratchett"))
    val b3 = Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    val biblioteca = Biblioteca(ArrayList())
    biblioteca.add(b1)
    biblioteca.add(b2)
    biblioteca.add(b3)
    biblioteca.mostrarLibrosDeAutorParticular("Terry Pratchett")
    biblioteca.mostrarAutores()
    */

    //26 Resuelto en punto19

    //27 Resuelto en punto19

    //28 PREGUNTAR
    //val conjuntoBichos = MutableSet<String>("Panda", "Rabbit", "Bear", "Penguin", "Kangaroo")
    //whoTookTheCookie(conjuntoBichos)

    //29
    //testNullables()
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

fun createPyramid(rows:Int){
    var cant=1
    var espacio=rows-1
    for(i in 1..rows){
        if(i!=rows) {
            for (k in 1..espacio)
                print(" ")
        }
        espacio--;
        for(j in 1..cant){
            print('*');
        }
        println();
        cant=cant+2
    }
}

fun joinOptions(array: Array<String>): String = array.joinToString(",","[","]")

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false) =
    (if (toUpperCase) name.toUpperCase() else name) + number

fun String.CaesarExtension(shift:Int) = Caesar(this, shift)

fun String.removeFirstLastChar() = this.drop(1).dropLast(1)

fun Int.removeFirstLastDigit() = this.toString().removeFirstLastChar()

infix fun Int.veces(num:Int): Int = this * num

class Piramide () {
    infix fun createP(valor:Int) = createPyramid(valor);
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

/*Ejercicio 25:  Implementar el ejemplo de libros y autores (class Book). Además de mostrar la lista de autores,
mostrar la lista de nombres de libros de un autor en particular*/
class Biblioteca(val books:ArrayList<Book>){
    fun add(b:Book) = books.add(b)

    fun mostrarAutores() = println(books.flatMap {it.autores}.toSet())
   fun mostrarLibrosDeAutorParticular(autor:String) = println(books.filter { it.autores.contains("$autor") }.map(Book::titulo))
}
class Book(val titulo:String, val autores:List<String>)



fun whoTookTheCookie(conjunto: MutableSet<String>){

    conjunto.shuffled()
    var bicho = conjunto.first()
}





//Ejercicio 29
//Simplificar el siguiente algoritmo utilizando safe call operators.
data class A(val b: String? = null, val c: Int? = null, val d: List<Boolean?>? = null)
fun testNullables() {
    val a1 = A("B1")
    val a2 = A(c = 2)
    val a3 = A(d = listOf(null, false, true, null))
    val a4 = A()
    val listA = listOf(a1, a2, a3, a4)

    listA.forEach {
        //Todo lo que esta dentro del let es cuando it.b NO es nulo.
        //Todo lo que esta dentro del run es cuando it.b ES nulo.
        it.b?.let { println(it) } ?: run {
            it.c?.let { println(it) } ?: run {
                it.d?.let { it.forEach { it?.let{print(it)} } ; println() } ?: run { println("all null") }
            }
        }
    }
}