package com.example.WebApp0.services.CurrenciesAndGifs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "Giphy", url = "${CurrenciesAndGifs.giphy_api_url}?api_key=${CurrenciesAndGifs.giphy_api_key}")
public interface Giphy {

    @RequestMapping(method = RequestMethod.GET, value = "/random?tag={tag}")
    RandomImageResponse getRandomImg(@PathVariable String tag);

    @RequestMapping(method = RequestMethod.GET, value = "/search?q={query}&limit={limit}")
    SearchImagesResponse getSearchedImg(@PathVariable String query, @PathVariable int limit);

}
