import javax.swing.*;
import java.awt.*;

class MainPanel extends JPanel {
    private final int panelWidth;
    private final int panelHeight;
    private TestPanel testPanel;
    private final Frame frame;

    public MainPanel(Frame frame, int width, int height) {
        panelWidth = width;
        panelHeight = height;
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.frame = frame;
        setLayout(null);
        setVisible(true);

        JButton startButton1 = new JButton("Start - test 1");
        JButton startButton2 = new JButton("Start - test 2");
        JButton infoButton = new JButton("Informacje");
        JButton exitButton = new JButton("Exit");

        add(startButton1);
        add(startButton2);
        add(infoButton);
        add(exitButton);
        int buttonWidth = 150;
        int buttonHeight = 40;
        startButton1.setBounds(panelWidth / 2 - buttonWidth / 2, 100, buttonWidth, buttonHeight);
        startButton2.setBounds(panelWidth / 2 - buttonWidth / 2, 160, buttonWidth, buttonHeight);
        infoButton.setBounds(panelWidth / 2 - buttonWidth / 2, 220, buttonWidth, buttonHeight);
        exitButton.setBounds(panelWidth / 2 - buttonWidth / 2, 280, buttonWidth, buttonHeight);
        startButton1.addActionListener(e -> {
            setVisible(false);
            testPanel = new TestPanel(this, panelWidth, panelHeight, 1);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });
        startButton2.addActionListener(e -> {
            setVisible(false);
            testPanel = new TestPanel(this, panelWidth, panelHeight, 2);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });
        infoButton.addActionListener(e -> {
            setVisible(false);
            InfoPanel infoPanel = new InfoPanel(this, panelWidth, panelHeight);
            frame.add(infoPanel);
            frame.setContentPane(infoPanel); //Po naciśnięciu przycisku przełączenie na panel z informacjami
        });
        exitButton.addActionListener(e -> System.exit(0));
    }

    public void showMainPanel() {
        setVisible(true);
        frame.setContentPane(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        drawHeading(g);
    }

    private void drawHeading(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 24));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        String s = "TEST - PŁEĆ MÓZGU";
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (panelWidth - stringWidth) / 2, 40);
    }

    private void paintBackground(Graphics g) {
        try {
            Image brain = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/brain.png"));
            new JLabel(new ImageIcon(brain));
            g.drawImage(brain, panelWidth / 2 - brain.getWidth(null) / 8, 60,
                    brain.getWidth(null) / 4, brain.getHeight(null) / 4, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
