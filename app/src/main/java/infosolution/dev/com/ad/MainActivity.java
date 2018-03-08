package infosolution.dev.com.ad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private AdView mAdView;
    private Button btnFullscreenAd, btnShowRewardedVideoAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-4603905447363288~2422588137");

        btnFullscreenAd = (Button) findViewById(R.id.btn_fullscreen_ad);
        btnShowRewardedVideoAd = (Button) findViewById(R.id.btn_show_rewarded_video);

        mAdView = (AdView) findViewById(R.id.adView);
//        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });


        btnFullscreenAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Interstitial();
            }
        });

        btnShowRewardedVideoAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RewardedActivity.class));

            }
        });




    }

    @Override
    public void onPause() {


        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {

        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {

        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }





    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

    }

    private void Interstitial(){


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());






        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
    }


}