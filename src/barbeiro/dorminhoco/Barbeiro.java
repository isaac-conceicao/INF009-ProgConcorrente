package barbeiro.dorminhoco;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Barbeiro extends Thread{
    static final int CADEIRAS = 3;//qtd de cadeiras disponiveis || tamanho da fila de espera
    static Semaforo cliente = new Semaforo(0);//semaforo de clientes
    static Semaforo barbeiro = new Semaforo(0);//semaforo do barbeiro
    static Semaforo semaforo = new Semaforo(1);//semaforo de corte ou da fila de espera
    static int esperando = 0;//qtd de clientes em espera
    static int chegaram = 0;//qtd de clientes que chegaram
    static int atendidos = 0;//qtd de clientes ja atendidos || qtd de cortes finalizados
    static int desistiram = 0;//qtd de clientes que foram embora
    
    @Override
    public void run(){
        while(true){//trabalha pra sempre (aka slavery)
            this.barbeiro();
        }
    }
    
    public void barbeiro(){
        if(esperando <= 0){//se não tem ninguem esperando
            System.out.println("zZ:O barbeiro dormiu!");
            cliente.P();//chegada de um cliente
        } else {//se tem alguem esperando
            semaforo.P();//anda a fila de espera
            esperando--;
            barbeiro.V();//UP acorda o barbeiro
            semaforo.V();//UP anda a fila de espera
            cortarCabelo();
        }
    }
    
    public void cortarCabelo(){
    	System.out.println("--:O barbeiro chamou um cliente.");
        System.out.println("O barbeiro está cortando!");
        try {
            Thread.sleep(sleepTime());//tempo gasto no corte
            System.out.println("<<:O barbeiro terminou o corte!");
            Barbeiro.atendidos++;//incrementa a qtd de cortes
            Barbeiro.relatorio();//imprime o relatorio parcial
            Thread.sleep(2000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Barbeiro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    public static int sleepTime(){//gera um valor para dar o tempo da thread dormir
        return (int) Math.floor(Math.random() * 4000);
    }
    
    public static void relatorio() {
    	System.out.println("\n-------------relatorio parcial-------------");
    	System.out.println("Até o momento:\n" + chegaram + " clientes chegaram.\n"
    					+ desistiram +" clientes desistiram.\n"
    					+ atendidos +" clientes foram atendidos.\n"
    					+ "Temos "+esperando+" clientes aguardando.");
    	System.out.println("-------------------------------------------\n");
    }
    
}
