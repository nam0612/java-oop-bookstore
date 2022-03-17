/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore.view;

import java.util.ArrayList;
import utilities.Validation;

/**
 *
 * @author black
 */
public class Menu {

    private String titleOfMenu;
    private ArrayList<String> listOfOption = new ArrayList();

    public Menu() {
    }

    public Menu(String titleOfMenu) {
        this.titleOfMenu = titleOfMenu;
    }

    public String getTitleOfMenu() {
        return titleOfMenu;
    }

    public void setTitleOfMenu(String titleOfMenu) {
        this.titleOfMenu = titleOfMenu;
    }

    public ArrayList<String> getListOfOption() {
        return listOfOption;
    }

    public void setListOfOption(ArrayList<String> listOfOption) {
        this.listOfOption = listOfOption;
    }

    public void addNewOption(String newOption) {
        listOfOption.add(newOption);
    }

    public int getChoice() {
        int limit = listOfOption.size();
        String inputMsg = "Choose option [1.." + limit + "]: ";
        String errorMsg = "You are required to choose the option 1.." + limit;
        return Validation.getAnInteger(inputMsg, errorMsg, 1, limit);
    }

    public void printMenu() {
        if (listOfOption.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("                                        MENU                                         ");
        System.out.println("=====================================================================================");
        System.out.println("");
        System.out.println("                          " + titleOfMenu + "\n");
        for (String o : listOfOption) {
            System.out.println(o);
        }
        System.out.println("=====================================================================================");

    }

}
