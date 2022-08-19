package meemseen.podcasto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends AppCompatActivity {
    //References for the views
    ImageView homeIcon;
    ImageView playIcon;
    ImageView pauseIcon;
    ImageView playAudioArt;
    TextView playTitle;
    TextView playSubtitle;

    MediaPlayer mediaPlayer;
    AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mediaPlayer.pause();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback because you lost the Audio Focus and abandon it
                        audioManager.abandonAudioFocus(afChangeListener);
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus again
                        mediaPlayer.start();
                    }
                }
            };

    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed playing a file.
     * This is for anonymous class to create the object only once instead of each time.
     */
    private MediaPlayer.OnCompletionListener onCompletionListener =
            new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            //Regardless of whether or not we ere granted Audio Focus, abandon it.
            //This also unregisters the AudioFocusChangeListener so we don't get more callbacks
            audioManager.abandonAudioFocus(afChangeListener);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        // Get references for the views
        homeIcon     = findViewById(R.id.home_icon);
        playIcon     = findViewById(R.id.play_icon);
        pauseIcon    = findViewById(R.id.pause_icon);
        playAudioArt = findViewById(R.id.play_audio_art);
        playTitle    = findViewById(R.id.play_title);
        playSubtitle = findViewById(R.id.play_subtitle);

        /* reate and setup the {@link AudioManager to request Audio Focus*/
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //Get the data passed from the Category Activity
        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        final String sharedStreamingUrl = sharedPreferences.getString("url", "");
        final String sharedPlayTitle = sharedPreferences.getString("title", "");
        final String sharedPlaySubitle = sharedPreferences.getString("subtitle", "");
        final int sharedAudiArtId = sharedPreferences.getInt("image_id", -1);

        // Display the involved images and texts
        playAudioArt.setImageResource(sharedAudiArtId);
        playTitle.setText(sharedPlayTitle);
        playSubtitle.setText(sharedPlaySubitle);


        //start streaming the audio file
        playIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Give a feedback to the user when he plays a podcast
                Toast.makeText
                        (Play.this, "Streaming, please wait...", Toast.LENGTH_SHORT)
                        .show();
                //Release memory before preparing a new media player
                releaseMediaPlayer();
                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(
                        //Specify the listener
                        afChangeListener,
                        // Use the music stream option
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have the Audio Focus now
                    //Using Factory Method, create a new media player
                    mediaPlayer = MediaPlayer
                            .create(Play.this, Uri.parse(sharedStreamingUrl));
                    //Stream the podcast
                    mediaPlayer.start();
                    //Release memory when a media file is finished
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }
            }
        });
        //Pause streaming
        pauseIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Give a feedback to the user when he pauses the podcast
                Toast.makeText
                        (Play.this, "pause", Toast.LENGTH_SHORT)
                        .show();
                mediaPlayer.pause();
            }
        });
        //Go to home screen
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Give a feedback to the user
                Toast.makeText
                        (Play.this, "Browse Categories...", Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(Play.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
        //Regardless of whether or not we ere granted Audio Focus, abandon it.
        //This also unregisters the AudioFocusChangeListener so we don't get more callbacks
        audioManager.abandonAudioFocus(afChangeListener);
    }
}
