package da;

import entity.Product;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class ProductDataAccess {
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private static final String INSERT_PRODUCT = "INSERT INTO product"+
            "(pro_name,pro_desc) VALUES (?,?);";
    private static final String DELETE_PRODUCT = "DELETE FROM product where id = ?";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM product";
    private static final String UPDATE_PRODUCT = "UPDATE product SET pro_name=?,pro_desc=?"+
            "WHERE id=?";

    public PreparedStatement getSearchStatement() throws SQLException, ClassNotFoundException {
        if(preparedStatement == null){
            //call connect database
            Connection connection = new DBConnection().getConnection();
            // 4. Create Statement
            preparedStatement = connection.prepareStatement
                    ("SELECT id,pro_name,pro_desc from Product  where pro_name like ? ");
        }
        return preparedStatement;
    }
    public PreparedStatement insertStatement() throws SQLException, ClassNotFoundException {
        if(preparedStatement == null){
            Connection connection = new DBConnection().getConnection();
            preparedStatement = connection.prepareStatement(INSERT_PRODUCT);
        }
        return preparedStatement;
    }
    public PreparedStatement deleteStatement() throws SQLException, ClassNotFoundException {
        if(preparedStatement == null){
            Connection connection = new DBConnection().getConnection();
            preparedStatement = connection.prepareStatement(DELETE_PRODUCT);
        }
        return preparedStatement;
    }
    public PreparedStatement updateStatement() throws SQLException, ClassNotFoundException {
        if(preparedStatement == null){
            Connection connection = new DBConnection().getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
        }
        return preparedStatement;
    }
    public PreparedStatement getAllStatement() throws SQLException, ClassNotFoundException {
        if(preparedStatement == null){
            Connection connection = new DBConnection().getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);
        }
        return preparedStatement;
    }
    public List<Product> getProductsByName(String name) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = getSearchStatement();
        // 5 : Call result set
        statement.setString(1, "%" + name + "%");
        ResultSet resultSet = statement.executeQuery();
        List<Product> products = new LinkedList<Product>();
        while (resultSet.next()) {
            products.add(new Product(resultSet.getInt("id"),
                    resultSet.getString("pro_name"),
                    resultSet.getString("pro_desc")
            ));
        }
        return products;
    }
    public List<Product> getProductsByDesc(String name) throws SQLException, ClassNotFoundException {
        return null;
    }
    public List<Product> getProductsByID(String name) throws SQLException, ClassNotFoundException {
        return null;
    }
    public boolean createProduct(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = insertStatement();
//        Product product = new Product();
        preparedStatement.setString(1,product.getName());
        preparedStatement.setString(2,product.getDesc());
        preparedStatement.executeUpdate();
        return true;
    }
    public boolean deleteProduct(int proId) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = deleteStatement();
//        Product product = new Product();
        preparedStatement.setInt(1,proId);
        preparedStatement.executeUpdate();
        return true;
    }
    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = updateStatement();
//        Product product = new Product();
        preparedStatement.setInt(1,product.getId());
        preparedStatement.setString(2,product.getName());
        preparedStatement.setString(3,product.getDesc());
        preparedStatement.executeUpdate();
        return true;
    }
    public List<Product> selectAllProduct() throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement = getAllStatement();
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Product> productList = new LinkedList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pro_name = resultSet.getString("pro_name");
            String pro_desc = resultSet.getString("pro_desc");
            productList.add(new Product(id,pro_name,pro_desc));
        }
        return productList;
    }
}
