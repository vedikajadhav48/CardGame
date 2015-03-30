package com.example.vedikajadhav.cardgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";
    private static int flipCount = 0;
    private ImageAdapter imageAdapter;
    private Integer[] faceCardList = {R.drawable.club2, R.drawable.club8, R.drawable.diamond6, R.drawable.diamond9, R.drawable.diamond_king,
            R.drawable.heart_king, R.drawable.heart_jack, R.drawable.heart_queen, R.drawable.heart3, R.drawable.spade4, R.drawable.spade_king,
            R.drawable.spade9};
    private ArrayList<BlankImageWithFaceImage> cardArrayList = new ArrayList<BlankImageWithFaceImage>();
    //cardArray stores positions of the two cards selected for comparison
    private ArrayList<BlankImageWithFaceImage> noOfCardsSelectedToCompare = new ArrayList<BlankImageWithFaceImage>();
    //cardStatus is used to check if the card has already been matched
    private boolean[] cardStatus = new boolean[24];
    private int cardsMatched =0;
    EditText flipCountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        flipCountEditText = (EditText) findViewById(R.id.flipCountEditText);
        flipCountEditText.setText("" +flipCount);

        GridView grid = (GridView) findViewById(R.id.gridView1);
        /*createCardArrayList(cardArrayList);*/
        imageAdapter = new ImageAdapter(cardArrayList, this);

        Object object = getLastCustomNonConfigurationInstance();
        if(null != object){
            imageAdapter = (ImageAdapter)object;
        }
        else{
            createCardArrayList(cardArrayList);
        }
        grid.setAdapter(imageAdapter);
        grid.setOnItemClickListener(this);
    }

    private void createCardArrayList(ArrayList<BlankImageWithFaceImage> cardArrayList) {
        for(int i =0; i<2 ; i++){
            for(int j = 0; j<12; j++) {
                cardArrayList.add(new BlankImageWithFaceImage(R.drawable.images, faceCardList[j]));
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        swapImageIds(position);
        if(cardsMatched == 12)
            showGameOverDialog();
    }

    private void swapImageIds(int position) {
        if (cardStatus[position] == false) {
            flipCount++;
            BlankImageWithFaceImage blankImageWithFaceImage = imageAdapter.getItem(position);
            int blankCardId = blankImageWithFaceImage.getBlankImageId();
            blankImageWithFaceImage.setBlankImageId(blankImageWithFaceImage.getFaceImageId());
            blankImageWithFaceImage.setFaceImageId(blankCardId);
            imageAdapter.notifyDataSetChanged();
            flipCountEditText.setText("" + flipCount);
            noOfCardsSelectedToCompare.add(blankImageWithFaceImage);

            if (noOfCardsSelectedToCompare.size() == 1) {
                return;
            }

            if (noOfCardsSelectedToCompare.size() == 2) {
                int firstImageId = noOfCardsSelectedToCompare.get(0).getBlankImageId();
                int secondImageId = noOfCardsSelectedToCompare.get(1).getBlankImageId();
                if (firstImageId == secondImageId) {
                    cardStatus[cardArrayList.indexOf(noOfCardsSelectedToCompare.get(0))] = true;
                    cardStatus[cardArrayList.indexOf(noOfCardsSelectedToCompare.get(1))] = true;
                    noOfCardsSelectedToCompare.clear();
                    cardsMatched++;
                    return;
                } else {
                    for (int i = 0; i < noOfCardsSelectedToCompare.size(); i++) {
                        flipCardsBack(noOfCardsSelectedToCompare.get(i));// if the cards don't match flip them back
                    }
                    noOfCardsSelectedToCompare.clear();
                }
            }
        }
        else{
            return;
        }
    }

    private void flipCardsBack(BlankImageWithFaceImage blankImageWithFaceImage) {
        int blankCardId = blankImageWithFaceImage.getBlankImageId();
        blankImageWithFaceImage.setBlankImageId(blankImageWithFaceImage.getFaceImageId());
        blankImageWithFaceImage.setFaceImageId(blankCardId);
    }

    private void showGameOverDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(R.string.game_over_title);
        alertDialogBuilder.setMessage(getResources().getString(R.string.game_over_message) + "" + flipCount);

        // set positive button: New Game message
        alertDialogBuilder.setPositiveButton(R.string.confirm_new_game_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Intent i = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(i);
                dismissAlertDialog(dialog);
            }
        });
        // set negative button: Quit Game message
        alertDialogBuilder.setNegativeButton(R.string.confirm_quit_game_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // cancel the alert box and put a Toast to the user
                dismissAlertDialog(dialog);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }

    private void dismissAlertDialog(DialogInterface dialog) {
        dialog.cancel();
        flipCount = 0;
        finish();
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return imageAdapter;
    }
}
