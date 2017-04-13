/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;
import static programowanieobiektowe2.Swiat.*;

/**
 *
 * @author Jakub
 */
public class Rosliny extends Organizm{
   public final static int prawdopodobienstwo=5;
   public Rosliny(Swiat swiat,int x,int y)
    {
        super(swiat,x,y);
    }
   
   @Override
   public void akcja()
   {
       int x=this.get_polozenie_x(),y=this.get_polozenie_y(),czy_rozmnozy_sie=0;
       czy_rozmnozy_sie=(int)(Math.random()*prawdopodobienstwo)+1;
       if(czy_rozmnozy_sie%prawdopodobienstwo==0)
       {
           for(int i=y-1;i<=y+1;i++)
               for(int j=x-1;j<=x+1;j++)
                   if(i>=1 && i<=planszaPion && j>=1 && j<=planszaPoziom && swiat.organizmy[i-1][j-1]==null)
                   {
                       swiat.dodaj_organizm(this.getClass().getSimpleName(), j, i);
                       swiat.organizmy[i-1][j-1].rysowanie(swiat);
                       swiat.log_rozmnazanie(this);
                       return;
                   }
       }
   }
   
   public void kolizja(Organizm atakujacy, Organizm atakowany)
   {
       swiat.usun_Organizm(atakowany);
       swiat.log_smierci(atakowany,atakujacy);
   }
}

