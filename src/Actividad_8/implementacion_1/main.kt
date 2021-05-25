package Actividad_8.implementacion_1

import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random

var min = arrayListOf<Punto>()
var distanciaMin : Double = 0.0

fun main(){
    val begin = System.currentTimeMillis()

    inicializacion()
    var puntos = generarPuntos(pow(10.0,3.0).toInt())
    mostrarPuntos(puntos)
    puntos.sortBy { punto -> punto.x }
    mostrarPuntos(puntos)
    distanciaMin = algoritmoBasico(puntos)
    mostrarSolucion()

    println("Minimo DYC1 ${dyc1(puntos)}")

    val end = System.currentTimeMillis()

    println("Tiempo: ${(end-begin)/1000}")
}

/*
    Clase que modela un punto
*/
class Punto (var x: Int, var y: Int){
    fun mostrarPunto() = "($x,$y)"
}

/*
    Muestra las variables gloables, min y distanciaMin
 */
fun mostrarSolucion(){
    println("------------------------------")
    println("Par minimo: ${min[0].mostrarPunto()} / ${min[1].mostrarPunto()}")
    println("Distancia minima: $distanciaMin")
    println("------------------------------")
}

/*
    Muestra el arreglo de puntos
 */
fun mostrarPuntos(puntos: Array<Punto>){
    for(i in puntos.indices){
        print("${puntos.elementAt(i).mostrarPunto()}")
    }
    println()
}

/*
    Genera un array, de la cantidad pasada por parametros, de puntos no repetidos
 */
fun generarPuntos(cant: Int): Array<Punto> {
    val puntos = mutableSetOf<Punto>()
    var punto : Punto
    var n = cant

    while(n > 0){
        punto = Punto(Random.nextInt(0, pow(10.0,9.0).toInt()), Random.nextInt(0, pow(10.0,9.0).toInt()))

        if(!puntos.any { par -> ((par.x == punto.x) && (par.y == punto.y)) }) {
            --n
            puntos.add(punto)
        }
    }
    return puntos.toTypedArray()
}

/*
    Calcula la distancia entre dos puntos
 */
fun distancia(p1:Punto, p2:Punto):Double{
    return kotlin.math.sqrt(
        ((p2.x - p1.x).toDouble().pow(2.0) + (p2.y - p1.y).toDouble().pow(2.0))
    )
}

/*
    Inicialización de las variables min y distanciaMin
 */
fun inicializacion(){
    min.add(0, Punto(0,0))
    min.add(1, Punto(0,0))
    distanciaMin = Double.MAX_VALUE
}


/*
   Algoritmo basico para encontrar la distancia minima entre un conjunto de puntos O(n^2)
   Convenciones
     -> No hay 2 puntos iguales
     -> Previamente esta ordenado
 */
fun algoritmoBasico(puntos: Array<Punto>) : Double{
    var par = arrayOf(Punto(0,0), Punto(0,0))
    var distanciaPar = Double.MIN_VALUE

    for(i in puntos.indices){
        par[0] = puntos[i]
        for(j in i+1 until puntos.size){
            par[1] = puntos[j]
            distanciaPar = distancia(par[0], par[1])

            if (distanciaPar < distanciaMin) {
                min[0] = par[0]
                min[1] = par[1]
                distanciaMin = distanciaPar
            }
        }
    }
    return distanciaMin
}


/*
    Primera implementación con dividir y conquistar
    Se asume que los puntos estan ordenados por X
*/
fun dyc1(puntos: Array<Punto>): Double {
    val d : Double
    val d3 : Double
    val m : Int
    val d1 : Double
    val d2 : Double

    if(puntos.size <= 3)
        return algoritmoBasico(puntos)
    else {
        m = puntos.size / 2
        d1 = dyc1(puntos.slice(0 until m).toTypedArray())
        d2 = dyc1(puntos.slice(m until (puntos.size)).toTypedArray())
        d = min(d1, d2)
        var franja = crearFranja(puntos, m, d) //Creo la franja con los puntos que estan dentro
        franja.sortBy { punto -> punto.y } //Ordeno por Y
        d3 = minimoEnFranja(franja.toTypedArray(), d) //Consigo el minimo en la franja
    }

    return min(d, d3)
}

fun crearFranja(puntos: Array<Punto>, m:Int, d:Double): MutableList<Punto> {
    var franja : MutableList<Punto> = ArrayList<Punto>()

    for(punto in puntos){
        if(abs(punto.x - m) < d){
            franja.add(punto)
        }
    }
    return franja
}

fun minimoEnFranja(franja: Array<Punto>, d: Double):Double{

    var distanciaMinFranja = Double.MAX_VALUE

    for(i in 0 until franja.size-1){
        var puntoActual = franja[i]
        var puntoSiguiente = franja[i+1]

        var j = i+1
        while((j < franja.size) && (puntoSiguiente.y < (puntoActual.y + d))){
            puntoSiguiente = franja[j]

            var distancia = distancia(puntoActual, puntoSiguiente)
            if(distancia < distanciaMinFranja){
                distanciaMinFranja = distancia
            }

            j++
        }
    }

    return distanciaMinFranja
}






