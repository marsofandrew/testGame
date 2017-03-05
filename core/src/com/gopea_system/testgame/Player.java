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
            for (int j = 0; j < playersArmy[i].size(); j++) {
                playersArmy[i].get(j).draw(batch, camera);
            }
        }
    }

}
