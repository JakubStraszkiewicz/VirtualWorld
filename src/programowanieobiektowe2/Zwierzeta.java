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
public class Zwierzeta extends Organizm {
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\tlo1.png");
    protected static final int Gora=1;
    protected static final int Dol=2;
    protected static final int Prawo=3;
    protected static final int Lewo=4;
    
    public void rysowanie(Swiat swiat){}
    public void ruch(int ile,int kierunek)
    {
        int x=get_polozenie_x(),y=get_polozenie_y();
        switch(kierunek)
        {
            case Gora:
                y-=ile;
                this.set_polozenie_y(y);
                swiat.organizmy[y-1][x-1]=swiat.organizmy[y-1+ile][x-1];
                swiat.organizmy[y-1+ile][x-1]=null;
                swiat.okno.przyciski[y-1+ile][x-1].setIcon(ikona);
                this.rysowanie(swiat);
                break;
            case Dol:
                y+=ile;
                this.set_polozenie_y(y);
                swiat.organizmy[y-1][x-1]=swiat.organizmy[y-1-ile][x-1];
                swiat.organizmy[y-1-ile][x-1]=null;
                swiat.okno.przyciski[y-1-ile][x-1].setIcon(ikona);
                this.rysowanie(swiat);
                break;
            case Lewo:
                x-=ile;
                this.set_polozenie_x(x);
                swiat.organizmy[y-1][x-1]=swiat.organizmy[y-1][x-1+ile];
                swiat.organizmy[y-1][x-1+ile]=null;
                swiat.okno.przyciski[y-1][x-1+ile].setIcon(ikona);
                this.rysowanie(swiat);
                break;
            case Prawo:
                x+=ile;
                this.set_polozenie_x(x);
                swiat.organizmy[y-1][x-1]=swiat.organizmy[y-1][x-1-ile];
                swiat.organizmy[y-1][x-1-ile]=null;
                swiat.okno.przyciski[y-1][x-1-ile].setIcon(ikona);
                this.rysowanie(swiat);
                break;
        }
        this.set_polozenie_x(x);
        this.set_polozenie_y(y);
    }
    @Override
    public void akcja() {
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
                       {
                           swiat.organizmy[y-2][x-1].kolizja(this,swiat.organizmy[y-2][x-1]);
                           if(swiat.organizmy[y-2][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                               ruch(1,Gora);
                       }
               break;
           case Dol:
               if(y+1<=planszaPion)
                   if(swiat.organizmy[y][x-1]==null)
                       ruch(1,Dol);
                   else
                       if(swiat.organizmy[y][x-1].getClass() == this.getClass())
                           swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                       else
                       {
                           swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                           if(swiat.organizmy[y][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                ruch(1,Dol);
                       }
               break;
           case Lewo:
               if(x-1>=1)
                   if(swiat.organizmy[y-1][x-2]==null)
                       ruch(1,Lewo);
                   else
                       if(swiat.organizmy[y-1][x-2].getClass() == this.getClass())
                           swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                       else
                       {
                           swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                           if(swiat.organizmy[y-1][x-2]==null && swiat.organizmy[y-1][x-1]!=null)
                               ruch(1,Lewo);
                       }
               break;
           case Prawo:
               if(x+1<=planszaPoziom)
                   if(swiat.organizmy[y-1][x]==null)
                       ruch(1,Prawo);
                   else
                       if(swiat.organizmy[y-1][x].getClass() == this.getClass())
                           swiat.organizmy[y-1][x].kolizja(this,swiat.organizmy[y-1][x]);
                       else
                       {
                           swiat.organizmy[y-1][x].kolizja(this,swiat.organizmy[y-1][x]);
                           if(swiat.organizmy[y-1][x]==null && swiat.organizmy[y-1][x-1]!=null)
                               ruch(1,Prawo);
                       }
           break;          
       }
    }
    @Override
    public void kolizja(Organizm atakujacy,Organizm atakowany) {
        int x=atakujacy.get_polozenie_x(),y=atakujacy.get_polozenie_y();
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
    }    
    public Zwierzeta(Swiat swiat,int x,int y)
    {
        super(swiat,x,y);
    }
    
}
