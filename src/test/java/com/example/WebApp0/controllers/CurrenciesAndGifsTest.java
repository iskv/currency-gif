package com.example.WebApp0.controllers;

import com.example.WebApp0.services.CurrenciesAndGifs.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CurrenciesAndGifsTest{

    @Autowired
    private Giphy gr;

    @MockBean
    private Giphy g;
    @MockBean
    private OpenExchangeRates oer;

    @Autowired
    private final CurrenciesAndGifs cag = new CurrenciesAndGifs();

    private CurrencyResponse crToday;
    private CurrencyResponse crYesterday;

    @Value("${CurrenciesAndGifs.img_limit}")
    private int imgLimit;

    @BeforeEach
    public void init() {

        crToday = new CurrencyResponse();
        crToday.setRates(new HashMap<String, Double>());

        crYesterday = new CurrencyResponse();
        crYesterday.setRates(new HashMap<String, Double>());

        Mockito.when(oer.getHistory(LocalDate.now().toString()))
                .thenReturn(crToday);
        Mockito.when(oer.getHistory(LocalDate.now().minusDays(1).toString()))
                .thenReturn(crYesterday);

        Mockito.when(g.getRandomImg("rich"))
                .thenReturn(getRandomImageResponse("up"));
        Mockito.when(g.getRandomImg("broke"))
                .thenReturn(getRandomImageResponse("down"));
        Mockito.when(g.getSearchedImg("rich", imgLimit))
                .thenReturn(getSearchImagesResponse("up", imgLimit));
        Mockito.when(g.getSearchedImg("broke", imgLimit))
                .thenReturn(getSearchImagesResponse("down", imgLimit));

    }

    @Test
    void eurRateUp() {

        cag.setBaseCurrency("RUB");

        crToday.getRates().put("RUB", 75.);
        crToday.getRates().put("EUR", 0.81);

        crYesterday.getRates().put("RUB", 71.);
        crYesterday.getRates().put("EUR", 0.83);

        assertEquals("redirect:up", cag.foo("EUR"));

    }

    @Test
    void rubRateDown() {

        cag.setBaseCurrency("EUR");

        crToday.getRates().put("RUB", 75.);
        crToday.getRates().put("EUR", 0.81);

        crYesterday.getRates().put("RUB", 71.);
        crYesterday.getRates().put("EUR", 0.83);

        assertEquals("redirect:down", cag.foo("RUB"));

    }

    private RandomImageResponse getRandomImageResponse(String url) {
        RandomImageResponse ir = new RandomImageResponse();
        HashMap<String, Object> data = new HashMap<>();
        data.put("image_url", url);
        ir.setData(data);
        return ir;
    }

    private SearchImagesResponse getSearchImagesResponse(String url, int limit) {
        SearchImagesResponse sir = new SearchImagesResponse();
        List<Map<String, Object>> imgs = new ArrayList<>();
        for (int i = 0; i < limit; i++) {

            HashMap<String, Object> image = new HashMap<>();
            HashMap<String, Object> imageVariations = new HashMap<>();
            HashMap<String, Object> original = new HashMap<>();

            original.put("url", url);
            imageVariations.put("original", original);
            image.put("images", imageVariations);
            imgs.add(image);

        }
        sir.setData(imgs);

        return sir;
    }


}