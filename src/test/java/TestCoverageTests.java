import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        WahrheitstabellenReader.verarbeiteDateienImOrdner("src/exercises/leer", "mmbü");

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();

        assertEquals("Kein Ordner vorhanden", ausgabe);
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
    public void mainTest(){
        WahrheitstabellenReader.main(new String[]{});
    }

    @Test
    public void userEingabeTestNeustart(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n4\n1\n1\n4\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("System wird neu gestartet"));

    }
  @Test
  public void userEingabeTestFehlerhafteEingabeCoverage(){
      // Leite die Standardausgabe um, um die Ausgabe zu erfassen
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      System.setOut(new PrintStream(outputStream));

      Algorithmen algorithmen = new Algorithmen();
      Scanner scanner = new Scanner("6\n2"); //beenden
      WahrheitstabellenReader.userEingabe(algorithmen, scanner);

      // Erfasse die Ausgabe
      String ausgabe = outputStream.toString().trim();
      assertTrue(ausgabe.contains("Sie haben etwas Falsches für die Coverage Maße eingegeben."));
  }

    @Test
    public void userEingabeTestFehlerhafteEingabeDateiImSystem(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n1\n13\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Sie haben etwas Falsches für die Dateiauswahl im System eingegeben."));
    }

    @Test
    public void userEingabeTestEinzelneDateiAufrufBeiDateiImSystem(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n1\n1\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains(" 1 - Ausführen von der Datei ex0.md"));
    }

    @Test
    public void userEingabeTestMMBUE(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n4\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MMBÜ der Datei src/exercises/exercise22b.md"));
    }

    @Test
    public void userEingabeTestMCDC(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("2\n4\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MCDC der Datei src/exercises/exercise22b.md"));

    }

    @Test
    public void userEingabeTestBeideCoverageMaße(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("3\n4\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MCDC der Datei src/exercises/exercise22b.md") && ausgabe.contains("MMBÜ der Datei src/exercises/exercise22b.md") );
    }

    @Test
    public void userEingabeTestBeideCoverageMaßeMitAllenDateienImSystem(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("3\n2\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Ausführen aller unserer Dateien im System mit mmbü und mcdc") );
    }

    @Test
    public void userEingabeTestCoverageMaßeMitAllenDateienImSystemMitEinerCoverage(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n2\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Ausführen aller unserer Dateien im System mit mmbü") );
    }

    @Test
    public void userEingabeTestCoverageMaßeMitEigenerDatei(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n3\nsrc/exercises/exercise1.md\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MMBÜ der Datei src/exercises/exercise1.md") );
    }

    @Test
    public void userEingabeTestCoverageMaßeMitEigenerFehlerhafterDatei(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n3\nC:/Users/Carina/Fesktop/exercise1.md\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Sie haben etwas Falsches für die Datei eingegeben.") );
    }

    @Test
    public void userEingabeTesteinzelneDateiFehlerMMBUe(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("1\n1\n4\n2"); //beenden
        WahrheitstabellenReader.userEingabe(algorithmen, scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Datei fehlerhaft: Falsche Werte") );
    }

    //todo
    @Test
    public void eingabeCoverageDefault(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        WahrheitstabellenReader.eingabeCoverage(7);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Nicht verfügbar") );
    }

    //todo
    @Test
    public void eingabeDateiDefault(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        Scanner scanner = new Scanner("");
        WahrheitstabellenReader.eingabeDatei("mmbü",7, algorithmen,scanner);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("Nicht verfügbar") );
    }

    //todo
    @Test
    public void einzelneDateiMMBUE(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.einzelneDatei("src/exercises/ex2.md","mmbü", algorithmen);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MMBÜ der Datei src/exercises/ex2.md") );
    }

    @Test
    public void einzelneDateiMCDC(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.einzelneDatei("src/exercises/ex2.md","mcdc", algorithmen);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MCDC der Datei src/exercises/ex2.md") );
    }

    //todo
    @Test
    public void einzelneDateiBeide(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.einzelneDatei("src/exercises/ex2.md","beideCoverageMaßen", algorithmen);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("MCDC der Datei src/exercises/ex2.md") && ausgabe.contains("MMBÜ der Datei src/exercises/ex2.md") );
    }

    //todo
    @Test
    public void einzelneDateiNichts(){
        // Leite die Standardausgabe um, um die Ausgabe zu erfassen
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.einzelneDatei("src/exercises/ex3.md","uff", algorithmen);

        // Erfasse die Ausgabe
        String ausgabe = outputStream.toString().trim();
        assertTrue(ausgabe.contains("uff") );
    }

    @Test
    public void userEingabeTestDateiAuswahl1(){
        String datei = WahrheitstabellenReader.dateiAuswahl(1);
        assertEquals("src/exercises/ex0.md",datei);
    }
    @Test
    public void userEingabeTestDateiAuswahl2(){
        String datei = WahrheitstabellenReader.dateiAuswahl(2);
        assertEquals("src/exercises/ex1.md",datei);
    }
    @Test
    public void userEingabeTestDateiAuswahl3(){
        String datei = WahrheitstabellenReader.dateiAuswahl(3);
        assertEquals("src/exercises/ex2.md",datei);
    }
    @Test
    public void userEingabeTestDateiAuswahl4(){
        String datei = WahrheitstabellenReader.dateiAuswahl(4);
        assertEquals("src/exercises/ex3.md",datei);

    }

    @Test
    public void userEingabeTestDateiAuswahl5(){
        String datei = WahrheitstabellenReader.dateiAuswahl(5);
        assertEquals("src/exercises/ex4.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl6(){
        String datei = WahrheitstabellenReader.dateiAuswahl(6);
        assertEquals("src/exercises/ex5.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl7(){
        String datei = WahrheitstabellenReader.dateiAuswahl(7);
        assertEquals("src/exercises/ex6.md",datei);
    }
    @Test
    public void userEingabeTestDateiAuswahl8(){
        String datei = WahrheitstabellenReader.dateiAuswahl(8);
        assertEquals("src/exercises/ex7.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl9(){
        String datei = WahrheitstabellenReader.dateiAuswahl(9);
        assertEquals("src/exercises/exercise1.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl10(){
        String datei = WahrheitstabellenReader.dateiAuswahl(10);
        assertEquals("src/exercises/exercise2.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl11(){
        String datei = WahrheitstabellenReader.dateiAuswahl(11);
        assertEquals("src/exercises/exercise22b.md",datei);
    }

    @Test
    public void userEingabeTestDateiAuswahl12(){
        String datei = WahrheitstabellenReader.dateiAuswahl(12);
        assertEquals("src/exercises/leererFehler.md",datei);
    }


}
