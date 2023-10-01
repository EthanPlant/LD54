package com.aquilla.ludumdare.state;

import com.aquilla.ludumdare.LudumDare;

public class LoadingState extends State {
    public LoadingState(LudumDare game) {
        super(game);

        loadAssets();
    }

    private void loadAssets() {
        getAssets().loadTexture("texture/bullet.png", "bullet_tex");
        getAssets().loadTexture("texture/gameover.png", "gameover");
        getAssets().loadTexture("texture/enemy.png", "enemy_tex");
        getAssets().loadTexture("texture/enemy_tank.png", "tank_tex");
        getAssets().loadTexture("texture/enemy_shooter.png", "shooter_tex");
        getAssets().loadTexture("texture/enemy_boss.png", "boss_tex");
        getAssets().loadTexture("texture/titlescreen.png", "titlescreen");
        getAssets().loadTexture("texture/winscreen.png", "winscreen");
        getAssets().loadTexture("texture/healthbar.png", "healthbar_tex");
        getAssets().loadTextureAtlas("texture/player.atlas", "player_tex");

        getAssets().loadTiledMap("tilemap/map.tmx", "map");

        getAssets().loadSound("sound/player_shoot.wav", "player_shoot_sfx");
        getAssets().loadSound("sound/player_hit.wav", "player_hit_sfx");
        getAssets().loadSound("sound/enemy_hit.wav", "enemy_hit_sfx");
        getAssets().loadSound("sound/enemy_death.wav", "enemy_death_sfx");
        getAssets().loadSound("sound/enemy_spawn.wav", "enemy_spawn_sfx");
        getAssets().loadSound("sound/player_death.wav", "player_death_sfx");
        getAssets().loadSound("sound/menu_select.wav", "menu_select_sfx");

        getAssets().loadMusic("music/titlescreen.wav", "titlescreen_bgm");
        getAssets().loadMusic("music/bgm.wav", "bgm");
    }

    @Override
    public void update(float delta) {
        if (getAssets().update()) {
            setState(new TitleState(getGame()));
        }
    }

    @Override
    public void draw() {
        getGame().getBatch().setProjectionMatrix(getCam().combined);
        getGame().getBatch().begin();
        getGame().getBatch().end();
    }
}
