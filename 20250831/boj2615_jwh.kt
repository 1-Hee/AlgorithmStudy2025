package problem250831

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/2615

val dirX = intArrayOf(0, 1, -1, 1)
val dirY = intArrayOf(1, 0, 1, 1)

fun main(){
    System.setIn(FileInputStream("data/250831/boj2615.txt"))

    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = 19
    val map = Array(n) { IntArray(n) { 0 } }

    var st:StringTokenizer
    var idx:Int
    for(i in 0 until n) {
        idx = 0
        st = StringTokenizer(br.readLine())
        while(st.hasMoreTokens()){
            map[i][idx++] = st.nextToken().toInt()
        }
    }

    val sb = StringBuilder()
    var isEnd = false
    for(r in map.indices){
        val line = map[r]
        for(c in line.indices){
            if(map[r][c] > 0){
                val flag = omokCheck(r, c, map)
                if(flag){
                    sb.append(map[r][c]).append("\n")
                        .append(r+1).append(" ").append(c+1)
                    isEnd = true
                    break
                }
            }
        }
        if(isEnd)break
    }

    if(sb.isBlank()){
        sb.append("0")
    }

    println(sb.toString())

    // 첫줄에 검은색이 이겼을 경우에는 1을, 흰색이 이겼을 경우에는 2
    // 아직 승부가 결정되지 않았을 경우에는 0
    // 검은색 또는 흰색이 이겼을 경우에는 둘째 줄에 연속된 다섯 개의 바둑알 중에서
    // 가장 왼쪽에 있는 바둑알
    // (연속된 다섯 개의 바둑알이 세로로 놓인 경우, 그 중 가장 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.
}

fun omokCheck(r:Int, c:Int, map:Array<IntArray>):Boolean {
    val color = map[r][c]

    var dx = 0;
    var dy = 0
    for(k in 0 until 4){

        var dr:Int = r
        var dc:Int = c

        dx = dirX[k]
        dy = dirY[k]

        val prevX = dr - dx
        val prevY = dc - dy
        if(prevX >= 0 && prevY >= 0 && prevX < map.size && prevY < map.size){
            if(map[prevX][prevY] == color) continue
        }

        var cnt = 1
        do {
            dr += dx
            dc += dy
            if(dr < 0 || dc < 0 || dr >= map.size || dc >= map.size) break;
            if(map[dr][dc] != color) break
            cnt++
        }while (true)

        if(cnt == 5) return true
    }

    return false
}


