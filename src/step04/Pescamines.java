package step04;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    // Tauler de joc del pescamines
    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step04.Pescamines");
    }

    public void setup(){

        // Constructor del tauler (8x8, 10 bombes)
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);

        // Crea les caselles del tauler
        t.setCaselles();

        // Col·loca les 10 bombes en el tauler aleatòriament
        t.setBombes(this);

        // Calcula el número de bombes al voltant de cada casella
        t.calculateNumbers();

    }

    public void draw(){
        background(220);

        // Dibuixa el tauler
        t.display(this);
    }

    public void mousePressed(){
        // Canvia l'estat de la propietat descobrirTotes del tauler
        boolean b = !t.descobrirTotes;
        t.setDescobrirTotes(b);
    }
}
