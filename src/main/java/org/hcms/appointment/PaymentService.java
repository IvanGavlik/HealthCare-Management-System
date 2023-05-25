package org.hcms.appointment;

public interface PaymentService {
    PaymentService DEFAULT_INSTANCE = new PaymentServiceImpl();

    boolean pay(Payment payment);

}
