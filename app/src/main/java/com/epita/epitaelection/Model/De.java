package com.epita.epitaelection.Model;

import android.graphics.drawable.Drawable;

import com.epita.epitaelection.R;

public class De {
    private int face;
    private int drawable;

    public De(int face, int drawable) {
        this.face = face;
        this.drawable = drawable;
    }

    public De() {
        this.face = 1;
        this.drawable = R.drawable.de1;
    }

    public int getFace() {
        return face;
    }

    public void setFace(int face) {
        this.face = face;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
