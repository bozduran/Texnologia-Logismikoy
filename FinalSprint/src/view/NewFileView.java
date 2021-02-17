/*
 * Created by JFormDesigner on Thu Feb 27 18:04:51 EET 2020
 */

package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author unknown
 */
public class NewFileView extends JDialog {

    public NewFileView(Window owner) {
        super(owner);
        initComponents();
    }

    private void createButtonActionPerformed(ActionEvent e) {

        if (textFieldTitle.isValid() && textFieldAuthor.isValid()){
            setAuthorName(textFieldAuthor.getText());
            setNewTitle(textFieldTitle.getText());
            dispose();
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        textFieldTitle = new JTextField();
        label2 = new JLabel();
        textFieldAuthor = new JTextField();
        createButton = new JButton();

        //======== this ========
        Container contentPane = getContentPane();

        //---- label1 ----
        label1.setText("Please enter title name.");

        //---- label2 ----
        label2.setText("Enter author name.");

        //---- createButton ----
        createButton.setText("Create");
        createButton.addActionListener(e -> createButtonActionPerformed(e));

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addComponent(label1, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addComponent(textFieldTitle, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addComponent(label2, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                        .addComponent(textFieldAuthor, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
                    .addContainerGap())
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(154, 154, 154)
                    .addComponent(createButton)
                    .addContainerGap(161, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(label1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(textFieldTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(26, 26, 26)
                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(textFieldAuthor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(createButton)
                    .addContainerGap(29, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField textFieldTitle;
    private JLabel label2;
    private JTextField textFieldAuthor;
    private JButton createButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    private String authorName;
    private String newTitle ;

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getNewTitle() {
        return newTitle;
    }


}
