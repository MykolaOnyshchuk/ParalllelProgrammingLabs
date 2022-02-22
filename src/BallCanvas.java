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
        this.balls.remove(b);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        score = 0;
        int previousBallsNumber = balls.size();
        for(int i = 0; i < balls.size(); i++){
            if (balls.size() < previousBallsNumber) {
                i--;
            }
            for (Pocket pocket : pockets) {
                if (balls.get(i).rolledIntoPocket(pocket)) {
                    balls.get(i).rolledIntoPocket = true;
                    previousBallsNumber = balls.size();
                    remove(balls.get(i));
                    score++;
                    break;
                }
            }
            if (!balls.isEmpty() && !balls.get(i).rolledIntoPocket) {
                balls.get(i).draw(g2);
            }
        }

        for (Pocket pocket : pockets) {
            pocket.draw(g2);
        }
    }
}