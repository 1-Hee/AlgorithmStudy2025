package problem250928

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer


class TomatoInspector2(
    private val X:Int,
    private val Y:Int,
    private val Z:Int,
    private val tomatoBox : Array<Array<IntArray>>
){

    private val dx:IntArray = intArrayOf(-1, 1, 0, 0, 0, 0)
    private val dy:IntArray = intArrayOf(0, 0, -1, 1, 0, 0)
    private val dz:IntArray = intArrayOf(0, 0, 0, 0, -1, 1)

    fun startInspection() : Int{
        val queue:Queue<IntArray> = LinkedList()
        val isVisited:Array<Array<BooleanArray>> = Array(Z) { Array(Y) { BooleanArray(X) { false } } }

        var cnt = 0
        for(z in 0 until Z){
            for(y in 0 until Y){
                for(x in 0 until X){
                    if(tomatoBox[z][y][x] == 1){
                        isVisited[z][y][x] = true
                        queue.offer(intArrayOf(z, y, x))
                    }
                }
            }
        }

        while(queue.isNotEmpty()){
            for(i in 0 until queue.size){
                val pos:IntArray = queue.poll()
                for(d in 0 until 6){
//                    val nz = pos[2] + dz[d]
//                    val ny = pos[1] + dy[d]
//                    val nx = pos[0] + dx[d]
                    val nz = pos[0] + dz[d]
                    val ny = pos[1] + dy[d]
                    val nx = pos[2] + dx[d]


                    if(checkRange(nx, ny, nz)) { continue } // range check
                    if(isVisited[nz][ny][nx]) { continue } // visit check
                    if(tomatoBox[nz][ny][nx] < 0) { continue } // block

                    isVisited[nz][ny][nx] = true
                    tomatoBox[nz][ny][nx] = 1
                    queue.offer(intArrayOf(nz, ny, nx))
                }
            }
            cnt++
        }

        for(z in 0 until Z){
            for(y in 0 until Y){
                for(x in 0 until X){
                    if(tomatoBox[z][y][x] == 0){
                        return -1
                    }
                }
            }
        }

        return (cnt - 1)
    }

    private fun checkRange(nx:Int, ny:Int, nz:Int) : Boolean {
        return !(nx in 0..<X && ny in 0..<Y && nz in 0 ..<Z)
    }
}

fun main(){
    // System.setIn(FileInputStream("data/250928/boj7569.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val X = st.nextToken().toInt()
    val Y = st.nextToken().toInt()
    val Z = st.nextToken().toInt()
    val mTomatoBox:Array<Array<IntArray>> = Array(Z) { Array(Y) { IntArray(X) { -1 } } }

    for(z in 0 until Z){
        for(y in 0 until Y){
            st = StringTokenizer(br.readLine())
            for(x in 0 until X){
                val data:Int = st.nextToken().toInt()
                mTomatoBox[z][y][x] = data
            }
        }
    }

    val inspector = TomatoInspector2(X, Y, Z, mTomatoBox)
    val result:Int = inspector.startInspection()

    println(result)

}
