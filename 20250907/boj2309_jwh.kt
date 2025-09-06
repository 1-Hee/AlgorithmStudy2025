package problem250907

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

private var numbers:IntArray = intArrayOf()
private var slice:IntArray = intArrayOf()
private val N:Int = 9
private val R:Int = 7
private var sumTotal:Int = 0
private var nanJList:List<Int> = listOf()

private fun comb(idx:Int, cnt:Int) {

    if(cnt == 2){
        var x = sumTotal
        slice.forEach { item ->
            x -= item
        }

        val sliceSet = slice.toSet() // 담아온 배열 2개 짜리를 Set으로 만듦.

        if(x == 100){
            val numList:MutableList<Int> = mutableListOf()
            for(i in 0 until N){
                val num = numbers[i]
                if(!sliceSet.contains(num)){ // Set에 있는지 확인하려고!
                    numList.add(num)
                }
            }
            nanJList = numList.toList()
        }
        return
    }

    // 조합 로직
    for(i in idx until N){
        slice[cnt] = numbers[i]
        comb(i+1, cnt+1)
    }

}


/**
 * https://www.acmicpc.net/problem/2309
 * 일곱 난쟁이
 *
 * 9C7인 경우에서 각 요소의 합이 100인 경우를 찾아라.
 */

fun main(){
    System.setIn(FileInputStream("data/250907/boj2309.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    /**
     * idea : 9C7 이나, 9C2나 수학적으로는 총 경우의 수가 36 임
     * 조합 공식!
     * nCr => n! / r!(n-r)!
     * = 9 x 8 x 7 .. / 7 x 6 .. x (2 x 1)
     * = 72 / 2 = 36
     *
     * = 9 x 8 x 7 ... / 2 x 1 x ( 7 x 6 x 5 ... )
     * = 72 / 2 = 36
     */

    nanJList = listOf()
    numbers = IntArray(N) { 0 }
    slice = IntArray(N-R) { 0 } // 9C7가 아니라 9C2로 해보려고!

    for(i in 0 until N){
        val numX:Int = br.readLine().toInt()
        numbers[i] = numX
    }

    // 전체 합을 구함, 전체 합에서 2개를 뺐을 때, 100이 되는 경우를 찾으려고!
    sumTotal = numbers.sum()

    comb(0, 0)

    val list = nanJList.sorted()
    list.forEach {
        println(it)
    }
}

/*
private var nanJList:IntArray = IntArray(7)

fun comb(idx:Int, cnt:Int, input:IntArray, numbers:IntArray) {
    if(cnt == 7){
        if(numbers.sum() == 100){
            nanJList = numbers.clone()
        }
        return
    }

    for(i in idx until 9) {
        numbers[cnt] = input[i]
        comb(i+1, cnt+1, input, numbers)
    }


}

fun main(){
    System.setIn(FileInputStream("data/250907/boj2309.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val input = IntArray(9) { 0 }
    val number = IntArray(7) { 0 }

    for(i in 0 until 9){
        val num:Int = br.readLine().toInt()
        input[i] = num
    }

    comb(0, 0, input, number)

    nanJList.sort()

    nanJList.forEach { item ->
        println(item)
    }

}

 */