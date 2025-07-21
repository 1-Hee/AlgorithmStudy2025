package problem250718

import java.util.*

// https://www.acmicpc.net/problem/1934
// 최소공배수

fun gcd(a:Int, b:Int):Int {
    return if(b == 0) a else gcd(b, a % b)
}

fun main(){
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val sb = StringBuilder()

    for(i in 0 until n){
        val x:Int = sc.nextInt()
        val y:Int = sc.nextInt()
        val value = (x * y) / gcd(x, y)
        sb.append(value).append("\n")
    }
    print(sb.toString())
}