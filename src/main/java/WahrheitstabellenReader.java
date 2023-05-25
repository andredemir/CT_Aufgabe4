import java.io.BufferedReader;
import java.io.File;
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

    //array null
    //array buchstaben
    //array anderes außer 0 und 1
    public static boolean uberpruefeObFehlerhaft(List<List<String>> array){

        if(array.isEmpty()){
            System.out.println("------Datei fehlerhaft: Datei ist leer------");
            return true;
        }
        for(List<String> s : array){
            for(String t : s){
                if(!((t.equals("0"))||(t.equals("1")))){
                    System.out.println("------Datei fehlerhaft: Falsche Werte------");
                    return true;
                }
            }
        }
        return false;
    }

    public static void verarbeiteDateienImOrdner(String ordnerpfad, String methode) {
        Algorithmen algorithmen = new Algorithmen();
        File ordner = new File(ordnerpfad);

        if (ordner.isDirectory()) {
            File[] dateien = ordner.listFiles();

            if (dateien != null) {
                for (File datei : dateien) {
                    // Überprüfe, ob es sich um eine Datei handelt
                    switch (methode){
                        case "mmbü":
                            if (datei.isFile()) {
                                System.out.println("Verarbeite Datei: " + datei.getName());
                                if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))) == false){
                                algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())));
                                writeToMarkdown(algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))));
                                }
                            }
                            break;
                        case "mcdc":
                            if (datei.isFile()) {
                                System.out.println("Verarbeite Datei: " + datei.getName());
                                if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))) == false){
                                    algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())));
                                    writeToMarkdown(algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))));
                                }
                            }
                            break;
                    }

                }
            }
        }
    }

    public static void writeToMarkdown(List<List<String>> table){
        System.out.println("| --- | --- | --- | --- |");
        for (int i = 0; i < table.size(); i++) {
            List<String> tmpZeile = table.get(i);
            System.out.print("|  ");
            for (int j = 0; j < tmpZeile.size(); j++) {
                if (j == tmpZeile.size()-1){
                    System.out.println(tmpZeile.get(j) + " |");
                } else {
                    System.out.print(tmpZeile.get(j) + " |  ");
                }
            }
        }
    }

    public static void tabelleAnzeigen(List<String> tabelle){
        for (String zeile : tabelle) {
            String tmp = zeile.replaceAll("[\\s|]", "");
            System.out.println(tmp);
        }
    }

    public static void main(String[] args) {
        System.out.println(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/ex0.md")));
        //System.out.println(mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md"))));
        //writeToMarkdown(mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md"))), null);

        Algorithmen algorithmen = new Algorithmen();

        /*System.out.println("--------------------------------------");
        System.out.println("MMBÜ: ");
        verarbeiteDateienImOrdner("src/exercises", "mmbü");
        System.out.println("--------------------------------------");
        System.out.println("MCDC: ");
        verarbeiteDateienImOrdner("src/exercises", "mcdc");
        System.out.println("--------------------------------------");*/

        //für System.out.println einkommentieren in Algorithmen bei mcdc
        //algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/ex0.md")));

        //aufgabe22b(algorithmen);

    }

    public static void aufgabe22b(Algorithmen algorithmen){

        System.out.println("Aufgabe 2.2 b)");
        System.out.println("--------------------------------------");
        System.out.println("MCDC: ");
        writeToMarkdown(algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
        System.out.println("--------------------------------------");
        System.out.println("MMBÜ: ");
        writeToMarkdown(algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise22b.md"))));
        System.out.println("--------------------------------------");

    }
}
