package SwingUI;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import JavaClasses.Person;
import JavaClasses.Relationship;
import JavaClasses.DataPrep;
import JavaClasses.TreeGenealogy;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static SwingUI.TreeFuncs.getTreeFuncs;

public class TreeSearch extends EntryPage {
    private static JFrame treeSearch;
    private JPanel TreeSearch;
    private JPanel SearchOptions;
    private JButton personButton;
    private JButton relationshipButton;
    private JPanel PersonSearch;
    private JTextPane searchForAPersonTextPane;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JButton searchPerson;
    private JTextPane useFirstOrLastTextPane;
    private JTextArea firstTextArea;
    private JTextField pIDField;
    private JTextArea usePIDTextArea;
    private JTextArea lastTextArea;
    private JTextArea pIDTextArea;
    private JPanel RelationshipSearch;
    private JTextField rIDField;
    private JTextField relationshipPIDField;
    private JTextPane searchForARelationshipTextPane;
    private JButton searchRelationship;
    private JTextArea useRIDTextArea;
    private JTextArea findRelationshipToATextArea;
    private JTextPane pIDTextPane;
    private JComboBox personRelationshipChoice;
    private JTextPane rIDTextPane;
    private JTextPane chooseARelationshipTextPane;
    private JButton backButton;
    private JButton backButton1;
    private JTextPane searchTextPane;
    private JPanel ConsolePage;
    private JScrollPane consoleScroll;
    private JTextArea consoleArea;
    private JButton closeBtn;
    private JButton backButton2;
    private PrintStream standardOut;
    private String rPID;
    private String getFirstName;
    private String getPID;
    private String getRID;
    private String getLastName;
    private int selection;
    private List<Person> a;
   // private static Person p = new Person();
    //private static Relationship r = new Relationship();

    //for TreeSearch to switch pages
    CardLayout newCL = new CardLayout(0, 0);

    public TreeSearch() {

        consoleArea.setEditable(false);

        PrintStream printStream = new PrintStream(new CustomOutputStream(consoleArea));

        // keeps reference of standard output stream
        standardOut = System.out;

        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);


        //Use CardLayout to switch between Pages
        TreeSearch.setLayout(newCL);
        TreeSearch.add(SearchOptions, "Search Options");
        TreeSearch.add(PersonSearch, "Person Search");
        TreeSearch.add(RelationshipSearch, "Relationship Search");
        TreeSearch.add(ConsolePage, "Console");
        newCL.show(TreeSearch, "Search Options");

        //=================================================
        //===================btns==========================
        //=================================================

