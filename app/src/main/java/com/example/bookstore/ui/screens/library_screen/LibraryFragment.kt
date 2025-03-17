package com.example.bookstore.ui.screens.library_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.databinding.FragmentLibraryBinding
import com.example.bookstore.domain.model.Book
import com.example.bookstore.ui.screens.library_screen.adapter.LibraryAdapter


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolBar.title = "Library"
        val list =
            listOf(Book("title"), Book("title1"), Book("title2"), Book("title3"), Book("title4"))


        with(binding.recentRV) {
            adapter = LibraryAdapter(list)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }


        with(binding.newRV) {
            adapter = LibraryAdapter(list)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }


        with(binding.readingRV) {
            adapter = LibraryAdapter(list)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}