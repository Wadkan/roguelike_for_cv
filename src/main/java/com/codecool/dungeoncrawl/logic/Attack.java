package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;

public class Attack {
    private Actor player;
    private Actor monster;

    public Attack(Actor player, Actor monster) {
        this.player = player;
        this.monster = monster;
    }

    public void fight() {
        System.out.println("LET THE FIGHT BEGIN!");
        while (true) {
            monster.decreaseHealthBy(player.getDamage());
            if (isDead(monster)) {
                System.out.println("Player wins!");
                break;
            }
            player.decreaseHealthBy(monster.getDamage());
            if (isDead(player)) {
                System.out.println("Monster wins!");
                break;
            }
        }
    }

    public boolean isDead(Actor actor){
        return actor.getHealth() <= 0;
    }
}
