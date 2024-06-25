package stepFinal;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    Tauler t;
    boolean gameOver, winner;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("stepFinal.Pescamines");
    }

    public void setup(){
        setGame();
    }

    void setGame(){
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);
        t.setBombs(this);
        t.calculateNumbers();
        gameOver = false;
        winner = false;
    }

    public void draw(){
        background(220);
        t.display(this);
        if (gameOver) {
            fill(255, 0, 0);
            textSize(48);
            textAlign(CENTER, CENTER);
            text("GAME OVER", width/2, height/2);
        }
        else if(winner){
            fill(255, 255, 0);
            textSize(48);
            textAlign(CENTER, CENTER);
            text("YOU WIN", width/2, height/2);
        }
    }

    public void mousePressed(){
        if (!gameOver) {
            int[] indexos = t.cellClicked(mouseX, mouseY);
            int f = indexos[0];
            int c = indexos[1];
            t.updateClicks();
            if (t.tauler[f][c].bomba) {
                gameOver = true;
                t.setShowAll(true);
            } else {
                t.updateTauler(f, c);
                if(t.numCasellesVisibles == t.numCasellesObrir){
                    winner = true;
                    t.setShowAll(true);
                }
            }
        }
    }

    public void keyPressed(){
        if(key == 'r' || key == 'R'){
            setGame();
        }
    }
}
