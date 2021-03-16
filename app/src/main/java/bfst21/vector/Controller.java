package bfst21.vector;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.stage.Stage;

public class Controller {

    private Model model;
    private Point2D lastMouse;

    @FXML
    private MapCanvas canvas;

    public void init(Model model) {
        this.model = model;
        canvas.init(model);
    }

    @FXML
    private void onScroll(ScrollEvent e) {
        double factor = Math.pow(1.01, e.getDeltaY());
        canvas.preZoom(factor, new Point2D(e.getX(), e.getY()));
    }

    @FXML
    private void onMouseDragged(MouseEvent e) {
        double dx = e.getX() - lastMouse.getX();
        double dy = e.getY() - lastMouse.getY();
        if (e.isPrimaryButtonDown()) {
            canvas.pan(dx, dy);
        } else {
            Point2D from = canvas.mouseToModelCoords(lastMouse);
            Point2D to = canvas.mouseToModelCoords(new Point2D(e.getX(), e.getY()));
            model.add(new Line(from, to));
            canvas.repaint();
        }
        onMousePressed(e);
    }

    @FXML
    private void onMousePressed(MouseEvent e) {
        lastMouse = new Point2D(e.getX(), e.getY());
    }

    @FXML
    public void handleKeyInput(KeyEvent keyEvent) {
        System.out.println("Test. KeyEvent: " + keyEvent);
    }

    public void zoomButtonClicked(ActionEvent actionEvent) {
        if (actionEvent.getSource().toString().contains("zoomIn")){
            canvas.preZoom(2.0,new Point2D(canvas.getWidth()/2,canvas.getHeight()/2));
        } else {
            canvas.preZoom(0.50,new Point2D(canvas.getWidth()/2,canvas.getHeight()/2));
        }
        //TODO: tjek at zoom centreres præcis i midten af vinduet, så menubaren ikke tæller med
    }
}
