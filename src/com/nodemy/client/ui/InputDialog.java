package com.nodemy.client.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * A simple dialog for inputing information.
 * 
*/
public class InputDialog extends JDialog implements ActionListener {


    /* Public Constructor(s) */
    /**
     * Construct an input dialog.
     * 
     * @param pParent
     *            the parent frame.
     */
    public InputDialog(Frame pParent) {
        super(pParent);
        setModal(true);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        setSize(350, 120);
        setVisible(false);
        description.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        description.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        description.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        description.setAlignmentX(0.5F);
        getContentPane().add(description);
        getContentPane().add(input);
        JButton okButton = new JButton();
        okButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        okButton.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        okButton.setText("OK");
        okButton.setActionCommand("OK");
        okButton.setOpaque(false);
        okButton.setMnemonic((int) 'O');
        JButton cancelButton = new JButton();
        cancelButton
                .setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cancelButton.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        cancelButton.setText("Cancel");
        cancelButton.setActionCommand("CANCEL");
        cancelButton.setOpaque(false);
        cancelButton.setMnemonic((int) 'C');
        JPanel buttons = new JPanel();
        new BoxLayout(buttons, BoxLayout.X_AXIS);
        buttons.add(okButton);
        buttons.add(cancelButton);
        getContentPane().add(buttons);
        addWindowListener(new SymWindow());
        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
    }

    protected void addText(String key, JTextField text) {
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setAlignmentX(0.0F);
        Dimension dim = text.getPreferredSize();
        text.setMaximumSize(dim);
        text.setMinimumSize(dim);
        this.createInput(key, text);
    }

    public void addInput(String key, int length) {
        addText(key, new JTextField(length));
    }

    public void addInput(String key, String text, int length) {
        addText(key, new JTextField(text, length));
    }

    public void addPasswordInput(String key, int length) {
        addText(key, new JPasswordField(length));
    }

    public void addPasswordInput(String key, String password, int length) {
        addText(key, new JPasswordField(password, length));
    }
    private Box createInput(String key,Component comp) {
        this.inputCount++;
        JLabel label = new JLabel(key+":");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        Box item = Box.createHorizontalBox();
        item.add(label);
        item.add(Box.createHorizontalStrut(4));
        item.add(comp);
        input.add(item);
        this.inputs.put(key,comp);
        return item;
    }
    public void addComboBox(String key,Collection<String> systems) {
        this.inputCount++;        
        JComboBox combo = new JComboBox(systems.toArray());
        combo.setSelectedIndex(0);
        createInput(key,combo);        
    }

    /**
     * Set the descriptive text.
     * 
     * @param pTxt
     *            the text.
     */
    public void setDescription(String pTxt) {
        description.setText(pTxt);
    }

    /**
     * @see Component#setVisible
     */
    public void setVisible(boolean pBool) {
        if (pBool) {
            Rectangle bounds = (getParent()).getBounds();
            Dimension size = getSize();
            setLocation(bounds.x + (bounds.width - size.width) / 2, bounds.y
                    + (bounds.height - size.height) / 2);
        }

        super.setVisible(pBool);
    }

    public boolean run() {
        setVisible(true);
        return getResult();
    }

    /**
     * @see ActionListener#actionPerformed
     */
    public void actionPerformed(ActionEvent event) {
        try {
            String errorMessage = validateInput();
            result = "OK".equals(event.getActionCommand());
            if (result && errorMessage != null) {
                showError(errorMessage);
                return;
            }
            setVisible(false);
        } catch (Exception e) {
        }
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Error",
                JOptionPane.ERROR_MESSAGE);
    }
    protected String validateInput() {
        return null;
    }

    private Map<String, Component> inputs = new HashMap<String, Component>();

    public boolean getResult() {
        return this.result;
    }

    public String getInput(String key) {
        Component comp = inputs.get(key);
        if(comp instanceof JTextField) {
            return ((JTextField)comp).getText();
        } else {
            return (String)((JComboBox)comp).getSelectedItem();
        }
    }

    /**
     * @see Component#addNotify
     */
    public void addNotify() {
        // Record the size of the window prior to calling parents addNotify.
        Dimension d = getSize();

        super.addNotify();

        if (myComponentsAdjusted) {
            return;
        }
        // Adjust components according to the insets
        Insets insets = getInsets();
        setSize(insets.left + insets.right + d.width, insets.top
                + insets.bottom + d.height);
        Component components[] = getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            Point p = components[i].getLocation();
            p.translate(insets.left, insets.top);
            components[i].setLocation(p);
        }
        myComponentsAdjusted = true;
    }

    /* Private Field(s) */
    /** Used for addNotify check. */
    private boolean myComponentsAdjusted = false;
    private JLabel description = new JLabel();
    private Box input = Box.createVerticalBox();
    private boolean result;

    /**
     * Handle the closing operation.
     */
    class SymWindow extends WindowAdapter {
        public void windowClosing(WindowEvent event) {
            try {
                setVisible(false);
            } catch (Exception e) {
            }
        }
    }
    private int inputCount;
    private static final long serialVersionUID = 3792073930961654970L;

}

