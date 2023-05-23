import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Algorithmen {
    public List<List<String>> mmbue(List<List<String>> table) {
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

    public void mcdc(){
        //Anzahl Spalten überprüfen und Minus eins rechnen für Condition
        // alle außer eine fixieren
        //ähnliches Muster suchen, wo sich nicht fixierte ändert und überprüfen, ob Condition ändert
        //wenn ja -> signifikante
        //wenn nein -> ignorieren
    }
}
