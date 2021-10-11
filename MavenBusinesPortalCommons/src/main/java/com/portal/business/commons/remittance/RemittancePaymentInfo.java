package com.portal.business.commons.remittance;

/**
 *
 * @author henry
 */
public class RemittancePaymentInfo {

    private boolean includeFee;
    private float amountOrigin;
    private float amountDestiny;
    private float totalAmount;
    private String exchangeRateId;
    private String ratePaymentNetworkId = "1";
    private RemittanceDeliveryForm deliveryForm;
    private PaymentNetwork paymentNetwork;

    public RemittancePaymentInfo() {
    }

    public float getAmountOrigin() {
        return amountOrigin;
    }

    public void setAmountOrigin(float amountOrigin) {
        this.amountOrigin = amountOrigin;
    }

    public float getAmountDestiny() {
        return amountDestiny;
    }

    public void setAmountDestiny(float amountDestiny) {
        this.amountDestiny = amountDestiny;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getExchangeRateId() {
        return exchangeRateId;
    }

    public void setExchangeRateId(String exchangeRateId) {
        this.exchangeRateId = exchangeRateId;
    }

    public String getRatePaymentNetworkId() {
        return ratePaymentNetworkId;
    }

    public void setRatePaymentNetworkId(String ratePaymentNetworkId) {
        this.ratePaymentNetworkId = ratePaymentNetworkId;
    }

    public RemittanceDeliveryForm getDeliveryForm() {
        return deliveryForm;
    }

    public void setDeliveryForm(RemittanceDeliveryForm deliveryForm) {
        this.deliveryForm = deliveryForm;
    }

    public PaymentNetwork getPaymentNetwork() {
        return paymentNetwork;
    }

    public void setPaymentNetwork(PaymentNetwork paymentNetwork) {
        this.paymentNetwork = paymentNetwork;
    }

    public boolean isIncludeFee() {
        return includeFee;
    }

    public void setIncludeFee(boolean includeFee) {
        this.includeFee = includeFee;
    }

}
