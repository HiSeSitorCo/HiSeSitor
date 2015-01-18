package HiSeSitor;

import java.awt.geom.Arc2D.Double;
import java.util.ArrayList;

public class Estrategia_espiral_testv4 extends Estrategia {
    public int periodo;
    public ArrayList<Nodo> visitados = new ArrayList<>();
    private int espiral;
    private int espiralAux = 0;
    private int pasoPasado = 0;
    private int div;
    private int itPasado = 1;
    private int jGlob = 2;
    public int transito=1;
    public int transitado=0;
    public int freeze = -1;
    
    protected int tam = 1;
    public Estrategia_espiral_testv4(ArrayList<Sensor> sen, ArrayList<Integer> v) {
        super(sen, v);
        nombre = "Espiral v4";
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void asignaVariables(ArrayList<Integer> v) throws Exception {
        if (v.size() != tam) {
            new Exception("Variables no correwspondientes con la estrategua");
        }
        espiral = v.get(0);
        div = v.get(1);
        
    }

    @Override
    public void update() {
        int i = espiral;
        int j = jGlob;
        int paso = pasoPasado; 
        int cont = 0;
        int mem = -1;
        int it = itPasado;
        Nodo actual = estado.getActual();
        visitados.add(actual);

        int x = (int) actual.pos.x;
        int y = (int) actual.pos.y;
        int notNull = 0;
        int first = 1;
        espiralAux = espiral;
        //bluce de transito
        
        
        //He concluido mi camino
        if (transito <= 1) {
            transitado = 1;
            pasoPasado=(pasoPasado+1)%4;
            paso= pasoPasado;
            
            
        } else {//Continuo caminando
            freeze = 1;
            
        }
        
        /*if (transito-1<1) {

            transito = it;
        
            transitado=0;
            if (it > 1) {
                if (transitado == -1)
                    transitado = 0;
                else
                    transitado++;
            }
            
        } else {
            if (transitado == -1)
                transitado = 0;
            else
                transitado++;
            
        }*/
        
        int MAX = memoria.getListaNodos().size()/2;
        
        while (espiralAux > 0 && cont < MAX) {
            Nodo bus;
            int itAux;
            if (j-- <= 0) {
                j=1;
                it++;
            }       
            mem = it;
            
            itAux = it;
        
            switch(paso) {
            
                //arriba
                case 0:
                        
                        if ((bus = memoria.getNodo(x, --y)) != null) {
                            calculaEstima(bus);
                            espiralAux--;
                            notNull=1;
                            cont++;
                        } else {
                            y++;
                            break;}
                    
                    break;
                //abajo
                case 2:
                        
                        if ((bus = memoria.getNodo(x, ++y)) != null) {
                            calculaEstima(bus);
                            espiralAux--;
                            notNull=1;
                            cont++;
                        } else {y--;
                        break;}
                    
                    break;
                //derehca
                case 1:
                        if ((bus = memoria.getNodo(++x, y)) != null) {
                            calculaEstima(bus);
                            espiralAux--;
                            notNull=1;
                            cont++;
                        } else {x--;
                        break;}
                    break;
                //izquierda
                default:
                        if ((bus = memoria.getNodo(--x, y)) != null) {
                            calculaEstima(bus);
                            espiralAux--;
                            notNull=1;
                            cont++;
                        } else {x++;
                        break;}
                    break;
            }
            if (notNull!=0) {

                paso=(paso+1)%4;
                notNull = 0;
            }
            if (first == 1) {
                
                first =0;
                itPasado = it;
                jGlob = j;
            }
        
        }
        
        for (Nodo n: visitados) {
            n.score = -10;
        }

        
    }
    
    
    @Override
    public double estima(Nodo n) {
        double gan = 0;
        if ((gan = dameGananciaMedia(n)) < 0) {
            gan = Math.abs(gan);
            n.score = espiralAux*div/gan;
        } else {
            gan = Math.abs(gan);
            n.score = espiralAux*gan/div;
        }
        return n.score;
    }
    
    public double dameGananciaMedia(Nodo n) {
        int ganancia = 0;
        int num = 0;
        for (Nodo ady : memoria.getAdjacents(n)) {
            if (!ady.isEstimacion()) {
                ganancia+=ady.ganancia;
                num++;
            }               
        }
        if (num!=0) {
            return ganancia/num;
        }
        return 0;
        
    }
    
    @Override
    public void reset_ext() {

        visitados = new ArrayList<>();
        espiral = -1;
        espiralAux = 0;
        pasoPasado = 0;
        div = 0;
        itPasado = 0;
    };

    @Override
    public double calcula(Nodo n) {
        if (estado.getActual() == n) n.score = -10;
        n.score += espiralAux;
        
        return n.score;
    }
}
