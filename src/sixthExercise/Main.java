package sixthExercise;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Thread: " + Thread.currentThread().getName());
        task3();


    }

    private static void task3(){
        Counter counter = new Counter(0,2);
        IncrementThread it = new IncrementThread(counter);
        DecrementThread dt = new DecrementThread(counter);
        it.start();
        dt.start();

        System.out.println(counter.getCounter());
    }
}
