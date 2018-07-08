/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2Staniukynas_Karolis;
import studijosKTU.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author merp
 */
public class vykdymas {
    
/**
 * Skaitymas
 * @param file
 * @return
 * @throws FileNotFoundException
 * @throws IOException 
 */
 ListKTUx<Knygos> Skait(String file) throws FileNotFoundException, IOException
    {
       
       
     FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        String eil;
      ListKTUx<Knygos> meh=new ListKTUx<>(new Knygos());
       // List<Zaidejas> info=new ArrayList<Zaidejas>();
        while((eil=br.readLine())!=null){
               String[] part=eil.split(" ");
               String pavadinimas=part[0];
               String autorius=part[1];
               String leidykla=part[2];
               int metai=Integer.parseInt(part[3]);
               int psl=Integer.parseInt(part[4]);
               double kaina=Double.parseDouble(part[5]);
              
               Knygos zaid=new Knygos(pavadinimas,autorius,leidykla,metai,psl,kaina);
               meh.add(zaid);
              
               
}
        //textArea1.append(" \n");
        return meh;
    }
 /**
  * Salinimas
  * @param kaina
  * @param duom 
  */
public void Remove(Double kaina,ListKTUx <Knygos> duom)
 {
    // vykdymas vyk=new vykdymas();
   
   
    Knygos b=duom.get(1);
    int k=duom.size();
    for(Knygos a:duom)
    {
        if(a.getKaina()>kaina)
            duom.remove(a);
    }
 }
/**
 * Tikrina ar yra
 * @param ar
 * @return 
 */
public String arYra(boolean ar)
{
    if(ar==true)
        return "egzistuoja";
    else
    return "neegzistuoja";
}
/**
 * formuoja nauja sarasa
 * @param duom
 * @param autorius
 * @return 
 */
ListKTUx<Knygos> Atrinkti(ListKTUx <Knygos> duom,String autorius)
{
     ListKTUx<Knygos> meh=new ListKTUx<>(new Knygos());
     for(Knygos a:duom)
     {
         if(a.getAut().equals(autorius))
         {
              meh.add(a);
         }
     }
    return meh;
}
}
