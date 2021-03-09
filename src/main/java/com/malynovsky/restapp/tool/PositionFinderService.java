package com.malynovsky.restapp.tool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class PositionFinderService {
    private static final int SEASON_LENGTH = 70;


    public static void main(String[] args) {
        Player player = new Player();

        player.shootingQ = 94;
        player.blockingQ = 60;
        player.passingQ = 73;
        player.techniqueQ = 89;
        player.speedQ = 77;
        player.aggressivenessQ = 97;
        player.jumpingQ = 95;

        for (Position position : Position.values()) {
            System.out.println(position.toString());

            double effectiveQualitySum = 0;
            for (int i = 0; i < SEASON_LENGTH * 5; i++) {
                effectiveQualitySum += position.updatePlayer(player);
            }
//            System.out.println(player);
            System.out.println(String.format("%4.1f", player.getOverall()) + " - " + effectiveQualitySum / (SEASON_LENGTH * 5));
            System.out.println(player);
            System.out.println();
            player.setToZero();
        }

    }

    private enum Position {
        PG(20, 50, 100, 75, 75, 20, 20),
        SQ(75, 40, 100, 100, 75, 50, 50),
        SF(60, 60, 100, 100, 100, 100, 100),
        PF(40, 60, 50, 50, 75, 100, 100),
        C(20, 70, 20, 40, 40, 100, 100),
        C_SIMPLE(0, 0, 20, 40, 40, 100, 100);

        public final double QUALITY100_TRAINING = 2.0;

        private int shooting;
        private int blocking;
        private int passing;
        private int technique;
        private int speed;
        private int aggressiveness;
        private int jumping;

        private Map<Integer, BiConsumer<Player, Integer>> setter = new HashMap<>();
        private Map<Integer, Integer> quality = new HashMap<>();

        Position(int shooting, int blocking, int passing, int technique, int speed, int aggressiveness, int jumping) {
            this.shooting = shooting;
            this.blocking = blocking;
            this.passing = passing;
            this.technique = technique;
            this.speed = speed;
            this.aggressiveness = aggressiveness;
            this.jumping = jumping;

            setter.put(0, (Player p, Integer quality) -> p.shooting += QUALITY100_TRAINING * quality / 100);
            setter.put(1, (Player p, Integer quality) -> p.blocking += QUALITY100_TRAINING * quality / 100);
            setter.put(2, (Player p, Integer quality) -> p.passing += QUALITY100_TRAINING * quality / 100);
            setter.put(3, (Player p, Integer quality) -> p.technique += QUALITY100_TRAINING * quality / 100);
            setter.put(4, (Player p, Integer quality) -> p.speed += QUALITY100_TRAINING * quality / 100);
            setter.put(5, (Player p, Integer quality) -> p.aggressiveness += QUALITY100_TRAINING * quality / 100);
            setter.put(6, (Player p, Integer quality) -> p.jumping += QUALITY100_TRAINING * quality / 100);

            quality.put(0, this.shooting);
            quality.put(1, this.blocking);
            quality.put(2, this.passing);
            quality.put(3, this.technique);
            quality.put(4, this.speed);
            quality.put(5, this.aggressiveness);
            quality.put(6, this.jumping);
        }

        public int updatePlayer(Player player) {
            List<Double> values = List.of(player.shooting / this.shooting,
                    player.blocking / this.blocking,
                    player.passing / this.passing,
                    player.technique / this.technique,
                    player.speed / this.speed,
                    player.aggressiveness / this.aggressiveness,
                    player.jumping / this.jumping);
            Map<Integer, Integer> playerQuality = new HashMap<>();
            playerQuality.put(0, player.shootingQ);
            playerQuality.put(1, player.blockingQ);
            playerQuality.put(2, player.passingQ);
            playerQuality.put(3, player.techniqueQ);
            playerQuality.put(4, player.speedQ);
            playerQuality.put(5, player.aggressivenessQ);
            playerQuality.put(6, player.jumpingQ);

            double min = values.stream().min(Double::compareTo).orElse(0D);

            int index = -1;
            int mostWanted = -1;
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) == min && mostWanted < quality.get(i)) {
                    mostWanted = quality.get(i);
                    index = i;
                }
            }

            setter.get(index).accept(player, playerQuality.get(index));

            return playerQuality.get(index);
        }
    }

    public static class Player {
        double shooting;
        double blocking;
        double passing;
        double technique;
        double speed;
        double aggressiveness;
        double jumping;

        int shootingQ;
        int blockingQ;
        int passingQ;
        int techniqueQ;
        int speedQ;
        int aggressivenessQ;
        int jumpingQ;

        public Player() {
        }

        public double getOverall() {
            return shooting + blocking + passing + technique + speed + aggressiveness + jumping;
        }

        public void setToZero() {
            shooting = 0;
            blocking = 0;
            passing = 0;
            technique = 0;
            speed = 0;
            aggressiveness = 0;
            jumping = 0;
        }

        @Override
        public String toString() {
            return String.format("%4.1f | %4.1f | %4.1f | %4.1f | %4.1f | %4.1f | %4.1f |", shooting, blocking, passing, technique,
                    speed, aggressiveness, jumping);
        }
    }
}
