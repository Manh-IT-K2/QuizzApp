package com.example.quizzapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    var score : Int = 0
    lateinit var binding : ActivityScoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)

        setContentView(binding.root)

        score = intent.getIntExtra("Score", 0)

        //
        binding.apply {
            txtScoreFinish.text = score.toString()
            btnBackFinish.setOnClickListener{
                startActivity(Intent(this@ScoreActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}