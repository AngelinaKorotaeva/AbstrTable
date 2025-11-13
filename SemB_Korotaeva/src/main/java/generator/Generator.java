package generator;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import enumClass.EnumPozice;
import java.util.NoSuchElementException;
import java.util.Random;
import spravaZaznamu.Zaznam;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;

public class Generator {

    private final Random rnd = new Random();
    private int id_zaznam = 1;

    public Zaznam generateZaznam() {
        Zaznam zaznam = new Zaznam(id_zaznam, "Zaznam" + id_zaznam);
        id_zaznam++;
        return zaznam;
    }

    public Oblast generateOblast(int id_oblast) {
        int kapacita = rnd.nextInt(19) + 2;
        Oblast oblast = new Oblast(id_oblast, kapacita);
        int pocetZaznamu = rnd.nextInt(kapacita - 1) + 1;
        
        for (int i = 0; i < pocetZaznamu; i++) {
            Zaznam zaznam = generateZaznam();
            oblast.getZaznamy().vloz(zaznam.getID(), zaznam);
        }
        id_zaznam = 1;
        return oblast;
    }
//
//    public Oblast initSpravaOblasti(SpravaOblasti spravaOblasti) {
//        if (spravaOblasti == null) {
//            return null;
//        }
//
//        boolean prazdny = false;
//        try {
//            spravaOblasti.iterator();
//        } catch (NoSuchElementException ex) {
//            prazdny = true;
//        }
//
//        if (prazdny) {
//            Oblast prvni = generateOblast(spravaOblasti.getID_oblast());
//            spravaOblasti.setID_oblast(spravaOblasti.getID_oblast() + 1);
//            spravaOblasti.init(prvni.getMaxKapacita());
//            return prvni;
//        } else {
//            return null;
//        }
//    }
//
    public IAbstrDoubleList<Oblast> generatePole(SpravaOblasti spravaOblasti) {
        IAbstrDoubleList<Oblast> list = new AbstrDoubleList<>();
        
        int pocetOblasti = rnd.nextInt(9) + 2;
        for (int i = 0; i < pocetOblasti; i++) {
            Oblast oblast = generateOblast(spravaOblasti.getID_oblast());
            spravaOblasti.setID_oblast(spravaOblasti.getID_oblast() + 1);
            list.vlozPosledni(oblast);
        }

        return list;
    }
}
