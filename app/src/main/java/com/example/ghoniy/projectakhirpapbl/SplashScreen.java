package com.example.ghoniy.projectakhirpapbl;

import android.content.Intent;
import android.view.WindowManager;

import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashScreen extends AwesomeSplash {
    @Override
    public void initSplash(ConfigSplash configSplash) {
        configSplash.setBackgroundColor(R.color.colorSplash);

        //set up animasi
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        configSplash.setAnimCircularRevealDuration(500);
        configSplash.setRevealFlagX(Flags.REVEAL_LEFT);
        configSplash.setRevealFlagX(Flags.REVEAL_BOTTOM);

        //set up logo
        configSplash.setLogoSplash(R.drawable.logo);
        configSplash.setAnimLogoSplashDuration(1000);
        configSplash.setAnimLogoSplashTechnique(Techniques.FadeIn);

        //set up  title
        configSplash.setTitleSplash("Disease Guide");
        configSplash.setTitleTextColor(R.color.colorSplashText);
        configSplash.setTitleTextSize(33f);
        configSplash.setAnimTitleDuration(800);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
    }

    @Override
    public void animationsFinished() {
        //set intent to main activity
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }
}
