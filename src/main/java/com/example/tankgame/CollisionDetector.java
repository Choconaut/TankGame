package com.example.tankgame;

import com.example.tankgame.gameobject.GameObject;

import java.util.*;

/**
 * This class is responsible for detecting collisions between game objects.
 * It uses a sweep and prune algorithm to detect collisions between game objects.
 * The algorithm works by sorting the game objects by their x position and then adding objects
 * that are overlapping in the x-axis to a list. Then, for each object in the list, it checks if
 * the object is overlapping in the y-axis. If it is, it handles the collision.
 *
 * This algorithm has a time complexity of O(n^2) in the worst case, but it is faster than the naive
 * collision detection algorithm because it reduces the number of comparisons needed to detect collisions.
 */
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


