package com.example.bookstore.ui.screens.book_screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentBookPageBinding
import com.example.bookstore.domain.model.Book
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookPageFragment : Fragment() {

    private var _binding: FragmentBookPageBinding? = null
    private val binding get() = _binding!!

    private lateinit var link: String
    private lateinit var book: Book

    private val bookPageViewModel: BookPageViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        link = arguments?.getString("LINK").toString()
        bookPageViewModel.fetchData(link)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bookPageViewModel.book.observe(viewLifecycleOwner) { it ->
            book = it
            binding.titleTV.text = book.title
            Glide.with(view.context).load(book.imageLink).placeholder(R.drawable.book_100).into(binding.bookIV)
            binding.genresTV.text = book.categories.joinToString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}