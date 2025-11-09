package tabZaznamu;

import enumClass.ETypProhl;
import java.util.Iterator;
import spravaZaznamu.Zaznam;

public interface ITabZaznamu<K extends Comparable<K>, V>{
    
    /**
     * Vyhledá záznam dle jého id.
     * @param idZaznam 
     * @return záznam nebo null pokud takový záznam neexistuje v stromu.
     */
    Zaznam najdi(int idZaznam);
    
    /**
     * Vloží záznam do tabulky.
     * @param idZaznam
     * @param zaznam
     */
    void vloz(int idZaznam, Zaznam zaznam);
    
    /**
     * Odebere záznam dle id z tabulky.
     * @param idZaznam
     * @return odebráný záznam nebo null pokud takové id neexistuje ve stromu.
     */
    Zaznam odeber(int idZaznam);
    
    /**
     * Vytvoří iterátor, který umožňuje procházení stromu do šířky a hloubky.
     * @param typ
     * @return iterator podle typu.
     */
    Iterator vytvorIterator(ETypProhl typ);
    
    /**
     * Vrátí záznam s k-tým nejmenším id ve stromu.
     * @param k
     * @return zaznam
     */
    Zaznam select(int k);
    
    /**
     * Vrátí pořadí záznamu s klíčem id.
     * @param idZaznam
     * @return pořadí záznamu
     */
    int rank(int idZaznam);
}
