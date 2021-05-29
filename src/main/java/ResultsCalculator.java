public class ResultsCalculator {
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

        return ans.toString();
    }
}
