package HiSeSitor;

import java.util.ArrayList;
import java.util.Random;

public class Estrategia {
    public ArrayList<Sensor> sensores = new ArrayList<>();
    public Estado estado;

    public void updateSensores() {
        for (Sensor s : sensores) {
            s.updateKnoledge();
        }
    }
    public ArrayList<Sensor> getSensores() {
        return sensores;
    }

    public void init(ArrayList<Sensor> sensores) {
        //Depende de la estrategia inicializar unos u otros sensores
        this.sensores = sensores;
    }

    //dependera de cada estrategia
    public void update() { //REVISAR ESTO PORQUE EL TEMA NODOS NO MOLA NADA
        int i = 9;
        Grafo memoria = estado.memoria;
        for (Sensor s : sensores) {
            for (Nodo n : s.sensorKnoledge) {
                if (getDistancia(n, estado.actual) < 2) {
                    n.score = i;
                    i--;
                } else {n.score = -1;}
            }
            i=9;
        }

    }

    public Nodo getObjetivo(){

    }
}
