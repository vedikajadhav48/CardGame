package com.example.vedikajadhav.cardgame;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vedika Jadhav on 3/24/2015.
 */
public class ImageAdapter extends ArrayAdapter<BlankImageWithFaceImage>{

    private Context mContext;
    //cardArray stores positions of the two cards selected for comparison
    private ArrayList<BlankImageWithFaceImage> cardArray = new ArrayList<BlankImageWithFaceImage>();
    //cardStatus is used to check if the card has already been matched
    private boolean[] cardStatus = new boolean[24];
    //cardsMatched is used to check end of the game
    private int cardsMatched = 0;

    public ImageAdapter(ArrayList<BlankImageWithFaceImage> mCardArrayList, Context context) {
        super(context.getApplicationContext(), 0, mCardArrayList);
        cardArray = mCardArrayList;
        mContext = context;
        //used to show up random images in gridView
        Random rnd = new Random();
/*        for (int i = imageIds.length - 1; i >= 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            blankImageWithFaceImage temp = imageIds[index];
            imageIds[index] = imageIds[i];
            imageIds[i] = temp;
        }*/
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        /*ImageView imageView;
        if (convertView == null) { // if it's not recycled, initialize some
            // attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(50, 50));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(cardArray.get(position).getBlankImageId());
        return imageView;*/
        if(convertView == null){
            convertView = ((Activity)mContext).getLayoutInflater().inflate(R.layout.grid_item, null);
        }

        //Configure the view for this Card(BlankImageWithFaceImage)
        BlankImageWithFaceImage blankImageWithFaceImage = getItem(position);

        ImageView blankImage = (ImageView)convertView.findViewById(R.id.grid_item_blank_image);
       // blankImage.setImageResource(R.drawable.images);
        blankImage.setImageResource(blankImageWithFaceImage.getBlankImageId());

       // ImageView faceImage = (ImageView)convertView.findViewById(R.id.grid_item_face_image);
        blankImage.setBackgroundResource(blankImageWithFaceImage.getFaceImageId());

        return convertView;
    }

/*    public void swapImageIds(int position) {
        if (cardStatus[position] == false) {
            Log.i("MYLOG", position + "");
            int bId = imageIds[position].getBlankImageId();
            imageIds[position].setBlankImageId(imageIds[position]
                    .getFaceImageId());
            imageIds[position].setFaceImageId(bId);
            cardArray.add(position);

            if (cardArray.size() == 1) {
                return;
            }

            if (cardArray.size() == 2) {
                int firstImageId = imageIds[cardArray.get(0)].getBlankImageId();
                int secondImageId = imageIds[cardArray.get(1)].getBlankImageId();

                if (firstImageId == secondImageId) {
                    cardStatus[cardArray.get(0)] = true;
                    cardStatus[cardArray.get(1)] = true;
                    cardsMatched++;

                    cardArray.clear();
                    return;
                }
            }

            for (int i = 0; i < cardArray.size(); i++) {
                restoreImageIds(cardArray.get(i));//if the cards dont match flip them back
            }
            cardArray.clear();
        } else {
           // MainActivity.flipCount--;
        }
        return;
    }

    public void restoreImageIds(int position) {
        int bId = imageIds[position].getBlankImageId();
        imageIds[position].setBlankImageId(imageIds[position].getFaceImageId());
        imageIds[position].setFaceImageId(bId);
    }

    public int getCardsMatched() {
        return cardsMatched;
    }*/
}