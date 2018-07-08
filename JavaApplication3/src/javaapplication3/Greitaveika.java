/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Random;
import javaapplication3.meh.*;

/**
 *
 * @author merp
 */
public class Greitaveika {
    NewClass[] zaid;
     LinkedL<NewClass> clase = new LinkedL<>();
     Random ag = new Random();  // atsitiktinių generatorius
	static int[] tiriamiKiekiai = {100, 200, 300, 400};
        LinkedList<NewClass> al=new LinkedList<NewClass>(); 
        

public void generuoti(int kiekis)
{
String[][] am = { //vartai
			{"M", "Greta", "Rita", "Ugne"},
			{"V", "Tomas", "Darius", "Arnas"}};
 zaid = new  NewClass[kiekis];
 ag.setSeed(2016);
 String[] lav={"aukst","vid","kolegijos"};
  for(int i=0;i<kiekis;i++)
                {
                    int lytis=ag.nextInt(am.length);
                    int vardas=ag.nextInt(am[lytis].length-1)+1;
                  int la=ag.nextInt(lav.length);
                   zaid[i]=new NewClass(am[lytis][vardas],am[lytis][0],10+ag.nextInt(10),lav[la],0+ag.nextInt(10),0+ag.nextInt(10));
                }
  Collections.shuffle(Arrays.asList(zaid));
                
	clase.reset();
        for (NewClass a : zaid) {
			clase.add(a);
		}
       
       
    //    clase.print();
}
 void tyrimoPasirinkimas() 
 {
      long memTotal = Runtime.getRuntime().totalMemory();
        System.out.println("Total:"+memTotal);
        generuoti(20);
 }
 public void generuotil(int kiekis)
 {
     String[][] am = { //vartai
			{"M", "Greta", "Rita", "Ugne"},
			{"V", "Tomas", "Darius", "Arnas"}};
 zaid = new  NewClass[kiekis];
 ag.setSeed(2016);
 String[] lav={"aukst","vid","kolegijos"};
  for(int i=0;i<kiekis;i++)
                {
                    int lytis=ag.nextInt(am.length);
                    int vardas=ag.nextInt(am[lytis].length-1)+1;
                  int la=ag.nextInt(lav.length);
                   zaid[i]=new NewClass(am[lytis][vardas],am[lytis][0],10+ag.nextInt(10),lav[la],0+ag.nextInt(10),0+ag.nextInt(10));
                }
  Collections.shuffle(Arrays.asList(zaid));
                
	al.clear();
        for (NewClass a : zaid) {
			al.add(a);
                        
		}
       
       
 }
  void paprastasTyrimasl(int elementųKiekis)
  {
    
       long t0 = System.nanoTime();
		generuotil(elementųKiekis);
                 LinkedList<NewClass> pirm=al;
		 LinkedList<NewClass> antr =al;
		 LinkedList<NewClass> tret = al;
               
        long t1 = System.nanoTime();
		System.gc();
		System.gc();
		System.gc();
	long t2 = System.nanoTime();
                 
	long t3 = System.nanoTime();
       int b;
       b=al.size();
                 pirm.remove(2);
        long t4 = System.nanoTime();
                antr.remove(zaid[2]);
        long t5=System.nanoTime();
                tret.contains(zaid[6]);
        long t6=System.nanoTime();
		
       System.out.println( elementųKiekis+" "+(t1 - t0) / 1e9+" "+ (t2 - t1) / 1e9+"              "+
				(t4 - t3) / 1e9+" "+(t5-t4)/1e9+" "+(t6-t5)/1e9);}
  
  void paprastasTyrimas(int elementųKiekis)
    {
        long t0 = System.nanoTime();
		generuoti(elementųKiekis);
                 LinkedL<NewClass> pirm=clase;
		 LinkedL<NewClass> antr = clase;
		 LinkedL<NewClass> tret = clase;
               
        long t1 = System.nanoTime();
		System.gc();
		System.gc();
		System.gc();
	long t2 = System.nanoTime();
                 pirm.sortJava();
	long t3 = System.nanoTime();
                 antr.delete(3);
        long t4 = System.nanoTime();
                antr.deleteKint(zaid[2]);
        long t5=System.nanoTime();
                tret.contains(zaid[6]);
        long t6=System.nanoTime();
		
       System.out.println( elementųKiekis+" "+(t1 - t0) / 1e9+" "+ (t2 - t1) / 1e9+" "+ (t3 - t2) / 1e9+" "+
				(t4 - t3) / 1e9+" "+(t5-t4)/1e9+" "+(t6-t5)/1e9);}
 
 
 
   public static void main(String[] args) {
		// suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
		Locale.setDefault(new Locale("LT"));
		new Greitaveika().tyrimoPasirinkimas();
                System.out.println("1 - Pasiruošimas tyrimui - duomenų generavimas");
		System.out.println("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
		System.out.println("3 - rikiavimas");
		System.out.println("4 - Salina indeksa");
                System.out.println("5 - Salina reiksme");
                System.out.println("6 - ar egzistuoja");
		
		System.out.println("           1          2            3        4          5     6");
                 for (int n : tiriamiKiekiai) {
			new Greitaveika().paprastasTyrimas(n);
		}
                for (int n : tiriamiKiekiai) {
			new Greitaveika().paprastasTyrimasl(n);
		} 
	}
}