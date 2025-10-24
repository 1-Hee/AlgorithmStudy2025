package problem251026

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.HashSet
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer
import kotlin.math.min

// 회장님 선별해주는 클래스
class LinkFinder(
    private val N:Int,
    private val linkPoints : Array<LinkPoint>
) {
    // 회원 간의 인적 네트워크를 관리할 노드
    data class LinkPoint(
        val no:Int, // 자기 자신의 번호
        val neighbor:HashSet<Int> = hashSetOf() // 자기 자신(=no)이 알고 있는, 즉 인접한 노드의 번호 Set
    )

    // 임의의 회원 번호 X에 대하여 그래프 탐색을 진행하는 함수
    private fun checkMaxCost(point:Int) : Int {
        val isVisited = BooleanArray(N) { false } // 방문 관리용 불리언 배열
        val cost = IntArray(N) { Int.MAX_VALUE } // 최소 비용을 저장할 배열
        val queue:Queue<IntArray> = LinkedList() // BFS 탐색을 위한 큐
        val start:LinkPoint = linkPoints[point]
        queue.offer(intArrayOf(start.no, 0))
        isVisited[point] = true
        cost[point] = 0 // 자기 자신으로의 이동 비용은 0 (= 최소임을 보장)

        while (queue.isNotEmpty()){
            val pos:IntArray = queue.poll()
            val no = pos[0]
            val distance = pos[1] + 1
            val linkPoint = linkPoints[no]
            // 회원 링크 풀에 대하여 bfs 탐색을 진행한다.
            val pooList:List<Int> = linkPoint.neighbor.toList()
            for(i in pooList.indices){
                val nPoint:Int = pooList[i]
                if(isVisited[nPoint]) continue
                isVisited[nPoint] = true
                queue.offer(intArrayOf(nPoint, distance))
                if(cost[nPoint] > distance) {
                    cost[nPoint] = distance
                }
            }
        }
        return cost.max() // cost 배열에서의 최대 값을 저장함.
    }

    fun findKing() : String {
        var idx = 0
        val rankList:Array<IntArray> = Array(N) { intArrayOf(idx++, Int.MAX_VALUE) } // 각 후보별로 자신의 번호와 비용을 저장하는 2차원 배열
        var minValue:Int = Int.MAX_VALUE // 최대 비용 중, 최소 비용의 값 (= 회장 선별 기준)
        // step 1. N번의 노드를 BFS 탐색하며 최대 비용을 계산한다.
        for(i in 0 until N){
            val maxCost = checkMaxCost(i)
            rankList[i][1] = maxCost // 자기 자신 비용 갱신
            minValue = min(minValue, maxCost) // 최소 값 연산
        }

        rankList.sortBy { it -> it[1] } // 비용 기준으로 오름차순

        // step2. 저장된 score를 N회 탐색하면서 후보군만 추려낸다.
        val sb = StringBuilder()
        sb.append(minValue).append(" ")
        var cnt = 0
        val peopleList:MutableList<Int> = mutableListOf()

        for(i in rankList.indices){
            val score = rankList[i]
            if(score[1] == minValue) { // 최소 값이라면
                val no = (score[0] + 1) // 자신의 진짜 번호를 얻어냄. (+1 해주어야 함)
                cnt++
                peopleList.add(no)
            }
        }
        peopleList.sort()
        sb.append(cnt).append("\n") // 회장님 수 추가

        // 회장님 정보 입력
        for(i in peopleList.indices){
            val no = peopleList[i]
            sb.append(no).append(" ")
        }

        return sb.toString()
    }

}



fun main(){
    System.setIn(FileInputStream("data/251026/boj2660.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N:Int = br.readLine().toInt()
    var idx = 0
    val linkArray:Array<LinkFinder.LinkPoint> = Array(N) { LinkFinder.LinkPoint(idx++) }

    do {
        val st = StringTokenizer(br.readLine())
        val from:Int = (st.nextToken().toInt() -1)
        val to:Int = (st.nextToken().toInt() -1)
        if(from < 0 || to < 0) break

        linkArray[from].neighbor.add(to)
        linkArray[to].neighbor.add(from)

    } while (true)

    // 주어진 문제의 답변을 찾아낼 전용 클래스 인스턴스 생성 및 파라미터 전달.
    val finder = LinkFinder(N, linkArray)
    val result:String = finder.findKing()
    println(result) // 결과 출력
}