package Punto19

fun main(args: Array<String>) {

    val A = Directory("A")
    val K = Directory("K")
    val R = Directory("R")
    val L = Directory("L")
    val B = Directory("B")
    val P = Directory("P")
    val Q = Directory("Q")

    val f1 = File("f1.dat")
    val f2 = File("f2.dat")
    val f3 = File("f3.dat")
    val f4 = File("f4.dat")
    val f5 = File("f5.dat")
    val f6 = File("f6.dat")

    RootDirectory.add(A)
    A.add(K)
    K.add(f1)
    K.add(R)
    R.add(f2)
    R.add(f3)
    K.add(L)
    L.add(f4)
    RootDirectory.add(B)
    B.add(P)
    P.add(f5)
    P.add(f6)
    B.add(Q)


    RootDirectory.ls()



}