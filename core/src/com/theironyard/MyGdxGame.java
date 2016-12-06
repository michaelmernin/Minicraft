package com.theironyard;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	TextureRegion stand, runUp, runDown, runRight, runLeft,
			sprintUp, sprintDown, sprintRight, sprintLeft, zombieRunUp, zombieRunDown,
			zombieRunRight, zombieStand, zombieRunLeft, toxicSludge, largeToxicSludge, tree, cactus;

	//boolean isAlive;

	float x, y, xv, yv;

	static final float MAX_RUN_VELOCITY = 130;
	static final float MAX_SPRINT_VELOCITY = 250;
	static final float ZOMBIE_MAX_RUN_VELOCITY = 180;

	//int playerHealth;


	@Override
	public void create() {


		batch = new SpriteBatch();

		//img = new TextureRegion();


		Texture tiles = new Texture("tiles.png");
		TextureRegion[][] grid = TextureRegion.split(tiles, 16, 16);


		stand = grid[6][2];
		runUp = grid[6][1];
		runDown = grid[6][0];
		runRight = grid[6][3];
		sprintUp = grid[7][1];
		sprintDown = grid[7][0];
		sprintRight = grid[7][3];
		runLeft = new TextureRegion(runRight);
		runLeft.flip(true, false);
		sprintLeft = new TextureRegion(sprintRight);
		sprintLeft.flip(true, false);
		zombieRunUp = grid[7][4];
		zombieRunDown = grid[7][5];
		zombieStand = grid[7][6];
		zombieRunRight = grid[7][7];
		zombieRunLeft = new TextureRegion(zombieRunRight);
		zombieRunLeft.flip(true, false);
		toxicSludge = grid[7][4];
		largeToxicSludge = grid[7][5];
		tree = grid[2][0];
		cactus = grid[2][1];



	}


	@Override
	public void render() {

		zombieMove();

		move();

		TextureRegion img = stand;



		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

			yv = MAX_RUN_VELOCITY;
			img = runUp;





		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_RUN_VELOCITY * -1;
			img = runDown;
			//y--;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_RUN_VELOCITY;
			img = runRight;
			//x++;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_RUN_VELOCITY * -1;
			img = runLeft;
			//x--;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
			yv = MAX_SPRINT_VELOCITY;
			img = sprintUp;
			//y++;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {

			yv = MAX_SPRINT_VELOCITY * -1;
			img = sprintDown;
			//y++;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
			xv = MAX_SPRINT_VELOCITY * -1;
			img = sprintLeft;
			//x++;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {

			xv = MAX_SPRINT_VELOCITY;
			img = sprintRight;
			//x++;
		}


		/*if (xv == 0 && yv == 0) {
			img = stand;
		}

		else if (yv > 0 && yv < MAX_SPRINT_VELOCITY) {
			img = runUp;

		}
		else if (yv > 0 && yv < MAX_SPRINT_VELOCITY && Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			img = runDown;
		}
		else if (xv > 0 && xv < MAX_SPRINT_VELOCITY) {
			img = runLeft;
		}
		else if (xv > 0 && xv < MAX_SPRINT_VELOCITY) {
			img = runRight;
		}
		else if (yv > MAX_RUN_VELOCITY) {
			img = sprintUp;
		}
		else if (yv > MAX_RUN_VELOCITY) {
			img = sprintDown;
		}
		else if (xv > MAX_RUN_VELOCITY) {
			img = sprintLeft;
		}
		else if (xv > MAX_RUN_VELOCITY) {
			img = sprintRight;

		} //else {
		//	img = stand;
		//	} */


		Gdx.gl.glClearColor(0.5f, 0.5f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}


	@Override
	public void dispose () {
		batch.dispose();



	}

	public void zombieMove() {

		TextureRegion img = zombieStand;

		for (xv = Math.random(float) * ZOMBIE_MAX_RUN_VELOCITY && yv = Math.random() * ZOMBIE_MAX_RUN_VELOCITY) {

			//xv = Math.random() * ZOMBIE_MAX_RUN_VELOCITY;
			//yv = Math.random() * ZOMBIE_MAX_RUN_VELOCITY;

			while (true) {

				if (xv > 0) {
					img = zombieRunRight;
				} else if (xv < 0) {
					img = zombieRunLeft;
				} else if (yv > 0) {
					img = zombieRunUp;
				} else if (yv < 0) {
					img = zombieRunDown;
				} else if (xv == 0 && yv == 0) {
					img = zombieStand;
				}


				y = y + (yv * Gdx.graphics.getDeltaTime());
				x = x + (xv * Gdx.graphics.getDeltaTime());

				yv = Math.random(stopping(yv));
				xv = stopping(xv);


			}
		}
	}


	public void move() {

		TextureRegion img = stand;


		//while (isAlive) {


		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {

			yv = MAX_RUN_VELOCITY;
			img = runUp;


			//y++;


		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			yv = MAX_RUN_VELOCITY * -1;
			img = runDown;
			//y--;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			xv = MAX_RUN_VELOCITY;
			img = runRight;
			//x++;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			xv = MAX_RUN_VELOCITY * -1;
			img = runLeft;
			//x--;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.UP) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
			yv = MAX_SPRINT_VELOCITY;
			img = sprintUp;
			//y++;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {

			yv = MAX_SPRINT_VELOCITY * -1;
			img = sprintDown;
			//y++;

		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {
			xv = MAX_SPRINT_VELOCITY * -1;
			img = sprintLeft;
			//x++;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (Gdx.input.isKeyPressed(Input.Keys.SPACE))) {

			xv = MAX_SPRINT_VELOCITY;
			img = sprintRight;
			//x++;
		}




		y = y + (yv * Gdx.graphics.getDeltaTime());
		x = x + (xv * Gdx.graphics.getDeltaTime());

		yv = stopping(yv);
		xv = stopping(xv);
	}


	public float stopping(float velocity) {
		float stopping = 0.f;
		velocity *= stopping;

		if (Math.abs(velocity) < 1) {
			velocity = 0;
		}
		return velocity;
	}
}


	/*lic void isAlive() {
		if (playerHealth > 0) {
			isAlive = false;
		} else {
			isAlive = true;
		}
	}

	public static void zombiePosition() {

	}

	public static void playerPosition() {


	}


} */



