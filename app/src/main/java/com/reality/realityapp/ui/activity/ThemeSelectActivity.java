package com.reality.realityapp.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.reality.realityapp.R;

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
        sportsImg = (ImageView) findViewById(R.id.id_img_sports);
        scienceImg = (ImageView) findViewById(R.id.id_img_science);
        financeImg = (ImageView) findViewById(R.id.id_img_finance);
        militaryImg = (ImageView) findViewById(R.id.id_img_military);
        carsImg = (ImageView) findViewById(R.id.id_img_cars);
        entertainmentImg = (ImageView) findViewById(R.id.id_img_entertainment);
        houseImg = (ImageView) findViewById(R.id.id_img_house);
        gameImg = (ImageView) findViewById(R.id.id_img_game);
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
        handleImageView((ImageView) v);
    }
    /**
     * 给点击选中的ImageView加边框，并将之前的ImageView边框清除
     * @param imageView 要添加边框的ImageView
     */
    public void handleImageView(ImageView imageView){
        currentImage.setImageDrawable(null);
        imageView.setImageResource(R.drawable.border);
        currentImage = imageView;
    }
}
