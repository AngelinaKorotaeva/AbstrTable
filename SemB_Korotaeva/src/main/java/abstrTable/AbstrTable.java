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
            int cislo = key.compareTo(node.key);
            if (cislo > 0) {
                node = node.right;
            } else if (cislo < 0) {
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

        if (jePrazdny()) {
            root = node;
        } else {
            Node<K, V> current = root;
            while (current != null) {
                int cislo = key.compareTo(current.key);
                if (cislo > 0) {
                    if (current.right == null) {
                        current.right = node;
                        break;
                    } else {
                        current = current.right;
                    }
                } else if (cislo < 0) {
                    if (current.left == null) {
                        current.left = node;
                        break;
                    } else {
                        current = current.left;
                    }
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

        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            
            current = data.right;
        }
        
        return rank;
    }

    @Override
    public Iterator iterator(ETypProhl typ) {
        switch (typ) {
            case SIRKA -> {
                return new Iterator() {
                    @Override
                    public boolean hasNext() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                    }

                    @Override
                    public Object next() {
                        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                            throw new NoSuchElementException("Žádné další prvky.");
                        }
                        Node<K, V> data = lifo.odeber();
                        node = data.right;
                        return data.value;
                    }

                };
            }
            default ->
                throw new IllegalArgumentException("Neznámý typ prohlížení: " + typ);
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

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            right = null;
            left = null;
        }

        public Node() {
            this.key = null;
            this.value = null;
            right = null;
            left = null;
        }
    }
}
