package com.guitarshack;

public class Program {

    private static StockMonitor monitor = new StockMonitor(product -> {
        // We are faking this for now
        System.out.println(
                "You need to reorder product " + product.getId() +
                        ". Only " + product.getStock() + " remaining in stock");
    }, new ProductRepository(), new SalesRepository());

    public static void main(String[] args) {
        int productId = 811;
        int quantity = 52;

        monitor.productSold(productId, quantity);
    }
}
