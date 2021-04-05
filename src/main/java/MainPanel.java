import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    private final int mainPanelWidth;
    private final int mainPanelHeight;
    private TestPanel testPanel;

    public MainPanel(Frame frame, int width, int height) {
        mainPanelWidth = width;
        mainPanelHeight = height;
        setLayout(null);
        setVisible(true);

        JButton startButton1 = new JButton("Start - test 1");
        JButton startButton2 = new JButton("Start - test 2");

        add(startButton1);
        add(startButton2);
        startButton1.setBounds(mainPanelWidth / 2 - 75, 100, 150, 40);
        startButton2.setBounds(mainPanelWidth / 2 - 75, 200, 150, 40);
        startButton1.addActionListener(e -> {
            setVisible(false);
            testPanel = new TestPanel(frame, mainPanelWidth, mainPanelHeight, 1);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });
        startButton2.addActionListener(e -> {
            setVisible(false);
            testPanel = new TestPanel(frame, mainPanelWidth, mainPanelHeight, 2);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHeading(g);
    }

    private void drawHeading(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        String s = "TEST - PŁEĆ MÓZGU";
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (mainPanelWidth - stringWidth) / 2, 50);
    }
}