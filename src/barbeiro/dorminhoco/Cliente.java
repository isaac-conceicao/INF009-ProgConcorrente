package barbeiro.dorminhoco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread{
	
	@Override
    public void run(){
        while(true){
            this.clientes();
            System.out.println("Um cliente chegou!");
            try {
                Thread.sleep(Barbeiro.sleepTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void clientes(){
        Barbeiro.semaforo.P();//DOWN
        
        if(Barbeiro.esperando < Barbeiro.CADEIRAS){
            Barbeiro.esperando++; //cliente se senta na cadeira para esperar sua vez
            Barbeiro.cliente.V();//UP
            Barbeiro.semaforo.V();//UP
        } else {
            Barbeiro.semaforo.V();//UP
            System.out.println("O cliente foi embora!");
        }
    }
}