package Controllers;

import Models.ChangeView;
import Models.Contact;
import Models.DatabaseConnection;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddContactViewController implements Initializable {

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField phoneNumberText;

    @FXML
    private DatePicker birthdayDatePicker;

    @FXML
    private ImageView contactImageView;

    @FXML
    private Label eLabel;

    @FXML
    private Button uploadButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button saveButton;

    private File upload;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        contactImageView.setImage(new Image("Images/msnGuy.png"));

        eLabel.setText(" ");
    }

    /**
     * this method will take the user back to the contacts table when the cancel button is pushed
     * @param event
     * @throws IOException
     */
    @FXML
    public void cancelButtonPushed(ActionEvent event) throws IOException {
        ChangeView.changeScenes(event, "Views/ContactsView.fxml", "Phone Book");
    }

    /**
     *this method will save the fields to the database when the save
     * button is pushed
     */
    public void saveContact(ActionEvent event) throws IOException {
        try {
            Contact contact = new Contact(
                    firstNameText.getText(),
                    lastNameText.getText(),
                    java.sql.Date.valueOf(birthdayDatePicker.getValue()),
                    addressText.getText(),
                    phoneNumberText.getText(),
                    contactImageView.getImage().toString()
            );
            DatabaseConnection.insertContactsIntoDB(contact);
            ChangeView.changeScenes(event, "Views/ContactsView.fxml", "Phone Book");
        } catch (IllegalArgumentException | SQLException e) {
            this.eLabel.setText(e.getMessage());
        }
    }

    /**
     * this method will let the user choose an image from their computer
     * when the upload image button is pushed, so that the user may
     * upload it
     * @param event
     */
    public void uploadImageButtonPushed(ActionEvent event)
    {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose an Image");

        FileChooser.ExtensionFilter jpgFilter =
                new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter =
                new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");
        chooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File picDirectory = new File(userDirectoryString);

        if (!picDirectory.canRead())
            picDirectory = new File(System.getProperty("user.home"));

        chooser.setInitialDirectory(picDirectory);

        File openFile = chooser.showOpenDialog(stage);

        if (openFile != null)
        {
            upload = openFile;

            if (upload.isFile())
            {
                try
                {
                    BufferedImage bufferedImage = ImageIO.read(upload);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    contactImageView.setImage(img);
                }
                catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }

    }
}