package step06;

import processing.core.PApplet;

import static processing.core.PApplet.floor;
import static processing.core.PApplet.max;

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

    // Estableix la posició de les bombes en el tauler
    void setBombes(PApplet p5){
        int num = 0;
        do {
            // Fila i columna aleatòries
            int rf = floor(p5.random(0, this.num));
            int rc = floor(p5.random(0, this.num));

            // Si ja no hi havia una bomba, l'hi posa i ho comptabilitza
            if(this.tauler[rf][rc].esBomba == false){
                this.tauler[rf][rc].setEsBomba(true);
                num++;
            }
        } while(num<this.numBombes);
        // Fins arribar al número de bombes.
    }


    // Calcula els número de bombes al voltant de les caselles
    void calculaNumBombes(){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                if(!this.tauler[f][c].esBomba){
                    int n = 0;
                    for(int fc = max(0, f-1); fc >= 0 && fc<this.num && fc <= f+1; fc++){
                        for(int rc = max(0, c-1); rc>=0 && rc<this.num && rc <=c+1; rc++){
                            if((fc!=f || rc!=c) && this.tauler[fc][rc].esBomba){
                                n++;
                            }
                        }
                    }
                    this.tauler[f][c].setNumBombes(n);
                }
            }
        }
    }


    // Determina quina casella (fila, columna) ha sigut clicada
    int[] casellaClickada(float mx, float my){
        int f = floor((my - this.y) / this.h);
        int c = floor((mx - this.x) / this.w);
        int[] indexos = {f, c};
        return indexos;
    }

    // Actualitza el número de clicks
    void updateClicks(){
        this.numClicks++;
    }

    // Actualitza el tauler en clickar a la fila f, columna c
    void updateTauler(int f, int c){
        if(!this.tauler[f][c].esBomba){
            this.descobreix(f, c);
        }
    }

    // Determina si una casella oculta és un zero
    boolean esUnZeroOcult(int fc, int rc){
        return !this.tauler[fc][rc].descobert &&
                this.tauler[fc][rc].numBombes == 0 &&
                !this.tauler[fc][rc].esBomba;
    }

    // Descobreix la casella (f, c) i les que l'enrevolten amb zeros
    void descobreix(int f, int c){

        this.tauler[f][c].setDescobert(true);
        // Actualitza els punts
        this.numPoints += this.tauler[f][c].numBombes;

        // Actualitza el número de caselles descobertes
        this.numCasellesVisibles++;

        // Comprova si ha de descobrir les caselles veïnes

        int  minF = max(0, f-1);
        int  maxF = this.num;

        for(int fc = minF; fc >= 0 && fc<maxF && fc <= f+1; fc++){
            int minC = max(0, c-1);
            int maxC = this.num;
            for(int rc= minC; rc>=0 && rc< maxC && rc <=c+1; rc++){
                if((fc!=f || rc!=c) && this.esUnZeroOcult(fc, rc)){
                    this.descobreix(fc, rc);
                }
            }
        }
    }


}
