package problem250817

import java.io.BufferedReader
import java.io.InputStreamReader

// https://www.acmicpc.net/problem/1110

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val numBuffer: Array<Int> = Array(2) { 0 }
    var inputStr:String = br.readLine()

    if(inputStr.length < 2){
        numBuffer[0] = 0
        numBuffer[1] = inputStr.toInt()
        inputStr = String.format("%d%d", numBuffer[0], numBuffer[1])
    } else {
        val num1:Int = (inputStr[0] - '0').toInt()
        val num2:Int = (inputStr[1] - '0').toInt()
        numBuffer[0] = num1
        numBuffer[1] = num2
    }

    var cnt = 0;
    do {
        cnt++;
        val left = numBuffer[0]
        val right = numBuffer[1]
        val newNum = (left + right) % 10

        numBuffer[0] = right
        numBuffer[1] = newNum
        val numX = String.format("%d%d", right, newNum)
        // println("[$cnt] $numX")
        if(numX == inputStr) break
    }while (true)

    // println("count : $cnt")
    println(cnt)


}
