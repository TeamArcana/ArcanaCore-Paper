package com.arcana.utils.economy;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.EconomyEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * This is an economy account that each player can have. It can hold multiple different currencies (of which should be
 * registered in the server's ArcanaEconomy instance).
 */
public class Account {

    private final UUID owner;
    private Map<Currency, Double> balance;

    public Account(UUID owner) {
        this.owner = owner;
        balance = new HashMap<>();
    }

    public void addCurrency(Currency currency, double amount){
        balance.put(currency, amount);
    }

    public boolean hasCurrency(Currency currency){
        return balance.containsKey(currency);
    }

    public double getBalance(Currency currency){
        if (balance.containsKey(currency)) {
            return balance.get(currency);
        }
        return 0;
    }

    public boolean canAfford(Currency currency, double amount){
        return hasCurrency(currency) && getBalance(currency) <= amount;
    }

    public Transaction withdraw(Currency currency, double amount, boolean suppressEvent){
        Transaction.TransactionBuilder builder = Transaction.builder();
        builder.payer(this)
                .currency(currency)
                .amount(amount)
                .type(Transaction.TransactionType.WITHDRAWAL);

        if(canAfford(currency, amount)){
            balance.put(currency, getBalance(currency) - amount);
            builder.result(Transaction.TransactionResult.SUCCESS);
        } else {
            builder.result(Transaction.TransactionResult.FAILURE);
        }

        Transaction transaction = builder.build();

        if(!suppressEvent) {
            ArcanaEvent.callEvent(new EconomyEvent.Account.Withdraw(transaction));
        }

        return transaction;
    }

    public Transaction deposit(Currency currency, double amount, boolean suppressEvent){
        Transaction.TransactionBuilder builder = Transaction.builder();
        builder.payer(this)
                .currency(currency)
                .amount(amount)
                .type(Transaction.TransactionType.DEPOSIT);

        balance.put(currency, getBalance(currency) + amount);
        builder.result(Transaction.TransactionResult.SUCCESS);

        Transaction transaction = builder.build();

        if(!suppressEvent) {
            ArcanaEvent.callEvent(new EconomyEvent.Account.Deposit(transaction));
        }

        return transaction;
    }
}
