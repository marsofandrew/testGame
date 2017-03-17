package com.gopea_system.testgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;

/**
 * Created by Андрей on 05.03.2017.
 */

public class Player {
    ArrayList<Army>[] playersArmy;

    public Player() {
        playersArmy = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            playersArmy[i] = new ArrayList<Army>();
        }
    }

    public void draw(Batch batch, OrthographicCamera camera) {
        for (int i = 0; i < 4; i++) {
            for (Army army:playersArmy[i]){
                army.draw(batch,camera);
            }
        }
    }

}
