import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    private static final int N_BLUE_BALLS = 1000;
    private static final int N_RED_BALLS = 1;
    private static final int N_JOIN_EXPERIMENT = 10;
    public static final int WIDTH = 850;
    public static final int HEIGHT = 600;
    public static final int PocketSize = 24;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce program");
        this.canvas = new BallCanvas();
        Pocket p1 = new Pocket(canvas, 0, 0);
        this.canvas.addPocket(p1);
        Pocket p2 = new Pocket(canvas, WIDTH - 14 - PocketSize, 0);
        this.canvas.addPocket(p2);
        Pocket p3 = new Pocket(canvas, 0, (HEIGHT - 73) - PocketSize);
        this.canvas.addPocket(p3);
        Pocket p4 = new Pocket(canvas, WIDTH - 14 - PocketSize, (HEIGHT - 73) - PocketSize);
        this.canvas.addPocket(p4);
        System.out.println("In Frame Thread name = "
                + Thread.currentThread().getName());
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);
        JLabel scoreLabel = new JLabel();
        scoreLabel.setText("Current score: " + this.canvas.getScore());

        System.out.println("Daemon thread name = " + Thread.currentThread().getName());
//        while (!Thread.currentThread().isInterrupted()) {
//            scoreLabel.setText("Score: " + this.canvas.getScore());
//            scoreLabel.repaint();
//            try {
//                Thread.sleep(5);
//            } catch (InterruptedException ignored) {
//            }
//        }

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(scoreLabel);
        buttonPanel.setBackground(Color.lightGray);
        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");
        JButton buttonAddBlueBall = new JButton("Add blue ball");
        JButton buttonAddRedBall = new JButton("Add red ball");
        JButton buttonPriorityExperiment = new JButton("Red/Blue priority experiment");
        JButton buttonJoinExperiment = new JButton("Join");
//        buttonStart.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                Ball b = new Ball(canvas);
//                canvas.add(b);
//
//                BallThread thread = new BallThread(b);
//                thread.start();
//                System.out.println("Thread name = " +
//                        thread.getName());
//            }
//        });
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);
            }
        });

        buttonAddBlueBall.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = Color.BLUE;
                        createBall(color, Thread.NORM_PRIORITY);
                    }
                });

        buttonAddRedBall.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Color color = Color.RED;
                        createBall(color, Thread.NORM_PRIORITY);
                    }
                });

        buttonPriorityExperiment.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createPriorityExperiment();
                    }
                });

        buttonJoinExperiment.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Runnable updatePanel =
                                new Runnable() {
                                    public void run() {
                                        createJoinExperiment();
                                    }
                                };
                        Thread t = new Thread(updatePanel);
                        t.start();
                    }
                });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(buttonAddBlueBall);
        buttonPanel.add(buttonAddRedBall);
        buttonPanel.add(buttonPriorityExperiment);
        buttonPanel.add(buttonJoinExperiment);
        content.add(buttonPanel, BorderLayout.SOUTH);


    }


    private void createBall(Color color, int priority) {
        Ball b = new Ball(canvas, color);
        canvas.add(b);

        BallThread thread = new BallThread(b);
        thread.setPriority(priority);
        thread.start();

        System.out.println("Thread name = " + thread.getName());
    }

    private void createBall(Color color, int priority, int x, int y) {
        Ball b = new Ball(canvas, color, x, y);
        canvas.add(b);

        BallThread thread = new BallThread(b);
        thread.setPriority(priority);
        thread.start();

        System.out.println("Thread name = " + thread.getName());
    }

    private void createBall(Color color, int priority, int x, int y, int TIME) {
        Ball b = new Ball(canvas, color, x, y);
        canvas.add(b);

        BallThread thread = new BallThread(b);
        thread.setTIME(TIME);
        thread.setPriority(priority);
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Thread name = " + thread.getName());
    }

    private void createPriorityExperiment() {
        Color color = Color.BLUE;
        for (int i = 0; i < N_BLUE_BALLS; i++) {
            createBall(color, 1, WIDTH / 2, HEIGHT / 2);
        }

        color = Color.RED;
        for (int i = 0; i < N_RED_BALLS; i++) {
            createBall(color, 10, WIDTH / 2, HEIGHT / 2);
        }
    }

    private void createJoinExperiment() {
        int TIME = 1000;

        for (int i = 0; i < N_JOIN_EXPERIMENT; i++) {
            Color color;
            if (i % 2 == 0) {
                color = Color.BLUE;
            } else {
                color = Color.RED;
            }

            Ball b = new Ball(canvas, color, WIDTH / 2, HEIGHT / 2);
            canvas.add(b);

            BallThread thread = new BallThread(b);
            thread.setTIME(TIME);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            System.out.println("Thread name = " + thread.getName());
        }
    }
}