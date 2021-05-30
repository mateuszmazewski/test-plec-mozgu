import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final int mainPanelWidth = 800 + getInsets().left;
    private final int mainPanelHeight = 600 + getInsets().top;
    private MainPanel mainPanel;

    public static void main(String[] args) {
        new Frame("Test - płeć mózgu");
    }

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        mainPanel = new MainPanel(this, mainPanelWidth, mainPanelHeight);
        mainPanel.setSize(mainPanelWidth, mainPanelHeight);

        add(mainPanel);
        setContentPane(mainPanel); //Po uruchomieniu widoczne jest menu główne
        setSize(mainPanel.getSize());
        setLocationRelativeTo(null); //Okno na środku ekranu
        setResizable(false); //Nie można zmieniać rozmiaru okna
        setVisible(true);

        try {
            setIconImage(ImageIO.read(new File("icon.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
