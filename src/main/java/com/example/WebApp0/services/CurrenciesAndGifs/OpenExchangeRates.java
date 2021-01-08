package com.example.WebApp0.services.CurrenciesAndGifs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "OpenExchangeRates", url = "${CurrenciesAndGifs.OpenExchangeRates_api_url}?app_id=${CurrenciesAndGifs.OpenExchangeRates_app_id}")
public interface OpenExchangeRates {

    @RequestMapping(method = RequestMethod.GET, value = "/historical/{date}.json")
    CurrencyResponse getHistory(@PathVariable("date") String date);

}
