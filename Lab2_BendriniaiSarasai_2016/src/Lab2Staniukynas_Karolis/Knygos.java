/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Staniukynas_Karolis;

/**
 *
 * @author merp
 */
import java.time.LocalDate;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import studijosKTU.*;
public class Knygos implements KTUable<Knygos>
{
    
        final static private int priimtinųMetųRiba = 1994;
	final static private int esamiMetai = LocalDate.now().getYear();
	final static private double valKursas = esamiMetai <= 2014 ? 1 : 1 / 3.4528;
	final static private double minKaina = 20.0;
	final static private double maxKaina = 120.0;
        
    private String pavadinimas;
    private String autorius;
    private String leidykla;
    private int metai;
    private int psl;
    private double kaina;
    public Knygos(){
    
}
public Knygos(String pavadinimas,String autorius,String leidykla,int metai,int psl,double kaina)
{
    this.autorius=autorius;
    this.pavadinimas=pavadinimas;
    this.leidykla=leidykla;
    this.metai=metai;
    this.psl=psl;
    this.kaina=kaina;
}
public Knygos(String dataString) {
		this.parse(dataString);
	}
@Override
	public Knygos create(String dataString) {
		return new Knygos(dataString);

	}
        public final void parse(String dataString) {
		try {   
			Scanner ed = new Scanner(dataString); // numatytieji skirtukai: tarpas, tab, eilutės pabaiga
			// Skiriklius galima pakeisti Scanner klasės metodu useDelimitersr 
			//	Pavyzdžiui, ed.useDelimiter(", *"); reikštų, kad skiriklis bus kablelis ir vienas ar daugiau tarpų.
			autorius = ed.next();
			pavadinimas = ed.next();
			leidykla = ed.next();
			metai = ed.nextInt();
                        psl=ed.nextInt();
			setKaina(ed.nextDouble());
		} catch (InputMismatchException e) {
			Ks.ern("Blogas duomenų formatas apie auto -> " + dataString);
		} catch (NoSuchElementException e) {
			Ks.ern("Trūksta duomenų apie auto -> " + dataString);
		}

	}
        @Override
	public String validate() {
		String klaidosTipas = "";
		if (metai < priimtinųMetųRiba || metai > esamiMetai) {
			klaidosTipas = "Netinkami gamybos metai, turi būti ["
					+ priimtinųMetųRiba + ":" + esamiMetai + "]";
		}
		if (kaina < minKaina || kaina > maxKaina) {
			klaidosTipas += " Kaina už leistinų ribų [" + minKaina
					+ ":" + maxKaina + "]";
		}
		return klaidosTipas;
	}
        @Override
	public String toString() {  // surenkama visa reikalinga informacija
		return String.format("%-8s %-8s %-4s %7d %7d %8.1f %s",
				pavadinimas,autorius,leidykla,metai,psl,kaina, validate());
	}

	;
        public String getPav()
        { return pavadinimas;}
        public String getAut()
        { return autorius;}
        public String getLeid()
        { return leidykla;}
        public double getMetai()
        {return metai;}
        public int getPsl()
        {return psl;}
        public double getKaina()
        {return kaina;}
       public void setKaina(double kaina) {
		this.kaina = kaina;
	} 
   public int compareTo(Knygos a) {
		// lyginame pagal svarbiausią požymį - kainą
		String leidKita = a.getAut();
                double kainaKita=a.getKaina();
                double kitaMetai=a.getMetai();
                int autoriai=leidKita.compareTo(autorius);
                
		if(autoriai==0){
   
                if (kainaKita>kaina)
                    return 1;
                if(kainaKita<kaina)
                    return -1;
                else
                {
                    if(kitaMetai>metai)
                        return 1;
                    if(kitaMetai<metai)
                        return -1; 
                    else 
                    return 0;
                }
                
                }
                else
                    return autoriai;
                
	}
	
	// Rikiavimo pagal modeli komparatorius
	public final static Comparator<Knygos> pagalMarkę = new Comparator<Knygos>() {
		@Override
		public int compare(Knygos a1, Knygos a2) {
			int cmp = a1.getPav().compareTo(a2.getPav());
			return cmp;
		}
	};

	/**
	 * Rikiavimo pagal kainą komparatorius
	 *	(geriau panaudoti Double klasės metodą compare)
	 */
	public final static Comparator<Knygos> pagalKainą = new Comparator<Knygos>() {
		@Override
		public int compare(Knygos o1, Knygos o2) {
			double k1 = o1.getKaina();
			double k2 = o2.getKaina();
			// didėjanti tvarka, pradedant nuo mažiausios
			if (k1 < k2) {
				return -1;
			}
			if (k1 > k2) {
				return 1;
			}
			return 0;
		}
	};
        public static void main(String... args)
        {
            Locale.setDefault(new Locale("LT"));
            Knygos a1=new Knygos();
            Knygos a2=new Knygos();
            Knygos a3=new Knygos();
            Knygos a4=new Knygos();
           a1.parse("vakaras Kestas Pegasas 1996 200 60,0");
           a2.parse("vakaras Kestas Pegasas 1996 200 60,0");
           a3.parse("vakaras Kestas Pegasas 1996 200 60,0");
           a4.parse("vakaras Kestas Pegasas 1996 200 60,0");
           
        }
}
