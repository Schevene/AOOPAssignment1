package Models;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeView {

    /**
     * the method will trigger the ActionEvent where a scene is called by its name and title and is launched
     * from its location
     * @param event
     * @param viewName
     * @param title
     * @throws IOException
     */
    public static void changeScenes(ActionEvent event, String viewName, String title) throws IOException {
        Parent root = FXMLLoader.load(new Object(){}.getClass().getClassLoader().getResource(viewName));
        Scene scene = new Scene(root);

        Stage primaryStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.show();
    }
}
