/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import java.util.Scanner;
import jlab1.beans.Message;

/**
 *
 * @author Administrator
 */
public class Lab2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        jlab1.beans.Message deleteEnt = new Message();
        //deleteEnt
        deleteEnt.setMessage("labas");
        deleteEnt.setName("karolis");
        Scanner sc = new Scanner(System.in);
//        System.out.println("Iveskite id ");
//        int i = sc.nextInt();
//        System.out.println(i);
      //  create(deleteEnt);
       jlab1.beans.Message del = new Message();
        //del.setId(20);
        
        findAll();
        
        System.out.println("Iveskite id ");
        int i = sc.nextInt();
        System.out.println(i);
        del.setId(i);
        del=find(del.getId());
        System.out.println(del.getMessage());
        
        System.out.println("nauja zinute");
        String zin = sc.next();
        del.setMessage(zin);
        edit(del);
        findAll();
    }

   
    

    private static void findAll() {
        jlab1.beans.MessageWS_Service service = new jlab1.beans.MessageWS_Service();
        jlab1.beans.MessageWS port = service.getMessageWSPort();
        java.util.List< jlab1.beans.Message> result = port.findAll();
        for( int i=0; i < result.size(); i++) {
            jlab1.beans.Message tmsg = result.get(i);
            System.out.println(tmsg.getId()+"-"+ tmsg.getName()+"-"+tmsg.getMessage()+"-"+tmsg.getPavard());
        }
        //return port.findAll();
    }

    private static Message find(java.lang.Object id) {
        jlab1.beans.MessageWS_Service service = new jlab1.beans.MessageWS_Service();
        jlab1.beans.MessageWS port = service.getMessageWSPort();
        return port.find(id);
        
    }

    private static void edit(jlab1.beans.Message entity) {
        jlab1.beans.MessageWS_Service service = new jlab1.beans.MessageWS_Service();
        jlab1.beans.MessageWS port = service.getMessageWSPort();
        port.edit(entity);
    }

  

  

   
    
}



//for( int i=0; i < result.size(); i++) {
//            jlab1.beans_client.Message tmsg = result.get(i);
//            out.println(tmsg.getId()+"-"+ tmsg.getName()+"-"+tmsg.getMessage()+"-"+tmsg.getPavard()+"<br>");
