package com.kotdev.postcomments.app.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kotdev.postcomments.app.models.Comments
import com.kotdev.postcomments.databinding.BottomSheetBinding
import com.kotdev.postcomments.helpers.Click

class BottomFragment: BottomSheetDialogFragment(){

    companion object {
        const val TAG = "BottomFragment"
    }
    private var click: Click? = null

    fun setClick(click: Click) {
        this.click = click
    }

    private lateinit var binding: BottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            val comment = Comments()
            comment.email = binding.mail.text.toString()
            comment.name = binding.name.text.toString()
            comment.body = binding.body.text.toString()
            Log.d(TAG, "onViewCreated: $comment")
            click?.add(comment)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
    }
}