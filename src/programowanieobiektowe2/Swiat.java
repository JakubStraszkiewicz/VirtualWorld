/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Jakub
 */
public class Swiat extends JPanel {
    ImageIcon ikona = new ImageIcon("src\\programowanieobiektowe2\\tlo1.png");
    protected static final int planszaPoziom=20;
    protected static final int planszaPion=20;
    protected Organizm[][] organizmy;
    public Okno okno;
    public Swiat()
    {
       
        this.organizmy=new Organizm[planszaPion][planszaPoziom];
        for(int i=0;i<planszaPion;i++)
            for(int j=0;j<planszaPoziom;j++)
                this.organizmy[i][j]=null;
    }
    public void dodaj_organizm(String nazwa,int x,int y)
    {
        switch(nazwa)
        {
            case "Antylopa":
                organizmy[y-1][x-1]= new Antylopa(this,x,y);
                break;
            case "Lis":
                organizmy[y-1][x-1]= new Lis(this,x,y);
                break;
            case "Owca":
                organizmy[y-1][x-1]= new Owca(this,x,y);
                break;
            case "Wilk":
                organizmy[y-1][x-1]= new Wilk(this,x,y);
                break;    
            case "Zolw":
                organizmy[y-1][x-1]= new Zolw(this,x,y);
                break;   
            case "Czlowiek":
                organizmy[y-1][x-1]= new Czlowiek(this,x,y);
                break;    
            case "WilczeJagody":
                organizmy[y-1][x-1]= new WilczeJagody(this,x,y);
                break;
            case "Guarana":
                organizmy[y-1][x-1]= new Guarana(this,x,y);
                break;
            case "Mlecz":
                organizmy[y-1][x-1]= new Mlecz(this,x,y);
                break;   
            case "Trawa":
                organizmy[y-1][x-1]= new Trawa(this,x,y);
                break;    
        }
    }
    public void poczatkowe_zwierzeta()
    {
        int x, y,licznikzwierzat=0;
        x = (int)(Math.random()*planszaPoziom) + 1;
        y = (int)(Math.random()*planszaPion) + 1;
        String[] zwierzeta = { "Antylopa","Lis","Owca","Wilk","Zolw","WilczeJagody","Guarana","Mlecz","Trawa" };
	while (licznikzwierzat != (planszaPion*planszaPoziom/100)*9)
	{
                x = (int)(Math.random()*planszaPoziom) + 1;
                y = (int)(Math.random()*planszaPion) + 1;
		if (organizmy[y - 1][x - 1] == null)
		{
			this.dodaj_organizm(zwierzeta[licznikzwierzat/(planszaPion*planszaPoziom/100)], x, y);
			organizmy[y-1][x-1].rysowanie(this);
			licznikzwierzat++;
		}
	}
	while (organizmy[y-1][x-1] != null)
	{
		x = (int)(Math.random()* planszaPoziom) + 1;
		y = (int)(Math.random()* planszaPion) + 1;
		dodaj_organizm("Czlowiek", x, y);
		organizmy[y-1][x-1].rysowanie(this);
		break;
	}
        if((planszaPion*planszaPoziom)/100==0)
        {
            dodaj_organizm("Czlowiek", x, y);
            organizmy[y-1][x-1].rysowanie(this);
        }
        this.czy_dodawac_organizmy();
    }
    public void usun_Organizm(Organizm organizm)
    {
        int x=organizm.get_polozenie_x(),y=organizm.get_polozenie_y();
        organizmy[y-1][x-1]=null;
        this.okno.przyciski[y-1][x-1].setIcon(ikona);
    }
    private void sortowanie(Organizm[] taborganizm, int ilosc)
    {
	Organizm pom;
	for (int i = 0;i < ilosc;i++)
	{
		for (int j = 1;j < ilosc;j++)
			if (taborganizm[j - 1].wiek < taborganizm[j].wiek)
			{
				pom = taborganizm[j - 1];
				taborganizm[j - 1] = taborganizm[j];
				taborganizm[j] = pom;
			}
	}
    }   
    public void nowa_tura()
    {
        
        Organizm[] taborganizm = new Organizm[planszaPoziom*planszaPion];
        
        try {
            PrintWriter zapis;
            zapis = new PrintWriter("log.txt");
            zapis.println("");
            zapis.close();
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Swiat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
	for (int i = 0;i < planszaPion*planszaPoziom;i++)
		taborganizm[i] = null;
	int k = 0, inicjatywa = 7;
	while (inicjatywa >= 0)
	{
		for (int i = 0;i < planszaPion;i++) // wektory
		{
			for (int j = 0;j < planszaPoziom;j++)
				if (organizmy[i][j] != null && organizmy[i][j].inicjatywa == inicjatywa)
				{
					taborganizm[k] = organizmy[i][j];
					k++;
				}
		}
		sortowanie(taborganizm,k);
		inicjatywa--;
		int pom = 0;
		while (pom <= k - 1)
		{
			if (taborganizm[pom] != null)
			{
				taborganizm[pom].wiek++;
				taborganizm[pom].rysowanie(this);
				taborganizm[pom].akcja();
			}
			pom++;
		}
		k = 0;
	}
        this.wypisanie_log();
        this.czy_dodawac_organizmy();
    }
    public void wypisanie_log()
    {
        try {
            File plik = new File("log.txt");
            Scanner skaner = new Scanner(plik);
            String zdanie = "";
            String linia="";
            while(skaner.hasNext()==true)
            {
                linia=skaner.nextLine();
                zdanie=zdanie+linia+'\n';
            }
            this.okno.tekst.setText(zdanie);
            skaner.close();
        } catch (FileNotFoundException ex) {
          Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    public void log_smierci(Organizm zabity,Organizm wygrany) 
    {
         try {
            String zab=zabity.getClass().getSimpleName(),wyg=wygrany.getClass().getSimpleName();
            PrintWriter zapis = new PrintWriter(new FileWriter("log.txt",true));
            if("WilczeJagody".equals(wyg))
                 zapis.println(zab + " zjada " + wyg + " i ginie");
            else
                 zapis.println(wyg + " zjada " + zab);
            zapis.close();
         } catch (IOException ex) {
            Logger.getLogger(WilczeJagody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void log_rozmnazanie(Organizm organizm)
    {
         try {
             String org=organizm.getClass().getSimpleName();
             PrintWriter zapis = new PrintWriter(new FileWriter("log.txt",true));
             zapis.println("Organizm "+ org + " rozmnozyl sie"); 
             zapis.close();
        } catch (IOException ex) {
            Logger.getLogger(WilczeJagody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void log_umiejetnosci(Organizm organizm)
    {
         try {
            String org=organizm.getClass().getSimpleName();
            PrintWriter zapis = new PrintWriter(new FileWriter("log.txt",true));
            if("Czlowiek".equals(org))
                zapis.println(org + " uzyl umiejetnosci calopalenie");
            if("Antylopa".equals(org))
                zapis.println(org + " ucieka przed walka");
            if("Zolw".equals(org))
                zapis.println(org + " odparl atak");
            zapis.close();
            } catch (IOException ex) {
            Logger.getLogger(WilczeJagody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ustaw_ruch_czlowieka(int ruch)
    {
        for(int i=0;i<planszaPion;i++)
            for(int j=0;j<planszaPoziom;j++)
               if(organizmy[i][j]!=null) {
                if ("Czlowiek".equals(organizmy[i][j].getClass().getSimpleName())) {
                    organizmy[i][j].kierunek=ruch;
                }
            }
    }
    
    public void zapis()
    {
        try {
            PrintWriter zapis = new PrintWriter("zapis.txt");
            for(int i=0;i<planszaPion;i++)
                for(int j=0;j<planszaPoziom;j++)
                    if(organizmy[i][j]!=null)
                    {
                        zapis.println(organizmy[i][j].toString());
                    }
            zapis.close();
            } catch (IOException ex) {
            Logger.getLogger(WilczeJagody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void wyczysc()
    {
        for(int i=0;i<planszaPion;i++)
            for(int j=0;j<planszaPoziom;j++)
                this.organizmy[i][j]=null;
        for(int i=0;i<planszaPion;i++)
            for(int j=0;j<planszaPoziom;j++)
                this.okno.przyciski[i][j].setIcon(ikona);
    }
    
    public void wczytaj()
    {
        String linia;
        wyczysc();
        try {
            File plik = new File("zapis.txt");
            Scanner skaner = new Scanner(plik);
            while(skaner.hasNext())
            {
                linia=skaner.nextLine();
                String[] wartosci = linia.split(" ", 0);
                this.dodaj_organizm(wartosci[0],Integer.parseInt(wartosci[2]), Integer.parseInt(wartosci[1]));
                this.organizmy[Integer.parseInt(wartosci[1])-1][Integer.parseInt(wartosci[2])-1].sila=Integer.parseInt(wartosci[3]);
                this.organizmy[Integer.parseInt(wartosci[1])-1][Integer.parseInt(wartosci[2])-1].wiek=Integer.parseInt(wartosci[4]);
                this.organizmy[Integer.parseInt(wartosci[1])-1][Integer.parseInt(wartosci[2])-1].rysowanie(this);
            }
            skaner.close();
        } catch (FileNotFoundException ex) {
          Logger.getLogger(Okno.class.getName()).log(Level.SEVERE, null, ex);
      }
        this.czy_dodawac_organizmy();
    }
    
    public void czy_dodawac_organizmy()
    {
       
        for(int i=0;i<planszaPoziom;i++)
            for(int j=0;j<planszaPion;j++)
                if(organizmy[j][i]==null)
                {
                     for(ActionListener akcja : okno.przyciski[j][i].getActionListeners()) {
                        okno.przyciski[j][i].removeActionListener(akcja);
}                   
            this.okno.przyciski[j][i].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                for(int i=0;i<planszaPoziom;i++)
                    for(int j=0;j<planszaPion;j++)
                        if(ae.getSource()==okno.przyciski[j][i])
                        {
                            okno.dialog.x=i;
                            okno.dialog.y=j;
                        }
                okno.dialog.setVisible(true);
                okno.dialog.odejmowanie_zwierzat();
                okno.requestFocus();
                
            }
                        
                    });
                }
        else    
                {
                     for(ActionListener akcja : okno.przyciski[j][i].getActionListeners()) {
    okno.przyciski[j][i].removeActionListener(akcja);
               this.okno.przyciski[j][i].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                okno.dialog.setVisible(false);
                okno.requestFocus();
            }
                        
                    }); 
                }
    }
   
    }
}
