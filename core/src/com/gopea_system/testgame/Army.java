package com.gopea_system.testgame;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Андрей on 10.02.2017.
 */

public class Army {
    int soldat, serjant, officer;
    int fighttime;
    int Level_of_army, typearm;
    double soldatType, serjantType, officerType, soldatTypedisc;
    double tecnologies_bonus;
    float speed;
    Rectangle rect = new Rectangle();

    Texture texturearm;

    /*
    Standart army = 35 (30 soldat, 3(4) - serjant, 1(2) - officer)
     */
    public Army(int typearm, int soldat, int serjant, int officer, int level_of_army, double tecnologies_bonus, double soldatType, double soldatTypedisc, double serjantType, double officerType, float speed) {
        this.typearm = typearm;
        this.soldat = soldat;
        this.serjant = serjant;
        this.officer = officer;
        this.Level_of_army = level_of_army;
        this.tecnologies_bonus = tecnologies_bonus;
        this.soldatType = soldatType;
        this.soldatTypedisc = soldatTypedisc;
        this.serjantType = serjantType;
        this.officerType = officerType;
        this.speed = speed;

        rect.width = 80;
        rect.height = 60;
        setTexturearm();


    }


    public void setTexturearm() {
        switch (typearm) {
            case 0:
                texturearm = new Texture("irregular.png");
                break;
            case 1:
                texturearm = new Texture("horse.png");
                break;
        }
    }

    public void setX(float x) {
        this.rect.x = x;
    }

    public void setY(float y) {
        this.rect.y = y;
    }

    public void setSpeed(float speed1) {
        this.speed = speed1;
    }

    public float getX() {
        float t = this.rect.x;
        return t;
    }

    public float getY() {
        float t = this.rect.y;
        return t;
    }

    public int getSoldat() {
        int getsold = soldat;
        return getsold;
    }

    public int getSerjant() {
        int getserjant = serjant;
        return getserjant;
    }

    public int getOfficer() {
        int getsold = officer;
        return getsold;
    }

    public void setSostav(int soldat, int serjant, int officer) {
        this.soldat = soldat;
        this.serjant = serjant;
        this.officer = officer;
    }

    public int grtFightTime() {
        int t = fighttime;
        return t;
    }

    public void setFighttime(int fightime) {
        this.fighttime = fightime;
    }

    /*
        This method count moral of army. Метод считает Мораль (Боевой дух) подразделения.
     */
    public double Moral(double Kbonus, double Hard) {

        double d = (soldat * 3 * soldatType + serjant * serjantType * 7 + officer * officerType * 17.5)
                * Kbonus * K_Level() * tecnologies_bonus * Hard / Ustalost();
        return d;
    }

    public double Disciplina(double bonus_disc, double Hard) {
        double Dis = -1;
        Dis = (soldat * soldatTypedisc + serjant * 1.5 + officer * 3.5) * (1 + ((officer) / (soldat + serjant)) * 11) *
                (1 + (serjant * 4 / soldat)) * K_Level() * bonus_disc * Hard / Math.pow(Ustalost(), 0.75);
        return Dis;
    }

    public double K_Level() {
        double K_lev = -1;
        switch (Level_of_army) {
            case 1:
                K_lev = 1.0;
                break;
            case 2:
                K_lev = 1.075;
                break;
            case 3:
                K_lev = 1.145;
                break;
            case 4:
                K_lev = 1.21;
                break;
            case 5:
                K_lev = 1.26;
                break;
            case 6:
                K_lev = 1.3;
                break;
            case 7:
                K_lev = 1.3375;
                break;
            case 8:
                K_lev = 1.3725;
                break;
            case 9:
                K_lev = 1.405;
                break;
            case 10:
                K_lev = 1.435;
                break;
            default:
                K_lev = 1.435 + 0.0295 * (Level_of_army - 10);
                break;
        }
        return K_lev;
    }

    public double Ustalost() {
        double Ust = Math.pow(Math.E, (fighttime / 40)) / 2.5 + 0.6;
        if (Ust < 0) {
            /*
            Вывести лог об ошибке.
             */
        }
        return Ust;
    }

    public void draw(Batch b, Camera camera) {
        SpriteBatch sb = new SpriteBatch();
        camera.update();
        sb.setProjectionMatrix(camera.combined);
        b.end();
        sb.begin();
        sb.draw(texturearm, rect.x, rect.y);
        sb.end();
        b.begin();
    }
}
