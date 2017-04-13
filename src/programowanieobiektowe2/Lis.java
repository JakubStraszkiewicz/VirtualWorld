/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;

import javax.swing.ImageIcon;
import static programowanieobiektowe2.Swiat.*;

/**
 *
 * @author Jakub
 */
public class Lis extends Zwierzeta{
ImageIcon ikona_lis = new ImageIcon("src\\programowanieobiektowe2\\lis1.png");

    public Lis(Swiat swiat,int x,int y){
        super(swiat,x,y);
        this.inicjatywa=7;
        this.sila=3;
    }
    @Override
    public void rysowanie(Swiat swiat)
    {
       int x=this.get_polozenie_x(),y=this.get_polozenie_y();
       swiat.okno.przyciski[y-1][x-1].setIcon(ikona_lis);
    }
    private void losowe_miejsce(Organizm organizm)
    {
        int x=organizm.get_polozenie_x(),y=organizm.get_polozenie_y();
        for(int i=y-1;i<=y+1;i++)
            for(int j=x-1;j<=x+1;j++)
                 if(i>=1 && i<=planszaPion && j>=1 && j<=planszaPoziom && swiat.organizmy[i-1][j-1]==null)
                 {
                     swiat.organizmy[i-1][j-1]=swiat.organizmy[y-1][x-1];
                     swiat.organizmy[y-1][x-1]=null;
                     swiat.okno.przyciski[y-1][x-1].setIcon(ikona);
                     swiat.organizmy[i-1][j-1].rysowanie(swiat);
                     y=i;
                     x=j;
                 }
        this.set_polozenie_x(x);
        this.set_polozenie_y(y);
    }
    @Override
    public void akcja()
    {
       int ruch,x=this.get_polozenie_x(),y=this.get_polozenie_y();
       ruch=(int)(Math.random()*4)+1;
       switch(ruch)
       {
           case Gora:
               if(y-1>=1)
                   if(swiat.organizmy[y-2][x-1] == null)
                       ruch(1,Gora);
                   else
                       if(swiat.organizmy[y-2][x-1].getClass() == this.getClass())
                           swiat.organizmy[y-2][x-1].kolizja(this,swiat.organizmy[y-2][x-1]);
                       else
                           if(swiat.organizmy[y-2][x-1].sila<=this.sila)
                           {
                              swiat.organizmy[y-2][x-1].kolizja(this,swiat.organizmy[y-2][x-1]);
                              if(swiat.organizmy[y-2][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                  ruch(1,Gora);
                           }
                           else
                               this.losowe_miejsce(this);
               break;
           case Dol:
               if(y+1<=planszaPion)
                   if(swiat.organizmy[y][x-1]==null)
                       ruch(1,Dol);
                   else
                       if(swiat.organizmy[y][x-1].getClass() == this.getClass())
                           swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                       else
                           if(swiat.organizmy[y][x-1].sila<=this.sila)
                           {
                              swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                              if(swiat.organizmy[y][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                 ruch(1,Dol);
                           }
                           else
                               this.losowe_miejsce(this);
               break;
           case Lewo:
               if(x-1>=1)
                   if(swiat.organizmy[y-1][x-2]==null)
                       ruch(1,Lewo);
                   else
                       if(swiat.organizmy[y-1][x-2].getClass() == this.getClass())
                           swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                       else
                           if(swiat.organizmy[y-1][x-2].sila<=this.sila)
                           {
                                swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                                if(swiat.organizmy[y-1][x-2]==null && swiat.organizmy[y-1][x-1]!=null)
                                    ruch(1,Lewo);
                           }
                           else
                               this.losowe_miejsce(this);
               break;
           case Prawo:
               if(x+1<=planszaPoziom)
                   if(swiat.organizmy[y-1][x]==null)
                       ruch(1,Prawo);
                   else
                       if(swiat.organizmy[y-1][x].getClass() == this.getClass())
                           swiat.organizmy[y-1][x].kolizja(this,swiat.organizmy[y-1][x]);
                       else
                           if(swiat.organizmy[y-1][x].sila<=this.sila)
                           {
                                swiat.organizmy[y-1][x].kolizja(this,swiat.organizmy[y-1][x]);
                                if(swiat.organizmy[y-1][x]==null && swiat.organizmy[y-1][x-1]!=null)
                                    ruch(1,Prawo);
                           }
                           else
                               this.losowe_miejsce(this);
           break;          
       }
    }
}
