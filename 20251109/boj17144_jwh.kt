package problem251109

import java.io.FileInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

class AeroMachine(
    private val R:Int,
    private val C:Int,
    private val T:Int,
    private val map: Array<IntArray>,
    private val airPos: Array<IntArray>
){

    // 미세먼지 기기 동작 함수
    fun execute() : Int {
        for(t in 0 until T){
            spread()
            topRefresh()
            bottomRefresh()
        }
        return lookupDust();
    }

    // 3. 미세먼지 카운트 로직: 남아있는 미세먼지 총량 계산
    private fun lookupDust() : Int  {
        var cnt = 0
        for(i in 0 until R){
            for(j in 0 until C){
                if(map[i][j] > 0) { cnt += map[i][j] }
            }
        }
        return cnt
    }


    // 상부 순환 방향 (반시계 방향)
    // 변화의 순서 : 우 -> 상 -> 좌 - > 하
    private val dxTop: IntArray = intArrayOf(0, -1, 0, 1)
    private val dyTop: IntArray = intArrayOf(1, 0, -1, 0)

    // 하부 순환 방향 (시계 방향)
    // 변화의 순서 : 우 -> 하 -> 좌 -> 상
    private val dxBottom: IntArray = intArrayOf(0, 1, 0, -1)
    private val dyBottom: IntArray = intArrayOf(1, 0, -1, 0)

    private fun bottomRefresh() {
        val px: Int = airPos[1][0]
        val py: Int = airPos[1][1]
        val dxArr: IntArray = dxBottom
        val dyArr: IntArray = dyBottom
        circulate(px, py, dxArr, dyArr)
        map[px][py] = -1
    }

    private fun topRefresh() {
        val px: Int = airPos[0][0]
        val py: Int = airPos[0][1]
        val dxArr: IntArray = dxTop
        val dyArr: IntArray = dyTop
        circulate(px, py, dxArr, dyArr)
        map[px][py] = -1
    }

    // 1. 미세먼지 순환 로직: 공기청정기 바람에 의해 미세먼지가 이동함.
    private fun circulate(
        px: Int, // 공기청정기 x 위치
        py: Int, // 공기청정기 y 위치
        dxArr: IntArray,
        dyArr: IntArray
    ) {
        var d = 0 // 방향 인덱스

        // 공기청정기 바로 옆 첫 칸에서 시작
        var x = px + dxArr[d]
        var y = py + dyArr[d]
        var prev = 0 // 공기청정기에서 들어오는 값 (0, 깨끗한 공기)

        // 공기청정기 바로 옆 첫 칸부터 시작해서 한 바퀴 돌기
        while (true) {
            // 다음에 이동할 위치 (nextX, nextY) 계산
            val nextX = x + dxArr[d]
            val nextY = y + dyArr[d]

            // 1. 경계에 부딪히면 방향 전환
            if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
                d++
                if (d >= 4) break // 안전 장치 (경로가 닫혀있으므로 사실상 불필요)
                continue // 새 방향으로 다시 시도
            }

            // 2. 공기청정기로 돌아오면 종료
            if (nextX == px && nextY == py) {
                // 현재 칸 (x, y)에 이전 칸의 값 (prev)을 최종적으로 할당
                map[x][y] = prev
                break
            }

            // 3. 순환 (값 이동)
            // 현재 칸 (x, y)의 먼지량을 저장
            val currentDust = map[x][y]

            // 현재 칸 (x, y)는 이전 칸의 먼지량 (prev)을 받음
            map[x][y] = prev

            // 현재 칸의 먼지량은 다음 칸으로 이동할 prev 값이 됨
            prev = currentDust

            // 4. 다음 칸으로 이동
            x = nextX
            y = nextY
        }
    }

    private val dx: IntArray = intArrayOf(-1, 1, 0, 0)
    private val dy: IntArray = intArrayOf(0, 0, -1, 1)

    // 2. 미세먼지 확산 로직
    private fun spread() {
        // 확산량을 저장할 임시 맵 (확산의 변화량을 저장)
        val vectorMap: Array<IntArray> = Array(R) { IntArray(C) { 0 } }

        for(i in 0 until R){
            for(j in 0 until C){
                // 공기 청정기(-1)이거나 먼지가 0인 칸은 확산하지 않음
                if(map[i][j] <= 0) continue

                val unit = map[i][j] / 5

                // 확산될 단위가 0이면 스킵 (먼지가 5 미만인 경우)
                if (unit == 0) continue

                var cnt = 0; // 실제로 확산이 발생한 방향의 수
                for(d in 0 until 4){
                    val nx = i + dx[d]
                    val ny = j + dy[d]

                    // 1. 경계 체크
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue

                    // 2. 공기 청정기 체크
                    if(map[nx][ny] < 0) continue

                    // 확산되는 양을 vectorMap에 더하고, 확산된 방향 수를 카운트
                    vectorMap[nx][ny] += unit
                    cnt++
                }

                // 원본 위치에서 확산된 총량만큼 뺌
                vectorMap[i][j] -= (unit * cnt)
            }
        }

        // vectorMap의 변경 사항을 원본 map에 반영 (동시 확산 완료)
        for(i in 0 until R){
            for(j in 0 until C){
                map[i][j] += vectorMap[i][j]
            }
        }
    }


    // 디버깅용 지도 출력
    fun printMap(){
        val str = buildString {
            for(i in 0 until R){
                for(j in 0 until C){
                    append(map[i][j].toString().padStart(3, ' ')) // 정렬을 위해 3자리 폭 설정
                    append(" ")
                }
                append("\n")
            }
            append("----------------------\n")
        }
        println(str)
    }

}

fun main() {
    System.setIn(FileInputStream("data/20251109/boj17144.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val r: Int = st.nextToken().toInt()
    val c: Int = st.nextToken().toInt()
    val t: Int = st.nextToken().toInt()

    val map: Array<IntArray> = Array(r) { IntArray(c) { 0 } }
    // airPos[0] = 상단 공기청정기,
    // airPos[1] = 하단 공기청정기 위치 저장
    val airPos: Array<IntArray> = Array(2) { IntArray(2) { 0 } }

    var idx = 0
    for(i in 0 until r){
        st = StringTokenizer(br.readLine())
        for(j in 0 until c) {
            map[i][j] = st.nextToken().toInt()
            if(map[i][j] < 0) {
                // 공기 청정기 위치 저장
                airPos[idx][0] = i
                airPos[idx++][1] = j
            }
        }
    }

    val machine = AeroMachine(r, c, t, map, airPos)
    val res = machine.execute()
    println(res)

    // 최종 맵 상태 출력 (확인용)
    // machine.printMap()


}