package Models;

import java.sql.Date;
import java.time.LocalDate;
import java.sql.Blob;
import java.time.Period;
import java.util.Calendar;

public class Contact {
    private String firstName, lastName, address, phoneNum;
    private Date birthday;
    private String contactImage;

    /**
     *Constructor for the Models.Contact class, instantiating the variables
     * @param firstName
     * @param lastName
     * @param birthday
     * @param address
     * @param phoneNum
     * @param contactImage
     */
    public Contact(String firstName, String lastName, Date birthday, String address, String phoneNum, String contactImage) {
        setFirstName(firstName);
        setLastName(lastName);
        setBirthday(birthday);
        setAddress(address);
        setPhoneNum(phoneNum);
        setContactImage(contactImage);
    }

    /**
     * get method for firstName
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * set method for firstName that verifies that it is not left empty
     * @param firstName
     */
    public void setFirstName(String firstName) {
        if(firstName.isEmpty()) {
            throw new IllegalArgumentException("Please fill in the first name of the new contact before submitting.");
        }
        else this.firstName = firstName;
    }

    /**
     * get method for lastName
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * set method for lastName that verifies that it is not left empty
     * @param lastName
     */
    public void setLastName(String lastName) {
        if(lastName.isEmpty()) {
            throw new IllegalArgumentException("Please fill in the last name of the new contact before submitting.");
        }
        else this.lastName = lastName;
    }

    /**
     * get method for birthday
     * @return
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * set method for birthday that verifies that it is for a person of
     * usual phone carrying age
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int year = cal.get(Calendar.YEAR);
        int age = LocalDate.now().getYear() - year;
        if(age <= 9 && age >=100)
        {
            throw new IllegalArgumentException("Please choose a birthday of a person with an age between 9 and 100");
        }
        else if (birthday == null)
        {
            throw new IllegalArgumentException("Please choose a birthday.");
        }
        else this.birthday = birthday;
    }

    /**
     * get method for address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * set method for address that verifies that it is not left empty
     * @param address
     */
    public void setAddress(String address) {
       if(address.isEmpty())
       {
           throw new IllegalArgumentException("Please fill in an address for the new contact before submitting.");
       }
        else this.address = address;
    }

    /**
     * get method for phoneNum
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * get method for phoneNum that verifies that it is not left empty
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
       if(phoneNum.isEmpty())
       {
           throw new IllegalArgumentException("Please fill in a phone number for the new contact before submitting.");
       }
        else this.phoneNum = phoneNum;
    }

    /**
     * get method for image
     * @return
     */
    public String getContactImage() {
        return contactImage;
    }

    /**
     * set method for image that verifies that an image is uploaded before submitting
     * @param contactImage
     */
    public void setContactImage(String contactImage) {
        if(contactImage == null)
        {
            throw new IllegalArgumentException("Please add a picture for the new contact before submitting.");
        }
        else this.contactImage = contactImage;
    }
}
