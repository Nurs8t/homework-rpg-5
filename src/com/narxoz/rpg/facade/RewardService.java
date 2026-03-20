package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward";
        }
        if ("Hero".equals(battleResult.getWinner())) {
            return "150 gold and 40 XP";
        }
        return "No reward";
    }
}
