package com.improve10x.templerun;

import java.util.Random;

public class TempleRunGame {
    public static void main(String[] args) {
        Player player = new Player("Adventurer", 100);
        int[] coinValues = {10, 20, 30, 40, 50};
        boolean isGameRunning = true;
        Random random = new Random();
        System.out.println("Welcome to Temple Run!");
        while (isGameRunning){
            player.run();
            int obstacleType = random.nextInt(3);
            if(obstacleType == 1){
                Fireball fireball = new Fireball(random.nextInt(30) + 10);
                fireball.roll();
                fireball.collide(player);
            } else if(obstacleType == 2){
                SpikePit spikePit = new SpikePit(random.nextInt(10) + 5);
                spikePit.trap(player);
            } else {
                int randomIndexValue = random.nextInt(coinValues.length);
                Coin coin = new Coin(coinValues[randomIndexValue]);
                player.collectCoins(coin);
            }
            if(player.getHealth() <= 0){
                System.out.println("Game over! Your final score is : " + player.getScore());
                isGameRunning = false;
            }

            try{
                Thread.sleep(1000);
            } catch (InterruptedException exception){
                exception.printStackTrace();
            }
        }
    }
}
