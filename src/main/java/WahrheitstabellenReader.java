import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void tabelleAnzeigen(List<String> tabelle){
        for (String zeile : tabelle) {
            System.out.println(zeile);
        }
    }

    public static void main(String[] args) {
        tabelleAnzeigen(wahrheitstabelleAusMarkdown("src/exercises/exercise1.md"));
    }
}
