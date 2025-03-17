/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;


/**
 *
 * @author hipp
 */
public class CategoryDAO  extends DBBase{
    
    public List<Category> getAllCategory() {
        List<Category> productList = new ArrayList<>();
        PreparedStatement stm = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Category";  

        try {
            if (connection != null) {
                System.out.println("Kết nối cơ sở dữ liệu thành công!");

                stm = connection.prepareStatement(sql);
                rs = stm.executeQuery();

                while (rs.next()) {
                    Category category = new Category(
                        rs.getInt(1),  // categoryId
                        rs.getString(2) // categoryName
                    );
                    productList.add(category);
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
    
     
    }

