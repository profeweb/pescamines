package stepFinal;

import processing.core.PApplet;
import processing.core.PFont;

public class Pescamines extends PApplet {

    // Tauler de joc del pescamines
    Tauler t;

    // Estats del joc
    boolean gameOver, winner;

    // Fonts dels textos
    PFont font1, font2;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("stepFinal.Pescamines");
    }

    public void setup(){

        // Inicialitza el joc del pescamines
        setGame();

        // Carrega la font per emprar en els textos
        font1 = createFont("KGHAPPY.ttf", 32);
        font2 = createFont("KGHAPPY.ttf", 48);
    }

    // Inicialitza el joc del pescamines
    void setGame(){
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);
        t.setBombs(this);
        t.calculateNumbers();
        t.setImatges(this);
        gameOver = false;
        winner = false;
    }

    public void draw(){
        background(255);
        t.display(this, font1);
        if (gameOver) {
            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255);
            textAlign(CENTER, CENTER);
            textFont(font2);
            text("GAME OVER", width/2, height/2);
        }
        else if(winner){
            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255);
            textFont(font2);
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
            if (t.tauler[f][c].esBomba) {
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
