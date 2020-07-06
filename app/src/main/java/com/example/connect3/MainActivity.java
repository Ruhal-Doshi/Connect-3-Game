package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int[] state = new int[9];
    boolean red;
    boolean isActive;

    public void restart(View view){
        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.INVISIBLE);
        TextView editText = (TextView) findViewById(R.id.redWonTextView);
        editText.setAlpha(0);
        editText = (TextView) findViewById(R.id.yellowWonTextView);
        editText.setAlpha(0);
        editText = (TextView) findViewById(R.id.drawTextView);
        editText.setAlpha(0);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

        for(int i =0;i<gridLayout.getChildCount();i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }
        state = new int[9];
        isActive = true;
        red = true;
    }

    public void onClick(View view){
        ImageView marker = (ImageView) view;
        int tag = Integer.parseInt(marker.getTag().toString());
        Log.i("Info","Image touched = "+tag);

        if(!isActive||state[tag]!=0){
            return;
        }
        marker.setTranslationY(-1500);

        if(red){
            state[tag]=1;
            red = false;
            marker.setImageResource(R.drawable.red);
            if(check(state,1)){
                TextView editText = (TextView) findViewById(R.id.redWonTextView);
                editText.setAlpha(1);
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                isActive=false;
            }else if(isDraw(state)){
                TextView editText = (TextView) findViewById(R.id.drawTextView);
                editText.setAlpha(1);
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
             isActive=false;
            }
        }else{
            state[tag]=2;
            red = true;
            marker.setImageResource(R.drawable.yellow);
            if(check(state,2)){
                TextView editText = (TextView) findViewById(R.id.yellowWonTextView);
                editText.setAlpha(1);
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                isActive=false;
            }else if(isDraw(state)){
                TextView editText = (TextView) findViewById(R.id.drawTextView);
                editText.setAlpha(1);
                Button button = (Button) findViewById(R.id.button);
                button.setVisibility(View.VISIBLE);
                isActive=false;
            }
        }

        marker.animate().translationYBy(1500).rotation(1800).setDuration(300);
    }

    public boolean check(int[] state,int player){
        for(int i =0;i<3;i++){
            if((state[3*i]==player&&state[3*i+1]==player&&state[3*i+2]==player)){
                return true;
            }
            if((state[i]==player&&state[i+3]==player&&state[i+6]==player)){
                return true;
            }
        }

        if(state[4]==player){
            if((state[0]==player&&state[8]==player)||(state[2]==player&&state[6]==player)){
                return true;
            }
        }
        return false;
    }

    public boolean isDraw(int[] state){
        for(int i : state){
            if(i==0)return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red = true;
        isActive = true;
    }
}