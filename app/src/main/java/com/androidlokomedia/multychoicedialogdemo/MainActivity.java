package com.androidlokomedia.multychoicedialogdemo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button mOrder;
    TextView mItemSelected;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> mUserItems =  new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mOrder = (Button)findViewById(R.id.buttonOrder);
        mItemSelected = (TextView)findViewById(R.id.textViewItemSelected);

        listItems = getResources().getStringArray(R.array.shopping_items);
        checkedItems = new boolean[listItems.length];

        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle(R.string.produk);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int posisi, boolean isChecked) {
                        if (isChecked){
                            if (!mUserItems.contains(posisi)){
                                mUserItems.add(posisi);
                            }
                        } else if (mUserItems.contains(posisi)){
                            mUserItems.remove(posisi);
                        }
                    }
                });
//
                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.posistif, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i = 0; i < mUserItems.size(); i++){
                            item = item + listItems[mUserItems.get(i)];
                            if (i != mUserItems.size() - 1){
                                item = item + ", ";
                            }
                            mItemSelected.setText(item);
                        }

                    }
                });
                mBuilder.setNegativeButton(R.string.negatif, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                mBuilder.setNeutralButton(R.string.netral, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++){
                            checkedItems[i] = false;
                            mUserItems.clear();
                            mItemSelected.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
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
