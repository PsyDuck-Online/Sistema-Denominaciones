
package Modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {
    private String ruta;
    private File file;
    
    public BD() {
        this.ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "BD_Java" + File.separator;
    }
    
    public void insertDenominacion(Denominacion d) {
        file = new File(this.ruta+"d"+d.getValor()+".txt");
        if(file.exists()) {
            System.out.println("El registro ya existe");
        } else {
            try {
                Formatter crea = new Formatter(this.ruta+"d"+d.getValor()+".txt");
                
                crea.format("%s\r\n%s\r\n",
                        "valor="+d.getValor(),
                        "cantAlmacenada="+d.getCantidadAlmacenada()
                );
                
                crea.close();
                
                System.out.println("Se creo el registro");
            } catch (Exception e) {
                System.out.println("Error en el registro");
            }
        }       
    }
    
    public int selectValDenominacion(int val) {
        file = new File(this.ruta+"d"+val+".txt");
        int valor = 0;
        
        if(file.exists()) {
            
            try{
                FileInputStream fis = new FileInputStream(file);
                Properties mostrar = new Properties();
                mostrar.load(fis);
                
                valor = Integer.parseInt(mostrar.getProperty("valor"));
                
            }catch(Exception e){
                
            }
            
        } else {
            System.out.println("No existe");  
        }
        return valor;
    }
    
    public int selectCantDenominacion(int val) {
        file = new File(this.ruta+"d"+val+".txt");
        int cant = 0;
        
        if(file.exists()) {
            
            try{
                FileInputStream fis = new FileInputStream(file);
                Properties mostrar = new Properties();
                mostrar.load(fis);
                
                cant = Integer.parseInt(mostrar.getProperty("cantAlmacenada"));
                
            }catch(Exception e){
                
            }
            
        } else {
            System.out.println("No existe");  
        }
        return cant;
    }
    
    public void updateCantDenominacion(int val, int cantAlmacenada) {  
        file = new File(this.ruta+"d"+val+".txt");
        
        if(file.exists()) {
            try {
                FileWriter permite_escrito = new FileWriter(this.ruta+"d"+val+".txt");
                String valor = "valor="; 
                String cant = "cantAlmacenada=";
                
                PrintWriter guardar = new PrintWriter(permite_escrito);
                
                guardar.println(valor+val);
                guardar.println(cant+cantAlmacenada);
                
                permite_escrito.close();
                        
                System.out.println("Registro editado correctamente");
                
            }catch(Exception e) {
                System.out.println("Error: "+ e);
            }
        } else {
            System.out.println("El registro no existe");
        }
        
        
        
        
    }
}
