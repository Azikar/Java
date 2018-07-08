/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * 
 */
package javaapplication3.meh;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.Comparator;
import javaapplication3.NewClass;

/**
 *
 * @author merp
 * Linked list sarasas.
 */
public class LinkedL<E> implements NewInterface<E>{
    private DLinkedList<E> head;
    private DLinkedList<E> temp;
    private DLinkedList<E> current;
    
    private int size;
    
    public LinkedL()
    {
        this.head=null;
        this.size=0;
    }
    /**
     * tikrina ar sarasas tuscias
     * @return 
     */
    public boolean isEmpty()
    {
       return head==null; 
    }
    
    
   /**
    * deda i saraso pradzia.
    * @param data 
    */ 
    public void add(E data)
    {
        if(head==null)
        {
         head=new DLinkedList<E>(null,data,null);
         
        }
        else
        {
           DLinkedList<E> newlink=new DLinkedList<E>(null,data,head);
           head.previouse=newlink;
           head=newlink;
        }
        size++;
    }
    /**
     * kiek elementu yra sarase
     * @return 
     */
   public int size()
    {
        return size;
    }
   /**
    * spausdina sarasa
    */
   public void reset(){head=null;}
   public void print()
    {
        DLinkedList<E> current=head;
        while(current!=null)
        {
            System.out.println(current.Data());
            current=current.next;
        }
    }
   /**
    * pakeicia norima elementa
    * @param key
    * @param data 
    */
   public void set(int key, E data)
   {
       int i=1;
       current=head;
       
       DLinkedList<E> newlink=new DLinkedList<E>(null,data,null);
       temp=newlink;
       if(key==1)
       {
           temp.previouse=null;
           temp.next=current.next;
           current=temp;
           head=current;
           
       }
       else
       {
           while(i!=key)
           {
               current=current.next;
               i++;
           }
           if(current.next!=null)
           {
              current.data=data;
               
           }
           else{
                current.data=data;
           }
       }
   }
   /**
    * tikrina ar toks elementas yra
    * @param data
    * @return 
    */
   public boolean contains(E data)
   {
       if(head==null)
           return false;
       current=head;
       for(DLinkedList<E> e=head;e!=null;e=e.next)
       {
           if(e.data.equals(data))
           return true;
           
       }
       
                   
       return false;
   }
   /**
    * salina pagal indeksa
    * @param key 
    */
   public void delete(int key)
   {
     
      int i=1;
      current=head;
      if(key==1)
      {
          head=current.next;
          current.next=null;
          current.previouse=null;
      }
      else{
          while(i!=key)
          {
              current=current.next;
              i++;
          }
          if(current.next==null)
          {
              current.previouse.next=null;
              current.previouse=null;
          }
          else{
             current.previouse.next=current.next;
             current.next.previouse=current.previouse;
          }
          }
      }
   /**
    * salina pagal reiksme
    * @param data 
    */
    public void deleteKint(E data)
   {
     
      int i=1;
      current=head;
      if(data.equals(head.data))
      {
          head=current.next;
          current.next=null;
          current.previouse=null;
      }
      else{
          while(current.data!=data&&current.next!=null)
          {
              current=current.next;
              i++;
          }
          if(current.next==null)
          {
              current.previouse.next=null;
              current.previouse=null;
          }
          else{
             current.previouse.next=current.next;
             current.next.previouse=current.previouse;
          }
          }
      }
    /**
     * rikiuoja sarasa
     */
 public void sortJava() {
		Object[] a = this.toArray();
		bubble(a);
		int i = 0;
		for (DLinkedList<E> e1 = head; e1 != null; e1 = e1.next) {
			e1.data = (E) a[i++];
		}
	}

   public Object[] toArray() {
		Object[] a = new Object[size];
		int i = 0;
		for (DLinkedList<E> e1 = head; e1 != null; e1 = e1.next) {
			a[i++] = e1.data;
		}
		return a;
	}
   /**
    * burbulas
    * @param a 
    */
   private void bubble(Object[] a)
   {
         boolean swapped = true;
    int j = 0;
    
    while (swapped) {
        swapped = false;
        j++;
        Object tmp=a;
        for (int i = 0; i < a.length - j; i++) {
            if (a[i].toString().compareTo(a[i+1].toString())<0) {
                tmp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = tmp;
                swapped = true;
            }
        }
    }
   }
	}
    
   
        
   
    

