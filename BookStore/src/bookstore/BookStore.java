/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

import bookstore.view.Menu;
import data.BookManage;
import data.CategoryManage;
import data.Management;


/**
 *
 * @author black
 */
public class BookStore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu("Welcome to Comlombus's Book store");
        menu.addNewOption("1- List all caegories");
        menu.addNewOption("2- Add a new category");
        menu.addNewOption("3- Search a category based on its ID");
        menu.addNewOption("4- Update a category");
        menu.addNewOption("5- Save categories to the file, named categories.txt");
        menu.addNewOption("6- List all books in ascending order of category names");
        menu.addNewOption("7- List all books of a category by descending order of price");
        menu.addNewOption("8- Add a book");
        menu.addNewOption("9- Remove a book based on its ID");
        menu.addNewOption("10- Update a book based on its ID");
        menu.addNewOption("11- Save books to file, named books.txt");
        menu.addNewOption("12- Quit");

        
        Management bookManage = new BookManage();
        bookManage.fetch();
        Management categoryManage = new CategoryManage();
        categoryManage.fetch();      
        int choice;
        do {
            
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    categoryManage.display(0);
                    break;
                case 2:
                    categoryManage.add();
                    break;                    
                case 3:
                    categoryManage.searchItem();
                    break;
                case 4:
                    categoryManage.update();
                    break;
                case 5:
                    categoryManage.save();
                    break;
                case 6:
                    bookManage.display(1);
                    break;
                case 7:
                    bookManage.display(2);
                    break;    
                case 8:
                    bookManage.add();
                    break;
                case 9:
                    bookManage.remove();
                    break;
                case 10:
                    bookManage.update();
                    break;
                case 11:
                    bookManage.save();
                    break;
                case 12:
                    System.out.println("By! See you later");
                    break;
                
            }
        } while (choice != 12);
    }

}
