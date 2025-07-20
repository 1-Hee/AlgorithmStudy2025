package problem250718

import java.util.*
import kotlin.math.abs
import kotlin.math.min

// https://www.acmicpc.net/problem/13866
// 팀 나누기

class Perm(
    val N:Int,
    val R:Int,
    val numbers:Array<Int>,
    val inputs:Array<Int>,
    val isSelected:Array<Boolean>
) {

    var minValue:Int = Int.MAX_VALUE

    fun perm(cnt:Int) {
        if(cnt == R){
            // println("array => ${numbers.contentToString()}")
            val frontArr:Array<Int> = numbers.copyOfRange(0, 2)
            val endArr:Array<Int> = numbers.copyOfRange(2, numbers.size)
            val gap:Int = frontArr.sum() - endArr.sum()
            val absGap = abs(gap)
            val mNewMin = min(minValue, absGap)
            minValue = mNewMin
            return
        }
        for (i in 0..<N) {
            if(isSelected[i]) continue
            numbers[cnt] = inputs[i]
            isSelected[i] = true
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
}


fun main() {
    val N = 4
    val R = 4

    val sc = Scanner(System.`in`)
    val numbers:Array<Int> = Array<Int>(N, init = { 0 })
    val inputs:Array<Int> = Array(N, {0})
    val isSelected:Array<Boolean> = Array(N, {false})

    for(i in inputs.indices) {
        inputs[i] = sc.nextInt()
    }

    val perm = Perm(N, R, numbers, inputs, isSelected)
    perm.perm(0)
    println(perm.minValue)
}

