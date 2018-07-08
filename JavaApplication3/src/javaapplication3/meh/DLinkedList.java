/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3.meh;

/**
 *mazgas
 * @author merp
 */
public class DLinkedList <E>{
    public E data;
    public DLinkedList<E> previouse;
    public DLinkedList<E> next;
    
//    public LinkedList(E Data)
//    {
//    previouse=null;
//    this.data=data;
//    next=null;
//    }
    public DLinkedList(DLinkedList<E> previouse,E data,DLinkedList<E> next)
    {
        this.previouse=previouse;
        this.data=data;
        this.next=next;
    }

    public DLinkedList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return 
     */
    public E Data()
    {
    return data;
    }
}