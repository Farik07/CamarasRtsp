package com.wolvesdev.cmarasdvr

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import org.videolan.libvlc.LibVLC
import org.videolan.libvlc.Media
import org.videolan.libvlc.MediaPlayer
import org.videolan.libvlc.util.VLCVideoLayout

class MainActivity : AppCompatActivity() {
    private lateinit var libVlc: LibVLC
    private lateinit var mediaPlayer1: MediaPlayer
    private lateinit var mediaPlayer2: MediaPlayer
    private lateinit var mediaPlayer3: MediaPlayer
    private lateinit var mediaPlayer4: MediaPlayer
    private lateinit var videoLayout1: VLCVideoLayout
    private lateinit var videoLayout2: VLCVideoLayout
    private lateinit var videoLayout3: VLCVideoLayout
    private lateinit var videoLayout4: VLCVideoLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        libVlc = LibVLC(this)
        mediaPlayer1 = MediaPlayer(libVlc)
        mediaPlayer2 = MediaPlayer(libVlc)
        mediaPlayer3 = MediaPlayer(libVlc)
        mediaPlayer4 = MediaPlayer(libVlc)
        videoLayout1 = findViewById(R.id.videoLayout1)
        videoLayout2 = findViewById(R.id.videoLayout2)
        videoLayout3 = findViewById(R.id.videoLayout3)
        videoLayout4 = findViewById(R.id.videoLayout4)
    }

    override fun onStart() {
        super.onStart()

        setupMediaPlayer(mediaPlayer1, videoLayout1, "url de rtsp")
        setupMediaPlayer(mediaPlayer2, videoLayout2, "url de rtsp")
        setupMediaPlayer(mediaPlayer3, videoLayout3, "url de rtsp")
        setupMediaPlayer(mediaPlayer4, videoLayout4, "url de rtsp")
    }

    private fun setupMediaPlayer(mediaPlayer: MediaPlayer, videoLayout: VLCVideoLayout, url: String) {
        mediaPlayer.attachViews(videoLayout, null, false, false)
        val media = Media(libVlc, Uri.parse(url))
        media.setHWDecoderEnabled(true, false)
        media.addOption(":network-caching=600")
        mediaPlayer.media = media
        media.release()
        mediaPlayer.play()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer1.stop()
        mediaPlayer2.stop()
        mediaPlayer3.stop()
        mediaPlayer4.stop()
        mediaPlayer1.detachViews()
        mediaPlayer2.detachViews()
        mediaPlayer3.detachViews()
        mediaPlayer4.detachViews()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer1.release()
        mediaPlayer2.release()
        mediaPlayer3.release()
        mediaPlayer4.release()
        libVlc.release()
    }
}