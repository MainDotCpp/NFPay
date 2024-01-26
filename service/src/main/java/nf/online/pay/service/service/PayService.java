package nf.online.pay.service.service;

public interface PayService {

    void queryOrder();

    void createOrder();

    void pay();

    void refund();

    void callback();
}
