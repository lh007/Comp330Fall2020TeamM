package SwingUI;

import JavaClasses.Person;
import JavaClasses.Relationship;
import JavaClasses.TreeGenealogy;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static SwingUI.EntryPage.*;
import static SwingUI.TreeFuncs.getTreeFuncs;

public class TreeEdit {
    private static JFrame treeEdit;
    private JPanel TreeEdit;
    private JPanel RootCard;
    private JPanel AddPerson;
    private JButton b_addPerson;
    private JButton b_addRelationship;
    private JButton b_removePerson;
    private JButton b_editRelationship;
    private JButton root_back;
    private JPanel AddRelationship;
    private JPanel EditRelationship;
    private JButton ap_cancel;
    private JTextField firstname_input;
    private JTextField lastname_input;
    private JTextField suffix_input;
    private JComboBox birthdate_mm;
    private JComboBox birthdate_dd;
    private JTextField birthplace_input;
    private JComboBox deathdate_mm;
    private JComboBox deathdate_dd;
    private JTextField deathplace_input;
    private JLabel firstname_label;
    private JLabel lastname_label;
    private JLabel suffix_label;
    private JLabel birthdate_label;
    private JLabel birthplace_label;
    private JLabel deathdate_label;
    private JLabel deathplace_label;
    private JButton ap_submit;
    private JButton ar_cancel;
    private JRadioButton select_partner;
    private JTextField pID_mother_input;
    private JTextField pID_part1_input;
    private JTextField pID_father_input;
    private JTextField pID_part2_input;
    private JTextArea reltype_prompt;
    private JButton ar_submit;
    private JRadioButton select_child;
    private JLabel pID_mother_label;
    private JLabel pID_father_label;
    private JTextField pID_child_input;
    private JLabel pID_child_label;
    private JLabel pID_part1_label;
    private JLabel startdate_label;
    private JLabel pID_part2_label;
    private JTextField startdate_input;
    private JLabel enddate_label;
    private JTextField enddate_input;
    private JButton er_cancel;
    private JFormattedTextField rID_input;
    private JLabel rID_label;
    private JTextArea current_rel_text;
    private JLabel current_rel;
    private JFormattedTextField pID_p1_input;
    private JLabel pID_p1_label;
    private JFormattedTextField pID_p2_input;
    private JLabel pID_p2_label;
    private JTextField birthdate_yyyy;
    private JTextField deathdate_yyyy;
    private JTextField guide_text;
    private JTextField required1;
    private JTextField required2;
    private JTextField prompt1;
    private JTextField prompt2;
    private JLabel location_label;
    private JTextField location_input;
    private ButtonGroup radioButtons;

    private JRadioButton buttonSelected;
    private CardLayout cardLayout = new CardLayout();

