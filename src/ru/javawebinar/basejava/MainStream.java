package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {
        System.out.println(minValue(new int[]{1, 2, 3, 3, 2, 3}));
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private static List<Integer> oddOrEven(List<Integer> integers) {
        if (integers.stream().reduce(0, Integer::sum) % 2 != 0) {
            return integers.stream().filter(o -> o % 2 != 0).collect(Collectors.toList());
        } else {
            return integers.stream().filter(o -> o % 2 == 0).collect(Collectors.toList());
        }
    }

    private static int minValue(int[] values) {
        AtomicInteger i = new AtomicInteger();
        Arrays.stream(values).distinct().sorted().forEach(n -> {
            i.updateAndGet(v -> v * 10);
            i.addAndGet(n);
        });
        return i.intValue();
    }
}