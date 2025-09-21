import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue


data class Person(
    val x:Int = 0,
    val y:Int = 0,
    val value: Char = 0x20.toChar()
)

class GroupFinder(
    private val N:Int = 5,
    private val R:Int = 7,
    private val peopleArr: Array<Person> = Array(N*N) { Person() },
    private val memberArr: Array<Person> = Array(R) { Person() }
) {

    private var tCnt = 0

    fun searchCount():Int {
        tCnt = 0
        memberArr.fill(Person())

        lookupPersonList(0, 0)

        return tCnt
    }

    private fun lookupPersonList(idx:Int, cnt:Int){
        if(cnt == R){
            var flag = checkAssemble(memberArr)
            if(!flag) return

            flag = checkPeopleLink(memberArr)
            if(flag) tCnt++
            return
        }
        for(i in idx until (N*N)) {
            memberArr[cnt] = peopleArr[i]
            lookupPersonList(i+1, cnt+1)
        }
    }

    private fun checkAssemble(memberArray: Array<Person>) : Boolean {
        var cnt = 0
        memberArray.forEach { item ->
            if(item.value == 'S') cnt++
        }

        return cnt > 3
    }

    private val dx: IntArray = intArrayOf(-1, 1, 0, 0)
    private val dy: IntArray = intArrayOf(0, 0, -1, 1)
    private fun checkPeopleLink(memberArray: Array<Person>) : Boolean{
        if(memberArray.isEmpty()) return false
        val queue: Queue<Person> = LinkedList<Person>()
        val size:Int = memberArray.size
        val isVisited = BooleanArray(size) { false }
        isVisited[0] = true
        queue.offer(memberArray[0])

        while (queue.isNotEmpty()){
            val curPerson = queue.poll()

            for(i in isVisited.indices){
                if(isVisited[i]) continue

                for(d in 0 until 4){
                    val nx = curPerson.x + dx[d]
                    val ny = curPerson.y + dy[d]
                    val flag: Boolean = nx !in 0..N || ny !in 0..N
                    if(flag) continue
                    val nNext:Person = memberArray[i]
                    val isNext: Boolean = nNext.x == nx && nNext.y == ny
                    if(isNext){
                        isVisited[i] = true
                        queue.offer(memberArray[i])
                        break
                    }
                }
            }
        }

        isVisited.forEach { flag ->
            if(!flag) return false
        }
        return true
    }
}


fun main(){
    System.setIn(FileInputStream("data/boj1941.txt"))

    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = 5
    val peopleArr: Array<Person> = Array(N*N) { Person() }

    var idx = 0
    for(i in 0 until N){
        val line: String = br.readLine()
        for(j in 0 until N){
            val mChar: Char = line[j]
            val mPeople = Person(i, j, mChar)
            peopleArr[idx++] = mPeople
        }
    }

    val finder = GroupFinder(N, peopleArr = peopleArr)
    val cnt = finder.searchCount()
    print(cnt)

}
