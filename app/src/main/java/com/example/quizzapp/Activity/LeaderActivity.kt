package com.example.quizzapp.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.quizzapp.Adapter.LeaderAdapter
import com.example.quizzapp.Domain.UserModel
import com.example.quizzapp.R
import com.example.quizzapp.databinding.ActivityLeaderBinding

class LeaderActivity : AppCompatActivity() {

    lateinit var binding : ActivityLeaderBinding
    private val leaderAdapter by lazy { LeaderAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //
        val window: Window = this@LeaderActivity.window
        window.statusBarColor = ContextCompat.getColor(this@LeaderActivity, R.color.grey)

        //
        binding.apply {
            txtScoreTopOne.text = loadData().get(0).score.toString()
            txtScoreTopTwo.text = loadData().get(1).score.toString()
            txtScoreTopThree.text = loadData().get(2).score.toString()
            txtTopOneTitle.text = loadData().get(0).name
            txtTopTwoTitle.text = loadData().get(2).name
            txtTitleTopThree.text = loadData().get(3).name

            val drawableResourceId1 : Int = binding.root.resources.getIdentifier(
                loadData().get(0).pic,"drawable",binding.root.context.packageName
            )

            //
            Glide.with(root.context)
                .load(drawableResourceId1)
                .into(imgTopOne)

            //
            val drawableResourceId2 : Int = binding.root.resources.getIdentifier(
                loadData().get(1).pic,"drawable",binding.root.context.packageName
            )

            //
            Glide.with(root.context)
                .load(drawableResourceId2)
                .into(imgTopTwo)

            //
            val drawableResourceId3 : Int = binding.root.resources.getIdentifier(
                loadData().get(2).pic,"drawable",binding.root.context.packageName
            )

            //
            Glide.with(root.context)
                .load(drawableResourceId3)
                .into(imgTopThree)

            menu.setItemSelected(R.id.board)
            menu.setOnItemSelectedListener {
                if(it == R.id.home){
                    startActivity(Intent(this@LeaderActivity, MainActivity::class.java))
                }
            }

            //
            var list : MutableList<UserModel> = loadData()
            list.removeAt(0)
            list.removeAt(1)
            list.removeAt(2)
            leaderAdapter.differ.submitList(list)

            //
            leaderView.apply {
                layoutManager = LinearLayoutManager(this@LeaderActivity)
                adapter = leaderAdapter
            }
        }
    }

    // you can get below list from your API service,  this is a example list
    private fun loadData() :  MutableList<UserModel>{
        val user : MutableList<UserModel> = mutableListOf()

        user.add(UserModel(1,"Sophia","person1",4850))
        user.add(UserModel(2,"Daniel","person2",2034))
        user.add(UserModel(3,"James","person3",1890))
        user.add(UserModel(4,"John","person4",5037))
        user.add(UserModel(5,"David","person5",2873))
        user.add(UserModel(6,"Emily","person6",1536))
        user.add(UserModel(7,"Michael","person7",6739))
        user.add(UserModel(8,"Sarah","person8",4978))
        user.add(UserModel(9,"Wilson","person9",3408))
        return user
    }
}