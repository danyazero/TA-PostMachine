package org.example;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    static int[] list = new int[]{0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0};

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        switch (m) {
            case 1 -> firstVariant();
            case 2 -> secondVariant();
        }
    }


    private static int deleteElement(int index) {
        list[index] = 0;
        return index;
    }

    private static int addElement(int index) {
        list[index] = 1;
        return index;
    }

    private static int goLeft(int index) {
        return index -= 1;
    }

    private static int goRight(int index) {
        return index += 1;
    }

    private int checkAndDo(int index, int presence, Function<Integer, Integer> isTrue, Function<Integer, Integer> isFalse) {
        do {
            index = isTrue.apply(index);
        } while ((index < list.length - 1) && (list[index] == presence));

        return isFalse.apply(index);
    }

    public void secondVariant(){
        int index = 10;

        System.out.println(Arrays.toString(list));

        deleteElement(index);
        System.out.println(Arrays.toString(list));
        index = goLeft(index);
        while (true) {
            deleteElement(index);
            System.out.println(Arrays.toString(list));

            index = checkAndDo(index, 1, Main::goLeft, Main::addElement);
            index = goLeft(index);
            if (list[index] == 1) break;
            index = checkAndDo(index, 1, Main::goRight, Main::goLeft);
        }
        System.out.println(Arrays.toString(list));
    }

    public void firstVariant() {
        int index = 10;

        System.out.println(Arrays.toString(list));

        deleteElement(index);
        System.out.println(Arrays.toString(list));

        index = checkAndDo(index, 1, Main::goLeft, Main::goRight);
        deleteElement(index);
        System.out.println(Arrays.toString(list));

        while (index < list.length - 1) {
            index = checkAndDo(index, 0, Main::goLeft, Main::goRight);
            addElement(index);
            index = checkAndDo(index, 0, Main::goRight, Main::deleteElement);
            System.out.println(Arrays.toString(list));
        }
    }
}