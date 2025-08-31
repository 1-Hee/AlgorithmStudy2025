package problem250831

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.HashMap
import java.util.StringTokenizer

// https://www.acmicpc.net/problem/2578
fun main(){
    System.setIn(FileInputStream("data/250831/boj2578.txt"))

    val br = BufferedReader(InputStreamReader(System.`in`))

    val bingoBoard = Array(5) { IntArray(5) { 0 } }
    val boardCall = Array(5) { BooleanArray(5) { false } }
    val callMap:HashMap<Int, IntArray> = hashMapOf()

    var st:StringTokenizer
    var idx:Int
    // input arr
    for(i in 0 until 5){
        idx = 0;
        st = StringTokenizer(br.readLine())
        while(st.hasMoreTokens()){
            val num = st.nextToken().toInt()
            callMap[num] = intArrayOf(i, idx)
            bingoBoard[i][idx++] = num
        }
    }

    // call start
    var recordCnt = 0;
    for(i in 0 until 5){
        st = StringTokenizer(br.readLine())
        while(st.hasMoreTokens()){
            val num = st.nextToken().toInt()
            val position:IntArray = callMap[num]?:continue
            val posX = position[0]
            val posY = position[1]
            boardCall[posX][posY] = true
            recordCnt++;

            if(recordCnt > 4){
                val cntX = getRowCall(boardCall)
                val cntY = getColCall(boardCall)
                val cntC = getCrossCall(boardCall)
                val sum = (cntX + cntY + cntC)
                if(sum > 2){
                    println(recordCnt)
                    return
                }
            }
        }
    }



}

fun getRowCall(boardCall:Array<BooleanArray>): Int {
    var cnt = 0;
    var row = 0;
    boardCall.forEach { line ->
        cnt = 0;
        line.forEach { item ->
            if(item){
                cnt++
            }
        }
        if(cnt == 5){
            row++;
        }
    }
    return row
}

fun getColCall(boardCall:Array<BooleanArray>): Int {
    var cnt = 0;
    var col = 0;
    val rowSize = boardCall.size
    val colSize = boardCall[0].size

    for(c in 0 until colSize){
        cnt = 0;
        for(r in 0 until rowSize){
            if(boardCall[r][c]){
                cnt++;
            }
        }
        if(cnt == 5){
            col ++;
        }
    }

    return col;
}

fun getCrossCall(boardCall: Array<BooleanArray>):Int {
    var cnt = 0;
    var cross = 0;

    val dr = arrayOf(1, -1, 0, 0)
    val dc = arrayOf(0, 0, 1, -1)

    var insX = 0
    var insY = 0
    for(i in 0 until 5){
        if(boardCall[insX][insY]){
            cnt++
        }
        insX += dr[0]
        insY += dc[2]
    }
    if(cnt == 5){
        cross++;
    }

    insX = 4;
    insY = 0;
    cnt = 0;
    for(i in 0 until 5){
        if(boardCall[insX][insY]){
            cnt++
        }
        insX += dr[1]
        insY += dc[2]
    }
    if(cnt == 5){
        cross++;
    }

    return cross
}