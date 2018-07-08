/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Staniukynas_Karolis;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import studijosKTU.*;
/**
 *
 * @author merp
 */
public class Greitaveika {
    Knygos[] Knygynas;
	ListKTU<Knygos> aSeries = new ListKTU<>();
	Random ag = new Random();  // atsitiktinių generatorius
	int[] tiriamiKiekiai = {2_000};
     LinkedList<Knygos> al=new LinkedList<String>();    
        

    void generuotiAutomobilius(int kiekis) {
		String[][] am = { // galimų automobilių markių ir jų modelių masyvas
			{"Mobis", "Alyvuoges", "Ukis", "Burokai", "Sejimas"},
			{"Kestas", "Mechanika", "Fizika", "Matematika"},
			{"Bronius", "Etiudai", "Eilerasciai"}
			
		};
                String[] ld={"Lankos","Kava","KTU"};
                Knygynas = new Knygos[kiekis];
		ag.setSeed(2016);
                for(int i=0;i<kiekis;i++)
                {
                    int ma=ag.nextInt(am.length);
                    int mo=ag.nextInt(am[ma].length-1)+1;
                    int leid=ag.nextInt(ld.length);
                    Knygynas[i]=new Knygos(am[ma][0],am[ma][mo],ld[leid],1994+ag.nextInt(20),100+ag.nextInt(300),50+ag.nextInt(200));
                }
                Collections.shuffle(Arrays.asList(Knygynas));
                aSeries.clear();
                for (Knygos a : Knygynas) {
			aSeries.add(a);
		}
                
}
    void tyrimoPasirinkimas() 
    {
        long memTotal = Runtime.getRuntime().totalMemory();
        System.out.println("Total:"+memTotal);
        generuotiAutomobilius(20);
        for (Knygos a : aSeries) {
			Ks.oun(a);
		}
                Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
		Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
		Ks.oun("3 - Rusiavimas minmax");
		Ks.oun("4 - Rūšiavimas bubble");
                Ks.oun("5 - Remove");
                Ks.oun("6 - Index Of");
		
		Ks.ouf("%6d %7d %7d %7d %7d %7d %7d\n", 0, 1, 2, 3, 4,5,6);
    
    for (int n : tiriamiKiekiai) {
			paprastasTyrimas(n);
		}
		Ks.oun("Rikiavimo metodų greitaveikos tyrimas baigtas.");
    }
    
    void paprastasTyrimas(int elementųKiekis)
    {
        long t0 = System.nanoTime();
		generuotiAutomobilius(elementųKiekis);
                ListKTU<Knygos> aSeries2 = aSeries.clone();
		ListKTU<Knygos> aSeries3 = aSeries.clone();
		ListKTU<Knygos> aSeries4 = aSeries.clone();
                
        long t1 = System.nanoTime();
		System.gc();
		System.gc();
		System.gc();
	long t2 = System.nanoTime();
                 aSeries2.sortMinMax();
	long t3 = System.nanoTime();
                 aSeries3.sortBuble();
        long t4 = System.nanoTime();
                aSeries3.remove(aSeries3.get(1));
        long t5=System.nanoTime();
                aSeries3.indexOf(aSeries3.get(1));
        long t6=System.nanoTime();
		
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f\n", elementųKiekis,
				(t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
				(t4 - t3) / 1e9,(t5-t4)/1e9,(t6-t5)/1e9);
    }
    
    
    public static void main(String[] args) {
		// suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
		Locale.setDefault(new Locale("LT"));
		new Greitaveika().tyrimoPasirinkimas();
	}
}


