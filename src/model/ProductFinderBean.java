package model;

import da.ProductDataAccess;
import entity.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductFinderBean {
    private String keyword;
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<Product> getAllProduct() throws SQLException, ClassNotFoundException {
       return new ProductDataAccess().getProductsByName(keyword);
    }
    public boolean createProduct(Product product) throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().createProduct(product);
    }
    public List<Product> getProducts() throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().selectAllProduct();
    }
    public boolean deleteProduct(int id) throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().deleteProduct(id);
    }
    public boolean updateProduct(Product product) throws SQLException, ClassNotFoundException {
        return new ProductDataAccess().updateProduct(product);
    }
}
