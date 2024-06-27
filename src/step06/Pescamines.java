package step06;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    // Tauler de joc del pescamines
    Tauler t;

    // Estats del joc
    boolean gameOver, winner;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step06.Pescamines");
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

        // Inicialitza l'estat del joc
        gameOver = false;
        winner = false;

    }

    public void draw(){
        background(220);

        // Dibuixa el tauler
        t.display(this);

        // Partida perduda
        if (gameOver) {

            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48);
            text("GAME OVER", width/2, height/2);
        }
        // Guanya jugador/a
        else if(winner){

            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48);
            text("YOU WIN", width/2, height/2);
        }
    }

    public void mousePressed(){
        if (!gameOver) {

            // Obté fila i columna de la casella clicada
            int[] indexos = t.casellaClickada(mouseX, mouseY);
            int f = indexos[0];
            int c = indexos[1];

            // Actualitza els clicks
            t.updateClicks();

            // Click sobre casella amb bomba
            if (t.tauler[f][c].esBomba) {
                gameOver = true;
                t.setDescobrirTotes(true);
            }
            // Click sobre casella lliure
            else {
                t.updateTauler(f, c);
                // Totes les caselles descobertes
                if(t.numCasellesVisibles == t.numCasellesPerObrir){
                    winner = true;
                    t.setDescobrirTotes(true);
                }
            }
        }
    }
}
