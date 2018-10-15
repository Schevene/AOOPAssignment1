package Controllers;

import Models.ChangeView;
import Models.Contact;
import Models.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ContactsViewController implements Initializable {

    @FXML
    private TableView<Contact> contactsTable;

    @FXML
    private TableColumn<Contact, String> firstNameCol;

    @FXML
    private TableColumn<Contact, String> lastNameCol;

    @FXML
    private TableColumn<Contact, String> birthdayCol;

    @FXML
    private TableColumn<Contact, String> addressCol;

    @FXML
    private TableColumn<Contact, String> phoneNumCol;

    @FXML
    private Button createButton;

    /**
     * this method sets the main scene and what will run when it starts
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //set up columns
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("lastName"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("birthday"));
        addressCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("address"));
        phoneNumCol.setCellValueFactory(new PropertyValueFactory<Contact, String>("phoneNum"));

        try {
            contactsTable.getItems().addAll(DatabaseConnection.getContacts());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * change scenes to the AddContactView to allow the user to submit a new contact
     * when the button is pushed on the GUI
     * @param event
     * @throws IOException
     */
    @FXML
    public void launchAddContactView(ActionEvent event) throws IOException {
        ChangeView.changeScenes(event, "Views/AddContactView.fxml","New Contact Profile");
    }
}
