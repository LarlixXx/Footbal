package com.kya.saft.fa.awr.fas.apos.tass.lots

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kya.saft.fa.awr.fas.apos.tass.lots.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @SuppressLint("ResourceAsColor", "UseCompatLoadingForDrawables")
    private lateinit var binding: ActivityMainBinding
    val start = 1
    val end = 3
    var pointsSumm = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.textPoints.text = "MY POINTS IS $pointsSumm"

        fun rand(start: Int, end: Int): Int {
            require(start <= end) { "Illegal Argument" }
            val rand = Random(System.nanoTime())
            return (start..end).random(rand)
        }
        fun redBallsVisible(request:Int){
            if(request==1){
                binding.leftBallRed.visibility = View.VISIBLE
                binding.centerBallRed.visibility = View.VISIBLE
                binding.rightBallRed.visibility = View.VISIBLE
            }else{
                binding.leftBallRed.visibility = View.INVISIBLE
                binding.centerBallRed.visibility = View.INVISIBLE
                binding.rightBallRed.visibility = View.INVISIBLE
            }

        }
        fun ballsVisible(request:Int){
            if(request==1){
                binding.rightBall.visibility = View.VISIBLE
                binding.leftBall.visibility = View.VISIBLE
                binding.centerBall.visibility = View.VISIBLE
            }else{
                binding.rightBall.visibility = View.INVISIBLE
                binding.leftBall.visibility = View.INVISIBLE
                binding.centerBall.visibility = View.INVISIBLE
            }
        }
        fun setRedBackgroundBall(){
            binding.rightBallRed.background = (getResources().getDrawable(R.drawable.cball))
            binding.centerBallRed.background = (getResources().getDrawable(R.drawable.cball))
            binding.leftBallRed.background = (getResources().getDrawable(R.drawable.cball))
        }
        fun controlRedBalls(request:Int){
            if (request == 1){
            binding.leftBallRed.isEnabled = false
            binding.centerBallRed.isEnabled = false
            binding.rightBallRed.isEnabled = false
            }else{
                binding.leftBallRed.isEnabled = true
                binding.centerBallRed.isEnabled = true
                binding.rightBallRed.isEnabled = true
            }
        }
        fun win(count:Int = 1){
            pointsSumm += count
            binding.textPoints.text = "MY POINTS IS $pointsSumm"
            binding.textMissGoal.text = "Hit"
            binding.textMissGoal.setTextColor(getResources().getColor(R.color.goal))
        }
        fun lose(){
            binding.textMissGoal.text = "Miss"
            binding.textMissGoal.setTextColor(getResources().getColor(R.color.miss))
        }
        fun punch(){
            binding.btnStart.alpha = 1.0F
            binding.btnStart.isEnabled = true
            controlRedBalls(1)
        }

        binding.btnStart.setOnClickListener {
            redBallsVisible(1)
            setRedBackgroundBall()
            controlRedBalls(52)

            binding.btnStart.isEnabled = false
            binding.btnStart.alpha = 0.5F
            binding.leftBallRed.alpha = 0.5F
            binding.centerBallRed.alpha = 0.5F
            binding.rightBallRed.alpha = 0.5F

            if (pointsSumm % 5 == 0 && pointsSumm != 0) {

                ballsVisible(1)
                setRedBackgroundBall()
                controlRedBalls(1)
                binding.btnStart.isEnabled = false

                val timer = object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        ballsVisible(52)
                        win(3)

                        binding.leftBallRed.alpha = 1.0F
                        binding.centerBallRed.alpha = 1.0F
                        binding.rightBallRed.alpha = 1.0F

                        binding.leftBallRed.background = (getResources().getDrawable(R.drawable.ball))
                        binding.centerBallRed.background = (getResources().getDrawable(R.drawable.ball))
                        binding.rightBallRed.background = (getResources().getDrawable(R.drawable.ball))
                    }
                }
                timer.start()
            }
        }

        binding.leftBallRed.setOnClickListener {
            punch()
            if (rand(start, end) == 1 or 2) {
                binding.leftBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.leftBallRed.alpha = 1.0F
                win()
                binding.centerBall.visibility = View.INVISIBLE
            } else {
                binding.leftBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.leftBallRed.alpha = 1.0F
                binding.centerBall.visibility = View.INVISIBLE
                lose()
            }
        }

        binding.centerBallRed.setOnClickListener {
            punch()
            if (rand(start, end) == 1 or 2) {
                binding.centerBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.centerBallRed.alpha = 1.0F
                win()
                binding.leftBall.visibility = View.INVISIBLE
            } else {
                binding.centerBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.centerBallRed.alpha = 1.0F
                binding.centerBall.visibility = View.INVISIBLE
                lose()
            }
        }
        binding.rightBallRed.setOnClickListener {
            punch()
            if (rand(start, end) == 1 or 2) {
                binding.rightBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.rightBallRed.alpha = 1.0F
                win()
                binding.centerBall.visibility = View.INVISIBLE
            } else {
                binding.rightBallRed.background = (getResources().getDrawable(R.drawable.ball))
                binding.rightBallRed.alpha = 1.0F
                binding.centerBall.visibility = View.INVISIBLE
                lose()
            }
        }
    }
}