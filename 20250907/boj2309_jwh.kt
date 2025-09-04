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

fun comb(idx:Int, cnt:Int) {

    if(cnt == 2){
        var x = sumTotal
        slice.forEach { item ->
            x -= item
        }

        val sliceSet = slice.toSet()

        if(x == 100){
            val numList:MutableList<Int> = mutableListOf()
            for(i in 0 until N){
                val num = numbers[i]
                if(!sliceSet.contains(num)){
                    numList.add(num)
                }
            }
            nanJList = numList.toList()
        }
        return
    }

    for(i in idx until N){
        slice[cnt] = numbers[i]
        comb(i+1, cnt+1)
    }

}

fun main(){
    System.setIn(FileInputStream("data/250907/boj2309.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    nanJList = listOf()
    numbers = IntArray(N) { 0 }
    slice = IntArray(N-R) { 0 }

    for(i in 0 until N){
        val numX:Int = br.readLine().toInt()
        numbers[i] = numX
    }

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