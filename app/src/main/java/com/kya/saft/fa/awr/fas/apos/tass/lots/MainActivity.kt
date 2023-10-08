package com.kya.saft.fa.awr.fas.apos.tass.lots

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var pointsSumm = 0
        val start = 1
        val end = 3
        val end2 = 2

        val btnStart = findViewById<Button>(R.id.startgame)
        val ball = findViewById<ImageView>(R.id.ball)
        val ballLeft = findViewById<ImageView>(R.id.balll)
        val ballRight = findViewById<ImageView>(R.id.ballr)
        ballRight.visibility = View.INVISIBLE
        ballLeft.visibility = View.INVISIBLE

        val cball = findViewById<CardView>(R.id.cball)
        val lball = findViewById<CardView>(R.id.lball)
        val rball = findViewById<CardView>(R.id.rball)
        cball.visibility = View.INVISIBLE
        lball.visibility = View.INVISIBLE
        rball.visibility = View.INVISIBLE
        var missOrGoal = findViewById<TextView>(R.id.mgoal)
        missOrGoal.text=""
        var points = findViewById<TextView>(R.id.mypoints)
        points.text = "MY POINTS IS $pointsSumm"

        fun rand(start: Int, end: Int): Int {
            require(start <= end) { "Illegal Argument" }
            val rand = Random(System.nanoTime())
            return (start..end).random(rand)
        }

        btnStart.setOnClickListener {
            cball.visibility = View.VISIBLE
            lball.visibility = View.VISIBLE
            rball.visibility = View.VISIBLE
            btnStart.alpha = 0.5F

            rball.background =(getResources().getDrawable(R.drawable.cball))
            lball.background =(getResources().getDrawable(R.drawable.cball))
            cball.background =(getResources().getDrawable(R.drawable.cball))

            cball.alpha = 0.5F
            lball.alpha = 0.5F
            rball.alpha = 0.5F

            missOrGoal.text = ""
            if(pointsSumm%5==0 && pointsSumm!=0){
                ballLeft.visibility = View.VISIBLE
                ballRight.visibility = View.VISIBLE
                ball.visibility = View.VISIBLE

                val timer = object: CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        ballLeft.visibility = View.INVISIBLE
                        ballRight.visibility = View.INVISIBLE
                        ball.visibility = View.INVISIBLE
                        cball.background =(getResources().getDrawable(R.drawable.ball))
                        lball.background =(getResources().getDrawable(R.drawable.ball))
                        rball.background =(getResources().getDrawable(R.drawable.ball))
                        pointsSumm+=3
                        cball.alpha = 1.0F
                        lball.alpha = 1.0F
                        rball.alpha = 1.0F
                        btnStart.alpha = 1.0F
                        points.text = "MY POINTS IS $pointsSumm"
                        missOrGoal.text = "BOOONUS GAAAME!"

                    }
                }
                timer.start()
            }
        }

        cball.setOnClickListener {
            btnStart.alpha = 1.0F
            if(rand(start, end) == 1 ){
                cball.background =(getResources().getDrawable(R.drawable.ball))
                cball.alpha = 1.0F
                pointsSumm+=1
                points.text = "MY POINTS IS $pointsSumm"
                rball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                lball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                missOrGoal.text = "Hit"
                missOrGoal.setTextColor(getResources().getColor(R.color.goal))
                ball.visibility = View.INVISIBLE
            }else{
                cball.background =(getResources().getDrawable(R.drawable.ball))
                cball.alpha = 1.0F
                ball.visibility = View.INVISIBLE
                missOrGoal.text = "Miss"
                missOrGoal.setTextColor(getResources().getColor(R.color.miss))
            }}

        lball.setOnClickListener {
            btnStart.alpha = 1.0F
            if(rand(start, end) == 1 ){
                lball.background =(getResources().getDrawable(R.drawable.ball))
                lball.alpha = 1.0F
                pointsSumm+=1
                points.text = "MY POINTS IS $pointsSumm"
                rball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                cball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                missOrGoal.text = "Hit"
                missOrGoal.setTextColor(getResources().getColor(R.color.goal))
                ball.visibility = View.INVISIBLE
            }else{
                lball.background =(getResources().getDrawable(R.drawable.ball))
                lball.alpha = 1.0F
                ball.visibility = View.INVISIBLE
                missOrGoal.text = "Miss"
                missOrGoal.setTextColor(getResources().getColor(R.color.miss))
            }}
        rball.setOnClickListener {
            btnStart.alpha = 1.0F
            if(rand(start, end) == 1 ){
                rball.background =(getResources().getDrawable(R.drawable.ball))
                rball.alpha = 1.0F
                pointsSumm+=1
                points.text = "MY POINTS IS $pointsSumm"
                cball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                lball.setCardBackgroundColor(getResources().getColor(R.color.miss))
                missOrGoal.text = "Hit"
                missOrGoal.setTextColor(getResources().getColor(R.color.goal))
                ball.visibility = View.INVISIBLE
            }else{
                rball.background =(getResources().getDrawable(R.drawable.ball))
                rball.alpha = 1.0F
                ball.visibility = View.INVISIBLE
                missOrGoal.text = "Miss"
                missOrGoal.setTextColor(getResources().getColor(R.color.miss))
            }}

    }
}