package meemseen.podcasto;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class Category extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        // Add back button to the Action Bar
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        SharedPreferences sharedPreferences = getSharedPreferences("pref", MODE_PRIVATE);
        String str = sharedPreferences.getString("category", "");
        SharedPreferences playPreferences = getSharedPreferences("pref" , MODE_PRIVATE);
        final SharedPreferences.Editor playEditor = playPreferences.edit();

        // Which category was selected?
        switch (str) {
            case "technology": { //technology category
                //Set the title of the activity
                setTitle(R.string.category_tech);

                //ArrayList of the audio files
                final ArrayList<AudioFile> audiosList = new ArrayList<>();

                audiosList.add(new AudioFile(
                        "TED Talks",
                        "What should electric cars sound like?",
                        "Renzo Vitale",
                        "https://download.ted.com/talks/RenzoVitale_2018S.mp4?apikey=172BB350-0013",
                        R.drawable.tedtech));
                audiosList.add(new AudioFile(
                        "The Verge",
                        "WWDC leaks, Luminary’s launch troubles, and Galaxy Fold review",
                        "Dieter Bohn, Ashley Carman, and Paul Miller",
                        "http://traffic.megaphone.fm/VMP7752779819.mp3",
                        R.drawable.vergecast));
                audiosList.add(new AudioFile(
                        "The Verge",
                        "Aurora CEO Chris Urmson on what's next for self-driving cars",
                        "Nilay Patel and Andrew Hawkins",
                        "http://traffic.megaphone.fm/VMP5546327530.mp3",
                        R.drawable.vergecast));
                audiosList.add(new AudioFile(
                        "TED Talks",
                        "Quantum computing explained in 10 minutes",
                        "Shohini Ghose",
                        "https://download.ted.com/talks/ShohiniGhose_2018W.mp4?apikey=172BB350-0013",
                        R.drawable.tedtech));
                audiosList.add(new AudioFile(
                        "The Verge",
                        "Samsung's Galaxy Fold phones are breaking",
                        "Nilay Patel, Dieter Bohn, and Paul Miller",
                        "http://traffic.megaphone.fm/VMP7134022371.mp3",
                        R.drawable.vergecast));
                audiosList.add(new AudioFile(
                        "The Verge",
                        "The electric scooter revolution with Lime co-founder Brad Bao",
                        "Nilay Patel and Andrew Hawkins",
                        "http://traffic.megaphone.fm/VMP8389952814.mp3",
                        R.drawable.vergecast));
                audiosList.add(new AudioFile(
                        "TED Talks",
                        "India's smartphone revolution is creating a new generation of readers and writers",
                        "Chiki Sarkar",
                        "https://download.ted.com/talks/ChikiSarkar_2018S.mp4?apikey=172BB350-0013",
                        R.drawable.tedtech));
                audiosList.add(new AudioFile(
                        "The Verge",
                        "The Age of Surveillance Capitalism with Shoshana Zuboff",
                        "Nilay Patel",
                        "http://traffic.megaphone.fm/VMP2098313987.mp3",
                        R.drawable.vergecast));

                //Custom Array Adapter which data source is a list of AudiFile objects (audiosList)
                //The adapters knows how to create list items for each item in the list
                AudioListAdapter customListAdapter =
                        new AudioListAdapter(this, audiosList);

                //Link this custom ArrayAdapter to the ListView
                listView = findViewById(R.id.list_view);
                listView.setAdapter(customListAdapter);

                //Set a listener to know when an item is clicked
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the AudioFile object that was clicked from the list item
                        AudioFile clickedAudioFile = audiosList.get(position);

                        playEditor.putString("url", clickedAudioFile.getUrl());
                        playEditor.putString("title", clickedAudioFile.getAudioTitle());
                        playEditor.putString("subtitle", clickedAudioFile.getAudioHost());
                        playEditor.putInt("image_id", clickedAudioFile.getImageResourceId());
                        playEditor.apply();

                        Intent intent = new Intent(Category.this, Play.class);
                        startActivity(intent);
                    }
                });
                break;
            }
            case "business": { //business category
                //Set the title of the activity
                setTitle(R.string.category_business);

                //ArrayList of the audio files
                final ArrayList<AudioFile> audiosList = new ArrayList<>();

                audiosList.add(new AudioFile(
                        "Rich Dad Radio Show",
                        "GOING SMALL MAY BE THE PATH TO SUCCESS",
                        "Robert & Kim Kiyosaki featuring Paul Jarvis",
                        "http://traffic.libsyn.com/richdadradio/rd-radio-full-19-04-24.mp3",
                        R.drawable.richdad));
                audiosList.add(new AudioFile(
                        "EntreLeadership",
                        "Solve Your People Problems",
                        "Sarah Sloyan",
                        "http://traffic.libsyn.com/entreleadershippodcast/entreleadership_20190318.mp3",
                        R.drawable.entreleadership));
                audiosList.add(new AudioFile(
                        "Rich Dad Radio Show",
                        "PERSONAL & FINANCIAL SPRING CLEANING",
                        "Robert & Kim Kiyosaki featuring Gretchen Rubin",
                        "http://traffic.libsyn.com/richdadradio/rd-radio-full-19-04-171.mp3",
                        R.drawable.richdad));
                audiosList.add(new AudioFile(
                        "EntreLeadership",
                        "Finding Freedom to Focus",
                        "Michael Hyatt",
                        "http://traffic.libsyn.com/entreleadershippodcast/entreleadership_20190408.mp3",
                        R.drawable.entreleadership));
                audiosList.add(new AudioFile(
                        "EntreLeadership",
                        "Creating Business Momentum",
                        "Rachel Hollis",
                        "http://traffic.libsyn.com/entreleadershippodcast/entreleadership_20190401.mp3",
                        R.drawable.entreleadership));
                audiosList.add(new AudioFile(
                        "Rich Dad Radio Show",
                        "HEAR AN INSIDER’S VIEW OF TRUMP’S ECONOMIC POLICIES",
                        "Robert & Kim Kiyosaki featuring David Stockman",
                        "http://traffic.libsyn.com/richdadradio/rd-radio-full-19-04-10.mp3",
                        R.drawable.richdad));
                audiosList.add(new AudioFile(
                        "Rich Dad Radio Show",
                        "THE BEST WAY TO DEAL WITH PEOPLE IN BUSINESS",
                        "Robert & Kim Kiyosaki featuring Stanley McChrystal",
                        "http://traffic.libsyn.com/richdadradio/rd-radio-full-19-04-03.mp3",
                        R.drawable.richdad));
                audiosList.add(new AudioFile(
                        "EntreLeadership",
                        "Word-of-Mouth Talk Triggers",
                        "Word-of-Mouth Talk Triggers",
                        "http://traffic.libsyn.com/entreleadershippodcast/entreleadership_20190325.mp3",
                        R.drawable.entreleadership));
                audiosList.add(new AudioFile(
                        "Rich Dad Radio Show",
                        "HOW THE PENSION CRISIS AFFECTS YOUR RETIREMENT & WEALTH",
                        "Robert & Kim Kiyosaki featuring Ted Siedle",
                        "http://traffic.libsyn.com/richdadradio/rd-radio-full-19-03-27.mp3",
                        R.drawable.richdad));

                //Array Adapter
                //Custom Array Adapter which data source is a list of AudiFile objects (audiosList)
                //The adapters knows how to create list items for each item in the list
                AudioListAdapter customListAdapter =
                        new AudioListAdapter(this, audiosList);

                //Link this custom ArrayAdapter to the ListView
                listView = findViewById(R.id.list_view);
                listView.setAdapter(customListAdapter);

                //Set a listener to know when an item is clicked
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the word object from the list item that was clicked
                        AudioFile clickedAudioFile = audiosList.get(position);

                        playEditor.putString("url", clickedAudioFile.getUrl());
                        playEditor.putString("title", clickedAudioFile.getAudioTitle());
                        playEditor.putString("subtitle", clickedAudioFile.getAudioHost());
                        playEditor.putInt("image_id", clickedAudioFile.getImageResourceId());
                        playEditor.apply();

                        Intent intent = new Intent(Category.this, Play.class);
                        startActivity(intent);
                    }
                });
                break;
            }
            case "health": {  //health category
                //Set the title of the activity
                setTitle(R.string.category_health);

                //ArrayList of the audio files
                final ArrayList<AudioFile> audiosList = new ArrayList<>();

                audiosList.add(new AudioFile(
                        "THE HEALTH CODE",
                        "Become Your Most Productive Self!",
                        "Sarah's Day & Kurt Tilse",
                        "http://traffic.libsyn.com/forcedn/thehealthcode/Ep.10_mixdown.mp3",
                        R.drawable.thehealthcode));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "Hate Veggies, Falling off the (Wagon) + Vegan to Keto",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/148_episode.mp3",
                        R.drawable.healthful));
                audiosList.add(new AudioFile(
                        "THE HEALTH CODE",
                        "What is The Health Code? & How to Get Back on Track!",
                        "Sarah's Day & Kurt Tilse",
                        "http://traffic.libsyn.com/forcedn/thehealthcode/What_Is_The_Health_Code.mp3",
                        R.drawable.thehealthcode));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "Keto Period Problems, Digestive Changes + Adrenal Fatigue",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/145_episode.mp3",
                        R.drawable.healthful));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "The Fasting Mimicking Diet",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/145_episode.mp3",
                        R.drawable.healthful));
                audiosList.add(new AudioFile(
                        "THE HEALTH CODE",
                        "HOW TO GET ABS! How we got shredded abs: Our Top 10 Tips!",
                        "Sarah's Day & Kurt Tilse",
                        "http://traffic.libsyn.com/forcedn/thehealthcode/Ep.8_mixdown.mp3",
                        R.drawable.thehealthcode));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "What Really Happens When You Eat Keto",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/137_episode.mp3",
                        R.drawable.healthful));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "Why (Is It Keto?) Is the Wrong Question with Diane Sanfilippo",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/124_episode.mp3",
                        R.drawable.healthful));
                audiosList.add(new AudioFile(
                        "Healthful Pursuit",
                        "Fasting for Weight Loss with Ashley Salvatori",
                        "Leanne Vogel",
                        "http://traffic.libsyn.com/thenosugarcoatingpodcast/122_episode.mp3",
                        R.drawable.healthful));


                //Custom Array Adapter which data source is a list of AudiFile objects (audiosList)
                //The adapters knows how to create list items for each item in the list
                AudioListAdapter customListAdapter =
                        new AudioListAdapter(this, audiosList);

                //Link this custom ArrayAdapter to the ListView
                listView = findViewById(R.id.list_view);
                listView.setAdapter(customListAdapter);

                //Set a listener to know when an item is clicked
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the word object from the list item that was clicked
                        AudioFile clickedAudioFile = audiosList.get(position);

                        playEditor.putString("url", clickedAudioFile.getUrl());
                        playEditor.putString("title", clickedAudioFile.getAudioTitle());
                        playEditor.putString("subtitle", clickedAudioFile.getAudioHost());
                        playEditor.putInt("image_id", clickedAudioFile.getImageResourceId());
                        playEditor.apply();

                        Intent intent = new Intent(Category.this, Play.class);
                        startActivity(intent);
                    }
                });
                break;
            }
            case "sports": {  //sports category
                //Set the title of the activity
                setTitle(R.string.category_sports);

                //ArrayList of the audio files
                final ArrayList<AudioFile> audiosList = new ArrayList<>();

                audiosList.add(new AudioFile(
                        "ESPN",
                        "Caught Offside: Champions League semi-finals",
                        "Andrew Gundling & JJ Devaney",
                        "http://play.podtrac.com/espn-caughtoffside/c.espnradio.com/audio/3635798/caughtoffside_2019-05-01-203938.64k.mp3",
                        R.drawable.espnfc));
                audiosList.add(new AudioFile(
                        "ESPN",
                        "Messi Makes Reds Blush: 5/1/19",
                        "Steve Nicol",
                        "http://play.podtrac.com/espn-espnfc/c.espnradio.com/audio/3635762/espnfc_2019-05-01-185502.64k.mp3",
                        R.drawable.espnfc));
                audiosList.add(new AudioFile(
                        "She Explores",
                        "Choosing Environmental Optimism",
                        "Gale Straub",
                        "https://media.simplecast.com/episodes/audio/292562/105_-_Environmental_Optimism_mixdown.mp3",
                        R.drawable.she_explores));
                audiosList.add(new AudioFile(
                        "ESPN",
                        "Tottenham Fall to Ajax: 5/1/19",
                        "Dan Thomas",
                        "http://play.podtrac.com/espn-espnfc/c.espnradio.com/audio/3635560/espnfc_2019-05-01-084100.64k.mp3",
                        R.drawable.espnfc));
                audiosList.add(new AudioFile(
                        "She Explores",
                        "On and Off the Icefield: Hannah Perrine ModeChoosing Environmental Optimism",
                        "Gale Straub",
                        "https://media.simplecast.com/episodes/audio/291231/104_Hannah_P_Mode_mixdown.mp3",
                        R.drawable.she_explores));
                audiosList.add(new AudioFile(
                        "She Explores",
                        "Making Time for Nature",
                        "Gale Straub",
                        "https://media.simplecast.com/episodes/audio/274202/99_-_Making_Time_for_Nature_mixdown.mp3",
                        R.drawable.she_explores));
                audiosList.add(new AudioFile(
                        "ESPN",
                        "Is David de Problem? : 4/29/19",
                        "Gab Marcotti",
                        "http://play.podtrac.com/espn-espnfc/c.espnradio.com/audio/3635086/espnfc_2019-04-29-185226.64k.mp3",
                        R.drawable.espnfc));
                audiosList.add(new AudioFile(
                        "She Explores",
                        "The Power of Storytelling: Danielle Williams & Melanin Base Camp",
                        "Gale Straub",
                        "https://media.simplecast.com/episodes/audio/270593/98_-_Danielle_Williams_FINAL_FINAL_1.mp3",
                        R.drawable.she_explores));
                audiosList.add(new AudioFile(
                        "She Explores",
                        "Quitting can be a Kindness: Nicole Antoinette",
                        "Gale Straub",
                        "https://media.simplecast.com/episodes/audio/236208/92_-_Nic_Antoinette_mixdown.mp3",
                        R.drawable.she_explores));


                //Custom Array Adapter which data source is a list of AudiFile objects (audiosList)
                //The adapters knows how to create list items for each item in the list
                AudioListAdapter customListAdapter =
                        new AudioListAdapter(this, audiosList);
 
                //Link this custom ArrayAdapter to the ListView
                listView = findViewById(R.id.list_view);
                listView.setAdapter(customListAdapter);

                //Set a listener to know when an item is clicked
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Get the word object from the list item that was clicked
                        AudioFile clickedAudioFile = audiosList.get(position);

                        playEditor.putString("url", clickedAudioFile.getUrl());
                        playEditor.putString("title", clickedAudioFile.getAudioTitle());
                        playEditor.putString("subtitle", clickedAudioFile.getAudioHost());
                        playEditor.putInt("image_id", clickedAudioFile.getImageResourceId());
                        playEditor.apply();

                        Intent intent = new Intent(Category.this, Play.class);
                        startActivity(intent);
                    }
                });
                break;
            }
        }
    }
}
