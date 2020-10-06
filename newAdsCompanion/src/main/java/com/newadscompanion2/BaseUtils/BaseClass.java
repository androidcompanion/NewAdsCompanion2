package com.newadscompanion2.BaseUtils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.RewardedVideoAd;
import com.facebook.ads.RewardedVideoAdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.inmobi.ads.AdMetaInfo;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.listeners.BannerAdEventListener;
import com.inmobi.ads.listeners.InterstitialAdEventListener;
import com.inmobi.sdk.InMobiSdk;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.InterstitialListener;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.newadscompanion2.AdsConfig.DefaultIds;
import com.newadscompanion2.Interfaces.OnCheckServiceListner;
import com.newadscompanion2.Interfaces.OnPlayVerificationFailed;
import com.newadscompanion2.Interfaces.OnRewardAdClosedListener;
import com.newadscompanion2.ModelsCompanion.AdsData;
import com.newadscompanion2.ModelsCompanion.AdsIdsList;
import com.newadscompanion2.ModelsCompanion.AdsPrefernce;
import com.newadscompanion2.ModelsCompanion.GsonUtils;
import com.newadscompanion2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

import cz.msebera.android.httpclient.Header;

public class BaseClass extends AppCompatActivity {


    public RelativeLayout lay_notification;
    public TextView tv_not_text;
    public ImageView iv_not_close;

    public static boolean checkAppService = true;
    public Dialog serviceDialog;

    private long exitTime = 0;

    public DefaultIds defaultIds;

    public static boolean isAdsAvailable = false;
    public AdsPrefernce adsPrefernce;
    ArrayList<AdsIdsList> adsList;
    public GsonUtils gsonUtils;

    public InterstitialAd fbSplashInter;
    public com.google.android.gms.ads.InterstitialAd gSplashInter;
    public static InterstitialAd fbInterstitial1;
    public InterstitialAd fbInterstitial11;
    public static com.google.android.gms.ads.InterstitialAd gInterstitial1;
    public com.google.android.gms.ads.InterstitialAd gInterstitial11;
    public static InterstitialAd fbInterstitial2;
    public InterstitialAd fbInterstitial22;
    public static com.google.android.gms.ads.InterstitialAd gInterstitial2;
    public com.google.android.gms.ads.InterstitialAd gInterstitial22;
    public com.google.android.gms.ads.InterstitialAd gInterstitial3;
    com.facebook.ads.InterstitialAd fbDialogInterstitial;
    com.google.android.gms.ads.InterstitialAd gDialogInterstitial;
    public static InMobiInterstitial inMobiInterstitial1;
    public static InMobiInterstitial inMobiInterstitial2;
    public InMobiInterstitial inMobiInterstitial11;
    public InMobiInterstitial inMobiInterstitial22;

    //Rewarded Ads
    public static RewardedAd gRewardedAd;
    public static RewardedAdLoadCallback adLoadCallback;
    public static RewardedVideoAd fbRewardedVideoAd;
    public static RewardedVideoAdListener rewardedVideoAdListener;
    public static InMobiInterstitial imRewardedAd;
    public static InterstitialAdEventListener mRewardAdEventListener;
    public static RewardedVideoListener isRewardedVideoListener;

    public static boolean isGRewardedShown = false;
    public static boolean isFbRewardedShown = false;
    public static boolean isImRewardedShown = false;
    public static boolean isIsRewardedShown = false;

    public static boolean isGRewardedReady = false;
    public static boolean isFbRewardedReady = false;
    public static boolean isImRewardedReady = false;
    public static boolean isIsRewardedReady = false;

    public static boolean isGUserRewarded = false;
    public static boolean isfbUserRewarded = false;
    public static boolean isImUserRewarded = false;
    public static boolean isIsUserRewarded = false;


    public static boolean mpInter1Initilized = false;
    private static boolean mpInter2Initilized = false;

    //mp banner
    private static boolean mpBannerInitilized = false;


    public ProgressDialog progressDialog;
    OnPlayVerificationFailed onPlayVerificationFailed;
    OnRewardAdClosedListener onRewardAdClosedListener;


    public static boolean isGInter1Ready = false;
    public static boolean isFbInter1Ready = false;
    public static boolean isAnInter1Ready = false;
    public static boolean isMpInter1Ready = false;
    public static boolean isIsInter1Ready = false;
    public static boolean isImInter1Ready = false;
    public static boolean isGInter2Ready = false;
    public static boolean isFbInter2Ready = false;
    //    public static boolean isAnInter2Ready = false;
    public static boolean isImInter2Ready = false;
    public static boolean isIsInter2Ready = false;
    public static boolean isGInter1Shown = false;
    public static boolean isFbInter1Shown = false;
    public static boolean isAnInter1Shown = false;
    public static boolean isMpInter1Shown = false;
    public static boolean isIsInter1Shown = false;
    public static boolean isImInter1Shown = false;
    public static boolean isGInter2Shown = false;
    public static boolean isFbInter2Shown = false;
    public static boolean isAnInter2Shown = false;
    public static boolean isMpInter2Shown = false;
    public static boolean isImInter2Shown = false;
    public static boolean isIsInter2Shown = false;

    public static boolean isGN1Shown = false;
    public static boolean isFbN1Shown = false;
    public static boolean isAnN1Shown = false;
    public static boolean isMpN1Shown = false;
    public static boolean isGN2Shown = false;
    public static boolean isFbN2Shown = false;
    public static boolean isAnN2Shown = false;
    public static boolean isMpN2Shown = false;

    public static boolean isvalidInstall = false;

    public static boolean inMobiInitialized = false;

    public void loadRewardAd() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                Log.e("RewardAds...", "planA");
                if (adsPrefernce.showgRewarded()) {
                    Log.e("RewardAds...", "showgRewarded true");
                    if (!isGRewardedReady) {
                        Log.e("RewardAds...", "google1 not ready");
                        MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                        gRewardedAd = new RewardedAd(this,
                                adsPrefernce.gRewardedId());
                        adLoadCallback = new RewardedAdLoadCallback() {
                            @Override
                            public void onRewardedAdLoaded() {
                                isGRewardedReady = true;
                                Log.e("RewardAds...", "google RewardAd ready");
                            }

                            @Override
                            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                                isGRewardedReady = false;
                            }
                        };
                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                    }
                }
                if (adsPrefernce.showfbRewarded()) {
                    if (!isFbRewardedReady) {
                        AudienceNetworkAds.initialize(this);
                        fbRewardedVideoAd = new RewardedVideoAd(this, adsPrefernce.fbRewardedId());
                        rewardedVideoAdListener = new RewardedVideoAdListener() {
                            @Override
                            public void onError(Ad ad, AdError error) {
                                // Rewarded video ad failed to load
                                Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
                                isFbRewardedReady = false;
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                // Rewarded video ad is loaded and ready to be displayed
                                Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
                                isFbRewardedReady = true;
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                // Rewarded video ad clicked
                                Log.d("RewardAds...", "Rewarded video ad clicked!");
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                // Rewarded Video ad impression - the event will fire when the
                                // video starts playing
                                Log.d("RewardAds...", "Rewarded video ad impression logged!");
                            }

                            @Override
                            public void onRewardedVideoCompleted() {
                                // Rewarded Video View Complete - the video has been played to the end.
                                // You can use this event to initialize your reward
                                Log.d("RewardAds...", "Rewarded video completed!");

                                // Call method to give reward
                                // giveReward();
                            }

                            @Override
                            public void onRewardedVideoClosed() {
                                // The Rewarded Video ad was closed - this can occur during the video
                                // by closing the app, or closing the end card.
                                Log.d("RewardAds...", "Rewarded video ad closed!");
                            }
                        };
                        fbRewardedVideoAd.loadAd(
                                fbRewardedVideoAd.buildLoadAdConfig()
                                        .withAdListener(rewardedVideoAdListener)
                                        .build());
                    }
                }
                if (adsPrefernce.showimRewarded()) {
                    if (!isImRewardedReady) {
                        if (inMobiInitialized) {
                            mRewardAdEventListener = new InterstitialAdEventListener() {
                                @Override
                                public void onAdFetchFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdFetchFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    isImRewardedReady = false;
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDisplayFailed(inMobiInterstitial);
                                }

                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                }

                                @Override
                                public void onRewardsUnlocked(@NonNull InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                                    super.onRewardsUnlocked(inMobiInterstitial, map);
                                }

                                @Override
                                public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                    isImRewardedReady = true;
                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                }
                            };
                            imRewardedAd = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
                            imRewardedAd.load();
                        }

                    }

                }
                if (adsPrefernce.showisRewarded()) {
                    if (!isIsRewardedReady) {
                        isRewardedVideoListener = new RewardedVideoListener() {
                            @Override
                            public void onRewardedVideoAdOpened() {

                            }

                            @Override
                            public void onRewardedVideoAdClosed() {

                            }

                            @Override
                            public void onRewardedVideoAvailabilityChanged(boolean b) {
                                isIsRewardedReady = IronSource.isRewardedVideoAvailable();
                            }

                            @Override
                            public void onRewardedVideoAdStarted() {

                            }

                            @Override
                            public void onRewardedVideoAdEnded() {

                            }

                            @Override
                            public void onRewardedVideoAdRewarded(Placement placement) {

                            }

                            @Override
                            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {

                            }

                            @Override
                            public void onRewardedVideoAdClicked(Placement placement) {

                            }
                        };
                        IronSource.setRewardedVideoListener(isRewardedVideoListener);
                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);

                    }
                }
            }
        }

    }

    public void showRewardAds(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.showgRewarded()) {
                    if (!isGRewardedShown) {
                        if (isGRewardedReady) {
                            if (gRewardedAd.isLoaded()) {
                                Log.d("RewardAds...", "gRewardedAd.isLoaded() = true");
                                RewardedAdCallback adCallback = new RewardedAdCallback() {
                                    @Override
                                    public void onRewardedAdOpened() {
                                        // Ad opened.
                                    }

                                    @Override
                                    public void onRewardedAdClosed() {
                                        Log.d("RewardAds...", "onRewardedAdClosed Google");
                                        // Ad closed.
                                        if (isGUserRewarded) {
                                            try {
                                                onRewardAdClosedListener.onRewardSuccess();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            isGRewardedReady = false;
                                            isGRewardedShown = true;
                                            isGUserRewarded = false;
                                            gRewardedAd = new RewardedAd(BaseClass.this,
                                                    adsPrefernce.gRewardedId());
                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                        } else {
                                            try {
                                                onRewardAdClosedListener.onRewardFailed();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            isGRewardedReady = false;
                                            isGRewardedShown = true;
                                            isGUserRewarded = false;
                                            gRewardedAd = new RewardedAd(BaseClass.this,
                                                    adsPrefernce.gRewardedId());
                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                        }
                                    }

                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem reward) {
                                        // User earned reward.
                                        isGUserRewarded = true;
                                        Log.d("RewardAds...", "onUserEarnedReward Google");

                                    }

                                    @Override
                                    public void onRewardedAdFailedToShow(com.google.android.gms.ads.AdError adError) {
                                        Log.d("RewardAds...", "onRewardedAdFailedToShow Google");
                                        // Ad failed to display.
                                        isGRewardedReady = false;
                                        isGRewardedShown = true;
                                        isGUserRewarded = false;
                                        gRewardedAd = new RewardedAd(BaseClass.this,
                                                adsPrefernce.gRewardedId());
                                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
                                    }
                                };
                                gRewardedAd.show(this, adCallback);
                            }
                        } else {
                            goToPlanA2Rewarded(onRewardAdClosedListener);
                        }
                    } else {
                        goToPlanA2Rewarded(onRewardAdClosedListener);
                    }
                } else {
                    goToPlanA2Rewarded(onRewardAdClosedListener);
                }
            }
        }

    }

    public void goToPlanA2Rewarded(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (adsPrefernce.showfbRewarded()) {
            if (!isFbRewardedShown) {
                if (isFbRewardedReady) {
                    if (fbRewardedVideoAd == null || !fbRewardedVideoAd.isAdLoaded()) {
                        goToPlanDRewarded(onRewardAdClosedListener);
                        return;
                    }
                    // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                    if (fbRewardedVideoAd.isAdInvalidated()) {
                        goToPlanDRewarded(onRewardAdClosedListener);
                        return;
                    }
                    fbRewardedVideoAd.show();
                    RewardedVideoAdListener rewardedVideoAdListenerShow = new RewardedVideoAdListener() {
                        @Override
                        public void onError(Ad ad, AdError error) {
                            // Rewarded video ad failed to load
                            Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
                            isFbRewardedReady = false;
                            isFbBannerShown = true;
                            isfbUserRewarded = false;
                            fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                            fbRewardedVideoAd.loadAd(
                                    fbRewardedVideoAd.buildLoadAdConfig()
                                            .withAdListener(rewardedVideoAdListener)
                                            .build());
                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            // Rewarded video ad is loaded and ready to be displayed
                            Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
                        }

                        @Override
                        public void onAdClicked(Ad ad) {
                            // Rewarded video ad clicked
                            Log.d("RewardAds...", "Rewarded video ad clicked!");
                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {
                            // Rewarded Video ad impression - the event will fire when the
                            // video starts playing
                            Log.d("RewardAds...", "Rewarded video ad impression logged!");
                        }

                        @Override
                        public void onRewardedVideoCompleted() {
                            // Rewarded Video View Complete - the video has been played to the end.
                            // You can use this event to initialize your reward
                            Log.d("RewardAds...", "Rewarded video completed!");
                            isfbUserRewarded = true;

                            // Call method to give reward
                            // giveReward();
                        }

                        @Override
                        public void onRewardedVideoClosed() {
                            // The Rewarded Video ad was closed - this can occur during the video
                            // by closing the app, or closing the end card.
                            Log.d("RewardAds...", "Rewarded video ad closed!");
                            if (isfbUserRewarded) {
                                try {
                                    onRewardAdClosedListener.onRewardSuccess();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                isFbRewardedReady = false;
                                isFbRewardedShown = true;
                                isfbUserRewarded = false;
                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                                fbRewardedVideoAd.loadAd(
                                        fbRewardedVideoAd.buildLoadAdConfig()
                                                .withAdListener(rewardedVideoAdListener)
                                                .build());
                            } else {
                                try {
                                    onRewardAdClosedListener.onRewardFailed();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                isFbRewardedReady = false;
                                isFbRewardedShown = true;
                                isfbUserRewarded = false;
                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
                                fbRewardedVideoAd.loadAd(
                                        fbRewardedVideoAd.buildLoadAdConfig()
                                                .withAdListener(rewardedVideoAdListener)
                                                .build());
                            }

                        }
                    };
                    fbRewardedVideoAd.setAdListener(rewardedVideoAdListenerShow);

                } else {
                    goToPlanDRewarded(onRewardAdClosedListener);
                }
            } else {
                goToPlanDRewarded(onRewardAdClosedListener);
            }
        } else {
            goToPlanDRewarded(onRewardAdClosedListener);
        }
    }

    public void goToPlanDRewarded(OnRewardAdClosedListener onRewardAdClosedListener) {

        if (adsPrefernce.showimRewarded()) {
            if (!isImRewardedShown) {
                if (isImRewardedReady) {
                    if (imRewardedAd.isReady()) {
                        imRewardedAd.show();
                        imRewardedAd.setListener(new InterstitialAdEventListener() {
                            @Override
                            public void onAdFetchFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                super.onAdFetchFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                isImRewardedReady = false;
                            }

                            @Override
                            public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                            }

                            @Override
                            public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                super.onAdDisplayFailed(inMobiInterstitial);
                                isImRewardedReady = false;
                                isImRewardedShown = true;
                                isImUserRewarded = false;
                                imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
                                imRewardedAd.load();
                            }

                            @Override
                            public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                super.onAdDismissed(inMobiInterstitial);
                                if (isImUserRewarded) {
                                    try {
                                        onRewardAdClosedListener.onRewardSuccess();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isImRewardedReady = false;
                                    isImRewardedShown = true;
                                    isImUserRewarded = false;
                                    imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
                                    imRewardedAd.load();
                                } else {
                                    try {
                                        onRewardAdClosedListener.onRewardFailed();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isImRewardedReady = false;
                                    isImRewardedShown = true;
                                    isImUserRewarded = false;
                                    imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
                                    imRewardedAd.load();
                                }
                            }

                            @Override
                            public void onRewardsUnlocked(@NonNull InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                                super.onRewardsUnlocked(inMobiInterstitial, map);
                                isImUserRewarded = true;
                            }

                        });
                    } else {
                        goToPlanERewarded(onRewardAdClosedListener);
                    }
                } else {
                    goToPlanERewarded(onRewardAdClosedListener);
                }
            } else {
                goToPlanERewarded(onRewardAdClosedListener);
            }
        } else {
            goToPlanERewarded(onRewardAdClosedListener);
        }

    }

    public void goToPlanERewarded(OnRewardAdClosedListener onRewardAdClosedListener) {
        if (adsPrefernce.showisRewarded()) {
            if (!isIsInter1Shown) {
                if (isIsInter1Ready) {
                    if (IronSource.isRewardedVideoAvailable()) {
                        IronSource.showRewardedVideo();
                        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                            @Override
                            public void onRewardedVideoAdOpened() {

                            }

                            @Override
                            public void onRewardedVideoAdClosed() {
                                if (isIsUserRewarded) {
                                    try {
                                        onRewardAdClosedListener.onRewardSuccess();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isIsRewardedReady = false;
                                    isIsRewardedShown = true;
                                    isIsUserRewarded = false;
                                    IronSource.setRewardedVideoListener(isRewardedVideoListener);
                                    IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
                                    resetAllRewardedShownBoolean();
                                } else {
                                    try {
                                        onRewardAdClosedListener.onRewardFailed();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    isIsRewardedReady = false;
                                    isIsRewardedShown = true;
                                    isIsUserRewarded = false;
                                    IronSource.setRewardedVideoListener(isRewardedVideoListener);
                                    IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
                                    resetAllRewardedShownBoolean();
                                }
                            }

                            @Override
                            public void onRewardedVideoAvailabilityChanged(boolean b) {

                            }

                            @Override
                            public void onRewardedVideoAdStarted() {

                            }

                            @Override
                            public void onRewardedVideoAdEnded() {

                            }

                            @Override
                            public void onRewardedVideoAdRewarded(Placement placement) {
                                isIsUserRewarded = true;
                            }

                            @Override
                            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
                                isIsRewardedReady = false;
                                isIsRewardedShown = true;
                                isIsUserRewarded = false;
                                IronSource.setRewardedVideoListener(isRewardedVideoListener);
                                IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
                                resetAllRewardedShownBoolean();
                            }

                            @Override
                            public void onRewardedVideoAdClicked(Placement placement) {

                            }
                        });
                    } else {
                        resetAllRewardedShownBoolean();
                    }
                } else {
                    resetAllRewardedShownBoolean();
                }
            } else {
                resetAllRewardedShownBoolean();
            }
        } else {
            resetAllRewardedShownBoolean();
        }

    }

    public void resetAllRewardedShownBoolean() {
        isGRewardedShown = false;
        isFbRewardedShown = false;
        isImRewardedShown = false;
        isIsRewardedShown = false;
    }


