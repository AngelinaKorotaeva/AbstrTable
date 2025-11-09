package spravaOblasti;

import spravaZaznamu.Zaznam;
import enumClass.EnumPozice;

public interface ISpravaOblasti extends Iterable{
    
    /**
     * Zpřístupní oblast z požadované pozice (první, poslední, předchůdce, následník, aktuální).
     * @param pozice
     * @return oblast z požadované pozice
     **/
    Oblast zpristupniOblast(EnumPozice pozice);
    
    /**
     * Vloží záznam se strategií first–fit (první volný je vyhovující). 
     * V případě, že všechny oblasti jsou již obsazeny, dochází alokaci nové oblasti, do které se záznam vloží.
     * @param zaznam
     **/
    void vlozZaznam(Zaznam zaznam);
    
    /**
     * Vloží záznam do aktuální oblasti na požadovanou pozici.
     * @param zaznam
     * @param pozice
     * @throws - V případě že je oblast zaplněna, vyvolá výjimku.
     **/
    //void vlozZaznamPozice(Zaznam zaznam, EnumPozice pozice);
    
    /**
     * Při odebírání záznamu z BVS odebere odkaz na stejný záznam i z příslušného ADL.
     * @param zaznam 
     */
    void odeberZaznam(Zaznam zaznam);
    
    /**
     * Zpřístupní záznam z požadované pozice (první, poslední, předchůdce, následník, aktuální).
     * @param pozice
     * @return záznam z požadované pozice
     **/
    //Zaznam zpristupniZaznam(EnumPozice pozice);
    
    /**
     * Odebere záznam z aktuální oblasti z požadované pozice (první, poslední, předchůdce, následník, aktuální).
     * @param pozice
     * @return odebraný záznam z aktuální oblasti z požadované pozice
     **/
    //Zaznam odeberZaznam(EnumPozice pozice);
    
    /**
     * Zruší všechny oblasti i jejich záznamy.
     **/
    void zrus();
}
