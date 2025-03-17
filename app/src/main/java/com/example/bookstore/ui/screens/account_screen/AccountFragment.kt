package com.example.bookstore.ui.screens.account_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bookstore.R
import com.example.bookstore.databinding.FragmentAccountBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    private val accountViewModel: AccountViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartLayout.paramNameTV.text = "Корзина"
        binding.cartLayout.paramValueTV.text = "0"
        binding.cartLayout.root.setOnClickListener {
            findNavController().navigate(R.id.action_AccountFragment_to_cartListFragment)
        }

        accountViewModel.numberCart.observe(viewLifecycleOwner) { number ->
            binding.cartLayout.paramValueTV.text = number.toString()
        }

        binding.ordersLayout.paramNameTV.text = "Заказы"
        binding.ordersLayout.paramValueTV.text = "0"

        binding.amountOfBooksLayout.paramNameTV.text = "Книг куплено"
        binding.amountOfBooksLayout.paramValueTV.text = "0"

        binding.readBooksLayout.paramNameTV.text = "Книг прочитано"
        binding.readBooksLayout.paramValueTV.text = "0"

        binding.readingBooksLayout.paramNameTV.text = "Книг в чтении"
        binding.readingBooksLayout.paramValueTV.text = "0"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}