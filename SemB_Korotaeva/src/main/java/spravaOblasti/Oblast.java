package spravaOblasti;

import tabZaznamu.ITabZaznamu;
import tabZaznamu.TabZaznamu;

public class Oblast {
    private int ID;
    private int maxKapacita;
    private int aktKapacita;
    private final TabZaznamu tabZaznamu;

    public Oblast(int ID, int maxKapacita) {
        this.ID = ID;
        this.maxKapacita = maxKapacita;
        this.aktKapacita = 0;
        this.tabZaznamu = new TabZaznamu();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getMaxKapacita() {
        return maxKapacita;
    }

    public void setMaxKapacita(int maxKapacita) {
        this.maxKapacita = maxKapacita;
    }

    public int getAktKapacita() {
        return tabZaznamu.getPocetZaznamu();
    }

    public ITabZaznamu getZaznamy() {
        return tabZaznamu;
    }

    @Override
    public String toString() {
        return "Oblast " + ID + ", maxKapacita: " + maxKapacita + ", aktKapacita: " + getAktKapacita() + ", zaznamy: ";
    }
}
