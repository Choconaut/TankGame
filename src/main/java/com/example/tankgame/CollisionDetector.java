package com.example.tankgame;

import com.example.tankgame.gameobject.GameObject;

import java.util.*;

//Sort and Prune
public class CollisionDetector {

    public void detectCollision(List<GameObject> gameObjects) {
        gameObjects.removeIf(obj -> !obj.isActive());

        //Sort game objects by x position
        gameObjects.sort(Comparator.comparingDouble(GameObject::getX));

        Deque<GameObject> activeObjects = new ArrayDeque<>();

        for (GameObject current : gameObjects) {
            //Remove objects that are not overlapping in the x-axis
//            activeObjects.removeIf(other -> current.getX() > other.getMaxX());
            //Faster because it removes objects in O(1) time vs O(n) time from removeIf
            while (!activeObjects.isEmpty() && activeObjects.peekFirst().getMaxX() < current.getX()) {
                activeObjects.pollFirst();
            }

            
            //for all objects in the active list, check for collision in the y-axis
            for (GameObject other : activeObjects) {
                //If the objects are overlapping in the y-axis, handle the collision
                if (current.intercepts(other)) {
                    current.handleCollision(other);
                    other.handleCollision(current);
                }
            }

            activeObjects.add(current);
        }
    }

}


