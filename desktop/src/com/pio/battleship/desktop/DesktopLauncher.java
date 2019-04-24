package com.pio.battleship.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pio.battleship.Battleship;

public class DesktopLauncher {

	public static void main ( String[] Args ) {

		LwjglApplicationConfiguration Config = new LwjglApplicationConfiguration();

		Config.width = 800;
		Config.height = 800;

		new LwjglApplication( new Battleship(), Config ); } }
