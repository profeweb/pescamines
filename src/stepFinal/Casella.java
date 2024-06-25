package stepFinal;

import processing.core.PApplet;

public class Casella {

    int fila, columna;
    int numBombes;
    boolean bomba, visible;

    Casella(int f, int c, boolean b) {
        this.fila = f;
        this.columna = c;
        this.numBombes = 0;
        this.bomba = b;
        this.visible = false;
    }

    void setBomba(boolean b) {
        this.bomba = b;
    }

    void setNumber(int nb) {
        this.numBombes = nb;
    }

    void setVisible(boolean b) {
        this.visible = b;
    }

    void display(PApplet p5, float xc, float yc, float w, float h, boolean showAll) {

        if (showAll) {
            p5.fill(150); p5.strokeWeight(3);
            p5.rect(xc, yc, w, h, 5);
            if (this.bomba == true) {
                p5.fill(0);
                p5.circle(xc + w / 2, yc + h / 2, w / 2);
            } else {
                p5.fill(0);
                p5.textSize(18);
                p5.textAlign(p5.CENTER, p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);
            }
        } else {

            if (this.visible) {
                p5.fill(220); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h);
                p5.fill(0);
                p5.textSize(18);
                p5.textAlign(p5.CENTER, p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);
            }
            else {
                p5.fill(150); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h, 5);
            }

        }
    }
}