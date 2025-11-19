package abstrTable;

import enumClass.ETypProhl;
import static enumClass.ETypProhl.HLOUBKA;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AbstrTable<K extends Comparable<K>, V> implements IAbstrTable<K, V> {

    private Node<K, V> root;

    public AbstrTable() {
        this.root = null;
    }

    @Override
    public V najdi(K key) {
        prazdnySeznam();
        keyNeniNull(key);

        Node<K, V> node = root;

        while (node != null) {
            int keyCislo = key.compareTo(node.key);
            if (keyCislo > 0) {
                node = node.right;
            } else if (keyCislo < 0) {
                node = node.left;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void vloz(K key, V value) {
        keyNeniNull(key);
        valueNeniNull(value);

        Node<K, V> node = new Node<>(key, value);
        boolean aktualizacePrvka = false;

        if (!jePrazdny() && value == najdi(key)) {
            aktualizacePrvka = true;
        }

        if (jePrazdny()) {
            root = node;
        } else {
            Node<K, V> current = root;
            while (current != null) {
                int keyCislo = key.compareTo(current.key);
                if (!aktualizacePrvka) {
                    current.pocetPotomku++;
                }
                if (keyCislo > 0) {
                    if (current.right == null) {
                        current.right = node;
                        break;
                    }
                    current = current.right;
                } else if (keyCislo < 0) {
                    if (current.left == null) {
                        current.left = node;
                        break;
                    }
                    current = current.left;
                } else {
                    current.value = value;
                    break;
                }
            }
        }
    }

    @Override
    public V odeber(K key) {
        prazdnySeznam();
        keyNeniNull(key);

        Node<K, V> current = root;
        Node<K, V> parent = null;
        
        V odebranyPrvek = current.value;

        if (current == null) {
            throw new NoSuchElementException();
        }
        if (root.right == null && root.left == null) {
            zrus();
            return odebranyPrvek;
        }

        while (current != null) {
            int keyCislo = key.compareTo(current.key);
            current.pocetPotomku--;

            if (keyCislo == 0) {
                break;
            }

            parent = current;

            if (keyCislo > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        if (current == null) {
            throw new NoSuchElementException();
        }
        
        odebranyPrvek = current.value;

        if (current.right == null && current.left == null) {
            if (parent.right == current) {
                parent.right = null;
            } else {
                parent.left = null;
            }
            return odebranyPrvek;
        } else {
            Node<K, V> child = current;
            if (current.right != null && current.left != null) {

                Node<K, V> oldValue = current;
                Node<K, V> newValue = current.right;
                
                Node<K, V> newValueParent = current;

                while (newValue.left != null) {
                    newValueParent = newValue;
                    newValue = newValue.left;
                }

                current.key = newValue.key;
                current.value = newValue.value;

                child = newValue.right;

                if (newValueParent.left == newValue) {
                    newValueParent.left = child;
                } else {
                    newValueParent.right = child;
                }

                return odebranyPrvek;
            } else if (current.right != null || current.left != null) {
                if (current.right == null) {
                    child = current.left;
                } else {
                    child = current.right;
                }

                if (parent == null) {
                    root = child;
                } else if (parent.left == current) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }

                return odebranyPrvek;
            }
        }

        return null;
    }

    @Override
    public void zrus() {
        root = null;
    }

    @Override
    public boolean jePrazdny() {
        return root == null;
    }

    @Override
    public V select(int k) {
        int pozice = 0;

        AbstrLIFO<Node<K, V>> lifo = new AbstrLIFO<>();
        Node<K, V> current = root;
        while (current != null || !lifo.jePrazdny()) {
            while (current != null) {
                lifo.vloz(current);
                current = current.left;
            }
            Node<K, V> data = lifo.odeber();
            pozice++;
            if (pozice == k) {
                return data.value;
            }
            current = data.right;
        }

        return null;
    }

    @Override
    public int rank(K key) {
        int rank = 0;

        AbstrLIFO<Node<K, V>> lifo = new AbstrLIFO<>();
        Node<K, V> current = root;
        while (current != null || !lifo.jePrazdny()) {
            while (current != null) {
                lifo.vloz(current);
                current = current.left;
            }
            Node<K, V> data = lifo.odeber();
            rank++;
            if (key == data.key) {
                return rank;
            }
            current = data.right;
        }

        return 0;
    }

    @Override
    public Iterator vytvorIterator(ETypProhl typ) {
        switch (typ) {
            case SIRKA -> {
                AbstrFIFO<Node<K, V>> abstrFIFO = new AbstrFIFO<>();
                if (root != null) {
                    abstrFIFO.vloz(root);
                }
                return new Iterator<V>() {
                    @Override
                    public boolean hasNext() {
                        return !abstrFIFO.jePrazdny();
                    }

                    @Override
                    public V next() {
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        } else {
                            Node<K, V> data = abstrFIFO.odeber();
                            if (data.left != null) {
                                abstrFIFO.vloz(data.left);
                            }
                            if (data.right != null) {
                                abstrFIFO.vloz(data.right);
                            }
                            return data.value;
                        }
                    }
                };
            }
            case HLOUBKA -> {
                AbstrLIFO<Node<K, V>> lifo = new AbstrLIFO<>();
                return new Iterator() {
                    Node<K, V> node = root;

                    @Override
                    public boolean hasNext() {
                        return node != null || !lifo.jePrazdny();
                    }

                    @Override
                    public Object next() {
                        while (node != null) {
                            lifo.vloz(node);
                            node = node.left;
                        }
                        if (!hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Node<K, V> data = lifo.odeber();
                        node = data.right;
                        return data.value;
                    }

                };
            }
            default ->
                throw new IllegalArgumentException();
        }
    }

    private void prazdnySeznam() {
        if (jePrazdny()) {
            throw new NullPointerException();
        }
    }

    private void keyNeniNull(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    private void valueNeniNull(V value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }

    private static class Node<K extends Comparable<K>, V> {

        private K key;
        private V value;
        private Node<K, V> right;
        private Node<K, V> left;
        private int pocetPotomku;

        public Node(K key, V value) {
            super();
            this.key = key;
            this.value = value;
        }

        public Node() {
            this.key = null;
            this.value = null;
            right = null;
            left = null;
            pocetPotomku = 1;
        }
    }
}
