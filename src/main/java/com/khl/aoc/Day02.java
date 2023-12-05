package com.khl.aoc;

import java.util.regex.Pattern;

/**
 * @author Kevin Lee
 */
public class Day02 implements Solution {

    @Override
    public int getDay() {
        return 2;
    }

    @Override
    public String getSolution1(String input) {
        var sum = 0;

        for (var game : input.split(System.lineSeparator())) {
            if (isPossibleGame(game)) {
                var matcher = GAME_PATTERN.matcher(game);
                if (!matcher.find()) {
                    throw new IllegalArgumentException("The game has no id: " + game);
                }
                sum += Integer.parseInt(matcher.group(1));
            }
        }

        return String.valueOf(sum);
    }

    @Override
    public String getSolution2(String input) {
        var sum = 0;

        for (var game : input.split(System.lineSeparator())) {
            var configuration = getMinimumConfiguration(game);
            var power = configuration[RED] * configuration[BLUE] * configuration[GREEN];
            sum += power;
        }

        return String.valueOf(sum);
    }

    private String[] getGrabsFromGame(String game) {
        game = game.replaceAll(",", "");
        game = game.replaceAll(" ", "");
        game = game.substring(game.indexOf(":") + 1);
        return game.split(";");
    }

    private int[] getMinimumConfiguration(String game) {
        var configuration = new int[3];

        for (var grab : getGrabsFromGame(game)) {
            var matcher = GRAB_PATTERN.matcher(grab);

            while (matcher.find()) {
                var count = Integer.parseInt(matcher.group(1));
                var color = matcher.group(2);

                switch (color) {
                    case "red":
                        configuration[RED] = Math.max(configuration[RED], count);
                        break;
                    case "green":
                        configuration[GREEN] = Math.max(configuration[GREEN], count);
                        break;
                    case "blue":
                        configuration[BLUE] = Math.max(configuration[BLUE], count);
                        break;
                }
            }
        }

        return configuration;
    }

    private boolean isPossibleGame(String game) {
        for (var grab : getGrabsFromGame(game)) {
            var matcher = GRAB_PATTERN.matcher(grab);

            while (matcher.find()) {
                var count = Integer.parseInt(matcher.group(1));
                var color = matcher.group(2);

                switch (color) {
                    case "red":
                        if (count > MAX_RED) {
                            return false;
                        }
                        break;
                    case "green":
                        if (count > MAX_GREEN) {
                            return false;
                        }
                        break;
                    case "blue":
                        if (count > MAX_BLUE) {
                            return false;
                        }
                        break;
                }
            }
        }

        return true;
    }

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    private static final int MAX_RED = 12;
    private static final int MAX_GREEN = 13;
    private static final int MAX_BLUE = 14;

    private static final Pattern GAME_PATTERN = Pattern.compile("Game\\s+(\\d+):");
    private static final Pattern GRAB_PATTERN = Pattern.compile("(\\d+)(red|green|blue)");

}