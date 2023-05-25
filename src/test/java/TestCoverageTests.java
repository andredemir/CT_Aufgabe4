import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        Assertions.assertEquals(list2, list);
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
        Assertions.assertEquals(list2, list);
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


    //Done hat kein Rückgabewert
    @Test
    public void writeToMarkdownTest(){
        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.writeToMarkdown(algorithmen.mmbue(WahrheitstabellenReader.create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
        WahrheitstabellenReader.writeToMarkdown(algorithmen.mcdc(WahrheitstabellenReader.create2DArrayList(WahrheitstabellenReader.wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
    }


    //Done hat kein Rückgabewert
    @Test
    public void aufgabe22bTest(){
        Algorithmen algorithmen = new Algorithmen();
        WahrheitstabellenReader.aufgabe22b(algorithmen);
    }

    //Done
    @Test
    public void mainTest(){
        WahrheitstabellenReader.main(new String[]{"src/exercises", "mcdc"});
        WahrheitstabellenReader.main(new String[]{"src/exercises", "mmbü"});
    }

}
