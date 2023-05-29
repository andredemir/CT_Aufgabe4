import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCoverageTests {

    //Done
    @Test
    public void ueberpruefeObFehlerHaftEmptyTest(){
        List<List<String>> emptyList = new ArrayList<>();
        Assertions.assertTrue(WahrheitstabellenReader.uberpruefeObFehlerhaft(emptyList));
    }

    //Done
    @Test
    public void exerciseTabelle2mmbueTest(){
        Algorithmen algorithmen = new Algorithmen();
        List<List<String>> list = algorithmen.mmbue(WahrheitstabellenReader.
                create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise2.md")));

        List<List<String>> list2 = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of("1", "0", "0", "0")),
                        new ArrayList<>(List.of("0", "1", "0", "0")),
                        new ArrayList<>(List.of("0", "0", "1", "0")),
                        new ArrayList<>(List.of("1", "0", "1", "1")),
                        new ArrayList<>(List.of("0", "1", "1", "1")),
                        new ArrayList<>(List.of("1", "1", "1", "0"))
                ));
        assertEquals(list2, list);
    }

    //Done
    @Test
    public void exerciseTabelle2mcdcTest(){
        Algorithmen algorithmen = new Algorithmen();
        List<List<String>> list = algorithmen.mcdc(WahrheitstabellenReader.create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise2.md")));
        List<List<String>> list2 = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of("0", "0", "1", "0")),
                        new ArrayList<>(List.of("1", "0", "1", "1")),
                        new ArrayList<>(List.of("0", "1", "1", "1")),
                        new ArrayList<>(List.of("1", "0", "0", "0"))
                ));
        assertEquals(list2, list);
    }

    @Test
    public void ueberpruefeObFehlerHaftWrongValueTest(){
        List<List<String>> list2 = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of("2", "3", "1", "0")),
                        new ArrayList<>(List.of("1", "0", "1", "1")),
                        new ArrayList<>(List.of("0", "1", "1", "1")),
                        new ArrayList<>(List.of("1", "0", "0", "0"))
                ));
        Assertions.assertTrue(WahrheitstabellenReader.uberpruefeObFehlerhaft(list2));
    }

    @Test
    public void ueberpruefeObFehlerHaftRightValueTest(){
        List<List<String>> list3 = new ArrayList<>(
                List.of(
                        new ArrayList<>(List.of("1", "0", "1", "0")),
                        new ArrayList<>(List.of("1", "0", "1", "1")),
                        new ArrayList<>(List.of("0", "1", "1", "1")),
                        new ArrayList<>(List.of("1", "0", "0", "0"))
                ));
        Assertions.assertFalse(WahrheitstabellenReader.uberpruefeObFehlerhaft(list3));
    }

    //Done hat kein Rückgabewert
    @Test
    public void verarbeiteDateienImOrdnerTest(){
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercises", "mcdc");
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercises", "mmbü");
    }

    //TODO//: Noch ein Test für Nullwerte für die Branches
    @Test
    public void verarbeiteDateienImOrdner_DefaultCase_Test() {

        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Rufe die verarbeiteDateienImOrdner-Methode mit dem ungültigen Testfall auf
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercises", "mmbe4");

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();

        assertEquals("Methode nicht vorhanden", ausgabe);
    }

    //TODO//: Noch ein Test für Nullwerte für die Branches
    @Test
    public void verarbeiteDateienImOrdner_KeineDateienImOrdner_Test() {

        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Rufe die verarbeiteDateienImOrdner-Methode mit dem ungültigen Testfall auf
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercises/exercisesEmpty", "mmbü");

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();

        assertEquals("Keine Dateien im Ordner vorhanden", ausgabe);
    }

    @Test
    public void verarbeiteDateienImOrdner_OrdnerNichtVorhanden_Test() {

        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Rufe die verarbeiteDateienImOrdner-Methode mit dem ungültigen Testfall auf
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercisesEmpty", "mmbü");

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();

        assertEquals("Kein Ordner vorhanden", ausgabe);
    }

    //Done hat kein Rückgabewert
    @Test
    public void writeToMarkdownTest(){
        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.writeToMarkdown(algorithmen.mmbue(WahrheitstabellenReader.create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
        WahrheitstabellenReader.writeToMarkdown(algorithmen.mcdc(WahrheitstabellenReader.create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
    }


    @Test
    public void userEingabeTestMitFehlerhafteEingabe(){
            // Erzeuge eine Instanz von Algorithmen für den Testfall
            Algorithmen algorithmen = new Algorithmen(/* Vordefinierte Werte */);

            // Definiere die gewünschten Eingabewerte für den Testfall
            String eingabe = "5";

            // Leite die Standardeingabe um, um die vordefinierte Eingabe bereitzustellen
            InputStream inputStream = new ByteArrayInputStream(eingabe.getBytes());
            System.setIn(inputStream);

            // Rufe die userEingabe-Methode auf
            WahrheitstabellenReader.userEingabe(algorithmen);
    }

    @Test
    public void userEingabeTestNeustart(){
        // Erzeuge eine Instanz von Algorithmen für den Testfall
        Algorithmen algorithmen = new Algorithmen(/* Vordefinierte Werte */);

        // Definiere die gewünschten Eingabewerte für den Testfall
        String eingabe = "1\n2\n1\n1\n2\n2";

        // Leite die Standardeingabe um, um die vordefinierte Eingabe bereitzustellen
        InputStream inputStream = new ByteArrayInputStream(eingabe.getBytes());
        System.setIn(inputStream);

        // Rufe die userEingabe-Methode auf
        WahrheitstabellenReader.userEingabe(algorithmen);
    }

    @Test
    public void userEingabeNormalerAblaufTest(){
        // Erzeuge eine Instanz von Algorithmen für den Testfall
        Algorithmen algorithmen = new Algorithmen(/* Vordefinierte Werte */);

        // Definiere die gewünschten Eingabewerte für den Testfall
        String eingabe = "1\n2\n2";

        // Leite die Standardeingabe um, um die vordefinierte Eingabe bereitzustellen
        InputStream inputStream = new ByteArrayInputStream(eingabe.getBytes());
        System.setIn(inputStream);

        // Rufe die userEingabe-Methode auf
        WahrheitstabellenReader.userEingabe(algorithmen);
    }

  //@Test
  //public void mainTest(){
  //    WahrheitstabellenReader.main(new String[]{});
  //}

}
