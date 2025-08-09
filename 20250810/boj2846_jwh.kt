package problem250810

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

// https://www.acmicpc.net/problem/2846


fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n:Int = br.readLine().toInt()
    val st = StringTokenizer(br.readLine())
    val buffer:Array<Int> = Array(n) { st.nextToken().toInt() }

    var maxValue = 0
    var start = buffer[0]
    for(i in 1 until n){
        if(buffer[i] > buffer[i-1]){
            maxValue = max(maxValue, buffer[i] - start)
        }else {
            start = buffer[i]
        }
    }

    println(maxValue)


}


