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
        questionArea.setBounds(testPanelWidth / 2 - 300, 100, 600, 200);
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
        radioButtonA.setBounds(testPanelWidth/2-75, 320, 50, 50);
        radioButtonB.setBounds(testPanelWidth/2-25, 320, 50, 50);
        radioButtonC.setBounds(testPanelWidth/2+25, 320, 50, 50);

        JButton nextButton = new JButton("Następne pytanie");
        JButton previousButton = new JButton("Poprzednie pytanie");
        JButton endButton = new JButton("Zakończ test");
        add(nextButton);
        add(previousButton);
        add(endButton);
        previousButton.setEnabled(false);
        nextButton.setBounds(testPanelWidth / 2 - 75 + 100, 400, 150, 40);
        previousButton.setBounds(testPanelWidth / 2 - 75 - 100, 400, 150, 40);
        endButton.setBounds(testPanelWidth / 2 - 75, 460, 150, 40);
        nextButton.addActionListener(e -> {
            saveAnswer();
            questionNumber++;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer();
            if(questionNumber == 10) {
                nextButton.setEnabled(false);
            }
            previousButton.setEnabled(true);
        });
        previousButton.addActionListener(e -> {
            saveAnswer();
            questionNumber--;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer();
            if(questionNumber == 1) {
                previousButton.setEnabled(false);
            }
            nextButton.setEnabled(true);
        });
        endButton.addActionListener(e -> {
            saveAnswer();
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
        repaint(); //Wyświetlenie dotychczasowych odpowiedzi na ekranie
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
        drawAnswers(g);
    }

    private void drawHeading(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        String s = "TEST 1";
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (testPanelWidth - stringWidth) / 2, 50);
    }

    private void drawAnswers(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        StringBuilder ans = new StringBuilder();
        for(int i = 0; i < answers.length; i++) {
            ans.append(i+1).append(". ").append(answers[i]).append(" ");
        }
        String s = ans.toString();
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (testPanelWidth - stringWidth) / 2, 550);
    }
}
