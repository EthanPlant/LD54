package com.aquilla.ludumdare.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class Assets {
    private static Assets instance;
    private final AssetManager manager;
    private final Map<String, String> map;

    private Assets() {
        manager = new AssetManager();
        map = new HashMap<>();
    }

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public void loadTexture(String filename, String name) {
        manager.load(filename, Texture.class);
        map.put(name, filename);
        manager.finishLoading();
    }

    public Texture getTexture(String name) {
        return manager.get(map.get(name));
    }
}
