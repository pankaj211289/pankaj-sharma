package com.bestbuy.api_auto.app;


import com.bestbuy.api_auto.api.APIUser;
import com.bestbuy.api_auto.api.StatusCode;
import com.bestbuy.api_auto.app.pojo.Product;
import com.google.gson.Gson;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class ProductManager {

    private APIUser apiUser;
    private String URL = "/products";

    public ProductManager(APIUser apiUser) {
        this.apiUser = apiUser;
        this.apiUser.setRelativeUrl(URL);
    }

    public List<Product> getAllProducts() {
        return getAllProducts(null, null);
    }

    public List<Product> getAllProducts(String limit, String skip) {
        String updateURL = "";
        if(limit != null && !limit.equals("")) {
            updateURL = URL + "?$limit=" + limit;
        }
        if(skip != null && !skip.equals("")) {
            updateURL = URL + "&$skip=" + skip;
        }

        this.apiUser.setRelativeUrl(updateURL);
         return this.apiUser
                .requestGet()
                .verify().responseCode(StatusCode.OK, "Response code while getting all products")
                .verify().responseBody("data.id", everyItem(not(empty())), "Product ID should not be null or empty")
                .verify().responseBody("total", is(not(emptyOrNullString())), "Total number of products should not be null or empty")
                .verify().responseBody("limit", is(not(emptyOrNullString())), "Limit number should not be null or empty")
                .verify().responseBody("skip", is(not(emptyOrNullString())), "Skip number should not be null or empty")
                .getResponse().getResponseBodyAsList("data");
    }

    public Product getProduct(String productID) {
        this.apiUser.setRelativeUrl(URL + "/" + productID);
        return this.apiUser
                .requestGet()
                .verify().responseCode(StatusCode.OK, "Response code while getting product with ID " + productID)
                .verify().responseBody("id", not(empty()), "Product ID should not be null or empty")
                .getResponse().getResponseBody(Product.class);
    }

    public Product createProduct(Product product) {
        this.apiUser.setRelativeUrl(URL);
        return this.apiUser
                .requestPost(new Gson().toJson(product))
                .verify().responseCode(StatusCode.CREATED, "Response code while creating product")
                .verify().responseBody("name", is(product.getName()), "Product Name should match")
                .verify().responseBody("type", is(product.getType()), "Product Type should match")
                .verify().responseBody("upc", is(product.getUpc()), "Product UPC should match")
                .verify().responseBody("description", is(product.getDescription()), "Product Description should match")
                .verify().responseBody("model", is(product.getModel()), "Product Model should match")
                .verify().responseBody("id", not(empty()), "Product ID should not be null or empty")
                .getResponse().getResponseBody(Product.class);
    }

    public void cannotCreateProduct(Product product) {
        this.apiUser.setRelativeUrl(URL);
        this.apiUser
                .requestPost(new Gson().toJson(product))
                .verify().responseCode(StatusCode.BAD_REQUEST, "Response code while creating product")
                .verify().responseBody("name", is("BadRequest"), "BadRequest should match")
                .verify().responseBody("message", is("Invalid Parameters"), "Invalid Parameters should match")
                .verify().responseBody("className", is("bad-request"), "bad-request should match");
    }

    public void deleteNotExistingProduct(String invalidProductID) {
        this.apiUser.setRelativeUrl(URL + "/" + invalidProductID);
        this.apiUser
                .requestDelete()
                .verify().responseCode(StatusCode.NOT_FOUND, "Response code while deleting non-existing product")
                .verify().responseBody("name", is("NotFound"), "BadRequest should match")
                .verify().responseBody("message", is("No record found for id '" + invalidProductID + "'"), "Invalid message should match");
    }

    public void fetchNotExistingProduct(String invalidProductID) {
        this.apiUser.setRelativeUrl(URL + "/" + invalidProductID);
        this.apiUser
                .requestGet()
                .verify().responseCode(StatusCode.NOT_FOUND, "Response code while deleting non-existing product")
                .verify().responseBody("name", is("NotFound"), "BadRequest should match")
                .verify().responseBody("message", is("No record found for id '" + invalidProductID + "'"), "Invalid message should match");
    }

    public Product updateProduct(String productID, Product product) {
        this.apiUser.setRelativeUrl(URL + "/" + productID);
        return this.apiUser
                .requestPatch(new Gson().toJson(product))
                .verify().responseCode(StatusCode.OK, "Response code while updating product")
                .verify().responseBody("name", is(product.getName()), "Product Name should match")
                .verify().responseBody("type", is(product.getType()), "Product Type should match")
                .verify().responseBody("upc", is(product.getUpc()), "Product UPC should match")
                .verify().responseBody("description", is(product.getDescription()), "Product Description should match")
                .verify().responseBody("model", is(product.getModel()), "Product Model should match")
                .verify().responseBody("id", not(empty()), "Product ID should not be null or empty")
                .getResponse().getResponseBody(Product.class);
    }

    public void deleteProduct(String productID, Product product) {
        this.apiUser.setRelativeUrl(URL + "/" + productID);
        this.apiUser
                .requestDelete()
                .verify().responseCode(StatusCode.OK, "Response code while deleting product");
    }

    public void validateProductNotExist(String productID) {
        this.apiUser.setRelativeUrl(URL + "/" + productID);
        this.apiUser
                .requestGet()
                .verify().responseCode(StatusCode.NOT_FOUND, "Response code while getting non existed product")
                .verify().responseBody("name", is("NotFound"), "Name should match")
                .verify().responseBody("className", is("not-found"), "className should match");
    }
}