    public TreeEdit() {
        TreeEdit.setLayout(cardLayout);
        TreeEdit.add(RootCard, "Edit Options");
        TreeEdit.add(AddPerson, "Add Person");
        TreeEdit.add(AddRelationship, "Add Relationship");
        TreeEdit.add(EditRelationship, "Edit Relationship");
        cardLayout.show(TreeEdit, "Edit Options");

        /////// CARD: RootCard ///////
        /* Action Listener for Add Person button */
        b_addPerson.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Add Person");
            }
        });

        /* Action Listener for Add Relationship button */
        b_addRelationship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Add Relationship");
            }
        });

        /* Action Listener for Edit Relationship button */
        b_editRelationship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Edit Relationship");
            }
        });

        /* Action Listener for Back button */
        root_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame treeFuncs = getTreeFuncs();
                getTreeEdit().setVisible(false);
                treeFuncs.setVisible(true);
            }
        });

        /////// CARD: AddPerson ///////
        /* Action Listener for Cancel button */
        ap_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Edit Options");
            }
        });

        /* Filling out Combo Boxes for dates */
        birthdate_mm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Filling in dd ComboBox */
                int index = birthdate_mm.getSelectedIndex();
                // January, March, May, July, August, October, December : 31 days
                int[] thirtyOne = {0, 2, 4, 6, 7, 9, 11}; //indices
                // April, June, September, November : 30 days
                int[] thirty = {3, 5, 8, 10}; //indices
                // February : 28 days
                int feb = 1;

                // Selected month has 31 days
                for (int i : thirtyOne) {
                    if (index == i) {
                        String[] days = {
                                "01", "02", "03", "04", "05", "06", "07", "08", "09",
                                "10", "11", "12", "13", "14", "15", "16", "17", "18",
                                "19", "20", "21", "22", "23", "24", "25", "26", "27",
                                "28", "29", "30", "31", "Unknown"
                        };
                        if (birthdate_dd.getItemCount() != 0) {
                            birthdate_dd.removeAllItems();
                        }
                        for (String day : days) {
                            birthdate_dd.addItem(day);
                        }
                    }
                }
                // Selected month has 30 days
                for (int i : thirty) {
                    if (index == i) {
                        String[] days = {
                                "01", "02", "03", "04", "05", "06", "07", "08", "09",
                                "10", "11", "12", "13", "14", "15", "16", "17", "18",
                                "19", "20", "21", "22", "23", "24", "25", "26", "27",
                                "28", "29", "30", "Unknown"
                        };
                        if (birthdate_dd.getItemCount() != 0) {
                            birthdate_dd.removeAllItems();
                        }
                        for (String day : days) {
                            birthdate_dd.addItem(day);
                        }
                    }
                }
                // Selected month is February
                if (index == feb) {
                    String[] days = {
                            "01", "02", "03", "04", "05", "06", "07", "08", "09",
                            "10", "11", "12", "13", "14", "15", "16", "17", "18",
                            "19", "20", "21", "22", "23", "24", "25", "26", "27",
                            "28", "Unknown"
                    };
                    if (birthdate_dd.getItemCount() != 0) {
                        birthdate_dd.removeAllItems();
                    }
                    for (String day : days) {
                        birthdate_dd.addItem(day);
                    }
                }
                // Unknown is selected, all days provided
                if (index == 12) {
                    String[] days = {
                            "01", "02", "03", "04", "05", "06", "07", "08", "09",
                            "10", "11", "12", "13", "14", "15", "16", "17", "18",
                            "19", "20", "21", "22", "23", "24", "25", "26", "27",
                            "28", "29", "30", "31", "Unknown"
                    };
                    if (birthdate_dd.getItemCount() != 0) {
                        birthdate_dd.removeAllItems();
                    }
                    for (String day : days) {
                        birthdate_dd.addItem(day);
                    }
                }
            }
        });
        deathdate_mm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Filling in dd ComboBox */
                int index = deathdate_mm.getSelectedIndex();
                // January, March, May, July, August, October, December : 31 days
                int[] thirtyOne = {1, 3, 5, 7, 8, 10, 12}; //indices
                // April, June, September, November : 30 days
                int[] thirty = {4, 6, 9, 11}; //indices
                // February : 28 days
                int feb = 2;
                // index 0 = N/A, index 13 = Unknown

                // Selected month has 31 days
                for (int i : thirtyOne) {
                    if (index == i) {
                        String[] days = {
                                "01", "02", "03", "04", "05", "06", "07", "08", "09",
                                "10", "11", "12", "13", "14", "15", "16", "17", "18",
                                "19", "20", "21", "22", "23", "24", "25", "26", "27",
                                "28", "29", "30", "31", "Unknown"
                        };
                        if (deathdate_dd.getItemCount() != 0) {
                            deathdate_dd.removeAllItems();
                        }
                        for (String day : days) {
                            deathdate_dd.addItem(day);
                        }
                    }
                }
                // Selected month has 30 days
                for (int i : thirty) {
                    if (index == i) {
                        String[] days = {
                                "01", "02", "03", "04", "05", "06", "07", "08", "09",
                                "10", "11", "12", "13", "14", "15", "16", "17", "18",
                                "19", "20", "21", "22", "23", "24", "25", "26", "27",
                                "28", "29", "30", "Unknown"
                        };
                        if (deathdate_dd.getItemCount() != 0) {
                            deathdate_dd.removeAllItems();
                        }
                        for (String day : days) {
                            deathdate_dd.addItem(day);
                        }
                    }
                }
                // Selected month is February
                if (index == feb) {
                    String[] days = {
                            "01", "02", "03", "04", "05", "06", "07", "08", "09",
                            "10", "11", "12", "13", "14", "15", "16", "17", "18",
                            "19", "20", "21", "22", "23", "24", "25", "26", "27",
                            "28", "Unknown"
                    };
                    if (deathdate_dd.getItemCount() != 0) {
                        deathdate_dd.removeAllItems();
                    }
                    for (String day : days) {
                        deathdate_dd.addItem(day);
                    }
                }
                // Unknown is selected, all days provided
                if (index == 13) {
                    String[] days = {
                            "01", "02", "03", "04", "05", "06", "07", "08", "09",
                            "10", "11", "12", "13", "14", "15", "16", "17", "18",
                            "19", "20", "21", "22", "23", "24", "25", "26", "27",
                            "28", "29", "30", "31", "Unknown"
                    };
                    if (deathdate_dd.getItemCount() != 0) {
                        deathdate_dd.removeAllItems();
                    }
                    for (String day : days) {
                        deathdate_dd.addItem(day);
                    }
                }
                if (index == 0) {
                    if (deathdate_dd.getItemCount() != 0) {
                        deathdate_dd.removeAllItems();
                    }
                    deathdate_dd.addItem("N/A");
                }
            }
        });

        /* Action Listener for Submit button */
        /* Text inputs:
                firstname_input | lastname_input | suffix_input | birthplace_input | deathplace_input
                birthdate_yyyy | deathdate_yyyy
           Combo Box inputs:
                birthdate_mm | birthdate_dd
                deathdate_mm | deathdate_dd
         */
        ap_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeGenealogy familyTree = getTree(); //Get tree from EntryPage
                String firstName = firstname_input.getText();
                if (firstName.equals("")) { firstName=null; }
                String lastName = lastname_input.getText();
                if (lastName.equals("")) { lastName=null; }
                String suffix = suffix_input.getText();
                if (suffix.equals("")) { suffix=null; }
                String birthPlace = birthplace_input.getText();
                if (birthPlace.equals("")) { birthPlace=null; }
                String deathPlace = deathplace_input.getText();
                if (deathPlace.equals("")) { deathPlace=null; }

                String birth_mm = birthdate_mm.getSelectedItem().toString();
                String birth_dd = birthdate_dd.getSelectedItem().toString();
                String birth_yyyy = birthdate_yyyy.getText();

                String death_mm = deathdate_mm.getSelectedItem().toString();
                String death_dd = deathdate_dd.getSelectedItem().toString();
                String death_yyyy = deathdate_yyyy.getText();

                // Generate a pID for the new person
                Map<String, Person> existingPeople = familyTree.getPeople();
                String pID = "";
                boolean found = false;
                int x = 1;
                String current = "";
                while (found == false) {
                    current = "P" + String.valueOf(x);
                    if (existingPeople.containsKey(current)) { //pID already exists in tree
                        x += 1;
                        continue; //try again
                    } else {
                        pID = current;
                        found = true; //break while loop
                    }
                }
                // Format birthdate and deathdate
                String deathDate = "";
                String birthDate = "";
                String par1 = "/";
                String par2 = "/";
                String par3 = "/";
                String par4 = "/";

                if (birth_mm.equals("Unknown")) {
                    birth_mm = "";
                    par1 = "";
                }
                if (birth_dd.equals("Unknown")) {
                    birth_dd = "";
                }
                if (birth_yyyy.equals("")) {
                    birth_yyyy = null;
                    par2 = "";
                }
                if (death_mm.equals("Unknown") || death_mm.equals("N/A")) {
                    death_mm = "";
                    par3="";
                }
                if (death_dd.equals("Unknown") || death_dd.equals("N/A")) {
                    death_dd = "";
                }
                if (death_yyyy.equals("")) {
                    birth_yyyy = null;
                    par4 = "";
                }

                birthDate = birth_mm + par1 + birth_dd + par2 + birth_yyyy;
                deathDate = death_mm + par3 + death_dd + par4 + death_yyyy;

                // Add the new person to the tree
                Person p = new Person(pID, lastName, firstName, suffix, birthDate, birthPlace, deathDate, deathPlace);
                existingPeople.put(pID, p);
                updatePeople(existingPeople); //updates the global var for the tree
                System.out.println("Successfully added person: " + p.toString());

                cardLayout.show(TreeEdit, "Edit Options"); //automatically return to root page
            }
        });

        /////// CARD: AddRelationship ///////
        /* Action Listener for Cancel button */
        ar_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit,"Edit Options");
            }
        });

        /* Action Listener for Select Child */
        select_child.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pID_part1_label.setEnabled(false);
                pID_part1_input.setEnabled(false);
                pID_part2_label.setEnabled(false);
                pID_part2_input.setEnabled(false);
                startdate_label.setEnabled(false);
                startdate_input.setEnabled(false);
                prompt1.setEnabled(false);
                enddate_label.setEnabled(false);
                enddate_input.setEnabled(false);
                location_label.setEnabled(false);
                location_input.setEnabled(false);
                prompt2.setEnabled(false);

                pID_mother_label.setEnabled(true);
                pID_mother_input.setEnabled(true);
                pID_father_label.setEnabled(true);
                pID_father_input.setEnabled(true);
                pID_child_label.setEnabled(true);
                pID_child_input.setEnabled(true);
                ar_submit.setEnabled(true);

                buttonSelected = select_child;
            }
        });
        /* Action Listener for Select Partner */
        select_partner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pID_mother_label.setEnabled(false);
                pID_mother_input.setEnabled(false);
                pID_father_label.setEnabled(false);
                pID_father_input.setEnabled(false);
                pID_child_label.setEnabled(false);
                pID_child_input.setEnabled(false);
                ar_submit.setEnabled(false);

                pID_part1_label.setEnabled(true);
                pID_part1_input.setEnabled(true);
                pID_part2_label.setEnabled(true);
                pID_part2_input.setEnabled(true);
                startdate_label.setEnabled(true);
                startdate_input.setEnabled(true);
                prompt1.setEnabled(true);
                enddate_label.setEnabled(true);
                enddate_input.setEnabled(true);
                location_label.setEnabled(true);
                location_input.setEnabled(true);
                prompt2.setEnabled(true);

                buttonSelected = select_partner;
            }
        });
        /* Action Listener for Submit button */
        ar_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeGenealogy familyTree = getTree(); //method from EntryPage
                Map<String, Relationship> relations = getRelations(); //method from EntryPage
                Map<String, Person> people = getPeople(); //method from EntryPage
                // Child Relationship Selected
                if (buttonSelected.equals(select_child)) {
                    String mom_pID = pID_mother_input.getText().toUpperCase(); //ensures "P"
                    String dad_pID = pID_father_input.getText().toUpperCase(); //ensures "P"
                    String child_pID = pID_child_input.getText();
                    if (mom_pID.equals("")) { mom_pID = null; }
                    if (dad_pID.equals("")) { dad_pID = null; }
                    if (child_pID.equals("")) { child_pID = null; }

                    // Error for no parents
                    if (mom_pID==null && dad_pID==null) {
                        System.out.println("Error: At least one parent must be entered");
                    }
                    // Error for no child
                    else if (child_pID==null) {
                        System.out.println("Error: A child must be entered");
                    }
                    // Check to see if the pID entered exists
                    else if (!people.containsKey(mom_pID)) {
                        System.out.println("Error: pID entered for Mother does not exist. Check that the correct value was entered or create a new person first");
                    }
                    else if (!people.containsKey(dad_pID)) {
                        System.out.println("Error: pID entered for Father does not exist. Check that the correct value was entered or create a new person first");
                    }
                    else if (!people.containsKey(child_pID)) {
                        System.out.println("Error: pID entered for Child does not exist. Check that the correct value was entered or create a new person first");
                    }
                    // All inputs are valid
                    else {
                        // Generate rID
                        String rID = "";
                        int x = 1;
                        String current = "";
                        boolean found = false;
                        while (!found) {
                            current = "R" + String.valueOf(x);
                            if (relations.containsKey(current)) { //rID already exists
                                x+=1;
                                continue; //try again
                            } else { //found an unused rID
                                rID = current;
                                found = true; //break loop
                            }
                        }

                        Person child = people.get(child_pID); //Person object for child
                        // For one-parent relationships
                        String startDate = null;
                        String endDate = null;
                        String marriageLocation = null;
                        Person femaleParent = null; //use in Relationship constructor if no mom is entered
                        Person maleParent = null; //use in Relationship constructor if no dad is entered

                        // Child already belongs to a parent relationship
                        if (child.getChildOfR() != null) {
                            System.out.println("Error: Entered child already has a parent relationship registered");
                        }
                        // Mom entered, dad not entered, no rID would exist
                        else if (mom_pID != null && dad_pID == null) {
                            Person mom = people.get(mom_pID); //Person object for mom
                            child.setChildOfR(rID); //method from Person class, set the child's parent
                            mom.addRelation(rID); //method from Person class, add the rID to mom's relationships
                            Relationship r = new Relationship(rID,mom,maleParent,startDate,endDate,marriageLocation);
                            r.addChild(child); //add the child to the relationship's list of children
                            relations.put(rID,r); //add to tree's relations
                            updateRelations(relations); //update the relations global var
                            System.out.println("Relationship successfully added: " + r.toString());
                        }
                        // Dad entered, mom not entered, no rID would exist
                        else if (dad_pID != null && mom_pID == null) {
                            Person dad = people.get(dad_pID);
                            child.setChildOfR(rID);
                            dad.addRelation(rID);
                            Relationship r = new Relationship(rID,femaleParent,dad,startDate,endDate,marriageLocation);
                            r.addChild(child);
                            relations.put(rID,r);
                            updateRelations(relations);
                            System.out.println("Relationship successfully added: " + r.toString());
                        }
                        // Both mom and dad entered, rID may or may not exist
                        else {
                            Person mom = people.get(mom_pID);
                            Person dad = people.get(dad_pID);
                            Set<String> momRelations = mom.getRelations(); //method from Person class
                            Set<String> dadRelations = dad.getRelations();
                            boolean changed = false;

                            // Check if a partnership exists between the parents
                            for (String ID : momRelations) { //traverse through mom's relationships
                                if (dadRelations.contains(ID)) { //checking for a common relationship in dad's
                                    rID = ID; //common relationship found
                                    changed = true;
                                } else {
                                    continue; //not found, try again
                                }
                            }

                            // No partnership exists
                            if (!changed) {
                                System.out.println("Error: No partnership found between entered mother and father. Add a new partnership first");
                            }
                            // Partnership exists
                            else {
                                Relationship r = relations.get(rID); //pull their relationship from the tree
                                List<Person> children = r.getChildren(); //method from Person, get their existing children
                                // The child relationship already exists
                                if (children.contains(child_pID)) {
                                    System.out.println("Error: A relationship for the entered parent(s) and child already exists");
                                }
                                // The child relationship does not exist
                                else {
                                    children.add(child); //Add the child to parents' children list
                                    child.setChildOfR(rID); //Set the child's parents
                                    System.out.println("Relationship successfully added: " + r.toString());
                                }
                            }
                        }
                    } //end processes for valid inputs
                } //end child select

                // Partner Relationship Selected
                if (buttonSelected.equals(select_partner)) {

                }
            }
        });
    }

    public static void setTreeEdit(JFrame frame) {
        treeEdit = frame;
    }

    public static JFrame getTreeEdit() {
        return treeEdit;
    }


    /////////////////////////
    /* INTELLIJ ADDED CODE */

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
        TreeEdit = new JPanel();
        TreeEdit.setLayout(new CardLayout(0, 0));
        TreeEdit.setBackground(new Color(-5997967));
        TreeEdit.setPreferredSize(new Dimension(600, 400));
        RootCard = new JPanel();
        RootCard.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 1, new Insets(0, 0, 0, 0), -1, -1));
        RootCard.setBackground(new Color(-3040113));
        RootCard.setMinimumSize(new Dimension(300, 200));
        RootCard.setPreferredSize(new Dimension(600, 400));
        TreeEdit.add(RootCard, "rootCard");
        RootCard.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Edit Tree", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, RootCard.getFont()), new Color(-16777216)));
        b_addPerson = new JButton();
        b_addPerson.setBackground(new Color(-855310));
        Font b_addPersonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, b_addPerson.getFont());
        if (b_addPersonFont != null) b_addPerson.setFont(b_addPersonFont);
        b_addPerson.setText("Add New Person");
        b_addPerson.setToolTipText("Add a new person to the tree");
        RootCard.add(b_addPerson, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        b_addRelationship = new JButton();
        b_addRelationship.setBackground(new Color(-855310));
        Font b_addRelationshipFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, b_addRelationship.getFont());
        if (b_addRelationshipFont != null) b_addRelationship.setFont(b_addRelationshipFont);
        b_addRelationship.setText("Add New Relationship");
        b_addRelationship.setToolTipText("Add a new relationship between two existing people");
        RootCard.add(b_addRelationship, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        b_editRelationship = new JButton();
        Font b_editRelationshipFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, b_editRelationship.getFont());
        if (b_editRelationshipFont != null) b_editRelationship.setFont(b_editRelationshipFont);
        b_editRelationship.setText("Edit Relationship");
        b_editRelationship.setToolTipText("Edit an existing relationship between two people");
        RootCard.add(b_editRelationship, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        root_back = new JButton();
        root_back.setBackground(new Color(-855310));
        Font root_backFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, root_back.getFont());
        if (root_backFont != null) root_back.setFont(root_backFont);
        root_back.setText("Back");
        root_back.setToolTipText("Return to previous window");
        RootCard.add(root_back, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        RootCard.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        RootCard.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        RootCard.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer4 = new com.intellij.uiDesigner.core.Spacer();
        RootCard.add(spacer4, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer5 = new com.intellij.uiDesigner.core.Spacer();
        RootCard.add(spacer5, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        AddPerson = new JPanel();
        AddPerson.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        AddPerson.setBackground(new Color(-3040113));
        AddPerson.setPreferredSize(new Dimension(600, 400));
        TreeEdit.add(AddPerson, "add_person");
        AddPerson.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Add A New Person", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, AddPerson.getFont()), new Color(-16777216)));
        ap_cancel = new JButton();
        Font ap_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ap_cancel.getFont());
        if (ap_cancelFont != null) ap_cancel.setFont(ap_cancelFont);
        ap_cancel.setHorizontalTextPosition(11);
        ap_cancel.setText("Cancel");
        AddPerson.add(ap_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        AddPerson.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        AddPerson.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        AddPerson.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, new Dimension(78, 11), null, 0, false));
        birthdate_mm = new JComboBox();
        birthdate_mm.setBackground(new Color(-855310));
        Font birthdate_mmFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthdate_mm.getFont());
        if (birthdate_mmFont != null) birthdate_mm.setFont(birthdate_mmFont);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("01");
        defaultComboBoxModel1.addElement("02");
        defaultComboBoxModel1.addElement("03");
        defaultComboBoxModel1.addElement("04");
        defaultComboBoxModel1.addElement("05");
        defaultComboBoxModel1.addElement("06");
        defaultComboBoxModel1.addElement("07");
        defaultComboBoxModel1.addElement("08");
        defaultComboBoxModel1.addElement("09");
        defaultComboBoxModel1.addElement("10");
        defaultComboBoxModel1.addElement("11");
        defaultComboBoxModel1.addElement("12");
        defaultComboBoxModel1.addElement("Unknown");
        birthdate_mm.setModel(defaultComboBoxModel1);
        birthdate_mm.setToolTipText("Month");
        AddPerson.add(birthdate_mm, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(78, 30), null, 0, false));
        birthplace_input = new JTextField();
        birthplace_input.setBackground(new Color(-1));
        Font birthplace_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthplace_input.getFont());
        if (birthplace_inputFont != null) birthplace_input.setFont(birthplace_inputFont);
        birthplace_input.setText("");
        birthplace_input.setToolTipText("e.g. Chicago, IL");
        AddPerson.add(birthplace_input, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        deathdate_mm = new JComboBox();
        deathdate_mm.setBackground(new Color(-855310));
        Font deathdate_mmFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_mm.getFont());
        if (deathdate_mmFont != null) deathdate_mm.setFont(deathdate_mmFont);
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("N/A");
        defaultComboBoxModel2.addElement("01");
        defaultComboBoxModel2.addElement("02");
        defaultComboBoxModel2.addElement("03");
        defaultComboBoxModel2.addElement("04");
        defaultComboBoxModel2.addElement("05");
        defaultComboBoxModel2.addElement("06");
        defaultComboBoxModel2.addElement("07");
        defaultComboBoxModel2.addElement("08");
        defaultComboBoxModel2.addElement("09");
        defaultComboBoxModel2.addElement("10");
        defaultComboBoxModel2.addElement("11");
        defaultComboBoxModel2.addElement("12");
        defaultComboBoxModel2.addElement("Unknown");
        deathdate_mm.setModel(defaultComboBoxModel2);
        deathdate_mm.setToolTipText("Month");
        AddPerson.add(deathdate_mm, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(78, 30), null, 0, false));
        deathdate_dd = new JComboBox();
        deathdate_dd.setBackground(new Color(-855310));
        Font deathdate_ddFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_dd.getFont());
        if (deathdate_ddFont != null) deathdate_dd.setFont(deathdate_ddFont);
        deathdate_dd.setToolTipText("Day");
        AddPerson.add(deathdate_dd, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deathplace_input = new JTextField();
        deathplace_input.setBackground(new Color(-1));
        Font deathplace_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathplace_input.getFont());
        if (deathplace_inputFont != null) deathplace_input.setFont(deathplace_inputFont);
        deathplace_input.setToolTipText("e.g. Dallas, TX");
        AddPerson.add(deathplace_input, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        firstname_label = new JLabel();
        Font firstname_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, firstname_label.getFont());
        if (firstname_labelFont != null) firstname_label.setFont(firstname_labelFont);
        firstname_label.setText("First Name:");
        AddPerson.add(firstname_label, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastname_label = new JLabel();
        Font lastname_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, lastname_label.getFont());
        if (lastname_labelFont != null) lastname_label.setFont(lastname_labelFont);
        lastname_label.setText("Last Name:");
        AddPerson.add(lastname_label, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        suffix_label = new JLabel();
        Font suffix_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, suffix_label.getFont());
        if (suffix_labelFont != null) suffix_label.setFont(suffix_labelFont);
        suffix_label.setText("Suffix:");
        AddPerson.add(suffix_label, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthdate_label = new JLabel();
        Font birthdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthdate_label.getFont());
        if (birthdate_labelFont != null) birthdate_label.setFont(birthdate_labelFont);
        birthdate_label.setText("Birth Date:");
        AddPerson.add(birthdate_label, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthplace_label = new JLabel();
        Font birthplace_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthplace_label.getFont());
        if (birthplace_labelFont != null) birthplace_label.setFont(birthplace_labelFont);
        birthplace_label.setText("Place of Birth:");
        AddPerson.add(birthplace_label, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deathdate_label = new JLabel();
        Font deathdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_label.getFont());
        if (deathdate_labelFont != null) deathdate_label.setFont(deathdate_labelFont);
        deathdate_label.setText("Date of Death:");
        deathdate_label.setToolTipText("Leave blank if still alive");
        AddPerson.add(deathdate_label, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deathplace_label = new JLabel();
        Font deathplace_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathplace_label.getFont());
        if (deathplace_labelFont != null) deathplace_label.setFont(deathplace_labelFont);
        deathplace_label.setText("Place of Death:");
        deathplace_label.setToolTipText("Leave blank if still alive");
        AddPerson.add(deathplace_label, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        firstname_input = new JTextField();
        firstname_input.setBackground(new Color(-1));
        Font firstname_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, firstname_input.getFont());
        if (firstname_inputFont != null) firstname_input.setFont(firstname_inputFont);
        AddPerson.add(firstname_input, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lastname_input = new JTextField();
        lastname_input.setBackground(new Color(-1));
        Font lastname_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, lastname_input.getFont());
        if (lastname_inputFont != null) lastname_input.setFont(lastname_inputFont);
        AddPerson.add(lastname_input, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        suffix_input = new JTextField();
        Font suffix_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, suffix_input.getFont());
        if (suffix_inputFont != null) suffix_input.setFont(suffix_inputFont);
        AddPerson.add(suffix_input, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ap_submit = new JButton();
        Font ap_submitFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ap_submit.getFont());
        if (ap_submitFont != null) ap_submit.setFont(ap_submitFont);
        ap_submit.setText("Submit");
        AddPerson.add(ap_submit, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthdate_dd = new JComboBox();
        birthdate_dd.setBackground(new Color(-855310));
        Font birthdate_ddFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthdate_dd.getFont());
        if (birthdate_ddFont != null) birthdate_dd.setFont(birthdate_ddFont);
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        birthdate_dd.setModel(defaultComboBoxModel3);
        birthdate_dd.setToolTipText("Day");
        AddPerson.add(birthdate_dd, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        birthdate_yyyy = new JTextField();
        Font birthdate_yyyyFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, birthdate_yyyy.getFont());
        if (birthdate_yyyyFont != null) birthdate_yyyy.setFont(birthdate_yyyyFont);
        AddPerson.add(birthdate_yyyy, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        deathdate_yyyy = new JTextField();
        Font deathdate_yyyyFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_yyyy.getFont());
        if (deathdate_yyyyFont != null) deathdate_yyyy.setFont(deathdate_yyyyFont);
        AddPerson.add(deathdate_yyyy, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddRelationship = new JPanel();
        AddRelationship.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(14, 2, new Insets(0, 0, 0, 0), -1, -1));
        AddRelationship.setBackground(new Color(-3040113));
        AddRelationship.setPreferredSize(new Dimension(600, 478));
        TreeEdit.add(AddRelationship, "add_relation");
        AddRelationship.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Add A New Relationship", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, AddRelationship.getFont()), new Color(-16777216)));
        ar_cancel = new JButton();
        Font ar_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ar_cancel.getFont());
        if (ar_cancelFont != null) ar_cancel.setFont(ar_cancelFont);
        ar_cancel.setText("Cancel");
        AddRelationship.add(ar_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_mother_label = new JLabel();
        pID_mother_label.setEnabled(false);
        Font pID_mother_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_mother_label.getFont());
        if (pID_mother_labelFont != null) pID_mother_label.setFont(pID_mother_labelFont);
        pID_mother_label.setText("Mother pID:");
        AddRelationship.add(pID_mother_label, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_mother_input = new JFormattedTextField();
        pID_mother_input.setBackground(new Color(-1));
        pID_mother_input.setEnabled(false);
        Font pID_mother_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_mother_input.getFont());
        if (pID_mother_inputFont != null) pID_mother_input.setFont(pID_mother_inputFont);
        AddRelationship.add(pID_mother_input, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_father_label = new JLabel();
        pID_father_label.setEnabled(false);
        Font pID_father_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_father_label.getFont());
        if (pID_father_labelFont != null) pID_father_label.setFont(pID_father_labelFont);
        pID_father_label.setText("Father pID:");
        AddRelationship.add(pID_father_label, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_father_input = new JFormattedTextField();
        pID_father_input.setBackground(new Color(-1));
        pID_father_input.setEnabled(false);
        Font pID_father_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_father_input.getFont());
        if (pID_father_inputFont != null) pID_father_input.setFont(pID_father_inputFont);
        AddRelationship.add(pID_father_input, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        reltype_prompt = new JTextArea();
        reltype_prompt.setBackground(new Color(-3040113));
        reltype_prompt.setEditable(false);
        Font reltype_promptFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 18, reltype_prompt.getFont());
        if (reltype_promptFont != null) reltype_prompt.setFont(reltype_promptFont);
        reltype_prompt.setText("\nChoose the type of relationship to add");
        AddRelationship.add(reltype_prompt, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ar_submit = new JButton();
        ar_submit.setEnabled(false);
        Font ar_submitFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ar_submit.getFont());
        if (ar_submitFont != null) ar_submit.setFont(ar_submitFont);
        ar_submit.setText("Submit");
        AddRelationship.add(ar_submit, new com.intellij.uiDesigner.core.GridConstraints(13, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pID_child_input = new JFormattedTextField();
        pID_child_input.setBackground(new Color(-1));
        pID_child_input.setEnabled(false);
        Font pID_child_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_child_input.getFont());
        if (pID_child_inputFont != null) pID_child_input.setFont(pID_child_inputFont);
        AddRelationship.add(pID_child_input, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_child_label = new JLabel();
        pID_child_label.setEnabled(false);
        Font pID_child_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_child_label.getFont());
        if (pID_child_labelFont != null) pID_child_label.setFont(pID_child_labelFont);
        pID_child_label.setText("Child pID:");
        AddRelationship.add(pID_child_label, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_part1_label = new JLabel();
        pID_part1_label.setEnabled(false);
        Font pID_part1_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_part1_label.getFont());
        if (pID_part1_labelFont != null) pID_part1_label.setFont(pID_part1_labelFont);
        pID_part1_label.setText("Partner1 pID:");
        AddRelationship.add(pID_part1_label, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_part1_input = new JFormattedTextField();
        pID_part1_input.setEditable(true);
        pID_part1_input.setEnabled(false);
        Font pID_part1_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_part1_input.getFont());
        if (pID_part1_inputFont != null) pID_part1_input.setFont(pID_part1_inputFont);
        AddRelationship.add(pID_part1_input, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_part2_label = new JLabel();
        pID_part2_label.setEnabled(false);
        Font pID_part2_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_part2_label.getFont());
        if (pID_part2_labelFont != null) pID_part2_label.setFont(pID_part2_labelFont);
        pID_part2_label.setText("Partner2 pID:");
        AddRelationship.add(pID_part2_label, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startdate_label = new JLabel();
        startdate_label.setEnabled(false);
        Font startdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, startdate_label.getFont());
        if (startdate_labelFont != null) startdate_label.setFont(startdate_labelFont);
        startdate_label.setText("Start Date:");
        AddRelationship.add(startdate_label, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_part2_input = new JFormattedTextField();
        pID_part2_input.setEnabled(false);
        AddRelationship.add(pID_part2_input, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        select_child = new JRadioButton();
        Font select_childFont = this.$$$getFont$$$(null, -1, -1, select_child.getFont());
        if (select_childFont != null) select_child.setFont(select_childFont);
        select_child.setText("Add Child");
        AddRelationship.add(select_child, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        select_partner = new JRadioButton();
        select_partner.setText("Add Partnership");
        AddRelationship.add(select_partner, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 20), null, 0, false));
        startdate_input = new JFormattedTextField();
        startdate_input.setEnabled(false);
        AddRelationship.add(startdate_input, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        enddate_label = new JLabel();
        enddate_label.setEnabled(false);
        Font enddate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, enddate_label.getFont());
        if (enddate_labelFont != null) enddate_label.setFont(enddate_labelFont);
        enddate_label.setText("End Date:");
        AddRelationship.add(enddate_label, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enddate_input = new JFormattedTextField();
        enddate_input.setEnabled(false);
        Font enddate_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, enddate_input.getFont());
        if (enddate_inputFont != null) enddate_input.setFont(enddate_inputFont);
        AddRelationship.add(enddate_input, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        EditRelationship = new JPanel();
        EditRelationship.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        EditRelationship.setBackground(new Color(-3040113));
        EditRelationship.setPreferredSize(new Dimension(600, 400));
        TreeEdit.add(EditRelationship, "edit_relation");
        EditRelationship.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Edit A Relationship", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, EditRelationship.getFont()), new Color(-16777216)));
        er_cancel = new JButton();
        Font er_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, er_cancel.getFont());
        if (er_cancelFont != null) er_cancel.setFont(er_cancelFont);
        er_cancel.setText("Cancel");
        EditRelationship.add(er_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer12 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer12, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer13 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer13, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer14 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer14, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer15 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer15, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        rID_label = new JLabel();
        Font rID_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, rID_label.getFont());
        if (rID_labelFont != null) rID_label.setFont(rID_labelFont);
        rID_label.setText("rID:");
        EditRelationship.add(rID_label, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        current_rel = new JLabel();
        current_rel.setEnabled(false);
        Font current_relFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_rel.getFont());
        if (current_relFont != null) current_rel.setFont(current_relFont);
        current_rel.setText("Current Relationship:");
        EditRelationship.add(current_rel, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer16 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer16, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer17 = new com.intellij.uiDesigner.core.Spacer();
        EditRelationship.add(spacer17, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        rID_input = new JFormattedTextField();
        Font rID_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, rID_input.getFont());
        if (rID_inputFont != null) rID_input.setFont(rID_inputFont);
        EditRelationship.add(rID_input, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        current_rel_text = new JTextArea();
        current_rel_text.setBackground(new Color(-3040113));
        current_rel_text.setEditable(false);
        current_rel_text.setEnabled(false);
        Font current_rel_textFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_rel_text.getFont());
        if (current_rel_textFont != null) current_rel_text.setFont(current_rel_textFont);
        EditRelationship.add(current_rel_text, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        pID_p1_label = new JLabel();
        Font pID_p1_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_p1_label.getFont());
        if (pID_p1_labelFont != null) pID_p1_label.setFont(pID_p1_labelFont);
        pID_p1_label.setText("First Person pID:");
        EditRelationship.add(pID_p1_label, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_p1_input = new JFormattedTextField();
        Font pID_p1_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_p1_input.getFont());
        if (pID_p1_inputFont != null) pID_p1_input.setFont(pID_p1_inputFont);
        EditRelationship.add(pID_p1_input, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_p2_label = new JLabel();
        Font pID_p2_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_p2_label.getFont());
        if (pID_p2_labelFont != null) pID_p2_label.setFont(pID_p2_labelFont);
        pID_p2_label.setText("Second Person pID:");
        EditRelationship.add(pID_p2_label, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_p2_input = new JFormattedTextField();
        Font pID_p2_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_p2_input.getFont());
        if (pID_p2_inputFont != null) pID_p2_input.setFont(pID_p2_inputFont);
        EditRelationship.add(pID_p2_input, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_father_label.setLabelFor(pID_father_input);
        pID_child_label.setLabelFor(pID_child_input);
        pID_part2_label.setLabelFor(pID_part2_input);
        startdate_label.setLabelFor(startdate_input);
        enddate_label.setLabelFor(enddate_input);
        rID_label.setLabelFor(rID_input);
        current_rel.setLabelFor(current_rel_text);
        pID_p1_label.setLabelFor(pID_p1_input);
        pID_p2_label.setLabelFor(pID_p2_input);
        radioButtons = new ButtonGroup();
        radioButtons.add(select_child);
        radioButtons.add(select_partner);
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
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return TreeEdit;
    }

}
