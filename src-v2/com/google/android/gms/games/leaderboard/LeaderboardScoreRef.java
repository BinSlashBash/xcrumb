/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.database.CharArrayBuffer
 *  android.net.Uri
 */
package com.google.android.gms.games.leaderboard;

import android.database.CharArrayBuffer;
import android.net.Uri;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.b;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;

public final class LeaderboardScoreRef
extends b
implements LeaderboardScore {
    private final PlayerRef Mg;

    LeaderboardScoreRef(DataHolder dataHolder, int n2) {
        super(dataHolder, n2);
        this.Mg = new PlayerRef(dataHolder, n2);
    }

    @Override
    public boolean equals(Object object) {
        return LeaderboardScoreEntity.a(this, object);
    }

    @Override
    public /* synthetic */ Object freeze() {
        return this.hF();
    }

    @Override
    public String getDisplayRank() {
        return this.getString("display_rank");
    }

    @Override
    public void getDisplayRank(CharArrayBuffer charArrayBuffer) {
        this.a("display_rank", charArrayBuffer);
    }

    @Override
    public String getDisplayScore() {
        return this.getString("display_score");
    }

    @Override
    public void getDisplayScore(CharArrayBuffer charArrayBuffer) {
        this.a("display_score", charArrayBuffer);
    }

    @Override
    public long getRank() {
        return this.getLong("rank");
    }

    @Override
    public long getRawScore() {
        return this.getLong("raw_score");
    }

    @Override
    public Player getScoreHolder() {
        if (this.ai("external_player_id")) {
            return null;
        }
        return this.Mg;
    }

    @Override
    public String getScoreHolderDisplayName() {
        if (this.ai("external_player_id")) {
            return this.getString("default_display_name");
        }
        return this.Mg.getDisplayName();
    }

    @Override
    public void getScoreHolderDisplayName(CharArrayBuffer charArrayBuffer) {
        if (this.ai("external_player_id")) {
            this.a("default_display_name", charArrayBuffer);
            return;
        }
        this.Mg.getDisplayName(charArrayBuffer);
    }

    @Override
    public Uri getScoreHolderHiResImageUri() {
        if (this.ai("external_player_id")) {
            return null;
        }
        return this.Mg.getHiResImageUri();
    }

    @Override
    public String getScoreHolderHiResImageUrl() {
        if (this.ai("external_player_id")) {
            return null;
        }
        return this.Mg.getHiResImageUrl();
    }

    @Override
    public Uri getScoreHolderIconImageUri() {
        if (this.ai("external_player_id")) {
            return this.ah("default_display_image_uri");
        }
        return this.Mg.getIconImageUri();
    }

    @Override
    public String getScoreHolderIconImageUrl() {
        if (this.ai("external_player_id")) {
            return this.getString("default_display_image_url");
        }
        return this.Mg.getIconImageUrl();
    }

    @Override
    public String getScoreTag() {
        return this.getString("score_tag");
    }

    @Override
    public long getTimestampMillis() {
        return this.getLong("achieved_timestamp");
    }

    public LeaderboardScore hF() {
        return new LeaderboardScoreEntity(this);
    }

    @Override
    public int hashCode() {
        return LeaderboardScoreEntity.a(this);
    }

    public String toString() {
        return LeaderboardScoreEntity.b(this);
    }
}

