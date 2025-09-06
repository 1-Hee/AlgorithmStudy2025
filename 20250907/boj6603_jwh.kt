package problem250907

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

private var N = 6
private val R = 6

fun main(){
    System.setIn(FileInputStream("data/250907/boj6603.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    var st: StringTokenizer
    var isLineFeed = false
    while (true){
        if(isLineFeed){
            println()
        }
        st = StringTokenizer(br.readLine())
        val n = st.nextToken().toInt()

        if(n == 0) break
        isLineFeed = true
        N = n
        val inputArray = IntArray(n) { 0 }
        val numberArray = IntArray(R) { 0 }

        for(i in 0 until N){
            val num:Int = st.nextToken().toInt()
            inputArray[i] = num
        }

        germLotto(0, 0, inputArray, numberArray)
    }

}

// 독일 로또 추첨기 ( = 조합 이용! )
private fun germLotto(idx:Int, cnt:Int, input:IntArray, number:IntArray) {
    if(cnt == R){
        // 탐색한 조합 경우의 수를 다 출력해야 하므로..!
        val sb = StringBuffer()
        number.forEach { item ->
            sb.append(item).append(" ")
        }
        println(sb.toString())
        return
    }

    for(i in idx until N){
        number[cnt] = input[i]
        germLotto(i+1, cnt+1, input, number)
    }

}
