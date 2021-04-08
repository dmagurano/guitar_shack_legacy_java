package com.guitarshack;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

public class StockMonitorTest {

    private final Alert alert = Mockito.mock(Alert.class);
    private final ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private final SalesRepository salesRepository = Mockito.mock(SalesRepository.class);

    private final StockMonitor stockMonitor = new StockMonitor(alert, productRepository, salesRepository);

    @Test
    public void shouldNotTriggerAlert_whenQuantitySoldIsLow() {
        Product product = new Product(811, 100, 2);
        SalesTotal total = new SalesTotal(0);

        Mockito.when(productRepository.getProduct(811)).thenReturn(product);
        Mockito.when(salesRepository.getSalesTotal(811)).thenReturn(total);

        stockMonitor.productSold(811, 1);

        verifyZeroInteractions(alert);
    }

    @Test
    public void shouldTriggerAlert_whenQuantitySoldIsHigh() {
        Product product = new Product(811, 100, 2);
        SalesTotal total = new SalesTotal(2000);

        Mockito.when(productRepository.getProduct(811)).thenReturn(product);
        Mockito.when(salesRepository.getSalesTotal(811)).thenReturn(total);

        stockMonitor.productSold(811, 1);

        verify(alert).send(product);
    }

}