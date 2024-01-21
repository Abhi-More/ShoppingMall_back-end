package com.shoppingmall.demo.controllers;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.ClientTokenRequest;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.shoppingmall.demo.CheckoutRequest;
import com.braintreegateway.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/braintree")
public class BraintreeController {

    @Autowired
    private BraintreeGateway braintreeGateway;

    @GetMapping("/token")
    public ResponseEntity<String> generateClientToken() {
        try {
            ClientTokenRequest clientTokenRequest = new ClientTokenRequest();
            String clientToken = braintreeGateway.clientToken().generate(clientTokenRequest);
            return ResponseEntity.ok(clientToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating client token");
        }
    }

    @PostMapping("/payment")
    public ResponseEntity<String> processPayment(@RequestBody CheckoutRequest checkoutRequest) {
        try {
        	BigDecimal amount = checkoutRequest.getAmount();
        	TransactionRequest request = new TransactionRequest()
                    .amount(amount)
                    .paymentMethodNonce(checkoutRequest.getNonce())
                    .options()
                    .submitForSettlement(true)
                    .done();
   

            Result<Transaction> transaction = braintreeGateway.transaction().sale(request);

            if (transaction.isSuccess()) {
                // Save order or perform necessary actions
                return ResponseEntity.ok("Payment successful! Transaction ID: " + transaction.getTarget().getId());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed. Error: " + transaction.getMessage());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment");
        }
    }
}
