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
public class Antylopa extends Zwierzeta{
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\antylopa1.png");
    public Antylopa(Swiat swiat,int x,int y){
        super(swiat,x,y);
        this.inicjatywa=4;
        this.sila=4;
    }
     @Override
    public void rysowanie(Swiat swiat)
    {
       int x=this.get_polozenie_x(),y=this.get_polozenie_y();
       swiat.okno.przyciski[y-1][x-1].setIcon(ikona);
    }
    @Override
    public void akcja()
    {
        int ruch=0,x=this.get_polozenie_x(),y=this.get_polozenie_y();
        ruch=(int)(Math.random()*4)+1;
        switch(ruch)
        {
            case Gora:
                if(y-2>=1)
                    if(swiat.organizmy[y-3][x-1]==null)
                        this.ruch(2, Gora);
                    else
                        if(swiat.organizmy[y-3][x-1].getClass()==this.getClass())
                            swiat.organizmy[y-3][x-1].kolizja(this,swiat.organizmy[y-3][x-1]);
                        else
                        {
                            swiat.organizmy[y-3][x-1].kolizja(this,swiat.organizmy[y-3][x-1]);
                            if(swiat.organizmy[y-3][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(2,Gora);
                        }
                break;
            case Dol:
                if(y+2<=planszaPion)
                    if(swiat.organizmy[y+1][x-1]==null)
                        this.ruch(2,Dol);
                    else
                        if(swiat.organizmy[y+1][x-1].getClass()==this.getClass())
                            swiat.organizmy[y+1][x-1].kolizja(this,swiat.organizmy[y+1][x-1]);
                        else
                        {
                            swiat.organizmy[y+1][x-1].kolizja(this,swiat.organizmy[y+1][x-1]);
                            if(swiat.organizmy[y+1][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(2,Dol);
                        }
                break;
            case Lewo:
                if(x-2>=1)
                    if(swiat.organizmy[y-1][x-3]==null)
                        this.ruch(2,Lewo);
                    else
                        if(swiat.organizmy[y-1][x-3].getClass()==this.getClass())
                            swiat.organizmy[y-1][x-3].kolizja(this,swiat.organizmy[y-1][x-3]);
                        else
                        {
                            swiat.organizmy[y-1][x-3].kolizja(this,swiat.organizmy[y-1][x-3]);
                            if(swiat.organizmy[y-1][x-3]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(2,Lewo);
                        }
                break;
            case Prawo:
                if(x+2<=planszaPoziom)
                    if(swiat.organizmy[y-1][x+1]==null)
                        this.ruch(2,Prawo);
                    else
                        if(swiat.organizmy[y-1][x+1].getClass()==this.getClass())
                            swiat.organizmy[y-1][x+1].kolizja(this,swiat.organizmy[y-1][x+1]);
                        else
                        {
                            swiat.organizmy[y-1][x+1].kolizja(this,swiat.organizmy[y-1][x+1]);
                            if(swiat.organizmy[y-1][x+1]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(2,Prawo);
                        }
                break;
        }
    }
    
    public void kolizja(Organizm atakujacy,Organizm atakowany)
    {
        int x=atakujacy.get_polozenie_x(),y=atakujacy.get_polozenie_y(),czy_uciekam=0;
        czy_uciekam=(int)(Math.random()*2)+1;
        if(atakujacy.getClass() == atakowany.getClass())
        {
            for(int i=y-1;i<=y+1;i++)
                for(int j=x-1;j<=x+1;j++)
                    if(i>=1 && i<=planszaPion && j>=1 && j<=planszaPoziom && swiat.organizmy[i-1][j-1]==null)
                    {
                        swiat.dodaj_organizm(atakujacy.getClass().getSimpleName(), j, i);
                        swiat.organizmy[i-1][j-1].rysowanie(swiat);
                        swiat.log_rozmnazanie(atakujacy);
                        return;
                    }
            
        }
        else
        {
            if(czy_uciekam%2==0)
            {
              if(atakujacy.sila>atakowany.sila)
              {
                 swiat.usun_Organizm(atakowany);
                 swiat.log_smierci(atakowany, atakujacy);
                  
              }
            else
               if(atakujacy.sila<atakowany.sila)
               {
                     swiat.usun_Organizm(atakujacy);
                     swiat.log_smierci(atakujacy, atakowany);

               }
               else
                   if(atakujacy.sila==atakowany.sila)
                   {
                         swiat.usun_Organizm(atakowany);
                         swiat.log_smierci(atakowany, atakujacy);

                   }
            }
            else
                swiat.log_umiejetnosci(atakowany);
        }
    }
}
