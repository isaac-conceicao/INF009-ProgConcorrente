package barbeiro.dorminhoco;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Semaforo {
    private int count = 0;
    
    public Semaforo(int valorIni){
        this.count = valorIni;
    }
    
    public synchronized void V(){//UP
        this.count++;
        notify();//acordar o processo
    }
    
    public synchronized void P(){//DOWN
        while(this.count <= 0){//espera para poder entrar na zona crítica
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.count--;//processo pode entrar na zona crítica
    }
    
}