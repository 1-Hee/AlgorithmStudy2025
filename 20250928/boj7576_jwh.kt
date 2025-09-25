package problem250928

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

class TomatoInspector(
    private val N:Int, // = R
    private val M:Int, // = C
    private val tomatoBox:Array<IntArray>
){

    private val dx:IntArray = intArrayOf(-1, 1, 0, 0)
    private val dy:IntArray = intArrayOf(0, 0, -1, 1)

    fun startInspection() : Int {
        val queue:Queue<Pair<Int, Int>> = LinkedList()
        val isVisited:Array<BooleanArray> = Array(M) { BooleanArray(N) { false } }

        var cnt = 0
        isVisited.forEachIndexed { i, line ->
            line.forEachIndexed { j, _ ->
                if(tomatoBox[i][j] == 1){
                    isVisited[i][j] = true
                    queue.offer(Pair(i, j))
                }
            }
        }


        // 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
        // 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고,
        // 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
        while(queue.isNotEmpty()){
            for(i in 0 until queue.size){
                val pos:Pair<Int, Int> = queue.poll()
                for(d in 0 until 4){
                    val nx = pos.first + dx[d]
                    val ny = pos.second + dy[d]
                    // check range
                    if(checkRange(nx, ny)) { continue }
                    // check visit
                    if(isVisited[nx][ny]){ continue }
                    // check value
                    if(tomatoBox[nx][ny] < 0) { continue } // 음수면 전이 불가!
                    tomatoBox[nx][ny] = 1
                    isVisited[nx][ny] = true
                    queue.offer(Pair(nx,ny)) // add pos
                }
            }
            cnt++
        }

        tomatoBox.forEachIndexed { i, line ->
            line.forEachIndexed { j, data ->
                if(data == 0){
                    return -1
                }
            }
        }

        return (cnt - 1)
    }

    private fun checkRange(nx:Int, ny:Int) : Boolean {
        return !(nx in 0..<M && ny in 0..<N )
    }

}

fun main(){
    System.setIn(FileInputStream("data/250928/boj7576.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val N:Int = st.nextToken().toInt()
    val M:Int = st.nextToken().toInt()
    val mTomatoBox:Array<IntArray> = Array(M) { IntArray(N) { -1 } }

    mTomatoBox.forEachIndexed { i, line ->
        st = StringTokenizer(br.readLine())
        line.forEachIndexed { j, _ ->
            val data:Int = st.nextToken().toInt()
            mTomatoBox[i][j] = data
        }
    }

    val inspector = TomatoInspector(
        N, M, mTomatoBox
    )

    val result:Int = inspector.startInspection()
    println(result)
}
