package com.google.android.gms.tagmanager;

import android.os.Build.VERSION;
import com.google.android.gms.internal.C0321a;
import com.google.android.gms.internal.C0355d.C1367a;
import java.util.Map;

class cu extends aj {
    private static final String ID;

    static {
        ID = C0321a.SDK_VERSION.toString();
    }

    public cu() {
        super(ID, new String[0]);
    }

    public boolean jX() {
        return true;
    }

    public C1367a m2506x(Map<String, C1367a> map) {
        return dh.m1471r(Integer.valueOf(VERSION.SDK_INT));
    }
}
