package barbeiro.dorminhoco;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Semaforo {
    private int count = 0;
    
    public Semaforo(int valorIni){
        this.count = valorIni;
    }
    
    public synchronized void V(){//UP libera a regi�o cr�tica
        this.count++;//incrementa o contador
        notify();//acorda o processo
    }
    
    public synchronized void P(){//DOWN: solicita acesso a regi�o cr�tica
        while(this.count <= 0){//caso n�o esteja livre
            try {
                wait();//enfila o processo
            } catch (InterruptedException ex) {
                Logger.getLogger(Semaforo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.count--;//acessa a regiao critica
    }
    
}