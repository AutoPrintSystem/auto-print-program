package main;


import components.CMenubar;
import constants.Constants;
import panels.SidePanel;
import screens.ControlScreen;
import screens.HomeScreen;
import screens.LogScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private CMenubar menuBar;
    SidePanel sidePanel;
    JPanel contentContainer;

    /*Screens*/
    private CardLayout cardLayout;
    private ControlScreen controlScreen;
    private HomeScreen homeScreen;
    private LogScreen logScreen;

    private ActionHandler actionHandler;


    MainFrame() {
        /*attributes*/
        this.setTitle("auto-print-scheduler");
        this.setSize(Constants.Sizes.preferSize);
        this.setLayout(new BorderLayout());
        this.setLocation(Constants.Sizes.screenSize.width / 2 - Constants.Sizes.preferSize.width / 2, Constants.Sizes.screenSize.height / 2 - Constants.Sizes.preferSize.height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridBagLayout());


        /*components*/
        this.menuBar = new CMenubar();
        this.setJMenuBar(menuBar);

        // sidePanel
        GridBagConstraints gbc = new GridBagConstraints();
        sidePanel = new SidePanel();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(sidePanel, gbc);

        actionHandler = new ActionHandler();
        sidePanel.getHomeButton().addActionListener(actionHandler);
        sidePanel.getControlButton().addActionListener(actionHandler);
        sidePanel.getLogButton().addActionListener(actionHandler);


        //contents container

        contentContainer = new JPanel();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(contentContainer, gbc);

        cardLayout = new CardLayout();
        contentContainer.setLayout(cardLayout);


        controlScreen = new ControlScreen();
        homeScreen = new HomeScreen();
        logScreen = new LogScreen();
        contentContainer.add(controlScreen,"control");
        contentContainer.add(homeScreen, "home");
        contentContainer.add(logScreen,"log");

        cardLayout.show(contentContainer,"home");


    }


    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String clickedButton = e.getActionCommand();
            switch (clickedButton) {
                case "home" -> {
                    cardLayout.show(contentContainer,"home");
                }
                case "control" -> {
                    cardLayout.show(contentContainer,"control");
                }
                case "log" -> {
                    cardLayout.show(contentContainer,"log");
                }
            }
        }
    }

}
