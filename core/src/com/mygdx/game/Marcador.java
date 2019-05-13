package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Marcador {

    int x, y;
    int w, h;
    int r,g,b;
    SpriteBatch batch;
    BitmapFont font;

    void create (){
        batch = new SpriteBatch();
        font = new BitmapFont();
    }

    void render(Pad pad1, Pad pad2){
        batch.begin();
        font.setColor(0f, 0f, 0f, 1f);
        font.draw(batch, "P1 " + pad1.goals + "    VS    " + pad2.goals + " P2", (Gdx.graphics.getWidth()/2) - 60, Gdx.graphics.getHeight()-5);
        batch.end();
    }


}
