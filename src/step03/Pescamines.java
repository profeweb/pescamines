package step03;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    // Tauler de joc del pescamines
    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step03.Pescamines");
    }

    public void setup(){

        // Constructor del tauler (8x8, 10 bombes)
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);

        // Crea les caselles del tauler
        t.setCaselles();

    }

    public void draw(){
        background(220);

        // Dibuixa el tauler
        t.display(this);
    }
}
