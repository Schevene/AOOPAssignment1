import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaunchContactsView extends Application {
    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * set the scene to be launched in the main method, the contact table
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Views/ContactsView.fxml"));
        Parent parent = loader.load();
        Scene scene = new Scene(parent);

        primaryStage.setTitle("Phone Book");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
