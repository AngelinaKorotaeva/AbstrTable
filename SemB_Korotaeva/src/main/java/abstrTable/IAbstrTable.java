package abstrTable;

import enumClass.ETypProhl;
import java.util.Iterator;

public interface IAbstrTable <K extends Comparable<K>, V>{
    /**
     * Vyhledá prvek dle klíče.
     * @param key
     * @return value hledaciho prvka nebo null poku prvek neni ve stromu.
     * @throws NullPointerException pokud strom je prazdny nebo key je null.
     */
    V najdi(K key);
    
    /**
     * Vloží prvek do tabulky.
     * @param key
     * @param value 
     * @throws NullPointerException pokud key nebo value je null.
     */
    void vloz(K key, V value);
    
    /**
     * Odebere prvek dle klíče z tabulky.
     * @param key
     * @return odebrany prvek nebo null pokud takový key neexistuje ve stromu.
     * @throws NullPointerException pokud strom je prazdny nebo key je null.
     */
    V odeber(K key);
    
    /**
     * Zrušení celé tabulky.
     */
    void zrus();
    
    /**
     * Test prázdnosti tabulky.
     * @return true pokud seznam je prazdný, false pokud není.
     */
    boolean jePrazdny();
    
    /**
     * Vrátí prvek s k-tým nejmenším klíčem ve stromu.
     * @param k
     * @return value prvka
     * @throws NullPointerException pokud strom je prazdny.
     */
    V select(int k);
    
    /**
     * Vrátí pořadí prvku s klíčem key.
     * @param key
     * @return pořadí prvku
     * @throws NullPointerException pokud strom je prazdny nebo key je null.
     */
    int rank(K key);
    
    /**
     * Vytvoří iterátor, který umožňuje procházení stromu do šířky a hloubky.
     * @param typ
     * @return iterator podle typu.
     */
    Iterator vytvorIterator(ETypProhl typ);
}
