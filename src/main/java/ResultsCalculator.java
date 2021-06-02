public class ResultsCalculator {
    private int points;

    public String calculateResults(Gender gender, Answer[] answers, int testNumber) {
        StringBuilder ans = new StringBuilder();
        int points = 0;
        for (int i = 0; i < answers.length; i++) {
            Answer answer = answers[i];
            ans.append(i + 1).append(". ").append(answer).append("\t--\t");

            switch (answer) {
                case a: {
                    if (testNumber == 1) { //Tylko odpowiedzi A są w testach punktowane różnie
                        if (gender == Gender.male) {
                            ans.append("10 pkt\n");
                            points += 10;
                        } else { //kobieta
                            ans.append("15 pkt\n");
                            points += 15;
                        }
                    } else if (testNumber == 2) {
                        if (gender == Gender.male) {
                            ans.append("15 pkt\n");
                            points += 15;
                        } else { //kobieta
                            ans.append("10 pkt\n");
                            points += 10;
                        }
                    }
                    break;
                }
                case b: //Odpowiedzi b i c są punktowane tak samo w obu testach
                case x: {
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

        if (testNumber == 1) {
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
        } else if (testNumber == 2) {
            switch (gender) {
                case male: {
                    if (points < 0) {
                        ans.append("Twój mózg jest skrajnie męski");
                    } else if (points < 150) {
                        ans.append("Płeć twojego mózgu odpowiada twojej płci (mężczyzna)");
                    } else if (points <= 180) {
                        ans.append("Twój mózg wykazuje się cechami zgodnymi dla obu płci");
                    } else {
                        ans.append("Twój mózg wykazuje kobiece skłonności");
                    }
                    break;
                }
                case female: {
                    if (points < 150) {
                        ans.append("Twój mózg wykazuje męskie skłonności");
                    } else if (points <= 180) {
                        ans.append("Twój mózg wykazuje się cechami zgodnymi dla obu płci");
                    } else if (points <= 300) {
                        ans.append("Płeć twojego mózgu odpowiada twojej płci (kobieta)");
                    } else {
                        ans.append("Twój mózg jest skrajnie kobiecy");
                    }
                    break;
                }
            }
        }

        if (testNumber == 1) {
            ans.append("\n\nWiększość mężczyzn osiąga od 0 do 60 punktów.\n" +
                    "Większość kobiet osiąga od 50 do 100 punktów.\n" +
                    "Wyniki pokrywające się, tzn. między 50 a 60 punktami, oznaczają" +
                    " zgodność w myśleniu obu płci.\nWyniki mężczyzny poniżej 0 i kobiety" +
                    " powyżej 100 wskazują na mózgi uformowane skrajnie odmiennie od" +
                    " mózgów płci przeciwnej.\nWyniki mężczyzny powyżej 60 punktów mogą" +
                    " wskazywać na kobiece skłonności jego umysłu.\nWyniki kobiety poniżej 50" +
                    " punktów mogą wskazywać na męskie skłonności jej umysłu.");
        } else if (testNumber == 2) {
            ans.append("\n\nWiększość mężczyzn osiągnie wynik od 0 do 180, a większość kobiet" +
                    " od 150 do 300.\nMózgi zaprogramowane głównie na męski sposób myślenia osiągają" +
                    " zazwyczaj poniżej 150 punktów. Im bliżej zera, tym człowiek jest bardziej" +
                    " męski i ma wyższy poziom testosteronu. Takie osoby miewają rozwinięte zdolności" +
                    " logiczne, analityczne i werbalne, bywają zdyscyplinowane i dobrze zorganizowane," +
                    " lepiej przewidują na podstawie danych statystycznych, nie poddają się emocjom." +
                    " Punkty poniżej zera to rezultaty bardzo męskie.\nMózgi zaprogramowane na myślenie" +
                    " kobiece osiągają wynik powyżej 180 punktów. Im wyższa liczba, tym bardziej kobiecy mózg," +
                    " i tym większe prawdopodobieństwo, że ta osoba będzie miała talenty twórcze," +
                    " artystyczne i muzyczne. Większość decyzji podejmuje na podstawie intuicji lub" +
                    " przeczuć. Dobrze również rozwiązuje problemy, stosując przy tym twórcze myślenie i" +
                    " zrozumienie");
        }

        this.points = points;
        return ans.toString();
    }

    public int getPoints() {
        return points;
    }
}
