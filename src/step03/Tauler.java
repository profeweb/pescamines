package step03;

import processing.core.PApplet;
import processing.core.PFont;

public class Tauler {

    // Posició
    float x, y;

    // Mides
    float w, h;

    // Número de caselles x fila
    int num;

    // Destapa totes les caselles
    boolean descobrirTotes;

    // Estadístiques del joc
    int numBombes, numPoints, numClicks;
    int numCasellesVisibles, numCasellesPerObrir;

    // Tauler del joc pescamines
    Casella[][] tauler;

    // Constructor
    Tauler(int m, int nb, float x, float y, float w, float h){

        // Número de caselles per fila
        this.num = m;

        // Número de bombes
        this.numBombes = nb;

        // Posició del tauler
        this.x = x; this.y = y;

        // Mides del tauler
        this.w = w; this.h = h;

        // Número de caselles sense bombes
        this.numCasellesPerObrir = (m*m) - nb;

        // Totes les caselles tapades inicialment
        this.descobrirTotes = false;

        // Estadístiques inicials a zero
        this.numPoints = 0;
        this.numClicks = 0;
        this.numCasellesVisibles = 0;

    }

    // Crea les caselles del tauler
    void setCaselles(){

        this.tauler = new Casella[this.num][this.num];

        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                this.tauler[f][c] = new Casella(f, c, false);
            }
        }
    }

    // Setter de la propietat descobrirTotes
    void setDescobrirTotes(boolean b){
        this.descobrirTotes = b;
    }

    // Dibuixa el tauler de joc
    void display(PApplet p5){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                // Calcula la posició de la casella (fila f, columna c)
                float xc = this.x + this.w*c;
                float yc = this.y + this.h*f;
                this.tauler[f][c].display(p5, xc, yc, this.w, this.h, this.descobrirTotes);
            }
        }

        // Estadístiques del joc
        p5.fill(184, 147, 196);
        p5.rect(0, 0, p5.width, this.y);
        p5. fill(0); p5.textSize(18); p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text("POINTS: "+ this.numPoints+ " / CLICKS: "+ this.numClicks, p5.width/2, 30);
    }



}
