/*
 * Decompiled with CFR 0_110.
 */
package com.google.android.gms.internal;

import com.google.android.gms.internal.dp;

public abstract class do {
    private final Runnable kW;
    private volatile Thread qY;

    public do() {
        this.kW = new Runnable(){

            @Override
            public final void run() {
                do.this.qY = Thread.currentThread();
                do.this.aY();
            }
        };
    }

    public abstract void aY();

    public final void cancel() {
        this.onStop();
        if (this.qY != null) {
            this.qY.interrupt();
        }
    }

    public abstract void onStop();

    public final void start() {
        dp.execute(this.kW);
    }

}

