/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3.meh;
import java.util.Collection;
/**
 *interfeisas 
 * @author merp
 */
public interface NewInterface<E> {
    
    void add(E e);
    int size();
    void print();
    void set(int i,E data);
    void delete(int key);
    boolean contains(E data);
  
    
}
