import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private final int infoPanelWidth;
    private final int infoPanelHeight;

    public InfoPanel(MainPanel mainPanel, int width, int height) {
        infoPanelWidth = width;
        infoPanelHeight = height;
        setLayout(null);
        setVisible(true);

        JButton returnButton = new JButton("Powrót do menu");

        add(returnButton);
        returnButton.setBounds(20, 20, 150, 40);
        returnButton.addActionListener(e -> {
            setVisible(false);
            mainPanel.showMainPanel();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHeading(g);
        drawInfo(g);
    }

    private void drawHeading(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        String s = "INFORMACJE";
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (infoPanelWidth - stringWidth) / 2, 50);
    }

    public void drawString(Graphics g, String text, int x, int y) {
        int lineHeight = g.getFontMetrics().getHeight();
        for (String line : text.split("\n"))
            g.drawString(line, x, y += lineHeight);
    }

    private void drawInfo(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.setColor(Color.BLACK);
        String info = "\u2022Testy mają na celu wykazanie płci, męskości lub kobiecości, wzorców twojego mózgu.\n" +
                "\u2022Nie ma złych ani dobrych odpowiedzi.\n" +
                "\u2022Rezultat to po prostu wskazówka co do poziomu męskiego hormonu, który twój mózg otrzymał między\n" +
                "  szóstym a ósmym tygodniem od poczęcia.\n" +
                "\u2022To miało i ma wpływ na twoje wybory, wartości, zachowanie, styl, orientację i preferencje.\n" +
                "\u2022Brak odpowiedzi również jest odpowiedzią!\n" +
                "\u2022Szczegóły odnośnie interpretacji wyników konkretnego testu można zobaczyć po wykonaniu danego testu.\n\n\n" +
                "Literatura:\n" +
                "\u2022\"płeć mózgu\", Anne Moir, David Jessel, Państwowy Instytut Wydawniczy, 1993";

        drawString(g, info, 30, 100);
    }
}
