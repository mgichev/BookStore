package com.example.bookstore.ui.screens.shop_screen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentShopBinding
import com.example.bookstore.domain.model.Book
import com.example.bookstore.ui.screens.shop_screen.adapters.ShopBooksAdapter
import com.example.bookstore.ui.screens.shop_screen.viewmodel.ShopBooksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!
    private lateinit var booksAdapter: ShopBooksAdapter
    private val booksViewModel: ShopBooksViewModel by viewModel()
    private val list: MutableList<Book> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val simpleTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val it = viewHolder.adapterPosition
                list.removeAt(it)
                booksViewModel.removeItem(it)
                booksAdapter.notifyItemRemoved(it)
            }
        }

        initToolBar()
        initAdapter(view)
        initRV()

        val itemTouchHelper = ItemTouchHelper(simpleTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.booksRV)

        initVMObservers()
    }

    private fun initVMObservers() {
        booksViewModel.toggleIsEmptyResult.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Test", Toast.LENGTH_LONG).show()
        }

        booksViewModel.books.observe(
            requireParentFragment().viewLifecycleOwner
        ) { books ->
            list.clear()
            list.addAll(books)
            booksAdapter.notifyDataSetChanged()
        }
    }

    private fun initRV() {
        with(binding.booksRV) {
            adapter = booksAdapter
            layoutManager = GridLayoutManager(activity, 2).apply {
                spanSizeLookup = object : SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if ((adapter as ShopBooksAdapter).getItemViewType(position) == ShopBooksAdapter.BOOK_VH) 1
                        else 2
                    }
                }
            }
            setHasFixedSize(true)
        }
    }

    private fun initAdapter(view: View) {
        val onMoreBtnClicked: () -> Unit = {
            booksViewModel.fetchData()
        }

        val onBuyBtnClicked: (book: Book) -> Unit = {
            Toast.makeText(view.context, "Buy", Toast.LENGTH_LONG).show()
        }

        val onToCartBtnClicked: (book: Book) -> Unit = { book ->
            booksViewModel.addToCart(book)
        }

        val onCardClicked: (link: String) -> Unit = { link ->
            val bundle = Bundle()
            bundle.putString("LINK", link)
            findNavController().navigate(R.id.BookPageFragment, bundle)
        }

        booksAdapter = ShopBooksAdapter(
            booksList = list,
            onCardClicked = onCardClicked,
            onMoreBtnClicked = onMoreBtnClicked,
            onBuyBtnClicked = onBuyBtnClicked,
            onToCartBtnClicked = onToCartBtnClicked
        )
    }

    private fun initToolBar() {
        binding.toolBar.title = "Shop"
        binding.toolBar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.search -> {
                    findNavController().navigate(R.id.action_ShopFragment_to_searchFragment)
                    true
                }

                R.id.cart -> {
                    findNavController().navigate(R.id.cartListFragment)
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}