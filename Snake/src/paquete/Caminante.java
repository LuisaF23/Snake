
package paquete;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Caminante implements Runnable {
    
    snake sn;
    boolean estado=true;
    
    
 public Caminante (snake sn) {
     this.sn=sn;
     
 }
    
    @Override
   public void run() {
       
       while(estado) {
        sn.avanzar();
        sn.repaint();
        
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
        }
       }
   }
   public void parar () {
       this.estado=false;
   }
   }
    

