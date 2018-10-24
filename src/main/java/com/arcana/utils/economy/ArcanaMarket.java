package com.arcana.utils.economy;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.economy.EconomyEvent;
import com.arcana.utils.ArcanaKey;

import java.util.Optional;

/**
 * A market is a part of the economy that handles transactions. By splitting the economy
 * up into markets, this allows the economy to be either global or regionalized with ease.
 *
 * ArcanaMarkets should handle each direct economic transaction (not the ArcanaEconomy instance), unless it's only
 * an account withdrawal or deposit, which are both handled by the Account class itself.
 */
public abstract class ArcanaMarket {

    private Optional<String> marketName;
    private ArcanaEconomy economy;
    private ArcanaKey key;

    public ArcanaMarket(ArcanaEconomy economy, ArcanaKey key) {
        this(economy, key, Optional.empty());

    }

    public ArcanaMarket(ArcanaEconomy economy, ArcanaKey key, Optional<String> marketName) {
        this.marketName = marketName;
        this.key = key;
        this.economy = economy;
    }

    public Optional<String> getMarketName() {
        return marketName;
    }

    public boolean is(ArcanaKey key){
        return this.key == key;
    }

    public ArcanaEconomy getEconomy() {
        return economy;
    }

    public Transaction transferPlayerToPlayer(Account payer, Account receiver, Currency currency, double amount){
        Transaction.TransactionBuilder builder = Transaction.builder();
        builder.payer(payer)
                .receiver(receiver)
                .currency(currency)
                .amount(amount)
                .type(Transaction.TransactionType.TRANSFER_TO_PLAYER);

        if(payer.canAfford(currency, amount)){
            payer.withdraw(currency, amount, true);
            builder.result(Transaction.TransactionResult.SUCCESS);
            receiver.deposit(currency, amount, true);
        } else {
            builder.result(Transaction.TransactionResult.FAILURE);
        }

        Transaction transaction = builder.build();

        ArcanaEvent.callEvent(new EconomyEvent.Account.Transfer(transaction));

        return transaction;
    }
}
