/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub
 */
public abstract class Organizm {
    protected int wiek;
    protected int sila;
    protected int inicjatywa;
    protected int kierunek;
    private class Polozenie{
        private int x;
        private int y;
    }
    Polozenie polozenie = new Polozenie(); 
    Swiat swiat;
    
    public int get_polozenie_x(){
        return polozenie.x;
    }
    public int get_polozenie_y(){
        return polozenie.y;   
    }
    public void set_polozenie_x(int x){
        polozenie.x=x;
    }
    public void set_polozenie_y(int y){
        polozenie.y=y;
    }
    public String toString()
    {
        String string=this.getClass().getSimpleName()+" "+this.get_polozenie_y()+" "+this.get_polozenie_x()+" "+sila+" "+wiek;
        return string;
    }
    public void rysowanie(Swiat swiat){}
    public void kolizja(Organizm atakujacy, Organizm atakowany) {}
    public void akcja() {}
    public Organizm(Swiat swiat,int x,int y){
        this.swiat=swiat;
        swiat.organizmy[y-1][x-1]=this;
        this.polozenie.x=x;
        this.polozenie.y=y;
        this.wiek=0;
        this.kierunek=0;
    }
    

}
