package com.example.bookstore.ui.screens.cart_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstore.databinding.FragmentCartListBinding
import com.example.bookstore.domain.model.Book
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartListFragment : Fragment() {

    private var _binding: FragmentCartListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: CartListAdapter
    private var cartList: MutableList<Book> = mutableListOf()
    private val cartListViewModel: CartListViewModel by viewModel()

    private val FRAGMENT_TITLE = "Cart"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartListBinding.inflate(inflater, container, false)
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
                cartList.removeAt(it)
                cartListViewModel.removeItem(it)
                adapter.notifyItemRemoved(it)
            }

        }

        val itemTouchHelper = ItemTouchHelper(simpleTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.rv)


        binding.toolBar.title = FRAGMENT_TITLE
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        adapter = CartListAdapter(cartList)
        with(binding.rv) {
            adapter = this@CartListFragment.adapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }

        cartListViewModel.cartList.observe(viewLifecycleOwner) { list ->
            val listSize = cartList.size
            cartList.clear()
            cartList.addAll(list)
            adapter.notifyItemRangeInserted(listSize, list.size)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}