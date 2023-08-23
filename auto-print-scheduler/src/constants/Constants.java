package constants;

import java.awt.*;

public class Constants {
    public static class Sizes{
        public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        public static final Dimension preferSize= new Dimension(1080,720);
    }

    public static class Fonts{
        public static final Font main20 = new Font("나눔스퀘어_ac", Font.BOLD, 20);

    }

    public static class Colors{
        public static final Color blue = new Color(Integer.valueOf("24405A",16));
        public static final Color darkBlue = new Color(Integer.valueOf("152E46",16));
        public static final Color darkGrey = new Color(Integer.valueOf("3D3D3D",16));

    }
}
