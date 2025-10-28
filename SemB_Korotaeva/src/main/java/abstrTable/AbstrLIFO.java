package abstrTable;

import abstrDoubleList.AbstrDoubleList;
import abstrDoubleList.IAbstrDoubleList;
import java.util.Iterator;

public class AbstrLIFO<T> {
    private IAbstrDoubleList<T> list = new AbstrDoubleList<>();

    public void zrus() {
        list = null;
    }

    public boolean jePrazdny() {
        return list.jePrazdny();
    }

    public void vloz(T data) {
        list.vlozPrvni(data);
    }

    public T odeber() {
        return list.odeberPrvni();
    }

    public Iterator<T> vytvorIterator() {
        return list.iterator();
    }
}
