/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import dao.AccountDao;
import model.User;

/**
 *
 * @author DELL
 */
public class AccountService {
    private AccountDao accountDAO = new AccountDao();

    public User login(String email, String password) {
        return accountDAO.GetAccount(email, password);
    }
}
