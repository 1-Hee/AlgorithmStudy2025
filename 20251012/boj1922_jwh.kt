package problem251012

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/1922
// 크루스칼 알고리즘 적용
class NetworkConnector(
    private val V:Int, // 노드의 수
    private val E:Int, // 간선의 수
    private val edgeList:MutableList<Edge>
) {

    init {
        edgeList.sort() // 정렬
        make()
    }

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

    // make, 주어진 간선에 대해서 자기 자신을 head(=parent)로 하는 서로소 집합 생성
    private lateinit var parent:Array<Int>

    private fun make() {
        parent = Array(V) { 0 }
        for(i in 0 until V){
            parent[i] = i
        }
    }

    private fun find(element:Int) : Int {
        if(parent[element] == element) return element
        parent[element] = find(parent[element])
        return parent[element]
    }

    private fun union(a:Int, b:Int) : Boolean{
        val aRoot = find(a)
        val bRoot = find(b)
        return if (aRoot == bRoot) false
        else {
            parent[bRoot] = aRoot
            true
        }
    }

    fun getMSTValue() : Int {
        var res = 0
        var cnt = 0
        for(i in edgeList.indices){
            val edge = edgeList[i]
            if(union(edge.from, edge.to)){
                res += edge.weight
                if(++cnt == V-1) break
            }
        }
        return res
    }
}

fun main() {
    System.setIn(FileInputStream("data/251012/boj1922.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val V = br.readLine().toInt()
    val E = br.readLine().toInt()

    val edgeList:MutableList<NetworkConnector.Edge> = mutableListOf()

    var st : StringTokenizer
    for(i in 0 until E) {
        st = StringTokenizer(br.readLine())
        val from = (st.nextToken().toInt() - 1)
        val to = (st.nextToken().toInt() - 1)
        val weight = st.nextToken().toInt()
        val edge = NetworkConnector.Edge(
            from, to, weight
        )
        edgeList.add(edge)
    }
    val connector = NetworkConnector(
        V, E, edgeList
    )

    val res = connector.getMSTValue() // 최단거리 구하기
    println(res)
}