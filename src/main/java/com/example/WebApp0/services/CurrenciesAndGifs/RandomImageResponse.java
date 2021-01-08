package com.example.WebApp0.services.CurrenciesAndGifs;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RandomImageResponse {

    private Map<String, Object> data;
    private Map<String, Object> meta;

}
