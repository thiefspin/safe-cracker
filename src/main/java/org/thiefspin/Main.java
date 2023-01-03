package org.thiefspin;

public class Main {

    static int NUM_WORKERS = Parameters.getNumWorkers();

    public static void main(String[] args) {
        String password = Parameters.getPassword();

        Workers workers = new Workers(NUM_WORKERS);
        for (int i = 0; i < NUM_WORKERS; i++) {
            String name = "Cracker-" + i;
            workers.addWork(new CrackerAction(name, password));
        }

        workers.whenDone(() -> {
            System.exit(0);
        });


    }
}