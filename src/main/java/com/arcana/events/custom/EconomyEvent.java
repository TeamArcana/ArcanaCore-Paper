package com.arcana.events.custom;

import com.arcana.utils.economy.Transaction;

/**
 * These are all the custom events used within the ArcanaEconomy system.
 */
public abstract class EconomyEvent extends ArcanaEvent {

    /**
     * This is the resulting Transaction that's returned from some sort of economic function.
     */
    private Transaction transaction;

    public EconomyEvent(Transaction transaction) {
        this.transaction = transaction;
    }

    public EconomyEvent(String cause, Transaction transaction) {
        super(cause);
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    /**
     * These are the economic events that have to do with player Accounts.
     */
    public abstract static class Account extends EconomyEvent{

        public Account(Transaction transaction) {
            super(transaction);
        }

        public Account(String cause, Transaction transaction) {
            super(cause, transaction);
        }

        public static class Deposit extends Account {

            public Deposit(Transaction transaction) {
                super(transaction);
            }

            public Deposit(String cause, Transaction transaction) {
                super(cause, transaction);
            }
        }

        public static class Withdraw extends Account {

            public Withdraw(Transaction transaction) {
                super(transaction);
            }

            public Withdraw(String cause, Transaction transaction) {
                super(cause, transaction);
            }
        }

        public static class Transfer extends Account{

            public Transfer(Transaction transaction) {
                super(transaction);
            }

            public Transfer(String cause, Transaction transaction) {
                super(cause, transaction);
            }
        }
    }

    /**
     * These are the economic events having to do with ArcanaMarket functions.
     */

    public abstract static class Market extends EconomyEvent{

        public Market(Transaction transaction) {
            super(transaction);
        }

        public Market(String cause, Transaction transaction) {
            super(cause, transaction);
        }
    }

}
