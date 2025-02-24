package com.example.youtubeplayerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeplayerapp.dataclasses.Items

class VideoAdapter(var video: List<Items>, private val onClick: (Items)->Unit): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
       holder.bind(video[position])
    }

    override fun getItemCount(): Int {
        return video.size
    }

    inner class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(video: Items) {
            val videoId = video.id?.videoId ?: ""
            itemView.findViewById<TextView>(R.id.videoName).text = video.snippet?.title ?: "No Title"
            val thumbnailUrl = video.snippet?.thumbnails?.high?.url  // ✅ Corrected URL access
            val imageView = itemView.findViewById<ImageView>(R.id.videoThumbnail)

            Glide.with(itemView.context)
                .load(thumbnailUrl)
                .into(imageView)
            itemView.setOnClickListener {
                onClick(video)
            }
        }
    }
}