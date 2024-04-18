package db;

import Entities.Contact;
import Entities.PhoneNumber;
import exceptions.MaxPhoneNumbersAmountException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ContactRepository {
    private Connection connection;

    public ContactRepository(Connection connection) {
        this.connection = connection;
    }

    public void saveContact(Contact contact) throws SQLException {
        String insertContactSql = "INSERT INTO contacts (name, surname) VALUES (?, ?);";
        PreparedStatement pstmt = connection.prepareStatement(insertContactSql, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, contact.getName());
        pstmt.setString(2, contact.getSurname());
        pstmt.executeUpdate();

        // Получаем сгенерированный ID для контакта
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        if (generatedKeys.next()) {
            long contactId = generatedKeys.getLong(1);

            // Теперь сохраняем номера телефонов
            for (PhoneNumber number : contact.getNumbers()) {
                savePhoneNumber(number, contactId);
            }
        }
        pstmt.close();
    }

    private void savePhoneNumber(PhoneNumber number, long contactId) throws SQLException {
        String insertNumberSql = "INSERT INTO phone_numbers (number, contact_id) VALUES (?, ?);";
        PreparedStatement pstmt = connection.prepareStatement(insertNumberSql);
        pstmt.setString(1, number.getNumber());
        pstmt.setLong(2, contactId);
        pstmt.executeUpdate();
        pstmt.close();
    }

    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        Map<Long, Contact> contactMap = new HashMap<>();

        String selectAllContactsSql =
                "SELECT c.id, c.name, c.surname, pn.id as phone_id, pn.number " +
                        "FROM contacts c " +
                        "LEFT JOIN phone_numbers pn ON c.id = pn.contact_id " +
                        "ORDER BY c.id, pn.id;";

        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(selectAllContactsSql);

        while (resultSet.next()) {
            long contactId = resultSet.getLong("id");
            Contact contact = contactMap.get(contactId);

            // Если этого контакта еще нет в map, создаем его и добавляем номера телефонов
            if (contact == null) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                contact = new Contact(contactId, name, surname);
                contactMap.put(contactId, contact);
                contacts.add(contact);
            }

            // Если у контакта есть телефонные номера, добавляем их
            long phoneId = resultSet.getLong("phone_id");
            if (!resultSet.wasNull()) {
                String number = resultSet.getString("number");
                try {
                    contact.addNumber(new PhoneNumber(number, phoneId));
                } catch (MaxPhoneNumbersAmountException exception) {
                    System.out.println("Max amount of numbers is 3. you cannot and fourth");
                }
            }
        }

        resultSet.close();
        stmt.close();
        return contacts;
    }
}