package main;


import components.CMenubar;
import constants.Constants;
import panels.ContentPanel;
import panels.SidePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CMenubar menuBar;
//    HomeScreen homeScreen;


    SidePanel sidePanel;
    JPanel contentContainer;

    MainFrame() {
        this.setTitle("auto-print-scheduler");
        this.setSize(Constants.Sizes.preferSize);
        this.setLayout(new BorderLayout());
        this.setLocation(Constants.Sizes.screenSize.width / 2 - Constants.Sizes.preferSize.width / 2, Constants.Sizes.screenSize.height / 2 - Constants.Sizes.preferSize.height / 2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuBar = new CMenubar();
        this.setJMenuBar(menuBar);

        /*set frame's layout*/
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        /*side panel*/
        sidePanel = new SidePanel();
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weightx = 0;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(sidePanel, gbc);


        /*contents panel*/
        contentContainer = new JPanel();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 1;
        gbc.gridy = 0;
        this.add(contentContainer, gbc);

    }


}
