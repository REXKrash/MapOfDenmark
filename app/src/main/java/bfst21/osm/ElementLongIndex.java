package bfst21.osm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * ElementLongIndex contains a list of elements that must extend the Element class.
 * Elements can then be found by their ID using binary search.
 */
public class ElementLongIndex<T extends Element> {

    private final List<T> elements = new ArrayList<>();
    private boolean sorted = true;

    public List<T> getElements() {
        return elements;
    }

    public void put(T element) {
        elements.add(element);
        sorted = false;
    }

    /**
     * @return Element with the given refID.
     * Sorts the list of elements if necessary.
     * Uses binary search to find the Element with the correct refID.
     */
    public T get(long refID) {
        if (!sorted) {
            elements.sort(Comparator.comparingLong(T::getID));
            sorted = true;
        }
        int lo = 0;               // nodes.get(lo).getID() <= ref
        int hi = elements.size(); // nodes.get(hi).getID() > ref
        while (lo + 1 < hi) {
            int mi = (lo + hi) / 2;
            if (elements.get(mi).getID() <= refID) {
                lo = mi;
            } else {
                hi = mi;
            }
        }
        T element = elements.get(lo);
        return element.getID() == refID ? element : null;
    }
}
