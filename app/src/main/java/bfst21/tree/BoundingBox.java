package bfst21.tree;


public class BoundingBox {

    private final float maxX, maxY, minX, minY;

    public BoundingBox(float maxX, float maxY, float minX, float minY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.minX = minX;
        this.minY = minY;
    }

    public boolean intersects(BoundingBox boundingBox) {
        float otherMaxX = boundingBox.getMaxX();
        float otherMaxY = boundingBox.getMaxY();
        float otherMinX = boundingBox.getMinX();
        float otherMinY = boundingBox.getMinY();

        //return (minX < otherMaxX) && (otherMinX < maxX) && (minY < otherMaxY) && (minY < otherMinY && otherMinY < maxY);
        //return minX < boundingBox.maxX && maxX > boundingBox.minX && minY < boundingBox.maxY && maxY > boundingBox.minY;

        //Check if other box is inside this box
        if (otherMinX >= minX && otherMaxX <= maxX && otherMinY >= minY && otherMaxY <= maxY) {
            return true;

            //Check if this box is inside other box
        } else if (otherMinX <= minX && otherMaxX >= maxX && otherMinY <= minY && otherMaxY >= maxY) {
            return true;

        //Check if other min/max x is inside this box
        } else if ((otherMinX >= minX && otherMinX <= maxX) || (otherMaxX >= minX && otherMaxX <= maxX)) {
            //Check if other min/max y is inside this box
            if ((otherMinY >= minY && otherMinY <= maxY) || (otherMaxY >= minY && otherMaxY <= maxY)) {
                return true;

            } else if ((otherMinY <= minY && otherMaxY >= maxY)) {
                return true;
            }
        }
        if ((otherMinY >= minY && otherMinY <= maxY) || (otherMaxY >= minY && otherMaxY <= maxY)) {
            if ((otherMinX <= minX && otherMaxX >= maxX)) {
                return true;
            }
        }
        return false;
    }

    public boolean contains(KdNode kdNode) {
        float maxX = getMaxX();
        float maxY = getMaxY();
        float minX = getMinX();
        float minY = getMinY();

        float nodeX = kdNode.getX();
        float nodeY = kdNode.getY();

        if (maxX >= nodeX && minX <= nodeX) {
            return maxY >= nodeY && minY <= nodeY;
        }
        return false;
    }

    public float getMaxX() {
        return maxX;
    }

    public float getMaxY() {
        return maxY;
    }

    public float getMinX() {
        return minX;
    }

    public float getMinY() {
        return minY;
    }
}