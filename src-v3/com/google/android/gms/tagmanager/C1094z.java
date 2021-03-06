package com.google.android.gms.tagmanager;

import android.content.Context;
import android.provider.Settings.Secure;
import com.google.android.gms.internal.C0321a;
import com.google.android.gms.internal.C0355d.C1367a;
import java.util.Map;

/* renamed from: com.google.android.gms.tagmanager.z */
class C1094z extends aj {
    private static final String ID;
    private final Context mContext;

    static {
        ID = C0321a.DEVICE_ID.toString();
    }

    public C1094z(Context context) {
        super(ID, new String[0]);
        this.mContext = context;
    }

    protected String m2569G(Context context) {
        return Secure.getString(context.getContentResolver(), "android_id");
    }

    public boolean jX() {
        return true;
    }

    public C1367a m2570x(Map<String, C1367a> map) {
        String G = m2569G(this.mContext);
        return G == null ? dh.lT() : dh.m1471r(G);
    }
}
