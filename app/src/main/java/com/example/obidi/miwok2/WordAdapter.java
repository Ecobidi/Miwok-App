package com.example.obidi.miwok2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 10/17/2018.
 */
public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Context context, ArrayList<Word> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the list item view or inflate a new one if null
        View listItemView = convertView;

        if (listItemView == null) { // inflate a new list item view
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item_view, parent, false);
        }
        // get current data that will be fed the list item view
        Word currentWord = getItem(position);

        // check if currentWord has an image or not, and display listItem view accordingly

        if (currentWord.getImageResourceId() == -1) {
            // get the imageView and completely remove it from the listItem view
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_image);
            // completely remove imageView from listItem
            imageView.setVisibility(View.GONE);
        } else {
            // get the image in the list item and set the image src and content description
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_image);
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setContentDescription("Pictorial representation of current word");
        }

        // get the default translation textView and set text
        TextView defaultTranslationText = (TextView) listItemView.findViewById(R.id.default_translation_text);
        defaultTranslationText.setText(currentWord.getdefaultTranslationStringResourceId());

        // get the default translation textView and set text
        TextView miwokTranslationText = (TextView) listItemView.findViewById(R.id.miwok_translation_text);
        miwokTranslationText.setText(currentWord.getmiwokTranslationStringResourceId());

        return listItemView;
    }
}
