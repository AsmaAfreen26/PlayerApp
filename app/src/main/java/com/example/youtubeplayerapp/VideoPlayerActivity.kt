package com.example.youtubeplayerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.maxrave.kotlinyoutubeextractor.State
import com.maxrave.kotlinyoutubeextractor.VideoMeta
import com.maxrave.kotlinyoutubeextractor.YTExtractor
import com.maxrave.kotlinyoutubeextractor.YtFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var playerView: PlayerView
    private var player: ExoPlayer? = null
    private val TAG = "VideoPlayerActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        playerView = findViewById(R.id.youtube_player_view)
        val button: Button = findViewById(R.id.backButton)
        button.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val videoId = intent.getStringExtra("VIDEO_ID")
        if (videoId != null) {
            Log.d(TAG, "Extracting URL for Video ID: $videoId")
            Log.d(TAG, "Calling playVideo() for videoId: $videoId")
           playVideo(videoId)
        } else {
            Log.e(TAG, "No video ID provided!")
        }
    }
//    private fun extractYouTubeUrl(videoId: String) {
//        Log.d(TAG, "Inside extractYouTubeUrl(). Video ID: $videoId")
//
//        val yt = YTExtractor(this, CACHING = false, LOGGING = true, retryCount = 3)
//
//        Log.d(TAG, "Before launching coroutine...")
//
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d(TAG, "Coroutine started... Extracting video ID: $videoId")
//
//            yt.extract(videoId)  // This might be blocking or failing.
//
//            Log.d(TAG, "Extraction completed. State: ${yt.state}")
//
//            if (yt.state == State.SUCCESS) {
//                val ytFiles: SparseArray<YtFile>? = yt.getYTFiles()
//                if (ytFiles != null && ytFiles.size() > 0) {
//                    val highestQuality = ytFiles[ytFiles.keyAt(ytFiles.size() - 1)]
//                    val videoUrl = highestQuality.url
//
//                    Log.d(TAG, "Extracted Video URL: $videoUrl")
//
//                    CoroutineScope(Dispatchers.Main).launch {
//                        playVideo(videoUrl.toString())
//                    }
//                } else {
//                    Log.e(TAG, "ytFiles is null! No video streams found.")
//                }
//            } else {
//                Log.e(TAG, "YTExtractor failed! State: ${yt.state}")
//            }
//        }
//    }

    private fun playVideo(videoUrl: String) {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }
}
