package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo ===\n");

        HeroProfile hero = new HeroProfile("Knight", 100);
        BossEnemy boss = new BossEnemy("Dragon", 120, 15);

        AttackAction basic = new BasicAttack("Slash", 10);
        AttackAction decorated = new FireRuneDecorator(basic);
        AttackAction multiDecorated = new PoisonCoatingDecorator(
                new CriticalFocusDecorator(
                        new BasicAttack("Power Slash", 12)
                )
        );

        System.out.println("--- Base vs Decorated ---");
        System.out.println("Base: " + basic.getActionName());
        System.out.println("Damage: " + basic.getDamage());
        System.out.println("Effects: " + basic.getEffectSummary());
        System.out.println();

        System.out.println("Decorated: " + decorated.getActionName());
        System.out.println("Damage: " + decorated.getDamage());
        System.out.println("Effects: " + decorated.getEffectSummary());
        System.out.println();

        System.out.println("--- Multiple Decorators ---");
        System.out.println("Action: " + multiDecorated.getActionName());
        System.out.println("Damage: " + multiDecorated.getDamage());
        System.out.println("Effects: " + multiDecorated.getEffectSummary());

        System.out.println("\n--- Facade Run ---");
        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, multiDecorated);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());

        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }
}