package problem250718

import java.util.*

// https://www.acmicpc.net/problem/15182
// Byte Me!

fun main() {

    val sc = Scanner(System.`in`)
    val n = sc.nextInt()

    val matrix:MutableList<List<Int>> = mutableListOf()

    for(i in 0 until n){
        val mBitList:MutableList<Int> = mutableListOf()

        for(j in 0..7){
            val x = sc.nextInt()
            mBitList.add(x)
        }

        matrix.add(mBitList)
    }

    val mParityList:MutableList<Int> = mutableListOf()
    for(j in 0..7){
        val x = sc.nextInt()
        mParityList.add(x)
    }
    matrix.add(mParityList)

    var oddCnt = 0;
    var evenCnt = 0
    for(i in 0 until n){
        val dataList:List<Int> = matrix[i]
        if(dataList.sum().and(1) > 0) oddCnt++ else evenCnt++
    }

    val parityFlag:Boolean = oddCnt > evenCnt
    println(if(parityFlag) "Odd" else "Even")

    var findCnt = 0
    for(c in 0..7){
        var sum = 0
        for(r in 0 until (n+1)) {
            sum += matrix[r][c]
            if(findCnt == 0 && r < n){
                val dataList:List<Int> = matrix[r]
                val dataFlag = dataList.sum().and(1) > 0
                if(dataFlag != parityFlag){ // Byte 검출
                    println("Byte ${r+1} is broken")
                    findCnt++
                }
            }
        }
        val posFlag = sum.and(1) > 0
        if(posFlag != parityFlag) { // 위치 검출
            println("Bit ${c+1} is broken")
            return
        }
    }

}