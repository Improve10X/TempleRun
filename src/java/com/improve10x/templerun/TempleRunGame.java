package com.improve10x.templerun;

import java.util.Random;

public class TempleRunGame {
    public static void main(String[] args) throws InterruptedException {
        Player player = new Player("Adventurer", 100);
        boolean isGameRunning = true;
        System.out.println("Welcome to Temple Run!");
        while (isGameRunning) {
            player.run();
            Obstacle obstacle = generateRandomObstacle();
            handleObstacle(player, obstacle);
            if (player.getHealth() <= 0) {
                System.out.println("Game over! Your final score is : " + player.getScore());
                isGameRunning = false;
            }
            Thread.sleep(1000);
        }
    }

    private static void handleObstacle(Player player, Obstacle obstacle) {
        if (obstacle instanceof Fireball) {
            ((Fireball) obstacle).roll();
            obstacle.collide(player);
        } else if (obstacle instanceof SpikePit) {
            ((SpikePit) obstacle).trap(player);
        } else if (obstacle instanceof Coin) {
            player.collectCoins((Coin) obstacle);
        }
    }

    private static Obstacle generateRandomObstacle() {
        Random random = new Random();
        int obstacleType = random.nextInt(3);
        switch (obstacleType) {
            case 1:
                return new Fireball(random.nextInt(30) + 10);
            case 2:
                return new SpikePit(random.nextInt(10) + 5);
            default: {
                int[] coinValues = {10, 20, 30, 40, 50};
                int randomIndexValue = random.nextInt(coinValues.length);
                return new Coin(coinValues[randomIndexValue]);
            }
        }
    }
}
