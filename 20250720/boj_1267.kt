package problem250718

import java.util.*

//https://www.acmicpc.net/problem/1267
// 핸드폰 요금

fun main() {
    val sc = Scanner(System.`in`)
    val n = sc.nextInt()
    val callTimes:Array<Int> = Array(n, {0})
    for(i in 0 until n)  {
        callTimes[i] = sc.nextInt()
    }

    var ysTotalCost = 0
    var msTotalCost = 0

    callTimes.forEach { time ->
        // ys 계산
        var ysUnit = (time / 30)
        if(time % 30 >= 0){
            ysUnit += 1
        }
        ysTotalCost += (ysUnit * 10) // 영식 요금 계산

        // ms 계산
        var msUnit = (time / 60)
        if(time % 60 >= 0){
            msUnit += 1
        }
        msTotalCost += (msUnit * 15) // 민식 요금 계산
    }

    if(ysTotalCost > msTotalCost) {
        println("M $msTotalCost")
    } else if(msTotalCost > ysTotalCost) {
        println("Y $ysTotalCost")
    } else {
        println("Y M $ysTotalCost")
    }
}

