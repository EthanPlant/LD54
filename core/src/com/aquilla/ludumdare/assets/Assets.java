package com.aquilla.ludumdare.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import java.util.HashMap;
import java.util.Map;

public class Assets {
    private static Assets instance;
    private final AssetManager manager;
    private final Map<String, String> map;

    private Assets() {
        manager = new AssetManager();
        manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        map = new HashMap<>();
    }

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public boolean update() {
        return manager.update();
    }

    public void loadTexture(String filename, String name) {
        manager.load(filename, Texture.class);
        map.put(name, filename);
    }

    public Texture getTexture(String name) {
        return manager.get(map.get(name), Texture.class);
    }

    public void loadTextureAtlas(String filename, String name) {
        manager.load(filename, TextureAtlas.class);
        map.put(name, filename);
    }

    public TextureAtlas getTextureAtlas(String name) {
        return manager.get(map.get(name), TextureAtlas.class);
    }

    public TextureRegion getRegionFromTextureAtlas(String atlas, String region) {
        TextureAtlas textureAtlas = manager.get(map.get(atlas), TextureAtlas.class);
        return textureAtlas.findRegion(region);
    }

    public void loadTiledMap(String filename, String name) {
        manager.load(filename, TiledMap.class);
        map.put(name, filename);
    }

    public TiledMap getTiledMap(String name) {
        return manager.get(map.get(name), TiledMap.class);
    }

    public void loadSound(String filename, String name) {
        manager.load(filename, Sound.class);
        map.put(name, filename);
    }

    public Sound getSound(String name) {
        return manager.get(map.get(name), Sound.class);
    }

    public void loadMusic(String filename, String name) {
        manager.load(filename, Music.class);
        map.put(name, filename);
    }

    public Music getMusic(String name) {
        return manager.get(map.get(name), Music.class);
    }
}
