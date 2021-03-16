package com.kotdev.postcomments.app.ui.adapters

import android.view.LayoutInflater

import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.kotdev.postcomments.R


class HeaderAdapter : RecyclerView.Adapter<Header>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Header {
        return Header(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_header, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Header, position: Int) {

    }

    override fun getItemCount(): Int {
        return 1
    }
}