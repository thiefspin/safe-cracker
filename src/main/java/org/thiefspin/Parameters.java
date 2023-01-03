package org.thiefspin;

public class Parameters {

    static int getNumWorkers() {
        String workers = System.getenv("workers");
        if (workers != null) {
            return Integer.parseInt(workers);
        }
        return 5;
    }

    static String getPassword() {
        String password = System.getenv("password");
        if (password != null) {
            return password;
        }
        throw new RuntimeException("Please provide a password!!");
    }
}
