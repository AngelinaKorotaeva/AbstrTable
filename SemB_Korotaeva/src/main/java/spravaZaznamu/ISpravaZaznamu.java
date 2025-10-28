package spravaZaznamu;

import enumClass.EnumPozice;

public interface ISpravaZaznamu extends Iterable{
    
    /**
     * Vloží nový záznam do seznamu záznamů na příslušnou pozici (první, poslední, předchůdce, následník).
     * @param zaznam
     * @param pozice
     **/
    void vlozZaznam(Zaznam zaznam, EnumPozice pozice);
    
    /**
     * Zpřístupní záznam z požadované pozice (první, poslední, předchůdce, následník, aktuální), v odpovídající oblasti.
     * @param pozice
     * @return záznam z požadované pozice
     **/
    Zaznam zpristupniZaznam(EnumPozice pozice);
    
    /**
     * Odebere záznam z požadované pozice (první, poslední, předchůdce, následník, aktuální), v odpovídající oblasti.
     * @param pozice
     * @return odebraný záznam z požadované pozice
     **/
    Zaznam odeberZaznam(EnumPozice pozice);
    
    /**
     * Zruší všechny záznamy.
     **/
    void zrus();
    /**
     * Vraci počet zaznamu v listu.
     * @return počet zaznamu
     */
    public int getPocetZaznamu();
}
