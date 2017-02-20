package com.gopea_system.testgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gopea_system.testgame.TestGameClass;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "My test Game";
        config.width = 480;
        config.height = 800;

        new LwjglApplication(new TestGameClass(), config);

    }
}
