/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Denominacion implements Cloneable {
    private int valor;
    private int cantidadAlmacenada = 0;
    private int cantidadDevolver = 0;

    public Denominacion(int valor) {
        this.valor = valor;
    }
    
    public Denominacion(int valor, int cantidadAlmacenada) {
        this.valor = valor;
        this.cantidadAlmacenada = cantidadAlmacenada;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getCantidadAlmacenada() {
        return cantidadAlmacenada;
    }

    public void setCantidadAlmacenada(int cantidadAlmacenada) {
        this.cantidadAlmacenada = cantidadAlmacenada;
    }

    public int getCantidadDevolver() {
        return cantidadDevolver;
    }

    public void setCantidadDevolver(int cantidadDevolver) {
        this.cantidadDevolver = cantidadDevolver;
    }
    
    public void agregar(int cant){
        this.cantidadAlmacenada = this.cantidadAlmacenada + cant;
    }
    
    public void retirar(int cant) {
        this.cantidadAlmacenada = this.cantidadAlmacenada - cant;
    }
    
    public void aumentarCantidadDevolver() {
        this.cantidadDevolver += 1;
    }
    
    public void disminuirCantidadAlmacenada() {
        this.cantidadAlmacenada -= 1;
    }
    
    public Denominacion clone() throws CloneNotSupportedException{
         Denominacion clonmalefico = (Denominacion) super.clone();
         return clonmalefico;
    }
    
    
}
