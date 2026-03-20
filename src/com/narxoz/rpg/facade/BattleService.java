package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;
import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();
        int round = 0;
        boolean heroFirst = random.nextBoolean();

        while (hero.isAlive() && boss.isAlive() && round < 12) {

            round++;

            if (heroFirst) {

                int damageFromHero = action.getDamage();
                boss.takeDamage(damageFromHero);
                result.addLine("Hero hits the boss for " + damageFromHero + " damage");

                if (!boss.isAlive()) {
                    break;
                }

                int damageFromBoss = boss.getAttackPower();
                hero.takeDamage(damageFromBoss);
                result.addLine("Boss hits the hero for " + damageFromBoss + " damage");

            } else {

                int damageFromBoss = boss.getAttackPower();
                hero.takeDamage(damageFromBoss);
                result.addLine("Boss hits the hero for " + damageFromBoss + " damage");

                if (!hero.isAlive()) {
                    break;
                }

                int damageFromHero = action.getDamage();
                boss.takeDamage(damageFromHero);
                result.addLine("Hero hits the boss for " + damageFromHero + " damage");
            }
        }

        result.setRounds(round);

        if (boss.isAlive()) {
            result.setWinner("Boss");
        } else {
            result.setWinner("Hero");
        }


        return result;
    }
}