package problem250824

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs


var cardSum:Int = Int.MAX_VALUE
var M:Int = -1
lateinit var numberArr:IntArray
lateinit var isSelect:BooleanArray

fun blackJack(idx:Int, cnt:Int){
    if(cnt == 3){
        var subSum = 0
        for(i in isSelect.indices){
            if(isSelect[i]){
                subSum += numberArr[i]
            }
        }
        if(subSum > M) return // 블랙잭 케이스 제외 필요
        val gap1 = abs(M - subSum)
        val gap2 = abs(M - cardSum)
        if(gap1 < gap2){
            cardSum = subSum
        }
        return
    }

    for(i in idx until numberArr.size) {
        isSelect[i] = true
        blackJack(i+1, cnt+1)
        isSelect[i] = false
    }

}

fun main(){
    System.setIn(FileInputStream("data/boj2798.txt"))

    // init values
    cardSum = Int.MAX_VALUE
    M = 0

    // input streams.
    val br = BufferedReader(InputStreamReader(System.`in`));
    var st = StringTokenizer(br.readLine())

    val n:Int = st.nextToken().toInt()
    M = st.nextToken().toInt()

    isSelect = BooleanArray(n) { false }
    numberArr = IntArray(n) { 0 }

    st = StringTokenizer(br.readLine())

    for(i in 0 until n){
        val num:Int = st.nextToken().toInt()
        numberArr[i] = num
    }

    blackJack(0, 0)

    println(cardSum)
}