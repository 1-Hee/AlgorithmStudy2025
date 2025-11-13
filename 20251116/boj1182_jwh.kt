package problem251116

import java.io.*;
import java.util.*;

private var nCase:Int = 0

fun findCase(idx:Int, N: Int, S:Int, numbers:IntArray, isSelected:BooleanArray){
    if(idx == N){
        val caseList:MutableList<Int> = mutableListOf()
        for(i in isSelected.indices){
            if(isSelected[i]){
                caseList.add(numbers[i])
            }
        }
        if(caseList.isEmpty()) return
        val sumVal:Int = caseList.sum()
        if(sumVal == S){
            nCase ++
        }
        return
    }
    isSelected[idx] = true
    findCase(idx+1, N, S, numbers, isSelected)

    isSelected[idx] = false
    findCase(idx+1, N, S, numbers, isSelected)
}

fun main(){
    System.setIn(FileInputStream("data/251116/boj1182.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N: Int = st.nextToken().toInt()
    val S: Int = st.nextToken().toInt()

    val numbers = IntArray(N) { 0 }
    val isSelected = BooleanArray(N) { false }
    st = StringTokenizer(br.readLine())
    for(i in 0 until N){
        val element: Int = st.nextToken().toInt()
        numbers[i] = element
    }

    nCase = 0
    findCase(0, N, S, numbers, isSelected)
    println(nCase)
}