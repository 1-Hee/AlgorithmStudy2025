package problem251019

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

class CheeseMaker(
    private val R:Int,
    private val C:Int,
    private val map:Array<IntArray>
) {
    private val dx = arrayOf(-1, 1, 0, 0)
    private val dy = arrayOf(0, 0, -1, 1)
    private var cnt = 1;
    private var size = 0;

    fun getCheeseMeltDown() : IntArray {
        while (true){
            // 배열이 실시간으로 변화하므로, 단계마다 치즈의 개수를 셈하는 로직
            var mCheese = 0
            for(i in 0 until R) {
                for(j in 0 until C){
                    if(map[i][j] == 1) {
                        mCheese++
                    }
                }
            }
            this.size = mCheese // 치즈의 개수를 항상 최신순으로 갱신
            cheeseMasking(0, 0) // 항상 0,0 부터 n,n 까지의 가장자리는 비어있으므로 고정된 값으로 BFS 시작
            val mSize = meltDown()  // 치즈 녹이기 작업
            if(mSize == 0) return intArrayOf(this.cnt, this.size) // 녹인 치즈가 없다면 그때까지 계산한 값을 반환
            cnt++
        }
    }

    // 치즈를 실제 녹여서 배열을 초기화 시켜주는 함수
    private fun meltDown() : Int{
        var area = 0
        for(i in 0 until R){
            for(j in 0 until C){
                if(map[i][j] == -1) {
                    map[i][j] = 0
                }else if(map[i][j] == 2){
                    map[i][j] = 0
                } else if(map[i][j] == 1){
                    area++
                }
            }
        }
        return area
    }

    // 녹일 치즈를 찾기 위한 BFS 탐색 함수
    private fun cheeseMasking(r:Int, c:Int) : Boolean {
        val queue:Queue<IntArray> = LinkedList() // 가장자리 공기를 탐색할 큐
        var isVisited:Array<BooleanArray> = Array(R) { BooleanArray(C) { false } } // BFS를 위한 방문 배열
        isVisited[r][c] = true
        queue.offer(intArrayOf(r, c))
        map[r][c] = -1

        val cheeseQue:Queue<IntArray> = LinkedList() // 치즈와 인접한 공기의 좌표를 담을 배열
        cheeseQue.offer(intArrayOf(r, c))
        var isFound = false

        // step 1. 배열을 순회하면서 외부 '공기'를 탐색한다.
        while(queue.isNotEmpty()){
            val pos:IntArray = queue.poll()
            for(d in 0 until 4){
                val nx = pos[0] + dx[d]
                val ny = pos[1] + dy[d]
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue
                if(isVisited[nx][ny]) continue
                if(map[nx][ny] == 1) continue
                isVisited[nx][ny] = true;
                queue.offer(intArrayOf(nx, ny))
                map[nx][ny] = -1
                cheeseQue.offer(intArrayOf(nx, ny))
            }
        }

        // step 2. 탐색된 '공기'를 기준으로 녹일 치즈를 '마스킹' 한다.
        isVisited = Array(R) { BooleanArray(C) { false } }
        while(cheeseQue.isNotEmpty()){
            val pos = cheeseQue.poll()
            val x = pos[0]
            val y = pos[1]
            isVisited[x][y] = true

            for(d in 0 until 4){
                val nx = x + dx[d]
                val ny = y + dy[d]
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue
                if(isVisited[nx][ny]) continue
                if(map[nx][ny] == 0) continue
                val flag = (map[x][y] == -1) && (map[nx][ny] == 1)
                if(flag){
                    queue.offer(intArrayOf(nx, ny))
                    map[nx][ny] = 2
                    isFound = true
                }

            }
        }
        // prtMap(map, r, c)
        return isFound // 결과 리턴
    }

    // 디버깅용 프린트 함수
    private fun prtMap(map:Array<IntArray>, r:Int, c:Int) {
        println("[POS] => r : $r , c : $c")
        for(i in 0 until R){
            for(j in 0 until C){
                val value:Int = map[i][j]
                val str = when(value) {
                    -1 -> "X"
                    1 -> "●"
                    2 -> "#"
                    else -> "○"
                }

                print("$str ")
            }
            println()
        }
        println("---------------------")
    }

}

fun main(){
    System.setIn(FileInputStream("data/251019/boj2636.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val R = st.nextToken().toInt()
    val C = st.nextToken().toInt()
    val map:Array<IntArray> = Array(R) { IntArray(C) { 0 } }

    for(i in 0 until R){
        st = StringTokenizer(br.readLine())
        for(j in 0 until C){
            map[i][j] = st.nextToken().toInt()
        }
    }

    val cheeseMaker = CheeseMaker(R, C, map)
    val result = cheeseMaker.getCheeseMeltDown()
    println("${result[0]}\n${result[1]}") // 정답 출력

}