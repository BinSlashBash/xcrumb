/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 */
package com.google.android.gms.ads;

import android.content.Context;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.purchase.InAppPurchaseListener;
import com.google.android.gms.internal.as;
import com.google.android.gms.internal.au;

public final class InterstitialAd {
    private final au kv;

    public InterstitialAd(Context context) {
        this.kv = new au(context);
    }

    public AdListener getAdListener() {
        return this.kv.getAdListener();
    }

    public String getAdUnitId() {
        return this.kv.getAdUnitId();
    }

    public InAppPurchaseListener getInAppPurchaseListener() {
        return this.kv.getInAppPurchaseListener();
    }

    public boolean isLoaded() {
        return this.kv.isLoaded();
    }

    public void loadAd(AdRequest adRequest) {
        this.kv.a(adRequest.O());
    }

    public void setAdListener(AdListener adListener) {
        this.kv.setAdListener(adListener);
    }

    public void setAdUnitId(String string2) {
        this.kv.setAdUnitId(string2);
    }

    public void setInAppPurchaseListener(InAppPurchaseListener inAppPurchaseListener) {
        this.kv.setInAppPurchaseListener(inAppPurchaseListener);
    }

    public void show() {
        this.kv.show();
    }
}

