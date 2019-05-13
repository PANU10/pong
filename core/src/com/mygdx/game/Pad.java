package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Pad {

    int x, y;
    int w, h;
    int r,g,b;
    int speed;
    int goals;

    void render(ShapeRenderer sr){
        sr.setColor(0,0,0,1);
        sr.rect(x, y, w, h);
    }
}
