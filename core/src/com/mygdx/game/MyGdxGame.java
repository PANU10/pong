package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer sr;

	Table table;
	Ball ball;  // NULL
	Pad pad1, pad2;
	Marcador marcador;

	@Override
	public void create() {
		Gdx.graphics.setWindowedMode(700, 400);
		Gdx.graphics.setResizable(false);
		sr = new ShapeRenderer();

		ball = new Ball();
		marcador = new Marcador();
		marcador.create();


		pad1 = new Pad();
		pad2 = new Pad();

		ball.x = 350;
		ball.y = 200;
		ball.r = 10;

		pad1.x = 45;
		pad1.y = 0;
		pad1.w = 10;
		pad1.h = 60;

		pad2.x = 650;
		pad2.y = 0;
		pad2.w = 10;
		pad2.h = 60;

		pad1.speed = 9;
		pad2.speed = 9;

		ball.speedX = 3;
		ball.speedY = 3;
		ball.speedFactor = 1.4f;

		marcador.w = 4;
		marcador.h = 18;
		marcador.x = 350;
		marcador.y = 200;
	}

	void update() {
		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
			pad1.y += pad1.speed;
		} else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
			pad1.y -= pad1.speed;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			pad2.y += pad2.speed;
		} else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			pad2.y -= pad2.speed;
		}

		ball.x += ball.speedX;
		ball.y += ball.speedY;

		checkCollisions();
	}

	private void checkCollisions() {
		checkPadsInWorld();
		checkBallRebounds();
		checkBallInPads();
		checkGoal();
	}

	private void checkGoal() {
		if (ball.x <= 0) {
			pad2.goals++;
			ball.x = 350;
			ball.y = 200;

			ball.speedX = 3;
			ball.speedY = 3;
		}

		if (ball.x >= 700) {
			pad1.goals++;
			ball.x = 350;
			ball.y = 200;

			ball.speedX = 3;
			ball.speedY = 3;
		}

	}

	private void checkBallInPads() {
		Circle ballCircle = new Circle(ball.x, ball.y, ball.r);
		Rectangle pad1Rect = new Rectangle(pad1.x, pad1.y, pad1.w, pad1.h);
		Rectangle pad2Rect = new Rectangle(pad2.x, pad2.y, pad2.w, pad2.h);

		if (Intersector.overlaps(ballCircle, pad1Rect)) {
			ball.speedY *= +ball.speedFactor;
			ball.speedX *= -ball.speedFactor;
		}

		if (Intersector.overlaps(ballCircle, pad2Rect)) {
			ball.speedY *= +ball.speedFactor;
			ball.speedX *= -ball.speedFactor;
		}
	}

	private void checkBallRebounds() {
		if (ball.y > 400) {
			ball.speedY *= -1;
		}

		if (ball.y < 0) {
			ball.speedY *= -1;
		}
	}

	private void checkPadsInWorld() {
		if (pad1.y > 400 - pad1.h) {
			pad1.y = 400 - pad1.h;
		}

		if (pad2.y > 400 - pad2.h) {
			pad2.y = 400 - pad2.h;
		}

		if (pad1.y < 0) {
			pad1.y = 0;
		}

		if (pad2.y < 0) {
			pad2.y = 0;
		}
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		sr.begin(ShapeRenderer.ShapeType.Filled);

		ball.render(sr);

		pad1.render(sr);
		pad2.render(sr);
		marcador.render(pad1, pad2);

		sr.end();
		System.out.println("P1 " + pad1.goals + "    VS    " + pad2.goals + " P2");
	}
}





