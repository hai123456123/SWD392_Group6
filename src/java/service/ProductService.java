/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.Product;
import dao.ProductDao;
import proxy.CloudinaryProxy;

import java.io.File;
import java.util.List;
/**
 *
 * @author admin
 */
public class ProductService {
    private ProductDao productDAO;
    private CloudinaryProxy cloudinaryProxy;

    public ProductService() {
        productDAO = new ProductDao();
        cloudinaryProxy = new CloudinaryProxy();
    }

    public boolean addProduct(Product product, File imageFile) {
        try {
            // Upload ảnh lên Cloudinary
            String imageUrl = cloudinaryProxy.uploadImage(imageFile);
            if (imageUrl == null) {
                System.out.println("Lỗi upload ảnh. Không thể thêm sản phẩm.");
                return false;
            }

            // Set URL ảnh vào sản phẩm
            product.setImage(imageUrl);

            // Gọi DAO để lưu vào database
            return productDAO.addProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hàm lấy danh sách sản phẩm
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Hàm tìm sản phẩm theo ID
    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    // Hàm cập nhật sản phẩm
    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    // Hàm xóa sản phẩm theo ID
    public boolean deleteProduct(int productId) {
        return productDAO.deleteProduct(productId);
    }
}
