package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;

@SpringBootTest()
class TestDemoControllerTest {
    @Test
    void array_sort() throws Exception {
        final int[] numbers = { 3, 2, 5, 1, 4};
        final int[] expected = {1,2,3,4,5};

        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers);
    }
}