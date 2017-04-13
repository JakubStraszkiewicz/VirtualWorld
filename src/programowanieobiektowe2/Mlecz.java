/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;

import javax.swing.ImageIcon;

/**
 *
 * @author Jakub
 */
public class Mlecz extends Rosliny {
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\mlecz1.png");
    public Mlecz(Swiat swiat,int x,int y)
    {
        super(swiat,x,y);
        this.inicjatywa=0;
        this.sila=0;
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
        for(int i=0;i<3;i++)
           super.akcja();
    }
}
