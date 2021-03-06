package bfst21.osm;

import bfst21.models.DisplayOption;
import bfst21.models.DisplayOptions;
import bfst21.models.TransportOption;
import javafx.scene.paint.Color;


/**
 * ElementType is used to separate Ways and Relations into different
 * type categories depending on their tag data in the given OSM data.
 * <p>
 * Each ElementType has their own values for drawing size, required zoom level,
 * possible line dashes and colors for the different color modes.
 */
public enum ElementType {

    //It is very important that each ElementType is declared in the correct drawing order
    //For example, we do not want to draw BUILDING below ISLAND
    ISLAND(
            1f,
            0.0f,
            0.0D,
            Color.rgb(223, 222, 222),
            Color.rgb(151, 151, 151),
            Color.rgb(25, 26, 28)
    ),
    LANDUSE(
            1f,
            400.0f,
            0.0D,
            Color.rgb(172, 220, 180),
            Color.rgb(69, 80, 7),
            Color.rgb(29, 50, 36)
    ),
    FOREST(
            1f,
            800.0f,
            0.0D,
            Color.rgb(162, 210, 170),
            Color.rgb(59, 70, 0),
            Color.rgb(19, 40, 26)
    ),
    WATER(
            1f,
            400.0f,
            0.0D,
            Color.rgb(160, 196, 252),
            Color.rgb(0, 126, 170),
            Color.rgb(52, 66, 93)
    ),
    WATERWAY(
            0.0002f,
            8000.0f,
            0.0D,
            Color.rgb(160, 196, 252),
            Color.rgb(0, 126, 170),
            Color.rgb(52, 66, 93)
    ),
    SUBWAY(
            0.00006f,
            2000.0f,
            0.00009D,
            Color.rgb(80, 80, 80),
            Color.rgb(80, 80, 80),
            Color.rgb(200, 200, 200)
    ),
    FERRY(
            0.0002f,
            800.0f,
            0.0001D,
            Color.rgb(126, 142, 244),
            Color.rgb(126, 142, 244),
            Color.rgb(126, 142, 244)
    ),
    CYCLEWAY(
            0.00005f,
            70000.0f,
            0.00005D,
            Color.rgb(33, 33, 250),
            Color.rgb(33, 33, 250),
            Color.rgb(52, 66, 130)
    ),
    FOOTWAY(
            0.00005f,
            70000.0f,
            0.00005D,
            Color.rgb(252, 132, 116),
            Color.rgb(152, 32, 116),
            Color.rgb(152, 32, 116)
    ),
    PEDESTRIAN(
            0.0001f,
            40000.0f,
            0.0D,
            Color.rgb(223, 241, 242),
            Color.rgb(223, 241, 242),
            Color.rgb(61, 64, 67)
    ),
    SERVICE(
            0.0003f,
            40000.0f,
            0.0D,
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(61, 64, 67)
    ),
    ROAD(
            0.0003f,
            40000.0f,
            0.0D,
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(61, 64, 67)
    ),
    RESIDENTIAL(
            0.00015f,
            8000.0f,
            0.0D,
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(61, 64, 67)
    ),
    RAILWAY(
            0.00006f,
            1400.0f,
            0.0D,
            Color.rgb(80, 80, 80),
            Color.rgb(80, 80, 80),
            Color.rgb(200, 200, 200)
    ),
    TRUNK(
            0.0003f,
            40000.0f,
            0.0D,
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(61, 64, 67)
    ),
    MOTORWAY(
            0.0003f,
            400.0f,
            0.0D,
            Color.rgb(248, 197, 81),
            Color.rgb(248, 198, 81),
            Color.rgb(150, 129, 67)
    ),
    TERTIARY(
            0.0003f,
            2000.0f,
            0.0D,
            Color.rgb(255, 255, 255),
            Color.rgb(255, 255, 255),
            Color.rgb(61, 64, 67)
    ),
    PRIMARY(
            0.0003f,
            1200.0f,
            0.0D,
            Color.rgb(236, 148, 164),
            Color.rgb(255, 140, 0),
            Color.rgb(95, 99, 104)
    ),
    AEROWAY(
            0.002f,
            4000.0f,
            0.0D,
            Color.rgb(200, 200, 200),
            Color.rgb(200, 200, 200),
            Color.rgb(61, 64, 67)
    ),
    BUILDING(
            1f,
            30000.0f,
            0.0D,
            Color.rgb(197, 185, 175),
            Color.rgb(51, 51, 51),
            Color.rgb(89, 89, 89)
    ),
    UNKNOWN(
            0.002f,
            400.0f,
            0.0D,
            Color.rgb(255, 0, 0),
            Color.rgb(255, 0, 0),
            Color.rgb(255, 0, 0)
    );

