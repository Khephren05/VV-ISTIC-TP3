package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private ArrayList<T> heap;
    private Comparator<T> comparator;

    /**
     * Constructeur permettant d'initialiser notre arbre binaire à partir d'une ArrayList
     * Cet arbre binaire est rangé du haut vers le bas dans l'ordre croissant (minimum tout en haut)
     */
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        this.heap = new ArrayList<T>();
    }

    public ArrayList<T> getHeap() {
        return heap;
    }

    /**
     * Fonction permettant de trouver et de retirer l'objet minimal de l'arbre binaire
     *
     * @return T objet minimal de l'arbre binaire
     * @throws NoSuchElementException si l'arbre binaire est vide
     */
    public T pop() throws NoSuchElementException {
        T minHeap = heap.get(findMinHeapIndex());
        heap.remove(minHeap);
        return minHeap;
    }

    /**
     * Fonction retournant l'objet minimal de l'arbre binaire
     *
     * @return T objet minimal de l'arbre binaire
     * @throws NoSuchElementException si l'arbre binaire est vide
     */
    public T peek() {
        return heap.get(findMinHeapIndex());
    }

    /**
     * Fonction permettant d'ajouter un élément à l'arbre binaire.
     * L'ajout d'un élément existant déjà dans le tableau n'aura aucun effet.
     * On l'ajoute à la fin de l'arbre, puis, tant qu'il est plus grand que son parent, on l'échange avec ce dernier.
     *
     * @param nouveau T à ajouter dans l'arbre binaire
     */
    public void push(T nouveau) {
        if (heap.contains(nouveau)) return;
        heap.add(nouveau);
        if (heap.size() > 1) {
            int actualIndex = heap.size() - 1;
            Integer parentIndex = (actualIndex - 1) / 2;
            T parent = this.heap.get(parentIndex);
            // Tant que le nouveau est plus grand, j'échange le parent et le nouveau
            while (this.comparator.compare(nouveau, parent) > 0) {
                this.heap.set(actualIndex, parent);
                this.heap.set(parentIndex, nouveau);
                if (parentIndex.equals(0)) break;
                actualIndex = parentIndex;
                parentIndex = (actualIndex - 1) / 2;
                parent = this.heap.get(parentIndex);
            }
        }
    }

    /**
     * Fonction permettant d'obtenir le nombre d'éléments contenus dans l'arbre binaire
     *
     * @return int le nombre d'éléments contenus dans l'arbre binaire
     */
    public int count() {
        return heap.size();
    }

    /**
     * Fonction permettant d'obtenir l'index de l'élément minimal contenu dans l'arbre binaire.
     *
     * @return int index minHeap
     */
    public int findMinHeapIndex() {
        if (heap.isEmpty()) throw new NoSuchElementException("You can't have minHeap in empty Heap");
        T minHeap = heap.get(heap.size() - 1);
        int minHeapIndex = heap.size() - 1;
        // Pour tous les éléments du tableau à partir de l'élément à gauche du parent du dernier élément
        for (int actualElementIndex = heap.size() - 2; actualElementIndex > ((heap.size() - 2) / 2); actualElementIndex--) {
            if (this.comparator.compare(minHeap, heap.get(actualElementIndex)) > 0) {
                minHeap = heap.get(actualElementIndex);
                minHeapIndex = actualElementIndex;
            }
        }
        return minHeapIndex;
    }
}