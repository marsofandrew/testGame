package com.gopea_system.testgame;

import java.util.ArrayList;

/**
 * Created by Андрей on 10.02.2017.
 */

public class Fight {

    public Fight(){

    }
    public void fightArm(ArrayList<Army> player, ArrayList<Army> enemy){
        if (player.get(0).getFightRect().overlaps(enemy.get(0).rect)){



            // at the end of this method
            player.get(0).addFighttime(1);// Увеличили время нахождения в бою
            enemy.get(0).addFighttime(1);
            checkSostav(player);
            checkSostav(enemy);

        }
    }
    private void checkSostav(ArrayList<Army> armys){
        if (armys.get(0).getSostav()<=0){
            armys.remove(0);
        }
    }
}