    private final Color color, colorBlind, blackWhite;
    private final float drawSize, zoomLevelRequired;
    private final double lineDashes;

    ElementType(float drawSize, float zoomLevelRequired, double lineDashes, Color color, Color colorBlind, Color blackWhite) {
        this.color = color;
        this.colorBlind = colorBlind;
        this.blackWhite = blackWhite;
        this.drawSize = drawSize;
        this.lineDashes = lineDashes;
        this.zoomLevelRequired = zoomLevelRequired;
    }

    /**
     * Determine if this ElementType should be using the fill drawing method.
     */
    public boolean doFillDraw() {
        return this == ElementType.BUILDING ||
                this == ElementType.FOREST ||
                this == ElementType.ISLAND ||
                this == ElementType.LANDUSE ||
                this == ElementType.WATER;
    }

    /**
     * Determine if there should be created multiple trees for this ElementType.
     * A tree will be created for each ElementSize with this ElementType.
     */
    public boolean hasMultipleSizes() {
        return this == ElementType.BUILDING ||
                this == ElementType.FOREST ||
                this == ElementType.LANDUSE ||
                this == ElementType.WATER;
    }

    /**
     * Determine if this ElementType can be navigated with the given TransportOption.
     */
    public boolean canNavigate(TransportOption transportOption) {
        if (transportOption == TransportOption.CAR) {
            return this == ElementType.MOTORWAY ||
                    this == ElementType.PRIMARY ||
                    this == ElementType.RESIDENTIAL ||
                    this == ElementType.ROAD ||
                    this == ElementType.TERTIARY ||
                    this == ElementType.TRUNK;

        } else if (transportOption == TransportOption.BIKE) {
            return this == ElementType.CYCLEWAY ||
                    this == ElementType.RESIDENTIAL ||
                    this == ElementType.ROAD ||
                    this == ElementType.TERTIARY;

        } else if (transportOption == TransportOption.WALK) {
            return this == ElementType.CYCLEWAY ||
                    this == ElementType.FOOTWAY ||
                    this == ElementType.RESIDENTIAL ||
                    this == ElementType.ROAD ||
                    this == ElementType.TERTIARY;
        } else {
            return this == ElementType.CYCLEWAY ||
                    this == ElementType.FOOTWAY ||
                    this == ElementType.MOTORWAY ||
                    this == ElementType.PRIMARY ||
                    this == ElementType.RESIDENTIAL ||
                    this == ElementType.ROAD ||
                    this == ElementType.TERTIARY ||
                    this == ElementType.TRUNK;
        }
    }

    public boolean isDisplayOptionEnabled() {
        return DisplayOptions.getInstance().getBool(DisplayOption.valueOf("DISPLAY_" + this));
    }

    public double getLineDashes() {
        return lineDashes;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorBlind() {
        return colorBlind;
    }

    public Color getBlackWhite() {
        return blackWhite;
    }

    public float getDrawSize() {
        return drawSize;
    }

    public boolean doShowElement(double zoomLevel) {
        return zoomLevel >= zoomLevelRequired;
    }
}