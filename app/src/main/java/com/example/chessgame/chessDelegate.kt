package com.example.chessgame

import android.graphics.Canvas

interface chessDelegate {
    fun pieceAt(col:Int,row:Int): ChessPiece?
    fun movePiece(fromCol:Int,fromRow:Int,toCol:Int,toRow:Int)

//    fun drawpiece(canvas: Canvas, a:Int, b:Int, piece:Int)

    fun drawpiec(canvas: Canvas,a:Int,b:Int,piece:Int)

}