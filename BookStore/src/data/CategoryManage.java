/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import utilities.Validation;

/**
 *
 * @author black
 */
public class CategoryManage implements Management{
    
    private final ArrayList<Category> categories = new ArrayList();
    private final Scanner sc = new Scanner(System.in);
    public String getCategoryName(int id) {
        for (Category o : categories) {
            if (o.getId() == id) {
                return o.getCategoryName();
            }
        }
        return null;
    }

    public int search(int id) {
        if (categories.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Category searchObject(int id) {
        if (categories.isEmpty()) {
            return null;
        }
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                return categories.get(i);
            }
        }
        return null;
    }
    
    @Override
    public void searchItem() {
        int id;
        id = Validation.getAnInteger("Input Category id: ", "Category id is required!");
        int x = search(id);
        if (x == -1) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("This is a category you look with id: " + categories.get(x).toString());
        }
    }
    public ArrayList list(){
        return categories;
    }
    @Override
    public void display(int choice){
        categories.forEach((o) -> {
            System.out.println(o.toString());
        });
    }
    
    
    @Override
     public void add() {
        int id, check;
        String categoryName;
        do {
            id = Validation.getAnInteger("Input id of new Category: ", "Please enter the Integer number");
            check = search(id);
            if (check == 1) {
                System.out.println("The Category's id already exists. " + "Please enter another one!");
            }
        } while (check != -1);
        categoryName = Validation.getString("Input name of new Category: ", "Category name can not be blank");
        categories.add(new Category(id, categoryName));
    }

    @Override
    public void update() {
        int id;
        Category t;
        id = Validation.getAnInteger("Input id of Category: ", "Category id is required!");
        t = searchObject(id);
        System.out.println("------------------------------------");
        if (t != null) {
            System.out.println("The Category before updating");
            System.out.println(t.toString());
            System.out.println("You are required to input a new name");
            t.setCategoryName(Validation.getString("Input new name of Category: ", "Name is required!"));
            System.out.println("The Category info is updated successfully!");
        } else {
            System.out.println("Sorry Your id not found!!!");
        }
    }
    
    @Override
    public void remove(){ 
    }

  

    @Override
    public void save() {
        FileWriter writer;
        int count = 0;
        try {
            File f = new File("categories.txt");
            File saveCate = new File("Categoriestemp.txt");
            writer = new FileWriter(saveCate);
            for (Category o : categories) {
                writer.write(o.getId()+", "+o.getCategoryName());
                count++;
                if(count < categories.size()){
                    writer.write(System.lineSeparator());
                }
            }
            writer.close();
            f.delete();
            saveCate.renameTo(f); 
        } catch (IOException e) {
            System.out.println("Can't open Categoriestemp.txt");;
        }
    }
    
    @Override
    public void fetch() {
        try {
            File f = new File("categories.txt");
            Scanner myReader = new Scanner(f);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer st = new StringTokenizer(data);
                st = new StringTokenizer(data, ",");
                int id = Integer.parseInt(st.nextToken().trim());
                String name = st.nextToken().trim();
                categories.add(new Category(id, name));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found or can't not open. Please contact to admin for help!!!");
        }
    }
}
