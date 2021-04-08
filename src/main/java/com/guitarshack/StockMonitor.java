package com.guitarshack;

public class StockMonitor {

    private final Alert alert;
    private final ProductRepository productRepository;
    private final SalesRate recentSalesRate;

    public StockMonitor(Alert alert, ProductRepository productRepository, SalesRate recentSalesRate) {
        this.alert = alert;
        this.productRepository = productRepository;
        this.recentSalesRate = recentSalesRate;
    }

    public void productSold(int productId, int quantity) {
        Product product = productRepository.getProduct(productId);

        int salesRate = recentSalesRate.getSalesRate(productId);

        if (product.getStock() - quantity <= (int) ((double) salesRate * product.getLeadTime())) {
            alert.send(product);
        }
    }
}
