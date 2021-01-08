package com.example.WebApp0.services.CurrenciesAndGifs;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyResponse {

    private String disclaimer;
    private String license;
    private Long timestamp;
    private String base;
    private Map<String, Double> rates;

}
