package com.levaniphoenix.mysnakeapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class GameBoard(context: Context, attrs: AttributeSet): View(context, attrs) {
    var mPaint = Paint()
    var foodPaint = Paint()
    val cubeSize = convertDpToPixels(context,20)
    val gameloop : GameLoop

    var snakeSegments= mutableListOf<Pair<Float,Float>>(Pair(0f, 0f), Pair(1f, 0f),Pair(2f, 0f))
    var alive = true;
    var dir = Pair(1,0)
    var foodPos= placeFood()

    init {
        mPaint.setColor(Color.parseColor("#C2BBF0"))
        mPaint.setStyle(Paint.Style.FILL)
        mPaint.setAntiAlias(true)
        mPaint.setDither(true)


        //foodPaint.setColor(Color.parseColor("#F1E3F3"))
        foodPaint.setColor(Color.parseColor("#ffc6c6"))
        foodPaint.setStyle(Paint.Style.FILL)
        foodPaint.setAntiAlias(true)
        foodPaint.setDither(true)

        gameloop = GameLoop(this)

    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawARGB(255, 98, 191, 237)
        //canvas.drawRect(0f,0f,cubeSize,cubeSize,mPaint)
        //draw snake
        Log.d("drawing snake",snakeSegments.toString() )
        for (pos in snakeSegments){
            canvas.drawRect(pos.first*cubeSize,pos.second*cubeSize,pos.first*cubeSize+cubeSize,pos.second*cubeSize+cubeSize,mPaint)
        }

        //draw food
            canvas.drawCircle(foodPos.first*cubeSize+cubeSize/2,foodPos.second*cubeSize+cubeSize/2 ,cubeSize/2,foodPaint)

    }

    fun update(){
        snakeSegments =updateSnake(snakeSegments)
        invalidate()
    }

    fun updateSnake(list: MutableList<Pair<Float,Float>>): MutableList<Pair<Float,Float>> {

        val head = list.last()
        var newHead= Pair(head.first + dir.first, head.second+dir.second)

        if(newHead.first >= 15)
            newHead = Pair(0f,newHead.second)

        if(newHead.first <= -1)
            newHead = Pair(14f,newHead.second)

        if(newHead.second <= -1)
            newHead = Pair(newHead.first,19f)

        if(newHead.second >= 20)
            newHead = Pair(newHead.first,0f)

        for(segment in list){
            if(newHead.first == segment.first && newHead.second == segment.second){
                gameloop.resetSpeed()
                list.size
                return mutableListOf<Pair<Float,Float>>(list[list.size-3], list[list.size-2],list[list.size-1])
            }
        }

        if (newHead.first == foodPos.first && newHead.second == foodPos.second )
        {
            foodPos=placeFood()
            gameloop.increaseSpeed()
        }else{
            list.removeAt(0)
        }
        list.add(newHead)

        return list
    }

    fun convertDpToPixels(context: Context, dp: Int) =
        (dp * context.resources.displayMetrics.density)

    fun destroy(){
        gameloop.stopRunning()
    }

    fun placeFood(): Pair<Float, Float> {
        val posX = (0..14).random()
        val posY = (0..19).random()
        return Pair(posX.toFloat(),posY.toFloat())
    }
}