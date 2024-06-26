package stepFinal;

import processing.core.PApplet;
import processing.core.PImage;

public class Casella {

    // Posició de la casella en el tauler
    int fila, columna;

    // Número de bombes al voltant de la casella
    int numBombes;

    // Estats de la casella
    boolean esBomba, visible;



    Casella(int f, int c, boolean b) {
        this.fila = f;
        this.columna = c;
        this.numBombes = 0;
        this.esBomba = b;
        this.visible = false;
    }

    // Setter de la propietat esBomba
    void setEsBomba(boolean b) {
        this.esBomba = b;
    }

    // Setter de la propietat numBombes
    void setNumBombes(int nb) {
        this.numBombes = nb;
    }

    // Setter de la propietat visible
    void setVisible(boolean b) {
        this.visible = b;
    }



    // Dibuixa la casella
    void display(PApplet p5, float xc, float yc, float w, float h, boolean showAll, PImage imgTapada, PImage imgDestapada, PImage imgBomba) {

        if (showAll) {
            p5.fill(150); p5.strokeWeight(3);
            p5.rect(xc, yc, w, h, 5);
            if (this.esBomba == true) {
                p5.fill(0);
                p5.circle(xc + w / 2, yc + h / 2, w / 2);
                p5.image(imgBomba, xc, yc, w, h);
            } else {
                p5.image(imgDestapada, xc, yc, w, h);
                p5.fill(0);
                p5.textSize(36);
                p5.textAlign(p5.CENTER, p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
        } else {

            if (this.visible) {
                //p5.fill(220); p5.strokeWeight(3);
                //p5.rect(xc, yc, w, h);
                p5.image(imgDestapada, xc, yc, w, h);
                p5.fill(0);
                p5.textSize(36);
                p5.textAlign(p5.CENTER, p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
            else {
                //p5.fill(150); p5.strokeWeight(3);
                //p5.rect(xc, yc, w, h, 5);
                p5.image(imgTapada, xc, yc, w, h);
            }

        }
    }
}