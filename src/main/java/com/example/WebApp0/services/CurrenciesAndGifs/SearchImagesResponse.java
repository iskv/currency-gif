package com.example.WebApp0.services.CurrenciesAndGifs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SearchImagesResponse {

    private List<Map<String, Object>> data;
    private Map<String, Object> pagination;
    private Map<String, Object> meta;

}
