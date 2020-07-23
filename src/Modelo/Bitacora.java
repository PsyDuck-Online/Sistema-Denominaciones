/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Bitacora {
  
    private String ruta;
    private File file;
    private FileWriter fw;
    private BufferedWriter bw;
    
    public Bitacora(){
        
        this.ruta = System.getProperty("user.dir") + File.separator + "src" + File.separator + "BD_Java" + File.separator + "bitacora.txt";
    }
    
    public String leerBitacora() {
        FileReader fr = null;
        BufferedReader br = null;
        
        String linea;
        String texto = "";
        
        try {
            this.file = new File(this.ruta);       
            fr = new FileReader(file);
            br = new BufferedReader(fr);   
            while((linea=br.readLine()) != null) {
                texto += linea + "\n";
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fr != null){
                    fr.close();
                }
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        return texto;
    }
    
    public void escribirBitacora(String data) {    
        this.file = new File(this.ruta);
        if(!file.exists()) {
            try {
                this.file.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Bitacora.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            this.fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(data);
        } catch (IOException ex) {
            Logger.getLogger(Bitacora.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                this.bw.close();
                this.fw.close();
            } catch (IOException ex) {
            Logger.getLogger(Bitacora.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
            
        System.out.println("Se escribio");
    }
}
