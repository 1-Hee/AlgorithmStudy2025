package problem250718

import java.util.*

// https://www.acmicpc.net/problem/32297
// 문자열을 만들어요

fun main() {
    val sc = Scanner(System.`in`)

    val strLen:Int = sc.nextInt()
    sc.nextLine()
    val inputStr:String = sc.nextLine()

    val max = strLen - 4
    for (i in 0..max) {
        val subSet:String = inputStr.substring(i, i+4)
        if (subSet == "gori"){
            println("YES")
            return
        }
    }
    println("NO")

}
