package kz.assel.joshapp.dao;

import kz.assel.joshapp.models.Product;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Component
public class ProductDao extends Dao{
    private static ProductDao instance;

    private ProductDao(){}

    public static ProductDao getInstance(){
        if (instance == null){
            instance = new ProductDao();
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

    public List<Product> index(){
        List<Product> products = new LinkedList<>();

        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");

            while (resultSet.next()){
                Product product = new Product();

                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setCompany(resultSet.getString("company"));
                product.setStatus(resultSet.getString("status"));
                product.setDetails(resultSet.getString("details"));

                products.add(product);
            }
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return products;
    }

    public Product show(int id){
        Product product = null;

        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Product WHERE id = ?");
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            product = new Product();

            product.setId(resultSet.getInt("id"));
            product.setName(resultSet.getString("name"));
            product.setCompany(resultSet.getString("company"));
            product.setStatus(resultSet.getString("status"));
            product.setDetails(resultSet.getString("details"));
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }

        return product;
    }

    public void save(Product product){
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Product VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getCompany());
            preparedStatement.setString(4, product.getStatus());
            preparedStatement.setString(5, product.getDetails());

            preparedStatement.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void update(int id, Product product){
        try{
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Product SET name = ?, company = ?, status = ?, details = ? WHERE id = ?");
            preparedStatement.setInt(5, id);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getCompany());
            preparedStatement.setString(3, product.getStatus());
            preparedStatement.setString(4, product.getDetails());

            preparedStatement.executeUpdate();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
