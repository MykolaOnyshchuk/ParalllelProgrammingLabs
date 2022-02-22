package sixthExercise;

public class IncrementThread extends Thread {
    private Counter counter;

    public IncrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            switch (counter.getMethod()) {
                case 0:
                    counter.increment();
                    break;
                case 1:
                    counter.incrementSynchronised();
                    break;
                case 2:
                    counter.incrementSynchronisedBlock();
                    break;
                case 3:
                    counter.incrementLock();
                    break;
            }
            System.out.println(counter.getCounter());
        }
    }
}
