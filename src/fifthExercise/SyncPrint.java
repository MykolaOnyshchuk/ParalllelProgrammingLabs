package fifthExercise;

public class SyncPrint extends Thread {
    private String text = "";
    private final Control control;

    public SyncPrint(String text, Control control) {
        this.text = text;
        this.control = control;
    }

    @Override
    public void run() {
        synchronized (control) {
            int symbolsNumber = 500;
            for (int i = 0; i < symbolsNumber; i++) {
                while (control.flag && this.text.equals("-")) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                while (!control.flag && this.text.equals("|")) {
                    try {
                        control.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.print(this.text.charAt(this.text.length() - 1));
                if (i % 50 == 0 && i != 0) {
                    System.out.print('\n');
                }
                control.flag = !control.flag;
                control.notifyAll();
            }
        }
    }
}