package bfst21.osm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ElementGroup is used to separate Ways into different categories.
 * An ElementGroup is created for each ElementType, but Ways that are drawn
 * using the fill method will have additional ElementGroups for different sizes.
 */
public class ElementGroup implements Serializable {

    private static final long serialVersionUID = -4246725919555317161L;

    private static List<ElementGroup> elementGroups;

    private final ElementType elementType;
    private final ElementSize elementSize;

    public ElementGroup(ElementType elementType, ElementSize elementSize) {
        this.elementType = elementType;
        this.elementSize = elementSize;
    }

    /**
     * @return list of all available ElementGroups.
     * If an ElementType is drawn using the fill method, an ElementGroup
     * will be created for every ElementSize with the same ElementType.
     * Otherwise ElementSize will be set to ElementSize.DEFAULT.
     */
    public static List<ElementGroup> values() {
        if (elementGroups == null) {
            elementGroups = new ArrayList<>();

            for (ElementType elementType : ElementType.values()) {
                if (elementType.hasMultipleSizes()) {
                    for (ElementSize elementSize : ElementSize.values()) {
                        if (elementSize != ElementSize.DEFAULT) {
                            elementGroups.add(new ElementGroup(elementType, elementSize));
                        }
                    }
                } else {
                    elementGroups.add(new ElementGroup(elementType, ElementSize.DEFAULT));
                }
            }
        }
        return elementGroups;
    }

    /**
     * @return ElementGroup with the specific ElementType and ElementSize.
     */
    public static ElementGroup getElementGroup(ElementType elementType, ElementSize elementSize) {
        for (ElementGroup elementGroup : values()) {
            if (elementGroup.getType() == elementType) {
                if (elementGroup.getSize() == elementSize) {
                    return elementGroup;
                }
            }
        }
        return new ElementGroup(ElementType.UNKNOWN, ElementSize.DEFAULT);
    }

    public boolean doShowElement(double zoomLevel) {
        return elementType.doShowElement(zoomLevel) && elementSize.doShowElement(zoomLevel);
    }

    public String toString() {
        return elementType.toString() + " " + elementSize.toString();
    }

    public ElementType getType() {
        return elementType;
    }

    public ElementSize getSize() {
        return elementSize;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (elementSize.hashCode() - elementType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ElementGroup other = (ElementGroup) obj;
        return this.elementType == other.elementType && this.elementSize == other.elementSize;
    }
}
