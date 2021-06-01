import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public static void main(String[] args) {
        new Frame("Test - płeć mózgu");
    }

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        int mainPanelWidth = 800 + getInsets().left;
        int mainPanelHeight = 600 + getInsets().top;
        MainPanel mainPanel = new MainPanel(this, mainPanelWidth, mainPanelHeight);
        mainPanel.setSize(mainPanelWidth, mainPanelHeight);

        add(mainPanel);
        setContentPane(mainPanel); //Po uruchomieniu widoczne jest menu główne
        setSize(mainPanel.getSize());
        setLocationRelativeTo(null); //Okno na środku ekranu
        setResizable(false); //Nie można zmieniać rozmiaru okna
        setVisible(true);

        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
