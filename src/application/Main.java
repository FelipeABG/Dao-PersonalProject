package application;

import dao.Dao;
import dao.DaoFactory;
import dao.implementation.SellerDao;
import entities.Department;
import entities.Seller;

import java.util.List;

public class Main {
    public static void main(String[] args){

        Dao<Seller> sellerDao = DaoFactory.createSellerDao();
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("#### Test seller find all ####");
        List<Seller> sellerList1 = sellerDao.findAll();
        sellerList1.forEach(System.out::println);
        System.out.println();


        System.out.println("#### Test seller find by department ####");
        List<Seller> sellerList2 = ((SellerDao)sellerDao).findByDepartment(new Department(2, "Electronics"));
        sellerList2.forEach(System.out::println);
        System.out.println();


        System.out.println("#### Test seller find by id ####");
        Seller seller = sellerDao.findById(2);
        System.out.println(seller);
        System.out.println();



    }
}
