package problem250928

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class RectangleInspector(
    private val M:Int, // R
    private val N:Int, // C
    private val paper:Array<IntArray>
){

    fun startDiscovery() : List<Int> {
        val areaList:MutableList<Int> = mutableListOf()

        paper.forEachIndexed { r, line ->
            line.forEachIndexed { c, i ->
                if(i == 0){
                    val area:Int = search(r, c)
                    areaList.add(area)
                }
            }
        }

        return areaList
    }

    private val dx:IntArray = intArrayOf(-1, 1, 0, 0)
    private val dy:IntArray = intArrayOf(0, 0, -1, 1)

    private fun search(x:Int , y:Int) : Int{
        val queue:Queue<Pair<Int, Int>> = LinkedList()
        val isVisited:Array<BooleanArray> = Array(M) { BooleanArray(N) { false } }
        queue.offer(Pair(x, y))
        isVisited[x][y] = true

        var size = 0

        while (queue.isNotEmpty()){
            val pos:Pair<Int, Int> = queue.poll()

            for(d in 0 until 4){
                val nx:Int = pos.first + dx[d]
                val ny:Int = pos.second + dy[d]
                if(checkRange(nx, ny)) continue // range
                if(paper[nx][ny] > 0) continue // value
                paper[nx][ny] = 2
                isVisited[nx][ny] = true
                queue.offer(Pair(nx, ny))
            }
        }

        isVisited.forEach { line ->
            line.forEach { flag ->
                if(flag){
                    size++
                }
            }
        }

        return size
    }

    private fun checkRange(nx:Int, ny:Int) : Boolean {
        return (nx !in 0..<M || ny !in 0..<N)
    }


}

fun main(){
    System.setIn(FileInputStream("data/250928/boj2583.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val M = st.nextToken().toInt()
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()

    val paper:Array<IntArray> = Array(M) { IntArray(N) { 0 } }

    for (k in 0 until K){
        st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()

        for(x in x1 until x2){
            for(y in y1 until y2) {
                paper[y][x] = 1
            }
        }
    }

    val inspector = RectangleInspector(M, N, paper)
    val output = inspector.startDiscovery()
    val result = output.sorted()
    val sb = StringBuilder()
    sb.append(result.size).append("\n")
    result.forEach { item ->
        sb.append(item).append(" ")
    }
    println(sb.toString())
}