package SwingUI;

import JavaClasses.TreeGenealogy;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TreeSearch {

    private JPanel SearchScreen;
    private JButton personButton;
    private JButton relationshipButton;
    private JTextPane searchTextPane;

    // maybe do a picker system? and keep to one page UI?
    //Person links to another menu: First, last name search AND pID search
    //first, last: needs first and last name space and search button
    //pID: needs pID and search button
    //Relationship link to another menu: rID search; 2 pIDs search;
        //find people of specified relationship "mother of" search (same as gparent search)
    //rID: needs rID and search button
    //2 pIds: needs to pIDs and search button
    //specififed relationship: pID or name of person and picker for "mother of" and search button

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        final javax.swing.JPanel panel1 = new javax.swing.JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new java.awt.Insets(0, 0, 0, 0), -1, -1));
    }

}
