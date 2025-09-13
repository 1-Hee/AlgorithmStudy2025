package problem250914

import java.io.*
import java.util.*

class PasswordTranslator(
    private var C:Int = 0, // = N
    private var L:Int = 0, // = R
    private val pwdComb:CharArray = charArrayOf(), // 고른 비밀번호를 담는 배열
    private val password:CharArray = charArrayOf() // 비밀번호를 만들 수 있는 재료 문자(=Char) 배열?
){

    // 비밀번호 조합을 담을 리스트
    private val passwordList:MutableList<String> = mutableListOf()
    // 문제 조건 중, 모음의 포함 여부를 판단할 필터용 Set, 모든 케이스에서 동일하므로 내부 선언!
    private val filterSet:HashSet<Char> = hashSetOf(
        'a', 'e', 'i', 'o', 'u'
    )

    init {
        // 코틀린에서 객체 인스턴스 생성 시 호출되는 메서드,
        // 특별한 기능은 없지만 정답 배열 초기화를 위해 활용
        passwordList.clear()
    }

    // 비밀 번호를 찾아내기 위한 재귀 조합 함수
    fun findPassword(idx:Int, cnt:Int){
        if(cnt == L){ // L = R
            // 최소 한 개의 모음(a, e, i, o, u)과 최소 두 개의 자음
            // 또한 정렬된 문자열을 선호하는 조교들의 성향으로 미루어 보아
            // 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열

            /**
             *  재귀의 종료 조건에 수렴하는 각각의 문자는 모음/자음 단 두 가지 경우임!
             *  따라서, 수렴된 비밀번호 후보군들에 대해 모음과 자음을 갯수를 샘해서 판정 가능
             */

            var vowelsCnt = 0 // 모음 철자 수
            var consonantsCnt = 0 // 자음 철자 수
            pwdComb.forEach { element ->

                // 미리 선언된 모음 '세트(Set)'를 통해서 자음인지 모음인지 판단가능!
                // 평균적으로 O(1)의 시간 복잡도를 가짐.
                // 그러나 최악의 경우 해시 충돌이 발생하면 O(n) 가능하나 대부분 O(1)

                val flag:Boolean = filterSet.contains(element)
                // 판정 결과로 자/모음 개수 계산!
                if(flag){
                    vowelsCnt++
                }else {
                    consonantsCnt++
                }
            }

            // 아래의 조건들을 만족하지 못하면, 유효한 문자열이 아님!
            if(vowelsCnt < 1) return
            if(consonantsCnt < 2) return

            val str = String(pwdComb)
            passwordList.add(str) // 비밀번호 후보군에 추가
            return
        }

        for(i in idx until C){
            pwdComb[cnt] = password[i]
            findPassword(i+1, cnt+1)
        }
    }

    // 정렬된 비밀번호 리스트를 반환하기 위한 메서드 (사전순 정리용)
    fun getPasswordList(isSorted:Boolean = true) : List<String> {
        if(isSorted){
            return this.passwordList.sorted()
        } else {
            return this.passwordList
        }
    }

    // 디버깅을 위한  toString() 메서드
    override fun toString(): String {
        return "[${this.javaClass.simpleName}]" +
                "{ L = $L , C = $C, " +
                "pwdComb = ${pwdComb.contentToString()}, " +
                "password = ${password.contentToString()} } "
    }
}

fun main() {
    System.setIn(FileInputStream("data/250914/boj1759.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val L:Int = st.nextToken().toInt() // = R
    val C:Int = st.nextToken().toInt() // = N
    // Char 배열이라서 일단 공백(0x20 = " ")로 채움
    val pwdComb = CharArray(L) { 0x20.toChar() }
    var password = CharArray(C) { 0x20.toChar() }

    st = StringTokenizer(br.readLine())
    for(i in 0 until C){ // 비밀번호 재료 채우기!
        password[i] = st.nextToken()[0]
    }
    password = password.sorted().toCharArray()

    // 클래스 인스턴스 생성 및 초기 변수 할당
    val translator = PasswordTranslator(C,L, pwdComb, password)

    // 비밀번호 찾기 시작!
    translator.findPassword(0, 0)

    val strList: List<String> = translator.getPasswordList(true) /// 결과 반환
    val sb = StringBuilder() // 출력을 한번에!
    strList.forEach { line ->
        sb.append(line).append("\n")
    }
    print(sb.toString()) // print result
}

