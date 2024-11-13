package com.example.tankgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Sort and Prune
public class CollisionDetector {

    public void detectCollision(List<GameObject> gameObjects) {
        //Sort game objects by x position
        gameObjects.sort(Comparator.comparingDouble(GameObject::getX));

        List<GameObject> activeObjects = new ArrayList<>();

        for (GameObject current : gameObjects) {
            //Remove objects that are not overlapping in the x-axis
            activeObjects.removeIf(other -> current.getX() > other.getMaxX());

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


