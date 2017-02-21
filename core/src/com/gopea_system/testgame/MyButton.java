package com.gopea_system.testgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Андрей on 12.02.2017.
 */

public class MyButton {
    Texture butTexture, butTextureOn, butTextureN;
    float x, y, widht, height;
    BitmapFont font;
    Rectangle rect = new Rectangle();
    Drawable drawable;
    ImageButton IButton;
    boolean button_pressed = false;
    int index;
    double price;

    public MyButton(int index, String fileName, String fileNameon, float x, float y, float widht, float height, double price) {
        butTextureN = new Texture(fileName);
        butTextureOn = new Texture(fileNameon);
        butTexture = butTextureN;
        // special not very good work
        drawable = new TextureRegionDrawable(new TextureRegion(butTexture));
        IButton = new ImageButton(drawable);
        IButton.setSize(widht, height);
        this.price = price;
        this.x = x;
        this.y = y;
        this.widht = widht;
        this.height = height;
        this.index = index;
        makeRect();
        setLocation();
    }

    public void makeRect() {
        rect.x = x;
        rect.y = y;
        rect.width = widht;
        rect.height = height;
    }

    public void setLocation() {
        IButton.setPosition(x, y);
    }

    public boolean isClick(OrthographicCamera camera, double money) {
        Vector3 touchpos = new Vector3();
        Vector3 touchposbefore = new Vector3();
        boolean click = false;
        if (Gdx.input.isTouched()) {

            touchpos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            if (touchpos.x != touchposbefore.x && touchpos.y != touchposbefore.y) {
                touchposbefore.set(touchpos.x, touchpos.y, touchpos.z);
                String s = "touchpos = (" + touchpos.x + ", " + touchpos.y + ", " + touchpos.z + ");";
                Gdx.app.log("my tag", s);
                s = "touchposbefore = (" + touchposbefore.x + ", " + touchposbefore.y + ", " + touchposbefore.z + ");";
                Gdx.app.log("my tag", s);

                camera.unproject(touchpos);

                if (touchpos.x >= x && touchpos.x <= x + widht && touchpos.y >= y && touchpos.y <= y + height & (money - price >= 0)) {
                    click = true;
                    button_pressed = true;
                    butTexture = butTextureOn;
                    Gdx.app.log("My tag", "My button. isClick = true");
                }
            }
        }

        return click;
    }

    public void draw(Batch b, Camera camera) {
        SpriteBatch sb = new SpriteBatch();
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        b.end();
        sb.begin();
        sb.draw(butTexture, x, y);
        sb.end();
        b.begin();

    }

    public void deselect() {
        button_pressed = false;
        butTexture = butTextureN;
    }
}
