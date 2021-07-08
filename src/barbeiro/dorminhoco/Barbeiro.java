package barbeiro.dorminhoco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Barbeiro extends Thread{
    static final int CADEIRAS = 5;//qtd de cadeiras disponiveis (tamanho maximo da fila de espera)
    static Semaforo cliente = new Semaforo(0);
    static Semaforo barbeiro = new Semaforo(0);
    static Semaforo semaforo = new Semaforo(1);
    static int esperando = 0;//qtd de clientes em espera
    static boolean cortando = false;
    static boolean dormindo = false;
    
    @Override
    public void run(){
        while(true){
            barbeiro();
        }
    }
    
    public void barbeiro(){
        if(esperando <= 0){
            System.out.println("O barbeiro dormiu!");
            dormindo = true;
            cliente.P();//DOWN
        } else {
            semaforo.P();//DOWN
            esperando--;
            barbeiro.V();//UP
            semaforo.V();//UP
            dormindo = false;
            cortarCabelo();
        }
    }
    
    public void cortarCabelo(){
        System.out.println("O barbeiro esta cortando!");
        cortando = true;//cortando o cabelo
        try {
            Thread.sleep(sleepTime());
            System.out.println("O barbeiro terminou o corte!");
            cortando = false;
            Thread.sleep(1000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public static int sleepTime(){//gera um valor aleatorio para dar o tempo da thread dormir
        return (int) Math.floor(Math.random() * 6000);
    }
    
}
