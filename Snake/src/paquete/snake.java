/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author luisa
 */
public class snake extends JPanel{
    
    Color colorsnake=Color.GREEN ; 
    Color colormanzanas= Color.red;
    int tammax,tam,can;
    List<int[]> culebrita= new ArrayList<>();
    int[] manzanas= new int [2];
    String direccion="derecha";
    String direccionProxima="derecha";
    
    Thread hilo;
    Caminante camino;
    
    public snake (int tammax, int can) {
        this.tammax=tammax;
        this.can=can;
        this.tam= tammax/can;
        int [] a={can/2-1, can/2-1};
        int [] b={can/2, can/2-1};  
        culebrita.add(a);
        culebrita.add(b);
        generarManzanas();
       
        
        camino=new Caminante(this);
        hilo=new Thread(camino);
        hilo.start();
    }
    
    @Override
    public void paint (Graphics pintar) {
        super.paint(pintar);
        pintar.setColor(colorsnake);
   
//        for (int i = 0; i < culebrita.size(); i++) {
//              pintar.fillRect(culebrita.get(i)[0]*tam, culebrita.get(i)[1]*tam, tam-1, tam-1);
//            
//        }
         for (int [] par:culebrita) {
              pintar.fillRect(par[0]*tam, par[1]*tam, tam-1, tam-1);
            
        }
          pintar.setColor(colormanzanas);
          pintar.fillRect(manzanas[0]*tam, manzanas[1]*tam, tam-1, tam-1);
         
    }
    
    public void avanzar() {
        igualarDirec();
        int[] ultimo= culebrita.get(culebrita.size()-1);
        int agregarx=0;
        int agregary=0;
        
        switch (direccion) {
            
            case"derecha":
                agregarx=1;
                break;
             case"izquierda":
                agregarx=-1;
                break;
             case"arriba":
                agregary=-1;
                break;
             case"abajo":
                agregary=1;
                break;
            
        }
        
        int[] nuevo={Math.floorMod(ultimo[0]+agregarx,can),Math.floorMod(ultimo[1]+agregary,can)};
        boolean existe=false;
        for (int i = 0; i < culebrita.size(); i++) {
            if(nuevo[0]==culebrita.get(i)[0]&&nuevo[1]==culebrita.get(i)[1]) {
                existe=true;
                break;
                
            }
            
        }
        
        if (existe){
            JOptionPane.showMessageDialog(this, "perdiste");
        } else {
            if(nuevo[0]==manzanas[0]&& nuevo[1]==manzanas[1]) {
                culebrita.add(nuevo);
                generarManzanas();
            }
            else {
         culebrita.add(nuevo);
        culebrita.remove(0);
        
        
    }
      }
    }
    public void generarManzanas () {
        boolean existe=false;
        int a=(int)(Math.random()*can);
        int b=(int)(Math.random()*can);
        
       for (int[] par: culebrita) {
           if(par[0]==a && par[1]==b) {
               existe=true;
               generarManzanas();
               break;
               
           }
        }
       
       if(!existe) {
           this.manzanas[0]=a;
           this.manzanas[1]=b;
       }
    }
    public void cambiarDireccion (String direc) {
        if((this.direccion.equals("derecha")||this.direccion.equals("izquierda"))&& (direc.equals("arriba")|| direc.equals("abajo"))) {
            this.direccionProxima=direc;
        }
         if((this.direccion.equals("arriba")||this.direccion.equals("abajo"))&& (direc.equals("izquierda")|| direc.equals("derecha"))) {
            this.direccionProxima=direc;
        }
        
    }
    public void igualarDirec (){
        this.direccion=this.direccionProxima;
    }
}