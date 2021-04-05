import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class TestPanel extends JPanel {
    private final int testPanelWidth;
    private final int testPanelHeight;
    Questions questions = new Questions(1);
    private int questionNumber = 1;

    private enum Answer {
        a, b, c, none
    }

    private Answer[] answers = new Answer[10];
    private final JRadioButton radioButtonA = new JRadioButton("a");
    private final JRadioButton radioButtonB = new JRadioButton("b");
    private final JRadioButton radioButtonC = new JRadioButton("c");

    public TestPanel(Frame frame, int width, int height, int testNumber) {
        testPanelWidth = width;
        testPanelHeight = height;
        setLayout(null);
        setVisible(true);
        Arrays.fill(answers, Answer.none);

        JTextArea questionArea = new JTextArea();
        add(questionArea);
        questionArea.setBounds(testPanelWidth / 2 - 200, 100, 400, 200);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        questionArea.setText(questions.getQuestion(questionNumber));

        add(radioButtonA);
        add(radioButtonB);
        add(radioButtonC);
        radioButtonA.addActionListener(e -> {
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
        });
        radioButtonB.addActionListener(e -> {
            radioButtonA.setSelected(false);
            radioButtonC.setSelected(false);
        });
        radioButtonC.addActionListener(e -> {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
        });
        radioButtonA.setBounds(200, 320, 50, 50);
        radioButtonB.setBounds(300, 320, 50, 50);
        radioButtonC.setBounds(400, 320, 50, 50);

        JButton nextButton = new JButton("Następne pytanie");
        JButton previousButton = new JButton("Poprzednie pytanie");
        add(nextButton);
        add(previousButton);
        nextButton.setBounds(testPanelWidth / 2 - 75 + 150, 400, 150, 40);
        previousButton.setBounds(testPanelWidth / 2 - 75 - 150, 400, 150, 40);
        nextButton.addActionListener(e -> {
            saveAnswer();
            questionNumber++;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer();

        });
        previousButton.addActionListener(e -> {
            saveAnswer();
            questionNumber--;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer();
        });
    }

    private void saveAnswer() {
        if (radioButtonA.isSelected()) {
            answers[questionNumber - 1] = Answer.a;
        } else if (radioButtonB.isSelected()) {
            answers[questionNumber - 1] = Answer.b;
        } else if (radioButtonC.isSelected()) {
            answers[questionNumber - 1] = Answer.c;
        } else {
            answers[questionNumber - 1] = Answer.none;
        }
    }

    private void getAnswer() {
        if (answers[questionNumber - 1] == Answer.a) {
            radioButtonA.setSelected(true);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
        } else if (answers[questionNumber - 1] == Answer.b) {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(true);
            radioButtonC.setSelected(false);
        } else if (answers[questionNumber - 1] == Answer.c) {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(true);
        } else {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
        }
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
        String s = "TEST 1";
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (testPanelWidth - stringWidth) / 2, 50);
    }
}
