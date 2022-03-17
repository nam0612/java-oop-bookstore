/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author black
 */
public class Hybrid extends Book {

    private String cateName;

    public Hybrid() {
    }

    public Hybrid(String cateName, int id, int idCategory, String title, String author, double price) {
        super(id, idCategory, title, author, price);
        this.cateName = cateName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    

}
