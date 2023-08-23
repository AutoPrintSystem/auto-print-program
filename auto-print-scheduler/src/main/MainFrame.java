package main;


import components.CMenubar;
import constants.Constants;
import screens.HomeScreen;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    CMenubar menuBar;
    HomeScreen homeScreen;

    MainFrame(){
        this.setTitle("auto-print-scheduler");
        this.setSize(Constants.Sizes.preferSize);
        this.setLayout(new BorderLayout()); // 추가된 부분
        this.setLocation(Constants.Sizes.screenSize.width/2- Constants.Sizes.preferSize.width/2,Constants.Sizes.screenSize.height/2-Constants.Sizes.preferSize.height/2);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.menuBar = new CMenubar();
        this.setJMenuBar(menuBar);

        homeScreen = new HomeScreen();
        this.add(homeScreen, BorderLayout.CENTER); // 수정된 부분
    }

}
