/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;
import javaapplication3.meh.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
/**
 *
 * @author merp
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // NewClass a1=new NewClass( "mew","v",50,"au",4,4);
        //  ListKTUx<Knygos> meh=new ListKTUx<>(new Knygos());
       LinkedL<NewClass> clase = new LinkedL<>();
       LinkedL<NewClass> kopija = new LinkedL<>();
        NewClass S=new NewClass("mew","v",50,"au",4,4);
        clase.add(S);
        NewClass A=new NewClass("aew","v",50,"au",4,4);
        clase.add(A);
        NewClass B=new NewClass("bew","v",50,"au",4,4);
        clase.add(B);
      //  for(int i=0;i<clase.size();i++)
       clase.print();
       System.out.println(" ");
   //    clase.delete(2);
      // clase.set(3, A);
       
       
     //  clase.deleteKint(A);
     //  clase.print();
     kopija=clase;
      kopija.sortJava();
      kopija.print();
      
if(clase.contains(S))
    System.out.println("taip");
else{
    System.out.println("ne");
}
clase.reset();
clase.print();
    }
    
}
