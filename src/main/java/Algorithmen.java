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

    public List<List<String>> mcdc(List<List<String>> tabelle) {
        //System.out.println(tabelle);

        //Liste für signifikante Paare
        List<List<String>> significantPairs = new ArrayList<>();

        //int signifikante = 0;

        //Jetzt müssen Paare gefunden werden,
        // die die gleichen anderen atomaren Bedingungen haben,
        // aber die eine anders
        // und die Condition jeweils anders ist
        //------------------------------------------------------

        for (int signifikante = 0; signifikante < tabelle.get(0).size() - 1; signifikante++) {


            //Betrachtung Signifikante 1 in allen Zeilen
            //System.out.println("Signifikante " + signifikante);

            boolean paarFound = false;

            //hier wird durch jede Zeile gegangen also ins erste Array
            for (int i = 0; i < tabelle.size(); i++) {
                //System.out.println("----------Neue-Zeile----------");

                // ein paar wurde bereits gefunden
                if (paarFound) {
                    //Was wird dann gemacht?
                } else {
                    List<String> tmpZeile = tabelle.get(i); //derzeitige Zeile
                    List<String> newTmpZeile = new ArrayList<>(tmpZeile); //derzeitige veränderte Zeile

                    if (newTmpZeile.get(signifikante).equals("0")) {
                        newTmpZeile.set(signifikante, "1");
                    } else {
                        newTmpZeile.set(signifikante, "0");
                    }

                    //hier muss nach den richtigen Paaren gesucht werden
                    for (List<String> s : tabelle) {

                        List<String> derzeitigeZeileDerTabelle = s.subList(0, s.size() - 1); //ohne Condition
                        List<String> derzeitigeZeileDerTabelleCondition = s.subList(s.size() - 1, s.size());

                        List<String> derzeitigeBetrachteteVeraenderteZeile = newTmpZeile.subList(0, newTmpZeile.size() - 1); //ohne Condition
                        List<String> derzeitigeBetrachteteVeraenderteZeileCondition = newTmpZeile.subList(newTmpZeile.size() - 1, newTmpZeile.size());

                        if (derzeitigeZeileDerTabelle.equals(derzeitigeBetrachteteVeraenderteZeile) //gleiches Aussehen der Zeilen ohne Condition
                                && !(derzeitigeZeileDerTabelleCondition.equals(derzeitigeBetrachteteVeraenderteZeileCondition))) { //Condition stimmt nicht überein

                            significantPairs.add(tmpZeile);
                            //System.out.println(tmpZeile);
                            significantPairs.add(s);
                            //System.out.println(s);
                            paarFound = true;
                        }
                    }
                }
            }
        }
        return significantPairs.stream().distinct().collect(Collectors.toList());
    }
}
