package com.gopea_system.testgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by Андрей on 24.02.2017.
 */

public class Enemy {
    ArrayList<Army>[] enemysArmy;
    int level;
    public Enemy(int levvel){
        this.level = levvel;
        enemysArmy = new ArrayList[4];
        for (int i = 0; i < 4; i++) {
            enemysArmy[i] = new ArrayList<Army>();
        }
    }
public void draw(Batch batch, OrthographicCamera camera) {
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < enemysArmy[i].size(); j++) {
            enemysArmy[i].get(j).draw(batch, camera);
        }
    }
}

}
