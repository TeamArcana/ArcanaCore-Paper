package com.arcana.utils.economy;

import com.arcana.utils.ArcanaKey;

/**
 * This is a currency for the server's ArcanaEconomy instance. The economy can have as many different
 * currencies as you'd like. Each player Account will keep track of the currencies they have and the corresponding
 * amount.
 */
public abstract class Currency {

    private final String symbol;
    private final String name;
    private final ArcanaKey key;

    public Currency(String symbol, String name, ArcanaKey key) {
        this.symbol = symbol;
        this.name = name;
        this.key = key;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public ArcanaKey getKey() {
        return key;
    }

    public boolean is(ArcanaKey key){
        return this.key == key;
    }
}
