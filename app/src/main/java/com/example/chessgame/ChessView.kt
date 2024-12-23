package com.example.chessgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast

class ChessView(context:Context?,attrs:AttributeSet?):View(context,attrs) {


    var chDelegate :chessDelegate?=null
    var squareSize=(width/10).toFloat()
    lateinit var cnvs:Canvas



    var xx:Int=-1
    var yy=-1


    var topstart=((height-squareSize*8)/2).toFloat()

    private val imgres= setOf<Int>(R.drawable.queen_white,
        R.drawable.bishop_white,
        R.drawable.bishop_black,
        R.drawable.king_black,
        R.drawable.king_white,
        R.drawable.knight_black,
        R.drawable.knight_white,
        R.drawable.pawn_black,
        R.drawable.pawn_white,
        R.drawable.queen_black,
        R.drawable.rook_black,
        R.drawable.rook_white)
    var map= mutableMapOf<Int,Bitmap>()
    var paint1=Paint()
    var paint2=Paint()

    var loc=Array(8){Array(8){Array(4){0.0f} } }

    fun loadBM(){
        imgres.forEach {
            map[it] = BitmapFactory.decodeResource(resources, it)!!
        }
    }
    fun drawpiec(canvas: Canvas,a:Int,b:Int,piece:Int?){
        canvas.drawBitmap(map[piece]!!,null,Rect(a,b,a+squareSize.toInt(),b+squareSize.toInt()),paint1)


    }
    fun drawpiece(canvas: Canvas,a:Int,b:Int,piece:Int){

        canvas.drawBitmap(map[piece]!!,null,Rect(loc[(7-a)+1][b-1][0].toInt(),loc[(7-a)+1][b-1][1].toInt(),loc[(7-a)+1][b-1][2].toInt(),loc[(7-a)+1][b-1][3].toInt()),paint1)

    }
    var fromcol=-1
    var fromrow=-1

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?: return false

//        Log.d("hellog","${event.rawX}  " + "  ${event.rawY} ")

        when(event.action){
            MotionEvent.ACTION_DOWN -> {

                fromcol=(event.x/squareSize).toInt()
                fromrow=8-((event.y-topstart)/squareSize).toInt()
                Log.d("hellog","${chDelegate?.pieceAt(fromcol,fromrow)?.player}")
                Log.d("hellog","down  ${fromcol}  ${fromrow}  ${topstart}")
            }

            MotionEvent.ACTION_UP -> {
                Log.d("hellog","up")
                val x=(event.x/squareSize).toInt()
                val y=8-((event.y-topstart)/squareSize).toInt()
                if (x in 1..8 && y in 1..8){
                    chDelegate?.movePiece(fromcol,fromrow,x,y)

                }


                Log.d("hellog1","${x} ${y}")

                Log.d("hellog","${x} ${y}")
                xx=-1
                yy=-1
                invalidate()

            }
            MotionEvent.ACTION_MOVE -> {
                xx=event.x.toInt()
                yy=event.y.toInt()
                chDelegate?.pieceAt(fromcol,fromrow)?.let { chDelegate?.drawpiec() }


            }
        }
        return true
    }
    fun drawpieces(canvas: Canvas){



        for (i in 1..8){
            for (j in 1..8){
                var chessPiece=chDelegate?.pieceAt(i,j)
                if (chessPiece!=null){
                    drawpiece(canvas,j,i,chessPiece.resid)
                }
            }
        }

//        drawpiece(canvas,2,1,R.drawable.pawn_white)
//        drawpiece(canvas,2,2,R.drawable.pawn_white)
//        drawpiece(canvas,2,3,R.drawable.pawn_white)
//        drawpiece(canvas,2,4,R.drawable.pawn_white)
//        drawpiece(canvas,2,5,R.drawable.pawn_white)
//        drawpiece(canvas,2,6,R.drawable.pawn_white)
//        drawpiece(canvas,2,7,R.drawable.pawn_white)
//        drawpiece(canvas,2,8,R.drawable.pawn_white)
//        drawpiece(canvas,1,5,R.drawable.king_white)

    }

    fun logicalXor(a: Boolean, b: Boolean): Boolean {
        return (a && !b) || (!a && b)
    }

    init {
        loadBM()
    }

    override fun onDraw(canvas: Canvas) {

        cnvs=canvas



        squareSize=(width/10).toFloat()
        Log.d("hellog","${squareSize}")

        topstart=((height-squareSize*8)/2).toFloat()


        paint2.color=Color.DKGRAY
        paint1.color=Color.LTGRAY
//        Toast.makeText(context,"$topstart",Toast.LENGTH_LONG).show()

//        canvas.drawRect(100f,topstart,100f+squareSize,topstart+squareSize,paint1)

        for (j in 0..7){
            var flag= if (j%2==0) true else false
            for (i in 1..8 ){
                loc[j][i-1][0]=i*squareSize
                loc[j][i-1][1]=topstart+j*squareSize
                loc[j][i-1][2]=i*squareSize+squareSize
                loc[j][i-1][3]=topstart+squareSize+j*squareSize


                if (logicalXor(i%2==0,flag) ){

                    canvas.drawRect(i*squareSize,topstart+j*squareSize,i*squareSize+squareSize,topstart+squareSize+j*squareSize,paint1)

                }
                else {
                    canvas.drawRect(i*squareSize,topstart+j*squareSize,i*squareSize+squareSize,topstart+squareSize+j*squareSize,paint2)
                }
            }
        }



        Log.d("hellog","${loc[0][0][0]}  ${loc[0][0][1]}  ${loc[0][0][2]}  ${loc[0][0][3]}  ")

        drawpieces(canvas)
        if (xx!=-1){
            drawpiec(canvas,(xx-squareSize/2).toInt(),yy-(squareSize/2).toInt(),chDelegate?.pieceAt(fromcol,fromrow)?.resid)
        }

//        canvas.drawBitmap(map[R.drawable.knight_black]!!,null,Rect(loc[6][2][0].toInt(),loc[6][2][1].toInt(),loc[6][2][2].toInt(),loc[6][2][3].toInt()),paint1)



    }
}