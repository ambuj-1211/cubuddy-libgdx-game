package io.github.cubuddy.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

import io.github.cubuddy.Map;
import io.github.cubuddy.MapRenderer;
import io.github.cubuddy.OnScreenControlRenderer;


public class GameScreen extends CubuddyScreen{

    Map map;
    MapRenderer renderer;
    OnScreenControlRenderer controlRenderer;

    public GameScreen (Game game) {
        super(game);
    }

    @Override
    public void show () {
        map = new Map();
        renderer = new MapRenderer(map);
        controlRenderer = new OnScreenControlRenderer(map);
    }

    @Override
    public void render (float delta) {
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        map.update(delta);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        renderer.render(delta);
        controlRenderer.render();

        if (map.bob.bounds.overlaps(map.endDoor.bounds)) {
            game.setScreen(new GameOverScreen(game));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            game.setScreen(new MainMenu(game));
        }
    }

    @Override
    public void hide () {
        Gdx.app.debug("Cubuddy", "dispose game screen");
        renderer.dispose();
        controlRenderer.dispose();
    }

}
