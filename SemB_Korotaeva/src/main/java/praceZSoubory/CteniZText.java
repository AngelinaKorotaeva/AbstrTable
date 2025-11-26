package praceZSoubory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;
import tabZaznamu.Zaznam;

public class CteniZText {
    
    public CteniZText() {
    }
    
    public void ObnovovaniZTextSoubor(String soubor, SpravaOblasti spravaOblasti) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(soubor));
            String radek1, radek2, radek3;
            while ((radek1 = reader.readLine()) != null
                    && (radek2 = reader.readLine()) != null
                    && (radek3 = reader.readLine()) != null) {
                spravaOblasti.vlozZaznam(new Zaznam(Integer.parseInt(radek2), radek3));
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Chyba: " + ex.getMessage());
        }
    }
}
