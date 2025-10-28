package abstrTable;

import enumClass.ETypProhl;
import java.util.Iterator;

public interface IAbstrTable <K extends Comparable<K>, V>{
    /**
     * Vyhledá prvek dle klíče.
     * @param key
     * @return 
     */
    V najdi(K key);
    
    /**
     * 
     * @param key
     * @param value 
     */
    void vloz(K key, V value);
    
    /**
     * 
     * @param key
     * @return 
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
     * 
     * @param k
     * @return 
     */
    V select(int k);
    
    /**
     * 
     * @param key
     * @return 
     */
    int rank(K key);
    
    /**
     * 
     * @param typ
     * @return 
     */
    Iterator iterator(ETypProhl typ);
}
