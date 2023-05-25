package org.hcms.appointment;

import org.hcms.data.Repository;

public interface PaymentService {
    PaymentService DEFAULT_INSTANCE = new PaymentServiceImpl();

    boolean pay(Payment payment);

}
