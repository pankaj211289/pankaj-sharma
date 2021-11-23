package com.bestbuy.api_auto.stepdefs;

import com.bestbuy.api_auto.app.ProductManager;
import com.bestbuy.api_auto.app.pojo.Product;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.HashMap;
import java.util.Map;

public class ProductStepDefs extends AbstractBaseTest {

    private ProductManager productManager = new ProductManager(api());
    private Map<String, Product> productsTracker = new HashMap<>();

    @When("I validate by setting limit for number of products as {int}")
    public void getDetailsOfAllProducts(int limit) {
        assertUtil.verify(productManager.getAllProducts(String.valueOf(limit), null).size(), limit, "Default limit should be " + limit + " products");
    }

    @When("I create a product with following details")
    public void createProduct(DataTable productDetails) {
        Map<String, String> productDetailsMap = dataTableConverter(productDetails);
        Product newProduct = new Product();
        newProduct.setName(productDetailsMap.get("name") + SetUp.getUniqueNumber());
        newProduct.setType(productDetailsMap.get("type"));
        newProduct.setUpc(productDetailsMap.get("upc"));
        newProduct.setDescription(productDetailsMap.get("description"));
        newProduct.setModel(productDetailsMap.get("model"));

        Product product = productManager.createProduct(newProduct);
        productsTracker.put(product.getName(), product);
    }

    @When("I create a product with insufficient details")
    public void invalidProductCreation(DataTable productDetails) {
        Map<String, String> productDetailsMap = dataTableConverter(productDetails);
        Product newProduct = new Product();
        newProduct.setName(productDetailsMap.get("name") + SetUp.getUniqueNumber());
        newProduct.setType(productDetailsMap.get("type"));

        productManager.cannotCreateProduct(newProduct);
    }

    @Then("I validate product {string} does not exist")
    public void deleteNonExistingProduct(String productName) {
        Product orgProduct = productsTracker.get(productName + SetUp.getUniqueNumber());
        productManager.validateProductNotExist(orgProduct.getId().toString());
    }

    @Then("I try to delete invalid product with ID {string}")
    public void deleteNotExistingProduct(String productID) {
        productManager.deleteNotExistingProduct(productID);
    }

    @Then("I try to fetch invalid product with ID {string}")
    public void validateProductNotExist(String productID) {
        productManager.deleteNotExistingProduct(productID);
    }

    @When("I update product {string} with following details and validate it")
    public void updateProduct(String productName, DataTable productDetails){
        Product orgProduct = productsTracker.get(productName + SetUp.getUniqueNumber());

        Map<String, String> productDetailsMap = dataTableConverter(productDetails);
        Product newProduct = new Product();
        newProduct.setName(productDetailsMap.containsKey("name") ? productDetailsMap.get("name") + SetUp.getUniqueNumber() : orgProduct.getName());
        newProduct.setType(productDetailsMap.containsKey("type") ? productDetailsMap.get("type")  : orgProduct.getType());
        newProduct.setUpc(productDetailsMap.containsKey("upc") ? productDetailsMap.get("upc")  : orgProduct.getUpc());
        newProduct.setDescription(productDetailsMap.containsKey("description") ? productDetailsMap.get("description")  : orgProduct.getDescription());
        newProduct.setModel(productDetailsMap.containsKey("model") ? productDetailsMap.get("model")  : orgProduct.getModel());

        Product product = productManager.updateProduct(orgProduct.getId().toString(), newProduct);
        productsTracker.put(product.getName(), product);
    }

    @Then("I can validate that product {string} is created")
    public void validateProductCreated(String productName) {
        Product product = productsTracker.get(productName + SetUp.getUniqueNumber());
        productManager.getProduct(product.getId().toString());
    }

    @Then("I can delete product {string}")
    public void deleteProduct(String productName) {
        Product product = productsTracker.get(productName + SetUp.getUniqueNumber());
        productManager.deleteProduct(product.getId().toString(), product);
    }

}
