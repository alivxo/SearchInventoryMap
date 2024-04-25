package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class InventoryMap {
    // the key is the product id (int)
    // the value is a product object (String)
    static HashMap<Integer, Product> inventory = new HashMap< >();

    public static void main(String[] args) {

        loadInventory("Inventory.csv");

        System.out.println("Welcome to the Inventory Map!");

        //Add a loadInventory() method to load all of the products from the Inventory.csv file
        // Create a product from each line and add it to a Map
        // Use the product name as the map key so that users can search for products by name.

        Scanner scanner = new Scanner(System.in);

        System.out.print("What item # are you interested in?: ");
        int id = scanner.nextInt();

        Product matchedProduct = inventory.get(id);
        if (matchedProduct != null) {
            System.out.println("Product found: ");
            System.out.println("id: " + matchedProduct.getId());
            System.out.println("Name: " + matchedProduct.getName());
            System.out.println("Price: " + matchedProduct.getPrice());
        } else {
            System.out.println("We don't carry that product");
        }
        System.out.printf("We carry %s and the price is $%.2f",
                matchedProduct.getName(), matchedProduct.getPrice());


    }

    //this method loads product objects into inventory
    public static void loadInventory( String action ) {
        try {
            // creating a file reader object to connect the file in this case inventory.csv
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");

            // creating a buffered reader to manage input stream
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String input;

            //below is reading until there are is no more data
            while ((input = bufferedReader.readLine()) != null) {
                String[] parts = input.split("\\|");
                int id = Integer.parseInt((parts[0]));
                String name = parts[1];
                float price = Float.parseFloat(parts[2]);

                Product product = new Product(id, name,price);
                inventory.put(id, product);
//                System.out.println(input);
            }

//             close the stream and release the resources
            bufferedReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

