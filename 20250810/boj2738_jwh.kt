package problem250810

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

// https://www.acmicpc.net/problem/2738

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val sb = StringBuilder()

    val Row:Int = st.nextToken().toInt()
    val Col:Int = st.nextToken().toInt()

    // define kotlin's array
    val arrays:Array<Array<Int>> = Array(Row) { Array(Col) { 0 } }

    // input first array
    for(i in 0 until Row){
        st = StringTokenizer(br.readLine())
        for(j in 0 until Col){
            val valueX:Int = st.nextToken().toInt()
            arrays[i][j] = valueX
        }
    }

    // add second array
    for(i in 0 until Row){
        st = StringTokenizer(br.readLine())
        for(j in 0 until Col){
            val valueX:Int = st.nextToken().toInt()
            arrays[i][j] += valueX
        }
    }

    // create result string
    arrays.forEach { rows ->
        val len:Int = rows.size - 1
        rows.forEachIndexed { index, i ->
            sb.append(i)
            if(index < len) {
                sb.append(' ')
            }
        }
        sb.append("\n")
    }

    println(sb.toString()) // output

}