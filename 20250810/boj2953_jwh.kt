package problem250810

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/2953

// 총 다섯 개 줄에  -> n = 5
// 각 참가자가 얻은 네 개의 평가 점수가 공백으로 구분되어 주어진다
// 항상 우승자가 유일한 경우만 입력으로 주어진다.
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = 5
    var st = StringTokenizer("")

    var maxPoint:Int = Int.MIN_VALUE
    var maxIdx:Int = -1
    var point = 0;

    for(i in 0 until n){
        st = StringTokenizer(br.readLine()) // read & make token!
        point = 0
        while (st.hasMoreTokens()){
            point += st.nextToken().toInt() // add all inputs
        }
        if(point > maxPoint) { // 최대값 검사
            maxPoint = point
            maxIdx = i
        }
    }

    println("${maxIdx+1} $maxPoint") // print result
}