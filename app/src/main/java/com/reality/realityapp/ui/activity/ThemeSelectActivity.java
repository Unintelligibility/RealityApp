package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.reality.realityapp.R;

import java.util.ArrayList;

public class ThemeSelectActivity extends AppCompatActivity implements OnClickListener {

    private Button skipButton;
    private ImageView politicsImg;
    private ImageView sportsImg;
    private ImageView scienceImg;
    private ImageView financeImg;
    private ImageView militaryImg;
    private ImageView carsImg;
    private ImageView entertainmentImg;
    private ImageView houseImg;
    private ImageView gameImg;

    private ArrayList<Integer> chosenTags = new ArrayList<Integer>();
    /** 当前的ImageView */
    private ImageView currentImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_select);

        initView();
        initEvent();
    }

    private void initView() {
        skipButton = (Button) findViewById(R.id.id_btn_skip);
        politicsImg = (ImageView) findViewById(R.id.id_img_politics);
        politicsImg.setTag(1);
        sportsImg = (ImageView) findViewById(R.id.id_img_sports);
        sportsImg.setTag(2);
        scienceImg = (ImageView) findViewById(R.id.id_img_science);
        scienceImg.setTag(3);
        financeImg = (ImageView) findViewById(R.id.id_img_finance);
        financeImg.setTag(4);
        militaryImg = (ImageView) findViewById(R.id.id_img_military);
        militaryImg.setTag(5);
        carsImg = (ImageView) findViewById(R.id.id_img_cars);
        carsImg.setTag(6);
        entertainmentImg = (ImageView) findViewById(R.id.id_img_entertainment);
        entertainmentImg.setTag(7);
        houseImg = (ImageView) findViewById(R.id.id_img_house);
        houseImg.setTag(8);
        gameImg = (ImageView) findViewById(R.id.id_img_game);
        gameImg.setTag(9);
    }

    private  void initEvent() {

        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toFirstPageActivity();
            }
        });

        politicsImg.setOnClickListener(this);
        sportsImg.setOnClickListener(this);
        scienceImg.setOnClickListener(this);
        financeImg.setOnClickListener(this);
        militaryImg.setOnClickListener(this);
        carsImg.setOnClickListener(this);
        entertainmentImg.setOnClickListener(this);
        houseImg.setOnClickListener(this);
        gameImg.setOnClickListener(this);
        //默认给第一个ImageView加边框
        politicsImg.setImageResource(R.drawable.border);
        currentImage = politicsImg;
    }

    /**
     * 跳转到首页
     */
    private void toFirstPageActivity() {
        Intent intent = new Intent(this, FirstPageActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public void onClick(View v) {
        int tag = (Integer)v.getTag();
        for (int i=0; i<chosenTags.size();i++) {
            if (tag==chosenTags.get(i)) {
//                imageView.setImageDrawable(null);
                chosenTags.remove(i);
                cancelSelect((ImageView) v);
                return;
            }
        }
        chosenTags.add(tag);
//        imageView.setImageResource(R.drawable.border);
        select((ImageView) v);
    }
    /**
     * 给点击选中的ImageView加边框，若已选中则取消边框
     * @param imageView 要添加/取消边框的ImageView
     */
    public void select(ImageView imageView){

//        currentImage.setImageDrawable(null);
//        int tag = (Integer) imageView.getTag();
        imageView.setImageResource(R.drawable.border);
//        currentImage = imageView;
    }
    public void cancelSelect(ImageView imageView){
        imageView.setImageDrawable(null);
    }
}
