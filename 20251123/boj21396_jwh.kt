package problem251123

import java.io.FileInputStream
import java.util.LinkedList
import java.util.Queue
import java.io.*
import java.util.StringTokenizer
import kotlin.text.StringBuilder

class BinaryCalculator(
    private val N:Int,
    private val X:Int,
    private val inputs:IntArray
) {

    fun getCase():Int {
        ansCnt = 0
        comb(0, 0)
        return ansCnt
    }

    private var ansCnt:Int = 0
    private val R = 2
    private val numbers = Array(2) { 0 }

    private fun comb(idx:Int, cnt:Int) {
        if(cnt == R) {
            val numA:Int = numbers[0]
            val numB:Int = numbers[1]
            val sNumA:String = parseToBinString(numA)
            val sNumB:String = parseToBinString(numB)
            val res:Int = sumAlbert(sNumA, sNumB)
            if(res == X){
                ansCnt++
            }
            return
        }
        for(i in idx until N) {
            numbers[cnt] = inputs[i]
            comb(i+1, cnt+1)
        }
    }

    private fun parseToBinString(value : Int) : String {
        var num:Int = value;
        val charList:MutableList<Char> = mutableListOf()
        do {
            val element = num.and(1)
            val charX:Char = ('0' + element)
            charList.add(charX)
            num = num.shr(1)
        }while(num != 0)

        charList.reverse()

        return charList.joinToString("")
    }

    private fun sumAlbert(bin1:String, bin2:String) : Int {
        var binX = bin1
        var binY = bin2
        if(binX.length > binY.length){
            var gap = binX.length - binY.length
            val prefix = buildString {
               while(gap > 0){
                   append("0")
                   gap--
               }
            }
            binY = "$prefix$bin2"
        } else if(binX.length < binY.length) {
            var gap = binY.length - binX.length
            val prefix = buildString {
                while(gap > 0){
                    append("0")
                    gap--
                }
            }
            binX = "$prefix$bin1"
        }

        val N = binX.length
        val binQueue:Queue<Int> = LinkedList()
        for(i in 0 until N){
            val byteA = (binX[i].code - '0'.code)
            val byteB = (binY[i].code - '0'.code)
            val bRes = (byteA + byteB).and(0x01)
            binQueue.offer(bRes)
        }

        val albertBinStr = buildString {
            while(binQueue.isNotEmpty()){
                append(binQueue.poll())
            }
        }

        // println(albertBinStr)

        return albertBinStr.toInt(2)
    }
}

fun main(){
    System.setIn(FileInputStream("data/251123/boj21396.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb:StringBuilder = StringBuilder()

    val T = br.readLine().toInt()
    var st:StringTokenizer
    for(t in 0 until T){
        st = StringTokenizer(br.readLine())
        val N = st.nextToken().toInt()
        val X = st.nextToken().toInt()
        val inputs = IntArray(N) { 0 }
        st = StringTokenizer(br.readLine())
        for(i in 0 until N){
            inputs[i] = st.nextToken().toInt()
        }

        // X...
        // val binaryCalculator = BinaryCalculator(N, X, inputs)
        // val res = binaryCalculator.getCase()
        // sb.append(res).append("\n")

        // new
        // 연산할 숫자와 빈도를 계산할 맵을 만든다.
        val freq = HashMap<Int, Long>()
        // 각 맵에 빈도를 누적합 시키는 로직
        for (v in inputs) {
            freq[v] = (freq[v] ?: 0) + 1
        }

        var ans = 0L
        // 아래의 식은 조건식으로 분기 되었지만 그 원리는 상동
        // 받아올림 없는 이진수 덧셈은 XOR 연산을 의미함.
        // eg. 7 xor 4 = 3
        // 0000 0111
        // 0000 0100
        // 0000 0011 => 3

        // 주어진 테스트 케이스에서 숫자 3과 7에 대해 생각한다고 하자.
        // 주어진 숫자들의 빈도 수는 freq 맵에 저장된다고 한다면,
        // 이때 숫자 3의 빈도는 freq[3], 숫자 7의 빈도는 freq[7]로 나타낼 수 있다.
        // 이를 수식으로 표현하면, 만들 수 있는 쌍은 freq[3] * freq[7] 개이다.

        // 이러한 원리를 이용하여, 문제에서 원하는 XOR 연산 값을 위한 페어를 찾고자 한다.
        // 이때 주어진 숫자가 담긴 배열을 v라고 했을때,
        // 임의의 숫자 v[i]가 XOR 연산을 통해 X 값이 되려면 그 짝이 될 수 있는 값은
        // 숫자 v[i]를 X로 XOR 한 값임을 만족한다.
        // 즉, v[i]의 짝(pair)은 v[i] XOR X 값으로 검색할 수 있는 것이다.

        // 이를 응용하면 아래의 코드와 같다.

        if (X == 0) { // 찾는 값이 0 일 경우
            // 이 경우 임의의 숫자 v[i]와 v[j]가 연산하여 0이 된다는 것을 의미하는데, 다시 말하자면 a XOR b = 0인 경우를 찾는 것이다.
            // 어떤 임의의 두 수가 XOR 연산하여 0이 된다는 것은 두 값이 같다는 것이다.
            // 즉, 주어진 맵에서 같은 수를 골라 연산하는 경우가 이에 해당할 수 있는데
            // 예를 들어, 값 3이 리스트에 4번 있다고 하자.
            // 이 숫자들 중 서로 다른 두 개를 뽑는 경우의 수는 조합의 계산 식을 통해 아래와 같이 계산할 수 있다.
            // C(f, 2) = f * (f - 1) / 2

            for ((k, f) in freq) {
                ans += f * (f - 1) / 2
            }
        } else { // 0이 아닌 자연수의 경우
            // 이 경우 v[i]의 짝 값을 찾아내어 그 수를 카운팅해 정답을 계산하는 것이다.
            for ((k, f) in freq) {
                val t = k xor X
                if (freq.containsKey(t)) {
                    ans += f * freq[t]!!
                }
            }
            ans /= 2
        }

        sb.append(ans).append("\n")
    }
    println(sb.toString())


}