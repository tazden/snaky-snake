package com.linebgame.linebgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var gameBoard:GameBoard
    lateinit var upbut: ImageButton
    lateinit var leftbut: ImageButton
    lateinit var downbut: ImageButton
    lateinit var rightbut: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameBoard = findViewById(R.id.gameBoard)

        upbut= findViewById(R.id.imageButton)
        leftbut = findViewById(R.id.imageButton2)
        downbut = findViewById(R.id.imageButton4)
        rightbut = findViewById(R.id.imageButton3)

        rightbut.setOnClickListener({
            gameBoard.dir = Pair(1,0)
        })

        leftbut.setOnClickListener({
            gameBoard.dir = Pair(-1,0)
        })

        upbut.setOnClickListener({
            gameBoard.dir = Pair(0,-1)
        })

        downbut.setOnClickListener({
            gameBoard.dir = Pair(0,1)
        })

        gameBoard.gameloop.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        gameBoard.destroy()
    }
}