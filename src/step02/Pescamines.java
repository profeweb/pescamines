package step02;

import processing.core.PApplet;

public class Pescamines extends PApplet {

    // Declara 3 variabes de classe Casella
    Casella c1, c2, c3;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step02.Pescamines");
    }

    public void setup(){
        // Crea les caselles amb el constructor
        c1 =  new Casella(0, 0, false);
        c2 =  new Casella(0, 1, false);
        c3 =  new Casella(0, 2, false);

        // Utilitzam els setters per modificar les propietats de les caselles
        c1.setEsBomba(true);
        c2.setNumBombes(3);
    }

    public void draw(){
        background(220);

        // Dibuixa les caselles (descobertes)
        c1.display(this, 100, 100, 100, 100, true);
        c2.display(this, 200, 100, 100, 100, true);
        c3.display(this, 300, 100, 100, 100, true);
    }
}
