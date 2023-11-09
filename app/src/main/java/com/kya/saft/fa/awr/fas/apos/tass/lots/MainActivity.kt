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

        fun View.visible(isVisible: Boolean) {
            if (isVisible) {
                this.visibility = View.VISIBLE
            } else {
                this.visibility = View.INVISIBLE
            }
        }

        fun ballsVisible(request: Int) {
            if (request == 1) {
                binding.rightBall.visible(true)
                binding.leftBall.visible(true)
                binding.centerBall.visible(true)
            } else {
                binding.rightBall.visible(false)
                binding.leftBall.visible(false)
                binding.centerBall.visible(false)
            }
        }


        fun setBackgroundBall(background: String, position: String) {
            val redBallRes = resources.getDrawable(R.drawable.cball)
            if (background == "red") {
                when (position) {
                    "left" -> binding.leftBallRed.background = redBallRes
                    "center" -> binding.centerBallRed.background = redBallRes
                    "right" -> binding.rightBallRed.background = redBallRes
                    "all" -> {
                        binding.rightBallRed.background = redBallRes
                        binding.centerBallRed.background = redBallRes
                        binding.leftBallRed.background = redBallRes
                    }
                }
            } else if (background == "ball") {
                val ballRes = resources.getDrawable(R.drawable.ball)
                when (position) {
                    "left" -> binding.leftBallRed.background = ballRes
                    "center" -> binding.centerBallRed.background = ballRes
                    "right" -> binding.rightBallRed.background = ballRes
                    "all" -> {
                        binding.rightBallRed.background = ballRes
                        binding.centerBallRed.background = ballRes
                        binding.leftBallRed.background = ballRes
                    }
                }
            }

        }

        fun controlRedBalls(request: Int) {
            if (request == 1) {
                binding.leftBallRed.isEnabled = false
                binding.centerBallRed.isEnabled = false
                binding.rightBallRed.isEnabled = false
            } else {
                binding.leftBallRed.isEnabled = true
                binding.centerBallRed.isEnabled = true
                binding.rightBallRed.isEnabled = true
            }
        }

        fun win(count: Int = 1) {
            pointsSumm += count
            binding.textPoints.text = "MY POINTS IS $pointsSumm"
            binding.textMissGoal.text = "Hit"
            binding.textMissGoal.setTextColor(resources.getColor(R.color.goal))
        }

        fun lose() {
            binding.textMissGoal.text = "Miss"
            binding.textMissGoal.setTextColor(resources.getColor(R.color.miss))
        }

        fun punch() {
            binding.btnStart.alpha = 1.0F
            binding.btnStart.isEnabled = true
            controlRedBalls(1)
        }

        binding.btnStart.setOnClickListener {
            binding.leftBallRed.visibility = View.VISIBLE
            binding.centerBallRed.visibility = View.VISIBLE
            binding.rightBallRed.visibility = View.VISIBLE
            setBackgroundBall("red", "all")
            controlRedBalls(52)

            binding.btnStart.isEnabled = false
            binding.btnStart.alpha = 0.5F
            binding.leftBallRed.alpha = 0.5F
            binding.centerBallRed.alpha = 0.5F
            binding.rightBallRed.alpha = 0.5F

            if (pointsSumm % 5 == 0 && pointsSumm != 0) {

                ballsVisible(1)
                setBackgroundBall("red", "all")
                controlRedBalls(1)
                binding.btnStart.isEnabled = false

                val timer = object : CountDownTimer(3000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}
                    override fun onFinish() {
                        ballsVisible(0)
                        win(3)

                        binding.leftBallRed.alpha = 1.0F
                        binding.centerBallRed.alpha = 1.0F
                        binding.rightBallRed.alpha = 1.0F
                        binding.btnStart.alpha = 1.0F
                        binding.btnStart.isEnabled = true
                        setBackgroundBall("ball", "all")
                    }
                }
                timer.start()
            }
        }

        binding.leftBallRed.setOnClickListener {
            punch()
            setBackgroundBall("ball", "left")
            binding.leftBallRed.alpha = 1.0F
            binding.centerBall.visible(false)
            if (rand(start, end) == 1 or 2) {
                win()
            } else {
                lose()
            }
        }

        binding.centerBallRed.setOnClickListener {
            punch()
            setBackgroundBall("ball", "center")
            binding.centerBallRed.alpha = 1.0F
            binding.centerBall.visible(false)
            if (rand(start, end) == 1 or 2) {
                win()
            } else {
                lose()
            }
        }
        binding.rightBallRed.setOnClickListener {
            punch()
            setBackgroundBall("ball", "right")
            binding.rightBallRed.alpha = 1.0F
            binding.centerBall.visible(false)
            if (rand(start, end) == 1 or 2) {
                win()
            } else {
                lose()
            }
        }
    }
}