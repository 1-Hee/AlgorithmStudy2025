package problem250824

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.HashMap
import java.util.StringTokenizer

// 학생들이 추천을 시작하기 전에 모든 사진틀은 비어있다.
// 어떤 학생이 특정 학생을 추천하면, 추천받은 학생의 사진이 반드시 사진틀에 게시되어야 한다.
// 비어있는 사진틀이 없는 경우에는 현재까지 추천 받은 횟수가 가장 적은 학생의 사진을 삭제하고, 그 자리에 새롭게 추천받은 학생의 사진을 게시한다. 이때, 현재까지 추천 받은 횟수가 가장 적은 학생이 두 명 이상일 경우에는 그러한 학생들 중 게시된 지 가장 오래된 사진을 삭제한다.
// 현재 사진이 게시된 학생이 다른 학생의 추천을 받은 경우에는 추천받은 횟수만 증가시킨다.
// 사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생이 추천받은 횟수는 0으로 바뀐다.

data class ArtWork(
    val num:Int,
    var like:Int = 0,
    var date:Int = 0,
)

fun main(){
    System.setIn(FileInputStream("data/boj1713.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    // 1 <= n <= 2
    val rcStatus = IntArray(101) { 0 }
    // val gallery = Array<ArtWork?>(n) { null }
    val gallery = HashMap<Int, ArtWork>()

    val st = StringTokenizer(br.readLine())
    for(i in 0 until m){
        val rcNum:Int = st.nextToken().toInt()

        rcStatus[rcNum]++

        if(gallery.size < n){
            val item:ArtWork = gallery.getOrDefault(rcNum, ArtWork(rcNum, 0, i))
            item.like = rcStatus[rcNum]
            gallery[rcNum] = item
        } else {
            val item:ArtWork? = gallery[rcNum]
            if(item != null) {
                // 랭킹 좋아요 처리만.
                item.like = rcStatus[rcNum]
                gallery[rcNum] = item
                continue
            }
            // 랭킹 처리
            // 추천 받은 횟수가 가장 적은 학생의 사진을 삭제
            var minKey = -1;
            var minLike = Int.MAX_VALUE
            var mDate = -1
            gallery.toList().forEachIndexed { _, pair ->
                val artWork:ArtWork = pair.second
                if(minLike > artWork.like){
                    minKey = artWork.num
                    minLike = artWork.like
                    mDate = artWork.date
                }else if (minLike == artWork.like) {
                    if(artWork.date < mDate){
                        minKey = artWork.num
                        minLike = artWork.like
                        mDate = artWork.date
                    }
                }
            }

            gallery.remove(minKey)
            rcStatus[minKey] = 0 // 사진틀에 게시된 사진이 삭제되는 경우에는 해당 학생이 추천받은 횟수는 0으로 바뀐다.

            // 그 자리에 새롭게 추천받은 학생의 사진을 게시
            val newItem = ArtWork(rcNum, 0, i)
            newItem.like = rcStatus[rcNum]
            gallery[rcNum] = newItem

            
        }
    }

    val sb = StringBuilder()
    val sortedGallery = gallery.toSortedMap()
    sortedGallery.forEach { item ->
        sb.append(item.value.num).append(" ")
    }
    println(sb.toString())
}