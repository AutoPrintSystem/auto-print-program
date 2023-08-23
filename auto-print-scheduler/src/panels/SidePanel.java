package panels;


import components.CButton;
import constants.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SidePanel extends JPanel {
    private final CButton homeButton;
    private final CButton button1;
    private final CButton button2;
    private ActionHandler actionHandler;

    public SidePanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(250, getHeight()));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL; //버튼 가로로 채우기
        gbc.anchor = GridBagConstraints.NORTH; // 버튼 위로 붙이기

        this.setBackground(Constants.Colors.blue);
        actionHandler =new ActionHandler();

        gbc.gridy = 0;
        gbc.weighty = 0;
        homeButton = new CButton("Pre-print","home");
        homeButton.addActionListener(actionHandler);
        homeButton.activeSelectedColor();
        this.add(homeButton, gbc);


        gbc.gridy = 1;
        button1 = new CButton("프린터기 설정","config");
        button1.addActionListener(actionHandler);
        this.add(button1, gbc);

        gbc.gridy = 2;
        button2 = new CButton("로그 조회 및 설정","log");
        button2.addActionListener(actionHandler);
        this.add(button2, gbc);

        gbc.gridy = 3;
        gbc.weighty = 1;
        this.add(new JLabel(""), gbc);

    }
    public CButton getButton1(){
        return this.button1;
    }
    public CButton getButton2(){
        return this.button2;
    }
    public CButton getHomeButton(){
        return this.homeButton;
    }

    private class ActionHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String clickedButton = e.getActionCommand();

            switch (clickedButton) {
                case "home" -> {
                    homeButton.setSelected(false);
                    button1.setSelected(false);
                    button2.setSelected(false);

                }
                case "config" -> {
                    homeButton.setSelected(false);
                    button1.setSelected(true);
                    button2.setSelected(false);
                }
                case "log" -> {
                    homeButton.setSelected(false);
                    button1.setSelected(false);
                    button2.setSelected(true);
                }
            }
        }
    }
}

