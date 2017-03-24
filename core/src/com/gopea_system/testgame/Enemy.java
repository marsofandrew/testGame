package com.gopea_system.testgame;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Андрей on 24.02.2017.
 */

public class Enemy {
    double profit = 0.5;
    double Money;
    double tech_bonus = 1;
    ArrayList<Army>[] enemysArmy;
    int maxtypeofArmy = 1, minTypeofArm = 0;
    double timeBetweenGenerate;
    double timeToGenerate[] = new double[4];


    int level;
    int maxArmsperLine = 10;
    ConstantOfArmys cofArm = new ConstantOfArmys();

    public Enemy(int levvel) {
        this.level = levvel;
        enemysArmy = new ArrayList[4];
        setTimeBetweenGenerate(levvel);
        for (int i = 0; i < 4; i++) {
            enemysArmy[i] = new ArrayList<Army>();
        }
        for (int i = 0; i < 4; i++) {
            timeToGenerate[i] = timeBetweenGenerate;
        }
    }


    public void draw(Batch batch, OrthographicCamera camera) {
        for (int i = 0; i < 4; i++) {
            for (Army army : enemysArmy[i]) {
                army.draw(batch, camera);
            }
        }
    }

    public void setTimeToGenerate(double dt) {
        for (int i = 0; i < 4; i++) {
            timeToGenerate[i] -= dt;
        }
    }

    private void setTimeBetweenGenerate(int lev) {
        timeBetweenGenerate = 3;
        switch (lev) {
            case 0:

                break;
            case 1:

                break;
            default:
        }
    }

    public void createArmy(int line, int type, int soldat, int serjant, int officer, float speed) {
        enemysArmy[line].add(new Army(type, soldat, serjant, officer, countLevelOfArmy(), tech_bonus, countSoldatType(),
                countSoldatDiscType(), countSerjantType(), countOfficerType(), speed));
        int length = enemysArmy[line].size();
        enemysArmy[line].get(length - 1).setX(10 + line * 100);
        enemysArmy[line].get(length - 1).setY(1360);
    }

    public int countLevelOfArmy() {
        int cL = -1;
        if (level <= 5) cL = 1;
        if (level > 5 & level <= 10) cL = 2;
        if (level > 10 & level < 15) cL = 3;
        if ((level >= 15) & (level <= 17)) cL = 4;
        if ((level >= 18) & (level <= 20)) cL = 5;
        if ((level >= 21) & (level <= 23)) cL = 6;
        if ((level >= 24) & (level <= 26)) cL = 7;
        if ((level >= 27) & (level <= 29)) cL = 8;
        if ((level >= 30) & (level <= 31)) cL = 9;
        if ((level >= 32) & (level <= 35)) cL = 10;
        if (level > 35) cL = 10 + level - 35;

        return cL;
    }

    public double countSoldatType() {
        double r = 1;
        //write
        return r;
    }

    public double countSerjantType() {
        double r = 1;
        //write
        return r;
    }

    public double countOfficerType() {
        double r = 1;
        //write
        return r;
    }

    public double countSoldatDiscType() {
        double r = 1;
        //write
        return r;
    }

    public void addResource() {
        Money += profit;

    }

    private double getkTime(int kolvo) {
        double a = 1 + 0.1 * kolvo;
        return 1/a;
    }

    public void makeArmy(ArrayList<Army> plaerArmy[]) {
        // возможно if и for нужно поменять местами
        for (int i = 0; i < 4; i++) {
            if (enemysArmy[i].size() < maxArmsperLine) {
                if (timeToGenerate[i] <= 0) {
                    int ptype = MathUtils.random(-2, 2);
                    int l = enemysArmy[i].size();
                    if (l < plaerArmy[i].size()) {
                        ptype += plaerArmy[i].get(l).typearm;
                        if (ptype > maxtypeofArmy) {
                            ptype = maxtypeofArmy;
                        }
                        if (ptype < minTypeofArm) {
                            ptype = minTypeofArm;
                        }
                    } else {
                        ptype = new Random().nextInt(maxtypeofArmy);
                    }
                    createArmy(i, ptype, 30, 4, 1, -cofArm.ArmysSpeed.get(ptype));
                    timeToGenerate[i] = timeBetweenGenerate * MathUtils.random(1.0f, 2.0f) * getkTime(plaerArmy[i].size());
                }
            }
        }
    }
}
