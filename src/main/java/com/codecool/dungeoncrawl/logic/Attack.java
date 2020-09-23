package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Attack {
    private Actor player;
    private Actor monster;
    private Actor winner;

    public Attack(Actor player, Actor monster) {
        this.player = player;
        this.monster = monster;
    }

    public void fight() {
        System.out.println("LET THE FIGHT BEGIN!");
        while (true) {
            int playerDamage = player.getDamage();
            int monsterDamage = monster.getDamage();
            monster.decreaseHealthBy(playerDamage);
            System.out.println("Player deals " + playerDamage + " damage");
            if (isDead(monster)) {
                System.out.println("Player wins!");
                setWinner(player);
                break;
            }
            player.decreaseHealthBy(monsterDamage);
            System.out.println("Monster deals " + monsterDamage + " damage");
            if (isDead(player)) {
                System.out.println("Monster wins!");
                setWinner(monster);
                break;
            }
        }
    }

    public boolean isDead(Actor actor){
        return actor.getHealth() <= 0;
    }

    public void setWinner(Actor winner) {
        this.winner = winner;
    }

    public Actor getWinner() {
        return winner;
    }
}
