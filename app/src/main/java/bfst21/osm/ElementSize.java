package bfst21.osm;


/**
 * ElementSize is used to separate Ways into different size categories.
 * This is only used for Ways that are drawn using the fill method.
 * <p>
 * Each ElementSize has a required area size and a required zoom level.
 * Ways with a larger area will be drawn before Ways with a small area.
 */
public enum ElementSize {
    MASSIVE(500_000.0f, 500.0f),
    LARGE(100_000.0f, 1_000.0f),
    MEDIUM(70_000.0f, 2_000.0f),
    SMALL(0.0f, 3_000.0f),
    DEFAULT(0.0f, 0.0f);

    private final float areaSizeRequired;
    private final float zoomLevelRequired;

    ElementSize(float areaSizeRequired, float zoomLevelRequired) {
        this.areaSizeRequired = areaSizeRequired;
        this.zoomLevelRequired = zoomLevelRequired;
    }

    public static ElementSize getSize(double areaSize) {
        for (ElementSize elementSize : ElementSize.values()) {
            if (areaSize >= elementSize.getAreaSizeRequired()) {
                return elementSize;
            }
        }
        return ElementSize.SMALL;
    }

    public boolean doShowElement(double zoomLevel) {
        return zoomLevel >= zoomLevelRequired;
    }

    public double getAreaSizeRequired() {
        return areaSizeRequired;
    }
}
