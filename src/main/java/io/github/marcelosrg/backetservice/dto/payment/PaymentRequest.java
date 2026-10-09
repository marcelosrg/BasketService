package io.github.marcelosrg.backetservice.dto.payment;

import io.github.marcelosrg.backetservice.entity.PaymentMethod;


public record PaymentRequest (PaymentMethod paymentMethod){}
