package problem251123

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Stack

class ComplementMachine(
    private val num:Int,
    private val size:Int = 32
) {

    // Hamming distance
    fun getHammingDistance() : Int {
        val x = (num.inv()) + 1
        val arr1 = getBinArray(num)
        val arr2 = getBinArray(x)
        var cnt = 0
        for(i in arr1.indices){
            if(arr1[i] != arr2[i]) cnt++
        }

        return cnt
    }

    private fun getBinArray(value:Int) : IntArray {
        var num = value
        val numStack = Stack<Int>()
        var i = 0
        while(i < size){
            i++
            val element = num.and(0x01)
            numStack.push(element)
            num = num.shr(1)
        }

        val numArray = IntArray(32) { 0 }
        var idx = 0
        while (numStack.isNotEmpty()){
            val element = numStack.pop()
            numArray[idx++] = element
        }
        return numArray
    }
}

fun main(){
    System.setIn(FileInputStream("data/251123/boj24389.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val num = br.readLine().toInt()
    val cMachine = ComplementMachine(num)
    println(cMachine.getHammingDistance())
}