package stepFinal;

import processing.core.PApplet;

import static processing.core.PApplet.floor;
import static processing.core.PApplet.max;

public class Tauler {

    float x, y, w, h;
    int num;

    boolean showAll;
    int numBombes, numPoints, numClicks;
    int numCasellesVisibles, numCasellesObrir;

    Casella[][] tauler;

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

    void creaCaselles(){
        this.tauler = new Casella[this.num][this.num];
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                this.tauler[f][c] = new Casella(f, c, false);
            }
        }
    }

    void setShowAll(boolean b){
        this.showAll = b;
    }

    void updateClicks(){
        this.numClicks++;
    }


    void setBombs(PApplet p5){
        int num = 0;
        do {
            int rf = floor(p5.random(0, this.num));
            int rc = floor(p5.random(0, this.num));
            if(this.tauler[rf][rc].bomba == false){
                this.tauler[rf][rc].setBomba(true);
                num++;
            }
        }while(num<this.numBombes);
    }


    void display(PApplet p5){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                float xc = this.x + this.w*c;
                float yc = this.y + this.h*f;
                this.tauler[f][c].display(p5, xc, yc, this.w, this.h, this.showAll);
            }
        }

        p5.fill(255, 0,0);
        p5.rect(0, 0, p5.width, this.y);
        p5. fill(0); p5.textSize(24); p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text("POINTS: "+ this.numPoints+ " / CLICKS: "+ this.numClicks, p5.width/2, 40);
    }

    void calculateNumbers(){
        for(int f = 0; f<this.num; f++){
            for(int c = 0; c<this.num; c++){
                if(!this.tauler[f][c].bomba){
                    int n = 0;
                    for(int fc = max(0, f-1); fc >= 0 && fc<this.num && fc <= f+1; fc++){
                        for(int rc = max(0, c-1); rc>=0 && rc<this.num && rc <=c+1; rc++){
                            if((fc!=f || rc!=c) && this.tauler[fc][rc].bomba){
                                n++;
                            }
                        }
                    }
                    this.tauler[f][c].setNumber(n);
                }
            }
        }
    }

    int[] cellClicked(float mx, float my){
        int f = floor((my - this.y) / this.h);
        int c = floor((mx - this.x) / this.w);
        int[] indexos = {f, c};
        return indexos;
    }

    void updateTauler(int f, int c){
        if(!this.tauler[f][c].bomba){
            this.descobreix(f, c);
        }
    }

    boolean esUnZeroOcult(int fc, int rc){
        return !this.tauler[fc][rc].visible &&
                this.tauler[fc][rc].numBombes == 0 &&
                !this.tauler[fc][rc].bomba;
    }

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