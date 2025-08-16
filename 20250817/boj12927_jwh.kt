package problem250817


import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

// https://www.acmicpc.net/problem/12927

fun main() {
    // System.setIn(FileInputStream("data/boj12927.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    // i번 스위치는 i의 배수 번호를 가지는 전구의 상태를 모두 반전

    val inputStr:String = br.readLine()
    val buffer = BooleanArray(inputStr.length) { false }

    for(i in inputStr.indices){
        val charX:Char = inputStr[i]
        buffer[i] = charX == 'Y'
    }

    // println(buffer.contentToString())

    var cnt = 0
    for(i in buffer.indices){
        val bulbX:Boolean = buffer[i]
        if(!bulbX) continue
        else {
            buffer[i] = false
            cnt++
            for(j in i+1 until buffer.size){
                if((j+1) % (i+1) == 0){
                    // println(j+1)
                    buffer[j] = !buffer[j]
                }
            }
        }
    }

    println(cnt)

}