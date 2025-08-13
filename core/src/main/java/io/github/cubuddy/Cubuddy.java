package io.github.cubuddy;

import com.badlogic.gdx.Game;

import io.github.cubuddy.screens.MainMenu;
public class Cubuddy extends Game {
    @Override
    public void create() {
        setScreen(new MainMenu(this));
    }
}
