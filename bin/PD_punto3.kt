
val a = arrayListOf(2,1,3,4,1,2)
var longitudes = arrayListOf<Int>(-1,-1,-1,-1,-1,-1)
var valor = 0

fun main(){
    var maximo = -1
    var aux = -1

    for (i in 0 until a.size-1){
        aux = lis(i)
        if (aux > maximo)
            maximo = aux
    }

    println("$maximo")


    println("${lis(2)}")
}

fun lis(i:Int):Int{

    if (longitudes[i] != -1){
        println("$i ya lo tengo!")
        return longitudes[i];
    }
    else{
        if (i == a.size-1 || a[i] > a[i+1]){
            return 1
        }
        else{
            valor = lis(i+1) + 1
            longitudes.add(i,valor)
            return valor
        }
    }

}