public class BallThread extends Thread {
    private Ball b;
    private int TTL = 10000;

    public BallThread(Ball ball){
        b = ball;
    }
    @Override
    public void run(){
        try{
            for(int i = 1; i < 10000; i++){
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
                if (b.rolledIntoPocket) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " was interrupted");
                    break;
                }

            }
        } catch(InterruptedException ex){

        }
    }


    public void setTTL(int TTL) {
        this.TTL = TTL;
    }
}