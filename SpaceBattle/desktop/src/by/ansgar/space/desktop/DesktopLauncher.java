package by.ansgar.space.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import by.ansgar.space.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Main.TITLE;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
