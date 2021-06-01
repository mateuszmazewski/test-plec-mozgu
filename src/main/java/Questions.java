public class Questions {
    private String[] questions;

    public Questions(int testNumber) {
        if (testNumber == 1) {
            questions = new String[]{
                    "Pytanie 1:\nSłyszysz niewyraźne miauczenie. Jak łatwo możesz zlokalizować kota bez rozglądania się wokoło?\na) Jeśli się zastanowisz, możesz go wskazać.\nb) Możesz go wskazać bez zastanowienia.\nc) Nie jesteś pewien/pewna, czy mógłbyś/mogłabyś go wskazać."
                    , "Pytanie 2:\nJak łatwo możesz zapamiętać piosenkę, którą przed chwilą usłyszałeś/usłyszałaś?\na) Jest to bardzo łatwe; możesz zaśpiewać jej każdy kawałek czysto.\nb) Możesz ją zaśpiewać czysto pod warunkiem, że jest prosta i rytmiczna.\nc) Sprawia ci to trudność."
                    , "Pytanie 3:\nDzwoni do ciebie osoba, którą spotkałeś/spotkałaś kilka razy. Czy łatwo ci rozpoznać jej głos w ciągu kilku sekund, zanim się przedstawi?\na) Jest to dla ciebie łatwe.\nb) Rozpoznałbyś/Rozpoznałabyś głos przynajmniej w połowie przypadków.\nc) Sprawia ci to trudność."
                    , "Pytanie 4:\nJesteś w towarzystwie zaprzyjaźnionych ze sobą małżeństw. Dwie osoby spośród obecnych mają potajemny romans. Czy wykryłbyś/wykryłabyś ich związek?\na) Niemal zawsze.\nb) W połowie przypadków\nc) Rzadko."
                    , "Pytanie 5:\nJesteś na dużym, czysto towarzyskim spotkaniu. Zostajesz przedstawiony/przedstawiona pięciu nieznanym ci osobom. Czy łatwo będzie ci przypomnieć sobie ich twarze na wspomnienie ich nazwisk następnego dnia?\na) Będziesz pamiętać większość z nich.\nb) Będziesz pamiętać tylko niektóre.\nc) Rzadko zapamiętasz którekolwiek z nich."
                    , "Pytanie 6:\nCzy łatwe były dla ciebie ortografia i pisanie wypracowań we wczesnych latach szkolnych?\na) Obie rzeczy były dość łatwe.\nb) Jedna z nich była łatwa.\nc) Żadna nie była łatwa."
                    , "Pytanie 7:\nSpostrzegasz miejsce na parkingu, ale musisz wjechać na nie tyłem, a miejsce jest dość wąskie:\na) Szukasz innego miejsca.\nb) Wjeżdżasz tyłem... ale uważnie.\nc) Nie myśląc wiele, wjeżdżasz tyłem."
                    , "Pytanie 8:\nPo trzech dniach spędzonych w nieznanej ci miejscowości ktoś cię pyta, gdzie jest północ:\na) Jest mało prawdopodobne, żebyś umiał/umiała ją wskazać.\nb) Nie jesteś pewien/pewna, ale mając chwilę czasu zorientujesz się.\nc) Wskazujesz północ."
                    , "Pytanie 9:\nJesteś w poczekalni u dentysty z paroma osobami tej samej płci co ty. Jak blisko którejś z nich możesz usiąść nie czując się niezręcznie?\na) Mniej niż 15 cm.\nb) 15 do 60 cm.\nc) Ponad 60 cm."
                    , "Pytanie 10:\nOdwiedzasz nowego sąsiada/sąsiadkę i prowadzicie rozmowę. W tle słychać kapanie z kranu. Poza tym w pokoju jest cicho.\na) Zwróciłbyś/Zwróciłabyś uwagę na ten dźwięk natychmiast i próbował/próbowała go ignorować.\nb) Gdybyś zwrócił/zwróciła na niego uwagę, prawdopodobnie byś o tym wspomniał/wspomniała.\nc) W ogóle by ci ten dźwięk nie przeszkadzał."
            };
        } else if (testNumber == 2) {
            questions = new String[]{
                    "Pytanie 1:\nPrzy czytaniu mapy lub drogowskazów:\na) masz trudności i często prosisz o pomoc\nb) obracasz mapę, żeby patrzeć w kierunku, w którym idziesz\nc) nie masz żadnych trudności w czytaniu map i drogowskazów"
                    , "Pytanie 2:\nGotujesz skomplikowane danie przy włączonym radiu, dzwoni znajomy. Co robisz?\na) zostawiasz radio włączone i kontynuujesz gotowanie, rozmawiając przez telefon\nb) wyłączasz radio, rozmawiasz i nadal gotujesz\nc) mówisz, że za chwilę oddzwonisz, gdy tylko skończysz gotować"
                    , "Pytanie 3:\nPrzyjaciele wybierają się do ciebie z wizytą i proszą o wskazówki, jak dotrzeć do twojego nowego domu. Co robisz?\na) rysujesz im mapkę z wyraźnie zaznaczoną trasą i im ją wysyłasz lub wzywasz kogoś innego, aby im to wytłumaczył\nb) pytasz, jakie znają punkty orientacyjne, potem próbujesz im wytłumaczyć, jak dojechać na miejsce\nc) wyjaśniasz im, jak dojechać: \"Wjeżdżasz na autostradę M3 do Newcastle, zawracasz, skręcasz w lewo, dojeżdżasz do drugich świateł...\""
                    , "Pytanie 4:\nKiedy objaśniasz jakiś nowy pomysł lub koncepcję, prawdopodobnie:\na) użyjesz ołówka, papieru i gestów mowy ciała\nb) wyjaśniasz słownie, używając gestów i mowy ciała\nc) wyjaśniasz słownie, wyranie i krótko"
                    , "Pytanie 5:\nKiedy wracasz do domu po obejrzeniu wybitnego filmu, wolisz:\na) wyobrażać sobie sceny z filmu\nb) rozmawiać o scenach i o tym, co powiedziano\nc) cytować wszystko, co powiedziano w filmie"
                    , "Pytanie 6:\nW kinie wolisz siedzieć:\na) z prawej strony sali\nb) gdziekolwiek, wszystko jedno gdzie\nc) z lewej strony sali"
                    , "Pytanie 7:\nZnajomy ma urządzenie mechaniczne, które nie działa:\na) współczujesz i omawiasz z nim, co w związku z tym czuje\nb) polecasz kogoś niezawodnego, kto potrafi to naprawić\nc) próbujesz domyślić się, jak to działa, i naprawić"
                    , "Pytanie 8:\nJesteś w nieznanym miejscu i ktoś cię pyta, gdzie jest północ. Co robisz?\na) przyznajesz się, że nie wiesz\nb) po pewnym namyśle zgadujesz, gdzie jest\nc) bez trudu wskazujesz północ"
                    , "Pytanie 9:\nZnajdujesz miejsce do parkowania, ale jest ciasno. Musisz wjechać w nie tyłem. Co robisz?\na) wolisz znaleźć inne miejsce\nb) starasz się ostrożnie wpasować\nc) wjeżdżasz tyłem bez trudności"
                    , "Pytanie 10:\nOglądasz telewizję, kiedy dzwoni telefon. Co robisz?\na) podnosisz słuchawkę przy włączonym telewizorze\nb) ściszasz telewizor i podnosisz słuchawkę\nc) wyłączasz telewizor, każesz wszystkim obecnym umilknąć i wtedy podnosisz słuchawkę"
                    , "Pytanie 11:\nWłaśnie usłyszałeś/usłyszałaś nową piosenkę ulubionego artysty. Zazwyczaj:\na) bez trudności potrafisz zaśpiewać fragment piosenki\nb) potrafisz to zaśpiewać nawet później, jeśli jest to prosta piosenka\nc) trudno ci sobie przypomnieć, jak brzmiała piosenka, ale pamiętasz niektóre słowa"
                    , "Pytanie 12:\nJesteś najlepszy(-a) w przewidywaniu rezultatów dzięki:\na) intuicji\nb) podejmujesz decyzję na podstawie dostępnych informacji i przeczuć\nc) korzystasz z faktów, statystyki i danych"
                    , "Pytanie 13:\nNie możesz znaleźć kluczy. Co robisz?\na) robisz coś innego, aż sobie przypominasz\nb) robisz coś innego, ale próbujesz sobie przypomnieć, gdzie je położyłeś(-aś)\nc) w myślach odtwarzasz swoje kroki, aż sobie przypominasz, gdzie je zostawiłeś(-aś)"
                    , "Pytanie 14:\nJesteś w pokoju hotelowym i słyszysz dochodzący cię z dużej odległości głos syreny. Potrafisz:\na) wskazać dokładnie miejsce, z którego pochodzi\nb) prawdopodobnie mógłbyś (mogłabyś) je wskazać, jeżeli się skupisz\nc) nie umiesz wskazać miejsca, z którego pochodzi"
                    , "Pytanie 15:\nIdziesz na spotkanie towarzyskie i jesteś przedstawiony(-a) siedmiu, ośmiu obecnym osobom. Nazajutrz:\na) potrafisz bez trudu wyobrazić sobie ich twarze\nb) pamiętasz kilka twarzy\nc) bardziej prawdopodobne, że zapamiętasz ich nazwiska"
                    , "Pytanie 16:\nChcesz na wakacje pojechać na wieś, ale twój partner (twoja partnerka) nalega na nadmorski kurort. Aby przekonać go (ją), że twój pomysł jest lepszy:\na) powiesz mu (jej) słodkim tonem, co czujesz. Uwielbiasz wieś, a dzieci i rodzina zawsze się tam dobrze bawią\nb) powiesz mu (jej), że jeżeli wybierze się na wieś, będziesz mu (jej) wdzięczny(-a) i z przyjemnością następnym razem wybierzesz się nad morze\nc) przedstawisz fakty: wieś jest bliżej, taniej, są tam zorganizowane sporty i wypoczynek"
                    , "Pytanie 17:\nKiedy planujesz zadania na określony dzień, zazwyczaj:\na) robisz listę, abyś mógł zobaczyć, co trzeba zrobić\nb) myślisz o tym, co trzeba zrobić\nc) wyobrażasz sobie ludzi, których zobaczysz, miejsca, które odwiedzisz i rzeczy, które będziesz robić"
                    , "Pytanie 18:\nZnajomy ma kłopoty osobiste i przyszedł je z tobą omówić:\na) współczujesz mu i wykazujesz zrozumienie\nb) mówisz mu, że kłopoty nigdy nie są aż tak poważne, jak wyglądają, i wyjaśniasz mu dlaczego\nc) podajesz mu pomysły i radzisz, jak rozwiązać problem"
                    , "Pytanie 19:\nDwoje przyjaciół z różnych małżeństw ma ze sobą w tajemnicy romans. Jakie jest prawdopodobieństwo, że to wykryjesz?\na) wykryjesz bardzo wcześnie\nb) mniej więcej w połowie\nc) prawdopodobnie niczego nie zauważysz"
                    , "Pytanie 20:\nNa czym polega życie twoim zdaniem:\na) na posiadaniu przyjaciół i życiu w harmonii z otaczającymi cię ludźmi\nb) przyjaznym zachowaniu wobec innych i jednoczesnym zachowaniu osobistej niezależności\nc) osiąganiu ważnych celów, zdobywaniu szacunku innych i zyskiwaniu prestiżu i wyższego poziomu życia"
                    , "Pytanie 21:\nGdybyś miał(-a) wybór, wolałbyś/wolałabyś pracować:\na) w zespole, w którym ludzie się uzupełniają\nb) wśród innych, ale zachowując własną przestrzeń\nc) w pojedynkę"
                    , "Pytanie 22:\nCo lubisz czytać?\na) powieści, beletrystykę\nb) czasopisma i gezety\nc) publicystykę i biografie"
                    , "Pytanie 23:\nKiedy robisz zakupy, masz skłonność:\na) często kupować pod wpływem impulsu, zwłaszcza na wyprzedażach\nb) masz przygotowany plan ogólny, ale bierzesz, jak leci\nc) czytasz opakowania i porównujesz ceny"
                    , "Pytanie 24:\nWolisz iść do łóżka, obudzić się i zjeść posiłek:\na) kiedy masz ochotę\nb) zgodnie z planem, ale pora nie musi być ta sama\nc) o tej samej porze każdego dnia"
                    , "Pytanie 25:\nWłaśnie zmieniłeś(-aś) pracę i poznałeś(-aś) wiele nowych osób z personelu. Jedna z nich dzwoni do ciebie do domu:\na) z łatwością rozpoznajesz głos\nb) rozpoznajesz w połowie rozmowy\nc) masz trudności z rozpoznaniem głosu"
                    , "Pytanie 26:\nCo cię najbardziej denerwuje, kiedy się z kimś kłócisz?\na) milczenie lub brak reakcji\nb) kiedy nie rozumie twojego punktu widzenia\nc) złośliwe pytania lub komentarze"
                    , "Pytanie 27:\nJak w szkole traktowałeś(-aś) klasówki z ortografii lub pisanie wypracowań?\na) uważałeś(-aś) je za stosunkowo łatwe\nb) nie były zbyt trudne, ale wolałeś(-aś) raczej klasówki z ortografii niż wypracowania\nc) ani w jednym, ani w drugim nie byłeś(-aś) za dobry(-a)"
                    , "Pytanie 28:\nKiedy już przychodzi do tańca lub gimnastyki przy muzyce:\na) \"wyczuwasz\" muzykę, kiedy już poznasz kroki\nb) możesz wykonać niektóre ćwiczenia lub tańce, ale gubisz się przy innych\nc) masz trudności w utrzymaniu tempa i rytmu"
                    , "Pytanie 29:\nJak dobry(-a) jesteś w naśladowaniu i rozpoznawaniu głosów zwierząt?\na) niezbyt\nb) może być\nc) bardzo dobry(-a)"
                    , "Pytanie 30:\nPod koniec długiego dnia najchętniej:\na) rozmawiasz z przyjaciółmi lub rodziną, jak minął\nb) słuchasz opowieści innych o ich dniu\nc) czytasz gazetę, oglądasz telewizję i nic nie mówisz"
            };
        }
    }

    public String getQuestion(int questionNumber) {
        return questions[questionNumber - 1];
    }

    public int getNumberOfQuestions() {
        return questions.length;
    }
}
