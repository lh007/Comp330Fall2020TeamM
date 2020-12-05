package SwingUI;

import JavaClasses.DataPrep;
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
import java.io.*;
import java.util.Locale;

public class EntryPage {
    private JPanel EntryScreen;
    private JTextField promptText;
    private JButton importFileButton;
    private JButton viewExistingButton;

    private String userFile;
    private TreeGenealogy tg;

    public EntryPage() {
        // Adds a listener for button push "import file"
        importFileButton.addActionListener(new ActionListener() {
            @Override
            // Allows a user to select a file from their system
            public void actionPerformed(ActionEvent e) {
                try {
                    readFile();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        // Adds a listener for button "View Existing Trees"
        viewExistingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tg.getPeople());
            }
        });
    }

    public void readFile() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(EntryScreen);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            userFile = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + userFile);
        }
        String treeFile = (userFile);
        TreeGenealogy t = new TreeGenealogy(treeFile);
        tg = t;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("EntryPage");
        frame.setContentPane(new EntryPage().EntryScreen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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
        EntryScreen = new JPanel();
        EntryScreen.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        EntryScreen.setBackground(new Color(-5997967));
        EntryScreen.setForeground(new Color(-16777216));
        EntryScreen.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), "Welcome to the Geneology Tree App!", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.BELOW_TOP, this.$$$getFont$$$("Consolas", Font.BOLD, 20, EntryScreen.getFont()), new Color(-16777216)));
        promptText = new JTextField();
        promptText.setBackground(new Color(-5997967));
        promptText.setEditable(false);
        promptText.setEnabled(true);
        Font promptTextFont = this.$$$getFont$$$("Consolas", Font.BOLD, 18, promptText.getFont());
        if (promptTextFont != null) promptText.setFont(promptTextFont);
        promptText.setForeground(new Color(-16777216));
        promptText.setHorizontalAlignment(0);
        promptText.setText("What would you like to do?");
        promptText.setToolTipText("");
        EntryScreen.add(promptText, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        importFileButton = new JButton();
        importFileButton.setBackground(new Color(-2960686));
        importFileButton.setEnabled(true);
        Font importFileButtonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, importFileButton.getFont());
        if (importFileButtonFont != null) importFileButton.setFont(importFileButtonFont);
        importFileButton.setForeground(new Color(-16777216));
        importFileButton.setText("Create Tree from File");
        importFileButton.setToolTipText("Select a file to create a new tree");
        EntryScreen.add(importFileButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        viewExistingButton = new JButton();
        viewExistingButton.setBackground(new Color(-2960686));
        viewExistingButton.setEnabled(true);
        Font viewExistingButtonFont = this.$$$getFont$$$("Consolas", Font.PLAIN, 16, viewExistingButton.getFont());
        if (viewExistingButtonFont != null) viewExistingButton.setFont(viewExistingButtonFont);
        viewExistingButton.setText("View Existing Tree");
        viewExistingButton.setToolTipText("Visualize, Edit, or Search your tree");
        EntryScreen.add(viewExistingButton, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        EntryScreen.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        EntryScreen.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer3 = new com.intellij.uiDesigner.core.Spacer();
        EntryScreen.add(spacer3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
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
        return EntryScreen;
    }

}