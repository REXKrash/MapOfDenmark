package bfst21.osm;

import bfst21.view.ColorMode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Objects;


/**
 * UserNode is a point of interest selected by the user.
 * Every UserNode has a name, description and coordinates.
 */
public class UserNode extends Node implements Serializable {

    private static final long serialVersionUID = -5801814520475467424L;

    private String name;
    private String description;

    public UserNode(float lat, float lon, String name, String description) {
        super(lat, lon);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void draw(GraphicsContext gc, double zoomLevel, ColorMode colorMode) {
        double x = getX() - (14.0D / zoomLevel);
        double y = getY() - (14.0D / zoomLevel);

        Image orangeStar = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/orange_star.png")));
        Image greenStar = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("images/green_star.png")));

        if (colorMode == ColorMode.DARK_MODE) {
            gc.drawImage(greenStar, x, y, 28.0D / zoomLevel, 28.0D / zoomLevel);
        } else {
            gc.drawImage(orangeStar, x, y, 28.0D / zoomLevel, 28.0D / zoomLevel);
        }
    }
}