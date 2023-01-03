package org.thiefspin;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.Duration;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.RecursiveAction;

public class CrackerAction extends RecursiveAction {
    private final String password;
    private final Long startTime;

    private final boolean hasNumber;

    private final boolean hasLetter;

    private final int numCharacters;

    private String matchedPassword;

    private boolean found = false;
    private Long counter = 0L;

    CrackerAction(String name, String password) {
        this.password = password;
        this.startTime = System.currentTimeMillis();
        this.hasLetter = containsAlphabetic(password);
        this.hasNumber = containsDigit(password);
        this.numCharacters = password.length();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(name + ". Elapsed time: " + getElapsedTime() + ". Guesses: " + counter);
            }
        }, 60000, 60000);
    }

    void crack() {
        matchedPassword = find();
    }

    void print() {
        System.out.println("Time: " + durationFormatter(durationSinceStart()));
        System.out.println("Guesses: " + counter);
        System.out.println("Original password: " + password);
        System.out.println("Matched password: " + matchedPassword);
    }

    private String find() {
        while (!found) {
            counter++;
            if (hasNumber && !hasLetter) {
                return numbersOnlyCracker();
            }
            String possibleMatch = generatePassword(numCharacters, hasLetter, hasNumber);
            if (password.equals(possibleMatch)) {
                found = true;
                return possibleMatch;
            }
        }
        return null;
    }

    private String numbersOnlyCracker() {
        Long numeric = Long.parseLong(password);
        for (long i = 0; i < Long.MAX_VALUE; i++) {
            if (i == numeric) return numeric.toString();
        }
        return null;
    }

    private Duration durationSinceStart() {
        return Duration.millis(System.currentTimeMillis() - startTime);
    }

    private static String durationFormatter(Duration duration) {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendDays()
                .appendSuffix("d ")
                .appendHours()
                .appendSuffix("h ")
                .appendMinutes()
                .appendSuffix("m ")
                .appendSeconds()
                .appendSuffix("s ")
                .appendMillis()
                .appendSuffix("ms")
                .toFormatter();
        return formatter.print(duration.toPeriod());
    }

    private static boolean containsDigit(String s) {
        return s.chars().mapToObj(c -> (char) c).anyMatch(Character::isDigit);
    }

    private static boolean containsAlphabetic(String s) {
        return s.chars().mapToObj(c -> (char) c).anyMatch(Character::isAlphabetic);
    }

    private static String generatePassword(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

    private String getElapsedTime() {
        return durationFormatter(durationSinceStart());
    }

    @Override
    protected void compute() {
        crack();
        print();
    }
}
