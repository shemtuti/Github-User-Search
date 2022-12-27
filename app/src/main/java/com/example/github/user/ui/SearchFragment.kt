package com.example.github.user.ui

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.github.user.R
import com.example.github.user.databinding.FragmentSearchBinding
import com.example.github.user.domain.User
import com.example.github.user.ui.adapter.FollowerAdapter
import com.example.github.user.ui.adapter.FollowingAdapter
import com.example.github.user.viewmodels.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var progressDialog: ProgressDialog
    private var githubUsername = ""
    private lateinit var user: User
    private lateinit var recyclerViewFollower: RecyclerView
    private lateinit var recyclerViewFollowing: RecyclerView
    private lateinit var followerAdapter: FollowerAdapter
    private lateinit var followingAdapter: FollowingAdapter

    // delay creation of the viewModel until an appropriate lifecycle method
    private val viewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, SearchViewModel.Factory(activity.application))[SearchViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeDataChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // get a reference to the binding object and inflate the fragment views
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_search, container, false)

        // set LifecycleOwner so that DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        // Set the ViewModel for data binding
        binding.searchViewModel = viewModel

        progressDialog = ProgressDialog(requireContext())

        // initialize recycler variable
        recyclerViewFollower = binding.root.findViewById<RecyclerView>(R.id.recycler_view_followers)
        recyclerViewFollowing = binding.root.findViewById<RecyclerView>(R.id.recycler_view_following)

        // set layout manager to our recycler view.
        recyclerViewFollower.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFollowing.layoutManager = LinearLayoutManager(requireContext())

        // initialize the adapter class
        followerAdapter = FollowerAdapter(requireContext())
        followingAdapter = FollowingAdapter(requireContext())

        // set adapter to our recycler view.
        recyclerViewFollower.adapter = followerAdapter
        recyclerViewFollowing.adapter = followingAdapter

        // search image onClick
        binding.imgSearchUser.setOnClickListener {
            githubUsername = binding.edSearchUser.text.toString().trim()

            if(githubUsername.isEmpty()){
                val snackBar = Snackbar.make(requireView(), getString(R.string.enter_github_username) , Snackbar.LENGTH_LONG).setAction("Okay", null)
                snackBar.setActionTextColor(Color.WHITE)
                val snackBarView = snackBar.view
                snackBarView.setBackgroundColor(Color.RED)

                val view: View = snackBar.view
                val params = view.layoutParams as FrameLayout.LayoutParams
                params.gravity = Gravity.TOP
                view.layoutParams = params

                val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                textView.setTextColor(Color.WHITE)
                textView.textSize = 18F
                snackBar.show()
            } else {
                progressDialog.setMessage("Fetching...Please Wait!")
                progressDialog.show()

                fetchUserProfile()
                it.hideKeyboard()
            }
        }

        binding.edSearchUser.setOnKeyListener(View.OnKeyListener{_, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                githubUsername = binding.edSearchUser.text.toString().trim()
                if(githubUsername.isEmpty()){
                    val snackBar = Snackbar.make(requireView(), getString(R.string.enter_github_username) , Snackbar.LENGTH_LONG).setAction("Okay", null)
                    snackBar.setActionTextColor(Color.WHITE)
                    val snackBarView = snackBar.view
                    snackBarView.setBackgroundColor(Color.RED)

                    val view: View = snackBar.view
                    val params = view.layoutParams as FrameLayout.LayoutParams
                    params.gravity = Gravity.TOP
                    view.layoutParams = params

                    val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
                    textView.setTextColor(Color.WHITE)
                    textView.textSize = 18F
                    snackBar.show()
                } else {
                    progressDialog.setMessage("Fetching...Please Wait!")
                    progressDialog.show()

                    fetchUserProfile()
                }
                return@OnKeyListener true
            }
            false
        })

        return binding.root
    }

    private fun fetchUserProfile() {
        viewModel.fetchUserDetails(githubUsername)

        observeFollowers()
        observeFollowing()
    }

    private fun observeDataChanged() {
        // observe user details change
        viewModel.userInfo.observe(viewLifecycleOwner, Observer { userInfo ->
            userInfo.apply {
                progressDialog.dismiss()
                if(userInfo.isNotEmpty()){
                    binding.edSearchUser.text.clear()
                    binding.divider2.visibility = View.VISIBLE
                    user = userInfo[0]

                    binding.rlUserMain.visibility = View.VISIBLE
                    binding.lUserError.visibility = View.GONE

                    binding.userFullName.text = user.name
                    binding.userName.text = getString(R.string.username) +user.login
                    
                    if(user.bio.isNullOrEmpty()){
                        binding.userDesc.text = ""
                    }
                    else{
                        binding.userDesc.text = getString(R.string.bio) +user.bio
                    }
                    binding.userFollowers.text =
                        user.followers.toString() + getString(R.string.followers)
                    binding.userFollowing.text =
                        user.following.toString() + getString(R.string.following)
                    binding.userRepositories.text =
                        user.public_repos.toString() + getString(R.string.repos)

                    val imgUri = user.avatar_url.toUri().buildUpon().build()
                    Glide.with(binding.imgUserPic.context)
                        .load(imgUri)
                        .apply(
                            RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image)
                                .circleCrop()
                        )
                        .into(binding.imgUserPic)

                    observeFollowers()
                    observeFollowing()

                }
                if(userInfo.isEmpty()){
                    Timber.i("DATA: HERE AND EMPTY")
                    binding.lFollowMain.visibility = View.GONE
                }
            }
        })
    }

    private fun observeFollowers() {
        // observe followers details change
        viewModel.userFollowers.observe(viewLifecycleOwner, Observer { followersInfo ->
            followersInfo.apply {
                progressDialog.dismiss()

                if(followersInfo.isNotEmpty()){
                    binding.lFollowMain.visibility = View.VISIBLE
                    binding.lFollowView.visibility = View.VISIBLE
                    binding.lFollowersNoData.visibility = View.INVISIBLE

                    // update followers list
                    followerAdapter.updateFollowersList(followersInfo)
                }
                else {
                    binding.lFollowView.visibility = View.VISIBLE
                    binding.lFollowersNoData.visibility = View.VISIBLE

                    // update followers list
                    followerAdapter.updateFollowersList(followersInfo)
                }
            }
        })
    }

    private fun observeFollowing() {
        // observe following details change
        viewModel.userFollowing.observe(viewLifecycleOwner, Observer { followingInfo ->
            followingInfo.apply {
                progressDialog.dismiss()

                if(followingInfo.isNotEmpty()){
                    binding.lFollowMain.visibility = View.VISIBLE
                    binding.lFollowView.visibility = View.VISIBLE
                    binding.lFollowingNoData.visibility = View.INVISIBLE

                    // update following list
                    followingAdapter.updateFollowingList(followingInfo)
                }
                else{
                    binding.lFollowView.visibility = View.VISIBLE
                    binding.lFollowingNoData.visibility = View.VISIBLE

                    // update following list
                    followingAdapter.updateFollowingList(followingInfo)
                }
            }
        })
    }

    private fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}