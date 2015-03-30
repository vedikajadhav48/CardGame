package com.example.vedikajadhav.cardgame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    public static int flipCount = 0;
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

        flipCountEditText = (EditText) findViewById(R.id.flipCountEditText);
        flipCountEditText.setText("" +flipCount);

        GridView grid = (GridView) findViewById(R.id.gridView1);
        createCardArrayList(cardArrayList);
        imageAdapter = new ImageAdapter(cardArrayList, this);
        grid.setAdapter(imageAdapter);
        grid.setOnItemClickListener(this);

 /*       grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                flipCount++;
                imageAdapter.swapImageIds(position);
                imageAdapter.notifyDataSetChanged();
                flipCountText.setText("FLIP COUNT: " + flipCount);

                //when all the 12 cards are matched, game is over
                if (imageAdapter.getCardsMatched() == 12) {
                    final Dialog dialog = new Dialog(context);
                    // tell the Dialog to use the dialog.xml as it's layout
                    // description
                    dialog.setContentView(R.layout.dialog);
                    dialog.setTitle(R.string.congratulations);

                    TextView txt = (TextView) dialog.findViewById(R.id.txt);
                    txt.setText("Your flipcount is: " + flipCount);
                    Button newGameButton = (Button) dialog
                            .findViewById(R.id.newGameButton);
                    Button quitGameButton = (Button) dialog
                            .findViewById(R.id.quitGameButton);

                    newGameButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                            flipCount = 0;
                            Intent i = new Intent(getApplicationContext(),
                                    MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });

                    quitGameButton.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            flipCount = 0;
                            finish();
                        }
                    });
                    dialog.show();
                }
            }
        });*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        swapImageIds(position);
        if(cardsMatched == 12)
        showGameOverDialog();
    }

    private void showGameOverDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);

        alertDialogBuilder.setTitle(R.string.game_over_title);
        alertDialogBuilder.setMessage(R.string.game_over_message);

        // set positive button: New Game message
        alertDialogBuilder.setPositiveButton(R.string.confirm_new_game_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                /*isDateSet = true;

                // go to a new activity of the app
                SharedPreferences.Editor editor = mSharedPreferences.edit();
                editor.putInt("Month", mDatePicker.getMonth());
                editor.putInt("Day", mDatePicker.getDayOfMonth());
                editor.putInt("Year", mDatePicker.getYear());
                long dateTime = mDatePicker.getCalendarView().getDate();
                Date date = new Date(dateTime);
                editor.commit();*/
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "You chose a positive answer",
                        Toast.LENGTH_LONG).show();
            }
        });
        // set negative button: Quit Game message
        alertDialogBuilder.setNegativeButton(R.string.confirm_quit_game_button,new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                // cancel the alert box and put a Toast to the user
                dialog.cancel();
                Toast.makeText(getApplicationContext(), "You chose a negative answer",
                        Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();

    }

    private void swapImageIds(int position) {
        if (cardStatus[position] == false) {
            flipCount++;
            BlankImageWithFaceImage blankImageWithFaceImage = imageAdapter.getItem(position);
            int blankCardId = blankImageWithFaceImage.getBlankImageId();
            blankImageWithFaceImage.setBlankImageId(blankImageWithFaceImage.getFaceImageId());
            blankImageWithFaceImage.setFaceImageId(blankCardId);
            imageAdapter.notifyDataSetChanged();
            flipCountEditText.setText("" +flipCount);
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

    private void createCardArrayList(ArrayList<BlankImageWithFaceImage> cardArrayList) {
        for(int i =0; i<2 ; i++){
            for(int j = 0; j<12; j++) {
                cardArrayList.add(new BlankImageWithFaceImage(R.drawable.images, faceCardList[j]));
            }
        }
    }
}
