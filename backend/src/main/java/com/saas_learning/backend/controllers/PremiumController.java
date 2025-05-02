package com.saas_learning.backend.controllers;

import com.saas_learning.backend.services.UserService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/premium")
public class PremiumController {

    private final UserService userService;
    private static final int PREMIUM_PRICE_CENTS = 299; // 2.99€ en centimes

    @Autowired
    public PremiumController(UserService userService, @Value("${stripe.api.key}") String stripeApiKey) {
        this.userService = userService;
        Stripe.apiKey = stripeApiKey;
    }

    @PostMapping("/create-payment")
    public Map<String, Object> createPremiumPayment() {
        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) PREMIUM_PRICE_CENTS)
                    .setCurrency("eur")
                    .setDescription("Passage à Premium")
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            Map<String, Object> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            response.put("amount", PREMIUM_PRICE_CENTS);

            return response;
        } catch (StripeException e) {
            throw new RuntimeException("Erreur lors de la création du payment intent", e);
        }
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmPremium(@RequestBody Map<String, Object> payload) {
        String paymentIntentId = (String) payload.get("paymentIntentId");
        Long userId = Long.valueOf(payload.get("userId").toString());

        try {
            // Vérifier que le paiement est bien réussi
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);
            if ("succeeded".equals(paymentIntent.getStatus())) {
                // Mise à jour du statut premium de l'utilisateur
                userService.upgradeToPremium(userId);
                return ResponseEntity.ok("Félicitations ! Vous êtes maintenant premium.");
            } else {
                return ResponseEntity.badRequest().body("Le paiement n'a pas été complété");
            }
        } catch (StripeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de la vérification du paiement");
        }
    }
    // Dans votre contrôleur backend
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint reached");
        return ResponseEntity.ok("Test successful");
    }
}