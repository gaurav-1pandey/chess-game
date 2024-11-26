package com.example.chessgame

interface chessDelegate {
    fun pieceAt(col:Int,row:Int): ChessPiece?
    fun movePiece(fromCol:Int,fromRow:Int,toCol:Int,toRow:Int)
    fun drawpiec()
}