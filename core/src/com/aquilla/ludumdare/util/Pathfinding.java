package com.aquilla.ludumdare.util;

import com.aquilla.ludumdare.assets.Assets;
import com.aquilla.ludumdare.entity.Player;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public final class Pathfinding {
    public static Map<Vector2, Vector2> calculatePaths(Player player) {
        int playerTileX = (int) player.getPos().x / 16;
        int playerTileY = (int) player.getPos().y / 16;

        Array<RectangleMapObject> mapObjects = Assets.getInstance()
                .getTiledMap("map")
                .getLayers()
                .get("enemy_walkable")
                .getObjects()
                .getByType(RectangleMapObject.class);

        Queue<Vector2> frontier = new LinkedList<>();
        frontier.add(new Vector2(playerTileX, playerTileY));
        Map<Vector2, Vector2> cameFrom = new HashMap<>();
        cameFrom.put(new Vector2(playerTileX, playerTileY), new Vector2(playerTileX, playerTileY));

        while (!frontier.isEmpty()) {
            Vector2 current = frontier.poll();

            Vector2 up = new Vector2(current.x, current.y + 1);
            Vector2 down = new Vector2(current.x, current.y - 1);
            Vector2 left = new Vector2(current.x - 1, current.y);
            Vector2 right = new Vector2(current.x + 1, current.y);

            if (up.y >= 0 && up.y < 15 && !cameFrom.containsKey(up)) {
                for (RectangleMapObject mapObject : mapObjects) {
                    if (mapObject.getRectangle().contains(up.x * 16, up.y * 16)) {
                        frontier.add(up);
                        cameFrom.put(up, current);
                        break;
                    }
                }
            }

            if (down.y >= 0 && down.y < 15 && !cameFrom.containsKey(down)) {
                for (RectangleMapObject mapObject : mapObjects) {
                    if (mapObject.getRectangle().contains(down.x * 16, down.y * 16)) {
                        frontier.add(down);
                        cameFrom.put(down, current);
                        break;
                    }
                }
            }

            if (left.x >= 0 && left.x < 27 && !cameFrom.containsKey(left)) {
                for (RectangleMapObject mapObject : mapObjects) {
                    if (mapObject.getRectangle().contains(left.x * 16, left.y * 16)) {
                        frontier.add(left);
                        cameFrom.put(left, current);
                        break;
                    }
                }
            }

            if (right.x >= 0 && right.x < 27 && !cameFrom.containsKey(right)) {
                for (RectangleMapObject mapObject : mapObjects) {
                    if (mapObject.getRectangle().contains(right.x * 16, right.y * 16)) {
                        frontier.add(right);
                        cameFrom.put(right, current);
                        break;
                    }
                }
            }
        }

        return cameFrom;
    }
}
