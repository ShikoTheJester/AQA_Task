package pages.currency_calculator;

import lombok.Getter;

@Getter
public
enum FooterCountries {
    Lithuania("Lithuania", "EUR"),
    Latvia("Latvia", "EUR"),
    Estonia("Estonia", "EUR"),
    Bulgaria("Bulgaria", "BGN"),
    Spain("Spain", "EUR"),
    Romania("Romania", "RON"),
    Poland("Poland", "PLN"),
    United("United Kingdom", "GBP"),
    Germany("Germany", "EUR"),
    Russia("Russia", "RUB"),
    Algeria("Algeria", "DZD"),
    Albania("Albania", "ALL"),
    Kosovo("Kosovo", "EUR"),
    Ukraine("Ukraine", "UAH"),
    Another("Another", "EUR");

    String country;
    String currency;

    FooterCountries(String country, String currency) {
        this.currency = currency;
        this.country = country;
    }
}
