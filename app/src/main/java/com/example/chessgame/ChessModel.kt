package com.example.chessgame

import android.util.Log
import android.view.MotionEvent
import android.view.View

class ChessModel {
    var pieceBox= mutableSetOf<ChessPiece>()

    var chessPlayer=ChessPlayer.WHITE

    init {
        reset()

//        movePiece(1,2,1,8)
//        movePiece(1,8,3,3)
    }

    fun reset(){
        pieceBox.clear()
        pieceBox.add(ChessPiece(1,1,ChessPlayer.WHITE,ChessRank.HATHI ,R.drawable.rook_white))
        pieceBox.add(ChessPiece(2,1,ChessPlayer.WHITE,ChessRank.GHODA,R.drawable.knight_white ))
        pieceBox.add(ChessPiece(3,1,ChessPlayer.WHITE,ChessRank.OONT,R.drawable.bishop_white ))
        pieceBox.add(ChessPiece(4,1,ChessPlayer.WHITE,ChessRank.RANI,R.drawable.queen_white ))
        pieceBox.add(ChessPiece(5,1,ChessPlayer.WHITE,ChessRank.RAJA,R.drawable.king_white ))
        pieceBox.add(ChessPiece(6,1,ChessPlayer.WHITE,ChessRank.OONT,R.drawable.bishop_white ))
        pieceBox.add(ChessPiece(7,1,ChessPlayer.WHITE,ChessRank.GHODA ,R.drawable.knight_white))
        pieceBox.add(ChessPiece(8,1,ChessPlayer.WHITE,ChessRank.HATHI ,R.drawable.rook_white))
        pieceBox.add(ChessPiece(1,2,ChessPlayer.WHITE,ChessRank.PYADA,R.drawable.pawn_white ))
        pieceBox.add(ChessPiece(2,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(3,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(4,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(5,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(6,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(7,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))
        pieceBox.add(ChessPiece(8,2,ChessPlayer.WHITE,ChessRank.PYADA ,R.drawable.pawn_white))

        pieceBox.add(ChessPiece(1,8,ChessPlayer.BLACK,ChessRank.HATHI ,R.drawable.rook_black))
        pieceBox.add(ChessPiece(2,8,ChessPlayer.BLACK,ChessRank.GHODA,R.drawable.knight_black ))
        pieceBox.add(ChessPiece(3,8,ChessPlayer.BLACK,ChessRank.OONT,R.drawable.bishop_black ))
        pieceBox.add(ChessPiece(4,8,ChessPlayer.BLACK,ChessRank.RANI,R.drawable.queen_black ))
        pieceBox.add(ChessPiece(5,8,ChessPlayer.BLACK,ChessRank.RAJA,R.drawable.king_black ))
        pieceBox.add(ChessPiece(6,8,ChessPlayer.BLACK,ChessRank.OONT,R.drawable.bishop_black ))
        pieceBox.add(ChessPiece(7,8,ChessPlayer.BLACK,ChessRank.GHODA ,R.drawable.knight_black))
        pieceBox.add(ChessPiece(8,8,ChessPlayer.BLACK,ChessRank.HATHI ,R.drawable.rook_black))
        pieceBox.add(ChessPiece(1,7,ChessPlayer.BLACK,ChessRank.PYADA,R.drawable.pawn_black ))
        pieceBox.add(ChessPiece(2,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(3,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(4,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(5,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(6,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(7,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))
        pieceBox.add(ChessPiece(8,7,ChessPlayer.BLACK,ChessRank.PYADA ,R.drawable.pawn_black))

    }
    fun pieceAt(col:Int,row:Int): ChessPiece? {
        for (piece in pieceBox){
            if (col == piece.col && row == piece.row){
                return piece
            }
        }
        return null
    }
    fun movePiece(fromCol:Int,fromRow:Int,toCol:Int,toRow:Int):Boolean{
        var movingPiece=pieceAt(fromCol,fromRow)
        var flag=false
        var attackedPiece=pieceAt(toCol,toRow)



        if (movingPiece!=null){


            if (chessPlayer==movingPiece.player){
                chessPlayer = if (chessPlayer==ChessPlayer.BLACK) ChessPlayer.WHITE else ChessPlayer.BLACK
            }
            else{
                return flag
            }
            if (attackedPiece!=null ){
                if (attackedPiece.player!=movingPiece.player){
//                    pieceBox.remove(attackedPiece)
                    attackedPiece.col=-1
                    attackedPiece.row=-1
                    movingPiece.col=toCol
                    movingPiece.row=toRow
                    flag=true

                    for ( k in pieceBox){

                        Log.d("hellog","${k.col}  ${k.row}    ${k.rank}   ${k.player}")
                    }
                }

            }
            else {

                movingPiece.col=toCol
                movingPiece.row=toRow
                flag=true
                for ( k in pieceBox){
                    Log.d("hellog","${k.col}  ${k.row}    ${k.rank}  ${k.player} kk")
                }
            }



        }
        Log.d("hellog",toString())
        return flag

    }

    override fun toString(): String {

        var desc=""

        for (row in 7 downTo 0 ){
            desc+= "$row "
            for ( col in 0..7){

                var piece=pieceAt(col,row)

                if (piece==null){
                    desc+=". "
                }
                else {
                    desc+= when(piece.rank){
                        ChessRank.HATHI -> if (piece.player == ChessPlayer.WHITE) "r " else "R "
                        ChessRank.GHODA -> if (piece.player == ChessPlayer.WHITE) "n " else "N "
                        ChessRank.RAJA -> if (piece.player == ChessPlayer.WHITE) "k " else "K "
                        ChessRank.RANI -> if (piece.player == ChessPlayer.WHITE) "q " else "Q "
                        ChessRank.PYADA -> if (piece.player == ChessPlayer.WHITE) "p " else "P "
                        ChessRank.OONT -> if (piece.player == ChessPlayer.WHITE) "b " else "B "
                    }
                }

            }
            desc+="\n"

        }
        desc+="  0 1 2 3 4 5 6 7\n"


        return desc
    }
}