package problem250914

import java.io.*
import java.util.*
import kotlin.math.max

/**
 * 축구 감독(= 수석코치 마이크 펠란 씨)이 해야할 일을 짬처리 해줄 클래스
 * 축구 선수 명단 및 선수 별 정보에 대한 값을 생성자로 부터 전달 받음!
 */
class SoccerDirector(
    private var N:Int = 0,                                          // 팀 인원 수 (= 11로 고정!)
    private var isSelected:BooleanArray = booleanArrayOf(),         // 팀 선택 여부 명단 (for 백트래킹)
    private val memberBoard:Array<IntArray> = arrayOf(intArrayOf()) // 멤버별 능력치 대시보드 배열 (2차원)
) {

    private var maxScore:Int = Int.MIN_VALUE // 재귀에서 비교 연산할 최대 점수값!

    // 최대 점수값 반환 메서드
    fun getTeamScore():Int {
        return this.maxScore
    }

    // 팀 점수 계싼하는 순열 + 백트래킹 함수
    fun calcTeamScore(cnt:Int, sum:Int){ // 카운트 계수, 누적 합(=팀 점수)
        if(cnt == N){ // 모든 팀원에 대한 배치가 끝났다면,
            if(sum < maxScore) return // 누적합이 최대값 보다 작다면 종료
            maxScore = max(sum, maxScore) // 최대값 갱신!
            return
        }

        for(j in 0 until N){
            if(memberBoard[cnt][j] > 0){ // 0인 경우는 고르면 안된다고 했으므로 백트래킹!
                if(isSelected[j]) continue // 선택된 배열은 배제하는 순열의 조건
                isSelected[j] = true
                calcTeamScore(cnt+1, (sum + memberBoard[cnt][j])) // 카운트계수 + 누적합을 패싱한다.
                isSelected[j] = false
            }
        }
    }
}

fun main() {
    System.setIn(FileInputStream("data/250914/boj3980.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val T = st.nextToken().toInt(); // TC 입력 받아야 함.
    val sb = StringBuilder()

    /**
     * 수석코치 마이크 펠란은 11명의 선수가 각각의 포지션에서의 능력을 0부터 100까지의 정수로 수치화 했다. 0은 그 선수가 그 포지션에 적합하지 않다는 뜻이다.
     * 이때, 모든 선수의 포지션을 정하는 프로그램을 작성하시오.
     * 모든 포지션에 선수를 배치해야 하고, 각 선수는 능력치가 0인 포지션에 배치될 수 없다
     */

    for(i in 0 until T){
        // 이 루프 속에서 T개의 테스트 개수에 대한 연산을 진행함!
        val N = 11
        val isSelected = BooleanArray(N) { false }
        val memberBoard:Array<IntArray> = Array(N) { IntArray(N) { 0 } }

        for(i in 0 until N){
            st = StringTokenizer(br.readLine())
            for(j in 0 until N){
                val point:Int = st.nextToken().toInt()
                memberBoard[i][j] = point
            }
        }

        // 게산에 필요한 변수들을 입력 받아 클래스 인스턴스르 생성!
        val director = SoccerDirector(N, isSelected, memberBoard)

        director.calcTeamScore(0, 0) // 최대 팀 값을 계산한다
        val result:Int = director.getTeamScore() // 최대 팀 점수를 반환 받는다.
        sb.append(result).append("\n") // 결과 추가!
    }
    print(sb.toString()) // print result

}