package sixthExercise;

public class DecrementThread extends Thread{
    private Counter counter;

    public DecrementThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++){
            switch (counter.getMethod()) {
                case 0:
                    counter.decrement();
                    break;
                case 1:
                    counter.decrementSynchronised();
                    break;
                case 2:
                    counter.decrementSynchronisedBlock();
                    break;
                case 3:
                    counter.decrementLock();
                    break;
            }
            System.out.println(counter.getCounter());
        }
    }
}
