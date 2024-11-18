package com.example.chessgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.Toast

class ChessView(context:Context?,attrs:AttributeSet?):View(context,attrs) {




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

    fun drawpiece(canvas: Canvas,a:Int,b:Int,piece:Int){

        canvas.drawBitmap(map[piece]!!,null,Rect(loc[(7-a)+1][b-1][0].toInt(),loc[(7-a)+1][b-1][1].toInt(),loc[(7-a)+1][b-1][2].toInt(),loc[(7-a)+1][b-1][3].toInt()),paint1)


    }

    fun drawpieces(canvas: Canvas){

        var chessModel:ChessModel=ChessModel()
        chessModel.reset()

        for (i in 1..8){
            for (j in 1..8){
                var chessPiece=chessModel.pieceAt(i,j)
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

        var squareSize=(width/10).toFloat()

        var topstart=((height-squareSize*8)/2).toFloat()


        paint2.color=Color.DKGRAY
        paint1.color=Color.LTGRAY
        Toast.makeText(context,"$topstart",Toast.LENGTH_LONG).show()

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




        drawpieces(canvas)

//        canvas.drawBitmap(map[R.drawable.knight_black]!!,null,Rect(loc[6][2][0].toInt(),loc[6][2][1].toInt(),loc[6][2][2].toInt(),loc[6][2][3].toInt()),paint1)



    }
}