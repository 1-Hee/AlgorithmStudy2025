package problem250810

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

// https://www.acmicpc.net/problem/2851

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = 10

    val cumulativeArray:Array<Int> = Array(n) { 0 }

    for(i in 0 until n){
        val point = br.readLine().toInt()
        // 누적합을 계산
        if(i == 0){
            cumulativeArray[i] = point
        } else {
            cumulativeArray[i] = cumulativeArray[i-1] + point
        }
    }

    var idx = 0;
    var min = Int.MAX_VALUE
    val mario = 100

    // 배열 10개 고정이니까 그냥 다 검사!
    cumulativeArray.forEachIndexed { index, i ->
        val gap = abs(mario - i)
        if(gap <= min) { // 최소 값 찾기
            min = gap
            idx = index
        }
    }

    println(cumulativeArray[idx]) // print result

}