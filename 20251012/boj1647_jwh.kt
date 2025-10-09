package problem251012

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.max

// https://www.acmicpc.net/problem/1647
// 크루스칼 알고리즘 적용 (Solution 1)
class MayerDesigner(
    private val V:Int,
    private val E:Int,
    private val edgeList:MutableList<Edge>
) {

    // 간선에 대한 정보를 저장할 inner 클래스
    data class Edge(
        val from:Int,
        val to:Int,
        val weight:Int
    ) : Comparable<Edge> {
        override fun compareTo(other: Edge): Int {
            return (this.weight - other.weight)
        }
    }

    init {
        edgeList.sort()
        make()
    }

    // make, 주어진 간선에 대해서 자기 자신을 head(=parent)로 하는 서로소 집합 생성
    private lateinit var parent:Array<Int>

    private fun make() {
        parent = Array(V) { 0 }
        for(i in 0 until V) {
            parent[i] = i
        }
    }

    private fun find(a:Int) : Int {
        if(parent[a] == a) return a
        parent[a] = find(parent[a])
        return parent[a]
    }

    private fun union(a:Int, b:Int) : Boolean {
        val aRoot:Int = find(a)
        val bRoot:Int = find(b)
        if(aRoot == bRoot) return false
        parent[bRoot] = aRoot
        return true
    }

    fun getMinimumCost() : Int {
        var res:Int = 0
        var cnt:Int = 0
        var maxWeight = Int.MIN_VALUE
        // 크루스칼 알고리즘을 통해 최단거리를 먼저 구함
        for(i in edgeList.indices){
            val edge = edgeList[i]
            if(union(edge.from, edge.to)){
                maxWeight = max(edge.weight, maxWeight) // 이분할 하기 위하여 최대 비용'만' 구함
                res += edge.weight
                if(++cnt == V-1) break
            }
        }

        res -= maxWeight // 최대 운영 비용만 차감!
        return res
    }
}

fun main() {
    System.setIn(FileInputStream("data/251012/boj1647.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()

    val edgeList:MutableList<MayerDesigner.Edge> = mutableListOf()

    for(i in 0 until E){
        st = StringTokenizer(br.readLine())
        val from = st.nextToken().toInt() - 1
        val to = st.nextToken().toInt() - 1
        val weight = st.nextToken().toInt()

        val edge = MayerDesigner.Edge(
            from, to, weight
        )
        edgeList.add(edge)
    }

    val designer = MayerDesigner(V, E, edgeList)
    val res = designer.getMinimumCost()
    println(res)
}

/*
// 프림 알고리즘 적용 (Solution 2) => 시간 초과나서 안됨! X
// 간선이 적은 경우에는 적용 가능할 수 있으나, 커지면 시간 초과 가능성 존재.
class MayerDesigner2(
    private val V:Int,
    private val E:Int,
    private val nodeList: MutableList<Node?>
) {

    data class Node(
        val vertex:Int,
        val weight:Int,
        val next:Node?
    )

    fun getMinimumCost() : Int {
        val minEdge = IntArray(V) { Int.MAX_VALUE }
        val isVisited = BooleanArray(V) { false }

        // 1. 임의의 시작점 처리, 0 번 정점을 신장트리의 시작점으로 가정
        minEdge[0] = 0
        var res = 0 // 최소 신장 트리 비용 누적.

        var maxCost:Int = Int.MIN_VALUE

        for(c in 0 until V) {
            // step1. 신장트리의 구성에 포함되지 않은 정점 중 최소 비용 정점 선택.
            var min = Int.MAX_VALUE
            var minV = -1

            for(v in 0 until V) {
                if(!isVisited[v] && minEdge[v] < min) {
                    min = minEdge[v]
                    minV = v
                }
            }

            // step2. 신장트리에 추가
            if(minV < 0) continue
            isVisited[minV] = true
            res += min
            maxCost = max(maxCost, min) // 노드 중에서 가중치가 큰 것을 갱신

            // step3. 신장트리에 새롭게 추가되는 정점과 신장트리에 포함되지 않은 정점들의 기존 최소 비용과 비교해서 갱신
            //			신장트리에 새롭게 추가되는 정점의 모든 인접 정점 들여다보며 처리
            var temp = nodeList[minV]
            while (temp != null) {
                val nextV = temp.vertex
                val weight = temp.weight
                if (!isVisited[nextV] && minEdge[nextV] > weight) {
                    minEdge[nextV] = weight
                }
                temp = temp.next
            }

        }

        return (res - maxCost)
    }
}

fun main() {
    System.setIn(FileInputStream("data/251012/boj1647.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val V = st.nextToken().toInt()
    val E = st.nextToken().toInt()

    val nodeArr:Array<MayerDesigner2.Node?> = Array(V) { null }

    for(i in 0 until E){
        st = StringTokenizer(br.readLine())
        val from = st.nextToken().toInt() - 1
        val to = st.nextToken().toInt() - 1
        val weight = st.nextToken().toInt()

        // 무향 처리
        nodeArr[from] = MayerDesigner2.Node(to, weight, nodeArr[from])
        nodeArr[to] = MayerDesigner2.Node(from, weight, nodeArr[to])
    }

    val designer2 = MayerDesigner2(
        V, E, nodeArr.toMutableList()
    )

    val res = designer2.getMinimumCost()
    println(res)
}
 */