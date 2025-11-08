package problem251109

import java.io.*;
import java.util.*
import kotlin.math.abs
import kotlin.math.min

// 게리멘더링을 위한 시장 클래스
class Mayer(
    private val N:Int,
    private val citizen:IntArray, // 선거구 인원 배열
    private val network: Array<HashSet<Int>> // 선거인에 대한 네트워크 정보 ( for BFS )
) {

    // step 1. 부분 집합 로직을 통한 경우의 수 산출을 위한 변수 셋업
    private var numbers:IntArray
    private var isSelected:BooleanArray
    private var minGap:Int = 1000
    init {
        var idx = 1;
        numbers = IntArray(N) { idx++ }
        isSelected = BooleanArray(N) { false }
        minGap = 1000
    }

    // 최종 정답 호출 진입점,
    fun getMinGap() : Int {
        subset(0)
        return minGap
    }

    // 부분 집합 로직
    private fun subset(idx:Int){
        if(idx == N) {
            val group1:MutableList<Int> = mutableListOf()
            val group2:MutableList<Int> = mutableListOf()
            for(i in isSelected.indices){
                if(isSelected[i]){
                    group1.add(numbers[i])
                } else {
                    group2.add(numbers[i])
                }
            }
            if(group1.isEmpty() || group2.isEmpty()) return
            val gCnt1:Int = checkNetwork(group1[0], group1)
            val gCnt2:Int = checkNetwork(group2[0], group2)
            if(gCnt1 < 0 || gCnt2 < 0) return
            val res:Int = abs(gCnt1 - gCnt2)
            minGap = min(minGap, res)
            return
        }

        // 부분 집합 핵심 로직
        isSelected[idx] = true
        subset(idx+1)
        isSelected[idx] = false
        subset(idx+1)
    }

    // [1, 4], [2, 3, 5, 6]
    // 주어진 좌표를 기준으로 BFS를 탐색하며, 멤버들의 번호 리스트를 기반으로 각 멤버의 Set을 참고해 BFS 탐색을 수행함.
    private fun checkNetwork(pos:Int, members:List<Int>):Int {
        val queue:Queue<Int> = LinkedList()
        queue.offer(pos)
        val visited = BooleanArray(N+1) { false } // 1차원 배열로 방문 체크하고, 이때 인덱스 1번부터 N만큼 사용.
        visited[pos] = true
        var mCitizen = citizen[pos]

        while(queue.isNotEmpty()){
            val curPos:Int = queue.poll()
            val hSet:HashSet<Int> = network[curPos]

            hSet.forEach { item ->
                if(!visited[item] && members.contains(item)){
                    visited[item] = true
                    queue.offer(item)
                    mCitizen += citizen[item]
                }
            }
        }

        for(i in members.indices){
            val member:Int = members[i]
            if(!visited[member]) return -1
        }

        return mCitizen // citizen 배열에는 여론 파워가 담김.
    }
}

fun main(){
    System.setIn(FileInputStream("data/251108/boj17471.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val N: Int = br.readLine().toInt()
    var st = StringTokenizer(br.readLine())

    val citizen = IntArray(N+1) { 0 }
    val network = Array<HashSet<Int>>(N+1){ hashSetOf() }

    for(i in 0 until N) {
        val p:Int = st.nextToken().toInt()
        citizen[i+1] = p
    }

    for(i in 0 until N){
        st = StringTokenizer(br.readLine())
        val m: Int = st.nextToken().toInt()
        for(j in 0 until m){
            val next: Int = st.nextToken().toInt()
            network[i+1].add(next)
        }
    }

    val mayer = Mayer(N, citizen, network)
    val res:Int = mayer.getMinGap()

    if(res == 1000){
        println(-1)
    } else {
        println(res)
    }

}