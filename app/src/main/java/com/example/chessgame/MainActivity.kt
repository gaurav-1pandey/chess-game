package com.example.chessgame

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

class MainActivity : AppCompatActivity(),chessDelegate {
    var chessModel=ChessModel()
    lateinit var chessView:ChessView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("hellog",chessModel.toString())

        chessView=findViewById<ChessView>(R.id.chessV)
        chessView.chDelegate=this





    }
    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        chessModel.movePiece(fromCol,fromRow,toCol,toRow)
        chessView.invalidate()
    }

    override fun drawpiec(canvas: Canvas, a: Int, b: Int, piece: Int) {
        chessView.drawpiec(canvas,a,b,piece)
        chessView.invalidate()
    }


}