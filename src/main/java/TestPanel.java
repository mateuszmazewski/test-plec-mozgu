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
    private boolean testEnded = false;
    private int points = 0;

    private final Answer[] answers;
    private final JRadioButton radioButtonA = new JRadioButton("a");
    private final JRadioButton radioButtonB = new JRadioButton("b");
    private final JRadioButton radioButtonC = new JRadioButton("c");
    private final JButton nextButton = new JButton("Następne pytanie");
    private final JButton previousButton = new JButton("Poprzednie pytanie");
    private final JButton endButton = new JButton("Zobacz wyniki");
    private final JButton returnButton = new JButton("Powrót do menu");
    private final JButton goToQuestionButton = new JButton("Przejdź");
    private final JLabel goToQuestionLabel = new JLabel("Przejdź do pytania");
    private final JTextField goToQuestionTextField = new JTextField();

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
        radioButtonA.setBounds(testPanelWidth / 2 - 75, 300, 40, 40);
        radioButtonB.setBounds(testPanelWidth / 2 - 25, 300, 40, 40);
        radioButtonC.setBounds(testPanelWidth / 2 + 25, 300, 40, 40);

        add(nextButton);
        add(previousButton);
        add(endButton);
        add(returnButton);
        add(goToQuestionButton);
        previousButton.setEnabled(false);
        nextButton.setBounds(testPanelWidth / 2 - 75 + 100, 360, 150, 40);
        previousButton.setBounds(testPanelWidth / 2 - 75 - 100, 360, 150, 40);
        endButton.setBounds(testPanelWidth / 2 - 75, 460, 150, 40);
        returnButton.setBounds(20, 20, 150, 40);
        nextButton.addActionListener(e -> goToQuestion(++questionNumber));
        previousButton.addActionListener(e -> goToQuestion(--questionNumber));
        endButton.addActionListener(e -> endTest());
        returnButton.addActionListener(e -> {
            boolean thereAreAnswers = false;
            for (Answer a : answers) {
                if (a != Answer.x) {
                    thereAreAnswers = true;
                    break;
                }
            }

            int choice = 0;
            if (thereAreAnswers && !testEnded) {
                choice = JOptionPane.showConfirmDialog(this,
                        "Udzielono odpowiedzi. Czy chcesz wyjść bez wyświetlenia wyników?",
                        "Powrót do menu", JOptionPane.YES_NO_OPTION);
            }
            if (choice == 0) { // 0 oznacza, że kliknięto "yes"
                setVisible(false);
                mainPanel.showMainPanel();
            }
        });

        add(goToQuestionLabel);
        goToQuestionLabel.setBounds(testPanelWidth / 2 - 140, 420, 110, 20);
        add(goToQuestionTextField);
        goToQuestionTextField.setBounds(testPanelWidth / 2 - 30, 420, 60, 20);
        goToQuestionButton.setBounds(testPanelWidth / 2 + 40, 420, 80, 20);

        goToQuestionButton.addActionListener(e -> {
            String text = goToQuestionTextField.getText();
            int qNumber;
            try {
                qNumber = Integer.parseInt(text.trim()); //tekst z usuniętymi znakami białymi z krańców
                if (qNumber > 0 && qNumber <= questions.getNumberOfQuestions()) {
                    goToQuestion(qNumber);
                    goToQuestionTextField.setText("");
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showConfirmDialog(null, "Nieprawidłowa wartość", "Błąd", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
                goToQuestionTextField.setText("");
            }
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
        Answer actualAnswer = answers[questionNumber - 1];
        if (actualAnswer == Answer.a) {
            radioButtonA.setSelected(true);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
        } else if (actualAnswer == Answer.b) {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(true);
            radioButtonC.setSelected(false);
        } else if (actualAnswer == Answer.c) {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(true);
        } else {
            radioButtonA.setSelected(false);
            radioButtonB.setSelected(false);
            radioButtonC.setSelected(false);
        }
    }

    private void goToQuestion(int qNumber) {
        if (qNumber > 0 && qNumber <= questions.getNumberOfQuestions()) {
            questionNumber = qNumber;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer(); //przełączenie pytania powoduje zaznaczenie wcześniej wybranej odpowiedzi
            if (questionNumber == 1) {
                previousButton.setEnabled(false);
                nextButton.setEnabled(true);
            } else if (questionNumber == questions.getNumberOfQuestions()) {
                previousButton.setEnabled(true);
                nextButton.setEnabled(false);
            } else {
                previousButton.setEnabled(true);
                nextButton.setEnabled(true);
            }
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

            testEnded = true;
            String results = resultsCalculator.calculateResults(gender, answers, testNumber);
            questionArea.setText(results);
            radioButtonA.setVisible(false);
            radioButtonB.setVisible(false);
            radioButtonC.setVisible(false);
            nextButton.setVisible(false);
            previousButton.setVisible(false);
            endButton.setVisible(false);
            goToQuestionButton.setVisible(false);
            goToQuestionTextField.setVisible(false);
            goToQuestionLabel.setVisible(false);

            points = resultsCalculator.getPoints();
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawHeading(g);
        drawAnswers(g);
        if (testEnded) {
            drawResults(g);
        }
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

    private void drawResults(Graphics g) {
        int minPoints = 0;
        int maxPoints = 0;
        if (testNumber == 1) {
            minPoints = -50;
            maxPoints = 150;
        } else if (testNumber == 2) {
            minPoints = -150;
            maxPoints = 450;
        }

        int lineLength = 600;
        int lineStartX = testPanelWidth / 2 - lineLength / 2;
        int lineEndX = testPanelWidth / 2 + lineLength / 2;
        int lineY = 400;
        int step = (lineLength / (maxPoints - minPoints)) * 10; // 1 podziałka na osi = 10 punktów
        g.drawLine(lineStartX, lineY, lineEndX, lineY);

        int actualNumber = minPoints;
        String number;
        FontMetrics fontMetrics = g.getFontMetrics();
        int stringWidth;
        int zeroX = 0;

        for (int x = lineStartX; x <= lineEndX; x += step) {
            g.drawLine(x, lineY + 10, x, lineY - 10);
            if (testNumber == 2 && x % 50 == 0) { //dla testu 2 dłuższa kreska pionowa co 50 na osi
                g.drawLine(x, lineY + 15, x, lineY - 15);
            }

            number = Integer.toString(actualNumber);
            if (actualNumber == 0) {
                zeroX = x; //zapisanie pozycji zera na osi
            }
            stringWidth = fontMetrics.stringWidth(number);
            if (testNumber == 1 || (testNumber == 2 && x % 50 == 0)) { //dla testu 2 liczba co 50 na osi
                g.drawString(number, x - stringWidth / 2, lineY + 30);
            }
            actualNumber += 10;
        }

        String extremelyManly = "skrajnie męski";
        int extremelyManlyWidth = fontMetrics.stringWidth(extremelyManly);
        String extremelyFeminine = "skrajnie kobiecy";
        int extremelyFeminineWidth = fontMetrics.stringWidth(extremelyFeminine);
        String manly = "męski";
        int manlyWidth = fontMetrics.stringWidth(manly);
        String feminine = "kobiecy";
        int feminineWidth = fontMetrics.stringWidth(feminine);
        String shared = "wspólny";
        int sharedWidth = fontMetrics.stringWidth(shared);
        g.setFont(new Font("TimesRoman", Font.BOLD, 14));

        Color transparentBlue = new Color(0, 0, 255, 127);
        Color transparentCyan = new Color(0, 200, 255, 127);
        Color transparentDarkPink = new Color(255, 0, 150, 127);
        Color transparentRed = new Color(255, 0, 0, 127);
        Color transparentPurple = new Color(127, 100, 203, 127);

        if (testNumber == 1) {
            g.setColor(transparentBlue);
            g.fillRect(lineStartX, lineY - 15, step * 5, 30); //przedział skrajnie męski
            g.drawString(extremelyManly, lineStartX + (step * 5) / 2 - extremelyManlyWidth / 2, lineY - 30);
            g.setColor(transparentCyan);
            g.fillRect(zeroX, lineY - 15, step * 6, 30); //przedział męski
            g.drawString(manly, zeroX + (step * 6) / 2 - manlyWidth / 2 - 20, lineY - 30);
            g.setColor(transparentDarkPink);
            g.fillRect(zeroX + step * 5, lineY - 15, step * 5, 30); //przedział kobiecy
            g.drawString(feminine, zeroX + step * 5 + (step * 5) / 2 - feminineWidth / 2 + 20, lineY - 30);
            g.setColor(transparentRed);
            g.fillRect(zeroX + step * 10, lineY - 15, step * 5, 30); //przedział skrajnie kobiecy
            g.drawString(extremelyFeminine, zeroX + step * 10 + (step * 5) / 2 - extremelyFeminineWidth / 2, lineY - 30);
            g.setColor(transparentPurple);
            g.drawString(shared, zeroX + step * 5 + step / 2 - sharedWidth / 2, lineY - 30); //przedział wspólny
        } else if (testNumber == 2) {
            g.setColor(transparentBlue);
            g.fillRect(lineStartX, lineY - 15, step * 15, 30); //przedział skrajnie męski
            g.drawString(extremelyManly, lineStartX + (step * 15) / 2 - extremelyManlyWidth / 2, lineY - 30);
            g.setColor(transparentCyan);
            g.fillRect(zeroX, lineY - 15, step * 18, 30); //przedział męski
            g.drawString(manly, zeroX + (step * 18) / 2 - manlyWidth / 2 - 20, lineY - 30);
            g.setColor(transparentDarkPink);
            g.fillRect(zeroX + step * 15, lineY - 15, step * 15, 30); //przedział kobiecy
            g.drawString(feminine, zeroX + step * 15 + (step * 15) / 2 - feminineWidth / 2 + 20, lineY - 30);
            g.setColor(transparentRed);
            g.fillRect(zeroX + step * 30, lineY - 15, step * 15, 30); //przedział skrajnie kobiecy
            g.drawString(extremelyFeminine, zeroX + step * 30 + (step * 15) / 2 - extremelyFeminineWidth / 2, lineY - 30);
            g.setColor(transparentPurple);
            g.drawString(shared, zeroX + step * 15 + (step * 3) / 2 - sharedWidth / 2, lineY - 30); //przedział wspólny
        }

        int actualPointsX = (int) (zeroX + step * ((double) (points) / 10));
        g.setColor(Color.RED);
        g.fillRect(actualPointsX - 2, lineY - 15, 5, 30);
    }
}
