package com.example.quizzapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.quizzapp.Adapter.QuestionAdapter
import com.example.quizzapp.Domain.QuestionModel
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityQuestionBinding

class QuestionActivity : AppCompatActivity(), QuestionAdapter.score {
    //
    lateinit var binding :  ActivityQuestionBinding
    var position : Int = 0
    var recievedList : MutableList<QuestionModel> = mutableListOf()
    var allScore = 0
    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val window: Window = this@QuestionActivity.window
        window.statusBarColor = ContextCompat.getColor(this@QuestionActivity, R.color.grey)

        //
        recievedList = intent.getParcelableArrayListExtra<QuestionModel>("list")!!.toMutableList()

        //
        binding.apply {

            btnBack.setOnClickListener{
                finish()
            }
            progressBar.progress = 1

            txtQuestion.text = recievedList[position].question
            val drawableResourceId : Int = binding.root.resources.getIdentifier(
                recievedList[position].picPath,
                "drawable", binding.root.context.packageName
            )

            //
            Glide.with(this@QuestionActivity)
                .load(drawableResourceId)
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                .into(pic)

            //
            loadAnswer()

            //
            rightArrow.setOnClickListener{
                if(progressBar.progress == 10){
                    val intent = Intent(this@QuestionActivity, ScoreActivity ::class.java)
                    intent.putExtra("Score", allScore)
                    startActivity(intent)
                    finish()
                    return@setOnClickListener
                }

                //
                position++
                progressBar.progress = progressBar.progress + 1
                txtQuestionNumber.text = "Question " + progressBar.progress + "/10"
                txtQuestion.text = recievedList[position].question

                //
                val drawableResourceId : Int = binding.root.resources.getIdentifier(
                    recievedList[position].picPath,
                    "drawable",binding.root.context.packageName
                )

                //
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                //
                loadAnswer()
            }

            //
            leftArrow.setOnClickListener{
                if(progressBar.progress == 1){
                    return@setOnClickListener
                }

                //
                position--
                progressBar.progress = progressBar.progress - 1
                txtQuestionNumber.text = "Question " + progressBar.progress + "/10"
                txtQuestion.text = recievedList[position].question

                //
                val drawableResourceId1 : Int = binding.root.resources.getIdentifier(
                    recievedList[position].picPath,
                    "drawable",binding.root.context.packageName
                )

                //
                Glide.with(this@QuestionActivity)
                    .load(drawableResourceId1)
                    .centerCrop()
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(60)))
                    .into(pic)

                //
                loadAnswer()
            }

        }
    }

    //
    private fun loadAnswer(){
        val users : MutableList<String> = mutableListOf()
        users.add(recievedList[position].answer_1.toString())
        users.add(recievedList[position].answer_2.toString())
        users.add(recievedList[position].answer_3.toString())
        users.add(recievedList[position].answer_4.toString())

        //
        if(recievedList[position].clickedAnswer !=  null) users.add(recievedList[position].clickedAnswer.toString())

        //
        val questionAdapter by lazy {
            QuestionAdapter(
                recievedList[position].clickedAnswer.toString(), users, this
            )
        }

        //
        questionAdapter.differ.submitList(users)
        binding.questionList.apply {
            layoutManager = LinearLayoutManager(this@QuestionActivity)
            adapter = questionAdapter
        }
    }

    override fun amount(number: Int, clickedAnswer: String) {
        allScore += number
        recievedList[position].clickedAnswer = clickedAnswer
    }

}