package components;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class CMenubar extends JMenuBar{
    JMenu fileMenu;
    public CMenubar() {
        this.setBackground(Constants.Colors.darkGrey);

        fileMenu  = new JMenu("setting");
        fileMenu.setForeground(Color.WHITE);
        this.add(fileMenu);
    }
}
