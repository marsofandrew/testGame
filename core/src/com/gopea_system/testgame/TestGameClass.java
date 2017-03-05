package com.gopea_system.testgame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;


public class TestGameClass extends ApplicationAdapter {
    SpriteBatch batch;
    Stage stage;
    Texture background;
    BitmapFont font;
    OrthographicCamera camera;
    Array<MyButton> button = new Array<MyButton>();
    MyButton butCanceled;
    Player player;
    Enemy enem;
    double Coins = 100;
    double profit;
    int level = 0;//test remove later

    @Override

    public void create() {
        player = new Player();
        enem = new Enemy(level);
        font = new BitmapFont();
        stage = new Stage();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 800);
        batch = new SpriteBatch();
        background = new Texture("background1.png");



        butCanceled = new MyButton(-1, "bcancel.png", "bcancel.png", 400, 0, 80, 60, 0);
        butCanceled.setiIShow(false);
        create_buttons(new ArrayList<Integer>(Arrays.asList(0, 1)));
        Gdx.input.setInputProcessor(new GestureDetector(new MGListener()));
    }

    public void create_buttons(ArrayList<Integer> arrIndex) {
        MyButton butMassive[] = new MyButton[2];
        butMassive[0] = new MyButton(0, "birr.png", "birron.png", 400, 740, 80, 60, 5);
        butMassive[1] = new MyButton(1, "blkavalery.png", "blkavaleryon.png", 400, 678, 80, 60, 10);
        for (int i : arrIndex) {
            if (i >= 0 & i < butMassive.length) {
                button.add(butMassive[i]);
            }
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // нужно некое условие movePole(1);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        font.draw(batch, "coins = " + Coins, 10, 40);
        batch.draw(background, 0, 40);
        for (int i = 0; i < button.size; i++) {
            button.get(i).draw(batch, camera);
        }
                player.draw(batch,camera);

        enem.draw(batch,camera);

        butCanceled.draw(batch, camera);
        batch.end();
        if (butCanceled.isClick(camera, 100)) {
            int i = getClicButton(button);
            if (i >= 0) {
                button.get(i).deselect();
                butCanceled.setiIShow(false);
                butCanceled.deselect();
            } else {
                Gdx.app.log("My tag", "No button pressed");
            }
        }
        for (int i = 0; i < button.size; i++) {
            if (canbeclicked(button)) {
                button.get(i).isClick(camera, Coins);
            }
            if (Gdx.input.isTouched()) {
                Vector3 posT = new Vector3();
                posT.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                camera.unproject(posT);
                if (posT.x >= 0 & posT.x <= 400 & !canbeclicked(button)) {
                    createPlayersArmy(posT);
                }

            }
        }
        moveArmys(player.playersArmy, true); // move player's army
        moveArmys(enem.enemysArmy, false); // move enemy's army
        removeArmys(player.playersArmy, true);
        removeArmys(enem.enemysArmy, false);
    }

    public void createPlayersArmy(Vector3 posT) {

        int butclick = getClicButton(button);

        if (Coins - button.get(butclick).price >= 0) {
            Coins -= button.get(butclick).price;
            player.playersArmy[get_Line(posT.x)].add(new Army(butclick, 30, 4, 1, 1, 1, 1, 1, 1, 1, 100));
            int length = player.playersArmy[get_Line(posT.x)].size();
            player.playersArmy[get_Line(posT.x)].get(length - 1).rect.x = get_Line(posT.x) * 100 + 10;
            player.playersArmy[get_Line(posT.x)].get(length - 1).rect.y = 40;
           butCanceled.setiIShow(false);

        }
        button.get(butclick).deselect();
    }

    public boolean canbeclicked(Array<MyButton> but) {
        boolean can = false;
        for (int i = 0; i < but.size; i++) {

            if (but.get(i).button_pressed) {
                can = false;
                break;
            } else {
                can = true;
            }
        }
        if (!can) {
            butCanceled.setiIShow(true);
        }
        return can;
    }

    public int get_Line(float x) {
        int x_line = -1;
        if (x >= 0 & x <= 100) {
            x_line = 0;
        }
        if (x > 100 & x <= 200) {
            x_line = 1;
        }
        if (x > 200 & x <= 300) {
            x_line = 2;
        }
        if (x > 300 & x <= 400) {
            x_line = 3;
        }

        if (x_line < 0) {
            Gdx.app.log("My tag", " Exception #1");
        }
        return x_line;
    }

    public int getClicButton(Array<MyButton> but) {
        int b = -1;
        for (int i = 0; i < but.size; i++) {

            if (but.get(i).button_pressed) {
                b = but.get(i).index;
            }
        }
        if (b < 0) {
            Gdx.app.log("My tag", "Exception 111");
        }
        return b;
    }

    public void moveArmys(ArrayList<Army>[] arm, boolean isPlayer) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arm[i].size(); j++) {
                float dy = arm[i].get(j).speed * Gdx.graphics.getDeltaTime();
                if (isPlayer) {
                    arm[i].get(j).setY(arm[i].get(j).getY() + dy);
                } else {
                    arm[i].get(j).setY(arm[i].get(j).getY() - dy);
                }
            }
        }
    }

    public void movePole(float y) {

        if (camera.position.y + y < 400 & y < 0) {
            Gdx.app.log("My tag", "CAmera don't moved " + camera.position.y);
            y = 0;
        }
        if (camera.position.y + y > 1000 & y > 0) {
            Gdx.app.log("My tag", "CAmera don't moved " + camera.position.y);
            y = 0;
        }
        Gdx.app.log("My tag", "Camera y = " + camera.position.y);
        camera.translate(0, y);

        for (int i = 0; i < button.size; i++) {
            button.get(i).y += y;
        }
        butCanceled.y += y;
    }

    public void removeArmys(ArrayList<Army>[] arm, boolean isPlayers) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < arm[i].size(); j++) {
                if (isPlayers) {
                    if (arm[i].get(j).getY() > 1500) {
                        arm[i].remove(j);
                    }
                } else {
                    if (arm[i].get(j).getY() < -100) {
                        arm[i].remove(j);
                    }
                }
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }

    public class MGListener implements GestureDetector.GestureListener {

        @Override
        public boolean touchDown(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            return false;
        }

        @Override
        public boolean longPress(float x, float y) {
            return false;
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {

            //if (velocityY >0)movePole(50);
            //if (velocityY<0) movePole(-50);

            return false;
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            Gdx.app.log("My tag", "pan y  = " + y + " delta y = " + deltaY);
            movePole(deltaY / 4);
            return false;
        }

        @Override
        public boolean panStop(float x, float y, int pointer, int button) {
            return false;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            return false;
        }

        @Override
        public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
            return false;
        }

        @Override
        public void pinchStop() {

        }
    }

}
