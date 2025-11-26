package generator;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import enumClass.EnumPozice;
import java.util.NoSuchElementException;
import java.util.Random;
import tabZaznamu.Zaznam;
import spravaOblasti.Oblast;
import spravaOblasti.SpravaOblasti;

public class Generator {

    private final Random rnd = new Random();
    private int id_zaznam;

    public Zaznam generateZaznam() {
        id_zaznam = rnd.nextInt(99) + 2;
        Zaznam zaznam = new Zaznam(id_zaznam, "Zaznam" + id_zaznam);
        return zaznam;
    }

    public Oblast generateOblast(int id_oblast) {
        int kapacita = rnd.nextInt(19) + 2;
        Oblast oblast = new Oblast(id_oblast, kapacita);
        int pocetZaznamu = rnd.nextInt(kapacita - 1) + 1;

        int[] poleID = new int[pocetZaznamu];

        for (int i = 0; i < pocetZaznamu; i++) {
            Zaznam zaznam = generateZaznam();
            if (i != 0) {
                for (int j = 0; j < i; j++) {
                    if (poleID[j] == zaznam.getID()) {
                        int inx = poleID[j];
                        while (inx == zaznam.getID()) {
                            zaznam = generateZaznam();
                        }
                        break;
                    }
                }
            } else {
                poleID[i] = zaznam.getID();
            }
            
            oblast.getZaznamy().vloz(zaznam.getID(), zaznam);
        }
        return oblast;
    }

    public SpravaOblasti generatePole(SpravaOblasti spravaOblasti) {
        IAbstrDoubleList<Oblast> list = new AbstrDoubleList<>();

        int pocetOblasti = rnd.nextInt(9) + 2;
        for (int i = 0; i < pocetOblasti; i++) {
            Oblast oblast = generateOblast(spravaOblasti.getID_oblast());
            spravaOblasti.setID_oblast(spravaOblasti.getID_oblast() + 1);
            list.vlozPosledni(oblast);
        }

        spravaOblasti = new SpravaOblasti(list);

        return spravaOblasti;
    }
}
