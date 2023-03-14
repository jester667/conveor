package ru.neoflex.conveor.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class CalculateServiceTest {

    private final CalculatorService calculatorService = new CalculatorServiceImpl();
    private final static BigDecimal TOTAL_AMOUNT_REQUEST = new BigDecimal("15000.00");
    private final static BigDecimal TOTAL_AMOUNT_NOT_INSURANCE = new BigDecimal("15000.00");
    private final static BigDecimal TOTAL_AMOUNT_IS_INSURANCE = new BigDecimal("30000.00");
    private final static BigDecimal RATE_IS_INSURANCE_IS_SALARY_CLIENT = new BigDecimal("8.00");
    private final static BigDecimal RATE_IS_INSURANCE_NOT_SALARY_CLIENT = new BigDecimal("10.00");
    private final static BigDecimal RATE_NOT_INSURANCE_IS_SALARY_CLIENT = new BigDecimal("11.00");
    private final static BigDecimal RATE_NOT_INSURANCE_NOT_SALARY_CLIENT = new BigDecimal("13.00");
    private final static Integer TERM = 12;
    private final static BigDecimal MONTHLY_PAYMENT = new BigDecimal("2609.48");

    @Test
    public void calculateTotalAmount() {
        Assertions.assertThat(calculatorService.calculateTotalAmount(TOTAL_AMOUNT_REQUEST, false))
                .isEqualTo(TOTAL_AMOUNT_NOT_INSURANCE);
        Assertions.assertThat(calculatorService.calculateTotalAmount(TOTAL_AMOUNT_REQUEST, true))
                .isEqualTo(TOTAL_AMOUNT_IS_INSURANCE);
    }

    @Test
    public void calculateRate() {
        Assertions.assertThat(calculatorService.calculateRate(true, true))
                .isEqualTo(RATE_IS_INSURANCE_IS_SALARY_CLIENT);
        Assertions.assertThat(calculatorService.calculateRate(true, false))
                .isEqualTo(RATE_IS_INSURANCE_NOT_SALARY_CLIENT);
        Assertions.assertThat(calculatorService.calculateRate(false, true))
                .isEqualTo(RATE_NOT_INSURANCE_IS_SALARY_CLIENT);
        Assertions.assertThat(calculatorService.calculateRate(false, false))
                .isEqualTo(RATE_NOT_INSURANCE_NOT_SALARY_CLIENT);
    }

    @Test
    public void calculateMonthlyPayment() {
        Assertions.assertThat(calculatorService.calculateMonthlyPayment(TOTAL_AMOUNT_IS_INSURANCE, TERM, RATE_IS_INSURANCE_IS_SALARY_CLIENT))
                .isEqualTo(MONTHLY_PAYMENT);
    }
}
