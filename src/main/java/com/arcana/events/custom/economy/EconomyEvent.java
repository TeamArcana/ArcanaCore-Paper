package com.arcana.events.custom.economy;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.utils.economy.Transaction;

public abstract class EconomyEvent extends ArcanaEvent {

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
     * ==========================================
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
     * ==========================================
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
