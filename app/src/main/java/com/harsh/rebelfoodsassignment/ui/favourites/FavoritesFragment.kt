package com.harsh.rebelfoodsassignment.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.harsh.rebelfoodsassignment.MyApplication
import com.harsh.rebelfoodsassignment.R
import com.harsh.rebelfoodsassignment.data.local.DatabaseService
import com.harsh.rebelfoodsassignment.data.local.entity.UsersEntity
import com.harsh.rebelfoodsassignment.databinding.FragmentUsersBinding
import com.harsh.rebelfoodsassignment.di.component.DaggerFragmentComponent
import com.harsh.rebelfoodsassignment.di.module.FragmentModule
import com.harsh.rebelfoodsassignment.ui.users.UsersAdapter
import com.harsh.rebelfoodsassignment.ui.users.UsersViewModel
import javax.inject.Inject

class FavoritesFragment : Fragment() {

    companion object {

        const val TAG = "FavoritesFragment"

        fun newInstance() : FavoritesFragment {
            val args = Bundle()
            val fragment = FavoritesFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var viewModel: UsersViewModel

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var usersAdapter: UsersAdapter

    @Inject
    lateinit var databaseService: DatabaseService

    lateinit var binding: FragmentUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun injectDependencies() {
        DaggerFragmentComponent.builder()
            .applicationComponent((context?.applicationContext as MyApplication).applicationComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }


    private fun setupObservers() {
        databaseService.usersDao().getFavList().observe(this,
            Observer<List<UsersEntity>> {
                viewModel.setData(it)
                usersAdapter.appendData(it)
            })
    }


    private fun setupView() {
        binding.viewModel = viewModel
        binding.usersRV.layoutManager = linearLayoutManager
        binding.usersRV.adapter = usersAdapter
    }
}
