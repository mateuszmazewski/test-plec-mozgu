import javax.swing.*;

public class Frame extends JFrame {
    private final int mainPanelWidth = 800 + getInsets().left;
    private final int mainPanelHeight = 600 + getInsets().top;

    public static void main(String[] args) {
        new Frame("Test - płeć mózgu");
    }

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        MainPanel mainPanel = new MainPanel(this, mainPanelWidth, mainPanelHeight);
        mainPanel.setSize(mainPanelWidth, mainPanelHeight);

        pack();
        add(mainPanel);
        setContentPane(mainPanel); //Po uruchomieniu widoczne jest menu główne
        setSize(mainPanel.getSize());
        setLocationRelativeTo(null); //Okno na środku ekranu
        setResizable(false); //Nie można zmieniać rozmiaru okna
        setVisible(true);
    }
}
