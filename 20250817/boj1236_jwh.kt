package problem250817

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

// https://www.acmicpc.net/problem/1236

fun main(){
    // System.setIn(FileInputStream("data/boj1236.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val r:Int = st.nextToken().toInt()
    val c:Int = st.nextToken().toInt()

    // array init
    val charArrays:Array<CharArray> = Array(r) { CharArray(c) { '.' } }
    for(i in 0 until r){
        val str = br.readLine()
        for(j in 0 until c){
            charArrays[i][j] = str[j]
        }
    }

    // row count
    var rCnt = 0;
    for(i in 0 until r){
        var hasEmployee = false
        for(j in 0 until c) {
            val element = charArrays[i][j]
            if(element == 'X') {
                hasEmployee  = true
                break
            }
        }
        if(!hasEmployee) rCnt++
    }

    var cCnt = 0
    for(i in 0 until c){
        var hasEmployee:Boolean = false
        for(j in 0 until r) {
            val element = charArrays[j][i]
            if(element == 'X') {
                hasEmployee  = true
                break
            }
        }
        if(!hasEmployee) cCnt++
    }

    val maxCnt:Int = max(rCnt, cCnt)
    println(maxCnt)

}