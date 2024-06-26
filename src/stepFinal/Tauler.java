package stepFinal;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import static processing.core.PApplet.floor;
import static processing.core.PApplet.max;

public class Tauler {

    // Posició
    float x, y;

    // Mides
    float w, h;

    // Número de caselles x fila
    int num;

    // Visualitza tot
    boolean showAll;

    // Estadístiques del joc
    int numBombes, numPoints, numClicks;
    int numCasellesVisibles, numCasellesObrir;

    // Tauler del joc pescamines
    Casella[][] tauler;

    // Imatges
    PImage imgTapada, imgDestapada, imgBomba;

    // Constructor
    Tauler(int m, int nb, float x, float y, float w, float h){

        this.num = m;
        this.numBombes = nb;
        this.numCasellesObrir = (m*m) - nb;
        this.x = x; this.y = y;
        this.w = w; this.h = h;
        this.showAll = false;

        this.numPoints = 0;
        this.numClicks = 0;
        this.numCasellesVisibles = 0;

        this.creaCaselles();
    }

    // Crea les caselles del tauler
    void creaCaselles(){
        this.tauler = new Casella[this.num][this.num];
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                this.tauler[f][c] = new Casella(f, c, false);
            }
        }
    }

    // Setter de la propietat showAll
    void setShowAll(boolean b){
        this.showAll = b;
    }

    // Setter de les propietats imgTapada, imgDestapada
    void setImatges(PApplet p5){
        this.imgTapada    = p5.loadImage("square.png");
        this.imgDestapada = p5.loadImage("destapat.png");
        this.imgBomba = p5.loadImage("bomba.png");
    }

    // Actualitza el número de clicks
    void updateClicks(){
        this.numClicks++;
    }


    // Estableix la posició de les bombes en el tauler
    void setBombs(PApplet p5){
        int num = 0;
        do {
            int rf = floor(p5.random(0, this.num));
            int rc = floor(p5.random(0, this.num));
            if(this.tauler[rf][rc].esBomba == false){
                this.tauler[rf][rc].setEsBomba(true);
                num++;
            }
        }while(num<this.numBombes);
    }


    // Dibuixa el tauler de joc
    void display(PApplet p5, PFont font){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                float xc = this.x + this.w*c;
                float yc = this.y + this.h*f;
                this.tauler[f][c].display(p5, xc, yc, this.w, this.h, this.showAll, imgTapada, imgDestapada, imgBomba);
            }
        }

        // Estadístiques del joc
        p5.fill(184, 147, 196);
        p5.rect(0, 0, p5.width, this.y);
        p5. fill(0); p5.textSize(18); p5.textAlign(p5.CENTER, p5.CENTER);
        p5.textFont(font);
        p5.text("POINTS: "+ this.numPoints+ " / CLICKS: "+ this.numClicks, p5.width/2, 30);
    }

    // Calcula els número de bombes al voltant de les caselles
    void calculateNumbers(){
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
    int[] cellClicked(float mx, float my){
        int f = floor((my - this.y) / this.h);
        int c = floor((mx - this.x) / this.w);
        int[] indexos = {f, c};
        return indexos;
    }

    // Actualitza el tauler en clickar a la fila f, columna c
    void updateTauler(int f, int c){
        if(!this.tauler[f][c].esBomba){
            this.descobreix(f, c);
        }
    }

    // Determina si una casella oculta és un zero
    boolean esUnZeroOcult(int fc, int rc){
        return !this.tauler[fc][rc].visible &&
                this.tauler[fc][rc].numBombes == 0 &&
                !this.tauler[fc][rc].esBomba;
    }

    // Descobreix la casella (f, c) i les que l'enrevolten amb zeros
    void descobreix(int f, int c){

        this.tauler[f][c].setVisible(true);
        this.numPoints += this.tauler[f][c].numBombes;
        this.numCasellesVisibles++;

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