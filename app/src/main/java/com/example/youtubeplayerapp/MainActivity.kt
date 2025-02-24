package com.example.youtubeplayerapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeplayerapp.dataclasses.High
import com.example.youtubeplayerapp.dataclasses.Id
import com.example.youtubeplayerapp.dataclasses.Items
import com.example.youtubeplayerapp.dataclasses.Snippet
import com.example.youtubeplayerapp.dataclasses.Thumbnails

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: YoutubeViewModel
    private lateinit var adapter: VideoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(YoutubeViewModel::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
//        val searchView: SearchView = findViewById(R.id.searchView)


        recyclerView.layoutManager = LinearLayoutManager(this)
        val videoList = listOf(
            Items(
                id = Id(videoId = "Og287zzOTEM"),
                snippet = Snippet(
                    title = "Video 1",
                    thumbnails = Thumbnails(high = High("https://img.youtube.com/vi/Og287zzOTEM/0.jpg"))
                )
            )
        )
        adapter = VideoAdapter(videoList) { video ->
            //handle item click to play video
            val videoId = video.id?.videoId ?: ""
            if (videoId.isNotEmpty()) {
                openWebView(videoId) // ✅ Correct video ID passing
            } else {
                Log.e("VIDEO_ERROR", "No Video ID found!")
            }
        }
        recyclerView.adapter = adapter

        viewModel.videosLiveData.observe(this, Observer { videos ->
            if (videos.isEmpty()) {
                println("🚨 API returned no videos!")
            } else {
                println("✅ API returned ${videos.size} videos!")
            }
            adapter.video = videos
            adapter.notifyDataSetChanged()
        })
//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                query?.let {
//                    viewModel.searchVideos(it)  // Call API with user input
//                }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                newText?.let {
//                    if (it.length > 2) { // Avoid excessive API calls for short queries
//                        viewModel.searchVideos(it)
//                    }
//                }
//                return true
//            }
//        })
//        viewModel.searchVideos("extra")
    }
    private fun openWebView(videoId: String) {
        Log.d("CLICK_EVENT", "Opening video: $videoId")
        val intent = Intent(this, VideoPlayerActivity::class.java)
        intent.putExtra("VIDEO_ID", videoId)
        startActivity(intent)
    }
}