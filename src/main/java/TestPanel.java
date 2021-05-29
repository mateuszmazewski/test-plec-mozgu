import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Arrays;

public class TestPanel extends JPanel {
    private final int testPanelWidth;
    private final int testPanelHeight;
    private final Questions questions;
    private int questionNumber = 1;
    private final JTextArea questionArea = new JTextArea();
    private final ResultsCalculator resultsCalculator = new ResultsCalculator();
    private final int testNumber;

    private final Answer[] answers;
    private final JRadioButton radioButtonA = new JRadioButton("a");
    private final JRadioButton radioButtonB = new JRadioButton("b");
    private final JRadioButton radioButtonC = new JRadioButton("c");
    private final JButton nextButton = new JButton("Następne pytanie");
    private final JButton previousButton = new JButton("Poprzednie pytanie");
    private final JButton endButton = new JButton("Zobacz wyniki");
    private final JButton returnButton = new JButton("Powrót do menu");

    public TestPanel(MainPanel mainPanel, int width, int height, int testNumber) {
        testPanelWidth = width;
        testPanelHeight = height;
        this.testNumber = testNumber;
        questions = new Questions(testNumber);
        answers = new Answer[questions.getNumberOfQuestions()];
        Arrays.fill(answers, Answer.x);

        setLayout(null);
        setVisible(true);
        add(questionArea);
        questionArea.setBounds(testPanelWidth / 2 - 300, 100, 600, 200);
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("TimesRoman", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(questionArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(questionArea.getX(), questionArea.getY(), questionArea.getWidth(), questionArea.getHeight());
        DefaultCaret caret = (DefaultCaret) questionArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        add(scrollPane);
        questionArea.setText(questions.getQuestion(questionNumber)); //pierwsze pytanie w polu tekstowym

        add(radioButtonA);
        add(radioButtonB);
        add(radioButtonC);
        radioButtonA.addActionListener(e -> {
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
            saveAnswer();
        });
        radioButtonB.addActionListener(e -> {
            radioButtonA.setSelected(false);
            radioButtonC.setSelected(false);
            saveAnswer();
        });
        radioButtonC.addActionListener(e -> {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
            saveAnswer();
        });
        radioButtonA.setBounds(testPanelWidth / 2 - 75, 320, 50, 50);
        radioButtonB.setBounds(testPanelWidth / 2 - 25, 320, 50, 50);
        radioButtonC.setBounds(testPanelWidth / 2 + 25, 320, 50, 50);

        add(nextButton);
        add(previousButton);
        add(endButton);
        add(returnButton);
        previousButton.setEnabled(false);
        nextButton.setBounds(testPanelWidth / 2 - 75 + 100, 400, 150, 40);
        previousButton.setBounds(testPanelWidth / 2 - 75 - 100, 400, 150, 40);
        endButton.setBounds(testPanelWidth / 2 - 75, 460, 150, 40);
        returnButton.setBounds(20, 20, 150, 40);
        nextButton.addActionListener(e -> {
            questionNumber++;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer(); //przełączenie pytania powoduje zaznaczenie wcześniej wybranej odpowiedzi
            if (questionNumber == questions.getNumberOfQuestions()) {
                nextButton.setEnabled(false);
            }
            previousButton.setEnabled(true);
        });
        previousButton.addActionListener(e -> {
            questionNumber--;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer(); //przełączenie pytania powoduje zaznaczenie wcześniej wybranej odpowiedzi
            if (questionNumber == 1) {
                previousButton.setEnabled(false);
            }
            nextButton.setEnabled(true);
        });
        endButton.addActionListener(e -> endTest());
        returnButton.addActionListener(e -> {
            setVisible(false);
            mainPanel.showMainPanel();
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
            answers[questionNumber - 1] = Answer.x;
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

    private void endTest() {
        boolean allAnswers = true; //czy udzielono odpowiedzi na wszystkie pytania?
        int choice;

        for (Answer a : answers) {
            if (a == Answer.x) {
                allAnswers = false;
                break;
            }
        }

        if (allAnswers) {
            choice = JOptionPane.showConfirmDialog(this,
                    "Czy na pewno chcesz zakończyć test?",
                    "Zakończenie testu", JOptionPane.YES_NO_OPTION);
        } else {
            choice = JOptionPane.showConfirmDialog(this,
                    "Nie udzielono odpowiedzi na wszystkie pytania.\n" +
                            "Brak odpowiedzi też jest odpowiednio punktowany.\n" +
                            "Czy na pewno chcesz zakończyć test?",
                    "Zakończenie testu", JOptionPane.YES_NO_OPTION);
        }

        if (choice == 0) { // 0 oznacza, że kliknięto "yes"
            Object[] options = {"Mężczyzna", "Kobieta"};
            int gend = JOptionPane.showOptionDialog(this, "Jaka jest twoja płeć?", "Wybór płci",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            Gender gender = Gender.male;
            if (gend == 1) {
                gender = Gender.female;
            } else if (gend != 0) {
                return; //jeśli nie zaznaczono płci, tylko zamknięto okno -- powrót do testu
            }

            String results = resultsCalculator.calculateResults(gender, answers, testNumber);
            questionArea.setText(results);
            //radioButtonA.setEnabled(false);
            //radioButtonB.setEnabled(false);
            //radioButtonC.setEnabled(false);
            //nextButton.setEnabled(false);
            //previousButton.setEnabled(false);
            //endButton.setEnabled(false);
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
        String s = "TEST " + testNumber;
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (testPanelWidth - stringWidth) / 2, 50);
    }

    private void drawAnswers(Graphics g) {
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        g.setColor(Color.BLACK);
        FontMetrics fontMetrics = g.getFontMetrics();
        StringBuilder questionNumsSB = new StringBuilder();
        StringBuilder answersSB = new StringBuilder();
        for (int i = 0; i < answers.length; i++) {
            questionNumsSB.append(i + 1).append(" ");
            if (i + 1 < 10)
                answersSB.append(answers[i]).append(" ");
            else {
                answersSB.append(answers[i]).append("  ");
            }
        }
        String questionNums = questionNumsSB.toString();
        String answers = answersSB.toString();
        int stringWidth = fontMetrics.stringWidth(questionNums);
        int stringHeight = fontMetrics.getHeight();
        g.drawString(questionNums, (testPanelWidth - stringWidth) / 2, 525);
        g.drawString(answers, (testPanelWidth - stringWidth) / 2, 525 + stringHeight);
    }
}
