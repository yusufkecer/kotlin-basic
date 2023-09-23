package com.example.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userName:String?;
        arguments.let {  userName = SecondFragmentArgs.fromBundle(it!!).userName }
        val textView1 = view.findViewById<android.widget.TextView>(R.id.textView1)
        textView1.text = userName;
        val action = SecondFragmentDirections.actionSecondFragmentToFirstFragment()
        val secondButtons = view.findViewById<Button>(R.id.backToFirst)
       secondButtons?.setOnClickListener {
           Navigation.findNavController(view).navigate(action)
      }
        super.onViewCreated(view, savedInstanceState)
    }
}