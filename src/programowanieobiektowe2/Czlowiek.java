/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;
import javax.swing.*;
import static programowanieobiektowe2.Swiat.*;


public class Czlowiek extends Zwierzeta {
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\czlowiek1.png");
    protected int umiejetnosc;
    protected int czas_trwania;
    public Czlowiek(Swiat swiat,int x,int y){
        super(swiat,x,y);
        this.inicjatywa=4;
        this.sila=5;
        this.umiejetnosc=0;
        this.czas_trwania=0;
    }
    
    @Override
    public void rysowanie(Swiat swiat)
    {
       int x=this.get_polozenie_x(),y=this.get_polozenie_y();
       swiat.okno.przyciski[y-1][x-1].setIcon(ikona);
    }
    public void calopalenie()
    {
        int x=this.get_polozenie_x(),y=this.get_polozenie_y();
        if(umiejetnosc>0)
        {
            for(int i=y-1;i<=y+1;i++)
                for(int j=x-1;j<=x+1;j++)
                    if(i<=planszaPion && i>=1 && j<=planszaPoziom && j>=1 && swiat.organizmy[i-1][j-1]!=null && swiat.organizmy[i-1][j-1]!=swiat.organizmy[y-1][x-1])
                        swiat.usun_Organizm(swiat.organizmy[i-1][j-1]);
            umiejetnosc--;
            swiat.log_umiejetnosci(swiat.organizmy[y-1][x-1]);
        }
        czas_trwania--;
    }
    @Override
    public void akcja()
    {
        int x=this.get_polozenie_x(),y=get_polozenie_y();
        calopalenie();
        switch(kierunek)
        {
            case Gora:
                if(y-1>=1)
                    if(swiat.organizmy[y-2][x-1]==null)
                        this.ruch(1, Gora);
                    else
                        if(swiat.organizmy[y-2][x-1].getClass()==this.getClass())
                            swiat.organizmy[y-2][x-1].kolizja(this,swiat.organizmy[y-2][x-1]);
                        else
                        {
                            swiat.organizmy[y-2][x-1].kolizja(this,swiat.organizmy[y-2][x-1]);
                            if(swiat.organizmy[y-2][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(1, Gora);
                        }
                break;
            case Dol:
                if(y+1<=planszaPion)
                    if(swiat.organizmy[y][x-1]==null)
                        this.ruch(1,Dol);
                    else
                        if(swiat.organizmy[y][x-1].getClass()==this.getClass())
                            swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                        else
                        {
                            swiat.organizmy[y][x-1].kolizja(this,swiat.organizmy[y][x-1]);
                            if(swiat.organizmy[y][x-1]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(1, Dol);
                        }
                break;
            case Lewo:
                if(x-1>=1)
                    if(swiat.organizmy[y-1][x-2]==null)
                        this.ruch(1, Lewo);
                    else
                        if(swiat.organizmy[y-1][x-2].getClass()==this.getClass())
                            swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                        else
                        {
                            swiat.organizmy[y-1][x-2].kolizja(this,swiat.organizmy[y-1][x-2]);
                            if(swiat.organizmy[y-1][x-2]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(1, Lewo);
                        }    
                break;
            case Prawo:
                if(x+1<=planszaPoziom)
                    if(swiat.organizmy[y-1][x]==null)
                        this.ruch(1,Prawo);
                    else
                        if(swiat.organizmy[y-1][x].getClass()==this.getClass())
                            swiat.organizmy[y-1][x].kolizja(this, swiat.organizmy[y-1][x]);
                        else
                        {
                            swiat.organizmy[y-1][x].kolizja(this,swiat.organizmy[y-1][x]);
                            if(swiat.organizmy[y-1][x]==null && swiat.organizmy[y-1][x-1]!=null)
                                this.ruch(1,Prawo);
                        }
                break;
            case 5:
                if(czas_trwania<=0)
                {
                    umiejetnosc=5;
                    czas_trwania=10;
                    calopalenie();
                }
        }
    }

   
    
}
