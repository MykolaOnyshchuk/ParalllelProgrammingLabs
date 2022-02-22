package fifthExercise;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable task1 =
                () -> {
                    System.out.println("Asynchronous:");
                    run51();
                };
        Thread firstThread = new Thread(task1);
        firstThread.start();
        firstThread.join();

        Thread.sleep(30);

        Runnable task2 =
                () -> {
                    System.out.println("\nSynchronous:");
                    run52();
                };
        Thread secondThread = new Thread(task2);
        secondThread.start();
        secondThread.join();
    }

    public static void run51() {
        AsyncPrint hyphenPrint = new AsyncPrint("-");
        AsyncPrint verticalLinePrint = new AsyncPrint("|");

        Thread hyphenThread = new Thread(hyphenPrint);
        Thread verticalLineThread = new Thread(verticalLinePrint);

        hyphenThread.start();
        verticalLineThread.start();
    }

    public static void run52() {
        Control control = new Control();
        SyncPrint hyphenPrint = new SyncPrint("-", control);
        SyncPrint verticalLinePrint = new SyncPrint("|", control);

        hyphenPrint.start();
        verticalLinePrint.start();
    }
}