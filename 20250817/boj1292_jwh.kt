package problem250817

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/1292

fun main(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val startIdx:Int = st.nextToken().toInt() - 1
    val endIdx:Int = st.nextToken().toInt() // exclusive 하게 사용!

    val buffer:IntArray = IntArray(1000) { 0 }

    // i : 45
    // value : 990
//    for(i in 0 until 45){
//        buffer[i] = i+1
//        for(j in 1 until i){
//            buffer[j] = i+1
//        }
//    }
    var p = 0
    for (i in 0..44) {       // 45까지면 1000칸 이상
        repeat(i+1) {
            if (p >= buffer.size) return@repeat
            buffer[p++] = (i+1)
        }
    }

    // slice buffer
    val slicedBuffer = buffer.copyOfRange(startIdx, endIdx)
    val sumX = slicedBuffer.sum()
    println(sumX)

}
