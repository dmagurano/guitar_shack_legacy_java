package com.guitarshack;

public interface SalesRepository {
    SalesTotal getSalesTotal(int productId, int previousDays);
}
