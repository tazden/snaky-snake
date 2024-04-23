package com.linebgame.linebgame


class GameLoop(gameBoard: GameBoard) : Thread(){

    val mGameBoard = gameBoard
    var maxFPS = 5
    var timemillis= 1000/maxFPS

    var exit = false
    var time = System.currentTimeMillis()

    override fun run() {
        super.run()
        while(!exit){
            while(true){
                timemillis= 1000/maxFPS
                val deltaTime = System.currentTimeMillis() - time
                if(deltaTime >= timemillis){
                    time = System.currentTimeMillis()
                    mGameBoard.update()
                }
            }
        }
    }
    fun stopRunning(){
        exit = true
    }
    fun increaseSpeed(){
        maxFPS = maxFPS + 1
    }
    fun resetSpeed(){
        maxFPS = 5
    }
}