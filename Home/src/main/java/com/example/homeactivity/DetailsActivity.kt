package com.example.homeactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.homeactivity.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel : HomeViewModel
    val adapter = CommentAdapter()
    val comments = ArrayList<Comment>()

    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)


        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.backbtn.setOnClickListener {
            this.onBackPressed()
        }
        window.statusBarColor = ContextCompat.getColor(this, R.color.drk_prpl)

        viewModel.readProduct.observe(this, Observer { prouct ->
                binding.itemname.setText(prouct.title)
                binding.prdctimg.load(prouct.image)
        })

        binding.button.setOnClickListener {
            Toast.makeText(this,"Don't worry, You will be able to rate soon (smiles)",Toast.LENGTH_SHORT).show()
        }

        comments.add(Comment("Kalistus Emmanuel","its 5 Star For Me"))
        comments.add(Comment("AdeKunle Bankole","Cash to Crop Is The Best Abeg"))
        comments.add(Comment("Cynthia Michelle","Not to Be Compared, with With 5 star for me"))
        comments.add(Comment("Prosper Daniel","Fast Dilivery Smooth User Experiance"))
        comments.add(Comment("Faith Anonymus","5 Star"))
        comments.add(Comment("Daniel Fashiola","Vote Labor Party"))

        adapter.submitList(comments)
        binding.rvComment.adapter = adapter
        setContentView(binding.root)


    }

}