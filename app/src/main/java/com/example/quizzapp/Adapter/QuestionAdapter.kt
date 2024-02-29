package com.example.quizzapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.databinding.ActivityLeaderBinding

class QuestionAdapter(
    val correctAnswer : String,
    val users :  MutableList<String> = mutableListOf(),
    val returnScore : score
) : RecyclerView.Adapter<QuestionAdapter.Viewholder>(){

    interface score {
        fun amount(number : Int, clickedAnswer : String)
    }

    class Viewholder {

    }


    //
    private lateinit var binding: ViewholderQuestionBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.Viewholder {

        val inflater = LayoutInflater.from(parent.context)
        binding = ViewholderQuestionBinding.in
    }

    override fun onBindViewHolder(holder: QuestionAdapter.Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}