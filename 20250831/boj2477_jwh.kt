package problem250831

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*
import kotlin.math.abs

// https://www.acmicpc.net/problem/2477
fun main(){
    /*
    System.setIn(FileInputStream("data/250831/boj2477.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val fieldData = Array(6) { IntArray(2) { 0 } }
    val innerSet:HashSet<Int> = hashSetOf()

    var st: StringTokenizer
    for(i in 0 until 6) {
        st = StringTokenizer(br.readLine());
        val way:Int = st.nextToken().toInt()
        val len:Int = st.nextToken().toInt()
        fieldData[i][0] = way;
        fieldData[i][1] = len;
    }

    var innerArea = 1
    for(i in fieldData.indices){
        if(fieldData[i][0] == fieldData[(i+2)%6][0]){
            innerArea *= fieldData[(i+1)%6][1]
            innerSet.add(fieldData[(i+1)%6][0]);
        }
    }

    var outerArea = 1
    for(i in fieldData.indices){
        if(!innerSet.contains(fieldData[i][0])){
            outerArea *= fieldData[i][1]
        }
    }

    val totalArea = outerArea - innerArea
    val result = n * totalArea

    println(result)
     */

    System.setIn(FileInputStream("data/250831/boj2477.txt"))
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val dirArr = IntArray(6) { -1 }
    val lenArr = IntArray(6) { -1 }


    var iMaxX:Int = 0
    var iMaxY:Int = 0
    var mMaxX:Int = Int.MIN_VALUE
    var mMaxY:Int = Int.MIN_VALUE

    var st:StringTokenizer
    for(i in 0 until 6) {
        st = StringTokenizer(br.readLine())
        val dir = st.nextToken().toInt()
        val len = st.nextToken().toInt()
        dirArr[i] = dir
        lenArr[i] = len

        if(dir == 1 || dir == 2){ // row
            if(len > mMaxX){
                iMaxX = i
                mMaxX = len
            }
        } else {
            if(len > mMaxY) {
                iMaxY = i
                mMaxY = len
            }
        }
    }

    val outArea = mMaxX * mMaxY
    val innerWidth = abs( lenArr[(iMaxX+5)%6]-lenArr[(iMaxX+1)%6] )
    val innerHeight = abs( lenArr[(iMaxY+5)%6]-lenArr[(iMaxY+1)%6] )
    val innerArea = innerWidth * innerHeight

    val totalArea = outArea - innerArea
    val result = n * totalArea

    println(result)




}