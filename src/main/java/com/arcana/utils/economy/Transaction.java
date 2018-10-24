package com.arcana.utils.economy;

import java.util.Optional;

/**
 * Every economic transaction will produce a Transaction and each transaction will call an event. This Transaction
 * instance can be retrieved in the events.
 */
public class Transaction {

    public static TransactionBuilder builder(){return new TransactionBuilder();}

    private final TransactionResult result;
    private final TransactionType type;
    private final Account payer;
    private final Optional<Account> receiver;
    private final double amount;
    private final Currency currency;

    protected Transaction(TransactionResult result, TransactionType type, Account payer, Optional<Account> receiver, double amount, Currency currency) {
        this.result = result;
        this.type = type;
        this.payer = payer;
        this.receiver = receiver;
        this.amount = amount;
        this.currency = currency;
    }

    public Account getPayer() {
        return payer;
    }

    public Optional<Account> getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionResult getResult() {
        return result;
    }

    public TransactionType getType() {
        return type;
    }

    public enum TransactionResult{
        SUCCESS,
        FAILURE
    }

    public enum TransactionType{
        WITHDRAWAL,
        DEPOSIT,
        TRANSFER_TO_PLAYER,
        TRANSFER_TO_MARKET
    }

    /**
     * ===============================================
     */

    public static class TransactionBuilder{

        private TransactionResult result;
        private TransactionType type;
        private Account payer;
        private Optional<Account> receiver = Optional.empty();
        private double amount;
        private Currency currency;

        public TransactionBuilder currency(Currency currency){
            this.currency = currency;
            return this;
        }

        public TransactionBuilder payer(Account payer){
            this.payer = payer;
            return this;
        }

        public TransactionBuilder receiver(Account receiver){
            this.receiver = Optional.of(receiver);
            return this;
        }

        public TransactionBuilder amount(double amount){
            this.amount = amount;
            return this;
        }

        public TransactionBuilder result(TransactionResult result){
            this.result = result;
            return this;
        }

        public TransactionBuilder type(TransactionType type){
            this.type = type;
            return this;
        }

        public Transaction build(){
            return new Transaction(result, type, payer, receiver, amount, currency);
        }
    }
}
