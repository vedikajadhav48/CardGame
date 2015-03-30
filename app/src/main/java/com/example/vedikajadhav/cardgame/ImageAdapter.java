package com.example.vedikajadhav.cardgame;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vedika Jadhav on 3/24/2015.
 */
public class ImageAdapter extends ArrayAdapter<BlankImageWithFaceImage>{
    private static final String TAG = "ImageAdapter";
    private Context mContext;
    private ArrayList<BlankImageWithFaceImage> cardArray;

    public ImageAdapter(ArrayList<BlankImageWithFaceImage> mCardArrayList, Context context) {
        super(context.getApplicationContext(), 0, mCardArrayList);
        cardArray = mCardArrayList;
        mContext = context;
        Log.i(TAG, "size" +cardArray.size());
        //used to show up random images in gridView
        Random rnd = new Random();
        for (int i =  cardArray.size()- 1; i >= 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            BlankImageWithFaceImage temp = cardArray.get(index);
            cardArray.set(index, cardArray.get(i));
            cardArray.set(i, temp);
        }
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)mContext).getLayoutInflater().inflate(R.layout.grid_item, null);
        }
        //Configure the view for this Card(BlankImageWithFaceImage)
        BlankImageWithFaceImage blankImageWithFaceImage = getItem(position);
        ImageView blankImage = (ImageView)convertView.findViewById(R.id.grid_item_blank_image);
        Resources r = mContext.getResources();
        Drawable[] layers = new Drawable[2];
        layers[0] = r.getDrawable(blankImageWithFaceImage.getFaceImageId());
        layers[1] = r.getDrawable(blankImageWithFaceImage.getBlankImageId());
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        blankImage.setImageDrawable(layerDrawable);


        /*blankImage.setImageResource(blankImageWithFaceImage.getBlankImageId());
        blankImage.setBackgroundResource(blankImageWithFaceImage.getFaceImageId());*/
        return convertView;
    }
}
