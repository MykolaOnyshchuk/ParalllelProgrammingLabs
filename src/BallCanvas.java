import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Pocket> pockets = new ArrayList<>();
    private int score = 0;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public void add(Ball b){
        this.balls.add(b);
    }
    public void addPocket(Pocket p){
        this.pockets.add(p);
    }

    public void remove(Ball b) {
        //Score.increase();
        this.balls.remove(b);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        for(int i = 0; i < balls.size();i++){
            for (Pocket p : pockets) {
                if (balls.get(i).isInPool(p)) {
                    balls.get(i).isInPool = true;
                    remove(balls.get(i));
                    score++;
                    break;
                }
            }
            if (!balls.get(i).isInPool) {
                balls.get(i).draw(g2);
            }
        }

        for(int i = 0; i < pockets.size();i++){
            Pocket p = pockets.get(i);
            p.draw(g2);
        }
    }
}