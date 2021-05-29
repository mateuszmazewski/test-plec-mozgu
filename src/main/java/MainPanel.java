import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;

class MainPanel extends JPanel {
    private final int mainPanelWidth;
    private final int mainPanelHeight;
    private TestPanel testPanel;
    private final JTextArea textArea = new JTextArea();
    Frame frame;

    public MainPanel(Frame frame, int width, int height) {
        mainPanelWidth = width;
        mainPanelHeight = height;
        this.frame = frame;
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
            testPanel = new TestPanel(this, mainPanelWidth, mainPanelHeight, 1);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });
        startButton2.addActionListener(e -> {
            setVisible(false);
            testPanel = new TestPanel(this, mainPanelWidth, mainPanelHeight, 2);
            frame.add(testPanel);
            frame.setContentPane(testPanel); //Po naciśnięciu przycisku przełączenie na panel z testem
        });

        add(textArea);
        textArea.setBounds(mainPanelWidth / 2 - 300, 300, 600, 200);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("TimesRoman", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(textArea.getX(), textArea.getY(), textArea.getWidth(), textArea.getHeight());
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        add(scrollPane);
        String info = "Testy mają na celu wykazanie płci, męskości lub kobiecości, wzorców twojego mózgu." +
                " Nie ma złych ani dobrych odpowiedzi. Rezultat to po prostu wskazówka co do poziomu męskiego hormonu," +
                " który twój mózg otrzymał między szóstym a ósmym tygodniem od poczęcia. To miało i ma wpływ na" +
                " twoje wybory, wartości, zachowanie, styl, orientację i preferencje.\n\nBrak odpowiedzi również jest odpowiedzią!\n" +
                "Szczegóły odnośnie interpretacji wyników konkretnego testu można zobaczyć po wykonaniu danego testu.";
        textArea.setText(info);
    }

    public void showMainPanel() {
        setVisible(true);
        frame.setContentPane(this);
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