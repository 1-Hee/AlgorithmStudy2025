package problem251012

// https://www.acmicpc.net/problem/4485

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

data class Node(
    val x:Int,
    val y:Int,
    val cost:Int
)

fun main() {
    System.setIn(FileInputStream("data/251012/boj4485.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()

    val dx = intArrayOf(-1, 1, 0, 0)
    val dy = intArrayOf(0, 0, -1, 1)
    var k = 0
    var idx = 1

    do {
        k = br.readLine().toInt()
        if(k == 0) break
        val graph:Array<IntArray> = Array(k) { IntArray(k) { 0 } }

        var st:StringTokenizer
        for(i in 0 until k){
            st = StringTokenizer(br.readLine())
            for(j in 0 until k){
                graph[i][j] = st.nextToken().toInt()
            }
        }

        val distance:Array<IntArray> = Array(k) { IntArray(k) { Int.MAX_VALUE } }
        // 시작점 (0,0) → 비용 = graph[0][0]
        distance[0][0] = graph[0][0]

        val comparable = object : Comparator<Node> {
            override fun compare(o1: Node?, o2: Node?): Int {
                // if(o1 == null || o2 == null) return 0
                // return o1.cost - o2.cost
                return o1!!.cost.compareTo(o2!!.cost)
            }
        }
        val pQue:PriorityQueue<Node> = PriorityQueue(comparable)
        pQue.offer(Node(0,0, distance[0][0]))

        while(pQue.isNotEmpty()){
            val cur:Node = pQue.poll()
            if (cur.cost > distance[cur.x][cur.y]) continue

            for(d in 0 until 4){
                val nx = cur.x + dx[d]
                val ny = cur.y + dy[d]
                if(nx < 0 || ny < 0 || nx >= k || ny >= k) continue
                // val cost = graph[cur.x][cur.y] + graph[nx][ny]
                val cost = distance[cur.x][cur.y] + graph[nx][ny]
                if(distance[nx][ny] > cost) {
                    // distance 를 “현재 cost + 그래피의 비용”으로 업데이트
                    distance[nx][ny] = cost
                    pQue.offer(Node(nx, ny, cost))
                }
            }
        }

        val result = distance[k-1][k-1]
        sb.append("Problem ${idx++}: $result\n")
    } while (true)

    print(sb.toString())
}