package JavaClasses;

import JavaClasses.DataPrep;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main{

    private static final String defaultFamilyTree = "FamilyTreeInputTextFileV2.txt";
    public static void main(String args[]) throws IOException{
        String treeFile = (args.length == 1 ? args[0] : defaultFamilyTree);

        TreeGenealogy t = new TreeGenealogy(treeFile);

        Scanner myScan = new Scanner(System.in);
        int menuSelection = 0;

        while(menuSelection != 8){
            displayMainMenu();
            try {
                menuSelection = myScan.nextInt();
                switch (menuSelection){
                    case 1:
                        //t.printAllRelationships();
                        break;
                    case 2:
                        //t.searchRelationShip(myScan);
                        break;
                    case 3:
                        //t.searchPerson(myScan);
                        break;
                    case 4:
                        //t.searchGrandParents(myScan);
                        break;
                    case 5:
                        //t.searchFirstName(myScan);
                        break;
                    case 6:
                        //t.searchLastName(myScan);
                        break;
                    case 7:
                        //t.loadFile(myScan);
                        break;
                    case 8:
                        //this is just an exit condition for the while loop
                        break;
                    default:
                        System.out.println("Entry " + menuSelection + " is not a listed option.");
                }
            } catch (Exception e){
                System.out.println("ERROR INPUT: " + myScan.next() + " is not valid.");
            }
            System.out.println("\n\n");
        }
    }


    public static void displayMainMenu(){
        /*
         * 1. List all relationships
         * 2. Print relationship with rID
         * 3. Print person with pID
         * 4. Print grandparents of pID
         * 5. Search by first name
         * 6. Search by last name
         */
        String[] menuItems = new String[8];
        menuItems[0] = "1. List all relationships     ";
        menuItems[1] = "2. Print relationship with rID";
        menuItems[2] = "3. print person with pID      ";
        menuItems[3] = "4. Print grandparents of pID  ";
        menuItems[4] = "5. Search by first name       ";
        menuItems[5] = "6. Search by last name        ";
        menuItems[6] = "7. Load Data                  ";
        menuItems[7] = "8. Exit program               ";

        for(String s : DataPrep.encapsulateWithBorder('+', menuItems))
            System.out.println(s);

    }

}