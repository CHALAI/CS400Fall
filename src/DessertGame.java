import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {

    int score = 0;

    @Override
    public void start(final Stage stage) {
        // Step 3 & 4
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 640, 480);
        stage.setTitle("Dessert in the Desert JavaFX Game");

        // Step 5
        Label scoreLabel = new Label("Score: 0");
        borderPane.setTop(scoreLabel);
        BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });
        borderPane.setBottom(exitButton);
        BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

        // Step 6
        Pane pane = new Pane();
        borderPane.setCenter(pane);
        BorderPane.setAlignment(pane, Pos.CENTER);

        // TODO: Step 7-10

        Button dessert = new Button("Dessert");
        Button desert1 = new Button("Desert");
        Button desert2 = new Button("Desert");
        Button desert3 = new Button("Desert");
        Button desert4 = new Button("Desert");
        Button desert5 = new Button("Desert");
        Button desert6 = new Button("Desert");
        Button desert7 = new Button("Desert");
        Random random = new Random();
        Button[] array = new Button[8];
        array[0] = dessert;
        array[1] = desert1;
        array[2] = desert2;
        array[3] = desert3;
        array[4] = desert4;
        array[5] = desert5;
        array[6] = desert6;
        array[7] = desert7;
        for (int i = 0; i<7; i++){
            pane.getChildren().add(array[i]);
        }
        dessert.setOnAction(event -> {
            score++;
            scoreLabel.setText("Score: " + score);
            exitButton.requestFocus();
            randomizeButtonPositions(random, array);
        });
        for(int i = 1; i<7; i++){
            array[i].setOnAction(event->{
                score--;
                scoreLabel.setText("Score: " + score);
                exitButton.requestFocus();
                randomizeButtonPositions(random,array);
            });
        }
        randomizeButtonPositions(random, array);
        exitButton.requestFocus();
        stage.setScene(scene);
        stage.show();
    }

    private void randomizeButtonPositions(Random random, Button[] array){
        for(Button i : array){
            i.setLayoutX(random.nextInt(600));
            i.setLayoutY(random.nextInt(400));
        }
    }
    public static void main(String[] args) {
        Application.launch();
    }
}