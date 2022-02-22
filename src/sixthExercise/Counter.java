package sixthExercise;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int counter;
    private int method;
    private ReentrantLock lock = new ReentrantLock();

    public Counter(int counter, int method) {
        this.counter = counter;
        this.method = method;
    }

    public void increment() {
        this.counter++;
    }
    public void decrement() {
        this.counter--;
    }

    public synchronized void incrementSynchronised() {
        this.counter++;
    }
    public synchronized void decrementSynchronised() {
        this.counter--;
    }

    public void incrementSynchronisedBlock() {
        synchronized (this) {
            this.counter++;
        }
    }
    public void decrementSynchronisedBlock() {
        synchronized (this) {
            this.counter--;
        }
    }

    public void incrementLock() {
        lock.lock();
        try {
            this.counter++;
        } finally {
            lock.unlock();
        }
    }
    public void decrementLock(){
        lock.lock();
        try {
            this.counter--;
        } finally {
            lock.unlock();
        }
    }


    public int getCounter() {
        return this.counter;
    }
    public int getMethod() {
        return this.method;
    }
}
