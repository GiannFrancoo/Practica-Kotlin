
val saltosPosibles = arrayOf(1,2)
val D = 3

fun main() {
    println("${saltar(0)}")
}

fun saltar(i:Int):Int{

    if (i == D || D == 0){
        return 1
    }
    else
    {
        if((D-i) == saltosPosibles.minOrNull()){
            return 1
        }
        else{
            for(j in 0 until saltosPosibles.size){
                return saltar(i + saltosPosibles[j])
            }
        }
    }
    return 0
}