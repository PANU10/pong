package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Ball {
    int x, y, r;
    int speedX, speedY;
    float speedFactor;

    void render(ShapeRenderer sr) {
        sr.setColor(0, 1, 0, 1);
        sr.circle(x, y, r);
    }
}
