package spravaOblasti;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import spravaZaznamu.Zaznam;
import enumClass.EnumPozice;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class SpravaOblasti implements ISpravaOblasti {

    private final IAbstrDoubleList<Oblast> oblasti;
    private Consumer<String> errorLog;
    private int ID_oblast;
    private int KAPACITA_OBLASTI = 10;
    private Oblast aktualniOblast;

    public SpravaOblasti() {
        this.oblasti = new AbstrDoubleList<>();
        ID_oblast = 1;
        Oblast novaOblast = new Oblast(ID_oblast, KAPACITA_OBLASTI);
        ID_oblast++;
        vlozOblast(novaOblast, EnumPozice.PRVNI);
        aktualniOblast = null;
    }

    public SpravaOblasti(IAbstrDoubleList<Oblast> oblasti) {
        this.oblasti = oblasti;
        ID_oblast = 1;
        Oblast novaOblast = new Oblast(ID_oblast, KAPACITA_OBLASTI);
        ID_oblast++;
        vlozOblast(novaOblast, EnumPozice.PRVNI);
        aktualniOblast = null;
    }

    private void vlozOblast(Oblast oblast, EnumPozice pozice) {
        if (oblast == null) {
            throw new NullPointerException();
        }

        switch (pozice) {
            case PRVNI -> {
                oblasti.vlozPrvni(oblast);
            }
            case POSLEDNI -> {
                oblasti.vlozPosledni(oblast);
            }
            case PREDCHUDCE -> {
                oblasti.vlozPredchudce(oblast);
            }
            case NASLEDNIK -> {
                oblasti.vlozNaslednika(oblast);
            }
        }
    }

    private Oblast odeberOblast(EnumPozice pozice) {
        Oblast odebranaOblast = null;
        switch (pozice) {
            case PRVNI -> {
                odebranaOblast = oblasti.odeberPrvni();
            }
            case POSLEDNI -> {
                odebranaOblast = oblasti.odeberPosledni();
            }
            case AKTUALNI -> {
                odebranaOblast = oblasti.odeberAktualni();
            }
            case PREDCHUDCE -> {
                odebranaOblast = oblasti.odeberPredchudce();
            }
            case NASLEDNIK -> {
                odebranaOblast = oblasti.odeberNaslednika();
            }
        }
        if (oblasti.jePrazdny()){
            zrus();
        }
        return odebranaOblast;
    }

    @Override
    public Oblast zpristupniOblast(EnumPozice pozice) {
        Oblast oblast = null;
        switch (pozice) {
            case PRVNI -> {
                oblast = oblasti.zpristupniPrvni();
            }
            case POSLEDNI -> {
                oblast = oblasti.zpristupniPosledni();
            }
            case AKTUALNI -> {
                oblast = oblasti.zpristupniAktualni();
            }
            case PREDCHUDCE -> {
                oblast = oblasti.zpristupniPredchudce();
            }
            case NASLEDNIK -> {
                oblast = oblasti.zpristupniNaslednika();
            }
        }
        if (oblast != null) {
            aktualniOblast = oblast;
        }
        return oblast;
    }

    @Override
    public void vlozZaznam(Zaznam zaznam) {
        if (zaznam == null) {
            throw new NullPointerException();
        }
        
        if (aktualniOblast == null) {
            return;
        }

        Random rand = new Random();
        int kapacita = rand.nextInt(7) + 2;

        boolean vlozeno = false;
        Iterator<Oblast> iterator = oblasti.iterator();

        while (iterator.hasNext()) {
            Oblast oblast = iterator.next();
            if (oblast.getAktKapacita() < oblast.getMaxKapacita()) {
                oblast.getZaznamy().vloz(zaznam.getID(), zaznam);
                vlozeno = true;
                break;
            }
        }

        if (!vlozeno) {
            Oblast oblast = new Oblast(ID_oblast, kapacita);
            ID_oblast++;
            vlozOblast(oblast, EnumPozice.POSLEDNI);
            oblast.getZaznamy().vloz(zaznam.getID(), zaznam);
        }
    }
    
    @Override
    public void odeberZaznam(Zaznam zaznam) {
        if (zaznam == null) {
            throw new NullPointerException();
        }
        
        if (aktualniOblast != null) {
            aktualniOblast.getZaznamy().odeber(zaznam.getID());
            
            if (aktualniOblast.getAktKapacita() == 0) {
                odeberOblast(EnumPozice.AKTUALNI);
                aktualniOblast = oblasti.zpristupniPrvni();
            }
        }
    }

//    @Override
//    public void vlozZaznamPozice(Zaznam zaznam, EnumPozice pozice) {
//        if (zaznam == null) {
//            throw new NullPointerException();
//        }
//        if (oblasti.zpristupniAktualni() == null || oblasti.jePrazdny()) {
//            throw new NullPointerException();
//        }
//        if (oblasti.zpristupniAktualni().getMaxKapacita() == oblasti.zpristupniAktualni().getAktKapacita()) {
//            throw new NoSuchElementException();
//        }
//
//        boolean vlozeno = false;
//        Iterator<Oblast> iterator = oblasti.iterator();
//
//        while (iterator.hasNext()) {
//            Oblast oblast = iterator.next();
//            if (oblast == oblasti.zpristupniAktualni()) {
//                switch (pozice) {
//                    case PRVNI -> {
//                        oblast.getZaznamy().vlozZaznam(zaznam, EnumPozice.PRVNI);
//                        break;
//                    }
//                    case POSLEDNI -> {
//                        oblast.getZaznamy().vlozZaznam(zaznam, EnumPozice.POSLEDNI);
//                        break;
//                    }
//                    case PREDCHUDCE -> {
//                        oblast.getZaznamy().vlozZaznam(zaznam, EnumPozice.PREDCHUDCE);
//                        break;
//                    }
//                    case NASLEDNIK -> {
//                        oblast.getZaznamy().vlozZaznam(zaznam, EnumPozice.NASLEDNIK);
//                        break;
//                    }
//                }
//            }
//            vlozeno = true;
//        }
//
//        if (!vlozeno) {
//            throw new NullPointerException();
//        }
//    }

//    @Override
//    public Zaznam zpristupniZaznam(EnumPozice pozice) {
//        Zaznam zaznam = null;
//        Oblast oblast = oblasti.zpristupniAktualni();
//        if (oblast != null) {
//            switch (pozice) {
//                case PRVNI -> {
//                    zaznam = oblast.getZaznamy().zpristupniZaznam(EnumPozice.PRVNI);
//                }
//                case POSLEDNI -> {
//                    zaznam = oblast.getZaznamy().zpristupniZaznam(EnumPozice.POSLEDNI);
//                }
//                case AKTUALNI -> {
//                    zaznam = oblast.getZaznamy().zpristupniZaznam(EnumPozice.AKTUALNI);
//                }
//                case PREDCHUDCE -> {
//                    zaznam = oblast.getZaznamy().zpristupniZaznam(EnumPozice.PREDCHUDCE);
//                }
//                case NASLEDNIK -> {
//                    zaznam = oblast.getZaznamy().zpristupniZaznam(EnumPozice.NASLEDNIK);
//                }
//            }
//        }
//        return zaznam;
//    }

//    @Override
//    public Zaznam odeberZaznam(EnumPozice pozice) {
//        Zaznam zaznam = null;
//        Oblast oblast = oblasti.zpristupniAktualni();
//        if (oblast != null) {
//            switch (pozice) {
//                case PRVNI -> {
//                    zaznam = oblast.getZaznamy().odeberZaznam(EnumPozice.PRVNI);
//                }
//                case POSLEDNI -> {
//                    zaznam = oblast.getZaznamy().odeberZaznam(EnumPozice.POSLEDNI);
//                }
//                case AKTUALNI -> {
//                    zaznam = oblast.getZaznamy().odeberZaznam(EnumPozice.AKTUALNI);
//                }
//                case PREDCHUDCE -> {
//                    zaznam = oblast.getZaznamy().odeberZaznam(EnumPozice.PREDCHUDCE);
//                }
//                case NASLEDNIK -> {
//                    zaznam = oblast.getZaznamy().odeberZaznam(EnumPozice.NASLEDNIK);
//                }
//            }
//            if (oblast.getAktKapacita() == 0 && zaznam != null) {
//                odeberOblast(EnumPozice.AKTUALNI);
//            }
//        }
//        return zaznam;
//    }

    @Override
    public void zrus() {
        this.ID_oblast = 1;
        oblasti.zrus();
    }

    private void error(String ex) {
        if (errorLog != null) {
            errorLog.accept(ex);
        }
    }

    public int getID_oblast() {
        return ID_oblast;
    }

    public void setID_oblast(int ID) {
        this.ID_oblast = ID;
    }

    @Override
    public Iterator<Oblast> iterator() {
        return oblasti.iterator();
    }
}
