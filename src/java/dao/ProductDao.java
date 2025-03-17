/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;



import model.Product;
import model.Category;
import model.Brand;
import model.ProductVariant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductDao extends DBBase{


    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    public ProductDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Product> getAllProducts() {
        List<Product> productList = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Product";

        try {
            if (connection != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");

                stm = connection.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    Product product = new Product(
                        rs.getInt("id"),
                        rs.getInt("isHidden"),
                        rs.getString("name"),
                        rs.getString("image"),
                        rs.getInt("price"),
                        rs.getString("description"),
                        rs.getInt("stock"),
                        rs.getString("chip"),
                        rs.getString("ram"),
                        rs.getString("rom"),
                        rs.getString("gpu"),
                        new Category(rs.getInt("category_id"), ""),
                        new Brand(rs.getInt("brand_id"), ""),
                        new ProductVariant(rs.getInt("variant_id"), "")
                    );
                    productList.add(product);
                }

            } else {
                System.out.println("Kết nối cơ sở dữ liệu bị null.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stm != null) stm.close();
            } catch (SQLException e) {
                System.out.println("Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }

        return productList;
    }
    // Lấy sản phẩm theo ID
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return mapResultSetToProduct(rs);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy sản phẩm theo ID: " + e.getMessage());
        }
        return null;
    }

    // Thêm sản phẩm mới
    public boolean addProduct(Product product) {
        String sql = "INSERT INTO Product (name, image, price, description, stock, chip, ram, rom, gpu, category_id, brand_id, isHidden) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            setProductStatement(product, stm);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sản phẩm: " + e.getMessage());
        }
        return false;
    }
    
    // Cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        String sql = "UPDATE Product SET name=?, image=?, price=?, description=?, stock=?, chip=?, ram=?, rom=?, gpu=?, category_id=?, brand_id=?, isHidden=? WHERE id=?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            setProductStatement(product, stm);
            stm.setInt(13, product.getId());
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật sản phẩm: " + e.getMessage());
        }
        return false;
    }

    // Xóa sản phẩm theo ID
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, id);
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa sản phẩm: " + e.getMessage());
        }
        return false;
    }

    // Ánh xạ ResultSet thành đối tượng Product
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        return new Product(
            rs.getInt("id"),
            rs.getInt("isHidden"),
            rs.getString("name"),
            rs.getString("image"),
            rs.getInt("price"),
            rs.getString("description"),
            rs.getInt("stock"),
            rs.getString("chip"),
            rs.getString("ram"),
            rs.getString("rom"),
            rs.getString("gpu"),
            new Category(rs.getInt("category_id"), null),
            new Brand(rs.getInt("brand_id"), null),
            new ProductVariant() // Bạn có thể thêm logic cho ProductVariant nếu cần
        );
    }

    // Thiết lập giá trị cho PreparedStatement từ Product
    private void setProductStatement(Product product, PreparedStatement stm) throws SQLException {
        stm.setString(1, product.getName());
        stm.setString(2, product.getImage());
        stm.setInt(3, product.getPrice());
        stm.setString(4, product.getDescription());
        stm.setInt(5, product.getStock());
        stm.setString(6, product.getChip());
        stm.setString(7, product.getRam());
        stm.setString(8, product.getRom());
        stm.setString(9, product.getGpu());
        stm.setInt(10, product.getCategory().getId());
        stm.setInt(11, product.getBrand().getId());
        stm.setInt(12, product.getIsHidden());
    }
}
