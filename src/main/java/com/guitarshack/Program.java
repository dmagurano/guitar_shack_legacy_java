package com.guitarshack;

public class Program {

    private static StockMonitor monitor = new StockMonitor(product -> {
        // We are faking this for now
        System.out.println(
                "You need to reorder product " + product.getId() +
                        ". Only " + product.getStock() + " remaining in stock");
    },
            new HttpProductRepository(),
            new HttpSalesRepository()
    );

    public static void main(String[] args) {
        int productId = 811;
        int quantity = 99;

        monitor.productSold(productId, quantity);
    }
}
