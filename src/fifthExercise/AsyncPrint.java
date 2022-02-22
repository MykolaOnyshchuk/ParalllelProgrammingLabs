package fifthExercise;

public class AsyncPrint implements Runnable {
    private String text = "";

    public AsyncPrint(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        int symbolsNumber = 500;
        for (int i = 0; i < symbolsNumber; i++) {
            System.out.print(this.text);
            if (i % 50 == 0 && i != 0) {
                System.out.print('\n');
            }
        }
    }
}