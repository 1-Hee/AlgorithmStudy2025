package problem251116

import java.io.*
import java.util.*
import kotlin.math.abs
import kotlin.math.min

class DoYoungChef(
    private val N:Int,
    private val cart:Array<String>
) {
    fun getBestRecipe() : Int {
        subset(0)
        return _minDiff
    }

    private var _minDiff = Int.MAX_VALUE
    private val isSelected = BooleanArray(N) { false }

    private fun subset(idx:Int) {
        if(idx == N){

            var flag = false
            val cookBook:MutableList<String> = mutableListOf()
            for(i in 0..<N){
                if(isSelected[i]){
                    flag = true
                    cookBook.add(cart[i])
                }
            }
            if(!flag) return

            // S : 신맛
            // B : 쓴맛
            // 신맛은 사용한 재료의 곱, 쓴맛은 합
            var sSum = 1
            var bSum = 0
            var st:StringTokenizer
            cookBook.forEach {
                st = StringTokenizer(it)
                val sItem:Int = st.nextToken().toInt()
                val bItem:Int = st.nextToken().toInt()
                sSum *= sItem
                bSum += bItem
            }

            val gap = abs(sSum - bSum)
            _minDiff = min(gap, _minDiff)
            return
        }
        isSelected[idx] = true
        subset(idx+1)

        isSelected[idx] = false
        subset(idx+1)
    }

}

fun main() {
    System.setIn(FileInputStream("data/251116/boj2961.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N: Int = br.readLine().toInt()

    val cart: Array<String> = Array(N) { "" }
    for(i in 0 until N){
        val item:String = br.readLine()
        cart[i] = item
    }

    val chef = DoYoungChef(N, cart)
    val ans:Int = chef.getBestRecipe()
    println(ans)

}