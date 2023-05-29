import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            if (dateien.length > 0) {
                for (File datei : dateien) {
                    // Überprüfe, ob es sich um eine Datei handelt
                    switch (methode){
                        case "mmbü":
                            if (datei.isFile()) {
                                System.out.println("Verarbeite Datei: " + datei.getName());
                                if(!uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())))){
                                algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())));
                                writeToMarkdown(algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))));
                                }else{
                                    System.out.println("--------------------------------------");
                                }
                            }
                            break;
                        case "mcdc":
                            if (datei.isFile()) {
                                System.out.println("Verarbeite Datei: " + datei.getName());
                                if(!uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())))){
                                    algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath())));
                                    writeToMarkdown(algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei.getAbsolutePath()))));
                                }else{
                                    System.out.println("--------------------------------------");
                                }
                            }
                            break;
                        default:
                            System.out.println("Methode nicht vorhanden");
                            return;
                    }

                }
            }else {
                System.out.println("Keine Dateien im Ordner vorhanden");
            }
        }else {
            System.out.println("Kein Ordner vorhanden");
        }
    }

    public static void writeToMarkdown(List<List<String>> table){
        System.out.println("| --- | --- | --- | --- |");
        for (List<String> tmpZeile : table) {
            System.out.print("|  ");
            for (int j = 0; j < tmpZeile.size(); j++) {
                if (j == tmpZeile.size() - 1) {
                    System.out.println(tmpZeile.get(j) + " |");
                } else {
                    System.out.print(tmpZeile.get(j) + " |  ");
                }
            }
        }
    }

    public static void userEingabe(Algorithmen algorithmen, Scanner eingabewert){

        System.out.println("-------------------------------------- " +
                "\nGeben Sie die entsprechende Zahl ein für die Aktion, die Sie ausführen möchten." +
                "\n 1 - Ausführen des MMBÜ " +
                "\n 2 - Ausführen des MCDC " +
                "\n 3 - Ausführen beider Coverage-Maßen" +
                "\n-------------------------------------- ");
        System.out.print("Geben Sie hier ihre Nummer ein: ");
        int nummerCoverage = eingabewert.nextInt();
        String coverage = eingabeCoverage(nummerCoverage);
        if(coverage.equals("existiert nicht")){
            System.out.println("--------------------------------------\nSie haben etwas Falsches eingegeben. Vorgang wird abgebrochen.\n--------------------------------------");
        }else {
            System.out.println("--------------------------------------" +
                    "\nGeben Sie nun an die entsprechende Zahl ein für die Auswahl der Datei, die Sie ausführen möchten" +
                    "\n 1 - Ausführen einer bestimmten Datei aus unserem System " +
                    "\n 2 - Ausführen aller unserer Dateien im System \n 3 - Ausführen Ihrer Datei" +
                    "\n 4 - Ausführen der Aufgabe 2.2 b)" +
                    "\n-------------------------------------- ");
            System.out.print("Geben Sie hier ihre Nummer ein: ");
            int nummerDatei = eingabewert.nextInt();
            System.out.println("--------------------------------------\n--------------------------------------");

            //Ab hier Ausgabe der jeweiligen Auswahl
            eingabeDatei(coverage, nummerDatei, algorithmen, eingabewert);
        }
            System.out.println("--------------------------------------\nWählen Sie die nächste Aktion aus.\n 1 - Neustart \n 2 - Beenden");
            System.out.print("Geben Sie hier ihre Nummer ein: ");
            int nummerAktion = eingabewert.nextInt();
            if (nummerAktion == 1){
                userEingabe(algorithmen, eingabewert);
            }else{
                System.out.println("--------------------------------------\n---------System wird beendet----------\n--------------------------------------");
            }}

    public static String eingabeCoverage(int nummerCoverage) {
        String antwort = "existiert nicht";
        switch (nummerCoverage){
            case 1: antwort = "mmbü"; break;
            case 2: antwort = "mcdc"; break;
            case 3: antwort = "beideCoverageMaßen";break;
            default:  System.out.println("Nicht verfügbar");
        }
        return antwort;}

    public static void eingabeDatei(String coverage, int nummerDatei, Algorithmen algorithmen, Scanner eingabewert){
        switch(nummerDatei){
            case 1:
                System.out.println(" 1 - Ausführen von der Datei ex0.md" +
                        "\n 2 - Ausführen von der Datei ex1.md" +
                        "\n 3 - Ausführen von der Datei ex2.md" +
                        "\n 4 - Ausführen von der Datei ex3.md" +
                        "\n 5 - Ausführen von der Datei ex4.md" +
                        "\n 6 - Ausführen von der Datei ex5.md" +
                        "\n 7 - Ausführen von der Datei ex6.md" +
                        "\n 8 - Ausführen von der Datei ex7.md" +
                        "\n 9 - Ausführen von der Datei exercise1.md" +
                        "\n 10 - Ausführen von der Datei exercise2.md" +
                        "\n 11 - Ausführen von der Datei exercise22b.md"+
                        "\n 12 - Ausführen von der Datei leererFehler.md");
                System.out.print("Geben Sie hier ihre Nummer ein: ");
                int datei = eingabewert.nextInt();
                String dateiString = dateiAuswahl(datei);
                if(dateiString.equals("existiert nicht")){
                    System.out.println("Sie haben etwas Falsches eingegeben. Vorgang wird abgebrochen.");
                }else{
                    einzelneDatei(dateiString, coverage, algorithmen);}
                break;
            case 2:
                if(coverage.equals("beideCoverageMaßen")){
                    System.out.println("mmbü" + ": ");
                    verarbeiteDateienImOrdner("src/exercises", "mmbü");
                    System.out.println("mcdc" + ": ");
                    verarbeiteDateienImOrdner("src/exercises", "mcdc");
                }else {
                    System.out.println(coverage + ": ");
                    verarbeiteDateienImOrdner("src/exercises", coverage);}
                break;
            case 3:
                // "C:/Users/Carina/Desktop/exercise1.md"
                System.out.print("Geben Sie Ihre Datei ein: ");
                String dateiMeins = eingabewert.next();
                File file = new File(dateiMeins);
                if(file.exists()){
                    einzelneDatei(dateiMeins, coverage, algorithmen);
                }else{
                    System.out.println("--------------------------------------\nSie haben etwas Falsches eingegeben. Vorgang wird abgebrochen.\n--------------------------------------");
                }
                break;
            case 4: System.out.println("Aufgabe 2.2 b)");
                String dateiAufgabe = "src/exercises/exercise22b.md";
                einzelneDatei(dateiAufgabe, coverage, algorithmen);break;
            default:  System.out.println("Nicht verfügbar");
        }}

    public static String dateiAuswahl(int datei) {
        return switch (datei) {
            case 1 -> "src/exercises/ex0.md";
            case 2 -> "src/exercises/ex1.md";
            case 3 -> "src/exercises/ex2.md";
            case 4 -> "src/exercises/ex3.md";
            case 5 -> "src/exercises/ex4.md";
            case 6 -> "src/exercises/ex5.md";
            case 7 -> "src/exercises/ex6.md";
            case 8 -> "src/exercises/ex7.md";
            case 9 -> "src/exercises/exercise1.md";
            case 10 -> "src/exercises/exercise2.md";
            case 11 -> "src/exercises/exercise22b.md";
            case 12 -> "src/exercises/leererFehler.md";
            default -> "existiert nicht";};}

    public static void einzelneDatei(String datei, String coverage, Algorithmen algorithmen){
        if(coverage.equals("mcdc")){
            System.out.println("--------------------------------------");
            System.out.println("MCDC " + "der Datei " + datei);
            if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei))) == false) {
                writeToMarkdown(algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei))));
            }
            else{
                System.out.println("--------------------------------------");
            }
        }else if(coverage.equals("mmbü")){
            System.out.println("--------------------------------------");
            System.out.println("MMBÜ " + "der Datei " + datei);
            if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei))) == false) {
                writeToMarkdown(algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei))));}
        }else if(coverage.equals("beideCoverageMaßen")){
            System.out.println("--------------------------------------");
            System.out.println("MCDC " + "der Datei " + datei);
            if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei))) == false) {
                writeToMarkdown(algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown(datei))));
            }else{
                System.out.println("--------------------------------------");
            }
            System.out.println("--------------------------------------");
            System.out.println("MMBÜ " + "der Datei " + datei);
            if(uberpruefeObFehlerhaft(create2DArrayList(wahrheitstabelleAusMarkdown(datei))) == false) {
                writeToMarkdown(algorithmen.mmbue(create2DArrayList(wahrheitstabelleAusMarkdown(datei))));
            }else{
                System.out.println("--------------------------------------");
            }
            System.out.println("--------------------------------------");
        }else{System.out.println("--------------------------------------");}}

    public static void main(String[] args, boolean keinTest) {
        //System.out.println(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/ex0.md")));
        //System.out.println(mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md"))));
        //writeToMarkdown(mmbue(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/exercise2.md"))), null);

        Algorithmen algorithmen = new Algorithmen();
        if(keinTest){
            Scanner scanner = new Scanner(System.in);
            userEingabe(algorithmen, scanner);
        }else{
            System.out.println("Ist ein Test");
        }

        /*System.out.println("--------------------------------------");
        System.out.println("MMBÜ: ");
        verarbeiteDateienImOrdner("src/exercises", "mmbü");
        System.out.println("--------------------------------------");
        System.out.println("MCDC: ");
        verarbeiteDateienImOrdner("src/exercises", "mcdc");
        System.out.println("--------------------------------------");*/

        //für System.out.println einkommentieren in Algorithmen bei mcdc
        //algorithmen.mcdc(create2DArrayList(wahrheitstabelleAusMarkdown("src/exercises/ex0.md")));

    }
}
