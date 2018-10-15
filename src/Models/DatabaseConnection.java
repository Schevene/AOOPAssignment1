package Models;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private static String userName = "gcc200380055";
    private static String password = "1g6IHRNEQ-";

    public static ArrayList<Contact> getContacts() throws SQLException {
        ArrayList<Contact> contacts = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            conn = DriverManager.getConnection
                    ("jdbc:mysql://aws.computerstudi.es:3306/gcc200380055?useSSL=false",
                            userName, password);

            statement = conn.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM contacts ORDER BY contactId");

            while (resultSet.next()) {
                Contact contact = new Contact(
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("birthday"),
                        resultSet.getString("address"),
                        resultSet.getString("phoneNum"),
                        resultSet.getString("contactImage"));
                contacts.add(contact);
            }

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }

        return contacts;
    }

    public static void insertContactsIntoDB(Contact newContact) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            //1.  Connect to the DB
            conn = DriverManager.getConnection
                    ("jdbc:mysql://aws.computerstudi.es:3306/gcc200380055?useSSL=false",
                    userName, password);

            //2.  create a sql statement
            String sql = "INSERT INTO contacts (firstName, lastName, birthday, address, phoneNum," +
                    " contactImage) VALUES (?, ?, ?, ?, ?, ?);";

            //3. create a prepared statement
            ps = conn.prepareStatement(sql);

            //4.  bind the parameters
            ps.setString(1, newContact.getFirstName());
            ps.setString(2, newContact.getLastName());
            ps.setDate(3, newContact.getBirthday());
            ps.setString(4, newContact.getAddress());
            ps.setString(5, newContact.getPhoneNum());
            ps.setString(6, newContact.getContactImage());

            //5.  execute the update
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if(ps != null)
                ps.close();
        }
    }
}
