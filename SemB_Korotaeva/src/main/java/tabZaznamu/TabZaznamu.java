package tabZaznamu;

import abstrTable.AbstrTable;
import abstrTable.IAbstrTable;
import enumClass.ETypProhl;
import java.util.Iterator;
import spravaZaznamu.Zaznam;

public class TabZaznamu implements ITabZaznamu{
    
    private final IAbstrTable<Integer, Zaznam> table;
    private int pocetZaznamu;
    
    public TabZaznamu() {
        this.table = new AbstrTable<>();
        this.pocetZaznamu = 0;
    }

    @Override
    public Zaznam najdi(int idZaznam) {
        return table.najdi(idZaznam);
    }

    @Override
    public void vloz(int idZaznam, Zaznam zaznam) {
        table.vloz(idZaznam, zaznam);
        pocetZaznamu++;
    }

    @Override
    public Zaznam odeber(int idZaznam) {
        pocetZaznamu--;
        return table.odeber(idZaznam);
    }

    @Override
    public Iterator vytvorIterator(ETypProhl typ) {
        return table.iterator(typ);
    }

    @Override
    public Zaznam select(int k) {
        return table.select(k);
    }

    @Override
    public int rank(int idZaznam) {
        return table.rank(idZaznam);
    }

    public int getPocetZaznamu() {
        return pocetZaznamu;
    }
}
