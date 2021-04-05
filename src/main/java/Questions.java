public class Questions {
    String[] questions;

    public Questions(int testNumber) {
        if (testNumber == 1) {
            questions = new String[]{"Pytanie 1:\nSłyszysz niewyraźne miauczenie. Jak łatwo możesz zlokalizować kota bez rozglądania się wokoło?\na) Jeśli się zastanowisz, możesz go wskazać.\nb) Możesz go wskazać bez zastanowienia.\nc) Nie jesteś pewien/pewna, czy mógłbyś/mogłabyś go wskazać."
                    , "Pytanie 2:\nJak łatwo możesz zapamiętać piosenkę, którą przed chwilą usłyszałeś/usłyszałaś?\na) Jest to bardzo łatwe; możesz zaśpiewać jej każdy kawałek czysto.\nb) Możesz ją zaśpiewać czysto pod warunkiem, że jest prosta i rytmiczna.\nc) Sprawia ci to trudność."
                    , "Pytanie 3:\nDzwoni do ciebie osoba, którą spotkałeś/spotkałaś kilka razy. Czy łatwo ci rozpoznać jej głos w ciągu kilku sekund, zanim się przedstawi?\na) Jest to dla ciebie łatwe.\nb) Rozpoznałbyś/Rozpoznałabyś głos przynajmniej w połowie przypadków.\nc) Sprawia ci to trudność."
                    , "Pytanie 4:\nJesteś w towarzystwie zaprzyjaźnionych ze sobą małżeństw. Dwie osoby spośród obecnych mają potajemny romans. Czy wykryłbyś/wykryłabyś ich związek?\na) Niemal zawsze.\nb) W połowie przypadków\nc) Rzadko."
                    , "Pytanie 5:\nJesteś na dużym, czysto towarzyskim spotkaniu. Zostajesz przedstawiony/przedstawiona pięciu nieznanym ci osobom. Czy łatwo będzie ci przypomnieć sobie ich twarze na wspomnienie ich nazwisk następnego dnia?\na) Będziesz pamiętać większość z nich.\nb) Będziesz pamiętać tylko niektóre.\nc) Rzadko zapamiętasz którekolwiek z nich."
                    , "Pytanie 6:\nCzy łatwe były dla ciebie ortografia i pisanie wypracowań we wczesnych latach szkolnych?\na) Obie rzeczy były dość łatwe.\nb) Jedna z nich była łatwa.\nc) Żadna nie była łatwa."
                    , "Pytanie 7:\nSpostrzegasz miejsce na parkingu, ale musisz wjechać na nie tyłem, a miejsce jest dość wąskie:\na) Szukasz innego miejsca.\nb) Wjeżdżasz tyłem... ale uważnie.\nc) Nie myśląc wiele, wjeżdżasz tyłem."
                    , "Pytanie 8:\nPo trzech dniach spędzonych w nieznanej ci miejscowościktoś cię pyta, gdzie jest północ:\na) Jest mało prawdopodobne, żebyś umiał/umiała ją wskazać.\nb) Nie jesteś pewien/pewna, ale mając chwilę czasu zorientujesz się.\nc) Wskazujesz północ."
                    , "Pytanie 9:\nJesteś w poczekalni u dentysty z paroma osobami tej samej płci co ty. Jak blisko którejś z nich możesz usiąść nie czując się niezręcznie?\na) Mniej niż 15 cm.\nb) 15 do 60 cm.\nc) Ponad 60 cm."
                    , "Pytanie 10:\nOdwiedzasz nowego sąsiada/sąsiadkę i prowadzicie rozmowę. W tle słychać kapanie z kranu. Poza tym w pokoju jest cicho.\na) Zwróciłbyś/Zwróciłabyś uwagę na ten dźwięk natychmiast i próbował/próbowała go ignorować.\nb) Gdybyś zwrócił/zwróciła na niego uwagę, prawdopodobnie byś o tym wspomniał/wspomniała.\nc) W ogóle by ci ten dźwięk nie przeszkadzał."};
        }
    }

    public String getQuestion(int questionNumber) {
        return questions[questionNumber - 1];
    }
}
