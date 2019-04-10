package com.pio.battleship;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class Battleship extends ApplicationAdapter {

	private Game MyGame;
	private EventListener MyEventListener;
	private SpriteBatch Window;
	private long Clock;

	@Override
	public void create ( ) { // On start

		MyGame = new Game();
		MyEventListener = new EventListener();
		Window = new SpriteBatch();
		Clock = TimeUtils.millis(); }

	@Override
	public void render ( ) { // Main loop

		// Process events
		while ( MyEventListener.isEventAvailable() ) {

			MyGame.process( MyEventListener.getEvent() ); }

		// Get elapsed time
		float ElapsedTime = TimeUtils.timeSinceMillis( Clock ) / 1000.f;
		Clock = TimeUtils.millis();

		// Process
		MyGame.process(ElapsedTime);

		// Check finish condition
		if ( MyGame.isFinished() ) {

			Gdx.app.exit(); }

		// Clear background
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render
		MyGame.render(Window); }
	
	@Override
	public void dispose ( ) { // On finish

		Window.dispose(); }

	}
