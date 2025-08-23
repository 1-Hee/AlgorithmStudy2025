package problem250824

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

data class RecordInfo(
    val num:Int = -1,
    var g:Int = -1,
    var s:Int = -1,
    var b:Int = -1,
)

fun isBigger(p1:RecordInfo, p2:RecordInfo) : Boolean {
    return if(p1.g != p2.g){
        p1.g > p2.g
    }else if(p1.s != p2.s) {
        p1.s > p2.s
    }else {
        p1.b > p2.b
    }
}

fun main(){
    System.setIn(FileInputStream("data/boj8979.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()
    val rankArr = Array(n) { RecordInfo() }

    var cInfo = RecordInfo()
    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        val num = st.nextToken().toInt()
        val g:Int = st.nextToken().toInt()
        val s:Int = st.nextToken().toInt()
        val b:Int = st.nextToken().toInt()

        val info =  RecordInfo(num, g, s, b)
        rankArr[i] = info
        if(num == k){
            cInfo = info
        }
    }

    var rank = 1;
    rankArr.forEach { info ->
        if(info.num != k){
            val flag = isBigger(info, cInfo)
            if(flag) rank++

        }
    }

    println(rank)
}