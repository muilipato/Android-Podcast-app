package meemseen.podcasto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class AudioListAdapter extends ArrayAdapter<AudioFile> {
    /**
     * This is my own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param audioFiles A List of audioFile objects to display in a list
     */
    public AudioListAdapter(@NonNull Context context, ArrayList<AudioFile> audioFiles) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, audioFiles);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the {@link Word} object located at this position in the list
        AudioFile currentAudioFile = getItem(position);

        //Create a new recycleView or populate a NEWLY CREATED VIEW
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Find the TextViews in the list_item.xml layout
        TextView audioTitle    = listItemView.findViewById(R.id.audio_title);
        TextView audioProducer = listItemView.findViewById(R.id.audio_producer);
        TextView audioHost     = listItemView.findViewById(R.id.audio_host);
        ImageView audioArt     = listItemView.findViewById(R.id.audio_art);
        //Set text for each TextView
        assert currentAudioFile != null;
        audioProducer.setText(currentAudioFile.getAudioProducer());
        audioTitle.setText(currentAudioFile.getAudioTitle());
        audioHost.setText(currentAudioFile.getAudioHost());
        audioArt.setImageResource(currentAudioFile.getImageResourceId());


        return listItemView;
    }
}
