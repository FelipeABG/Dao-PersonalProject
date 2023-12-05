package application;

import dao.Dao;
import dao.DaoFactory;
import dao.implementation.SellerDao;
import entities.Department;
import entities.Seller;
import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args){

        Dao<Seller> sellerDao = DaoFactory.createSellerDao();
        Dao<Department> departmentDao = DaoFactory.createDepartmentDao();


        //Seller Dao tests
        System.out.println("#### Test seller find all ####");
        List<Seller> sellerList1 = sellerDao.findAll();
        sellerList1.forEach(System.out::println);
        System.out.println();


        System.out.println("#### Test seller find by department ####");
        List<Seller> sellerList2 = ((SellerDao)sellerDao).findByDepartment(departmentDao.findById(1));
        sellerList2.forEach(System.out::println);
        System.out.println();


        System.out.println("#### Test seller find by id ####");
        Seller seller = sellerDao.findById(2);
        System.out.println(seller);
        System.out.println();


        System.out.println("#### Test seller insert ####");
        Seller seller1 = new Seller("Felipe Orange", "felipe@gmail.com",
                Date.valueOf("2023-12-03"),1500.00 ,departmentDao.findById(3));
        sellerDao.insert(seller1);
        System.out.println(sellerDao.findById(seller1.getId()));
        System.out.println();


        System.out.println("#### Test seller update ####");
        Seller seller2 = sellerDao.findById(4);
        seller2.setBaseSalary(7600.00);
        sellerDao.update(seller2);
        System.out.println(sellerDao.findById(4));
        System.out.println();


        System.out.println("#### Test seller delete ####");
        sellerDao.deleteById(1);
        System.out.println(sellerDao.findById(1));
        System.out.println();



        //Department Dao tests
        System.out.println("#### Test department insert");
        Department department = new Department("Human resources");
        departmentDao.insert(department);
        System.out.println(departmentDao.findById(department.getId()));
        System.out.println();


        System.out.println("#### Test department delete ####");
        departmentDao.deleteById(department.getId());
        System.out.println(departmentDao.findById(department.getId()));
        System.out.println();


        System.out.println("#### Test department find all");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);
        System.out.println();


        System.out.println("#### Test department update");
        Department dp = departmentDao.findById(1);
        dp.setName("Nothing");
        departmentDao.update(dp);
        System.out.println(departmentDao.findById(dp.getId()));


        System.out.println("#### Test department find by id ####");
        System.out.println(departmentDao.findById(3));
        System.out.println();

    }
}
