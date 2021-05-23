package Actividad_8.implementacion_1

import kotlin.math.ceil
import kotlin.math.min
import kotlin.math.pow
import kotlin.random.Random



fun main(){
    val begin = System.currentTimeMillis()

    var puntos = generarPuntos(10)

    for(i in puntos.indices){
        print("(${puntos.elementAt(i).x},${puntos.elementAt(i).y}) ")
    }

    puntos.sortBy { punto -> punto.x }

    println()
    for(i in puntos.indices){
        print("(${puntos.elementAt(i).x},${puntos.elementAt(i).y}) ")
    }

    println()

    algoritmoBasico(puntos)

    val end = System.currentTimeMillis()

    println("Tiempo: ${(end-begin)/1000}")

}

class Punto (var x: Int, var y: Int){
    fun mostrarPunto() = "($x,$y)"
}

fun generarPuntos(n: Int): Array<Punto> {
    val puntos = mutableSetOf<Punto>()
    var n = 10
    var punto : Punto

    //Random.nextInt(0, pow(10.0,9.0).toInt()

    while(n > 0){
        punto = Punto(Random.nextInt(0, 10), Random.nextInt(0, 10))
        if(!puntos.contains(punto)){
            --n
            puntos.add(punto)
        }
    }
    return puntos.toTypedArray()
}

fun distancia(p1:Punto, p2:Punto):Double{
    return kotlin.math.sqrt(
        ((p2.x - p1.x).toDouble().pow(2.0) + (p2.y - p1.y).toDouble().pow(2.0))
    )
}

/*
   Algoritmo basico para encontrar la distancia minima entre un conjunto de puntos
 */
fun algoritmoBasico(puntos: Array<Punto>){
    var par = arrayOf(Punto(0,0), Punto(0,0))
    var min = arrayOf(Punto(0,0), Punto(90,90))
    var distanciaMin = 9999.99
    var distanciaPar = 0.0

    for(i in puntos.indices){
        par[0] = puntos[i]
        for(j in i+1 until puntos.size){
            par[1] = puntos[j]

            if((par[0].x != par[1].x) && (par[0].y != par[1].y)){
                distanciaPar = distancia(par[0], par[1])

                if (distanciaPar < distanciaMin) {
                    min[0] = par[0]
                    min[1] = par[1]
                    distanciaMin = distanciaPar
                    //println("P: ${min[0].mostrarPunto()} / ${min[1].mostrarPunto()}")
                    //println("D: $distanciaMin")
                }
            }
        }
    }

    println("Par minimo: ${min[0].mostrarPunto()} / ${min[1].mostrarPunto()}")
    println("Distancia minima: $distanciaMin")
}

/*
    Primera implementación con dividir y conquistar
    Se asume que los puntos estan ordenados por X
*/
/*
fun dyc1(puntos: ArrayList<Punto>):Double {
    if(puntos.size es pequeño)
        return algoritmoBasico(puntos)
    else {
        val m = puntos.size / 2
        val d1 = dyc1(puntos.slice(0..(m - 1)))
        val d2 = dyc1(puntos.slice(m..(puntos.size)))
        val d = min(d1, d2)
        val Franja = crearFranja(puntos, m, d)
        val d3 = minimoEnFranja(Franja)
    }

    return min(d, d3)
}

fun crearFranja(puntos: ArrayList<Punto>, m:Int, d:Double): MutableList<Punto> {
    var Franja : MutableList<Punto> = ArrayList<Punto>()
    for(i in ceil(m-d).toInt() until ceil(m+d).toInt()){
        Franja.add(puntos[i])
    }

    // Ordenar por Y?
    return Franja
}

fun minimoEnFranja(franja: ArrayList<Punto>){

*/


