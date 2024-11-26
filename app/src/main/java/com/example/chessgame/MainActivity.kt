package com.example.chessgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity(),chessDelegate {
    var chessModel=ChessModel()
    lateinit var chessView:ChessView
    val database= Firebase.database
    var myref:DatabaseReference?=null

    var code=""

    lateinit var btn_create: Button
    lateinit var btn_join: Button
    lateinit var ed_code:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("hellog",chessModel.toString())


        chessView=findViewById<ChessView>(R.id.chessV)
        chessView.chDelegate=this

        var reset=findViewById<FloatingActionButton>(R.id.reset)

        reset.setOnClickListener(View.OnClickListener {
            chessModel.reset()
            chessView.invalidate()
        })


        btn_create=findViewById(R.id.btn_create)
        btn_join=findViewById(R.id.btn_join)
        ed_code=findViewById(R.id.ed_Code)

        btn_create.setOnClickListener{
            connect(database)
        }

        btn_join.setOnClickListener{
            join()
        }
    }
    override fun pieceAt(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(fromCol: Int, fromRow: Int, toCol: Int, toRow: Int) {
        chessModel.movePiece(fromCol,fromRow,toCol,toRow,"x")
        chessModel.f=true
        chessView.invalidate()




    }



    override fun drawpiec() {
        chessView.invalidate()
    }

    fun connect(database: FirebaseDatabase){
        code=ed_code.text.toString()
        if (code.isBlank()){
            Toast.makeText(applicationContext,"please enter a code",Toast.LENGTH_SHORT).show()
        }
        else{


            myref=database.getReference(code)
            chessModel.myref=myref
            myref?.child("move")?.setValue("")
            myref?.child("player")?.setValue("white")?.addOnSuccessListener {
                Toast.makeText(applicationContext,"game created ${code}",Toast.LENGTH_SHORT).show()
                btn_create.visibility=View.INVISIBLE
                btn_join.visibility=View.INVISIBLE
                ed_code.visibility=View.INVISIBLE
                addevent()

            }
        }


    }



    fun join(){
        code=ed_code.text.toString()


        if (code.isNotEmpty() ){
            myref=database.getReference(code)
            chessModel.myref=myref

            chessModel.j()
            chessView.invalidate()
            myref?.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()){
                        btn_create.visibility=View.INVISIBLE
                        btn_join.visibility=View.INVISIBLE
                        ed_code.visibility=View.INVISIBLE
                        Toast.makeText(applicationContext,"game joined ${code}",Toast.LENGTH_SHORT).show()
                        addevent()

                    }
                    else{
                        Toast.makeText(applicationContext,"no game found ${code}",Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }

    }


    fun addevent(){
                myref?.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var mv=snapshot.child("move").value.toString()
                if (mv.isNotEmpty()){
                    val input =mv
                    val parts = input.trim().split("\\s+".toRegex())

                    val num1 = parts[0].toInt()
                    val num2 = parts[1].toInt()
                    val num3 = parts[2].toInt()
                    val num4 = parts[3].toInt()
//
                    movePiece(9-num1,9-num2,9-num3,9-num4)


                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}