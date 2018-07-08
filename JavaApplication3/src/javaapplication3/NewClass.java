/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import java.util.Objects;
import javaapplication3.meh.*;
import java.util.Comparator;
import java.util.Locale;
/**
 *duomeny klase
 * @author merp
 */
public class NewClass {
    private String PavVard;
    private String Lytis;
    private int Amzius;
    private String Issilavinimas;
    private int zaid_sk;
    private int atsakyti_kl;

   
/**
 * 
 * @return 
 */
    public String getPavVard() {
        return PavVard;
    }
/**
 * 
 * @return 
 */
    public String getLytis() {
        return Lytis;
    }
/**
 * 
 * @return 
 */
    public int getAmzius() {
        return Amzius;
    }
/**
 * 
 * @return 
 */
    public String getIssilavinimas() {
        return Issilavinimas;
    }
/**
 * 
 * @return 
 */
    public int getZaid_sk() {
        return zaid_sk;
    }
/**
 * 
 * @return 
 */
    public int getAtsakyti_kl() {
        return atsakyti_kl;
    }
    
            
/**
 * 
 * @param PavVard
 * @param Lytis
 * @param Amzius
 * @param Issilavinimas
 * @param zaid_sk
 * @param atsakyti_kl 
 */
public NewClass(String PavVard,String Lytis,int Amzius,String Issilavinimas,
int zaid_sk,int atsakyti_kl ){
this.PavVard=PavVard;
this.Lytis=Lytis;
this.Amzius=Amzius;
this.Issilavinimas=Issilavinimas;
this.zaid_sk=zaid_sk;
this.atsakyti_kl=atsakyti_kl;
}
public String imtLav(){return Issilavinimas;}
/**
 * 
 * @return 
 */
public String toString()
{
   
    return String.format("%10s,%4s,%3d,%12s,%4d,%4d", PavVard , Lytis , Amzius ,Issilavinimas,
            zaid_sk , atsakyti_kl );
          
}
/**
 * lygina duomenis
 * @param obj
 * @return 
 */
public boolean equals(Object obj)
{
    if (obj == null) {
            return false;}
   if (getClass() != obj.getClass()) {
            return false;
        }
   final NewClass other=(NewClass) obj;
   if(!Objects.equals(this.PavVard, other.PavVard))
       return false;
   if(!Objects.equals(this.Lytis, other.Lytis))
       return false;
   if(!Objects.equals(this.Issilavinimas, other.Issilavinimas))
       return false;
   if(this.Amzius!= other.Amzius)
       return false;
   if(this.zaid_sk!= zaid_sk)
       return false;
   if (Double.doubleToLongBits(this.atsakyti_kl) != Double.doubleToLongBits(other.atsakyti_kl)) 
            return false;
            
            return true;
            
}
 public int compareTo(NewClass a) {
		// lyginame pagal svarbiausią požymį - kainą
                String PavVar=a.PavVard;
                String Lyti=a.Lytis;
                 int Amziu=a.Amzius;
                 String Issilavinima=a.Issilavinimas;
                 int zaid_s=a.zaid_sk;
                 int atsakyti_k=a.atsakyti_kl;
                 
      int paw=PavVar.compareTo(PavVard);
      
      
          return paw;
                
	
 }
}

