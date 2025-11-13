package abstrDoubleList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrDoubleList<T> implements IAbstrDoubleList<T> {

    private Node<T> prvni;
    private Node<T> posledni;
    private Node<T> aktualni;

    public AbstrDoubleList() {
        this.prvni = null;
        this.posledni = null;
        this.aktualni = null;
    }

    @Override
    public void zrus() {
        prvni = null;
        posledni = null;
        aktualni = null;
    }

    @Override
    public boolean jePrazdny() {
        return prvni == null;
    }

    @Override
    public void vlozPrvni(T data) {
        prvekNeniNull(data);
        Node<T> prvek = new Node<>(data);

        if (prvni == null) {
            prvni = prvek;
            posledni = prvek;
        } else {
            prvek.naslednik = prvni;
            prvni.predchudce = prvek;
            prvni = prvek;
        }
    }

    @Override
    public void vlozPosledni(T data) {
        prvekNeniNull(data);
        Node<T> prvek = new Node<>(data);

        if (prvni == null) {
            prvni = prvek;
            posledni = prvek;
        } else {
            prvek.predchudce = posledni;
            posledni.naslednik = prvek;
            posledni = prvek;
        }
    }

    @Override
    public void vlozNaslednika(T data) {
        prvekNeniNull(data);
        prazdnySeznam();
        aktualniNenastaven();
        Node<T> prvek = new Node<>(data);
        Node<T> next = aktualni.naslednik;

        aktualni.naslednik = prvek;
        prvek.predchudce = aktualni;
        prvek.naslednik = next;
        if (next != null) {
            next.predchudce = prvek;
        } else {
            posledni = prvek;
        }
    }

    @Override
    public void vlozPredchudce(T data) {
        prvekNeniNull(data);
        prazdnySeznam();
        aktualniNenastaven();
        Node<T> prvek = new Node<>(data);
        Node<T> pred = aktualni.predchudce;

        aktualni.predchudce = prvek;
        prvek.naslednik = aktualni;
        prvek.predchudce = pred;
        if (pred != null) {
            pred.naslednik = prvek;
        } else {
            prvni = prvek;
        }
    }

    @Override
    public T zpristupniAktualni() {
        prazdnySeznam();
        aktualniNenastaven();
        return aktualni.data;
    }

    @Override
    public T zpristupniPrvni() {
        prazdnySeznam();
        aktualni = prvni;
        return aktualni.data;
    }

    @Override
    public T zpristupniPosledni() {
        prazdnySeznam();
        aktualni = posledni;
        return aktualni.data;
    }

    @Override
    public T zpristupniNaslednika() {
        prazdnySeznam();
        aktualniNenastaven();
        if (aktualni.naslednik != null) {
            aktualni = aktualni.naslednik;
        } else {
            throw new NullPointerException();
        }
        return aktualni.data;
    }

    @Override
    public T zpristupniPredchudce() {
        prazdnySeznam();
        aktualniNenastaven();
        if (aktualni.predchudce != null) {
            aktualni = aktualni.predchudce;
        } else {
            throw new NullPointerException();
        }
        return aktualni.data;
    }

    @Override
    public T odeberAktualni() {
        prazdnySeznam();
        aktualniNenastaven();
        Node<T> odebranyPrvek = aktualni;
        if (prvni == posledni) {
            zrus();
        } else {
            if (aktualni == prvni) {
                prvni = odebranyPrvek.naslednik;
            } else if (aktualni == posledni) {
                posledni = odebranyPrvek.predchudce;
            } else {
                aktualni.predchudce.naslednik = aktualni.naslednik;
                aktualni.naslednik.predchudce = aktualni.predchudce;
            }
            aktualni = prvni;
        }
        return odebranyPrvek.data;
    }

    @Override
    public T odeberPrvni() {
        prazdnySeznam();
        Node<T> odebranyPrvek = prvni;
        if (prvni == posledni) {
            zrus();
        } else {
            if (aktualni == prvni) {
                aktualni = odebranyPrvek.naslednik;
            }
            prvni = prvni.naslednik;
            prvni.predchudce = null;
        }
        return odebranyPrvek.data;
    }

    @Override
    public T odeberPosledni() {
        prazdnySeznam();
        Node<T> odebranyPrvek = posledni;
        if (prvni == posledni) {
            zrus();
        } else {
            if (aktualni == posledni) {
                aktualni = prvni;
            }
            posledni = posledni.predchudce;
            posledni.naslednik = null;
        }
        return odebranyPrvek.data;
    }

    @Override
    public T odeberNaslednika() {
        prazdnySeznam();
        aktualniNenastaven();
        if (aktualni == posledni) {
            return null;
        } else {
            Node<T> odebranyPrvek = aktualni.naslednik;
            if (odebranyPrvek == posledni) {
                posledni = aktualni;
                aktualni.naslednik = null;
            } else {
                aktualni.naslednik = odebranyPrvek.naslednik;
                odebranyPrvek.naslednik.predchudce = aktualni;
            }
            return odebranyPrvek.data;
        }
    }

    @Override
    public T odeberPredchudce() {
        prazdnySeznam();
        aktualniNenastaven();
        if (aktualni == prvni) {
            return null;
        } else {
            Node<T> odebranyPrvek = aktualni.predchudce;
            if (odebranyPrvek == prvni) {
                prvni = aktualni;
                aktualni.predchudce = null;
            } else {
                aktualni.predchudce = odebranyPrvek.predchudce;
                odebranyPrvek.predchudce.naslednik = aktualni;
            }
            return odebranyPrvek.data;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> prvek = prvni;

            @Override
            public boolean hasNext() {
                return prvek != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    T data = prvek.data;
                    prvek = prvek.naslednik;
                    return data;
                }
            }
        };
    }

    private void prazdnySeznam() {
        if (jePrazdny()) {
            throw new NoSuchElementException();
        }
    }

    private void prvekNeniNull(T data) {
        if (data == null) {
            throw new NullPointerException();
        }
    }
    
    private void aktualniNenastaven(){
        if (aktualni == null){
            throw new NoSuchElementException();
        }
    }

    private static class Node<T> {
        T data;
        Node<T> predchudce;
        Node<T> naslednik;

        public Node(T data) {
            this.data = data;
            predchudce = null;
            naslednik = null;
        }

        public Node() {
            this.data = null;
            predchudce = null;
            naslednik = null;
        }
    }
}
