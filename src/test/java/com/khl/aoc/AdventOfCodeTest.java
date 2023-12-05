package com.khl.aoc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Supplier;

/**
 * @author Kevin Lee
 */
public class AdventOfCodeTest {

    @Test
    public void testDay01() throws Exception {
        testSolution("55386", "54824", Day01::new);
    }

    private void testSolution(String expected1, String expected2, Supplier<Solution> supplier) throws Exception {
        var solution = supplier.get();
        var testInput = getTestInput(solution.getDay());

        Assertions.assertEquals(expected1, solution.getSolution1(testInput));
        Assertions.assertEquals(expected2, solution.getSolution2(testInput));
    }

    private String getTestInput(int day) throws Exception {
        var classLoader = AdventOfCodeTest.class.getClassLoader();
        var resource = classLoader.getResource(String.format("inputs/day%02d.txt", day));
        if (resource == null) {
            throw new Exception("Test input file does not exist");
        }
        return Files.readString(Paths.get(resource.getPath()));
    }

}
