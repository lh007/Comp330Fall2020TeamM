package SwingUI;

import JavaClasses.Person;
import JavaClasses.Relationship;
import JavaClasses.TreeGenealogy;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;
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
    //RootCard
    private JPanel RootCard;
    private JButton b_addPerson;
    private JButton b_addRelationship;
    private JButton b_updatePartnership;
    private JButton root_back;
    //AddPerson
    private JPanel AddPerson;
    private JButton ap_cancel;
    private JTextField guide_text;
    private JLabel firstname_label;
    private JLabel lastname_label;
    private JLabel suffix_label;
    private JLabel birthdate_label;
    private JLabel birthplace_label;
    private JLabel deathdate_label;
    private JLabel deathplace_label;
    private JTextField firstname_input;
    private JTextField lastname_input;
    private JTextField suffix_input;
    private JComboBox birthdate_mm;
    private JComboBox birthdate_dd;
    private JTextField birthdate_yyyy;
    private JTextField birthplace_input;
    private JComboBox deathdate_mm;
    private JComboBox deathdate_dd;
    private JTextField deathdate_yyyy;
    private JTextField deathplace_input;
    private JTextField required3;
    private JTextField required4;
    private JTextField required1;
    private JTextField required2;
    private JButton ap_submit;
    //AddRelationship
    private JPanel AddRelationship;
    private JButton ar_cancel;
    private JTextArea reltype_prompt;
    private ButtonGroup radioButtons;
    private JRadioButton select_child;
    private JLabel pID_parent1_label;
    private JLabel pID_parent2_label;
    private JLabel pID_child_label;
    private JTextField pID_parent1_input;
    private JTextField pID_parent2_input;
    private JTextField pID_child_input;
    private JRadioButton select_partner;
    private JLabel pID_partner1_label;
    private JLabel pID_partner2_label;
    private JLabel startdate_label;
    private JLabel enddate_label;
    private JLabel location_label;
    private JTextField pID_partner1_input;
    private JTextField pID_partner2_input;
    private JTextField startdate_input;
    private JTextField enddate_input;
    private JTextField location_input;
    private JTextField prompt1;
    private JTextField prompt2;
    private JButton ar_submit;
    //Edit Relationship
    private JPanel UpdatePartnership;
    private JButton up_cancel;
    private JLabel rID_label;
    private JLabel pID_marr1_label;
    private JLabel pID_marr2_label;
    private JTextField rID_input;
    private JTextField pID_marr1_input;
    private JTextField pID_marr2_input;
    private JButton check_button;
    private JLabel current_startdate_label;
    private JLabel current_enddate_label;
    private JLabel current_location_label;
    private JTextField current_startdate_text;
    private JTextField current_enddate_text;
    private JTextField current_location_text;
    private JTextField new_startdate_input;
    private JTextField new_enddate_input;
    private JTextField new_location_input;
    private JLabel new_startdate_label;
    private JTextField date_note;
    private JLabel new_enddate_label;
    private JButton up_submit;
    private JLabel new_location_label;
    private JTextField input_note;

    private JRadioButton buttonSelected;
    private CardLayout cardLayout = new CardLayout();

    public TreeEdit() {
        TreeEdit.setLayout(cardLayout);
        TreeEdit.add(RootCard, "Edit Options");
        TreeEdit.add(AddPerson, "Add Person");
        TreeEdit.add(AddRelationship, "Add Relationship");
        TreeEdit.add(UpdatePartnership, "Update Partnership");
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

        /* Action Listener for Update Partnership button */
        b_updatePartnership.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Update Partnership");
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
                firstname_input.setText("");
                lastname_input.setText("");
                suffix_input.setText("");
                birthdate_mm.setSelectedIndex(0);
                birthdate_dd.setSelectedIndex(0);
                birthdate_yyyy.setText("");
                birthplace_input.setText("");
                deathdate_mm.setSelectedIndex(0);
                deathdate_dd.setSelectedIndex(0);
                deathplace_input.setText("");
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
                if (firstName.equals("")) {
                    firstName = null;
                }
                String lastName = lastname_input.getText();
                if (lastName.equals("")) {
                    lastName = null;
                }
                String suffix = suffix_input.getText();
                if (suffix.equals("")) {
                    suffix = null;
                }
                String birthPlace = birthplace_input.getText();
                if (birthPlace.equals("")) {
                    birthPlace = null;
                }
                String deathPlace = deathplace_input.getText();
                if (deathPlace.equals("")) {
                    deathPlace = null;
                }

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
                // Error: Birth year is a required field
                if (birth_yyyy.equals("")) {
                    System.out.println("Error: Birth year is a required field");
                    return;
                }
                if (death_mm.equals("Unknown") || death_mm.equals("N/A")) {
                    death_mm = "";
                    par3 = "";
                }
                if (death_dd.equals("Unknown") || death_dd.equals("N/A")) {
                    death_dd = "";
                }
                if (death_yyyy.equals("")) {
                    death_yyyy = "";
                    par4 = "";
                }

                if (birth_mm.equals("") && birth_dd.equals("")) {
                    birthDate = birth_yyyy;
                } else {
                    birthDate = birth_mm + par1 + birth_dd + par2 + birth_yyyy;
                }
                if (death_mm.equals("") && death_dd.equals("") && death_yyyy.equals("")) {
                    deathDate = null;
                } else {
                    deathDate = death_mm + par3 + death_dd + par4 + death_yyyy;
                }

                // Add the new person to the tree
                Person p = new Person(pID, lastName, firstName, suffix, birthDate, birthPlace, deathDate, deathPlace);
                existingPeople.put(pID, p);
                updatePeople(existingPeople); //updates the global var for the tree
                System.out.println("Successfully added person: " + "\n" + p.toString());
                cardLayout.show(TreeEdit, "Edit Options"); //automatically return to root page
                firstname_input.setText("");
                lastname_input.setText("");
                suffix_input.setText("");
                birthdate_mm.setSelectedIndex(0);
                birthdate_dd.setSelectedIndex(0);
                birthdate_yyyy.setText("");
                birthplace_input.setText("");
                deathdate_mm.setSelectedIndex(0);
                deathdate_dd.setSelectedIndex(0);
                deathplace_input.setText("");
            }
        });

        /////// CARD: AddRelationship ///////
        /* Action Listener for Cancel button */
        ar_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Edit Options");
                pID_parent1_input.setText("");
                pID_parent2_input.setText("");
                pID_child_input.setText("");
                pID_partner1_input.setText("");
                pID_partner2_input.setText("");
                startdate_input.setText("");
                enddate_input.setText("");
                location_input.setText("");
            }
        });

        /* Action Listener for Select Child */
        select_child.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Hide partner select components
                pID_partner1_label.setEnabled(false);
                pID_partner1_input.setEnabled(false);
                pID_partner2_label.setEnabled(false);
                pID_partner2_input.setEnabled(false);
                startdate_label.setEnabled(false);
                startdate_input.setEnabled(false);
                prompt1.setEnabled(false);
                enddate_label.setEnabled(false);
                enddate_input.setEnabled(false);
                location_label.setEnabled(false);
                location_input.setEnabled(false);
                prompt2.setEnabled(false);
                //Show child select components
                pID_parent1_label.setEnabled(true);
                pID_parent1_input.setEnabled(true);
                pID_parent2_label.setEnabled(true);
                pID_parent2_input.setEnabled(true);
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
                //Hide child select components
                pID_parent1_label.setEnabled(false);
                pID_parent1_input.setEnabled(false);
                pID_parent2_label.setEnabled(false);
                pID_parent2_input.setEnabled(false);
                pID_child_label.setEnabled(false);
                pID_child_input.setEnabled(false);
                //Show partner select components
                pID_partner1_label.setEnabled(true);
                pID_partner1_input.setEnabled(true);
                pID_partner2_label.setEnabled(true);
                pID_partner2_input.setEnabled(true);
                startdate_label.setEnabled(true);
                startdate_input.setEnabled(true);
                prompt1.setEnabled(true);
                enddate_label.setEnabled(true);
                enddate_input.setEnabled(true);
                location_label.setEnabled(true);
                location_input.setEnabled(true);
                prompt2.setEnabled(true);
                ar_submit.setEnabled(true);
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

                /* Child Relationship Selected */
                if (buttonSelected.equals(select_child)) {
                    String par1_pID = pID_parent1_input.getText().toUpperCase(); //ensures "P"
                    String par2_pID = pID_parent2_input.getText().toUpperCase();
                    String child_pID = pID_child_input.getText().toUpperCase();
                    if (par1_pID.equals("")) {
                        par1_pID = null;
                    }
                    if (par2_pID.equals("")) {
                        par2_pID = null;
                    }
                    if (child_pID.equals("")) {
                        child_pID = null;
                    }

                    // Error: No parents entered
                    if (par1_pID == null && par2_pID == null) {
                        System.out.println("Error: At least one parent must be entered");
                        return;
                    }
                    // Error: No child entered
                    if (child_pID == null) {
                        System.out.println("Error: A child must be entered");
                        return;
                    }
                    // Error: Parent 1 does not exist in tree
                    if (!people.containsKey(par1_pID)) {
                        System.out.println("Error: pID entered for First Parent does not exist. " +
                                "Ensure the correct value was entered or create a new person first");
                        return;
                    }
                    // Error: Parent 2 does not exist in tree
                    if (!people.containsKey(par2_pID)) {
                        System.out.println("Error: pID entered for Second Parent does not exist. " +
                                "Ensure the correct value was entered or create a new person first");
                        return;
                    }
                    // Error: Child does not exist in tree
                    if (!people.containsKey(child_pID)) {
                        System.out.println("Error: pID entered for Child does not exist. " +
                                "Ensure the correct value was entered or create a new person first");
                        return;
                    }

                    /* Checkpoint: All inputs are valid */
                    // Generate rID
                    String rID = "";
                    int x = 1;
                    String current = "";
                    boolean found = false;
                    while (!found) {
                        current = "R" + String.valueOf(x);
                        if (relations.containsKey(current)) { //rID already exists
                            x += 1;
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
                    Person oneParent = null; //use in Relationship constructor if no par1 is entered
                    Person twoParent = null; //use in Relationship constructor if no par2 is entered

                    // Error: Child already belongs to a parent relationship
                    if (child.getChildOfR() != null) {
                        System.out.println("Error: Entered child already has a parent relationship registered,");
                        String existing_rID = child.getChildOfR(); //parents' rID
                        Relationship exists = relations.get(existing_rID); //get the existing relationship
                        System.out.println(exists.childSelect_toString(child));
                        return;
                    }
                    // Scenario 1: parent1 entered, parent2 not entered, no rID would exist
                    if (par1_pID != null && par2_pID == null) {
                        Person par1 = people.get(par1_pID); //Person object for par1
                        child.setChildOfR(rID); //method from Person class, set the child's parent
                        par1.addRelation(rID); //method from Person class, add the rID to par1's relationships
                        Relationship r = new Relationship(rID, par1, twoParent, startDate, endDate, marriageLocation);
                        r.addChild(child); //add the child to the relationship's list of children
                        relations.put(rID, r); //add to tree's relations
                        updateRelations(relations); //update the relations global var
                        System.out.println("Relationship successfully added: " + "\n" + r.childSelect_toString(child));
                        cardLayout.show(TreeEdit, "Edit Options"); //go back
                        pID_parent1_input.setText("");
                        pID_parent2_input.setText("");
                        pID_child_input.setText("");
                        pID_partner1_input.setText("");
                        pID_partner2_input.setText("");
                        startdate_input.setText("");
                        enddate_input.setText("");
                        location_input.setText("");
                        return;
                    }
                    // Scenario 2: parent2 entered, parent1 not entered, no rID would exist
                    else if (par2_pID != null && par1_pID == null) {
                        Person par2 = people.get(par2_pID);
                        child.setChildOfR(rID);
                        par2.addRelation(rID);
                        Relationship r = new Relationship(rID, oneParent, par2, startDate, endDate, marriageLocation);
                        r.addChild(child);
                        relations.put(rID, r);
                        updateRelations(relations);
                        System.out.println("Relationship successfully added: " + "\n" + r.childSelect_toString(child));
                        cardLayout.show(TreeEdit, "Edit Options");
                        pID_parent1_input.setText("");
                        pID_parent2_input.setText("");
                        pID_child_input.setText("");
                        pID_partner1_input.setText("");
                        pID_partner2_input.setText("");
                        startdate_input.setText("");
                        enddate_input.setText("");
                        location_input.setText("");
                        return;
                    }
                    // Scenario 3: Both parent1 and parent2 entered, rID may or may not exist already
                    else {
                        Person par1 = people.get(par1_pID);
                        Person par2 = people.get(par2_pID);
                        Set<String> par1Relations = par1.getRelations(); //method from Person class
                        Set<String> par2Relations = par2.getRelations();
                        boolean exists = false;

                        // Check if a partnership exists between the parents
                        for (String ID : par1Relations) { //traverse through par1's relationships
                            if (par2Relations.contains(ID)) { //checking for a common relationship in par2's
                                rID = ID; //common relationship found
                                exists = true;
                            } else {
                                continue; //not found yet
                            }
                        }
                        // Error: No partnership exists
                        if (!exists) {
                            System.out.println("Error: No partnership found between entered mother and father." +
                                    "Ensure values are correct or add a new partnership first");
                            return;
                        }
                        /* Checkpoint: Partnership exists */
                        else {
                            Relationship r = relations.get(rID); //pull their relationship from the tree
                            List<Person> children = r.getChildren(); //method from Person, get their existing children
                            // Error: The child relationship already exists
                            if (children.contains(child_pID)) {
                                System.out.println("Error: A relationship for the entered parent(s) and child already exists,");
                                String existing_rID = child.getChildOfR();
                                Relationship existing_rel = relations.get(existing_rID);
                                System.out.println(existing_rel.childSelect_toString(child)); //print existing relationship
                                return;
                            }
                            /* Checkpoint: The child relationship does not yet exist */
                            else {
                                children.add(child); //Add the child to parents' children list
                                child.setChildOfR(rID); //Set the child's parents
                                System.out.println("Relationship successfully added: " + r.childSelect_toString(child));
                                cardLayout.show(TreeEdit, "Edit Options");
                                pID_parent1_input.setText("");
                                pID_parent2_input.setText("");
                                pID_child_input.setText("");
                                pID_partner1_input.setText("");
                                pID_partner2_input.setText("");
                                startdate_input.setText("");
                                enddate_input.setText("");
                                location_input.setText("");
                                return;
                            }
                        } //end checkpoint: a partnership exists
                    } //end case: determine which parents entered
                } //end child select

                /* Partner Relationship Selected
                        Still declared:
                     (TreeGenealogy) familyTree //the tree made from file input
                     (Map<String,Person>) people //list of all people in the tree
                     (Map<String,Relationship> relations //list of all relationships in the tree
                */
                if (buttonSelected.equals(select_partner)) {
                    String p1_pID = pID_partner1_input.getText().toUpperCase();
                    String p2_pID = pID_partner2_input.getText().toUpperCase();
                    String startDate = startdate_input.getText();
                    String endDate = enddate_input.getText();
                    String location = location_input.getText();
                    if (startDate.equals("")) {
                        startDate = null;
                    }
                    // Error: incorrect date format
                    else if (startDate.length() != 10) {
                        System.out.println("Error: Start Date has incorrect format, " +
                                "refer to the template next to the text field");
                        return;
                    }
                    if (endDate.equals("")) {
                        endDate = null;
                    }
                    // Error: incorrect date format
                    else if (endDate.length() != 10) {
                        System.out.println("Error: End Date has incorrect format, " +
                                "refer to the template next to the text field");
                        return;
                    }
                    if (location.equals("")) {
                        location = null;
                    }

                    // Error: Partner 1 not entered
                    if (p1_pID.equals("")) {
                        System.out.println("Error: Partner 1 pID is a required field");
                        return;
                    }
                    // Error: Partner 2 not entered
                    else if (p2_pID.equals("")) {
                        System.out.println("Error: Partner 2 pID is a required field");
                        return;
                    }
                    /* Checkpoint: Both pID values are entered */
                    else {
                        // Check to see if the pID values exist
                        if (!people.containsKey(p1_pID)) { // Error: Partner 1 does not exist in tree
                            System.out.println("Error: Partner 1 not found. " +
                                    "Ensure the right value was entered or add a new person first");
                            return;
                        }
                        // p1 exists, check p2
                        else if (!people.containsKey(p2_pID)) { // Error: Partner 2 does not exist in tree
                            System.out.println("Error: Partner 2 not found. " +
                                    "Ensure the right value was entered or add a new person first");
                            return;
                        }
                        /* Checkpoint: Both partners exist */
                        else {
                            Person p1_obj = people.get(p1_pID);
                            Person p2_obj = people.get(p2_pID);
                            // Check to see if there is already a relationship between them
                            Set<String> p1_relations = p1_obj.getRelations();
                            Set<String> p2_relations = p2_obj.getRelations();
                            boolean rel_found = false;
                            for (String r : p1_relations) {
                                if (p2_relations.contains(r)) { // Error: An existing relationship was found
                                    rel_found = true;
                                    System.out.println("Error: A relationship between the two entered people already exists,");
                                    Relationship rel = getRelations().get(r); //get the existing relationship
                                    System.out.println(rel.partnerSelect_toString()); //print it
                                    break; //break for loop
                                }
                            }
                            //Ensures the code exits if a relationship is found
                            if (rel_found) {
                                return;
                            }
                            /* Checkpoint: No existing relationship found */
                            else {
                                // Generate an rID
                                String rID = "";
                                int x = 1;
                                String current = "";
                                boolean id_found = false;
                                while (!id_found) {
                                    current = "R" + String.valueOf(x);
                                    if (relations.containsKey(current)) { //rID already exists
                                        x += 1;
                                    } else { //found an unused rID
                                        rID = current;
                                        id_found = true; //break loop
                                    }
                                }
                                // Create the new relationship
                                Relationship r = new Relationship(rID, p1_obj, p2_obj, startDate, endDate, location);
                                p1_relations.add(rID); //add rID to partner1's list
                                p2_relations.add(rID); //add rID to partner2's list
                                relations.put(rID, r); //add to global var relations
                                updateRelations(relations); //update global var as well
                                System.out.println("Relationship added successfully: " + "\n" + r.partnerSelect_toString());
                                cardLayout.show(TreeEdit, "Edit Options");
                                pID_parent1_input.setText("");
                                pID_parent2_input.setText("");
                                pID_child_input.setText("");
                                pID_partner1_input.setText("");
                                pID_partner2_input.setText("");
                                startdate_input.setText("");
                                enddate_input.setText("");
                                location_input.setText("");
                            }
                        } //end checkpoint: both partners exist
                    } //end checkpoint: both pID values are entered
                } //end select partner
            } //end of action event
        });

        /////// CARD: Update Partnership ///////
        /* Action Listener for Cancel button */
        up_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(TreeEdit, "Edit Options");
                rID_input.setText("");
                pID_marr1_input.setText("");
                pID_marr2_input.setText("");
                current_startdate_text.setText("");
                current_enddate_text.setText("");
                current_location_text.setText("");
                new_startdate_input.setText("");
                new_enddate_input.setText("");
                new_location_input.setText("");
            }
        });
        /* Action Listener for Check button */
        check_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeGenealogy familyTree = getTree();
                Map<String, Person> people = getPeople();
                Map<String, Relationship> relations = getRelations();
                String rID = rID_input.getText().toUpperCase();
                String pID_1 = pID_marr1_input.getText().toUpperCase();
                String pID_2 = pID_marr2_input.getText().toUpperCase();
                // Make sure none of the fields are empty
                // Error: There is an empty field
                if (rID.equals("")) {
                    System.out.println("Error: rID is a requried field");
                    return;
                }
                if (pID_1.equals("")) {
                    System.out.println("Error: First Person pID is a required field");
                    return;
                }
                if (pID_2.equals("")) {
                    System.out.println("Error: Second Person pID is a required field");
                    return;
                }
                // Make sure the IDs exist
                // Error: An entered ID does not exist
                if (!relations.containsKey(rID)) {
                    System.out.println("Error: Cannot find relationship from entered rID. " +
                            "Ensure the value is correct, or create a new relationship first");
                    return;
                }
                if (!people.containsKey(pID_1)) {
                    System.out.println("Error: Cannot find First Person. Ensure the pID value is correct, " +
                            "or create a new person first");
                    return;
                }
                if (!people.containsKey(pID_2)) {
                    System.out.println("Error: Cannot find Second Person. Ensure the pID value is correct, " +
                            "or create a new person first");
                    return;
                }
                // Make sure both people have a common partnership
                Person per1 = people.get(pID_1);
                Person per2 = people.get(pID_2);
                Set<String> p1_rel = per1.getRelations();
                Set<String> p2_rel = per2.getRelations();
                boolean rel_exists = false;
                for (String s : p1_rel) {
                    if (p2_rel.contains(s)) {
                        rel_exists = true;
                        break;
                    }
                }
                // Error: Partnership does not exist
                if (!rel_exists) {
                    System.out.println("Error: No partnership found between Person One and Person Two. " +
                            "Ensure the values are correct, or create a new relationship first");
                    return;
                }
                /* Checkpoint: IDs are valid and a partnership was found */
                System.out.println("Relationship found");
                /* Get the current values of the relationship */
                Relationship r = familyTree.getRelationship(rID);
                String curr_start = r.getStartDate();
                String curr_end = r.getEndDate();
                String curr_location = r.getMarriageLocation();
                // Display the current values on the screen
                current_startdate_label.setEnabled(true);
                current_startdate_text.setEnabled(true);
                current_startdate_text.setText(curr_start);
                current_enddate_label.setEnabled(true);
                current_enddate_text.setEnabled(true);
                current_enddate_text.setText(curr_end);
                current_location_label.setEnabled(true);
                current_location_text.setEnabled(true);
                current_location_text.setText(curr_location);
                /* Allow user to input new values and submit changed */
                new_startdate_label.setEnabled(true);
                new_startdate_input.setEnabled(true);
                new_enddate_label.setEnabled(true);
                new_enddate_input.setEnabled(true);
                new_location_label.setEnabled(true);
                new_location_input.setEnabled(true);
                date_note.setEnabled(true);
                input_note.setEnabled(true);
                up_submit.setEnabled(true);
                // Input checks and relationship update will be done in Submit listener
            }
        });
        /* Action Listener for Submit button */
        up_submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreeGenealogy familyTree = getTree();
                Map<String, Person> people = getPeople();
                Map<String, Relationship> relations = getRelations();
                String rID = rID_input.getText().toUpperCase();
                Relationship r = relations.get(rID);
                String new_start = new_startdate_input.getText();
                String new_end = new_enddate_input.getText();
                String new_location = new_location_input.getText();
                // Error: incorrect date formats
                if (new_start.length() != 10) {
                    System.out.println("Error: Incorrect format for Start Date");
                    return;
                }
                if (new_end.length() != 10) {
                    System.out.println("Error: Incorrect format for End Date");
                    return;
                }
                // Check for values to change
                if (!new_start.equals("")) {
                    r.setStartDate(new_start);
                }
                if (!new_end.equals("")) {
                    r.setEndDate(new_end);
                }
                if (!new_location.equals("")) {
                    r.setMarriageLocation(new_location);
                }
                System.out.println("Relationship successfully updated: ");
                System.out.println("Start Date: " + r.getStartDate());
                System.out.println("End Date: " + r.getEndDate());
                System.out.println("Marriage Location: " + r.getMarriageLocation());
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
        b_updatePartnership = new JButton();
        Font b_updatePartnershipFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, b_updatePartnership.getFont());
        if (b_updatePartnershipFont != null) b_updatePartnership.setFont(b_updatePartnershipFont);
        b_updatePartnership.setText("Update Partnership");
        b_updatePartnership.setToolTipText("Edit an existing partnership between two people");
        RootCard.add(b_updatePartnership, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        AddPerson.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 5, new Insets(0, 0, 0, 0), -1, -1));
        AddPerson.setBackground(new Color(-3040113));
        AddPerson.setPreferredSize(new Dimension(700, 500));
        TreeEdit.add(AddPerson, "add_person");
        AddPerson.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Add A New Person", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, AddPerson.getFont()), new Color(-16777216)));
        ap_cancel = new JButton();
        Font ap_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ap_cancel.getFont());
        if (ap_cancelFont != null) ap_cancel.setFont(ap_cancelFont);
        ap_cancel.setHorizontalTextPosition(11);
        ap_cancel.setText("Cancel");
        AddPerson.add(ap_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
        AddPerson.add(birthplace_input, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
        deathplace_input = new JTextField();
        deathplace_input.setBackground(new Color(-1));
        Font deathplace_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathplace_input.getFont());
        if (deathplace_inputFont != null) deathplace_input.setFont(deathplace_inputFont);
        deathplace_input.setToolTipText("e.g. Dallas, TX");
        AddPerson.add(deathplace_input, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
        AddPerson.add(firstname_input, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        lastname_input = new JTextField();
        lastname_input.setBackground(new Color(-1));
        Font lastname_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, lastname_input.getFont());
        if (lastname_inputFont != null) lastname_input.setFont(lastname_inputFont);
        AddPerson.add(lastname_input, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        suffix_input = new JTextField();
        Font suffix_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, suffix_input.getFont());
        if (suffix_inputFont != null) suffix_input.setFont(suffix_inputFont);
        AddPerson.add(suffix_input, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        guide_text = new JTextField();
        guide_text.setBackground(new Color(-3040113));
        guide_text.setEditable(false);
        Font guide_textFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, guide_text.getFont());
        if (guide_textFont != null) guide_text.setFont(guide_textFont);
        guide_text.setForeground(new Color(-16777216));
        guide_text.setText("Fill in all fields that apply. Leave unknown fields blank (except Required)");
        AddPerson.add(guide_text, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
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
        AddPerson.add(birthdate_yyyy, new com.intellij.uiDesigner.core.GridConstraints(4, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        deathdate_dd = new JComboBox();
        deathdate_dd.setBackground(new Color(-855310));
        Font deathdate_ddFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_dd.getFont());
        if (deathdate_ddFont != null) deathdate_dd.setFont(deathdate_ddFont);
        deathdate_dd.setToolTipText("Day");
        AddPerson.add(deathdate_dd, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deathdate_yyyy = new JTextField();
        Font deathdate_yyyyFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, deathdate_yyyy.getFont());
        if (deathdate_yyyyFont != null) deathdate_yyyy.setFont(deathdate_yyyyFont);
        AddPerson.add(deathdate_yyyy, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(100, -1), null, 0, false));
        ap_submit = new JButton();
        Font ap_submitFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ap_submit.getFont());
        if (ap_submitFont != null) ap_submit.setFont(ap_submitFont);
        ap_submit.setText("Submit");
        AddPerson.add(ap_submit, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        required3 = new JTextField();
        required3.setBackground(new Color(-3040113));
        required3.setEditable(false);
        Font required3Font = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, required3.getFont());
        if (required3Font != null) required3.setFont(required3Font);
        required3.setText("MM/DD/YYYY, Required");
        AddPerson.add(required3, new com.intellij.uiDesigner.core.GridConstraints(4, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        required4 = new JTextField();
        required4.setBackground(new Color(-3040113));
        required4.setEditable(false);
        Font required4Font = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, required4.getFont());
        if (required4Font != null) required4.setFont(required4Font);
        required4.setText("MM/DD/YYYY");
        AddPerson.add(required4, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        required1 = new JTextField();
        required1.setBackground(new Color(-3040113));
        required1.setEditable(false);
        Font required1Font = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, required1.getFont());
        if (required1Font != null) required1.setFont(required1Font);
        required1.setText("Required");
        AddPerson.add(required1, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        required2 = new JTextField();
        required2.setBackground(new Color(-3040113));
        required2.setEditable(false);
        Font required2Font = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, required2.getFont());
        if (required2Font != null) required2.setFont(required2Font);
        required2.setText("Required");
        AddPerson.add(required2, new com.intellij.uiDesigner.core.GridConstraints(2, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        AddRelationship = new JPanel();
        AddRelationship.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(15, 3, new Insets(0, 0, 0, 0), -1, -1));
        AddRelationship.setBackground(new Color(-3040113));
        AddRelationship.setPreferredSize(new Dimension(700, 600));
        TreeEdit.add(AddRelationship, "add_relation");
        AddRelationship.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Add A New Relationship", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, AddRelationship.getFont()), new Color(-16777216)));
        ar_cancel = new JButton();
        Font ar_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ar_cancel.getFont());
        if (ar_cancelFont != null) ar_cancel.setFont(ar_cancelFont);
        ar_cancel.setText("Cancel");
        AddRelationship.add(ar_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTHWEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_parent1_label = new JLabel();
        pID_parent1_label.setEnabled(false);
        Font pID_parent1_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_parent1_label.getFont());
        if (pID_parent1_labelFont != null) pID_parent1_label.setFont(pID_parent1_labelFont);
        pID_parent1_label.setText("First Parent pID:");
        AddRelationship.add(pID_parent1_label, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_parent1_input = new JTextField();
        pID_parent1_input.setBackground(new Color(-1));
        pID_parent1_input.setEnabled(false);
        Font pID_parent1_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_parent1_input.getFont());
        if (pID_parent1_inputFont != null) pID_parent1_input.setFont(pID_parent1_inputFont);
        AddRelationship.add(pID_parent1_input, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_parent2_label = new JLabel();
        pID_parent2_label.setEnabled(false);
        Font pID_parent2_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_parent2_label.getFont());
        if (pID_parent2_labelFont != null) pID_parent2_label.setFont(pID_parent2_labelFont);
        pID_parent2_label.setText("Second Parent pID:");
        AddRelationship.add(pID_parent2_label, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_parent2_input = new JTextField();
        pID_parent2_input.setBackground(new Color(-1));
        pID_parent2_input.setEnabled(false);
        Font pID_parent2_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_parent2_input.getFont());
        if (pID_parent2_inputFont != null) pID_parent2_input.setFont(pID_parent2_inputFont);
        AddRelationship.add(pID_parent2_input, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer6 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer6, new com.intellij.uiDesigner.core.GridConstraints(13, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer7 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer7, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        reltype_prompt = new JTextArea();
        reltype_prompt.setBackground(new Color(-3040113));
        reltype_prompt.setEditable(false);
        Font reltype_promptFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 18, reltype_prompt.getFont());
        if (reltype_promptFont != null) reltype_prompt.setFont(reltype_promptFont);
        reltype_prompt.setText("Choose the type of relationship to add\nFill in all fields that apply");
        AddRelationship.add(reltype_prompt, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        ar_submit = new JButton();
        ar_submit.setEnabled(false);
        Font ar_submitFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, ar_submit.getFont());
        if (ar_submitFont != null) ar_submit.setFont(ar_submitFont);
        ar_submit.setText("Submit");
        AddRelationship.add(ar_submit, new com.intellij.uiDesigner.core.GridConstraints(14, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer8 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer8, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pID_child_input = new JTextField();
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
        pID_partner1_label = new JLabel();
        pID_partner1_label.setEnabled(false);
        Font pID_partner1_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_partner1_label.getFont());
        if (pID_partner1_labelFont != null) pID_partner1_label.setFont(pID_partner1_labelFont);
        pID_partner1_label.setText("Partner1 pID:");
        AddRelationship.add(pID_partner1_label, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_partner1_input = new JTextField();
        pID_partner1_input.setBackground(new Color(-1));
        pID_partner1_input.setEditable(true);
        pID_partner1_input.setEnabled(false);
        Font pID_partner1_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_partner1_input.getFont());
        if (pID_partner1_inputFont != null) pID_partner1_input.setFont(pID_partner1_inputFont);
        AddRelationship.add(pID_partner1_input, new com.intellij.uiDesigner.core.GridConstraints(8, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_partner2_label = new JLabel();
        pID_partner2_label.setEnabled(false);
        Font pID_partner2_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_partner2_label.getFont());
        if (pID_partner2_labelFont != null) pID_partner2_label.setFont(pID_partner2_labelFont);
        pID_partner2_label.setText("Partner2 pID:");
        AddRelationship.add(pID_partner2_label, new com.intellij.uiDesigner.core.GridConstraints(9, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startdate_label = new JLabel();
        startdate_label.setEnabled(false);
        Font startdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, startdate_label.getFont());
        if (startdate_labelFont != null) startdate_label.setFont(startdate_labelFont);
        startdate_label.setText("Start Date:");
        AddRelationship.add(startdate_label, new com.intellij.uiDesigner.core.GridConstraints(10, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_partner2_input = new JTextField();
        pID_partner2_input.setBackground(new Color(-1));
        pID_partner2_input.setEnabled(false);
        Font pID_partner2_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_partner2_input.getFont());
        if (pID_partner2_inputFont != null) pID_partner2_input.setFont(pID_partner2_inputFont);
        AddRelationship.add(pID_partner2_input, new com.intellij.uiDesigner.core.GridConstraints(9, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        select_child = new JRadioButton();
        Font select_childFont = this.$$$getFont$$$(null, -1, -1, select_child.getFont());
        if (select_childFont != null) select_child.setFont(select_childFont);
        select_child.setText("Add Child");
        AddRelationship.add(select_child, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        select_partner = new JRadioButton();
        select_partner.setText("Add Partnership");
        AddRelationship.add(select_partner, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(151, 20), null, 0, false));
        startdate_input = new JTextField();
        startdate_input.setBackground(new Color(-1));
        startdate_input.setEnabled(false);
        Font startdate_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, startdate_input.getFont());
        if (startdate_inputFont != null) startdate_input.setFont(startdate_inputFont);
        AddRelationship.add(startdate_input, new com.intellij.uiDesigner.core.GridConstraints(10, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        enddate_label = new JLabel();
        enddate_label.setEnabled(false);
        Font enddate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, enddate_label.getFont());
        if (enddate_labelFont != null) enddate_label.setFont(enddate_labelFont);
        enddate_label.setText("End Date:");
        AddRelationship.add(enddate_label, new com.intellij.uiDesigner.core.GridConstraints(11, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        enddate_input = new JTextField();
        enddate_input.setBackground(new Color(-1));
        enddate_input.setEnabled(false);
        Font enddate_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, enddate_input.getFont());
        if (enddate_inputFont != null) enddate_input.setFont(enddate_inputFont);
        AddRelationship.add(enddate_input, new com.intellij.uiDesigner.core.GridConstraints(11, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer9 = new com.intellij.uiDesigner.core.Spacer();
        AddRelationship.add(spacer9, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        prompt1 = new JTextField();
        prompt1.setBackground(new Color(-3040113));
        prompt1.setEditable(false);
        prompt1.setEnabled(false);
        Font prompt1Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 14, prompt1.getFont());
        if (prompt1Font != null) prompt1.setFont(prompt1Font);
        prompt1.setText("MM/DD/YYYY");
        AddRelationship.add(prompt1, new com.intellij.uiDesigner.core.GridConstraints(10, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        prompt2 = new JTextField();
        prompt2.setBackground(new Color(-3040113));
        prompt2.setEditable(false);
        prompt2.setEnabled(false);
        Font prompt2Font = this.$$$getFont$$$("Consolas", Font.ITALIC, 14, prompt2.getFont());
        if (prompt2Font != null) prompt2.setFont(prompt2Font);
        prompt2.setText("MM/DD/YYYY");
        AddRelationship.add(prompt2, new com.intellij.uiDesigner.core.GridConstraints(11, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        location_label = new JLabel();
        location_label.setEnabled(false);
        Font location_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, location_label.getFont());
        if (location_labelFont != null) location_label.setFont(location_labelFont);
        location_label.setText("Marriage Location:");
        AddRelationship.add(location_label, new com.intellij.uiDesigner.core.GridConstraints(12, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        location_input = new JTextField();
        location_input.setEnabled(false);
        Font location_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, location_input.getFont());
        if (location_inputFont != null) location_input.setFont(location_inputFont);
        AddRelationship.add(location_input, new com.intellij.uiDesigner.core.GridConstraints(12, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        UpdatePartnership = new JPanel();
        UpdatePartnership.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        UpdatePartnership.setBackground(new Color(-3040113));
        UpdatePartnership.setPreferredSize(new Dimension(600, 400));
        TreeEdit.add(UpdatePartnership, "update_partnership");
        UpdatePartnership.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Update A Partnership", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, UpdatePartnership.getFont()), new Color(-16777216)));
        up_cancel = new JButton();
        Font up_cancelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, up_cancel.getFont());
        if (up_cancelFont != null) up_cancel.setFont(up_cancelFont);
        up_cancel.setText("Cancel");
        UpdatePartnership.add(up_cancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer10 = new com.intellij.uiDesigner.core.Spacer();
        UpdatePartnership.add(spacer10, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer11 = new com.intellij.uiDesigner.core.Spacer();
        UpdatePartnership.add(spacer11, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer12 = new com.intellij.uiDesigner.core.Spacer();
        UpdatePartnership.add(spacer12, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        check_button = new JButton();
        Font check_buttonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, check_button.getFont());
        if (check_buttonFont != null) check_button.setFont(check_buttonFont);
        check_button.setText("Check");
        UpdatePartnership.add(check_button, new com.intellij.uiDesigner.core.GridConstraints(4, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        current_location_label = new JLabel();
        current_location_label.setEnabled(false);
        Font current_location_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_location_label.getFont());
        if (current_location_labelFont != null) current_location_label.setFont(current_location_labelFont);
        current_location_label.setText("Current Marriage Location:");
        UpdatePartnership.add(current_location_label, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        current_enddate_label = new JLabel();
        current_enddate_label.setEnabled(false);
        Font current_enddate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_enddate_label.getFont());
        if (current_enddate_labelFont != null) current_enddate_label.setFont(current_enddate_labelFont);
        current_enddate_label.setText("Current End Date:");
        UpdatePartnership.add(current_enddate_label, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        current_startdate_label = new JLabel();
        current_startdate_label.setEnabled(false);
        Font current_startdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_startdate_label.getFont());
        if (current_startdate_labelFont != null) current_startdate_label.setFont(current_startdate_labelFont);
        current_startdate_label.setText("Current Start Date:");
        UpdatePartnership.add(current_startdate_label, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_marr1_label = new JLabel();
        Font pID_marr1_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_marr1_label.getFont());
        if (pID_marr1_labelFont != null) pID_marr1_label.setFont(pID_marr1_labelFont);
        pID_marr1_label.setText("First Person pID:");
        UpdatePartnership.add(pID_marr1_label, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rID_label = new JLabel();
        Font rID_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, rID_label.getFont());
        if (rID_labelFont != null) rID_label.setFont(rID_labelFont);
        rID_label.setText("rID:");
        UpdatePartnership.add(rID_label, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        pID_marr2_label = new JLabel();
        Font pID_marr2_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_marr2_label.getFont());
        if (pID_marr2_labelFont != null) pID_marr2_label.setFont(pID_marr2_labelFont);
        pID_marr2_label.setText("Second Person pID:");
        UpdatePartnership.add(pID_marr2_label, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rID_input = new JTextField();
        Font rID_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, rID_input.getFont());
        if (rID_inputFont != null) rID_input.setFont(rID_inputFont);
        UpdatePartnership.add(rID_input, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_marr1_input = new JTextField();
        Font pID_marr1_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_marr1_input.getFont());
        if (pID_marr1_inputFont != null) pID_marr1_input.setFont(pID_marr1_inputFont);
        UpdatePartnership.add(pID_marr1_input, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_marr2_input = new JTextField();
        Font pID_marr2_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, pID_marr2_input.getFont());
        if (pID_marr2_inputFont != null) pID_marr2_input.setFont(pID_marr2_inputFont);
        UpdatePartnership.add(pID_marr2_input, new com.intellij.uiDesigner.core.GridConstraints(3, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        current_startdate_text = new JTextField();
        current_startdate_text.setBackground(new Color(-3040113));
        current_startdate_text.setEditable(false);
        current_startdate_text.setEnabled(false);
        Font current_startdate_textFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_startdate_text.getFont());
        if (current_startdate_textFont != null) current_startdate_text.setFont(current_startdate_textFont);
        current_startdate_text.setToolTipText("");
        UpdatePartnership.add(current_startdate_text, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        current_enddate_text = new JTextField();
        current_enddate_text.setBackground(new Color(-3040113));
        current_enddate_text.setEditable(false);
        current_enddate_text.setEnabled(false);
        Font current_enddate_textFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_enddate_text.getFont());
        if (current_enddate_textFont != null) current_enddate_text.setFont(current_enddate_textFont);
        current_enddate_text.setToolTipText("");
        UpdatePartnership.add(current_enddate_text, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        current_location_text = new JTextField();
        current_location_text.setBackground(new Color(-3040113));
        current_location_text.setEditable(false);
        current_location_text.setEnabled(false);
        Font current_location_textFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, current_location_text.getFont());
        if (current_location_textFont != null) current_location_text.setFont(current_location_textFont);
        UpdatePartnership.add(current_location_text, new com.intellij.uiDesigner.core.GridConstraints(7, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        new_startdate_label = new JLabel();
        new_startdate_label.setEnabled(false);
        Font new_startdate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_startdate_label.getFont());
        if (new_startdate_labelFont != null) new_startdate_label.setFont(new_startdate_labelFont);
        new_startdate_label.setText("New Start Date:");
        UpdatePartnership.add(new_startdate_label, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        new_enddate_label = new JLabel();
        new_enddate_label.setEnabled(false);
        Font new_enddate_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_enddate_label.getFont());
        if (new_enddate_labelFont != null) new_enddate_label.setFont(new_enddate_labelFont);
        new_enddate_label.setText("New End Date:");
        UpdatePartnership.add(new_enddate_label, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        new_location_label = new JLabel();
        new_location_label.setEnabled(false);
        Font new_location_labelFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_location_label.getFont());
        if (new_location_labelFont != null) new_location_label.setFont(new_location_labelFont);
        new_location_label.setText("New Marriage Location:");
        UpdatePartnership.add(new_location_label, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        new_startdate_input = new JTextField();
        new_startdate_input.setEnabled(false);
        Font new_startdate_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_startdate_input.getFont());
        if (new_startdate_inputFont != null) new_startdate_input.setFont(new_startdate_inputFont);
        new_startdate_input.setToolTipText("MM/DD/YYYY");
        UpdatePartnership.add(new_startdate_input, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        new_enddate_input = new JTextField();
        new_enddate_input.setEnabled(false);
        Font new_enddate_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_enddate_input.getFont());
        if (new_enddate_inputFont != null) new_enddate_input.setFont(new_enddate_inputFont);
        new_enddate_input.setToolTipText("MM/DD/YYYY");
        UpdatePartnership.add(new_enddate_input, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        new_location_input = new JTextField();
        new_location_input.setEnabled(false);
        Font new_location_inputFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, new_location_input.getFont());
        if (new_location_inputFont != null) new_location_input.setFont(new_location_inputFont);
        new_location_input.setToolTipText("MM/DD/YYYY");
        UpdatePartnership.add(new_location_input, new com.intellij.uiDesigner.core.GridConstraints(7, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        date_note = new JTextField();
        date_note.setBackground(new Color(-3040113));
        date_note.setEditable(false);
        date_note.setEnabled(false);
        Font date_noteFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, date_note.getFont());
        if (date_noteFont != null) date_note.setFont(date_noteFont);
        date_note.setText("Dates must be in the format MM/DD/YYYY");
        UpdatePartnership.add(date_note, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        up_submit = new JButton();
        up_submit.setEnabled(false);
        Font up_submitFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 14, up_submit.getFont());
        if (up_submitFont != null) up_submit.setFont(up_submitFont);
        up_submit.setText("Submit");
        UpdatePartnership.add(up_submit, new com.intellij.uiDesigner.core.GridConstraints(8, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        input_note = new JTextField();
        input_note.setBackground(new Color(-3040113));
        input_note.setEditable(false);
        input_note.setEnabled(false);
        Font input_noteFont = this.$$$getFont$$$("Consolas", Font.BOLD | Font.ITALIC, 14, input_note.getFont());
        if (input_noteFont != null) input_note.setFont(input_noteFont);
        input_note.setText("Leave unwanted 'New' fields blank");
        UpdatePartnership.add(input_note, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pID_parent1_label.setLabelFor(pID_parent1_input);
        pID_parent2_label.setLabelFor(pID_parent2_input);
        pID_child_label.setLabelFor(pID_child_input);
        pID_partner1_label.setLabelFor(pID_partner1_input);
        pID_partner2_label.setLabelFor(pID_partner2_input);
        startdate_label.setLabelFor(startdate_input);
        enddate_label.setLabelFor(enddate_input);
        location_label.setLabelFor(location_input);
        current_location_label.setLabelFor(current_location_text);
        current_enddate_label.setLabelFor(current_enddate_text);
        current_startdate_label.setLabelFor(current_startdate_text);
        pID_marr1_label.setLabelFor(pID_marr1_input);
        rID_label.setLabelFor(rID_input);
        pID_marr2_label.setLabelFor(pID_marr2_input);
        new_startdate_label.setLabelFor(new_startdate_input);
        new_enddate_label.setLabelFor(new_enddate_input);
        new_location_label.setLabelFor(new_location_input);
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
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return TreeEdit;
    }

}