//    public void loadRewardAd() {
//        adsPrefernce = new AdsPrefernce(this);
//        if (isNetworkAvailable(this)) {
//            if (isAdsAvailable) {
//                Log.e("RewardAds...", "planA");
//                if (adsPrefernce.showgRewarded()) {
//                    Log.e("RewardAds...", "showgRewarded true");
//                    if (!isGRewardedReady) {
//                        Log.e("RewardAds...", "google1 not ready");
//                        MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
//                        gRewardedAd = new RewardedAd(this,
//                                adsPrefernce.gRewardedId());
//                        adLoadCallback = new RewardedAdLoadCallback() {
//                            @Override
//                            public void onRewardedAdLoaded() {
//                                isGRewardedReady = true;
//                                Log.e("RewardAds...", "google RewardAd ready");
//                            }
//
//                            @Override
//                            public void onRewardedAdFailedToLoad(LoadAdError adError) {
//                                isGRewardedReady = false;
//                            }
//                        };
//                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//                    }
//                }
//                if (adsPrefernce.showfbRewarded()) {
//                    if (!isFbRewardedReady) {
//                        AudienceNetworkAds.initialize(this);
//                        fbRewardedVideoAd = new RewardedVideoAd(this, adsPrefernce.fbRewardedId());
//                        rewardedVideoAdListener = new RewardedVideoAdListener() {
//                            @Override
//                            public void onError(Ad ad, AdError error) {
//                                // Rewarded video ad failed to load
//                                Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
//                                isFbRewardedReady = false;
//                            }
//
//                            @Override
//                            public void onAdLoaded(Ad ad) {
//                                // Rewarded video ad is loaded and ready to be displayed
//                                Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
//                                isFbRewardedReady = true;
//                            }
//
//                            @Override
//                            public void onAdClicked(Ad ad) {
//                                // Rewarded video ad clicked
//                                Log.d("RewardAds...", "Rewarded video ad clicked!");
//                            }
//
//                            @Override
//                            public void onLoggingImpression(Ad ad) {
//                                // Rewarded Video ad impression - the event will fire when the
//                                // video starts playing
//                                Log.d("RewardAds...", "Rewarded video ad impression logged!");
//                            }
//
//                            @Override
//                            public void onRewardedVideoCompleted() {
//                                // Rewarded Video View Complete - the video has been played to the end.
//                                // You can use this event to initialize your reward
//                                Log.d("RewardAds...", "Rewarded video completed!");
//
//                                // Call method to give reward
//                                // giveReward();
//                            }
//
//                            @Override
//                            public void onRewardedVideoClosed() {
//                                // The Rewarded Video ad was closed - this can occur during the video
//                                // by closing the app, or closing the end card.
//                                Log.d("RewardAds...", "Rewarded video ad closed!");
//                            }
//                        };
//                        fbRewardedVideoAd.loadAd(
//                                fbRewardedVideoAd.buildLoadAdConfig()
//                                        .withAdListener(rewardedVideoAdListener)
//                                        .build());
//                    }
//                }
//                if (adsPrefernce.showimRewarded()) {
//                    if (!isImRewardedReady) {
//                        if (inMobiInitialized) {
//                            mRewardAdEventListener = new InterstitialAdEventListener() {
//                                @Override
//                                public void onAdFetchFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
//                                    super.onAdFetchFailed(inMobiInterstitial, inMobiAdRequestStatus);
//                                    isImRewardedReady = false;
//                                }
//
//                                @Override
//                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
//                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
//                                }
//
//                                @Override
//                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
//                                    super.onAdDisplayFailed(inMobiInterstitial);
//                                }
//
//                                @Override
//                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
//                                    super.onAdDismissed(inMobiInterstitial);
//                                }
//
//                                @Override
//                                public void onRewardsUnlocked(@NonNull InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
//                                    super.onRewardsUnlocked(inMobiInterstitial, map);
//                                }
//
//                                @Override
//                                public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
//                                    super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
//                                    isImRewardedReady = true;
//                                }
//
//                                @Override
//                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
//                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
//                                }
//                            };
//                            imRewardedAd = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
//                            imRewardedAd.load();
//                        }
//
//                    }
//
//                }
//                if (adsPrefernce.showisRewarded()) {
//                    if (!isIsRewardedReady) {
//                        isRewardedVideoListener = new RewardedVideoListener() {
//                            @Override
//                            public void onRewardedVideoAdOpened() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdClosed() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAvailabilityChanged(boolean b) {
//                                isIsRewardedReady = IronSource.isRewardedVideoAvailable();
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdStarted() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdEnded() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdRewarded(Placement placement) {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdClicked(Placement placement) {
//
//                            }
//                        };
//                        IronSource.setRewardedVideoListener(isRewardedVideoListener);
//                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
//
//                    }
//                }
//            }
//        }
//
//    }
//
//    public void showRewardAds(Callable<Void> taskToPerform,Callable<Void> taskIfNotRewarded) {
//        if (isNetworkAvailable(this)) {
//            if (isAdsAvailable) {
//                if (adsPrefernce.showgRewarded()) {
//                    if (!isGRewardedShown) {
//                        if (isGRewardedReady) {
//                            if (gRewardedAd.isLoaded()) {
//                                Log.d("RewardAds...", "gRewardedAd.isLoaded() = true");
//                                RewardedAdCallback adCallback = new RewardedAdCallback() {
//                                    @Override
//                                    public void onRewardedAdOpened() {
//                                        // Ad opened.
//                                    }
//
//                                    @Override
//                                    public void onRewardedAdClosed() {
//                                        Log.d("RewardAds...", "onRewardedAdClosed Google");
//                                        // Ad closed.
//                                        if (isGUserRewarded) {
//                                            try {
//                                                taskToPerform.call();
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            isGRewardedReady = false;
//                                            isGRewardedShown = true;
//                                            isGUserRewarded = false;
//                                            gRewardedAd = new RewardedAd(BaseClass.this,
//                                                    adsPrefernce.gRewardedId());
//                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//                                        } else {
//                                            try {
//                                                taskIfNotRewarded.call();
//                                            } catch (Exception e) {
//                                                e.printStackTrace();
//                                            }
//                                            isGRewardedReady = false;
//                                            isGRewardedShown = true;
//                                            isGUserRewarded = false;
//                                            gRewardedAd = new RewardedAd(BaseClass.this,
//                                                    adsPrefernce.gRewardedId());
//                                            gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onUserEarnedReward(@NonNull RewardItem reward) {
//                                        // User earned reward.
//                                        isGUserRewarded = true;
//                                        Log.d("RewardAds...", "onUserEarnedReward Google");
//
//                                    }
//
//                                    @Override
//                                    public void onRewardedAdFailedToShow(com.google.android.gms.ads.AdError adError) {
//                                        Log.d("RewardAds...", "onRewardedAdFailedToShow Google");
//                                        // Ad failed to display.
//                                        isGRewardedReady = false;
//                                        isGRewardedShown = true;
//                                        isGUserRewarded = false;
//                                        gRewardedAd = new RewardedAd(BaseClass.this,
//                                                adsPrefernce.gRewardedId());
//                                        gRewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
//                                    }
//                                };
//                                gRewardedAd.show(this, adCallback);
//                            }
//                        } else {
//                            goToPlanA2Rewarded(taskToPerform,taskIfNotRewarded);
//                        }
//                    } else {
//                        goToPlanA2Rewarded(taskToPerform,taskIfNotRewarded);
//                    }
//                } else {
//                    goToPlanA2Rewarded(taskToPerform,taskIfNotRewarded);
//                }
//            }
//        }
//
//    }
//
//    public void goToPlanA2Rewarded(Callable<Void> taskToPerform,Callable<Void> taskIfNotRewarded) {
//        if (adsPrefernce.showfbRewarded()) {
//            if (!isFbRewardedShown) {
//                if (isFbRewardedReady) {
//                    if (fbRewardedVideoAd == null || !fbRewardedVideoAd.isAdLoaded()) {
//                        goToPlanDRewarded(taskToPerform,taskIfNotRewarded);
//                        return;
//                    }
//                    // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
//                    if (fbRewardedVideoAd.isAdInvalidated()) {
//                        goToPlanDRewarded(taskToPerform,taskIfNotRewarded);
//                        return;
//                    }
//                    fbRewardedVideoAd.show();
//                    RewardedVideoAdListener rewardedVideoAdListenerShow = new RewardedVideoAdListener() {
//                        @Override
//                        public void onError(Ad ad, AdError error) {
//                            // Rewarded video ad failed to load
//                            Log.e("RewardAds...", "Rewarded video ad failed to load: " + error.getErrorMessage());
//                            isFbRewardedReady = false;
//                            isFbBannerShown = true;
//                            isfbUserRewarded = false;
//                            fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
//                            fbRewardedVideoAd.loadAd(
//                                    fbRewardedVideoAd.buildLoadAdConfig()
//                                            .withAdListener(rewardedVideoAdListener)
//                                            .build());
//                        }
//
//                        @Override
//                        public void onAdLoaded(Ad ad) {
//                            // Rewarded video ad is loaded and ready to be displayed
//                            Log.d("RewardAds...", "Rewarded video ad is loaded and ready to be displayed!");
//                        }
//
//                        @Override
//                        public void onAdClicked(Ad ad) {
//                            // Rewarded video ad clicked
//                            Log.d("RewardAds...", "Rewarded video ad clicked!");
//                        }
//
//                        @Override
//                        public void onLoggingImpression(Ad ad) {
//                            // Rewarded Video ad impression - the event will fire when the
//                            // video starts playing
//                            Log.d("RewardAds...", "Rewarded video ad impression logged!");
//                        }
//
//                        @Override
//                        public void onRewardedVideoCompleted() {
//                            // Rewarded Video View Complete - the video has been played to the end.
//                            // You can use this event to initialize your reward
//                            Log.d("RewardAds...", "Rewarded video completed!");
//                            isfbUserRewarded = true;
//
//                            // Call method to give reward
//                            // giveReward();
//                        }
//
//                        @Override
//                        public void onRewardedVideoClosed() {
//                            // The Rewarded Video ad was closed - this can occur during the video
//                            // by closing the app, or closing the end card.
//                            Log.d("RewardAds...", "Rewarded video ad closed!");
//                            if (isfbUserRewarded) {
//                                try {
//                                    taskToPerform.call();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                isFbRewardedReady = false;
//                                isFbRewardedShown = true;
//                                isfbUserRewarded = false;
//                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
//                                fbRewardedVideoAd.loadAd(
//                                        fbRewardedVideoAd.buildLoadAdConfig()
//                                                .withAdListener(rewardedVideoAdListener)
//                                                .build());
//                            } else {
//                                try {
//                                    taskIfNotRewarded.call();
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                isFbRewardedReady = false;
//                                isFbRewardedShown = true;
//                                isfbUserRewarded = false;
//                                fbRewardedVideoAd = new RewardedVideoAd(BaseClass.this, adsPrefernce.fbRewardedId());
//                                fbRewardedVideoAd.loadAd(
//                                        fbRewardedVideoAd.buildLoadAdConfig()
//                                                .withAdListener(rewardedVideoAdListener)
//                                                .build());
//                            }
//
//                        }
//                    };
//                    fbRewardedVideoAd.setAdListener(rewardedVideoAdListenerShow);
//
//                } else {
//                    goToPlanDRewarded(taskToPerform,taskIfNotRewarded);
//                }
//            } else {
//                goToPlanDRewarded(taskToPerform,taskIfNotRewarded);
//            }
//        } else {
//            goToPlanDRewarded(taskToPerform,taskIfNotRewarded);
//        }
//    }
//
//    public void goToPlanDRewarded(Callable<Void> taskToPerform,Callable<Void> taskIfNotRewarded) {
//
//        if (adsPrefernce.showimRewarded()) {
//            if (!isImRewardedShown) {
//                if (isImRewardedReady) {
//                    if (imRewardedAd.isReady()) {
//                        imRewardedAd.show();
//                        imRewardedAd.setListener(new InterstitialAdEventListener() {
//                            @Override
//                            public void onAdFetchFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
//                                super.onAdFetchFailed(inMobiInterstitial, inMobiAdRequestStatus);
//                                isImRewardedReady = false;
//                            }
//
//                            @Override
//                            public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
//                                super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
//                            }
//
//                            @Override
//                            public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
//                                super.onAdDisplayFailed(inMobiInterstitial);
//                                isImRewardedReady = false;
//                                isImRewardedShown = true;
//                                isImUserRewarded = false;
//                                imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
//                                imRewardedAd.load();
//                            }
//
//                            @Override
//                            public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
//                                super.onAdDismissed(inMobiInterstitial);
//                                if (isImUserRewarded) {
//                                    try {
//                                        taskToPerform.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    isImRewardedReady = false;
//                                    isImRewardedShown = true;
//                                    isImUserRewarded = false;
//                                    imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
//                                    imRewardedAd.load();
//                                } else {
//                                    try {
//                                        taskIfNotRewarded.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    isImRewardedReady = false;
//                                    isImRewardedShown = true;
//                                    isImUserRewarded = false;
//                                    imRewardedAd = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_REWARDED()), mRewardAdEventListener);
//                                    imRewardedAd.load();
//                                }
//                            }
//
//                            @Override
//                            public void onRewardsUnlocked(@NonNull InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
//                                super.onRewardsUnlocked(inMobiInterstitial, map);
//                                isImUserRewarded = true;
//                            }
//
//                        });
//                    } else {
//                        goToPlanERewarded(taskToPerform,taskIfNotRewarded);
//                    }
//                } else {
//                    goToPlanERewarded(taskToPerform,taskIfNotRewarded);
//                }
//            } else {
//                goToPlanERewarded(taskToPerform,taskIfNotRewarded);
//            }
//        } else {
//            goToPlanERewarded(taskToPerform,taskIfNotRewarded);
//        }
//
//    }
//
//    public void goToPlanERewarded(Callable<Void> taskToPerform,Callable<Void> taskIfNotRewarded) {
//        if (adsPrefernce.showisRewarded()) {
//            if (!isIsInter1Shown) {
//                if (isIsInter1Ready) {
//                    if (IronSource.isRewardedVideoAvailable()) {
//                        IronSource.showRewardedVideo();
//                        IronSource.setRewardedVideoListener(new RewardedVideoListener() {
//                            @Override
//                            public void onRewardedVideoAdOpened() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdClosed() {
//                                if (isIsUserRewarded) {
//                                    try {
//                                        taskToPerform.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    isIsRewardedReady = false;
//                                    isIsRewardedShown = true;
//                                    isIsUserRewarded = false;
//                                    IronSource.setRewardedVideoListener(isRewardedVideoListener);
//                                    IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
//                                    resetAllRewardedShownBoolean();
//                                } else {
//                                    try {
//                                        taskIfNotRewarded.call();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    isIsRewardedReady = false;
//                                    isIsRewardedShown = true;
//                                    isIsUserRewarded = false;
//                                    IronSource.setRewardedVideoListener(isRewardedVideoListener);
//                                    IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
//                                    resetAllRewardedShownBoolean();
//                                }
//                            }
//
//                            @Override
//                            public void onRewardedVideoAvailabilityChanged(boolean b) {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdStarted() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdEnded() {
//
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdRewarded(Placement placement) {
//                                isIsUserRewarded = true;
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdShowFailed(IronSourceError ironSourceError) {
//                                isIsRewardedReady = false;
//                                isIsRewardedShown = true;
//                                isIsUserRewarded = false;
//                                IronSource.setRewardedVideoListener(isRewardedVideoListener);
//                                IronSource.init(BaseClass.this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.REWARDED_VIDEO);
//                                resetAllRewardedShownBoolean();
//                            }
//
//                            @Override
//                            public void onRewardedVideoAdClicked(Placement placement) {
//
//                            }
//                        });
//                    } else {
//                        resetAllRewardedShownBoolean();
//                    }
//                } else {
//                    resetAllRewardedShownBoolean();
//                }
//            } else {
//                resetAllRewardedShownBoolean();
//            }
//        } else {
//            resetAllRewardedShownBoolean();
//        }
//
//    }
//
//    public void resetAllRewardedShownBoolean() {
//        isGRewardedShown = false;
//        isFbRewardedShown = false;
//        isImRewardedShown = false;
//        isIsRewardedShown = false;
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        defaultIds = new DefaultIds(this);
        adsPrefernce = new AdsPrefernce(this);

//        loadRewardAd();
        isvalidInstall = verifyInstallerId(this);
        Log.e("validation", String.valueOf(isvalidInstall));


        // initialize inmobi sdk
        if (!inMobiInitialized) {
            //inMobi User Consent(Needed to provide cosent for Every Session)
            JSONObject consentObject = new JSONObject();
            int gdpr;
            try {
                // Provide correct consent value to sdk which is obtained by User
                consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, adsPrefernce.getConsent());
                // Provide 0 if GDPR is not applicable and 1 if applicable
                if (adsPrefernce.getConsent()) {
                    gdpr = 1;
                } else {
                    gdpr = 0;
                }
                consentObject.put("gdpr", gdpr);
                // Provide user consent in IAB format
                // consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, “<<consent in IAB format>>”);
                InMobiSdk.updateGDPRConsent(consentObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            InMobiSdk.init(this, defaultIds.IM_ACCOUNT_ID(), consentObject, new com.inmobi.sdk.SdkInitializationListener() {
                @Override
                public void onInitializationComplete(@Nullable Error error) {
                    if (null == error) {
                        inMobiInitialized = true;
//                        Log.d("INMOBI INIT LISTNER", "InMobi Init failed -" + error.getMessage());
                    } else {
//                        Log.d("INMOBI INIT LISTNER", "InMobi Init Successful");
                    }
                }
            });

        }


        // initialize startapp sdk
//        StartAppSDK.init(BaseClass.this, defaultIds.SA_APP_ID(), false);
//        StartAppAd.enableConsent(this, false);
//        //disable startapp splash
//        if (defaultIds.DISABLE_SA_SPLASH()) {
//            StartAppAd.disableSplash();
//        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isNetworkAvailable(BaseClass.this)) {
                    if (!isAdsAvailable) {
                        Log.e("Ads...", "call get Ads");
                        getAds(defaultIds.APP_KEY());
                    }
                }
            }
        }, 500);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void getAds(final String appKey) {

        adsList = new ArrayList<AdsIdsList>();
        AsyncHttpClient client = new AsyncHttpClient();
        gsonUtils = GsonUtils.getInstance();
        RequestParams params1 = new RequestParams();
        params1.put("app_key", appKey);
        try {

            client.setConnectTimeout(50000);

            client.post("http://developercompanion.get-fans-for-musically.com/iapi/ads_service2.php", params1, new BaseJsonHttpResponseHandler<AdsData>() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, String rawJsonResponse, AdsData response) {

                    Log.e("Ads...", "Success");

                    adsList.clear();
                    adsList = new ArrayList<>();

                    if (response.getSuccess() == 0) {
                        Log.e("Ads...", "Success = 0");

                        isAdsAvailable = true;
                        if (adsPrefernce.isMediationActive()) {
                            loadMixedInterAds();
                        }
                        return;
                    }

                    adsList.addAll(response.getAdsIdsList());
                    Log.e("Ads...", "Success = 1");


                    AdsIdsList ads = adsList.get(0);

                    //add data to shared_preference
                    adsPrefernce = new AdsPrefernce(BaseClass.this);
                    adsPrefernce.setAdsDefaults(ads.getShowAds(), ads.getShowLoading(), ads.getAllowAccess(), ads.getMediation(), ads.getAd1(), ads.getAd2(), ads.getAd3(), ads.getAd4(), ads.getAd5(), ads.getAd6(),
                            ads.getGAppId(), ads.getGBanner(), ads.getShowGbanner(), ads.getGInter1(), ads.getShowGinter1(), ads.getGInter2(), ads.getShowGinter2(),
                            ads.getGRewarded(), ads.getShowGrewarded(), ads.getGNative1(), ads.getShowGnative1(), ads.getGNative2(), ads.getShowGnative2(),

                            ads.getFbBanner(), ads.getShowFbbanner(), ads.getFbInter1(), ads.getShowFbinter1(), ads.getFbInter2(), ads.getShowFbinter2(),
                            ads.getFbRewarded(), ads.getShowFbRewarded(), ads.getFbNative1(), ads.getShowFbnative1(), ads.getFbNative2(), ads.getShowFbnative2(),
                            ads.getFbNativebanner(), ads.getFbNativebanner(),

                            ads.getAnAdId(), ads.getShowAnbanner(), ads.getShowAninter1(), ads.getShowAninter2(),
                            ads.getShowAnnative1(), ads.getShowAnnative2(), ads.getShowAnrewarded(),

                            ads.getMpBanner(), ads.getShowMpbanner(), ads.getMpInter1(), ads.getShowMpinter1(), ads.getMpInter2(), ads.getShowMpinter2(),
                            ads.getMpNative1(), ads.getShowMpnative1(), ads.getMpNative2(), ads.getShowMpnative2(), ads.getMpRewarded(), ads.getShowMprewarded(),

                            ads.getShowIsbanner(), ads.getShowIsinter1(), ads.getShowIsinter2(), ads.getShowIsofferwall(), ads.getShowIsrewarded(),

                            ads.getShowImbanner(), ads.getShowIminter1(), ads.getShowIminter2(), ads.getShowImnative1(), ads.getShowImnative2(), ads.getShowImrewarded(),
                            ads.getExtraPara1(), ads.getExtraPara2(), ads.getExtraPara3(), ads.getExtraPara4(),

                            ads.getSaAdCount()
                    );

                    loadRewardAd();

//                    initializeMoPubSDKforInter1(adsPrefernce.mpInterId1(), false);
//                    initializeMoPubSDKforInter2(adsPrefernce.mpInterId2(), false);
//                    initializeMoPubSDK();


                    //StartApp

//                    if (ads.getAd4().equals("1")) {
//
//                        adsPrefernce = new AdsPrefernce(BaseClass.this);
//                        adsPrefernce.setAdsDefaults("0", ads.getShowLoading(), ads.getAllowAccess(), ads.getMediation(), "0", "0", "0", ads.getAd4(), "0", "na", "na", "0",
//                                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
//                                "na", "0", "na", "0", "na", "0", "na", "0", "na", "0",
//                                "na", "0", "na", "0", "0", "0", "0",
//                                "0", "0", "na", "0", "na", "0",
//                                "na", "0", "na", "0", "na", "0", "na", "0",
//                                "na", "0", "na", "na", "0", "na", "0",
//                                "na", "0", "na", "0", ads.getExtraPara1(), ads.getExtraPara2(), ads.getExtraPara3(), ads.getExtraPara4(),
//                                ads.getSaAdCount());
//                        // initialize startapp sdk
//                        StartAppSDK.init(BaseClass.this, defaultIds.SA_APP_ID(), false);
//                        StartAppAd.enableAutoInterstitial();
//
//                        //disable startapp splash
//                        if (defaultIds.DISABLE_SA_SPLASH()) {
//                            StartAppAd.disableSplash();
//                        }
//                        StartAppAd.setAutoInterstitialPreferences(
//                                new AutoInterstitialPreferences()
//                                        .setActivitiesBetweenAds(Integer.parseInt(adsPrefernce.adCountSA()))
//                        );
//                    }

                    if (adsPrefernce.isMediationActive()) {
                        Log.e("Ads...", "loading Mixed Inter");
                        isAdsAvailable = true;
                        loadMixedInterAds();
                    }

                    isAdsAvailable = true;

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, String rawJsonData, AdsData errorResponse) {
                    isAdsAvailable = false;


                }

                @Override
                protected AdsData parseResponse(String rawJsonData, boolean isFailure) throws Throwable {

                    try {
                        if (!isFailure && !rawJsonData.isEmpty()) {
                            return gsonUtils.getGson().fromJson(rawJsonData, AdsData.class);
                        }
                    } catch (Exception ignored) {

                    }
                    return null;
                }
            });

        } catch (Exception ignored) {
            toast("calling get ads in catch...");

        }

    }

    public void showNativeBannerAd(Integer top, Integer bottom) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.showgBanner()) {
                    AdView gadView;
                    MobileAds.initialize(this, adsPrefernce.gAppId());
                    final FrameLayout adContainerView = this.findViewById(R.id.native_banner_container);
                    adContainerView.setVisibility(View.VISIBLE);
                    gadView = new AdView(this);
                    adContainerView.setPadding(0, top, 0, bottom);
                    gadView.setAdUnitId(adsPrefernce.gBannerId());
                    adContainerView.addView(gadView);
                    AdRequest adRequest = new AdRequest.Builder().build();
                    gadView.setAdSize(AdSize.LARGE_BANNER);
                    gadView.loadAd(adRequest);
                    gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
                        @Override
                        public void onAdLoaded() {
                            super.onAdLoaded();
                            adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                            }
                        }
                    });
                } else {
                    final NativeBannerAd nativeBannerAd;
                    if (adsPrefernce.showfbNativeBanner()) {
                        AudienceNetworkAds.initialize(this);
                        nativeBannerAd = new NativeBannerAd(this, adsPrefernce.fbNativeBannerId());
                        final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                        adContainerView.setVisibility(View.VISIBLE);
                        adContainerView.setPadding(0, top, 0, bottom);
                        nativeBannerAd.loadAd();
                        nativeBannerAd.setAdListener(new NativeAdListener() {
                            @Override
                            public void onMediaDownloaded(Ad ad) {
                                // Native ad finished downloading all assets
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                // Native ad failed to load
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                // Native ad is loaded and ready to be displayed
                                inflateNativeBannerAdFacebook(nativeBannerAd);
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }
                            }

                            @Override
                            public void onAdClicked(Ad ad) {
                                // Native ad clicked
                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {
                                // Native ad impression
                            }
                        });
                    }
                }

            } else {
                final NativeBannerAd nativeBannerAd;
                AudienceNetworkAds.initialize(this);
                nativeBannerAd = new NativeBannerAd(this, defaultIds.FB_NATIVE_BANNER());
                final FrameLayout adContainerView = findViewById(R.id.native_banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                nativeBannerAd.loadAd();
                nativeBannerAd.setAdListener(new NativeAdListener() {
                    @Override
                    public void onMediaDownloaded(Ad ad) {
                        // Native ad finished downloading all assets
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {
                        // Native ad failed to load
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        // Native ad is loaded and ready to be displayed
                        inflateNativeBannerAdFacebook(nativeBannerAd);
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onAdClicked(Ad ad) {
                        // Native ad clicked
                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {
                        // Native ad impression
                    }
                });

            }
        }
    }

    private void inflateNativeBannerAdFacebook(NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        NativeAdLayout nativeAdLayout = new NativeAdLayout(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        FrameLayout adContainer = (FrameLayout) findViewById(R.id.native_banner_container);
        LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_layout_facebook, adContainer, false);
        adContainer.addView(adView);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(this, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
        AdIconView nativeAdIconView = adView.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adView, nativeAdIconView, clickableViews);
    }

    public static boolean isGBannerShown = false;
    public static boolean isFbBannerShown = false;
    public static boolean isAnBannerShown = false;
    public static boolean isImBannerShown = false;
    public static boolean isIsBannerShown = false;

    public void destroyIsBanner() {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.showisBanner()) {
                    IronSource.destroyBanner(banner);
                    FrameLayout banner_container = findViewById(R.id.banner_container);
                    banner_container.removeAllViews();
                }

            }
        }
    }

    public IronSourceBannerLayout banner;

    public void showBannerAd(Integer top, Integer bottom) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.isMediationActive()) {
                    if (adsPrefernce.showgBanner()) {
                        AdView gadView;
                        MobileAds.initialize(this, adsPrefernce.gAppId());
                        final FrameLayout adContainerView = this.findViewById(R.id.banner_container);
                        adContainerView.setVisibility(View.VISIBLE);
                        gadView = new AdView(this);
                        adContainerView.setPadding(0, top, 0, bottom);
                        gadView.setAdUnitId(adsPrefernce.gBannerId());
                        adContainerView.addView(gadView);
                        AdRequest adRequest = new AdRequest.Builder().build();
                        com.google.android.gms.ads.AdSize adSize = getAdSize();
                        gadView.setAdSize(adSize);
                        gadView.loadAd(adRequest);
                        gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
                            @Override
                            public void onAdLoaded() {
                                super.onAdLoaded();
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }
                            }

                            @Override
                            public void onAdFailedToLoad(LoadAdError loadAdError) {
                                super.onAdFailedToLoad(loadAdError);
                                Log.e("bannerFailed:",loadAdError.getMessage().toString());
                            }
                        });
                    } else if (adsPrefernce.showfbBanner()) {
                        com.facebook.ads.AdView adView;
                        AudienceNetworkAds.initialize(this);
                        adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                        final FrameLayout adContainerView = findViewById(R.id.banner_container);
                        adContainerView.setVisibility(View.VISIBLE);
                        adContainerView.addView(adView);
                        adContainerView.setPadding(0, top, 0, bottom);
                        adView.loadAd();
                        adView.setAdListener(new AdListener() {
                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });

                    } else if (adsPrefernce.showimBanner()) {
                        final FrameLayout adContainerView = findViewById(R.id.banner_container);
                        InMobiBanner bannerAd = new InMobiBanner(this, Long.parseLong(defaultIds.IM_BANNER()));
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        adContainerView.setVisibility(View.VISIBLE);
                        adContainerView.setPadding(0, top, 0, bottom);
                        adContainerView.addView(bannerAd, layoutParams);
                        BannerAdEventListener bannerAdEventListener = new BannerAdEventListener() {
                            @Override
                            public void onAdLoadSucceeded(@NonNull InMobiBanner inMobiBanner, @NonNull AdMetaInfo adMetaInfo) {
                                super.onAdLoadSucceeded(inMobiBanner, adMetaInfo);
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }
                            }

                            @Override
                            public void onAdLoadFailed(@NonNull InMobiBanner inMobiBanner, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                super.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                            }
                        };
                        bannerAd.setListener(bannerAdEventListener);
                        bannerAd.load();
                    } else if (adsPrefernce.showisBanner()) {
                        final FrameLayout adContainerView = findViewById(R.id.banner_container);
                        banner = IronSource.createBanner(this, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        adContainerView.setVisibility(View.VISIBLE);
                        adContainerView.setPadding(0, top, 0, bottom);
                        adContainerView.addView(banner, 0, layoutParams);
                        banner.setBannerListener(new com.ironsource.mediationsdk.sdk.BannerListener() {
                            @Override
                            public void onBannerAdLoaded() {
                                banner.setVisibility(View.VISIBLE);
                                // Called after a banner ad has been successfully loaded
                                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                                }

                            }

                            @Override
                            public void onBannerAdLoadFailed(IronSourceError error) {
                                // Called after a banner has attempted to load an ad but failed.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        adContainerView.removeAllViews();
                                    }
                                });

                            }

                            @Override
                            public void onBannerAdClicked() {
                                // Called after a banner has been clicked.
                            }

                            @Override
                            public void onBannerAdScreenPresented() {
                                // Called when a banner is about to present a full screen content.
                            }

                            @Override
                            public void onBannerAdScreenDismissed() {
                                // Called after a full screen content has been dismissed
                            }

                            @Override
                            public void onBannerAdLeftApplication() {
                                // Called when a user would be taken out of the application context.
                            }
                        });

                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.BANNER);
                        IronSource.loadBanner(banner);
                    }
                } else {
                    if (adsPrefernce.showgBanner()) {
                        if (!isGBannerShown) {
                            showGBanner(top, bottom);
                        } else {
                            showFbBanner(top, bottom);
                        }
                    } else {
                        showFbBanner(top, bottom);
                    }
                }

            } else {
                com.facebook.ads.AdView adView;
                AudienceNetworkAds.initialize(this);
                adView = new com.facebook.ads.AdView(this, defaultIds.FB_BANNER(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                adContainerView.addView(adView);
                adView.loadAd();
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });

            }
        }
    }

    public void resetAllBannerBoolean() {
        isGBannerShown = false;
        isFbBannerShown = false;
        isAnBannerShown = false;
        isImBannerShown = false;
        isIsBannerShown = false;
    }

    public void showIsBanner(int top, int bottom) {

        if (adsPrefernce.showisBanner()) {
            if (!isIsBannerShown) {
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                banner = IronSource.createBanner(this, ISBannerSize.BANNER);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                adContainerView.addView(banner, 0, layoutParams);
                banner.setBannerListener(new com.ironsource.mediationsdk.sdk.BannerListener() {
                    @Override
                    public void onBannerAdLoaded() {
                        isIsBannerShown = true;
                        resetAllBannerBoolean();
                        banner.setVisibility(View.VISIBLE);
                        // Called after a banner ad has been successfully loaded
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }

                    }

                    @Override
                    public void onBannerAdLoadFailed(IronSourceError error) {
                        // Called after a banner has attempted to load an ad but failed.
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adContainerView.removeAllViews();
                            }
                        });

                        isIsBannerShown = true;
                        resetAllBannerBoolean();
                        if (adsPrefernce.showgBanner()) {
                            if (!isGBannerShown) {
                                showGBanner(top, bottom);
                            } else {
                                showFbBanner(top, bottom);
                            }
                        } else {
                            showFbBanner(top, bottom);
                        }

                    }

                    @Override
                    public void onBannerAdClicked() {
                        // Called after a banner has been clicked.
                    }

                    @Override
                    public void onBannerAdScreenPresented() {
                        // Called when a banner is about to present a full screen content.
                    }

                    @Override
                    public void onBannerAdScreenDismissed() {
                        // Called after a full screen content has been dismissed
                    }

                    @Override
                    public void onBannerAdLeftApplication() {
                        // Called when a user would be taken out of the application context.
                    }
                });

                IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.BANNER);
                IronSource.loadBanner(banner);
            } else {
                resetAllBannerBoolean();
                if (adsPrefernce.showgBanner()) {
                    if (!isGBannerShown) {
                        showGBanner(top, bottom);
                    } else {
                        showFbBanner(top, bottom);
                    }
                } else {
                    showFbBanner(top, bottom);
                }
            }
        } else {
            resetAllBannerBoolean();
            if (adsPrefernce.showgBanner()) {
                if (!isGBannerShown) {
                    showGBanner(top, bottom);
                } else {
                    showFbBanner(top, bottom);
                }
            } else {
                showFbBanner(top, bottom);
            }
        }

    }

    public void showImBanner(int top, int bottom) {
        if (adsPrefernce.showimBanner()) {
            if (!isImBannerShown) {
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                InMobiBanner bannerAd = new InMobiBanner(this, Long.parseLong(defaultIds.IM_BANNER()));
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.setPadding(0, top, 0, bottom);
                adContainerView.addView(bannerAd, layoutParams);
                BannerAdEventListener bannerAdEventListener = new BannerAdEventListener() {
                    @Override
                    public void onAdLoadSucceeded(@NonNull InMobiBanner inMobiBanner, @NonNull AdMetaInfo adMetaInfo) {
                        super.onAdLoadSucceeded(inMobiBanner, adMetaInfo);
                        isImBannerShown = true;
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }
                    }

                    @Override
                    public void onAdLoadFailed(@NonNull InMobiBanner inMobiBanner, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                        super.onAdLoadFailed(inMobiBanner, inMobiAdRequestStatus);
                        isImBannerShown = true;
                        showIsBanner(top, bottom);
                    }
                };
                bannerAd.setListener(bannerAdEventListener);
                bannerAd.load();
            } else {
                showIsBanner(top, bottom);
            }


        } else {
            showIsBanner(top, bottom);
        }
    }

    public void showFbBanner(int top, int bottom) {
        if (adsPrefernce.showfbBanner()) {
            if (!isFbBannerShown) {
                com.facebook.ads.AdView adView;
                AudienceNetworkAds.initialize(this);
                adView = new com.facebook.ads.AdView(this, adsPrefernce.fbBannerId(), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
                final FrameLayout adContainerView = findViewById(R.id.banner_container);
                adContainerView.setVisibility(View.VISIBLE);
                adContainerView.addView(adView);
                adContainerView.setPadding(0, top, 0, bottom);
                adView.loadAd();
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onError(Ad ad, AdError adError) {
                        isFbBannerShown = true;
                        showImBanner(top, bottom);
                    }

                    @Override
                    public void onAdLoaded(Ad ad) {
                        isFbBannerShown = true;
                        adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                        }

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });
            } else {
                showImBanner(top, bottom);
            }
        } else {
            showImBanner(top, bottom);
        }

    }

    public void showGBanner(int top, int bottom) {
        AdView gadView;
        MobileAds.initialize(this, adsPrefernce.gAppId());
        final FrameLayout adContainerView = this.findViewById(R.id.banner_container);
        adContainerView.setVisibility(View.VISIBLE);
        gadView = new AdView(this);
        adContainerView.setPadding(0, top, 0, bottom);
        gadView.setAdUnitId(adsPrefernce.gBannerId());
        adContainerView.addView(gadView);
        AdRequest adRequest = new AdRequest.Builder().build();
        com.google.android.gms.ads.AdSize adSize = getAdSize();
        gadView.setAdSize(adSize);
        gadView.loadAd(adRequest);
        gadView.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                isGBannerShown = true;
                adContainerView.setBackground(getResources().getDrawable(R.drawable.bg_banner));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getResources().getDrawable(R.drawable.bg_banner).setTint(defaultIds.TINT_COLOR());
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                isGBannerShown = true;
                showFbBanner(top, bottom);
            }
        });
    }

    public void toast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void toast(String text, Boolean longToast) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void loadSplashInterstitial() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (!adsPrefernce.isMediationActive()) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (!isGInter1Ready) {
                            MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                            gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(this);
                            gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                            gInterstitial1.loadAd(new AdRequest.Builder().build());
                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                @Override
                                public void onAdLoaded() {
                                    super.onAdLoaded();
                                    isGInter1Ready = true;
                                }
                            });
                        }
                    }
                    if (adsPrefernce.showfbInter1()) {
                        if (!isFbInter1Ready) {
                            AudienceNetworkAds.initialize(this);
                            fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                            fbInterstitial1.loadAd();
                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {

                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {
                                    isFbInter1Ready = true;
                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            });
                        }

                    }

                }
                if (adsPrefernce.showimInter1()) {
                    if (!isImInter1Ready) {
                        if (inMobiInitialized) {
                            InMobiSdk.init(this, defaultIds.IM_ACCOUNT_ID());
                            inMobiInterstitial1 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                @Override
                                public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                    isImInter1Ready = true;
                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                }
                            });
                            inMobiInterstitial1.load();
                        }

                    }
                }
                if (adsPrefernce.showisInter1()) {
                    if (!isIsInter1Ready) {
                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.INTERSTITIAL);
                        IronSource.loadInterstitial();
                        IronSource.setInterstitialListener(new InterstitialListener() {
                            @Override
                            public void onInterstitialAdReady() {
                                isIsInter1Ready = true;
                            }

                            @Override
                            public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                            }

                            @Override
                            public void onInterstitialAdOpened() {

                            }

                            @Override
                            public void onInterstitialAdClosed() {

                            }

                            @Override
                            public void onInterstitialAdShowSucceeded() {

                            }

                            @Override
                            public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                            }

                            @Override
                            public void onInterstitialAdClicked() {

                            }
                        });
                    }
                }

            }

        }
    }

    public void showSplashInterstitial() {
        if (adsPrefernce.allowAccess()) {
//                if (isvalidInstall) {
            if (!adsPrefernce.isMediationActive()) {
                showSplashAd();
            } else {
                showMixedInterAds();
            }
//                }
        }
    }

    public void showSplashAd() {
        if (isNetworkAvailable(this)) {
            if (adsPrefernce.planA()) {
                if (adsPrefernce.showgInter1()) {
                    if (isGInter1Ready) {
                        if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                            gInterstitial1.show();
                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    gInterstitial1.loadAd(new AdRequest.Builder().build());
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    isGInter1Ready = false;
                                    isGInter1Shown = true;
                                }
                            });
                        }

                    }
                } else if (adsPrefernce.showfbInter1()) {
                    if (isFbInter1Ready) {
                        if (fbInterstitial1.isAdLoaded()) {
                            fbInterstitial1.show();
                            fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    isFbInter1Ready = false;
                                    isFbInter1Shown = true;
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    fbInterstitial1.loadAd();
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            });
                        }

                    }
                } else if (adsPrefernce.showimInter1()) {
                    if (isImInter1Ready) {
                        if (inMobiInterstitial1.isReady()) {
                            inMobiInterstitial1.show();
                            inMobiInterstitial1.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);

                                    InMobiSdk.init(BaseClass.this, defaultIds.IM_ACCOUNT_ID());
                                    inMobiInterstitial1 = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter1Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                        }
                                    });
                                    inMobiInterstitial1.load();
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                    isImInter1Ready = false;
                                    isImInter1Shown = true;
                                }
                            });
                        }
                    }
                } else if (adsPrefernce.showisInter1()) {
                    if (isIsInter1Ready) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial();
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {
                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    IronSource.loadInterstitial();
                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {
                                    isIsInter1Ready = false;
                                    isIsInter1Shown = true;
                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        }
                    }
                }
            }


        }
    }


    public void loadMixedInterAds() {
        Log.e("Ads...", "loading ads");
        if (isNetworkAvailable(this)) {
            Log.e("Ads...", "isNetworkAvailable");
            if (isAdsAvailable) {
                Log.e("Ads...", "isAdsAvailable");
                if (adsPrefernce.isMediationActive()) {
                    Log.e("Ads...", "isMediationActive");
                    if (adsPrefernce.planA()) {
                        Log.e("Ads...", "planA");
                        if (adsPrefernce.showgInter1()) {
                            Log.e("Ads...", "showgoogle1");

                            if (!isGInter1Ready) {
                                Log.e("Ads...", "google1 not ready");
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                gInterstitial1.loadAd(new AdRequest.Builder().build());
                                gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        Log.e("Ads...", "google1 ready");
                                        isGInter1Ready = true;
                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showgInter2()) {
                            Log.e("Ads...", "showgoogle2");
                            if (!isGInter2Ready) {
                                Log.e("Ads...", "google2 not ready");
                                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                gInterstitial2 = new com.google.android.gms.ads.InterstitialAd(this);
                                gInterstitial2.setAdUnitId(adsPrefernce.gInterId2());
                                gInterstitial2.loadAd(new AdRequest.Builder().build());
                                gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                        super.onAdLoaded();
                                        Log.e("Ads...", "google2 ready");
                                        isGInter2Ready = true;
                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showfbInter1()) {
                            if (!isFbInter1Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }
                        }
                        if (adsPrefernce.showfbInter2()) {
                            if (!isFbInter2Ready) {
                                AudienceNetworkAds.initialize(this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                                fbInterstitial2.loadAd();
                                fbInterstitial2.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter2Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }
                        }
                    }
                    if (adsPrefernce.planD()) {
                        if (adsPrefernce.showimInter1()) {
                            if (!isImInter1Ready) {
                                if (inMobiInitialized) {
                                    inMobiInterstitial1 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter1Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            Log.e("inmobi_inter1", String.valueOf(inMobiAdRequestStatus));
                                            isImInter1Ready = false;

                                        }
                                    });
                                    inMobiInterstitial1.load();
                                }

                            }

                        }
                        if (adsPrefernce.showimInter2()) {
                            if (!isImInter2Ready) {
                                if (inMobiInitialized) {
                                    inMobiInterstitial2 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER2()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter2Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            Log.e("inmobi_inter2", String.valueOf(inMobiAdRequestStatus));
                                            isImInter2Ready = false;

                                        }
                                    });
                                    inMobiInterstitial2.load();
                                }

                            }

                        }

                    }
                    if (adsPrefernce.planE()) {
                        if (adsPrefernce.showisInter1()) {
                            if (!isIsInter1Ready) {
                                IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.INTERSTITIAL);
                                IronSource.loadInterstitial();
                                IronSource.setInterstitialListener(new InterstitialListener() {
                                    @Override
                                    public void onInterstitialAdReady() {
                                        isIsInter1Ready = true;
                                        if (adsPrefernce.showisInter2()) {
                                            isIsInter2Ready = true;
                                        }
                                    }

                                    @Override
                                    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                    }

                                    @Override
                                    public void onInterstitialAdOpened() {

                                    }

                                    @Override
                                    public void onInterstitialAdClosed() {

                                    }

                                    @Override
                                    public void onInterstitialAdShowSucceeded() {

                                    }

                                    @Override
                                    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                    }

                                    @Override
                                    public void onInterstitialAdClicked() {

                                    }
                                });
                            }
                        }
                    }
                }
            } else {
                // if ad data not downloaded
                if (!isFbInter1Ready) {
                    fbInterstitial1 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                    fbInterstitial1.loadAd();
                    fbInterstitial1.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {

                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {
                            isFbInter1Ready = true;
                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    });
                }

            }
        }

    }

    public void showMixedInterAds() {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (!isGInter1Shown) {
                            if (isGInter1Ready) {
                                if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                                    gInterstitial1.show();
                                    gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        public void onAdClosed() {
                                            Log.e("Ads...", "g Inter 1 dismissed");
                                            MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                            gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                            gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                            gInterstitial1.loadAd(new AdRequest.Builder().build());
                                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                                @Override
                                                public void onAdLoaded() {
                                                    super.onAdLoaded();
                                                    isGInter1Ready = true;
                                                }
                                            });
                                        }

                                        @Override
                                        public void onAdOpened() {
                                            super.onAdOpened();
                                            Log.e("Ads...", "g Inter 1 shown");
                                            isGInter1Ready = false;
                                            isGInter1Shown = true;
                                        }
                                    });
                                } else {
                                    goToPlanA2();
                                }
                            } else {
                                goToPlanA2();
                            }
                        } else {
                            goToPlanA2();
                        }
                    } else {
                        goToPlanA2();
                    }
                } else {
                    goToPlanD();
                }
            } else {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;
                                Log.e("Ads...", "in else");

                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    }
                }
            }


        }

    }

    public void goToPlanA2() {

        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;
                                Log.e("Ads...", "fb 1 displayed");
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Log.e("Ads...", "fb 1 dismissed");
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId1());
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {
                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        goToPlanD();
                    }
                } else {
                    goToPlanD();
                }
            } else {
                goToPlanD();
            }

        } else {
            goToPlanD();
        }

    }

