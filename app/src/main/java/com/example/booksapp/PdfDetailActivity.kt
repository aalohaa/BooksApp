package com.example.booksapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.booksapp.MyApplication.Companion.incrementBookViewCount
import com.example.booksapp.databinding.ActivityPdfDetailBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PdfDetailActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding:ActivityPdfDetailBinding

    //book id
    private var bookId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPdfDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get book id from intent
        bookId = intent.getStringExtra("bookId")!!


        MyApplication.incrementBookViewCount(bookId)

        loadBookDetails()

        //handle backbtn click, go back
        binding.backBtn.setOnClickListener {
            onBackPressed()
        }
    }

    private fun loadBookDetails() {
        //books > bookid > details
        val ref = FirebaseDatabase.getInstance().getReference("Books")
        ref.child(bookId)
            .addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    //get data
                    val categoryId = "${snapshot.child("categoryId").value}"
                    val description = "${snapshot.child("description").value}"
                    val downloadsCount = "${snapshot.child("downloadsCount").value}"
                    val timestamp = "${snapshot.child("timestamp").value}"
                    val title = "${snapshot.child("title").value}"
                    val uid = "${snapshot.child("uid").value}"
                    val url = "${snapshot.child("url").value}"
                    val viewsCount = "${snapshot.child("viewsCount").value}"

                    //format date
                    val date = MyApplication.formatTimeStamp(timestamp.toLong())

                    //load pdf category
                    MyApplication.loadCategory(categoryId, binding.categoryTv)
                    //load pdf thumbnail, pages count
                    MyApplication.loadPdfFromUrlSinglePage("$url", "$title", binding.pdfView, binding.progressBar, binding.pagesTv)
                    //load pdf size
                    MyApplication.loadPdfSize("$url", "$title", binding.sizeTv)

                    //set data
                    binding.titleTv.text = title
                    binding.descriptionTv.text = description
                    binding.viewsTv.text = viewsCount
                    binding.downloadsTv.text = downloadsCount
                    binding.dateTv.text = date
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
    }

}