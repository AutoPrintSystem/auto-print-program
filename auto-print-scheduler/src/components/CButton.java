package components;

import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CButton extends JToggleButton {
    BtnActionListener actionListener;
    public CButton(String text, String actionCmd) {
        super(text);
        this.setPreferredSize(new Dimension(250, 50));
        this.setBackground(Constants.Colors.blue);
        this.setFont(Constants.Fonts.main20);
        this.setForeground(Color.WHITE);

        this.setFocusPainted(false);
        this.setBorderPainted(false);

        this.setSelectedColor(Constants.Colors.darkBlue);

        this.setActionCommand(actionCmd);

        actionListener = new BtnActionListener();
        this.addActionListener(actionListener);
    }

    private void setSelectedColor(Color color) {
        UIManager.put("ToggleButton.select", color);
        SwingUtilities.updateComponentTreeUI(this);
    }

    public static class BtnActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());

        }
    }
    public void activeSelectedColor(){
        System.out.println("test");
        this.setSelectedColor(new Color(Integer.valueOf("24405A", 16)));
    }


}


