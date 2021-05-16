import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Arrays;

public class TestPanel extends JPanel {
    private final int testPanelWidth;
    private final int testPanelHeight;
    private Questions questions = new Questions(1);
    private int questionNumber = 1;
    private final JTextArea questionArea = new JTextArea();

    private enum Answer {
        a, b, c, none
    }

    private enum Gender {
        male, female
    }

    private Answer[] answers = new Answer[10];
    private final JRadioButton radioButtonA = new JRadioButton("a");
    private final JRadioButton radioButtonB = new JRadioButton("b");
    private final JRadioButton radioButtonC = new JRadioButton("c");
    private final JButton nextButton = new JButton("Następne pytanie");
    private final JButton previousButton = new JButton("Poprzednie pytanie");
    private final JButton endButton = new JButton("Zakończ test");
    private final JButton returnButton = new JButton("Powrót do menu");

    public TestPanel(MainPanel mainPanel, int width, int height, int testNumber) {
        testPanelWidth = width;
        testPanelHeight = height;
        setLayout(null);
        setVisible(true);
        Arrays.fill(answers, Answer.none);

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
        questionArea.setText(questions.getQuestion(questionNumber));

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
            getAnswer();
            if (questionNumber == 10) {
                nextButton.setEnabled(false);
            }
            previousButton.setEnabled(true);
        });
        previousButton.addActionListener(e -> {
            questionNumber--;
            questionArea.setText(questions.getQuestion(questionNumber));
            getAnswer();
            if (questionNumber == 1) {
                previousButton.setEnabled(false);
            }
            nextButton.setEnabled(true);
        });
        endButton.addActionListener(e -> {
            endTest();
        });
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

    private void endTest() {
        boolean allAnswers = true;
        int choice;

        for (Answer a : answers) {
            if (a == Answer.none) {
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

        if (choice == 0) {
            Object[] options = {"Mężczyzna", "Kobieta"};
            int gend = JOptionPane.showOptionDialog(this, "Jaka jest twoja płeć?", "Wybór płci",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);

            Gender gender = Gender.male;
            if (gend == 1) {
                gender = Gender.female;
            } else if (gend != 0) {
                return;
            }

            calculateResults(gender);
            radioButtonA.setEnabled(false);
            radioButtonB.setEnabled(false);
            radioButtonC.setEnabled(false);
            nextButton.setEnabled(false);
            previousButton.setEnabled(false);
            endButton.setEnabled(false);
        }
    }

    private void calculateResults(Gender gender) {
        StringBuilder ans = new StringBuilder();
        int points = 0;
        for (int i = 0; i < answers.length; i++) {
            Answer answer = answers[i];
            ans.append(i + 1).append(". ").append(answer).append("\t--\t");

            switch (answer) {
                case a: {
                    if (gender == Gender.male) {
                        ans.append("10 pkt\n");
                        points += 10;
                    } else { //kobieta
                        ans.append("15 pkt\n");
                        points += 15;
                    }
                    break;
                }
                case b:
                case none: {
                    ans.append("5 pkt\n");
                    points += 5;
                    break;
                }
                case c: {
                    ans.append("-5 pkt\n");
                    points += -5;
                    break;
                }
            }
        }

        ans.append("\nSuma punktów: ").append(points).append("\n");

        switch (gender) {
            case male: {
                if (points < 0) {
                    ans.append("Twój mózg jest skrajnie męski");
                } else if (points < 50) {
                    ans.append("Płeć twojego mózgu odpowiada twojej płci (mężczyzna)");
                } else if (points <= 60) {
                    ans.append("Twój mózg wykazuje się cechami zgodnymi dla obu płci");
                } else {
                    ans.append("Twój mózg wykazuje kobiece skłonności");
                }
                break;
            }
            case female: {
                if (points < 50) {
                    ans.append("Twój mózg wykazuje męskie skłonności");
                } else if (points <= 60) {
                    ans.append("Twój mózg wykazuje się cechami zgodnymi dla obu płci");
                } else if (points <= 100) {
                    ans.append("Płeć twojego mózgu odpowiada twojej płci (kobieta)");
                } else {
                    ans.append("Twój mózg jest skrajnie kobiecy");
                }
                break;
            }
        }

        String s = ans.toString();
        questionArea.setText(s);
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
        StringBuilder ans = new StringBuilder("| ");
        for (int i = 0; i < answers.length; i++) {
            ans.append(i + 1).append(". ").append(answers[i]).append(" | ");
        }
        String s = ans.toString();
        int stringWidth = fontMetrics.stringWidth(s);
        g.drawString(s, (testPanelWidth - stringWidth) / 2, 550);
    }
}
