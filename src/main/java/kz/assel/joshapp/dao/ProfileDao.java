package kz.assel.joshapp.dao;

import kz.assel.joshapp.controllers.authentication.Encryption;
import kz.assel.joshapp.controllers.authentication.Hash;
import kz.assel.joshapp.controllers.authentication.MAC;
import kz.assel.joshapp.models.Profile;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class ProfileDao extends Dao{
    private static ProfileDao instance;

    private ProfileDao(){}

    public static ProfileDao getInstance(){
        if (instance == null){
            instance = new ProfileDao();
        }
        return instance;
    }

    private static final String URL = "jdbc:postgresql://localhost:5432/joshapp";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean check(Profile profile){
        boolean res = false;

        Encryption HMAC = new MAC(new Hash(profile.getPassword()));
        String password_ent = HMAC.getEncryption();

        String username_ent = profile.getUsername();

        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Administration WHERE username = ?");
            preparedStatement.setString(1, username_ent);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String password = resultSet.getString("password");

            if (password_ent.equals(password)){
                res = true;
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return res;
    }


}
