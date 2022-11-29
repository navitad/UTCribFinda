package com.orange.utcribfinda.ui//package com.orange.utcribfinda.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.orange.utcribfinda.MainActivity
import com.orange.utcribfinda.R
import com.orange.utcribfinda.ui.saved.SavedViewModel
import com.orange.utcribfinda.api.ListingPost
import com.orange.utcribfinda.databinding.ListingPostBinding


class PostRowAdapter(private val viewModel: SavedViewModel)
    : ListAdapter<ListingPost, PostRowAdapter.VH>(ListingDiff()) {

    class ListingDiff : DiffUtil.ItemCallback<ListingPost>() {
        override fun areItemsTheSame(oldItem: ListingPost, newItem: ListingPost): Boolean {
            return false
//            return oldItem.key == newItem.key
        }
        override fun areContentsTheSame(oldItem: ListingPost, newItem: ListingPost): Boolean {
            return false
//            return ListingPost.spannableStringsEqual(oldItem.title, newItem.title) &&
//                    ListingPost.spannableStringsEqual(oldItem.selfText, newItem.selfText) &&
//                    ListingPost.spannableStringsEqual(oldItem.publicDescription, newItem.publicDescription) &&
//                    ListingPost.spannableStringsEqual(oldItem.displayName, newItem.displayName)

        }
    }

    inner class VH(val rowPostBinding: ListingPostBinding)
        : RecyclerView.ViewHolder(rowPostBinding.root) {

        init {
            rowPostBinding.rowFav.setOnClickListener {
                val item = getItem(adapterPosition)
                if(viewModel.observeFavoritesList().contains(item)) {
                    viewModel.removeFavorite(item)
                    rowPostBinding.rowFav.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                } else {
                    viewModel.setFavoritesList(item)
                    rowPostBinding.rowFav.setImageResource(R.drawable.ic_favorite_black_24dp)
                }
            }

//            rowPostBinding.root.setOnClickListener {
//                SavedViewModel.doOnePost(rowPostBinding.root.context, getItem(adapterPosition))
//            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding = ListingPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        val rowBinding = holder.rowPostBinding

        rowBinding.title.text = "Villas On Rio"
        rowBinding.selfText.text = item.price
//        rowBinding.title.text = item.title
//        rowBinding.selfText.text = item.selfText
//        rowBinding.comments.text = item.commentCount.toString()
//        rowBinding.score.text = item.score.toString()
//        var thumbnailUrl = item.thumbnailURL
//        var imageUrl = item.imageURL
//        if(thumbnailUrl != null && imageUrl != null) {
//            Glide.glideFetch(imageUrl, thumbnailUrl, rowBinding.image)
//        }
        // if heart was clicked change image
        if(viewModel.observeFavoritesList().contains(item)) {
            rowBinding.rowFav.setImageResource(R.drawable.ic_favorite_black_24dp)
        } else {
            rowBinding.rowFav.setImageResource(R.drawable.ic_favorite_border_black_24dp)
        }

//        override fun submitList(list: MutableList<ListingPost>?) {
//            super.submitList(list)
//        }


    }
}