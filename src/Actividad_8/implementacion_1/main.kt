package Actividad_8.implementacion_1

import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

fun main(){
    //val begin = System.currentTimeMillis()

    var puntosOrdenadosPorX = generarPuntos(pow(10.0,3.0).toInt())
    //mostrarPuntos(puntos)
    puntosOrdenadosPorX.sortBy { punto -> punto.x }
    //mostrarPuntos(puntos)

    mostrarSolucion(algoritmoBasico(puntosOrdenadosPorX))
    mostrarSolucion(dyc1(puntosOrdenadosPorX))

    var puntosOrdenadosPorY = puntosOrdenadosPorX
    puntosOrdenadosPorY.sortBy { punto -> punto.y }
    mostrarSolucion(dyc2(puntosOrdenadosPorX, puntosOrdenadosPorY))




    //val end = System.currentTimeMillis()
    //println("Tiempo: ${(end-begin)/1000}")
}

/*
    Clase que modela un punto
*/
class Punto (var x: Int, var y: Int){
    fun mostrarPunto() = "($x,$y)"
}

/*
    Clase que modela un par, con key y value
    Esta clase es modelada porque la clase pair no acepta modificar los valores
 */
class Par(var k: Punto, var v: Punto){
    fun mostrarPar() = "${k.mostrarPunto()} ${v.mostrarPunto()}" //Quizas en mostrar solución hacemos solucion.first.mostrarPar
}


/*
    Muestra las variables gloables, min y distanciaMin
 */
fun mostrarSolucion(solucion: Pair<Par, Double>){
    println("------------------------------")
    println("Par minimo: ${solucion.first.k.mostrarPunto()} / ${solucion.first.v.mostrarPunto()}")
    println("Distancia minima: ${solucion.second}")
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
fun generarPuntos(cant: Int): ArrayList<Punto> {
    val puntos = arrayListOf<Punto>()
    var punto : Punto
    var n = cant

    while(n > 0){
        punto = Punto(Random.nextInt(0, pow(10.0,9.0).toInt()), Random.nextInt(0, pow(10.0,9.0).toInt()))

        if(!puntos.any { par -> ((par.x == punto.x) && (par.y == punto.y)) }) {
            --n
            puntos.add(punto)
        }
    }
    return puntos
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
   Algoritmo basico para encontrar la distancia minima entre un conjunto de puntos O(n^2)
   Convenciones
     -> No hay 2 puntos iguales
     -> Previamente esta ordenado
 */
fun algoritmoBasico(puntos: ArrayList<Punto>) : Pair<Par, Double> {
    var par = Par(Punto(0,0), Punto(0,0))
    var distanciaPar = Double.MIN_VALUE
    var min = Par(Punto(0,0), Punto(0,0))
    var distanciaMin = Double.MAX_VALUE

    for(i in puntos.indices){
        par.k = puntos[i]
        for(j in i+1 until puntos.size){
            par.v = puntos[j]
            distanciaPar = distancia(par.k, par.v)

            if (distanciaPar < distanciaMin) {
                min.k = par.k
                min.v = par.v
                distanciaMin = distanciaPar
            }
        }
    }

    return Pair(min, distanciaMin)
}


/*
    Primera implementación con dividir y conquistar
    Se asume que los puntos estan ordenados por X
    Se ordena por Y a la hora de ver la franja
*/
fun dyc1(puntosOrdenadosPorX: ArrayList<Punto>): Pair<Par, Double> {
    val d : Pair<Par, Double>
    val d3 : Pair<Par, Double>
    val mitadArreglo : Int
    val d1 : Pair<Par, Double>
    val d2 : Pair<Par, Double>

    if(puntosOrdenadosPorX.size <= 3)
        return algoritmoBasico(puntosOrdenadosPorX)
    else {
        mitadArreglo = puntosOrdenadosPorX.size / 2
        var mitadFranja = puntosOrdenadosPorX[mitadArreglo].x

        var izquierdaX = puntosOrdenadosPorX.slice(0 until mitadArreglo) as ArrayList<Punto>
        var derechaX = puntosOrdenadosPorX.slice(mitadArreglo until (puntosOrdenadosPorX.size)) as ArrayList<Punto>

        d1 = dyc1(izquierdaX)
        d2 = dyc1(derechaX)

        d = minimo(d1,d2)
        var franja = puntosOrdenadosPorX.filter { abs(mitadFranja - it.x) < d.second } as ArrayList<Punto>//Creo la franja con los puntos que estan dentro
        franja.sortBy { punto -> punto.y } //Ordeno por Y
        d3 = minimoEnFranja(franja.toTypedArray(), d.second) //Consigo el minimo en la franja
    }

    return minimo(d,d3)
}

fun minimo(min1: Pair<Par, Double>, min2: Pair<Par, Double>):Pair<Par, Double>{
    var toReturn : Pair<Par, Double>

    if (min1.second < min2.second) toReturn = min1 else toReturn = min2

    return toReturn
}

fun minimoEnFranja(franja: Array<Punto>, d: Double):Pair<Par, Double>{
    var distanciaMinFranja = Double.MAX_VALUE
    var min = Par(Punto(0,0), Punto(0,0))

    for(i in 0 until franja.size-1){
        var puntoActual = franja[i]
        var puntoSiguiente = franja[i+1]

        var j = i+1
        while((j < franja.size) && (puntoSiguiente.y < (puntoActual.y + d))){
            puntoSiguiente = franja[j]

            var distancia = distancia(puntoActual, puntoSiguiente)
            if(distancia < distanciaMinFranja){
                min.k = puntoActual
                min.v = puntoSiguiente
                distanciaMinFranja = distancia
            }

            j++
        }
    }

    return Pair(min, distanciaMinFranja)
}

/*
   Segunda implementación con dividir y conquistar
   Se asume que los puntos estan ordenados por X
   A diferencia, se ordena por Y previamente
 */

fun dyc2(puntosOrdenadosPorX: ArrayList<Punto>, puntosOrdenadosPorY: ArrayList<Punto>): Pair<Par, Double>{

    var izquierdaY = arrayListOf<Punto>()
    var derechaY = arrayListOf<Punto>()

    val d : Pair<Par, Double>
    val d3 : Pair<Par, Double>
    val mitadArreglo : Int
    val d1 : Pair<Par, Double>
    val d2 : Pair<Par, Double>

    if(puntosOrdenadosPorX.size <= 3)
        return algoritmoBasico(puntosOrdenadosPorX)
    else {
        mitadArreglo = puntosOrdenadosPorX.size / 2
        var mitadFranja = puntosOrdenadosPorX[mitadArreglo].x

        //mitades con respecto a X
        var izquierdaX = puntosOrdenadosPorX.slice(0 until mitadArreglo) as ArrayList<Punto>
        var derechaX = puntosOrdenadosPorX.slice(mitadArreglo until (puntosOrdenadosPorX.size)) as ArrayList<Punto>

        //mitades con respecto a Y
        izquierdaY.filter { it.x < mitadFranja }
        derechaY.filter { it.x >= mitadFranja }

        d1 = dyc2(izquierdaX, izquierdaY)
        d2 = dyc2(derechaX, derechaY)

        d = minimo(d1,d2)

        var franja = puntosOrdenadosPorY.filter { abs(mitadFranja - it.x) < d.second } as ArrayList<Punto> //Creo la franja con los puntos que estan dentro
        d3 = minimoEnFranja(franja.toTypedArray(), d.second) //Consigo el minimo en la franja
    }

    return minimo(d,d3)

}