        //Navigate back to previous form
        backButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame treeFuncs = getTreeFuncs();
                getTreeSearch().setVisible(false);
                treeFuncs.setVisible(true);
            }
        });
        //Search to person search page btn
        personButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Person Search");
            }
        });

        //Search to Relationship search page btn
        relationshipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Relationship Search");
            }
        });

        //back button for person page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Search Options");
            }
        });

        //back button for relationship page
        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Search Options");
            }
        });

        //New search btn for console page (add functionality so that it will clear console each time X is clicked)
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consoleArea.setText("");
                newCL.show(TreeSearch, "Search Options");
            }
        });

        //=================================================
        //====================Searches=====================
        //=================================================

        // Search function for Person (pID, First name, Last name)
        searchPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Console");
                if (!pIDField.getText().isEmpty()) {
                    try {
                        getPID = pIDField.getText().toUpperCase();
                        System.out.println(getTree().getPerson(getPID));
                    } catch (Exception i) {
                        System.out.println("ERROR INPUT: " + getPID + " is not valid.");
                    }
                } else if (!firstNameField.getText().isEmpty()) { //gets person info by First name
                    try {
                        getFirstName = firstNameField.getText();
                        System.out.println(getTree().searchFirstName(getFirstName));
                    } catch (Exception j) {
                        System.out.println("ERROR INPUT: " + getFirstName + " is not valid.");
                    }
                } else if (!lastNameField.getText().isEmpty()) {  //get person info by Last name
                    try {
                        getLastName = lastNameField.getText();
                        System.out.println(getTree().searchLastName(getLastName));
                    } catch (Exception k) {
                        System.out.println("ERROR INPUT: " + getLastName + " is not valid.");
                    }
                }
                //clears the textfields
                pIDField.setText("");
                firstNameField.setText("");
                lastNameField.setText("");

            }
        });

        personRelationshipChoice.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                TreeGenealogy tg = getTree();
                Map<String, Person> people = getPeople();
                Map<String, Relationship> relation = getRelations();
                rPID = relationshipPIDField.getText().toUpperCase();
                selection = personRelationshipChoice.getSelectedIndex();
                switch (selection) {
                    case 0: //Does nothing
                        break;
                    case 1: //parents
                        a = new ArrayList<>(getTree().getParents(rPID));
                        break;
                    case 2: //grandparents
                        a = new ArrayList<>(getTree().getGrandParents(rPID));
                        break;
                        /*
                    case 3: //child
                        a = new ArrayList<>();
                        for (String r : tg.getPerson(rPID).getRelations()) {
                            System.out.println(people.get(rPID).getRelations());
                            System.out.println(r);
                            Relationship w = getTree().getRelationship(r);
                            if (w != null && w.getChildren() != null) {
                                a.addAll(w.getChildren());
                            }
                        }
                        break;
                    case 4: //partner
                        a = new ArrayList<>();
                        System.out.println("Starting p search" + rPID);
                        if (getTree().getPerson(rPID).getRelations() != null) {
                            System.out.println("Relation not null" + rPID);
                            System.out.println(getTree().getPerson(rPID).getRelations());
                            for (String r : getTree().getPerson(rPID).getRelations()) {
                                System.out.println("Checking relation" + r);
                                Relationship w = getTree().getRelationship(r);
                                if (w != null && w.getParents() != null) {
                                    System.out.println("w not null and parents present");
                                    for (Person p : w.getParents()) {
                                        System.out.println("Checking parent" + p.getPersonID());
                                        if (p.getPersonID() != rPID) {
                                            a.add(p);
                                            System.out.println("Adding" + p.getPersonID());
                                        }
                                    }
                                }
                            }
                        }
                        break; */
                    default:
                        break;
                }
            }
        });


        //Search function for Relationships (rID, Relationships to pID)
        searchRelationship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCL.show(TreeSearch, "Console");
                if (!rIDField.getText().isEmpty()) {
                    try {
                        getRID = rIDField.getText().toUpperCase();
                        System.out.println(getTree().getRelationship(getRID));
                    } catch (Exception l) {
                        System.out.println("ERROR INPUT: " + getRID + " is not valid.");
                    }
                } else if (!relationshipPIDField.getText().isEmpty()) {
                    try {
                        if (selection == 1) { //parents
                            try {
                                if (a.isEmpty()) {
                                    throw new IOException();
                                }
                                System.out.println(a);
                            } catch (Exception l) {
                                System.out.println("ERROR INPUT: " + rPID + " has no parents.");
                            }
                        } else if (selection == 2) { //grandparents
                            try {
                                if (a.isEmpty()) {
                                    throw new IOException();
                                }
                                System.out.println(a);
                            } catch (Exception l) {
                                System.out.println("ERROR INPUT: " + rPID + " has no grandparents.");
                            }
                        } /*
                        else if (selection == 3) { //child
                            try {
                                if (a.isEmpty()) {
                                    throw new IOException();
                                }
                                System.out.println(a);
                            } catch (Exception l) {
                                System.out.println("ERROR INPUT: " + rPID + " has no children.");
                            }
                        } else if (selection == 4) { //spouse
                            try {
                                if (a.isEmpty()) {
                                    throw new IOException();
                                }
                                System.out.println(a);
                            } catch (Exception l) {
                                System.out.println("ERROR INPUT: " + rPID + " has no spouse.");
                            }
                        } */
                    } catch (Exception m) {
                        System.out.println("ERROR INPUT: " + relationshipPIDField.getText().toUpperCase() + " is not valid.");
                    }
                }
                //clears the textfields
                rIDField.setText("");
                relationshipPIDField.setText("");
                personRelationshipChoice.setSelectedIndex(0);
            }
        });

    }

    public static void setTreeSearch(JFrame frame) {
        treeSearch = frame;
    }

    public static JFrame getTreeSearch() {
        return treeSearch;
    }

    //prints console to jtextarea

    public class CustomOutputStream extends OutputStream {
        private JTextArea consoleArea;

        public CustomOutputStream(JTextArea consoleArea) {
            this.consoleArea = consoleArea;
        }

        @Override
        public void write(int b) throws IOException {
            // redirects data to the text area
            consoleArea.append(String.valueOf((char) b));
            // scrolls the text area to the end of data
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        }
    }

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
        TreeSearch = new JPanel();
        TreeSearch.setLayout(new CardLayout(0, 0));
        TreeSearch.setBackground(new Color(-2760988));
        TreeSearch.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Search Tree", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, TreeSearch.getFont()), new Color(-16777216)));
        SearchOptions = new JPanel();
        SearchOptions.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 3, new Insets(0, 0, 0, 0), -1, -1));
        SearchOptions.setBackground(new Color(-2760988));
        TreeSearch.add(SearchOptions, "Card1");
        searchTextPane = new JTextPane();
        searchTextPane.setBackground(new Color(-2760988));
        searchTextPane.setEditable(false);
        Font searchTextPaneFont = this.$$$getFont$$$("Consolas", Font.BOLD, 28, searchTextPane.getFont());
        if (searchTextPaneFont != null) searchTextPane.setFont(searchTextPaneFont);
        searchTextPane.setForeground(new Color(-16777216));
        searchTextPane.setText("Search");
        SearchOptions.add(searchTextPane, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        personButton = new JButton();
        Font personButtonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, personButton.getFont());
        if (personButtonFont != null) personButton.setFont(personButtonFont);
        personButton.setText("Person");
        SearchOptions.add(personButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        relationshipButton = new JButton();
        Font relationshipButtonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, relationshipButton.getFont());
        if (relationshipButtonFont != null) relationshipButton.setFont(relationshipButtonFont);
        relationshipButton.setText("Relationship");
        SearchOptions.add(relationshipButton, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        backButton2 = new JButton();
        Font backButton2Font = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, backButton2.getFont());
        if (backButton2Font != null) backButton2.setFont(backButton2Font);
        backButton2.setText("Back");
        SearchOptions.add(backButton2, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        SearchOptions.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        PersonSearch = new JPanel();
        PersonSearch.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 3, new Insets(0, 0, 0, 0), -1, -1));
        PersonSearch.setBackground(new Color(-2760988));
        TreeSearch.add(PersonSearch, "Card2");
        searchForAPersonTextPane = new JTextPane();
        searchForAPersonTextPane.setBackground(new Color(-2760988));
        searchForAPersonTextPane.setEditable(false);
        Font searchForAPersonTextPaneFont = this.$$$getFont$$$("Consolas", Font.BOLD, 24, searchForAPersonTextPane.getFont());
        if (searchForAPersonTextPaneFont != null) searchForAPersonTextPane.setFont(searchForAPersonTextPaneFont);
        searchForAPersonTextPane.setForeground(new Color(-16777216));
        searchForAPersonTextPane.setText("Search for a Person");
        PersonSearch.add(searchForAPersonTextPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        searchPerson = new JButton();
        Font searchPersonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, searchPerson.getFont());
        if (searchPersonFont != null) searchPerson.setFont(searchPersonFont);
        searchPerson.setText("Search");
        PersonSearch.add(searchPerson, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        PersonSearch.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        PersonSearch.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        useFirstOrLastTextPane = new JTextPane();
        useFirstOrLastTextPane.setBackground(new Color(-2760988));
        useFirstOrLastTextPane.setEditable(false);
        Font useFirstOrLastTextPaneFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 20, useFirstOrLastTextPane.getFont());
        if (useFirstOrLastTextPaneFont != null) useFirstOrLastTextPane.setFont(useFirstOrLastTextPaneFont);
        useFirstOrLastTextPane.setForeground(new Color(-16777216));
        useFirstOrLastTextPane.setText("Use First or Last Name");
        PersonSearch.add(useFirstOrLastTextPane, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(170, 50), null, 0, false));
        lastNameField = new JTextField();
        PersonSearch.add(lastNameField, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(170, 30), null, 0, false));
        firstNameField = new JTextField();
        PersonSearch.add(firstNameField, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(170, 30), null, 0, false));
        firstTextArea = new JTextArea();
        firstTextArea.setBackground(new Color(-2760988));
        firstTextArea.setEditable(false);
        firstTextArea.setForeground(new Color(-16777216));
        firstTextArea.setText("first");
        PersonSearch.add(firstTextArea, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        pIDField = new JTextField();
        PersonSearch.add(pIDField, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        usePIDTextArea = new JTextArea();
        usePIDTextArea.setBackground(new Color(-2760988));
        usePIDTextArea.setEditable(false);
        Font usePIDTextAreaFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 20, usePIDTextArea.getFont());
        if (usePIDTextAreaFont != null) usePIDTextArea.setFont(usePIDTextAreaFont);
        usePIDTextArea.setForeground(new Color(-16777216));
        usePIDTextArea.setText("Use pID");
        PersonSearch.add(usePIDTextArea, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        lastTextArea = new JTextArea();
        lastTextArea.setBackground(new Color(-2760988));
        lastTextArea.setEditable(false);
        lastTextArea.setForeground(new Color(-16777216));
        lastTextArea.setText("last");
        PersonSearch.add(lastTextArea, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        pIDTextArea = new JTextArea();
        pIDTextArea.setBackground(new Color(-2760988));
        pIDTextArea.setEditable(false);
        pIDTextArea.setForeground(new Color(-16777216));
        pIDTextArea.setText("pID");
        PersonSearch.add(pIDTextArea, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        backButton = new JButton();
        Font backButtonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, backButton.getFont());
        if (backButtonFont != null) backButton.setFont(backButtonFont);
        backButton.setText("Back");
        PersonSearch.add(backButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        RelationshipSearch = new JPanel();
        RelationshipSearch.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(11, 3, new Insets(0, 0, 0, 0), -1, -1));
        RelationshipSearch.setBackground(new Color(-2760988));
        TreeSearch.add(RelationshipSearch, "Card3");
        relationshipPIDField = new JTextField();
        RelationshipSearch.add(relationshipPIDField, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        searchForARelationshipTextPane = new JTextPane();
        searchForARelationshipTextPane.setBackground(new Color(-2760988));
        searchForARelationshipTextPane.setEditable(false);
        Font searchForARelationshipTextPaneFont = this.$$$getFont$$$("Consolas", Font.BOLD, 24, searchForARelationshipTextPane.getFont());
        if (searchForARelationshipTextPaneFont != null)
            searchForARelationshipTextPane.setFont(searchForARelationshipTextPaneFont);
        searchForARelationshipTextPane.setForeground(new Color(-16777216));
        searchForARelationshipTextPane.setText("Search for a Relationship");
        RelationshipSearch.add(searchForARelationshipTextPane, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        searchRelationship = new JButton();
        Font searchRelationshipFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, searchRelationship.getFont());
        if (searchRelationshipFont != null) searchRelationship.setFont(searchRelationshipFont);
        searchRelationship.setText("Search");
        RelationshipSearch.add(searchRelationship, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        useRIDTextArea = new JTextArea();
        useRIDTextArea.setBackground(new Color(-2760988));
        useRIDTextArea.setEditable(false);
        Font useRIDTextAreaFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 20, useRIDTextArea.getFont());
        if (useRIDTextAreaFont != null) useRIDTextArea.setFont(useRIDTextAreaFont);
        useRIDTextArea.setForeground(new Color(-16777216));
        useRIDTextArea.setText("Use rID");
        RelationshipSearch.add(useRIDTextArea, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        findRelationshipToATextArea = new JTextArea();
        findRelationshipToATextArea.setBackground(new Color(-2760988));
        findRelationshipToATextArea.setEditable(false);
        Font findRelationshipToATextAreaFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 20, findRelationshipToATextArea.getFont());
        if (findRelationshipToATextAreaFont != null)
            findRelationshipToATextArea.setFont(findRelationshipToATextAreaFont);
        findRelationshipToATextArea.setForeground(new Color(-16777216));
        findRelationshipToATextArea.setText("Find relationship to a person");
        findRelationshipToATextArea.setWrapStyleWord(false);
        RelationshipSearch.add(findRelationshipToATextArea, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        personRelationshipChoice = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Choose one...");
        defaultComboBoxModel1.addElement("Parents");
        defaultComboBoxModel1.addElement("Grandparents");
        defaultComboBoxModel1.addElement("Child");
        defaultComboBoxModel1.addElement("Partner");
        personRelationshipChoice.setModel(defaultComboBoxModel1);
        RelationshipSearch.add(personRelationshipChoice, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        RelationshipSearch.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        RelationshipSearch.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer12 = new com.intellij.uiDesigner.core.Spacer();
        RelationshipSearch.add(spacer12, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pIDTextPane = new JTextPane();
        pIDTextPane.setBackground(new Color(-2760988));
        pIDTextPane.setEditable(false);
        pIDTextPane.setForeground(new Color(-16777216));
        pIDTextPane.setText("pID");
        RelationshipSearch.add(pIDTextPane, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        rIDTextPane = new JTextPane();
        rIDTextPane.setBackground(new Color(-2760988));
        rIDTextPane.setEditable(false);
        rIDTextPane.setForeground(new Color(-16777216));
        rIDTextPane.setText("rID");
        RelationshipSearch.add(rIDTextPane, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        chooseARelationshipTextPane = new JTextPane();
        chooseARelationshipTextPane.setBackground(new Color(-2760988));
        chooseARelationshipTextPane.setEditable(false);
        chooseARelationshipTextPane.setForeground(new Color(-16777216));
        chooseARelationshipTextPane.setText("Choose a relationship");
        RelationshipSearch.add(chooseARelationshipTextPane, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 25), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer13 = new com.intellij.uiDesigner.core.Spacer();
        RelationshipSearch.add(spacer13, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        rIDField = new JTextField();
        RelationshipSearch.add(rIDField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        backButton1 = new JButton();
        Font backButton1Font = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, backButton1.getFont());
        if (backButton1Font != null) backButton1.setFont(backButton1Font);
        backButton1.setText("Back");
        RelationshipSearch.add(backButton1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ConsolePage = new JPanel();
        ConsolePage.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        ConsolePage.setBackground(new Color(-2760988));
        TreeSearch.add(ConsolePage, "Card4");
        consoleScroll = new JScrollPane();
        consoleScroll.setBackground(new Color(-2760988));
        ConsolePage.add(consoleScroll, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        consoleArea = new JTextArea();
        consoleArea.setBackground(new Color(-2760988));
        consoleArea.setForeground(new Color(-16777216));
        consoleScroll.setViewportView(consoleArea);
        closeBtn = new JButton();
        closeBtn.setText("New Search");
        ConsolePage.add(closeBtn, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return TreeSearch;
    }

}
