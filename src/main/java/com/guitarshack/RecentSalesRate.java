package com.guitarshack;

public class RecentSalesRate implements SalesRate {

    private SalesRepository salesRepository;

    public RecentSalesRate(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public int getSalesRate(int productId) {
        SalesTotal total = salesRepository.getSalesTotal(productId, 30);
        int salesRate = total.getTotal() / 30;
        return salesRate;
    }
}
