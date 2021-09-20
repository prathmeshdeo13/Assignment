package assignment;

import java.sql.*;
import java.util.Scanner;

public class Inventory {

    public static void main(String[] args) {

        String user;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select ShopKeeper or Customer");
        user = scanner.next();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignment?characterEncoding =latin1", "root", "password");
            Statement statement = connection.createStatement();

            int id;
            String name;
            int price;
            int quantity;
            PreparedStatement preparedStatement = null;

            switch (user) {
                case "Shopkeeper":
                    Scanner scanner1 = new Scanner(System.in);
                    String option;
                    System.out.println("Choose option");
                    option = scanner1.next();

                    switch (option) {
                        case "Add":
                            Scanner sc = new Scanner(System.in);
                            System.out.println("Enter id of product");
                            id = sc.nextInt();
                            System.out.println("Enter name of product");
                            name = sc.next();
                            System.out.println("Enter price of product");
                            price = sc.nextInt();
                            System.out.println("Enter quantity of product");
                            quantity = sc.nextInt();
                            String insert = ("insert into product(id,name,price,quantity) values(?,?,?,?)");
                            preparedStatement = connection.prepareStatement(insert);
                            preparedStatement.setInt(1, id);
                            preparedStatement.setString(2, name);
                            preparedStatement.setInt(3, price);
                            preparedStatement.setInt(4, quantity);
                            int i = preparedStatement.executeUpdate();
                            System.out.println("Record added " + i);
                            break;

                        case "remove":
                            Scanner sc2 = new Scanner(System.in);
                            int removeId;
                            System.out.println("Enter the id for removing the product ");
                            removeId = sc2.nextInt();

                            String remove = "delete from product where id = " + removeId;
                            preparedStatement = connection.prepareStatement(remove);
                            int j = preparedStatement.executeUpdate();
                            System.out.println("Record removed " + j);
                            break;

                        case "list":
                            String list = "select * from product";
                            preparedStatement = connection.prepareStatement(list);
                            ResultSet rs = preparedStatement.executeQuery();
                            while (rs.next()) {
                                System.out.println(rs.getInt(1));
                                System.out.println(rs.getInt(2));
                                System.out.println(rs.getInt(3));
                                System.out.println(rs.getInt(4));
                            }
                            break;

                        case "edit":
                            Scanner sc3 = new Scanner(System.in);
                            System.out.println("Enter the id");
                            id = sc3.nextInt();
                            System.out.println("Enter the name");
                            name = sc3.next();
                            System.out.println("Enter the price");
                            price = sc3.nextInt();
                            System.out.println("Enter the quantity");
                            quantity = sc3.nextInt();
                            String update = ("update product set id = ?, name =? , price ?, quantity=?  where id=? ,name=?,price=?,quantity=?");
                            preparedStatement = connection.prepareStatement(update);

                            preparedStatement.setInt(1, id);
                            preparedStatement.setString(2, name);
                            preparedStatement.setInt(3, price);
                            preparedStatement.setInt(4, quantity);

                            int z = preparedStatement.executeUpdate();
                            System.out.println("Edited product " + z);
                    }
                    break;
                default:
                    System.out.println("Invalid Input");
            }

            switch (user) {
                case "Customer":
                    Scanner scanner2 = new Scanner(System.in);
                    String option1;
                    System.out.println("Choose Option");
                    option1 = scanner2.next();

                    switch (option1) {
                        case "list":
                            System.out.println("select * from orders");
                        case "edit":
                            System.out.println("select * from orders");
                    }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
