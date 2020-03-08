package utils;

import java.util.Random;

public class RandomDataGenerator {

    public static int randomInteger(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    public static String randomString(int targetStringLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        return new Random().ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
