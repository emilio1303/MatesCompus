//Emilio C. Alegría - A01631303
class Nodo<T extends Comparable<?>> {
    Nodo<T> left, right;
    T data;

    public Nodo(T data) {
        this.data = data;
    }
}