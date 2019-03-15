package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        IntPredicate ip = o -> o % 2 == 0;
        boolean checkSum = ip.test(integers.stream().reduce(0, Integer::sum));
        return integers.stream().filter(o -> ip.negate().test(o) == checkSum).collect(Collectors.toList());
    }

    private static int minValue(int[] values) {
        return Arrays.stream(values).distinct().sorted().reduce(0, (i, v) -> (i * 10) + v);
    }
}