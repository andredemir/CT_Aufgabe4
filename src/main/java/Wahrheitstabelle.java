public class Wahrheitstabelle {
        private Integer[][] tabelle;

        public Wahrheitstabelle(Integer[][] tabelle) {
            this.tabelle = tabelle;
        }

        public int getAnzahlZeilen() {
            return tabelle.length;
        }

        public int getAnzahlSpalten() {
            return tabelle[0].length;
        }

        public int getWert(int zeile, int spalte) {
            return tabelle[zeile][spalte];
        }

        public void setWert(int zeile, int spalte, int wert) {
            tabelle[zeile][spalte] = wert;
        }

        public void ausgeben() {
            for (Integer[] zeile : tabelle) {
                for (int wert : zeile) {
                    System.out.print(wert + " ");
                }
                System.out.println();
            }
        }
}