//    public void goToPlanC() {
//        if (adsPrefernce.planC()) {
//            if (adsPrefernce.showmpInter1()) {
//                if (!isMpInter1Shown) {
//                    if (mpInterstitial1 != null) {
//                        if (mpInterstitial1.isReady()) {
//                            mpInterstitial1.show();
//                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                @Override
//                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialShown(MoPubInterstitial interstitial) {
//                                    Log.e("Ads...", "Mp Inter 1 shown");
//                                    isMpInter1Ready = false;
//                                    isMpInter1Shown = true;
//                                }
//
//                                @Override
//                                public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//                                    Log.e("Ads...", "Mp Inter 1 dismissed");
//                                    if (mpInter1Initilized) {
//                                        mpInterstitial1.load();
//                                        mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                            @Override
//                                            public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                                isMpInter1Ready = true;
//                                            }
//
//                                            @Override
//                                            public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialShown(MoPubInterstitial interstitial) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                            }
//
//                                            @Override
//                                            public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                            }
//                                        });
//                                    } else {
//                                        initializeMoPubSDK();
//                                    }
//                                }
//                            });
//                        } else {
//                            goToPlanE();
//                        }
//                    } else {
//                        goToPlanE();
//                    }
//                } else {
//                    goToPlanE();
//                }
//            } else {
//                goToPlanE();
//            }
//
//        } else {
//            goToPlanE();
//        }
//    }

    public void goToPlanD() {
        if (adsPrefernce.planD()) {
            if (adsPrefernce.showimInter1()) {
                if (!isImInter1Shown) {
                    if (isImInter1Ready) {
                        if (inMobiInterstitial1.isReady()) {
                            inMobiInterstitial1.show();
                            inMobiInterstitial1.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    InMobiSdk.init(BaseClass.this, defaultIds.IM_ACCOUNT_ID());
                                    inMobiInterstitial1 = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter1Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            isImInter1Ready = true;

                                        }
                                    });
                                    inMobiInterstitial1.load();

                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    isImInter1Shown = true;
                                    isImInter1Ready = false;
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDisplayFailed(inMobiInterstitial);
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                    isImInter1Shown = true;
                                    isImInter1Ready = false;
                                }
                            });
                        } else {
                            goToPlanE();
                        }
                    } else {
                        goToPlanE();
                    }
                } else {
                    goToPlanE();
                }
            } else {
                goToPlanE();
            }
        } else {
            goToPlanE();
        }
    }

    public void goToPlanE() {
        if (adsPrefernce.planE()) {
            if (adsPrefernce.showisInter1()) {
                if (!isIsInter1Shown) {
                    if (isIsInter1Ready) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial("DefaultInterstitial");
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {

                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    IronSource.loadInterstitial();
                                    IronSource.setInterstitialListener(new InterstitialListener() {
                                        @Override
                                        public void onInterstitialAdReady() {
                                            isIsInter1Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                                            isIsInter1Ready = false;
                                        }

                                        @Override
                                        public void onInterstitialAdOpened() {

                                        }

                                        @Override
                                        public void onInterstitialAdClosed() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowSucceeded() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                        }

                                        @Override
                                        public void onInterstitialAdClicked() {

                                        }
                                    });

                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {
                                    isIsInter1Ready = false;
                                    isIsInter1Shown = true;

                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        } else {
                            goToPlanAI2();
                        }
                    } else {
                        goToPlanAI2();
                    }
                } else {
                    goToPlanAI2();
                }
            } else {
                goToPlanAI2();
            }
        } else {
            goToPlanAI2();
        }
    }

    public void goToPlanAI2() {
        if (adsPrefernce.planA()) {
            if (adsPrefernce.showgInter2()) {
                if (!isGInter2Shown) {
                    if (isGInter2Ready) {
                        if (gInterstitial2.isLoaded() && gInterstitial2 != null) {
                            gInterstitial2.show();
                            gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    Log.e("Ads...", "g Inter 2 dismissed");
                                    MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                    gInterstitial2 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                    gInterstitial2.setAdUnitId(adsPrefernce.gInterId2());
                                    gInterstitial2.loadAd(new AdRequest.Builder().build());
                                    gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            super.onAdLoaded();
                                            isGInter2Ready = true;
                                        }
                                    });
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    Log.e("Ads...", "g Inter 2 shown");
                                    isGInter2Ready = false;
                                    isGInter2Shown = true;
                                }
                            });
                        } else {
                            goToPlanA2I2();
                        }
                    } else {
                        goToPlanA2I2();
                    }
                } else {
                    goToPlanA2I2();
                }
            } else {
                goToPlanA2I2();
            }
        } else {
            goToPlanEI2();
        }
    }

    public void goToPlanA2I2() {

        if (adsPrefernce.showfbInter2()) {
            if (!isFbInter2Shown) {
                if (isFbInter2Ready) {
                    if (fbInterstitial2.isAdLoaded()) {
                        fbInterstitial2.show();
                        fbInterstitial2.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                Log.e("Ads...", "fb 2 displayed");
                                isFbInter2Ready = false;
                                isFbInter2Shown = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                Log.e("Ads...", "fb 2 dismissed");
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId2());
                                fbInterstitial2.loadAd();
                                fbInterstitial2.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter2Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        goToPlanDI2();
                    }
                } else {
                    goToPlanDI2();
                }
            } else {
                goToPlanDI2();
            }

        } else {
            goToPlanDI2();
        }

    }

    public void goToPlanDI2() {
        if (adsPrefernce.planD()) {
            if (adsPrefernce.showimInter2()) {
                if (!isImInter2Shown) {
                    if (isImInter2Ready) {
                        if (inMobiInterstitial2.isReady()) {
                            inMobiInterstitial2.show();
                            inMobiInterstitial2.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    inMobiInterstitial2 = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_INTER2()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter2Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            isImInter2Ready = true;

                                        }
                                    });
                                    inMobiInterstitial2.load();

                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    isImInter2Shown = true;
                                    isImInter2Ready = false;
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDisplayFailed(inMobiInterstitial);
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                    isImInter2Shown = true;
                                    isImInter2Ready = false;
                                }
                            });
                        } else {
                            goToPlanEI2();
                        }
                    } else {
                        goToPlanEI2();
                    }
                } else {
                    goToPlanEI2();
                }
            } else {
                goToPlanEI2();
            }
        } else {
            goToPlanEI2();
        }
    }

    public void goToPlanEI2() {
        if (adsPrefernce.planE()) {
            if (adsPrefernce.showisInter2()) {
                if (!isIsInter2Shown) {
                    if (isIsInter2Ready) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial(defaultIds.IS_INTER2());
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {

                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    IronSource.loadInterstitial();
                                    IronSource.setInterstitialListener(new InterstitialListener() {
                                        @Override
                                        public void onInterstitialAdReady() {
                                            isIsInter2Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                                            isIsInter2Ready = false;
                                        }

                                        @Override
                                        public void onInterstitialAdOpened() {

                                        }

                                        @Override
                                        public void onInterstitialAdClosed() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowSucceeded() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                        }

                                        @Override
                                        public void onInterstitialAdClicked() {

                                        }
                                    });

                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {
                                    isIsInter1Ready = false;
                                    isIsInter1Shown = true;
                                    resetAllShownBoolean();

                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        } else {
                            resetAllShownBoolean();
                        }
                    } else {
                        resetAllShownBoolean();
                    }
                } else {
                    resetAllShownBoolean();
                }
            } else {
                resetAllShownBoolean();
            }
        } else {
            resetAllShownBoolean();
        }
    }

    public void showMixedInterAdsOnClosed(Callable<Void> mathodToPerform) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (!isGInter1Shown) {
                            if (isGInter1Ready) {
                                if (gInterstitial1.isLoaded() && gInterstitial1 != null) {
                                    gInterstitial1.show();
                                    gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        public void onAdClosed() {
                                            try {
                                                mathodToPerform.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                            MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                            gInterstitial1 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                            gInterstitial1.setAdUnitId(adsPrefernce.gInterId1());
                                            gInterstitial1.loadAd(new AdRequest.Builder().build());
                                            gInterstitial1.setAdListener(new com.google.android.gms.ads.AdListener() {
                                                @Override
                                                public void onAdLoaded() {
                                                    super.onAdLoaded();
                                                    isGInter1Ready = true;
                                                }

                                                @Override
                                                public void onAdClosed() {
                                                    super.onAdClosed();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onAdOpened() {
                                            super.onAdOpened();
                                            isGInter1Ready = false;
                                            isGInter1Shown = true;
                                        }


                                        @Override
                                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                                            super.onAdFailedToLoad(loadAdError);
                                            //Added Later
                                            isGInter1Shown = true;
                                            try {
                                                mathodToPerform.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                } else {
                                    goToPlanA2OnClosed(mathodToPerform);
                                }
                            } else {
                                goToPlanA2OnClosed(mathodToPerform);
                            }
                        } else {
                            goToPlanA2OnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanA2OnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanDOnClosed(mathodToPerform);
                }
            } else {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;


                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    }
                }
            }


        }

    }

    public void goToPlanA2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.showfbInter1()) {
            if (!isFbInter1Shown) {
                if (isFbInter1Ready) {
                    if (fbInterstitial1.isAdLoaded()) {
                        fbInterstitial1.show();
                        fbInterstitial1.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter1Ready = false;
                                isFbInter1Shown = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial1 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId1());
                                fbInterstitial1.loadAd();
                                fbInterstitial1.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter1Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                //Added Later
                                isFbInter1Shown = true;
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        goToPlanDOnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanDOnClosed(mathodToPerform);
                }
            } else {
                goToPlanDOnClosed(mathodToPerform);
            }
        } else {
            goToPlanDOnClosed(mathodToPerform);
        }

    }

    public void goToPlanDOnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planD()) {
            if (adsPrefernce.showimInter1()) {
                if (!isImInter1Shown) {
                    if (isImInter1Ready) {
                        if (inMobiInterstitial1.isReady()) {
                            inMobiInterstitial1.show();
                            inMobiInterstitial1.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    inMobiInterstitial1 = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter1Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            isImInter1Ready = true;

                                        }
                                    });
                                    inMobiInterstitial1.load();

                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    isImInter1Shown = true;
                                    isImInter1Ready = false;
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDisplayFailed(inMobiInterstitial);
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                    isImInter1Shown = true;
                                    isImInter1Ready = false;
                                }
                            });
                        } else {
                            goToPlanEOnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanEOnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanEOnClosed(mathodToPerform);
                }
            } else {
                goToPlanEOnClosed(mathodToPerform);
            }
        } else {
            goToPlanEOnClosed(mathodToPerform);
        }
    }

    public void goToPlanEOnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planE()) {
            if (adsPrefernce.showisInter1()) {
                if (!isIsInter1Shown) {
                    if (isIsInter1Ready) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial(defaultIds.IS_INTER1());
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {

                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    IronSource.loadInterstitial();
                                    IronSource.setInterstitialListener(new InterstitialListener() {
                                        @Override
                                        public void onInterstitialAdReady() {
                                            isIsInter1Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                                            isIsInter1Ready = false;
                                        }

                                        @Override
                                        public void onInterstitialAdOpened() {

                                        }

                                        @Override
                                        public void onInterstitialAdClosed() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowSucceeded() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                        }

                                        @Override
                                        public void onInterstitialAdClicked() {

                                        }
                                    });

                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {
                                    isIsInter1Ready = false;
                                    isIsInter1Shown = true;

                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                                    isIsInter1Ready = false;
                                    isIsInter1Shown = true;
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        } else {
                            goToPlanAI2OnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanAI2OnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanAI2OnClosed(mathodToPerform);
                }
            } else {
                goToPlanAI2OnClosed(mathodToPerform);
            }
        } else {
            goToPlanAI2OnClosed(mathodToPerform);
        }
    }

    public void goToPlanAI2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planA()) {
            if (adsPrefernce.showgInter2()) {
                if (!isGInter2Shown) {
                    if (isGInter2Ready) {
                        if (gInterstitial2.isLoaded() && gInterstitial2 != null) {
                            gInterstitial2.show();
                            gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                                    gInterstitial2 = new com.google.android.gms.ads.InterstitialAd(BaseClass.this);
                                    gInterstitial2.setAdUnitId(adsPrefernce.gInterId2());
                                    gInterstitial2.loadAd(new AdRequest.Builder().build());
                                    gInterstitial2.setAdListener(new com.google.android.gms.ads.AdListener() {
                                        @Override
                                        public void onAdLoaded() {
                                            super.onAdLoaded();
                                            isGInter2Ready = true;
                                        }

                                        @Override
                                        public void onAdClosed() {
                                            super.onAdClosed();
                                        }
                                    });
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    isGInter2Ready = false;
                                    isGInter2Shown = true;
                                }


                                @Override
                                public void onAdFailedToLoad(LoadAdError loadAdError) {
                                    super.onAdFailedToLoad(loadAdError);
                                    //Added Later
                                    isGInter2Shown = true;
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        } else {
                            goToPlanA2I2OnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanA2I2OnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanA2I2OnClosed(mathodToPerform);
                }
            } else {
                goToPlanA2I2OnClosed(mathodToPerform);
            }
        } else {
            goToPlanD2OnClosed(mathodToPerform);
        }
    }

    public void goToPlanA2I2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.showfbInter2()) {
            if (!isFbInter2Shown) {
                if (isFbInter2Ready) {
                    if (fbInterstitial2.isAdLoaded()) {
                        fbInterstitial2.show();
                        fbInterstitial2.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {
                                isFbInter2Ready = false;
                                isFbInter2Shown = true;
                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                AudienceNetworkAds.initialize(BaseClass.this);
                                fbInterstitial2 = new com.facebook.ads.InterstitialAd(BaseClass.this, adsPrefernce.fbInterId2());
                                fbInterstitial2.loadAd();
                                fbInterstitial2.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {

                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {

                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {
                                        isFbInter2Ready = true;
                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {
                                //Added Later
                                isFbInter2Shown = true;
                                try {
                                    mathodToPerform.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        goToPlanD2OnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanD2OnClosed(mathodToPerform);
                }
            } else {
                goToPlanD2OnClosed(mathodToPerform);
            }
        } else {
            goToPlanD2OnClosed(mathodToPerform);
        }
    }

    public void goToPlanD2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planD()) {
            if (adsPrefernce.showimInter2()) {
                if (!isImInter2Shown) {
                    if (isImInter2Ready) {
                        if (inMobiInterstitial2.isReady()) {
                            inMobiInterstitial2.show();
                            inMobiInterstitial2.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    inMobiInterstitial2 = new InMobiInterstitial(BaseClass.this, Long.parseLong(defaultIds.IM_INTER2()), new InterstitialAdEventListener() {
                                        @Override
                                        public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                            super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                            isImInter2Ready = true;
                                        }

                                        @Override
                                        public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                            super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                            isImInter2Ready = true;

                                        }
                                    });
                                    inMobiInterstitial2.load();

                                }

                                @Override
                                public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                    super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    isImInter2Shown = true;
                                    isImInter2Ready = false;
                                }

                                @Override
                                public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDisplayFailed(inMobiInterstitial);
                                }

                                @Override
                                public void onAdDisplayed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdDisplayed(inMobiInterstitial, adMetaInfo);
                                    isImInter2Shown = true;
                                    isImInter2Ready = false;
                                }
                            });
                        } else {
                            goToPlanE2OnClosed(mathodToPerform);
                        }
                    } else {
                        goToPlanE2OnClosed(mathodToPerform);
                    }
                } else {
                    goToPlanE2OnClosed(mathodToPerform);
                }
            } else {
                goToPlanE2OnClosed(mathodToPerform);
            }
        } else {
            goToPlanE2OnClosed(mathodToPerform);
        }
    }

    public void goToPlanE2OnClosed(Callable<Void> mathodToPerform) {
        if (adsPrefernce.planE()) {
            if (adsPrefernce.showisInter2()) {
                if (!isIsInter2Shown) {
                    if (isIsInter2Ready) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial(defaultIds.IS_INTER2());
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {
                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    resetAllShownBoolean();
                                    IronSource.loadInterstitial();
                                    IronSource.setInterstitialListener(new InterstitialListener() {
                                        @Override
                                        public void onInterstitialAdReady() {
                                            isIsInter2Ready = true;
                                        }

                                        @Override
                                        public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {
                                            isIsInter2Ready = false;
                                        }

                                        @Override
                                        public void onInterstitialAdOpened() {

                                        }

                                        @Override
                                        public void onInterstitialAdClosed() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowSucceeded() {

                                        }

                                        @Override
                                        public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                        }

                                        @Override
                                        public void onInterstitialAdClicked() {

                                        }
                                    });

                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {
                                    isIsInter2Ready = false;
                                    isIsInter2Shown = true;

                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                                    isIsInter2Ready = false;
                                    isIsInter2Shown = true;
                                    try {
                                        mathodToPerform.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    resetAllShownBoolean();

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        } else {
                            try {
                                mathodToPerform.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            resetAllShownBoolean();
                        }
                    } else {
                        try {
                            mathodToPerform.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        resetAllShownBoolean();
                    }
                } else {
                    try {
                        mathodToPerform.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    resetAllShownBoolean();
                }
            } else {
                try {
                    mathodToPerform.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resetAllShownBoolean();
            }
        } else {
            try {
                mathodToPerform.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resetAllShownBoolean();
        }
    }


    public void resetAllShownBoolean() {
        isGInter1Shown = false;
        isFbInter1Shown = false;
        isAnInter1Shown = false;
        isMpInter1Shown = false;
        isImInter1Shown = false;
        isIsInter1Shown = false;
        isGInter2Shown = false;
        isFbInter2Shown = false;
        isAnInter2Shown = false;
        isMpInter2Shown = false;
        isImInter2Shown = false;
        isIsInter2Shown = false;
        Log.e("Ads...", "resetAllShownBoolean");

    }


    public void loadInterstitial1() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.isMediationActive()) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                            gInterstitial11 = new com.google.android.gms.ads.InterstitialAd(this);
                            gInterstitial11.setAdUnitId(adsPrefernce.gInterId1());

                            gInterstitial11.loadAd(new AdRequest.Builder().build());
                            gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                @Override
                                public void onAdLoaded() {
                                    super.onAdLoaded();
                                }
                            });

                        } else {
                            AudienceNetworkAds.initialize(this);
                            if (adsPrefernce.showfbInter1()) {
                                fbInterstitial11 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                                fbInterstitial11.loadAd();
                            }
                        }
                    } else if (adsPrefernce.planD()) {
                        if (adsPrefernce.showimInter1()) {
                            if (inMobiInitialized) {
                                InMobiSdk.init(this, defaultIds.IM_ACCOUNT_ID());
                                inMobiInterstitial11 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER1()), new InterstitialAdEventListener() {
                                    @Override
                                    public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                        super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                    }

                                    @Override
                                    public void onAdLoadFailed(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus) {
                                        super.onAdLoadFailed(inMobiInterstitial, inMobiAdRequestStatus);
                                    }
                                });
