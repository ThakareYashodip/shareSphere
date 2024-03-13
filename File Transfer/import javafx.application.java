import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class StarButtonExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a star shape using Polygon
        Polygon star = createStar();

        // Create a button and set its shape to the star
        Button starButton = new Button();
        starButton.setShape(star);

        // Create a layout and add the button
        StackPane root = new StackPane();
        root.getChildren().add(starButton);

        // Create a scene
        Scene scene = new Scene(root, 200, 200);

        // Set the stage properties
        primaryStage.setTitle("Star Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Polygon createStar() {
        // Define the points of a star
        double[] starPoints = {
                0, -50,
                15, -15,
                50, 0,
                15, 15,
                0, 50,
                -15, 15,
                -50, 0,
                -15, -15
        };

        // Create a Polygon with the specified points
        Polygon star = new Polygon(starPoints);

        // Set the fill color of the star (you can customize this)
        star.setStyle("-fx-fill: gold;");

        return star;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
