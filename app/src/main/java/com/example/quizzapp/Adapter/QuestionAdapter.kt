package com.example.quizzapp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quizzapp.Domain.QuestionModel
import com.example.quizzapp.Domain.UserModel
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ViewHolderQuestionBinding

class QuestionAdapter(
    val correctAnswer : String,
    val users :  MutableList<String> = mutableListOf(),
    val returnScore : score
) : RecyclerView.Adapter<QuestionAdapter.Viewholder>(){

    interface score {
        fun amount(number : Int, clickedAnswer : String)
    }

    //
    private lateinit var binding: ViewHolderQuestionBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAdapter.Viewholder {

        val inflater = LayoutInflater.from(parent.context)
        binding = ViewHolderQuestionBinding.inflate(inflater,parent,false)
        return Viewholder()
    }

    //
    override fun onBindViewHolder(holder: QuestionAdapter.Viewholder, position: Int) {
        val binding = ViewHolderQuestionBinding.bind(holder.itemView)
        binding.txtAnswer.text = differ.currentList[position]
        var currectPos = 0
        when (correctAnswer){
            "a" -> {
                currectPos = 0
            }
            "b" -> {
                currectPos = 1
            }
            "c" -> {
                currectPos = 2
            }
            "d" -> {
                currectPos = 3
            }
        }
        //
        if(differ.currentList.size == 5 && currectPos == position){
            binding.txtAnswer.setBackgroundResource(R.drawable.green_background)
            binding.txtAnswer.setTextColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.white
                )
            )

            //
            val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
            binding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, drawable,null)
        }

        //
        if (differ.currentList.size == 5){
            var clickedPos = 0
            when (differ.currentList[4]){
                "a" -> {
                    clickedPos = 0
                }
                "b" -> {
                    clickedPos = 1
                }
                "c" -> {
                    clickedPos = 2
                }
                "d" -> {
                    clickedPos = 3
                }
            }

            //
            if(clickedPos == position && clickedPos != currectPos){
                binding.txtAnswer.setBackgroundResource(R.drawable.red_background)
                binding.txtAnswer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )

                //
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, drawable,null)
            }
        }

        //
        if (position == 4){
            binding.root.visibility = View.GONE
        }

        //
        holder.itemView.setOnClickListener{
            var str = ""
            when(position){
                0 -> {
                    str = "a"
                }
                1 -> {
                    str = "b"
                }
                2 -> {
                    str = "c"
                }
                3 -> {
                    str = "d"
                }
            }

            users.add(4, str)
            notifyDataSetChanged()
            if(currectPos ==  position){
                binding.txtAnswer.setBackgroundResource(R.drawable.green_background)
                binding.txtAnswer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )

                //
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.tick)
                binding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, drawable,null)
                returnScore.amount(5,str)
            } else{
                binding.txtAnswer.setBackgroundResource(R.drawable.red_background)
                binding.txtAnswer.setTextColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.white
                    )
                )

                //
                val drawable = ContextCompat.getDrawable(binding.root.context, R.drawable.thieves)
                binding.txtAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, drawable,null)
                returnScore.amount(0,str)
            }
        }

        //
        if(differ.currentList.size == 5) holder.itemView.setOnClickListener(null)
    }

    //
    override fun getItemCount() = differ.currentList.size

    //
    inner class Viewholder : RecyclerView.ViewHolder(binding.root)

    //
    private val differCallBack = object :DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return  oldItem == newItem
        }
    }

    //
    val differ = AsyncListDiffer(this,differCallBack)
}