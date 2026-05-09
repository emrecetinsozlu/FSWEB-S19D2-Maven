package com.workintech.s18d4.dto;

import org.springframework.stereotype.Component;


public record AccountResponse(
        Long id,
        String accountName,
        double moneyAmount,
        CustomerResponse customerResponse
) {

}
