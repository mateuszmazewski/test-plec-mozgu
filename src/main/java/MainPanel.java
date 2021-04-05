import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    private final int mainPanelWidth;
    private final int mainPanelHeight;
    JButton startButton1 = new JButton("Start - test 1");
    JButton startButton2 = new JButton("Start - test 2");

    public MainPanel(int width, int height) {
        mainPanelWidth = width;
        mainPanelHeight = height;
        setLayout(null);

        startButton1.setBounds(mainPanelWidth / 2 - 75, 100, 150, 40);
        startButton2.setBounds(mainPanelWidth / 2 - 75, 200, 150, 40);
        add(startButton1);
        add(startButton2);
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