/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programowanieobiektowe2;


/**
 *
 * @author Jakub
 */
public class ProgramowanieObiektowe2{
    
    public static void main(String[] args) {
     
      Swiat swiat = new Swiat();
      Okno okno=new Okno(swiat);
      swiat.okno=okno;
      swiat.poczatkowe_zwierzeta();

     
    }
    
}
