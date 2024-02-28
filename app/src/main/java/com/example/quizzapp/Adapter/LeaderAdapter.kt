package com.example.quizzapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quizzapp.Domain.UserModel
import com.example.quizzapp.databinding.ViewHolderLeadersBinding

class LeaderAdapter: RecyclerView.Adapter<LeaderAdapter.ViewHolder>() {

    private lateinit var binding: ViewHolderLeadersBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderAdapter.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        binding = ViewHolderLeadersBinding.inflate(inflater,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: LeaderAdapter.ViewHolder, position: Int) {
        val binding = ViewHolderLeadersBinding.bind(holder.itemView)
        binding.txtTitleViewHolder.text = differ.currentList[position].name

        val drawableResourceId : Int = binding.root.resources.getIdentifier(
            differ.currentList[position].pic,
            "drawable",binding.root.context.packageName
        )

        Glide.with(binding.root.context)
            .load(drawableResourceId)
            .into(binding.pic)
        binding.txtOrdinal.text = (position + 4).toString()
        binding.txtScore.text = differ.currentList[position].score.toString()
    }


    //
    override fun getItemCount()  = differ.currentList.size

    //
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root)

    //
    private val differCallback = object : DiffUtil.ItemCallback<UserModel>(){
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
            return oldItem == newItem
        }
    }

    //
    val differ = AsyncListDiffer(this,differCallback)


}