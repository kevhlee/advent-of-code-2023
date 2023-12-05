package com.khl.aoc;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author Kevin Lee
 */
public class Day01 implements Solution {

    @Override
    public int getDay() {
        return 1;
    }

    @Override
    public String getSolution1(String input) {
        var sum = 0;

        for (var line : input.split(System.lineSeparator())) {
            var array = line.toCharArray();

            var firstDigit = '\0';
            for (char ch : array) {
                if (Character.isDigit(ch)) {
                    firstDigit = ch;
                    break;
                }
            }

            var lastDigit = '\0';
            for (var i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(array[i])) {
                    lastDigit = array[i];
                    break;
                }
            }

            sum += Integer.parseInt("" + firstDigit + lastDigit);
        }

        return String.valueOf(sum);
    }

    @Override
    public String getSolution2(String input) {
        var sum = 0;
        var pattern = Pattern.compile("(\\d|one|two|three|four|five|six|seven|eight|nine)");

        for (var line : input.split(System.lineSeparator())) {
            var matcher = pattern.matcher(line);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Line has no digits: " + line);
            }

            var start = matcher.start();
            var digit = matcher.group(1);
            var firstDigit = matcher.group(1);

            while (matcher.find(start + 1)) {
                digit = matcher.group(1);
                start = matcher.start();
            }

            sum += Integer.parseInt(DIGITS.getOrDefault(firstDigit, firstDigit) + DIGITS.getOrDefault(digit, digit));
        }

        return String.valueOf(sum);
    }

    private static final Map<String, String> DIGITS = new HashMap<>() {{
        put("one", "1");
        put("two", "2");
        put("three", "3");
        put("four", "4");
        put("five", "5");
        put("six", "6");
        put("seven", "7");
        put("eight", "8");
        put("nine", "9");
    }};

}