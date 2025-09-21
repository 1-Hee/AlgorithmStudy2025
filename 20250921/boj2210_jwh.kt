import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.StringTokenizer

class NumberFinder(
    private val map:Array<IntArray>
) {
    private val ROW = map.size
    private val COL = if(map.isEmpty()) 0 else map[0].size

    private val dx: IntArray = intArrayOf(-1, 1, 0, 0)
    private val dy: IntArray = intArrayOf(0, 0, -1, 1)

    private val numSet: HashSet<String> = hashSetOf()

    fun search():Int{
        numSet.clear()

        val array = IntArray(6) { 0 }

        map.forEachIndexed { r, row ->
            row.forEachIndexed { c, item ->
                array.fill(0)
                array[0] = map[r][c]
                find(0, r, c, array, array.size-1)
            }
        }

        return numSet.size
    }

    private fun find(
        cnt:Int,
        x:Int,
        y:Int,
        array: IntArray,
        maxSize:Int = array.size-1
    ){
        if(cnt == maxSize){
            var strX: String = ""
            array.forEach{ item ->
                strX += "$item"
            }
            numSet.add(strX)
            return
        }

        for(i in 0 .. 3){
            val mx:Int = dx[i]
            val my:Int = dy[i]
            val nx = x + mx
            val ny = y + my
            val flag = nx !in 0..<ROW || ny !in 0..<COL
            if(flag) continue
            array[cnt+1] = map[nx][ny]
            find(cnt+1, nx, ny, array)
        }
    }

}

fun main(){
    System.setIn(FileInputStream("data/boj2210.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))


    val R = 5
    val C = 5

    var st: StringTokenizer
    val map: Array<IntArray> = Array(R) { IntArray(C) { 0 } }

    for(i in 0 until R){
        st = StringTokenizer(br.readLine())
        for(j in 0 until C) {
            map[i][j] = st.nextToken().toInt()
        }
    }

    val finder = NumberFinder(map)
    val cnt = finder.search()
    println(cnt)

}

