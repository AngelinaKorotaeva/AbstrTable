package praceZSoubory;

import enumClass.ETypProhl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Iterator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;
import spravaZaznamu.Zaznam;

public class ZapisDoText {

    public ZapisDoText() {
    }

    public void zapisDoTextSouboru(String soubor, SpravaOblasti spravaOblasti) {
        try {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Vyberte soubor pro uchovávání dat.");
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                PrintWriter printWriter = new PrintWriter(selectedFile);
                SpravaOblasti spOb = spravaOblasti;
                Iterator<Oblast> iteratorOblast = spOb.iterator();
                Oblast ob = null;
                while (iteratorOblast.hasNext()) {
                    ob = iteratorOblast.next();
                    Iterator<Zaznam> iteratorZaznam = ob.getZaznamy().vytvorIterator(ETypProhl.SIRKA);
                    Zaznam zaznam = null;
                    while (iteratorZaznam.hasNext()) {
                        zaznam = iteratorZaznam.next();
                        printWriter.println("vloz");
                        printWriter.println("" + zaznam.getID());
                        printWriter.println("" + zaznam.getJmeno());
                    }
                }
                printWriter.close();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Chyba: " + ex.getMessage());
        }
    }
}
