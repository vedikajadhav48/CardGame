package com.example.vedikajadhav.cardgame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    /*final Context context = this;
    public static int flipCount = 0;*/
    private TextView flipCountText;
    public static int flipCount = 0;
    private ImageAdapter imageAdapter;
    private Integer[] faceCardList = {R.drawable.club2, R.drawable.club8, R.drawable.diamond6, R.drawable.diamond9, R.drawable.diamond_king,
            R.drawable.heart_king, R.drawable.heart_jack, R.drawable.heart_queen, R.drawable.heart3, R.drawable.spade4, R.drawable.spade_king,
            R.drawable.spade9};
    private ArrayList<BlankImageWithFaceImage> cardArrayList = new ArrayList<BlankImageWithFaceImage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView flipCountText = (TextView) findViewById(R.id.textFlipCount);
        flipCountText.setText(getResources().getString(R.string.flip_count_text) + flipCount);

        GridView grid = (GridView) findViewById(R.id.gridView1);
        createCardArrayList(cardArrayList);
        imageAdapter = new ImageAdapter(cardArrayList, this);
        grid.setAdapter(imageAdapter);

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

    private void createCardArrayList(ArrayList<BlankImageWithFaceImage> cardArrayList) {
        for(int i =0; i<2 ; i++){
            for(int j = 0; j<12; j++) {
                cardArrayList.add(new BlankImageWithFaceImage(R.drawable.images, faceCardList[j]));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
