package com.example.booksapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.booksapp.databinding.RowPdfAdminBinding

class AdapterPdfAdmin {

    //viewBinding
    private lateinit var binding:RowPdfAdminBinding

    /* View holder class for row_pdf_admin.xml*/
    inner class HolderPdfAdmin(itemView: View) : ViewHolder(itemView){
        // UI views of row_pdf_admin.xml
        val pdfView = binding.pdfView
        val progressBar = binding.progressBar
        val titleTv = binding.titleTv
        val descriptionTv = binding.descriptionTv
        val categoryTv = binding.categoryTv
        val sizeTv = binding.sizeTv
        val dateTv = binding.dateTv
        val moreBtn = binding.moreBtn
    }
}