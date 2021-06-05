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

        int mainPanelWidth = 800;
        int mainPanelHeight = 600;
        MainPanel mainPanel = new MainPanel(this, mainPanelWidth, mainPanelHeight);

        setContentPane(mainPanel); //Po uruchomieniu widoczne jest menu główne
        pack(); //Dopasowanie rozmiaru okna do rozmiaru panelu
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
