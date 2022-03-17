/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
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
public class BookManage implements Management {

    private final ArrayList<Book> books = new ArrayList();
    private final Scanner sc = new Scanner(System.in);

    public void print() {
        books.forEach((o) -> {
            System.out.println(o.toString());
        });
    }

    public int search(int id) {
        if (books.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public Book searchObject(int id) {
        if (books.isEmpty()) {
            return null;
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }

    @Override
    public void searchItem() {
        int id;
        id = Validation.getAnInteger("Input Book id: ", "Book id is required!");
        int x = search(id);
        if (x == -1) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("This is a Book you look with id: " + books.get(x).toString());
        }
    }

    public ArrayList list() {
        return books;
    }

    //List all books in ascending order of category names
    @Override
    public void display(int choice) {
        if (choice == 1) {
            CategoryManage a = new CategoryManage();
            a.fetch();
            BookManage b = new BookManage();
            b.fetch();
            ArrayList<Category> cate = new ArrayList();
            cate = a.list();
            ArrayList<Book> book = new ArrayList();
            book = b.list();
            ArrayList<Hybrid> hybrid = new ArrayList();
            for (int i = 0; i < book.size(); i++) {
                for (int j = 0; j < cate.size(); j++) {
                    if (book.get(i).getIdCategory() == cate.get(j).getId()) {
                        hybrid.add(new Hybrid(cate.get(j).getCategoryName(), book.get(i).getId(), book.get(i).getIdCategory(), book.get(i).getTitle(), book.get(i).getAuthor(), book.get(i).getPrice()));
                    }
                }
            }
            for (int i = 0; i < hybrid.size(); i++) {
                for (int j = 0; j < hybrid.size()-i-1; j++) {
                    if (hybrid.get(j).getCateName().charAt(0) > hybrid.get(j + 1).getCateName().charAt(0)) {
                        Hybrid tmp = hybrid.get(j);
                        hybrid.set(j, hybrid.get(j + 1));
                        hybrid.set(j + 1, tmp);
                    }

                }

            }
            for (Hybrid o : hybrid) {
                System.out.println(o.toString());
            }
        } else {
            int idCategory;
            CategoryManage a = new CategoryManage();
            ArrayList<Book> tmp = new ArrayList();
            a.fetch();
            do {
                idCategory = Validation.getAnInteger("Input id of Category: ", "Category id is required!");
            } while (a.search(idCategory) == -1);
            for (Book o : books) {
                if (o.getIdCategory() == idCategory) {
                    tmp.add(o);
                }
            }
            for (int i = 0; i < tmp.size(); i++) {
                for (int j = 0; j < tmp.size() - i - 1; j++) {
                    if (tmp.get(j).getPrice() < tmp.get(j + 1).getPrice()) {
                        Book temp = tmp.get(j);
                        tmp.set(j, tmp.get(j + 1));
                        tmp.set(j + 1, temp);
                    }

                }

            }
            for (Book o : tmp) {
                System.out.println(o.toString());
            }
        }

    }

    @Override
    public void add() {
        int id, check, idCategory;
        String bookName, title, author;
        double price;
        do {
            id = Validation.getAnInteger("Input id of new Book: ", "Please enter the Integer number");
            check = search(id);
            if (check >= 0) {
                System.out.println("The Book's id already exists. " + "Please enter another one!");
            }
        } while (check != -1);
        bookName = Validation.getString("Input name of new Book: ", "Book name can not be blank");
        CategoryManage a = new CategoryManage();
        a.fetch();
        do {
            idCategory = Validation.getAnInteger("Input id of Category: ", "Category id is required!");
        } while (a.search(idCategory) == -1);
        title = Validation.getString("Plese enter the title of book: ", "title is required!");
        author = Validation.getString("Plese enter the author of book: ", "Author is required!");
        price = Validation.getADouble("Plese enter the price of book: ", "Price is required!", 0, 5000);
        books.add(new Book(id, idCategory, title, author, price));
    }

    @Override
    public void update() {
        int id;
        Book t;
        id = Validation.getAnInteger("Input id of book: ", "Book id is required and it must be integer!");
        t = searchObject(id);
        System.out.println("------------------------------------");
        if (t != null) {
            System.out.println("The Book before updating");
            System.out.println(t.toString());
            int choice;

            do {
                System.out.println("1. Update new id Category");
                System.out.println("2. Update new title");
                System.out.println("3. Update new Author");
                System.out.println("4. Update new price");
                System.out.println("5. Exit");
                choice = Validation.getAnInteger("Enter your choice", "Please enter the integer in [1..5]", 1, 5);
                switch (choice) {
                    case 1: {
                        int idCategory;
                        CategoryManage a = new CategoryManage();
                        a.fetch();
                        do {
                            idCategory = Validation.getAnInteger("Input id of Category: ", "Category id is required!");
                        } while (a.search(idCategory) == -1);
                        t.setIdCategory(idCategory);
                        break;
                    }
                    case 2: {
                        t.setTitle(Validation.getString("Input new Title of Book: ", "Ttile is required!"));
                        break;
                    }
                    case 3: {
                        t.setAuthor(Validation.getString("Input new Author of Book: ", "Author is required!"));
                        break;
                    }
                    case 4: {
                        t.setPrice(Validation.getADouble("Input new Price of Book: ", "Price is required!"));
                        break;
                    }
                    case 5: {
                        break;
                    }

                }
            } while (choice != 5);
            System.out.println("The Book info is updated successfully!");
        } else {
            System.out.println("Sorry Your id not found!!!");
        }
    }

    @Override
    public void remove() {
        int id, index;
        id = Validation.getAnInteger("Input id of book: ", "Book id is required it must be integer!");
        index = search(id);
        if (index == -1) {
            System.out.println("Sorry Your item you want remove not found!!!");
        } else {
            books.remove(index);
            System.out.println("The Books is removed successfully!");
        }

    }

    @Override
    public void save() {
        FileWriter writer;
        int count = 0;
        try {
            File f = new File("books.txt");
            File saveBook = new File("Bookstemp.txt");
            writer = new FileWriter(saveBook);
            for (Book o : books) {
                writer.write(o.getId() + ", " + o.getIdCategory() + ", " + o.getTitle() + ", " + ", " + o.getAuthor() + ", " + o.getPrice());
                count++;
                if (count < books.size()) {
                    writer.write(System.lineSeparator());
                }
            }
            writer.close();
            f.delete();
            saveBook.renameTo(f);
        } catch (IOException e) {
            System.out.println("Can't open Categoriestemp.txt");;
        }
    }

    @Override
    public void fetch() {
        try {
            File f = new File("books.txt");
            Scanner myReader = new Scanner(f);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer st = new StringTokenizer(data);
                st = new StringTokenizer(data, ";");
                int id = Integer.parseInt(st.nextToken().trim());
                int idCate = Integer.parseInt(st.nextToken().trim());
                String title = st.nextToken().trim();
                String author = st.nextToken().trim();
                Double price = Double.parseDouble(st.nextToken().trim());
                books.add(new Book(id, idCate, title, author, price));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found or can't not open. Please contact to admin for help!!!");
        }

    }

}
