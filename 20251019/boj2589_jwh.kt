package problem251019

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.max

class TreasureFinder(
    private val R:Int,
    private val C:Int,
    private val map:Array<IntArray>
) {

    private val dx = arrayOf(-1, 1, 0, 0)
    private val dy = arrayOf(0, 0, -1, 1)

    // 최대 보물의 경로 길이를 탐색하는 함수
    // 각 좌표마다 BFS를 n회 반복하여 최대 길이를 셈한다.
    fun find() : Int {
        var max = 0
        for(i in 0 until R){
            for(j in 0 until C){
                if(map[i][j] == 'L'.code){
                    val len:Int = calcMaxRoute(i, j)
                    max = max(len, max)
                }
            }
        }
        return max
    }

    // 보물 찾기용 BFS 함수
    private fun calcMaxRoute(r:Int, c:Int) : Int {
        val queue:Queue<IntArray> = LinkedList()
        val isVisited:Array<BooleanArray> = Array(R) { BooleanArray(C) { false } }
        queue.offer(intArrayOf(r, c, 0)) // 시작 지점 자체의 길이 값은 0으로 본다.
        isVisited[r][c] = true

        var maxVal = 0;

        while(!queue.isEmpty()) {
            val p:IntArray = queue.poll()

            for(d in 0 until 4){
                val nx = p[0] + dx[d]
                val ny = p[1] + dy[d]
                val cnt = p[2] + 1 // 현재 거리 + 1 만큼 가산
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue
                if(isVisited[nx][ny]) continue
                if(map[nx][ny] == 'W'.code) continue
                queue.offer(intArrayOf(nx, ny, cnt))
                maxVal = max(cnt, maxVal) // 최대 값 연산
                isVisited[nx][ny] = true
            }
        }
        return maxVal;
    }

}

fun main(){
    System.setIn(FileInputStream("data/251019/boj2589.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine());
    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val map:Array<IntArray> = Array(R) { IntArray(C) { 0 } }

    for(i in 0 until R){
        val line = br.readLine()
        for(j in 0 until C) {
            map[i][j] = line[j].code
        }
    }

    val finder = TreasureFinder(R, C, map)
    val maxLen:Int = finder.find()
    println(maxLen)

}