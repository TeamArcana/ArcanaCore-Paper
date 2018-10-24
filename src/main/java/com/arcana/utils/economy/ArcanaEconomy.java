package com.arcana.utils.economy;

import com.arcana.utils.ArcanaKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the economy conatiner for the entire server. This shouldn't handle any direct economic functions between
 * markets or players. If you want different "economies", use the ArcanaMarkets
 * for regionalized "economies"
 */
public class ArcanaEconomy {

    private List<Currency> currencies;
    private List<ArcanaMarket> markets;

    public ArcanaEconomy() {
        currencies = new ArrayList<>();
        markets = new CopyOnWriteArrayList<>();
    }

    public void addCurrency(Currency currency){
        if(!currencies.contains(currency)){
            currencies.add(currency);
        }
    }

    public void addMarket(ArcanaMarket market){
        markets.add(market);
    }

    public Optional<ArcanaMarket> getMarket(ArcanaKey key){
        for(ArcanaMarket market: markets){
            if(market.is(key)){
                return Optional.of(market);
            }
        }
        return Optional.empty();
    }

    public Optional<Currency> getCurrency(ArcanaKey key){
        for(Currency currency: currencies){
            if(currency.is(key)){
                return Optional.of(currency);
            }
        }
        return Optional.empty();
    }

}
