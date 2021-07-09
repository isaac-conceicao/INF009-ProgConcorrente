package barbeiro.dorminhoco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente extends Thread{
	
	@Override
    public void run(){
        while(true){//demanda indefinida
            this.clientes();
            try {
                Thread.sleep(Barbeiro.sleepTime());
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
	  
    public void clientes(){
    	Barbeiro.chegaram++;
        Barbeiro.semaforo.P();//DOWN
        System.out.println(">>:O cliente "+(Barbeiro.chegaram)+" chegou!");
        
        if(Barbeiro.esperando < Barbeiro.CADEIRAS){//se tem cadeira livre
        	System.out.println("++:O cliente "+Barbeiro.chegaram+" resolveu aguardar");
        	Barbeiro.esperando++; //cliente se senta em uma cadeira para esperar sua vez
            Barbeiro.cliente.V();//UP
            Barbeiro.semaforo.V();//UP
            
            System.out.println();
        } else {
            Barbeiro.semaforo.V();//UP
            System.out.println("<<:O cliente "+Barbeiro.chegaram+" desistiu e foi embora!");
        }
    }
}