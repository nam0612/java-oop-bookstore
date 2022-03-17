/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;

/**
 *
 * @author black
 */
public class Book implements Comparable<Book>{
    
    private int id;
    private int idCategory;
    private String title; 
    private String author; 
    private double price;

    public Book() {
    }

    
    public Book(int id, int idCategory, String title, String author, double price) {
        this.id = id;
        this.idCategory = idCategory;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getCategoryName(ArrayList<Category> t,int id ){
        for (Category o : t) {
            if(o.getId() == id)
                return o.getCategoryName();
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", idCategory=" + idCategory + ", title=" + title + ", author=" + author + ", price=" + price +'}';
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareToIgnoreCase(o.title);
    }
  
}
