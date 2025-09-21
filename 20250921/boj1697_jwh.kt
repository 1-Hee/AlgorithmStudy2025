import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

class PeopleBroker(
    private val position: Int,
    private val target:Int
) {
    private val MAX_SIZE = 100_001
    private val distanceMap: IntArray = IntArray(MAX_SIZE) { 0 }

    fun search() : Int {
        return findPath(position, target)
    }

    private fun findPath(position:Int, target:Int) : Int{
        distanceMap.fill(Int.MIN_VALUE)
        val queue: Queue<Int> = LinkedList()
        queue.offer(position)
        distanceMap[position] = 0 // 자기 자신에서 자기 자신으로 가는 길이는 0

        while(queue.isNotEmpty()){
            val pos:Int = queue.poll()

            if( pos -1 >= 0 && distanceMap[pos-1] < distanceMap[pos] - 1 ) {
                queue.offer(pos-1)
                distanceMap[pos-1] = distanceMap[pos] - 1
            }

            if( pos + 1 < distanceMap.size && distanceMap[pos+1] < distanceMap[pos] - 1) {
                queue.offer(pos+1)
                distanceMap[pos+1] = distanceMap[pos] - 1
            }

            if( pos * 2 < distanceMap.size && distanceMap[pos*2] < distanceMap[pos] - 1){
                queue.offer(pos*2)
                distanceMap[pos*2] = distanceMap[pos] - 1
            }

        }

        return abs(distanceMap[target])
    }
}

fun main() {
    System.setIn(FileInputStream("data/boj1697.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())

    val position = st.nextToken().toInt()
    val target = st.nextToken().toInt()

    val broker = PeopleBroker(position, target)
    val result = broker.search()

    println(result)

}