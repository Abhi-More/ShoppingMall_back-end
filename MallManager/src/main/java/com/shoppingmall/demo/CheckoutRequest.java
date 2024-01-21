package com.shoppingmall.demo;

import java.math.BigDecimal;

public class CheckoutRequest {

    private String nonce; // Payment nonce obtained from Braintree Drop-in UI
    private BigDecimal amount;

    // Constructors, getters, and setters

    public CheckoutRequest() {
        // Default constructor for Jackson
    }

    public CheckoutRequest(String nonce, BigDecimal amount) {
        this.nonce = nonce;
        this.amount = amount;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}