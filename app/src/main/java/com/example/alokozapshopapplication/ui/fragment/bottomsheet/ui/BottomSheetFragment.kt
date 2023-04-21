package com.example.alokozapshopapplication.ui.fragment.bottomsheet.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.alokozapshopapplication.R
import com.example.alokozapshopapplication.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding:FragmentBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_bottom_sheet, container, false)


        binding.tvHighToLow.setOnClickListener {
        }

        binding.tvLowToHigh.setOnClickListener {
        }

        binding.tvPosition.setOnClickListener {
        }

        binding.tvPosition.setOnClickListener {
        }

        binding.ivCancel.setOnClickListener {
            dismiss()
        }

        return  binding.root
    }




}