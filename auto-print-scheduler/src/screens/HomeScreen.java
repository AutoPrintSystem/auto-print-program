package screens;

import panels.ContentPanel;
import panels.SidePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel {
    SidePanel sidePanel;
    ContentPanel contentPanel;
    ActionHandler actionHandler;
    public HomeScreen() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        sidePanel = new SidePanel();
        contentPanel = new ContentPanel();

        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0; // SidePanel의 너비를 고정하려면 0으로 설정
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(sidePanel, gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1; // ContentPanel이 가능한 많은 공간을 차지하도록 설정
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(contentPanel, gbc);

        actionHandler = new ActionHandler();
        this.sidePanel.getHomeButton().addActionListener(actionHandler);
        this.sidePanel.getButton1().addActionListener(actionHandler);
        this.sidePanel.getButton2().addActionListener(actionHandler);
    }

    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clickedButton = e.getActionCommand();
            switch (clickedButton) {
                case "home" -> {
                    sidePanel.getHomeButton().setSelected(false);
                    sidePanel.getButton1().setSelected(false);
                    sidePanel.getButton2().setSelected(false);

                }
                case "config" -> {
                    sidePanel.getHomeButton().setSelected(false);
                    sidePanel.getButton1().setSelected(true);
                    sidePanel.getButton2().setSelected(false);
                }
                case "log" -> {
                    sidePanel.getHomeButton().setSelected(false);
                    sidePanel.getButton1().setSelected(false);
                    sidePanel.getButton2().setSelected(true);
                }
            }

        }
    }
}
