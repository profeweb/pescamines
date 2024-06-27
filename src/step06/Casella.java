package step06;

import processing.core.PApplet;

public class Casella {

    // Posició de la casella en el tauler
    int fila, columna;

    // Número de bombes al voltant de la casella
    int numBombes;

    // Estats de la casella
    boolean esBomba, descobert;

    // Constructor
    Casella(int f, int c, boolean b) {
        this.fila = f;
        this.columna = c;
        this.numBombes = 0;
        this.esBomba = b;
        this.descobert = false;
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
    void setDescobert(boolean b) {
        this.descobert = b;
    }

    // Dibuixa la casella
    void display(PApplet p5, float xc, float yc, float w, float h, boolean descobrirTot) {

        // Descobrir totes les caselles (final de joc)
        if (descobrirTot) {

            // Rectangle
            p5.fill(150); p5.strokeWeight(3);
            p5.rect(xc, yc, w, h, 5);

            // Casella bomba
            if (this.esBomba) {
                p5.fill(0);
                p5.circle(xc + w / 2, yc + h / 2, w / 2);
            }
            // Casella sense bomba
            else {
                p5.fill(0); p5.textSize(36); p5.textAlign(p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
        }
        // Només caselles descobertes
        else {

            // Casella Descoberta
            if (this.descobert) {
                p5.fill(220); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h);

                p5.fill(0); p5.textSize(36); p5.textAlign(p5.CENTER);
                p5.text(this.numBombes, xc + w / 2, yc + h / 2);

            }
            // Casella Tapada
            else {
                p5.fill(150); p5.strokeWeight(3);
                p5.rect(xc, yc, w, h, 5);
            }

        }
    }
}
