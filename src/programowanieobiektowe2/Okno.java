/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;
import java.awt.event.*;
import javax.swing.*;
import static programowanieobiektowe2.Swiat.*;
import static programowanieobiektowe2.Zwierzeta.*;

class Dialog extends JDialog{
    int x,y;
    Okno okno;
    String[] zwierzeta_wszystkie={"Antylopa","Lis","Owca","Wilk","Zolw","WilczeJagody","Guarana","Mlecz","Trawa"};
    JComboBox box = new JComboBox(zwierzeta_wszystkie);
    
    Dialog(Okno okno){
        this.okno=okno;
        setSize(300,100);
        JButton przycisk_dodania = new JButton("Dodaj");
        
        przycisk_dodania.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                okno.swiat.dodaj_organizm((String)box.getSelectedItem(), x+1, y+1);
                okno.swiat.organizmy[y][x].rysowanie(okno.swiat);
            }
            
        });
        
        JPanel panel2 = new JPanel();
        add(panel2);
        panel2.add(box);
        panel2.add(przycisk_dodania); 
    }
    public void odejmowanie_zwierzat()
    {
        int licznik=0,l=0;
        for(int k=0;k<9;k++)
        {
            for(int i=0;i<planszaPion;i++)
                for(int j=0;j<planszaPion;j++)
                    if(okno.swiat.organizmy[i][j]!=null&&okno.swiat.organizmy[i][j].getClass().getSimpleName().equals(okno.dialog.zwierzeta_wszystkie[k]))
                        licznik++;
            if(licznik==0)
                okno.dialog.box.removeItem(okno.dialog.zwierzeta_wszystkie[k]);
            licznik=0;
        }
    }
}

public class Okno extends JFrame implements KeyListener{
    JPanel panel = new JPanel();
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\tlo1.png");
    JButton[][] przyciski= new JButton[planszaPion][planszaPoziom];
    JTextArea tekst = new JTextArea(5,20);
    Swiat swiat;
    Dialog dialog = new Dialog(this);
    
    public Okno(Swiat swiat)
    {
        this.swiat=swiat;   
        panel.setLayout(null);
        if(planszaPoziom>=12)
            setSize(planszaPoziom*30+100,planszaPion*30+150);
        else
            setSize(460,planszaPion*30+150);
        setVisible(true);
        setLocation(200,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Gra w życie");
        add(panel);
        requestFocus();
        
        for(int i=0;i<planszaPoziom;i++)
            for(int j=0;j<planszaPion;j++)
            {
                przyciski[j][i]=new JButton();                
                przyciski[j][i].setBounds(i*30,j*30,30,30);
                panel.add(przyciski[j][i]);
                przyciski[j][i].setIcon(ikona);
            }
        
    JScrollPane sp=new JScrollPane(tekst);
    panel.add(sp);
    sp.setBounds(0, planszaPion*30, 360, 100);
    tekst.setEditable(false);
    
    JButton zapisz = new JButton("zapisz");
    panel.add(zapisz);
    zapisz.setBounds(360,planszaPion*30,80,20);
    zapisz.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                swiat.zapis();
                swiat.okno.requestFocus();
            }
    });
    
    JButton wczytaj = new JButton("wczytaj");
    panel.add(wczytaj);
    wczytaj.setBounds(360,planszaPion*30+20,80,20);
    wczytaj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
               swiat.wczytaj();
               swiat.okno.requestFocus();
            } 
    });
    
    JButton nastepna_tura = new JButton("tura");
    panel.add(nastepna_tura);
    nastepna_tura.setBounds(360,planszaPion*30+40,80,20);
    nastepna_tura.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                swiat.nowa_tura();
                swiat.okno.requestFocus();
            }
    });
    
    addKeyListener(this);
    setFocusable(true);  
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
      if(ke.getKeyCode() == KeyEvent.VK_UP)
      {
        swiat.ustaw_ruch_czlowieka(Gora);
        swiat.nowa_tura();
      }
      if(ke.getKeyCode() == KeyEvent.VK_DOWN)
      {
        swiat.ustaw_ruch_czlowieka(Dol);
         swiat.nowa_tura();
      }
      if(ke.getKeyCode() == KeyEvent.VK_LEFT)
      {
        swiat.ustaw_ruch_czlowieka(Lewo);
         swiat.nowa_tura();
      }
      if(ke.getKeyCode() == KeyEvent.VK_RIGHT)
      {
        swiat.ustaw_ruch_czlowieka(Prawo);
         swiat.nowa_tura();
      }
      if(ke.getKeyCode() == KeyEvent.VK_E)
      {
          swiat.ustaw_ruch_czlowieka(5);
          swiat.nowa_tura();
      }
      if(ke.getKeyCode() == KeyEvent.VK_Z)
          swiat.zapis();
      if(ke.getKeyCode() == KeyEvent.VK_W)
          swiat.wczytaj();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
   
}
