package step07;

import processing.core.PApplet;
import processing.core.PFont;
import processing.sound.SoundFile;

public class Pescamines extends PApplet {

    // Tauler de joc del pescamines
    Tauler t;

    // Estats del joc
    boolean gameOver, winner;

    // Fonts del texte
    PFont font1, font2;

    // Sons dels clicks
    SoundFile soBomba, soClick;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step07.Pescamines");
    }

    public void setup(){

        // Inicialitza el joc
        setGame();

        // Carrega la font per emprar en els textos
        font1 = createFont("KGHAPPY.ttf", 32);
        font2 = createFont("KGHAPPY.ttf", 48);

        // Carrega els sons
        soBomba = new SoundFile(this, "explosio.wav");
        soClick = new SoundFile(this, "click.wav");

    }

    public void draw(){
        background(220);

        // Dibuixa el tauler
        t.display(this, font1);

        // Partida perduda
        if (gameOver) {

            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48); textFont(font2);
            text("GAME OVER", width/2, height/2);
        }
        // Guanya jugador/a
        else if(winner){

            fill(50, 150); noStroke();
            rect(0, 0, width, height);

            fill(255); textAlign(CENTER); textSize(48); textFont(font2);
            text("YOU WIN", width/2, height/2);
        }
    }

    public void mousePressed(){
        if (!gameOver) {

            // Obté fila i columna de la casella clicada
            int[] indexos = t.casellaClicada(mouseX, mouseY);
            int f = indexos[0];
            int c = indexos[1];

            // Actualitza els clicks
            t.updateClicks();

            // Click sobre casella amb bomba
            if (t.tauler[f][c].esBomba) {
                gameOver = true;
                t.setDescobrirTotes(true);
                soBomba.play();
            }
            // Click sobre casella lliure
            else {
                soClick.play();
                t.updateTauler(f, c);
                // Totes les caselles descobertes
                if(t.numCasellesVisibles == t.numCasellesPerObrir){
                    winner = true;
                    t.setDescobrirTotes(true);
                }
            }
        }
    }

    // Inicialitza el joc del pescamines
    void setGame(){

        // Crea el tauler
        t = new Tauler(8, 10, 0, 70, width/8, (height-70)/8);

        // Crea les caselles del tauler
        t.setCaselles();

        // Posiciona les bombes en el tauler
        t.setBombes(this);

        // Calcula el número de bombes que envolten cada casella
        t.calculaNumBombes();

        // Carrega les imatges per representar les caselles
        t.setImatges(this);

        // Inicialitza l'estat del joc
        gameOver = false;
        winner = false;
    }

    public void keyPressed(){
        // Reset del joc del pescamines
        if(key == 'r' || key == 'R'){
            setGame();
        }
    }
}
