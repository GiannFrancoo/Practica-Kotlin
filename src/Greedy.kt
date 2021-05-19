
fun main(){

    /*
    //x = -1, 4, 2
    //y = 3, 4, -10
    //p = 2, 0, 1
    //10 + 6 + 16 = 32
    var x:Array<Int> = arrayOf<Int>(-1,4,2);
    var y:Array<Int> = arrayOf<Int>(3,4,-10);
    */
    var x:Array<Int> = arrayOf<Int>(-2,5,-1);
    var y:Array<Int> = arrayOf<Int>(3,4,-1);
    /*
    x - y
    -2 * -1
    -1 * 3
    5 * 4
    */


    println("${algoritmo_greedy(x,y)}")

}

fun algoritmo_greedy(x:Array<Int>, y:Array<Int>):Int {

    var usadosX:Array<Boolean> = arrayOf<Boolean>(false,false,false)
    var usadosY:Array<Boolean> = arrayOf<Boolean>(false,false,false)
    var p:Array<Int> = arrayOf(-1,-1,-1)
    var indiceMinX = -1
    var indiceMinY = -1
    var total = 0

    for(k in x.indices){
        indiceMinX = seleccionar(x,usadosX)
        indiceMinY = seleccionar(y,usadosY)
        p[indiceMinX] = indiceMinY
    }

    for(i in x.indices){
        total += x[i] * y[p[i]]
    }

    return total
}


fun seleccionar(x:Array<Int>, usadosX:Array<Boolean>): Int{
    var min = Int.MAX_VALUE
    var indiceMinimo = 0
    for(j in x.indices){
        if(!usadosX[j]){
            if(x[j] < min){
                indiceMinimo = j
                min = x[indiceMinimo]
            }
        }
    }
    usadosX[indiceMinimo] = true
    return indiceMinimo
}
