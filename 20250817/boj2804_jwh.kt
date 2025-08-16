package problem250817

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/2804

fun main() {
    // System.setIn(FileInputStream("data/boj2804.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val sb = StringBuilder()

    val str1 = st.nextToken()
    val str2 = st.nextToken()
    val rowSize:Int = str2.length
    val colSize:Int = str1.length

    val crossMap:Array<CharArray> = Array(rowSize) {
        CharArray(colSize) { '.' }
    }

    var r = -1
    var c = -1
    var isFound = false

    for(i in str1.indices){
        isFound = false
        val charX = str1[i]
        for(j in str2.indices){
            val charY = str2[j]
            if(charX == charY) {
                c = i
                r = j
                isFound = true
                break
            }
        }
        if(isFound) break
    }

    // println("x : $r , y : $c  / charX = ${str1[r]} , charY = ${str2[c]}")
    // step1. 가로 단어 채우기
    for(j in str1.indices){
        crossMap[r][j] = str1[j]
    }

    // step2. 세로 단어 채우기
    for(i in str2.indices){
        crossMap[i][c] = str2[i]
    }

    for(i in 0 until rowSize){
        for(j in 0 until colSize) {
            sb.append(crossMap[i][j])
        }
        sb.append("\n")
    }
    println(sb.toString())

}