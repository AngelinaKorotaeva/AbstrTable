package abstrDoubleList;

public interface IAbstrDoubleList<T> extends Iterable{
    
    /**
     * Zrušení celého seznamu.
     **/
    void zrus();
    
    /**
     * Test naplněnosti seznamu.
     * @return true pokud seznam je prázdny, jinak false
     */
    boolean jePrazdny();
    
    /**Vložení prvku do seznamu na první místo.
     * @param data prvek, který se vkládá
     * @throws NullPointerException pokud data je null
     */
    void vlozPrvni(T data);
    
    /**
     * Vložení prvku do seznamu na poslední místo.
     * @param data prvek, který se vkládá
     * @throws NullPointerException pokud data je null
     */
    void vlozPosledni(T data);
    
    /**
     * Vložení prvku do seznamu jakožto následníka aktuálního prvku.
     * @param data prvek, který se vkládá
     * @throws NullPointerException pokud data je null
     *         NoSuchElementException pokud aktuální prvek je null
     */
    void vlozNaslednika(T data);
    
    /**
     * Vložení prvku do seznamu jakožto předchůdce aktuálního prvku.
     * @param data prvek, který se vkládá
     * @throws NullPointerException pokud data je null
     *         NoSuchElementException pokud aktuální prvek je null
     */
    void vlozPredchudce(T data);
    
    /**
     * Zpřístupnění aktuálního prvku seznamu.
     * @return aktualní prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T zpristupniAktualni();
    
    /**
     * Zpřístupnění prvního prvku seznamu.
     * @return první prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     */
    T zpristupniPrvni();
    
    /**
     * Zpřístupnění posledního prvku seznamu.
     * @return poslední prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     */
    T zpristupniPosledni();
    
    /**
     * Zpřístupnění následníka aktuálního prvku.
     * @return prvek za aktualním
     * @throws NoSuchElementException pokud seznam je prazdny
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T zpristupniNaslednika();
    
    /**
     * Zpřístupnění předchůdce aktuálního prvku.
     * @return prvek před aktualním
     * @throws NoSuchElementException pokud seznam je prazdnys
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T zpristupniPredchudce();
    
    /**
     * Odebrání (vyjmutí) aktuálního prvku ze seznamu poté je aktuální prvek nastaven na první prvek.
     * @return odebraný aktualní prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T odeberAktualni();
    
    /**
     * Odebrání prvního prvku ze seznamu.
     * @return odebraný první prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     */
    T odeberPrvni();
    
    /**
     * Odebrání posledního prvku ze seznamu.
     * @return odebraný poslední prvek
     * @throws NoSuchElementException pokud seznam je prazdny
     */
    T odeberPosledni();
    
    /**
     * Odebrání následníka aktuálního prvku ze seznamu.
     * @return odebraný prvek za aktualním
     * @throws NoSuchElementException pokud seznam je prazdny
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T odeberNaslednika();
    
    /**
     * Odebrání předchůdce aktuálního prvku ze seznamu.
     * @return odebraný prvek za aktualním
     * @throws NoSuchElementException pokud seznam je prazdny
     * @throws NullPointerException pokud aktualni prvek je null
     */
    T odeberPredchudce();
    
}