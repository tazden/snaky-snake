package com.levaniphoenix.mysnakeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    lateinit var gameBoard:GameBoard
    lateinit var upButton: ImageButton
    lateinit var leftButton: ImageButton
    lateinit var downButton: ImageButton
    lateinit var rightButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gameBoard = findViewById(R.id.gameBoard)

        upButton= findViewById(R.id.imageButton)
        leftButton = findViewById(R.id.imageButton2)
        downButton = findViewById(R.id.imageButton4)
        rightButton = findViewById(R.id.imageButton3)

        rightButton.setOnClickListener({
            gameBoard.dir = Pair(1,0)
        })

        leftButton.setOnClickListener({
            gameBoard.dir = Pair(-1,0)
        })

        upButton.setOnClickListener({
            gameBoard.dir = Pair(0,-1)
        })

        downButton.setOnClickListener({
            gameBoard.dir = Pair(0,1)
        })

        gameBoard.gameloop.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        gameBoard.destroy()
    }
}