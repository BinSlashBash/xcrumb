/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 */
package com.google.android.gms.games.internal.player;

import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.internal.player.ExtendedPlayer;
import com.google.android.gms.games.internal.player.ExtendedPlayerEntity;

public final class ExtendedPlayerRef
extends b
implements ExtendedPlayer {
    private final PlayerRef LL;

    ExtendedPlayerRef(DataHolder dataHolder, int n2) {
        super(dataHolder, n2);
        this.LL = new PlayerRef(dataHolder, n2);
    }

    @Override
    public /* synthetic */ Object freeze() {
        return this.hy();
    }

    @Override
    public Player getPlayer() {
        return this.LL;
    }

    @Override
    public String hu() {
        return this.getString("most_recent_external_game_id");
    }

    @Override
    public long hv() {
        return this.getLong("most_recent_activity_timestamp");
    }

    @Override
    public Uri hw() {
        return this.ah("game_icon_image_uri");
    }

    public ExtendedPlayer hy() {
        return new ExtendedPlayerEntity(this);
    }
}

