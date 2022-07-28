package com.example.a512j;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import com.example.a512j.Model.ItemModel;

import java.util.ArrayList;

public class IntroActivity  extends AppCompatActivity {

    RecyclerView rv;
    LinearLayout dotsLayout;
    Button btn;
    TextView[] dots;
    TextView textView;
    ArrayList<ItemModel> item;
    Animation animation;
    int view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        getData();
        setRv();

        textView = findViewById(R.id.textSkip);
        dotsLayout = findViewById(R.id.dots);

          textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               rv.smoothScrollToPosition(2);
            }
        });


        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
            }
        });
        addDots(0);



        rv.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                view = rv.getScrollY();
                Log.e("Next: ", view + "");

                if (view == 2){
                    animation = AnimationUtils.loadAnimation(IntroActivity.this,R.anim.animation);
                    btn.setAnimation(animation);
                    textView.setVisibility(View.INVISIBLE);
                    btn.setVisibility(View.VISIBLE);

                }else {
                    btn.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);

                }
            }
        });
    }

    private void setRv(){
       rv =findViewById(R.id.rv);
       rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, item);
        rv.setAdapter(recyclerAdapter);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {startActivity(new Intent(IntroActivity.this,MainActivity.class));}
        });
    }

    void getData(){
        item = new ArrayList<>();
        item.add(new ItemModel(R.raw.fraglottie,   "Say Hello to \n Global Top-Up",  "Send mobile top-up to more than 500 networks in over 140 countries."));
        item.add(new ItemModel(R.raw.secondfraglottie,    "Safe, Trusted & \n Fully Secure",  "Encrypted transactions mean your payments & Privacy and protected."));
        item.add(new ItemModel(R.raw.thirdfraglottie,     "Easy to Use",   "Pick a number, choose an amount, send your Top-up. Simple."));
    }

    private void  addDots(int position){
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.blue));
        }
    }
}
