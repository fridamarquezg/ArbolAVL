package AAVL;

public interface PrintableNode <T extends Comparable<T>> {
    PrintableNode getHijoIzq();
    PrintableNode getHijoDer();
    T getDato();
}