//                            inMobiInterstitial11 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER1()), mInterstitialAdEventListener);
                                inMobiInterstitial11.load();
                            }

                        }
                    } else if (adsPrefernce.planE()) {
                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.INTERSTITIAL);
                        if (adsPrefernce.showisInter1()) {
                            IronSource.loadInterstitial();
                        }
                    }
                }
            } else {
                fbInterstitial11 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                fbInterstitial11.loadAd();
                fbInterstitial11.setAdListener(new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {

                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });
            }
        }
    }

    public void loadInterstitial2() {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (!adsPrefernce.isMediationActive()) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter2()) {
                            Log.e("inter2", "showgInter2");
                            MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                            gInterstitial22 = new com.google.android.gms.ads.InterstitialAd(this);
                            gInterstitial22.setAdUnitId(adsPrefernce.gInterId2());

                            Log.e("inter2", "isGInter2Ready false");
                            gInterstitial22.loadAd(new AdRequest.Builder().build());
                            gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                @Override
                                public void onAdLoaded() {
                                    super.onAdLoaded();
                                    Log.e("inter2", "isGInter2Ready = true");
                                }
                            });
                        } else if (adsPrefernce.showfbInter2()) {
                            AudienceNetworkAds.initialize(this);
                            fbInterstitial22 = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                            fbInterstitial22.loadAd();
                        }
                    } else if (adsPrefernce.planD()) {
                        if (adsPrefernce.showimInter2()) {
                            InterstitialAdEventListener mInterstitialAdEventListener = new InterstitialAdEventListener() {
                                @Override
                                public void onAdLoadSucceeded(@NonNull InMobiInterstitial inMobiInterstitial, @NonNull AdMetaInfo adMetaInfo) {
                                    super.onAdLoadSucceeded(inMobiInterstitial, adMetaInfo);
                                }
                            };
                            inMobiInterstitial22 = new InMobiInterstitial(this, Long.parseLong(defaultIds.IM_INTER2()), mInterstitialAdEventListener);
                            inMobiInterstitial22.load();
                        }
                    } else if (adsPrefernce.planE()) {
                        IronSource.init(this, defaultIds.IS_APP_KEY(), IronSource.AD_UNIT.INTERSTITIAL);
                        if (adsPrefernce.showisInter2()) {
                            IronSource.loadInterstitial();
                        }
                    }
                }


            }
        } else {
            fbInterstitial22 = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER2());
            fbInterstitial22.loadAd();
            fbInterstitial22.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {

                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
        }
    }

    public void InterstitialAd2(final boolean loadOnClosed) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter2()) {
                        Log.e("inter2", "show: showgInter2");
                        if (gInterstitial22.isLoaded() && gInterstitial22 != null) {
                            Log.e("inter2", "show: isGInter2 isLoaded = true");
                            gInterstitial22.show();
                            gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    if (loadOnClosed) {
//                                            loadInterstitial2();
                                        Log.e("inter2", "isGInter2Ready false");
                                        gInterstitial22.loadAd(new AdRequest.Builder().build());
                                        gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                            @Override
                                            public void onAdLoaded() {
                                                super.onAdLoaded();
                                                Log.e("inter2", "isGInter2Ready = true");
                                            }
                                        });


                                    }
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                    Log.e("Inter2", "g2");
                                }
                            });
                        }
                    } else if (adsPrefernce.showfbInter2()) {
                        if (fbInterstitial22.isAdLoaded()) {
                            fbInterstitial22.show();
                            fbInterstitial22.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {
                                    Log.e("Inter2", "fb2");
                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        loadInterstitial2();
                                    }
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            });
                        }


                    }

                } else if (adsPrefernce.planD()) {
                    if (adsPrefernce.showimInter2()) {
                        if (inMobiInterstitial22.isReady()) {
                            inMobiInterstitial22.show();
                            inMobiInterstitial22.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    if (loadOnClosed) {
                                        loadInterstitial2();
                                    }
                                }
                            });
                        }
                    }
                } else if (adsPrefernce.planE()) {
                    if (adsPrefernce.showisInter2()) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial(defaultIds.IS_INTER2());
                        }
                    }
                }
            }
        } else {
            if (fbInterstitial22.isAdLoaded()) {
                fbInterstitial22.show();
                fbInterstitial22.setAdListener(new InterstitialAdListener() {
                    @Override
                    public void onInterstitialDisplayed(Ad ad) {
                        Log.e("Inter2", "fb 2 in else");
                    }

                    @Override
                    public void onInterstitialDismissed(Ad ad) {
                        if (loadOnClosed) {
                            loadInterstitial2();
                        }
                    }

                    @Override
                    public void onError(Ad ad, AdError adError) {

                    }

                    @Override
                    public void onAdLoaded(Ad ad) {

                    }

                    @Override
                    public void onAdClicked(Ad ad) {

                    }

                    @Override
                    public void onLoggingImpression(Ad ad) {

                    }
                });
            }


        }
    }

    public void InterstitialAd1(final boolean loadOnClosed) {
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {
                if (adsPrefernce.planA()) {
                    if (adsPrefernce.showgInter1()) {
                        if (gInterstitial11.isLoaded() && gInterstitial11 != null) {
                            gInterstitial11.show();
                            gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                public void onAdClosed() {
                                    if (loadOnClosed) {
                                        loadInterstitial1();
                                    }
                                }

                                @Override
                                public void onAdOpened() {
                                    super.onAdOpened();
                                }
                            });


                        }

                    } else {
                        if (adsPrefernce.showfbInter1()) {

                            if (fbInterstitial11.isAdLoaded()) {
                                fbInterstitial11.show();
                                fbInterstitial11.setAdListener(new InterstitialAdListener() {
                                    @Override
                                    public void onInterstitialDisplayed(Ad ad) {
                                    }

                                    @Override
                                    public void onInterstitialDismissed(Ad ad) {
                                        if (loadOnClosed) {
                                            loadInterstitial1();
                                        }
                                    }

                                    @Override
                                    public void onError(Ad ad, AdError adError) {

                                    }

                                    @Override
                                    public void onAdLoaded(Ad ad) {

                                    }

                                    @Override
                                    public void onAdClicked(Ad ad) {

                                    }

                                    @Override
                                    public void onLoggingImpression(Ad ad) {

                                    }
                                });
                            }


                        }
                    }
                } else if (adsPrefernce.planD()) {
                    if (adsPrefernce.showimInter1()) {
                        if (inMobiInterstitial11.isReady()) {
                            inMobiInterstitial11.show();
                            inMobiInterstitial11.setListener(new InterstitialAdEventListener() {
                                @Override
                                public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                    super.onAdDismissed(inMobiInterstitial);
                                    if (loadOnClosed) {
                                        loadInterstitial1();
                                    }
                                }
                            });
                        }
                    }
                } else if (adsPrefernce.planE()) {
                    if (adsPrefernce.showisInter1()) {
                        if (IronSource.isInterstitialReady()) {
                            IronSource.showInterstitial();
                            IronSource.setInterstitialListener(new InterstitialListener() {
                                @Override
                                public void onInterstitialAdReady() {

                                }

                                @Override
                                public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdOpened() {

                                }

                                @Override
                                public void onInterstitialAdClosed() {
                                    if (loadOnClosed) {
                                        IronSource.loadInterstitial();
                                    }
                                }

                                @Override
                                public void onInterstitialAdShowSucceeded() {

                                }

                                @Override
                                public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {

                                }

                                @Override
                                public void onInterstitialAdClicked() {

                                }
                            });
                        }
                    }
                }
            } else {
                if (fbInterstitial11.isAdLoaded()) {
                    fbInterstitial11.show();
                    fbInterstitial11.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (loadOnClosed) {
                                loadInterstitial1();
                            }
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {

                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    });
                }


            }
        }
    }

    public void showInterstitial1(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!adsPrefernce.isMediationActive()) {
                        InterstitialAd1(loadOnClosed);
                    } else {
                        showMixedInterAds();
                    }

                    return null;
                }
            });
        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!adsPrefernce.isMediationActive()) {
                InterstitialAd1(loadOnClosed);
            } else {
                showMixedInterAds();
            }

        }
    }

    public void showInterstitial2(final boolean loadOnClosed,
                                  final Callable<Void> mathodToFollow) {
        if (adsPrefernce.showLoading()) {
            proceedWithDelay(1000, "Showing Ad...", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    try {
                        mathodToFollow.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!adsPrefernce.isMediationActive()) {
                        InterstitialAd2(loadOnClosed);
                    } else {
                        showMixedInterAds();
                    }

                    return null;
                }
            });
        } else {
            try {
                mathodToFollow.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!adsPrefernce.isMediationActive()) {
                InterstitialAd2(loadOnClosed);
            } else {
                showMixedInterAds();
            }

        }
    }

    public void proceedWithDelay(int delay, String messageText,
                                 final Callable<Void> mathodToProceed) {

        progressDialog.setMessage(messageText);
        progressDialog.show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                try {
                    mathodToProceed.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    public void proceedWithDelay(int delay, final Callable<Void> mathodToProceed) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mathodToProceed.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, delay);
    }

    public void loadInterAdsDialog(int adToLoad) {
        adsPrefernce = new AdsPrefernce(this);
        if (isNetworkAvailable(this)) {
            if (isAdsAvailable) {

                MobileAds.initialize(getApplicationContext(), adsPrefernce.gAppId());
                if (adToLoad == 1 && adsPrefernce.showgInter1()) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(adsPrefernce.gInterId1());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                } else if (adToLoad == 2 && adsPrefernce.showgInter2()) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(adsPrefernce.gInterId2());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                } else {
                    AudienceNetworkAds.initialize(this);
                    if (adToLoad == 1 && adsPrefernce.showfbInter1()) {
                        fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId1());
                        fbDialogInterstitial.loadAd();
                    } else if (adToLoad == 2 && adsPrefernce.showfbInter2()) {
                        fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, adsPrefernce.fbInterId2());
                        fbDialogInterstitial.loadAd();
                    }
                }
            } else {
                MobileAds.initialize(getApplicationContext(), defaultIds.GOOGLE_APP_ID());
                if (adToLoad == 1) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(defaultIds.GOOGLE_INTER1());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                    fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER1());
                    fbDialogInterstitial.loadAd();
                } else if (adToLoad == 2) {
                    gDialogInterstitial = new com.google.android.gms.ads.InterstitialAd(this);
                    gDialogInterstitial.setAdUnitId(defaultIds.GOOGLE_INTER2());
                    gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                    fbDialogInterstitial = new com.facebook.ads.InterstitialAd(this, defaultIds.FB_INTER2());
                    fbDialogInterstitial.loadAd();
                }
            }
        }
    }

    public void showInterAdsDialog(final boolean loadOnClosed, final int adToShow) {
        if (isNetworkAvailable(BaseClass.this)) {
            if (isAdsAvailable) {
                if (adToShow == 1 && adsPrefernce.showgInter1()) {
                    if (gDialogInterstitial.isLoaded() && gDialogInterstitial != null) {
                        gDialogInterstitial.show();
                    }
                    gDialogInterstitial.setAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdClosed() {
                            if (loadOnClosed) {
                                gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                            }
                        }
                    });
                } else if (adToShow == 2 && adsPrefernce.showgInter2()) {
                    if (gDialogInterstitial.isLoaded() && gDialogInterstitial != null) {
                        gDialogInterstitial.show();
                    }
                    gDialogInterstitial.setAdListener(new com.google.android.gms.ads.AdListener() {
                        public void onAdClosed() {
                            if (loadOnClosed) {
                                gDialogInterstitial.loadAd(new AdRequest.Builder().build());
                            }
                        }
                    });
                } else {
                    if (adToShow == 1 && adsPrefernce.showfbInter1()) {
                        if (fbDialogInterstitial.isAdLoaded()) {
                            fbDialogInterstitial.show();
                            fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        fbDialogInterstitial.loadAd();
                                    }
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            });
                        }
                    } else if (adToShow == 2 && adsPrefernce.showfbInter2()) {
                        if (fbDialogInterstitial.isAdLoaded()) {
                            fbDialogInterstitial.show();
                            fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                                @Override
                                public void onInterstitialDisplayed(Ad ad) {

                                }

                                @Override
                                public void onInterstitialDismissed(Ad ad) {
                                    if (loadOnClosed) {
                                        fbDialogInterstitial.loadAd();
                                    }
                                }

                                @Override
                                public void onError(Ad ad, AdError adError) {

                                }

                                @Override
                                public void onAdLoaded(Ad ad) {

                                }

                                @Override
                                public void onAdClicked(Ad ad) {

                                }

                                @Override
                                public void onLoggingImpression(Ad ad) {

                                }
                            });
                        }
                    }
                }
            } else {
                if (fbDialogInterstitial.isAdLoaded()) {
                    fbDialogInterstitial.show();
                    fbDialogInterstitial.setAdListener(new InterstitialAdListener() {
                        @Override
                        public void onInterstitialDisplayed(Ad ad) {

                        }

                        @Override
                        public void onInterstitialDismissed(Ad ad) {
                            if (loadOnClosed) {
                                fbDialogInterstitial.loadAd();
                            }
                        }

                        @Override
                        public void onError(Ad ad, AdError adError) {

                        }

                        @Override
                        public void onAdLoaded(Ad ad) {

                        }

                        @Override
                        public void onAdClicked(Ad ad) {

                        }

                        @Override
                        public void onLoggingImpression(Ad ad) {

                        }
                    });
                }
            }
        }
    }

    public void showInter1AdonClosed(final Callable<Void> methodParam) {
        if (isNetworkAvailable(this)) {
            adsPrefernce = new AdsPrefernce(this);
            if (!adsPrefernce.isMediationActive()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter1()) {
                            if (gInterstitial11.isLoaded()) {
                                gInterstitial11.show();
                                gInterstitial11.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial11.loadAd(new AdRequest.Builder().build());
                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                        } else {
                            if (adsPrefernce.showfbInter1()) {
                                if (fbInterstitial11.isAdLoaded()) {
                                    fbInterstitial11.show();
                                    fbInterstitial11.setAdListener(new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {

                                        }

                                        @Override
                                        public void onAdLoaded(Ad ad) {

                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    });
                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else if (adsPrefernce.planD()) {
                        if (adsPrefernce.showimInter1()) {
                            if (inMobiInterstitial11.isReady()) {
                                inMobiInterstitial11.show();
                                inMobiInterstitial11.setListener(new InterstitialAdEventListener() {
                                    @Override
                                    public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                        super.onAdDisplayFailed(inMobiInterstitial);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                        super.onAdDismissed(inMobiInterstitial);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else if (adsPrefernce.planE()) {
                        if (adsPrefernce.showisInter1()) {
                            if (IronSource.isInterstitialReady()) {
                                IronSource.showInterstitial();
                                IronSource.setInterstitialListener(new InterstitialListener() {
                                    @Override
                                    public void onInterstitialAdReady() {

                                    }

                                    @Override
                                    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                    }

                                    @Override
                                    public void onInterstitialAdOpened() {

                                    }

                                    @Override
                                    public void onInterstitialAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onInterstitialAdShowSucceeded() {

                                    }

                                    @Override
                                    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onInterstitialAdClicked() {

                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (fbInterstitial11.isAdLoaded()) {
                        fbInterstitial11.show();
                        fbInterstitial11.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {

                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                showMixedInterAdsOnClosed(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }

        } else {
            try {
                methodParam.call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showInter2AdonClosed(final Callable<Void> methodParam) {
        if (isNetworkAvailable(this)) {
            adsPrefernce = new AdsPrefernce(this);
            if (!adsPrefernce.isMediationActive()) {
                if (isAdsAvailable) {
                    if (adsPrefernce.planA()) {
                        if (adsPrefernce.showgInter2()) {
                            if (gInterstitial22.isLoaded()) {
                                gInterstitial22.show();
                                gInterstitial22.setAdListener(new com.google.android.gms.ads.AdListener() {
                                    @Override
                                    public void onAdLoaded() {
                                    }

                                    @Override
                                    public void onAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        gInterstitial22.loadAd(new AdRequest.Builder().build());
                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            if (adsPrefernce.showfbInter2()) {
                                if (fbInterstitial22.isAdLoaded()) {
                                    fbInterstitial22.show();
                                    fbInterstitial22.setAdListener(new InterstitialAdListener() {
                                        @Override
                                        public void onInterstitialDisplayed(Ad ad) {

                                        }

                                        @Override
                                        public void onInterstitialDismissed(Ad ad) {
                                            try {
                                                methodParam.call();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void onError(Ad ad, AdError adError) {

                                        }

                                        @Override
                                        public void onAdLoaded(Ad ad) {

                                        }

                                        @Override
                                        public void onAdClicked(Ad ad) {

                                        }

                                        @Override
                                        public void onLoggingImpression(Ad ad) {

                                        }
                                    });
                                } else {
                                    try {
                                        methodParam.call();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }

                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } else if (adsPrefernce.planD()) {
                        if (adsPrefernce.showimInter2()) {
                            if (inMobiInterstitial22.isReady()) {
                                inMobiInterstitial22.show();
                                inMobiInterstitial22.setListener(new InterstitialAdEventListener() {
                                    @Override
                                    public void onAdDisplayFailed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                        super.onAdDisplayFailed(inMobiInterstitial);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onAdDismissed(@NonNull InMobiInterstitial inMobiInterstitial) {
                                        super.onAdDismissed(inMobiInterstitial);
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    } else if (adsPrefernce.planE()) {
                        if (adsPrefernce.showisInter2()) {
                            if (IronSource.isInterstitialReady()) {
                                IronSource.showInterstitial(defaultIds.IS_INTER2());
                                IronSource.setInterstitialListener(new InterstitialListener() {
                                    @Override
                                    public void onInterstitialAdReady() {

                                    }

                                    @Override
                                    public void onInterstitialAdLoadFailed(IronSourceError ironSourceError) {

                                    }

                                    @Override
                                    public void onInterstitialAdOpened() {

                                    }

                                    @Override
                                    public void onInterstitialAdClosed() {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onInterstitialAdShowSucceeded() {

                                    }

                                    @Override
                                    public void onInterstitialAdShowFailed(IronSourceError ironSourceError) {
                                        try {
                                            methodParam.call();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }

                                    }

                                    @Override
                                    public void onInterstitialAdClicked() {

                                    }
                                });
                            } else {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            try {
                                methodParam.call();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if (fbInterstitial22.isAdLoaded()) {
                        fbInterstitial22.show();
                        fbInterstitial22.setAdListener(new InterstitialAdListener() {
                            @Override
                            public void onInterstitialDisplayed(Ad ad) {

                            }

                            @Override
                            public void onInterstitialDismissed(Ad ad) {
                                try {
                                    methodParam.call();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onError(Ad ad, AdError adError) {

                            }

                            @Override
                            public void onAdLoaded(Ad ad) {

                            }

                            @Override
                            public void onAdClicked(Ad ad) {

                            }

                            @Override
                            public void onLoggingImpression(Ad ad) {

                            }
                        });
                    } else {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } else {
                showMixedInterAdsOnClosed(new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        try {
                            methodParam.call();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
            }

        } else {
            try {
                methodParam.call();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void checkAppService(String key, String appVersion, OnCheckServiceListner onCheckServiceListner) {
        if (adsPrefernce.allowAccess()) {
            if (isNetworkAvailable(this) && checkAppService) {
                runAppService(key, appVersion, onCheckServiceListner);
            }
        } else {
            if (isvalidInstall) {
                if (isNetworkAvailable(this) && checkAppService) {
                    runAppService(key, appVersion, onCheckServiceListner);
                }
            }
        }
    }

    public void checkAppService(String key, String appVersion) {
        if (adsPrefernce.allowAccess()) {
            if (isNetworkAvailable(this) && checkAppService) {
                runAppService(key, appVersion);
            }
        } else {
            if (isvalidInstall) {
                if (isNetworkAvailable(this) && checkAppService) {
                    runAppService(key, appVersion);
                }
            }
        }
    }

    public void runAppService(String app_key, final String appVersion, OnCheckServiceListner onCheckServiceListner) {
        AsyncHttpClient client = new AsyncHttpClient();

        gsonUtils = GsonUtils.getInstance();

        RequestParams params1 = new RequestParams();
        params1.put("app_key", app_key);

        try {
            client.setConnectTimeout(50000);
            client.post("http://developercompanion.get-fans-for-musically.com/iapi/app_service2.php", params1, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    String Success = response.optString("success");
                    int con = Integer.parseInt(Success);
                    if (con == 1) {

                        int isUpdate = response.optInt("isUpdate");
                        int isNotification = response.optInt("isNotification");
                        int isAd = response.optInt("isAd");
                        String update_dialog_title = response.optString("update_dialog_title");
                        String update_title = response.optString("update_title");
                        String update_version_name = response.optString("update_version_name");
                        String update_message = response.optString("update_message");
                        int update_show_cancel = response.optInt("update_show_cancel");
                        String update_app_url = response.optString("update_app_url");
                        int update_force_update = response.optInt("update_force_update");
                        String update_force_v1 = response.optString("update_force_v1");
                        String update_force_v2 = response.optString("update_force_v2");
                        String update_force_v3 = response.optString("update_force_v3");

                        String not_dialog_title = response.optString("not_dialog_title");
                        String not_message = response.optString("not_message");
                        String not_image_url = response.optString("not_image_url");
                        int not_show_dialog = response.optInt("not_show_dialog");
                        String not_cancel_button_text = response.optString("not_cancel_button_text");
                        int not_show_cancel_button = response.optInt("not_show_cancel_button");
                        int not_show_ad_icon = response.optInt("not_show_ad_icon");
                        String not_btn_1_activity_text = response.optString("not_btn_1_activity_text");
                        int not_btn_1_show = response.optInt("not_btn_1_show");
                        String not_btn_1_video_url = response.optString("not_btn_1_video_url");
                        String not_btn_2_webview_text = response.optString("not_btn_2_webview_text");
                        int not_btn_2_show = response.optInt("not_btn_2_show");
                        String not_btn_2_webview_url = response.optString("not_btn_2_webview_url");
                        String not_btn_3_text = response.optString("not_btn_3_text");
                        int not_btn_3_show = response.optInt("not_btn_3_show");
                        String not_btn_3_url = response.optString("not_btn_3_url");

                        String ad_dialog_title = response.optString("ad_dialog_title");
                        int ad_show_cancel = response.optInt("ad_show_cancel");
                        String ad_message = response.optString("ad_message");
                        String ad_banner_url = response.optString("ad_banner_url");
                        String ad_icon_url = response.optString("ad_icon_url");
                        String ad_app_name = response.optString("ad_app_name");
                        String ad_app_short_desc = response.optString("ad_app_short_desc");
                        String ad_app_url = response.optString("ad_app_url");

                        checkAppService = false;

                        if (isUpdate == 1) {
                            if (!appVersion.equals(update_version_name)) {
                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);

//                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
//                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
//                                        not_message, not_image_url,not_show_dialog == 1,not_cancel_button_text, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url,onCheckServiceListner);
                                if (defaultIds.SHOW_NOTIFICATION()) {
                                    if (isNotification == 1) {
                                        if (not_show_dialog == 0) {
                                            lay_notification.setVisibility(View.VISIBLE);
                                            tv_not_text.setText(not_message);
                                            iv_not_close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    lay_notification.setVisibility(View.GONE);
                                                }
                                            });
                                        }
                                    }
                                }

                                return;
                            }

                        }
                        if (isNotification == 1) {
                            if (defaultIds.SHOW_NOTIFICATION()) {
                                if (not_show_dialog == 0) {
                                    lay_notification.setVisibility(View.VISIBLE);
                                    tv_not_text.setText(not_message);
                                    iv_not_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lay_notification.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            } else {
                                serviceDialog(false, true, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);
                                return;
                            }
                        }
                        if (isAd == 1) {
                            serviceDialog(false, false, true, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                    update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                    not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                    not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                    not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                    not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                    ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, onCheckServiceListner);

                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            });

        } catch (Exception e) {

        }
    }

    public void runAppService(String app_key, final String appVersion) {
        AsyncHttpClient client = new AsyncHttpClient();

        gsonUtils = GsonUtils.getInstance();

        RequestParams params1 = new RequestParams();
        params1.put("app_key", app_key);

        try {
            client.setConnectTimeout(50000);
            client.post("http://developercompanion.get-fans-for-musically.com/iapi/app_service2.php", params1, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    String Success = response.optString("success");
                    int con = Integer.parseInt(Success);
                    if (con == 1) {

                        int isUpdate = response.optInt("isUpdate");
                        int isNotification = response.optInt("isNotification");
                        int isAd = response.optInt("isAd");
                        String update_dialog_title = response.optString("update_dialog_title");
                        String update_title = response.optString("update_title");
                        String update_version_name = response.optString("update_version_name");
                        String update_message = response.optString("update_message");
                        int update_show_cancel = response.optInt("update_show_cancel");
                        String update_app_url = response.optString("update_app_url");
                        int update_force_update = response.optInt("update_force_update");
                        String update_force_v1 = response.optString("update_force_v1");
                        String update_force_v2 = response.optString("update_force_v2");
                        String update_force_v3 = response.optString("update_force_v3");

                        String not_dialog_title = response.optString("not_dialog_title");
                        String not_message = response.optString("not_message");
                        String not_image_url = response.optString("not_image_url");
                        int not_show_dialog = response.optInt("not_show_dialog");
                        String not_cancel_button_text = response.optString("not_cancel_button_text");
                        int not_show_cancel_button = response.optInt("not_show_cancel_button");
                        int not_show_ad_icon = response.optInt("not_show_ad_icon");
                        String not_btn_1_activity_text = response.optString("not_btn_1_activity_text");
                        int not_btn_1_show = response.optInt("not_btn_1_show");
                        String not_btn_1_video_url = response.optString("not_btn_1_video_url");
                        String not_btn_2_webview_text = response.optString("not_btn_2_webview_text");
                        int not_btn_2_show = response.optInt("not_btn_2_show");
                        String not_btn_2_webview_url = response.optString("not_btn_2_webview_url");
                        String not_btn_3_text = response.optString("not_btn_3_text");
                        int not_btn_3_show = response.optInt("not_btn_3_show");
                        String not_btn_3_url = response.optString("not_btn_3_url");

                        String ad_dialog_title = response.optString("ad_dialog_title");
                        int ad_show_cancel = response.optInt("ad_show_cancel");
                        String ad_message = response.optString("ad_message");
                        String ad_banner_url = response.optString("ad_banner_url");
                        String ad_icon_url = response.optString("ad_icon_url");
                        String ad_app_name = response.optString("ad_app_name");
                        String ad_app_short_desc = response.optString("ad_app_short_desc");
                        String ad_app_url = response.optString("ad_app_url");

                        checkAppService = false;

                        if (isUpdate == 1) {
                            if (!appVersion.equals(update_version_name)) {
                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);

//                                serviceDialog(true, false, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
//                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
//                                        not_message, not_image_url,not_show_dialog == 1,not_cancel_button_text, ad_dialog_title, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url,onCheckServiceListner);
                                if (defaultIds.SHOW_NOTIFICATION()) {
                                    if (isNotification == 1) {
                                        if (not_show_dialog == 0) {
                                            lay_notification.setVisibility(View.VISIBLE);
                                            tv_not_text.setText(not_message);
                                            iv_not_close.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    lay_notification.setVisibility(View.GONE);
                                                }
                                            });
                                        }
                                    }
                                }

                                return;
                            }

                        }
                        if (isNotification == 1) {
                            if (defaultIds.SHOW_NOTIFICATION()) {
                                if (not_show_dialog == 0) {
                                    lay_notification.setVisibility(View.VISIBLE);
                                    tv_not_text.setText(not_message);
                                    iv_not_close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            lay_notification.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            } else {
                                serviceDialog(false, true, false, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                        update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                        not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                        not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                        not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                        not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                        ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);
                                return;
                            }
                        }
                        if (isAd == 1) {
                            serviceDialog(false, false, true, update_dialog_title, update_title, update_version_name, update_message, not_show_dialog == 1,
                                    update_show_cancel == 1, update_app_url, update_force_update == 1, update_force_v1, update_force_v2, update_force_v3, not_dialog_title,
                                    not_message, not_show_dialog == 1, not_image_url, not_cancel_button_text, not_show_cancel_button == 1, not_show_ad_icon == 1,
                                    not_btn_1_activity_text, not_btn_1_show == 1, not_btn_1_video_url,
                                    not_btn_2_webview_text, not_btn_2_show == 1, not_btn_2_webview_url,
                                    not_btn_3_text, not_btn_3_show == 1, not_btn_3_url,
                                    ad_dialog_title, ad_show_cancel == 1, ad_message, ad_banner_url, ad_icon_url, ad_app_name, ad_app_short_desc, ad_app_url, null);

                        }
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            });

        } catch (Exception e) {

        }
    }

    public void serviceDialog(Boolean isUpdate, Boolean isNotification, Boolean isAd, String
            update_dialog_title, String update_title,
                              String update_version_name, String update_message, Boolean update_available, Boolean
                                      update_show_cancel, final String update_app_url,
                              Boolean update_force_update, String update_force_v1, String update_force_v2, String
                                      update_force_v3,
                              String not_dialog_title,
                              String not_message, Boolean not_show_dialog, String not_image_url, String not_cancel_button_text, Boolean not_show_cancel_button, Boolean not_show_ad_icon,
                              String not_btn_1_activity_text, Boolean not_btn_1_show, String not_btn_1_video_url,
                              String not_btn_2_webview_text, Boolean not_btn_2_show, String not_btn_2_webview_url,
                              String not_btn_3_text, Boolean not_btn_3_show, String not_btn_3_url,
                              String ad_dialog_title, Boolean ad_show_cancel, String ad_message, String ad_banner_url, String ad_icon_url,
                              String ad_app_name, String ad_app_short_desc, final String ad_app_url, OnCheckServiceListner onCheckServiceListner) {

        this.serviceDialog = new Dialog(this);
        this.serviceDialog.setCancelable(false);
        this.serviceDialog.setContentView(R.layout.dialog_service);
        Objects.requireNonNull(this.serviceDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        LinearLayout lay_updateApp = this.serviceDialog.findViewById(R.id.lay_updateApp);
        LinearLayout lay_message = this.serviceDialog.findViewById(R.id.lay_message);
        LinearLayout lay_ads = this.serviceDialog.findViewById(R.id.lay_ads);

        ImageView iv_ad_icon_title = this.serviceDialog.findViewById(R.id.iv_ad_icon_title);
        TextView tv_dialog_title = this.serviceDialog.findViewById(R.id.tv_dialog_title);

        //update
        TextView tv_updatetitle = this.serviceDialog.findViewById(R.id.tv_updatetitle);
        TextView tv_versionName = this.serviceDialog.findViewById(R.id.tv_versionName);
        TextView tv_updatemessage = this.serviceDialog.findViewById(R.id.tv_updatemessage);
        TextView tv_updatebutton = this.serviceDialog.findViewById(R.id.tv_updatebutton);
        TextView tv_canclebutton = this.serviceDialog.findViewById(R.id.tv_canclebutton);

        //message
        TextView tv_message = this.serviceDialog.findViewById(R.id.tv_message);
        ImageView iv_not_banner = this.serviceDialog.findViewById(R.id.iv_not_banner);
        TextView tv_not_cancel_button = this.serviceDialog.findViewById(R.id.tv_not_cancel_button);
        TextView tv_sponsered = this.serviceDialog.findViewById(R.id.tv_sponsered);
        TextView btn_1_acitivty = this.serviceDialog.findViewById(R.id.btn_1_acitivty);
        TextView btn_2_webview = this.serviceDialog.findViewById(R.id.btn_2_webview);
        TextView btn_3_openurl = this.serviceDialog.findViewById(R.id.btn_3_openurl);

        //ads
        TextView tv_ad_message = this.serviceDialog.findViewById(R.id.tv_ad_message);
        ImageView iv_ad_banner = this.serviceDialog.findViewById(R.id.iv_ad_banner);
        ImageView iv_app_icon = this.serviceDialog.findViewById(R.id.iv_app_icon);
        TextView tv_app_name = this.serviceDialog.findViewById(R.id.tv_app_name);
        TextView tv_app_shortdesc = this.serviceDialog.findViewById(R.id.tv_app_shortdesc);
        TextView tv_app_download = this.serviceDialog.findViewById(R.id.tv_app_download);
        TextView tv_app_cancel = this.serviceDialog.findViewById(R.id.tv_app_cancel);

        if (isUpdate) {
            iv_ad_icon_title.setVisibility(View.GONE);
            lay_message.setVisibility(View.GONE);
            lay_ads.setVisibility(View.GONE);
            lay_updateApp.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(update_dialog_title);

            tv_updatetitle.setText(update_title);
            tv_versionName.setText(update_version_name);
            tv_updatemessage.setText(update_message);

            if (update_show_cancel) {
                tv_canclebutton.setVisibility(View.VISIBLE);
            } else {
                tv_canclebutton.setVisibility(View.GONE);
            }

            tv_updatebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(update_app_url));
                    startActivity(intent);
                }
            });

            tv_canclebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceDialog.dismiss();
                }
            });
            this.serviceDialog.show();

        }

        if (isNotification) {
            if (not_show_dialog) {
                lay_ads.setVisibility(View.GONE);
                lay_updateApp.setVisibility(View.GONE);
                lay_message.setVisibility(View.VISIBLE);
                tv_dialog_title.setText(not_dialog_title);
                if (not_show_ad_icon) {
                    tv_sponsered.setVisibility(View.VISIBLE);
                    iv_ad_icon_title.setVisibility(View.VISIBLE);
                } else {
                    tv_sponsered.setVisibility(View.GONE);
                    iv_ad_icon_title.setVisibility(View.GONE);
                }
                if (!not_image_url.equals("na")) {
                    Glide.with(this).load(not_image_url).into(iv_not_banner);
                }

                tv_message.setText(not_message);

                if (not_show_cancel_button) {
                    tv_not_cancel_button.setVisibility(View.VISIBLE);
                    tv_not_cancel_button.setText(not_cancel_button_text);
                } else {
                    tv_not_cancel_button.setVisibility(View.GONE);
                }

                if (not_btn_1_show) {
                    btn_1_acitivty.setVisibility(View.VISIBLE);
                    btn_2_webview.setVisibility(View.GONE);
                    btn_3_openurl.setVisibility(View.GONE);
                    btn_1_acitivty.setText(not_btn_1_activity_text);
                } else if (not_btn_2_show) {
                    btn_1_acitivty.setVisibility(View.GONE);
                    btn_2_webview.setVisibility(View.VISIBLE);
                    btn_3_openurl.setVisibility(View.GONE);
                    btn_2_webview.setText(not_btn_2_webview_text);
                } else if (not_btn_3_show) {
                    btn_1_acitivty.setVisibility(View.GONE);
                    btn_2_webview.setVisibility(View.GONE);
                    btn_3_openurl.setVisibility(View.VISIBLE);
                    btn_3_openurl.setText(not_btn_3_text);
                }
                tv_not_cancel_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        serviceDialog.dismiss();
                    }
                });
                btn_1_acitivty.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton1Clicked(not_btn_1_video_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                btn_2_webview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton2Clicked(not_btn_2_webview_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                btn_3_openurl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            onCheckServiceListner.onButton3Clicked(not_btn_3_url);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
                this.serviceDialog.show();

            }

        }

        if (isAd) {
            iv_ad_icon_title.setVisibility(View.VISIBLE);
            lay_updateApp.setVisibility(View.GONE);
            lay_message.setVisibility(View.GONE);
            lay_ads.setVisibility(View.VISIBLE);
            tv_dialog_title.setText(ad_dialog_title);

            if (ad_show_cancel) {
                tv_app_cancel.setVisibility(View.VISIBLE);
            } else {
                tv_app_cancel.setVisibility(View.GONE);
            }

            tv_ad_message.setText(ad_message);
            Glide.with(this).load(ad_banner_url).into(iv_ad_banner);
            Glide.with(this).load(ad_icon_url).into(iv_app_icon);
            tv_app_name.setText(ad_app_name);
            tv_app_shortdesc.setText(ad_app_short_desc);

            tv_app_download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ad_app_url));
                    startActivity(intent);
                }
            });

            tv_app_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    serviceDialog.dismiss();
                }
            });
            String link=ad_app_url;
            String[] s1 = link.split("id=");
            String[] s2 = s1[1].split("&");
            String app_id = s2[0].toString();
            Log.e("app_id_get",app_id);
            if (!appInstalledOrNot(app_id)){
                this.serviceDialog.show();
            }

            Log.e("app_id_installed",String.valueOf(appInstalledOrNot(app_id)));

        }

    }

    public boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }


    public void hideStatusBar() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void exitApp() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
        finish();
    }

    public void backAgainToExit() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            toast("Press again to exit");
            this.exitTime = System.currentTimeMillis();
            return;
        }
        exitApp();
    }

    private com.google.android.gms.ads.AdSize getAdSize() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;

        int adWidth = (int) (widthPixels / density);

        return com.google.android.gms.ads.AdSize.getPortraitAnchoredAdaptiveBannerAdSize(this, adWidth);
    }

//    private void initializeMoPubSDK() {
//
//        if (!mpInter1Initilized) {
//            if (adsPrefernce.showmpInter1()) {
//                Log.e("Ads..", "Inside Init mp Inter 1");
//                SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpInterId1())
//                        .withLegitimateInterestAllowed(false)
//                        .build();
//                MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
//            }
//        } else if (!mpInter2Initilized) {
//            Log.e("Ads..", "Inside Init mp Inter 2");
//            SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpInterId2())
//                    .withLegitimateInterestAllowed(false)
//                    .build();
//            MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
//        } else if (!mpBannerInitilized) {
//            Log.e("Ads..", "Inside Init mp Banner");
//            SdkConfiguration sdkConfiguration = new SdkConfiguration.Builder(adsPrefernce.mpBannerId())
//                    .withLegitimateInterestAllowed(false)
//                    .build();
//            MoPub.initializeSdk(this, sdkConfiguration, initSdkListener());
//        }
//
//    }

//    private SdkInitializationListener initSdkListener() {
//        return new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//                Log.e("Ads...", "onInitializationFinished");
//
//                if (!mpInter1Initilized) {
//                    if (adsPrefernce.showmpInter1()) {
//                        if (!isMpInter1Ready) {
//                            Log.e("Ads...", "isMpInter1Ready false");
//                            mpInterstitial1 = new MoPubInterstitial(BaseClass.this, adsPrefernce.mpInterId1());
//                            mpInterstitial1.load();
//                            mpInterstitial1.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                @Override
//                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                    Log.e("Ads...", "isMpInter1Ready true");
//                                    isMpInter1Ready = true;
//                                }
//
//                                @Override
//                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialShown(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                }
//                            });
//                            initializeMoPubSDK();
//
//                        } else if (!mpInter2Initilized) {
//                            initializeMoPubSDK();
//                        }
//                    } else if (!mpInter2Initilized) {
//                        initializeMoPubSDK();
//                    }
//                    mpInter1Initilized = true;
//                } else if (!mpInter2Initilized) {
//                    if (adsPrefernce.showmpInter2()) {
//                        if (!isMpInter2Ready) {
//                            mpInterstitial2 = new MoPubInterstitial(BaseClass.this, adsPrefernce.mpInterId2());
//                            mpInterstitial2.load();
//                            mpInterstitial2.setInterstitialAdListener(new MoPubInterstitial.InterstitialAdListener() {
//                                @Override
//                                public void onInterstitialLoaded(MoPubInterstitial interstitial) {
//                                    isMpInter2Ready = true;
//                                }
//
//                                @Override
//                                public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialShown(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialClicked(MoPubInterstitial interstitial) {
//
//                                }
//
//                                @Override
//                                public void onInterstitialDismissed(MoPubInterstitial interstitial) {
//
//                                }
//                            });
//                            if (!mpBannerInitilized) {
//                                if (adsPrefernce.showmpBanner()) {
//                                    initializeMoPubSDK();
//                                }
//                            }
//
//                        } else if (!mpBannerInitilized) {
//                            if (adsPrefernce.showmpBanner()) {
//                                initializeMoPubSDK();
//                            }
//                        }
//
//                    } else if (!mpBannerInitilized) {
//                        if (adsPrefernce.showmpBanner()) {
//                            initializeMoPubSDK();
//                        }
//                    }
//                    mpInter2Initilized = true;
//
//                } else if (!mpBannerInitilized) {
//                    mpBannerInitilized = true;
//                }
//
//
//            }
//
//        };
//    }


//    private SdkInitializationListener initSdkListenerBanner(Boolean loadOnInitilized) {
//        return new SdkInitializationListener() {
//            @Override
//            public void onInitializationFinished() {
//
//                if (loadOnInitilized) {
//
//                }
//
//            }
//        };
//    }

    boolean verifyInstallerId(Context context) {
        // A list with valid installers package name
        List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));

// The package name of the app that has installed your app
        final String installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());

        // true if your app has been downloaded from Play Store
        return installer != null && validInstallers.contains(installer);
    }

    public void validateInstall(Callable<Void> functionToCall) {
        if (!adsPrefernce.allowAccess()) {
            Log.e("validation", "allowAccess false");
            if (!isvalidInstall) {
                Log.e("validation", "isvalidInstall false");
                try {
                    functionToCall.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void showConsentDialog(Context context) {
        BottomSheetDialog consentDialog = new BottomSheetDialog(context, R.style.ConsentDialogTheme);
        consentDialog.setCancelable(false);
        consentDialog.setContentView(R.layout.dialog_consent);
        Window window = consentDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        window.setAttributes(wlp);
        Objects.requireNonNull(consentDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        TextView tv_positive = consentDialog.findViewById(R.id.tv_positive);
        TextView tv_negative = consentDialog.findViewById(R.id.tv_negative);
        TextView tv_consentdetails = consentDialog.findViewById(R.id.tv_consentdetails);
        TextView p1 = consentDialog.findViewById(R.id.tv_p1);
        TextView p2 = consentDialog.findViewById(R.id.tv_p2);
        TextView p3 = consentDialog.findViewById(R.id.tv_p3);
        TextView p4 = consentDialog.findViewById(R.id.tv_p4);
        TextView p5 = consentDialog.findViewById(R.id.tv_p5);
        TextView p6 = consentDialog.findViewById(R.id.tv_p6);
        TextView p7 = consentDialog.findViewById(R.id.tv_p7);
        TextView p8 = consentDialog.findViewById(R.id.tv_p8);

        p1.setMovementMethod(LinkMovementMethod.getInstance());
        p2.setMovementMethod(LinkMovementMethod.getInstance());
        p3.setMovementMethod(LinkMovementMethod.getInstance());
        p4.setMovementMethod(LinkMovementMethod.getInstance());
        p5.setMovementMethod(LinkMovementMethod.getInstance());
        p6.setMovementMethod(LinkMovementMethod.getInstance());
        p7.setMovementMethod(LinkMovementMethod.getInstance());
        p8.setMovementMethod(LinkMovementMethod.getInstance());
        tv_consentdetails.setMovementMethod(LinkMovementMethod.getInstance());
        tv_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consentDialog.dismiss();
                adsPrefernce.setConsent(true);
                adsPrefernce.setConsentShown(true);

                // iron source user consent
                IronSource.setConsent(adsPrefernce.getConsent());
                IronSource.setMetaData("do_not_sell", String.valueOf(adsPrefernce.getConsent()));

                //inMobi User Consent
                JSONObject consentObject = new JSONObject();
                int gdpr;
                try {
                    // Provide correct consent value to sdk which is obtained by User
                    consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, adsPrefernce.getConsent());
                    // Provide 0 if GDPR is not applicable and 1 if applicable
                    if (adsPrefernce.getConsent()) {
                        gdpr = 1;
                    } else {
                        gdpr = 0;
                    }
                    consentObject.put("gdpr", gdpr);
                    // Provide user consent in IAB format
                    // consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, “<<consent in IAB format>>”);
                    InMobiSdk.updateGDPRConsent(consentObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // StartApp User Consent

//                StartAppSDK.setUserConsent(context,
//                        "pas",
//                        System.currentTimeMillis(),
//                        adsPrefernce.getConsent());
//                PersonalInfoManager personalInfoManager = MoPub.getPersonalInformationManager();
//                if (adsPrefernce.getConsent()) {
//                    if (personalInfoManager != null) {
//                        personalInfoManager.grantConsent();
//                    }
//                } else {
//                    if (personalInfoManager != null) {
//                        personalInfoManager.revokeConsent();
//                    }
//                }
//                Appnext.setParam("consent", String.valueOf(adsPrefernce.getConsent()));
            }
        });
        tv_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consentDialog.dismiss();
                adsPrefernce.setConsent(false);
                adsPrefernce.setConsentShown(true);

                // iron source user consent
                IronSource.setConsent(adsPrefernce.getConsent());
                IronSource.setMetaData("do_not_sell", String.valueOf(adsPrefernce.getConsent()));

                //inMobi User Consent
                JSONObject consentObject = new JSONObject();
                int gdpr;
                try {
                    // Provide correct consent value to sdk which is obtained by User
                    consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, adsPrefernce.getConsent());
                    // Provide 0 if GDPR is not applicable and 1 if applicable
                    if (adsPrefernce.getConsent()) {
                        gdpr = 1;
                    } else {
                        gdpr = 0;
                    }
                    consentObject.put("gdpr", gdpr);
                    // Provide user consent in IAB format
                    // consentObject.put(InMobiSdk.IM_GDPR_CONSENT_IAB, “<<consent in IAB format>>”);
                    InMobiSdk.updateGDPRConsent(consentObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                StartAppSDK.setUserConsent(context,
//                        "pas",
//                        System.currentTimeMillis(),
//                        adsPrefernce.getConsent());

//                PersonalInfoManager personalInfoManager = MoPub.getPersonalInformationManager();
//                if (adsPrefernce.getConsent()) {
//                    if (personalInfoManager != null) {
//                        personalInfoManager.grantConsent();
//                    }
//                } else {
//                    if (personalInfoManager != null) {
//                        personalInfoManager.revokeConsent();
//                    }
//                }
//                Appnext.setParam("consent", String.valueOf(adsPrefernce.getConsent()));
            }
        });

        if (!adsPrefernce.isConsentShown()) {
            consentDialog.show();
        }

    }

}
