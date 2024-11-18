package com.example.chessgame

interface chessDelegate {
    fun pieceAt(col:Int,row:Int): ChessPiece?

}