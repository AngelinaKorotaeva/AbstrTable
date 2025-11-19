package spravaOblasti;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import spravaZaznamu.Zaznam;
import enumClass.EnumPozice;
import generator.Generator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class SpravaOblasti implements ISpravaOblasti {

    private final IAbstrDoubleList<Oblast> oblasti;
    private Consumer<String> errorLog;
    private int ID_oblast;
    private Oblast aktualniOblast;
    private Generator generator;

    public SpravaOblasti() {
        this.generator = new Generator();
        this.oblasti = new AbstrDoubleList<>();
        ID_oblast = 1;
        Oblast novaOblast = generator.generateOblast(ID_oblast);
        ID_oblast++;
        vlozOblast(novaOblast, EnumPozice.PRVNI);
        aktualniOblast = null;
    }

    public SpravaOblasti(IAbstrDoubleList<Oblast> oblasti) {
        this.generator = new Generator();
        this.oblasti = oblasti;
        ID_oblast = 1;
        Oblast novaOblast = generator.generateOblast(ID_oblast);
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
    public void vlozZaznamPozice(Zaznam zaznam){
        aktualniOblast.getZaznamy().vloz(zaznam.getID(), zaznam);
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
