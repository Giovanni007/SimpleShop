package com.ynap.shop;

import java.io.BufferedReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ynap.shop.domain.Basket;
import com.ynap.shop.domain.Product;

public class YNAPShop {
	
	private final static String CSV_PATH = "src/main/resources/product-data.csv";
	
	private Map<String,Product> products = new HashMap<>();
	
	private Basket basket = new Basket();
	
	public static final Logger logger = LoggerFactory.getLogger(YNAPShop.class);

    
	/**
     * Load products from the .csv file
     */
    public void loadProducts() {
    	Currency currency = Currency.getInstance(Locale.UK);

        //I will not consider the Currency a relevant information
        Path pathToFile = Paths.get(CSV_PATH);
        try(BufferedReader br = Files.newBufferedReader(pathToFile)){

            // read the first line from the text file
            String line = br.readLine();
            
			while((line = br.readLine()) != null) {
				 String [] attributes  = line.split(",");

	             Product p = new Product(
	            		 	attributes[0],
	            		 	attributes[1], 
	            		 	new BigDecimal(attributes[2].replace(currency.getSymbol(),"").trim())
	            		 );
	             logger.debug(p.toString());
	             products.put(p.getId(),p);
			}

        } catch (Exception e) {
            logger.error("Error reading file", e);
        }

    }

    /**
     * List available products
     */
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * Display available products
     */
    public String displayProducts() {  
        return this.products.values().stream()
        		  .map(Product::toString)
        		  .collect(Collectors.joining(", "));
    }

    /**
     * Add a product to the Basket
     */
    public void addProductToBasket(String productId) {
    	if(products.containsKey(productId))
    		basket.addProduct(products.get(productId));
    	else {
    		logger.error("Prodouct Id " + productId + "is not a valid product");
    		throw new IllegalArgumentException("Invalid input");
    	}
    }

    /**
     * Get the items in the basket
     */
    public List<Product> getBasketItems(){
        return basket.getProducts();
    }

    /**
     * Remove a product from the Basket
     */
    public void removeProductFromBasket(String productId) {
    	basket.removeProduct(productId);
    }

    /**
     * Return the total value of the products in the basket
     */
    public BigDecimal getTotal() {
        return basket.getTotal();
    }

    public static void main(String args[]){
        System.out.println("YNAP Shop. This application should be exercised by using the YNAPShopTest class.");
    }

}
