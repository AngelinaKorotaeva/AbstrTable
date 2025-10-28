package spravaZaznamu;

import enumClass.EnumPozice;
import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import java.util.Iterator;

public class SpravaZaznamu implements ISpravaZaznamu {

    private final IAbstrDoubleList<Zaznam> zaznamy;
    private int pocetZaznamu;

    public SpravaZaznamu() {
        this.zaznamy = new AbstrDoubleList<>();
        this.pocetZaznamu = 0;
    }

    @Override
    public void vlozZaznam(Zaznam zaznam, EnumPozice pozice) {
        if (zaznam == null) {
            throw new NullPointerException();
        }
        switch (pozice) {
            case PRVNI -> {
                zaznamy.vlozPrvni(zaznam);
                break;
            }
            case POSLEDNI -> {
                zaznamy.vlozPosledni(zaznam);
                break;
            }
            case PREDCHUDCE -> {
                zaznamy.vlozPredchudce(zaznam);
                break;
            }
            case NASLEDNIK -> {
                zaznamy.vlozNaslednika(zaznam);
                break;
            }
            default -> {
                throw new IllegalArgumentException();
            }
        }
        pocetZaznamu++;
    }

    @Override
    public Zaznam zpristupniZaznam(EnumPozice pozice) {
        Zaznam returnZaznam = null;
        switch (pozice) {
            case PRVNI -> {
                returnZaznam = zaznamy.zpristupniPrvni();
            }
            case POSLEDNI -> {
                returnZaznam = zaznamy.zpristupniPosledni();
            }
            case AKTUALNI -> {
                returnZaznam = zaznamy.zpristupniAktualni();
            }
            case PREDCHUDCE -> {
                returnZaznam = zaznamy.zpristupniPredchudce();
            }
            case NASLEDNIK -> {
                returnZaznam = zaznamy.zpristupniNaslednika();
            }
        }
        return returnZaznam;
    }

    @Override
    public Zaznam odeberZaznam(EnumPozice pozice) {
        Zaznam odebranyZaznam = null;
        switch (pozice) {
            case PRVNI -> {
                odebranyZaznam = zaznamy.odeberPrvni();
            }
            case POSLEDNI -> {
                odebranyZaznam = zaznamy.odeberPosledni();
            }
            case AKTUALNI -> {
                odebranyZaznam = zaznamy.odeberAktualni();
            }
            case PREDCHUDCE -> {
                odebranyZaznam = zaznamy.odeberPredchudce();
            }
            case NASLEDNIK -> {
                odebranyZaznam = zaznamy.odeberNaslednika();
            }
        }
        if (odebranyZaznam != null) {
            pocetZaznamu--;
        }
        return odebranyZaznam;
    }

    @Override
    public void zrus() {
        zaznamy.zrus();
        pocetZaznamu = 0;
    }

    @Override
    public Iterator<Zaznam> iterator() {
        return zaznamy.iterator();
    }

    @Override
    public int getPocetZaznamu() {
        return pocetZaznamu;
    }
}
