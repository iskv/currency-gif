# CurrenciesAndGifs

Данный проект реализует веб-сервис работающий следующим образом:
1) На веб-сервис передается код валюты (пример: http://localhost/EUR или http://localhost/cny).
2) Веб-сервис запрашивает курсы валют с <a href="https://openexchangerates.org">openexchangerates.org</a> за сегодняшний и вчерашний день.
3) Веб-сервис возвращает случайную картинку тематики "rich" или "broke" с <a href="https://giphy.com">giphy.com</a>. Тематика картинки зависит от направления движения курса переданной валюты к базовой валюте (по-умолчанию RUB) относительно вчерашнего дня. Если направление движения курса положительное - возращается картинка тематики "rich", иначе "broke". 

* Параметры взаимодействия с внешними сервисами вынесены в "application.properties".
* Реализованы две версии API получения случайной картинки (версия устанавливается в "application.properties"). Первая версия использует https://api.giphy.com/v1/gifs/search с указанием колличества картинок в результате (свойство CurrenciesAndGifs.img_limit). Вторая версия использует https://api.giphy.com/v1/gifs/random.

---

This project implements a web service that works as follows:
1) The currency code is transmitted to the web service (example: http://localhost/EUR or http://localhost/cny).
2) The web service requests exchange rates from <a href="https://openexchangerates.org">openexchangerates.org</a> for today and yesterday.
3) The web service returns a random "rich" or "broke" image from <a href="https://giphy.com">giphy.com</a>. The subject matter of the picture depends on the direction of movement of the rate of the transferred currency to the base currency (by default RUB) relative to yesterday. If the direction of the rate movement is positive, the "rich" subject is returned, otherwise "broke". Two versions of the random image API are implemented (the version is set in "application. properties").

* Parameters for interaction with external services are moved to "application.properties".
* Two versions of the random image API are implemented (the version is set in "application. properties"). The first version uses https://api.giphy.com/v1/gifs/search with an indication of the number of images in the result (the currenciesandgifs.img_limit property). The second version uses https://api.giphy.com/v1/gifs/random.
