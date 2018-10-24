package com.arcana.utils.economy;

import com.arcana.utils.ArcanaKey;

public abstract class Currency {

    private String symbol;
    private String name;
    private ArcanaKey key;

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
