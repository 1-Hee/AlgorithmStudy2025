package problem250810

import java.io.BufferedReader
import java.io.InputStreamReader

// https://www.acmicpc.net/problem/2847

/*
마지막 레벨부터 거꾸로 보면서,
앞 레벨 점수가 뒤 레벨보다 작아야 한다는 조건을 만족하도록
조정하는 관점으로 생각하기.
 */
fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n:Int = br.readLine().toInt()
    val buffer = IntArray(n) { br.readLine().toInt() } // 주어진 입력 set up
    val cBuffer:IntArray = buffer.reversedArray() // 코틀린에서는 배열의 거꾸로 정렬을 이렇게 사용 가능.

    // println(cBuffer.contentToString()) // 디버깅용....

    var cnt = 0; // 조정이 필요한 횟수
    for(i in 1 until n){
        if(cBuffer[i] >= cBuffer[i-1]){
            // 이 조건문식을 통과했다는 것은 점수를 줄여야 한다는 것.
            // 그런데, 점수를 조정할 때 점수가 1 감소할 때마다, 카운트 계수가 1만큼 증가해야함
            // 즉, 현재 점수와 직전 점수간의 '차이' 가 곧 감소 횟수가 되고 이에 대한 누적합을 구한다.
            val gap = (cBuffer[i] - cBuffer[i-1]) + 1 // 이를 코드로 표현하면 이와 같고
            cBuffer[i] -= gap // 배열에도 실제로 반영해줘서, 다음번 계산에서 오류를 방지
            cnt += gap // 점수 줄인 크기를 누적합.
        }
    }

    // println(cBuffer.contentToString()) // 디버깅용....
    println(cnt) // print output
}