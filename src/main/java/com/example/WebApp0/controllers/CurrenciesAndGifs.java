package com.example.WebApp0.controllers;

import com.example.WebApp0.services.CurrenciesAndGifs.Giphy;
import com.example.WebApp0.services.CurrenciesAndGifs.OpenExchangeRates;
import com.example.WebApp0.services.CurrenciesAndGifs.CurrencyResponse;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class CurrenciesAndGifs {

    @Autowired
    private OpenExchangeRates oer;

    @Autowired
    private Giphy g;

    @Setter
    @Value("${CurrenciesAndGifs.base_currency}")
    private String baseCurrency;
    @Value("${CurrenciesAndGifs.rate_up_img_tag}")
    private String rateUpImgTag;
    @Value("${CurrenciesAndGifs.rate_down_img_tag}")
    private String rateDownImgTag;
    @Value("${CurrenciesAndGifs.img_limit}")
    private int imgLimit;
    @Value("${CurrenciesAndGifs.giphy_api}")
    private int giphyAPI;

    @GetMapping("/{currency}")
    public String foo(@PathVariable("currency") String currency) {

        CurrencyResponse todayCurrencyResponse = oer.getHistory(LocalDate.now().toString());
        CurrencyResponse yesterdayCurrencyResponse = oer.getHistory(LocalDate.now().minusDays(1).toString());

        double today = todayCurrencyResponse.getRates().get(baseCurrency) / todayCurrencyResponse.getRates().get(currency.toUpperCase());
        double yesterday = yesterdayCurrencyResponse.getRates().get(baseCurrency) / yesterdayCurrencyResponse.getRates().get(currency.toUpperCase());

        if (today > yesterday) {
            // the exchange rate rose against the base currency
            if (giphyAPI == 0) return "redirect:" + getRandomSearchedImgLink(rateUpImgTag);
            else if (giphyAPI == 1) return "redirect:" + getRandomImgLink(rateUpImgTag);
        } else {
            // the exchange rate fell against the ruble
            if (giphyAPI == 0) return "redirect:" + getRandomSearchedImgLink(rateDownImgTag);
            else if (giphyAPI == 1) return "redirect:" + getRandomImgLink(rateDownImgTag);
        }
        return "";
    }

    private String getRandomSearchedImgLink(String query) {

        List<Map<String, Object>> imgs = g.getSearchedImg(query, imgLimit).getData();

        int randNum = (int) (Math.random() * imgLimit);

        Map<String, Object> image = imgs.get(randNum);
        Map<String, Object> imageVariation = (Map<String, Object>) image.get("images");
        Map<String, String> original = (Map<String, String>) imageVariation.get("original");

        return original.get("url");

    }

    private String getRandomImgLink(String tag) {
        return (String) g.getRandomImg(tag).getData().get("image_url");
    }

}
