package problem250831

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer


fun main(){
    System.setIn(FileInputStream("data/250831/boj15702.txt"))

    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val pointArr = IntArray(m) { 0 }
    st = StringTokenizer(br.readLine())
    var idx = 0;
    while (st.hasMoreTokens()){
        val point = st.nextToken().toInt()
        pointArr[idx++] = point
    }

    val recordList:MutableList<Pair<Int, Int>> = mutableListOf()

    for(i in 0 until n){
        st = StringTokenizer(br.readLine())
        val num:Int = st.nextToken().toInt()
        idx = 0;
        var totalPoint = 0;
        while (st.hasMoreTokens()){
            val charX:Char = st.nextToken()[0]
            if(charX == 'O'){
                totalPoint += pointArr[idx]
            }
            idx++
        }

        recordList.add(Pair(num, totalPoint))
    }

    val result = recordList.sortedWith { o1, o2 ->
        if(o1.second != o2.second){
            o2.second - o1.second
        } else {
            o1.first - o2.first
        }
    }

    val sb = StringBuilder()
    sb.append(result[0].first).append(" ").append(result[0].second)
    println(sb.toString())


}
