package com.example.bookstore.ui.screens.search_screen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentSearchBinding
import com.example.bookstore.domain.model.Book
import com.google.android.material.search.SearchView
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val searchBooksViewModel: SearchBooksViewModel by viewModel()
    private lateinit var searchBooksAdapter: SearchBooksAdapter
    private val list: MutableList<Book> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onToCartClicked = { book: Book ->
            searchBooksViewModel.insertBookToCart(book)
        }

        val onCardClicked = { link: String ->
            val bundle = Bundle()
            bundle.putString("LINK", link)
            findNavController().navigate(R.id.BookPageFragment, bundle)
        }

        val onBuyClicked = {

        }




        with(binding.searchView) {
            show()
            addTransitionListener { _, _, newState ->
                if (newState == SearchView.TransitionState.HIDING) {
                    findNavController().popBackStack()
                }
            }
            editText.doAfterTextChanged {
                searchBooksViewModel.fetchData(text = text.toString())
            }

            searchBooksAdapter = SearchBooksAdapter(list, onToCartClicked, onCardClicked, onBuyClicked)

            searchBooksViewModel.books.observe(viewLifecycleOwner) { books ->
                list.clear()
                Log.d("123log", "request end")
                list.addAll(books)
                binding.searchRV.adapter?.notifyDataSetChanged()
            }
        }

        with(binding.searchRV) {
            adapter = searchBooksAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}