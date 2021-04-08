package com.guitarshack;

public class StockMonitor {

    private final Alert alert;
    private final ProductRepository productRepository;
    private final SalesRepository salesRepository;

    public StockMonitor(Alert alert, ProductRepository productRepository, SalesRepository salesRepository) {
        this.alert = alert;
        this.productRepository = productRepository;
        this.salesRepository = salesRepository;
    }

    public void productSold(int productId, int quantity) {
        Product product = productRepository.getProduct(productId);
        SalesTotal total = salesRepository.getSalesTotal(productId);

        if (product.getStock() - quantity <= (int) ((double) (total.getTotal() / 30) * product.getLeadTime())) {
            alert.send(product);
        }

    }
}
