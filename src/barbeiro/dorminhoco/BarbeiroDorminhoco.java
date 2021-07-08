package barbeiro.dorminhoco;

public class BarbeiroDorminhoco {

    public static void main(String[] args) {
        Barbeiro b = new Barbeiro();
        Thread tb = new Thread(b);
        
        Cliente c = new Cliente();
        Thread tc = new Thread(c);
        
        tb.start();
        tc.start();
    }
    
}
