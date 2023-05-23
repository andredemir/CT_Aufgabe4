import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WahrheitstabellenReader {
    public static List<String> wahrheitstabelleAusMarkdown(String dateipfad) {
        List<String> zeilen = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(dateipfad))) {
            String zeile;
            while ((zeile = reader.readLine()) != null) {
                zeilen.add(zeile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return zeilen;
    }

    public static List<List<String>> create2DArrayList(List<String> tabelle) {
        List<List<String>> array = new ArrayList<>();
        for (int k = 2; k < tabelle.size(); k++) {
            array.add(new ArrayList<>());
        }
        for (int i = 2; i < tabelle.size(); i++) {
            String tmp = tabelle.get(i).replaceAll("[|\\s]", "");
            for (int j = 0; j < tmp.length(); j++) {
                array.get(i - 2).add(Character.toString(tmp.charAt(j)));
            }
        }

        return array;
    }

    public static List<List<String>> mmbue(List<List<String>> table) {
        List<List<String>> significant = new ArrayList<>();

        for (int i = 0; i < table.size(); i++) {
            List<String> tmpZeile = table.get(i);

            for (int j = 0; j < tmpZeile.size() - 1; j++) {
                List<String> newTmpZeile = new ArrayList<>(tmpZeile);

                if (newTmpZeile.get(j).equals("0")) {
                    newTmpZeile.set(j, "1");
                } else {
                    newTmpZeile.set(j, "0");
                }
                for (List<String> s : table) {
                    if (s.subList(0, s.size()-1).equals(newTmpZeile.subList(0, newTmpZeile.size()-1)) && !(s.subList(s.size()-1, s.size()).equals(newTmpZeile.subList(newTmpZeile.size()-1, newTmpZeile.size())))){
                        significant.add(tmpZeile);
                    }
                }
            }
        }
        //problem for future us: duplicates
        return significant.stream().distinct().collect(Collectors.toList());
    }

    public boolean isSignificant (List<List<String>> table){
        boolean significant = false;

        return significant;
    }

    public static void mcdc(){
        //Anzahl Spalten überprüfen und Minus eins rechnen für Condition
        // alle außer eine fixieren
        //ähnliches Muster suchen, wo sich nicht fixierte ändert und überprüfen, ob Condition ändert
        //wenn ja -> signifikante
        //wenn nein -> ignorieren
    }




    public static void writeToMarkdown(List<List<String>> table, String dateipfad){

    }




    public static void tabelleAnzeigen(List<String> tabelle){
        for (String zeile : tabelle) {
            String tmp = zeile.replaceAll("[\\s|]", "");
            System.out.println(tmp);
        }
    }

    public static void main(String[] args) {
        //tabelleAnzeigen(wahrheitstabelleAusMarkdown("src/exercises/exercise1.md"));
        System.out.println(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md")));
        System.out.println(mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md"))));
    }
}
