package abstrTable;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import java.util.Iterator;

public class AbstrFIFO<T> {

    private IAbstrDoubleList<T> list = new AbstrDoubleList<>();

    public AbstrFIFO() {
    }

    public void zrus() {
        list = null;
    }

    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    public void vloz(T data) {
        list.vlozPosledni(data);
    }

    public T odeber() {
        return list.odeberPrvni();
    }

    public Iterator<T> vytvorIterator() {
        return list.iterator();
    }
}
