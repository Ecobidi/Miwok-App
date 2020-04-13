package com.example.obidi.miwok2;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoryListActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    AudioManager.OnAudioFocusChangeListener audioFocusChangeListener;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        // set actionbar title
        setTitle(getIntent().getStringExtra("title"));

        // implement OnAudioFocusChangeListener Interface
        audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener(){
            @Override
            public void onAudioFocusChange(int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                        focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                    // pause our MediaPlayer object
                    mediaPlayer.pause();
                    // reset to beginning of audio
                    mediaPlayer.seekTo(0);

                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                    // release our MediaPlayer object
                    releaseMediaPlayer();
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                    // resume our MediaPlayer object
                    mediaPlayer.start();
                } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                    // resume our MediaPlayer object
                    mediaPlayer.start();
                }
            }
        };
        // assign audioManager
        audioManager = (AudioManager) this.getSystemService(CategoryListActivity.AUDIO_SERVICE);
        // get category tag
        String categoryTag = getIntent().getStringExtra("category");

        // get request category data
        final ArrayList<Word> words = QueryUtils.getWords(categoryTag);
        // create custom WordAdapter
        WordAdapter wordAdapter = new WordAdapter(CategoryListActivity.this, words);
        // get the reference of listView that will display the words
        ListView listView = (ListView) findViewById(R.id.list_view_1);
        // set the listView adapter
        listView.setAdapter(wordAdapter);

        // set onItemClickListener event listener on the listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // release mediaPlayer, in case it's still playing
                releaseMediaPlayer();
                // Request Audio Focus of our AudioManager object
                audioManager.requestAudioFocus(
                        // listenerObject
                        audioFocusChangeListener,
                        // Audio Stream Type
                        AudioManager.STREAM_MUSIC,
                        // Audio Duration
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                Toast.makeText(CategoryListActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                // get the data objected related to this listItem
                Word currentWord = words.get(position);
                // initialize the mediaPlayer with this listItem's audio resource
                mediaPlayer = MediaPlayer.create(CategoryListActivity.this, currentWord.getAudioResourceId());
                // play the audio resource
                mediaPlayer.start();

                // set onCompletionListener on the mediaPlayer
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        // free up memory resource
                        releaseMediaPlayer();
                    }
                });
            }
        });
    }

    // override the Activity onStop method
    @Override
    protected void onStop() {
        super.onStop();
        // free up memory resource
        releaseMediaPlayer();
    }

    public void releaseMediaPlayer() {
        // if mediaPlayer has been initialized (i.e. currently playing a song), free up memory resources
        if (mediaPlayer != null) {
            // release mediaPlayer resource
            mediaPlayer.release();
            // set mediaPlayer back to null
            mediaPlayer = null;
            // Abandon Audio focus
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_category_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
