
package paquete;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelFondo extends JPanel{
    
    Color colorfondo=Color.gray; 
    int tammax,tam,can;
    
    public PanelFondo (int tammax, int can) {
        this.tammax=tammax;
        this.can=can;
        this.tam= tammax/can;
    }
    
    @Override
    public void paint (Graphics pintar) {
        super.paint(pintar);
        pintar.setColor(colorfondo);
        for (int i = 0; i < can; i++) {
            for (int j = 0; j < can; j++) {
                 pintar.fillRect(i*tam, j*tam, tam-1, tam-1);
                
            }
            
        }
    }
    
}
