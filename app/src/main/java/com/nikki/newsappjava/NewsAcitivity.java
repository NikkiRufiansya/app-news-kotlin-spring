package com.nikki.newsappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;

import com.nikki.newsappjava.databinding.ActivityNewsAcitivityBinding;

public class NewsAcitivity extends AppCompatActivity {
    ActivityNewsAcitivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_news_acitivity);
        String image = getIntent().getStringExtra("image");
        String content = getIntent().getStringExtra("content");
        byte[] decodedString = Base64.decode(image, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        binding.ivDetail.setImageBitmap(decodedByte);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.tvContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT));
        } else {
            binding.tvContent.setText(Html.fromHtml(content));
        }
    }
}