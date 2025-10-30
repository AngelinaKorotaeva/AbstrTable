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
                }
            }
        }
    }

    @Override
    public V odeber(K key) {
        prazdnySeznam();
        keyNeniNull(key);

        if (najdi(key) == null) {
            return null;
        }
        if (root.right == null && root.left == null) {
            zrus();
        }

        Node<K, V> current = root;
        Node<K, V> parentCurrent = root;

        while (current != null) {
            int keyCislo = key.compareTo(current.key);
            current.pocetPotomku--;
            if (keyCislo > 0) {
                parentCurrent = current;
                current = current.right;
            } else if (keyCislo < 0) {
                parentCurrent = current;
                current = current.left;
            } else {
                if (current.right == null && current.left == null) {
                    if (parentCurrent.right == current) {
                        parentCurrent.right = null;
                        return current.value;
                    } else {
                        parentCurrent.left = null;
                        return current.value;
                    }
                } else {
                    boolean parentMaChild = true;
                    Node<K, V> child = current;

                    while (parentMaChild) {
                        if (current.right != null && current.left != null) {
                            boolean nextZmena = false;
                            AbstrLIFO<Node<K, V>> lifo = new AbstrLIFO<>();
                            Node<K, V> node = root;
                            while (node != null || !lifo.jePrazdny()) {
                                while (node != null) {
                                    lifo.vloz(node);
                                    node = current.left;
                                }
                                Node<K, V> data = lifo.odeber();
                                if (nextZmena) {
                                    current.value = data.value;
                                    child = data;
                                    break;
                                }
                                if (data == current) {
                                    nextZmena = true;
                                }
                                node = data.right;
                            }

                        } else if (current.right != null || current.left != null) {
                            if (current.right == null) {
                                child = current.left;
                                current.value = current.left.value;
                                current = child;
                            } else {
                                child = current.right;
                                current.value = current.right.value;
                                current = child;
                            }
                        } else {
                            parentMaChild = false;
                        }
                    }
                    break;
                }
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
    public Iterator iterator(ETypProhl typ) {
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
            this.key = key;
            this.value = value;
            right = null;
            left = null;
            pocetPotomku = 1;
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
