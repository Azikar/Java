/** @author Eimutis Karčiauskas, KTU IF Programų inžinerijos katedra, 2013 09 09
   *
   *  Tai yra demonstracinė piramidės formavimo ekrane ScreenKTU klasė, 
   *    kurioje demonstruojama metodika, kai klasė yra išvestinė iš ScreenKTU.
   *  Atkreipkite dėmesį į daugelio gijų sukūrimą ir panaudojimą.
   *  IŠBANDYKITE nuoseklų formavimą ir lygiagretų formavimą su gijomis.
   *  EKSPERIMENTUOKITE su spalvomis ir ekrano formavimo algoritmais.
   *  SURAŠYKITE reikiamus veiksmus neužbaigtiems metodams.
   ****************************************************************************/
package parodomieji;

import java.awt.Color;
import studijosKTU.ScreenKTU;

public class B_Piramide extends ScreenKTU {
    int aukštis=3;
    
    
    B_Piramide(int aukštis){
        super(12, 12, aukštis+2, 2*aukštis+1);
        this.aukštis = aukštis;
    }
    // konstruojant spalvų masyvas colors yra imamas iš klasės ScreentKTU
    void konstruotiPiramidę(int miegoTrukmėVienaiPakopai){
        clearAll(Color.black);
        for (int i = 0; i < aukštis; i++) {
            setColors(colors[i%10], colors[(i+3)%10]);
            fillRect(i+1, aukštis-i, 1, 2*i+1, '+');
            refresh(miegoTrukmėVienaiPakopai);
        }
    }
    // šis metodas naudojamas atskiros gijos metu
    public void run(){
        konstruotiPiramidę(1000); // kadangi gijos vykdomos lygiagrečiai, galima daugiau palaukti
    }
    public static void main(String[] args) {
        for(int h=6; h<18; h++){
            B_Piramide pir = new B_Piramide(h);
//            pir.konstruotiPiramidę(100);      // nuoseklus vykdymas
            new Thread(pir).start();   // lygiagretus vykdymas
        }
    }

}