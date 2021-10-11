package com.portal.business.commons.startup;

import com.portal.business.commons.data.BusinessCloseData;
import com.portal.business.commons.data.BusinessData;
import com.portal.business.commons.data.BusinessSellData;
import com.portal.business.commons.data.OperatorData;
import com.portal.business.commons.data.PosData;
import com.portal.business.commons.data.StoreData;
import com.portal.business.commons.enumeration.BPTransactionStatus;
import com.portal.business.commons.enumeration.OperationType;
import com.portal.business.commons.exceptions.EmptyListException;
import com.portal.business.commons.exceptions.GeneralException;
import com.portal.business.commons.exceptions.NullParameterException;
import com.portal.business.commons.models.Business;
import com.portal.business.commons.models.BusinessBalanceIncoming;
import com.portal.business.commons.models.BusinessBalanceOutgoing;
import com.portal.business.commons.models.BusinessSell;
import com.portal.business.commons.models.Operator;
import com.portal.business.commons.models.Pos;
import com.portal.business.commons.models.Store;
import com.portal.business.commons.models.BPUser;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author hvarona
 */
public class Startup extends HttpServlet {

    private static final int weeks = 20;
    private static final float[] posibleAmounts = new float[]{1, 2, 5, 10, 20, 50, 100};

    private static final float businessCommissionPercentage = 2;
    private static final float DiscountRatePercentage = 1;

    @Override
    public void init() throws ServletException {
        System.out.println("BusinessPortal Checking Languages ...");

        /*UtilsData utilsdata = new UtilsData();
            for (Language lang : Languages.LANG) {
                try {

                    if (!lang.getId().equals(utilsdata.getLanguage(lang.getIso()).getId())) {

                    }
                } catch (RegisterNotFoundException | NullParameterException | GeneralException ex) {
                    System.out.println("Didn't found Language " + lang.getIso());

                }
            }*/
        createSellData();
        createRechargeTransactions(false);
        createRechargeTransactions(true);
        //createWithdrawTransaction();
        createCloses();
        System.out.println("---------- BussinessPortal Initialized successfully ----------");

    }

    private float getRandomAmount() {
        int amount = (int) (Math.random() * 500);

        return (float) amount / 100;
    }

    private float getRoundRandomAmount() {
        return posibleAmounts[(int) (posibleAmounts.length * Math.random())];
    }

    private void createSellData() {
        try {
            BusinessSellData businessSellData = new BusinessSellData();

            BusinessData businessData = new BusinessData();
            Business business = businessData.getBusinessByCode("codigo1");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < weeks; i++) {
                cal.roll(Calendar.WEEK_OF_YEAR, false);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
            if (businessSellData.getBusinessSalesNumber(business, cal.getTime(), new Date()) <= 0) {
                List<Pos> pos = new PosData().getPosList(business);
                List<Store> stores = new StoreData().getStores(business);

                while (cal.getTimeInMillis() < System.currentTimeMillis()) {
                    Date sellDate = cal.getTime();
                    float amount = getRandomAmount();
                    switch ((int) (Math.random() * 3)) {
                        case 0:
                            Pos ps = pos.get((int) (Math.random() * pos.size()));
                            addBusinessSellTransaction(business, ps.getStore(), ps, (long) (Math.random() * 2) + 1, sellDate, "AlodigaWallet", amount);
                            break;
                        case 1:
                            addBusinessSellTransaction(business, stores.get((int) (Math.random() * stores.size())), null, (long) (Math.random() * 2) + 1, sellDate, "AlodigaWallet", amount);
                            break;
                        case 2:
                        case 3:
                            addBusinessSellTransaction(business, null, null, (long) (Math.random() * 2) + 1, sellDate, "AlodigaWallet", amount);
                            break;

                    }
                    cal.setTimeInMillis(cal.getTimeInMillis() + ((int) (Math.random() * 30 * 60000)));
                    if (cal.get(Calendar.HOUR_OF_DAY) > 17) {
                        cal.roll(Calendar.DAY_OF_WEEK, true);
                        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            cal.roll(Calendar.WEEK_OF_YEAR, true);
                            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        }
                        cal.set(Calendar.HOUR_OF_DAY, 9);
                        cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createRechargeTransactions(boolean isCard) {
        try {

            BusinessData businessData = new BusinessData();
            Business business = businessData.getBusinessByCode("codigo1");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < weeks; i++) {
                cal.roll(Calendar.WEEK_OF_YEAR, false);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 9);
            cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
            long transactionAmount = 0;
            if (isCard) {
                transactionAmount = businessData.getBusinessTransactionsNumber(business, cal.getTime(), new Date(), OperationType.CARD_RECHARGE);
            } else {
                transactionAmount = businessData.getBusinessTransactionsNumber(business, cal.getTime(), new Date(), OperationType.RECHARGE);
            }
            if (transactionAmount <= 0) {
                List<Operator> operators = new OperatorData().getOperatorList(business);
                while (cal.getTimeInMillis() < System.currentTimeMillis()) {
                    long transactionId = (long) (Math.random() * 2500);
                    BPUser operator = null;
                    if (operators.size() <= 0) {
                        operator = business;
                    } else {
                        operator = operators.get((int) (Math.random() * operators.size()));
                    }
                    float totalCharge = getRoundRandomAmount();

                    Date rechargeDate = cal.getTime();
                    float amount = getRandomAmount();
                    switch ((int) (Math.random() * 2)) {
                        case 0: {
                            float businessFee = (float) (0.05 * totalCharge);
                            if (isCard) {
                                saveCardRecharge(business, operator, businessFee, totalCharge, transactionId, rechargeDate);
                            } else {
                                saveRecharge(business, operator, businessFee, totalCharge, transactionId, rechargeDate);
                            }
                            transactionId += (long) (Math.random() * 10);
                        }
                        break;
                        case 1:
                            float businessFee = (float) (0.2 * totalCharge);
                            if (isCard) {
                                saveCardRecharge(business, operator, businessFee, totalCharge, transactionId, rechargeDate);
                            } else {
                                saveRecharge(business, operator, businessFee, totalCharge, transactionId, rechargeDate);
                            }
                            transactionId += (long) (Math.random() * 10);
                            break;

                    }
                    cal.setTimeInMillis(cal.getTimeInMillis() + ((int) ((Math.random() + 5) * 30 * 60000)));
                    if (cal.get(Calendar.HOUR_OF_DAY) > 17) {
                        cal.roll(Calendar.DAY_OF_WEEK, true);
                        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            cal.roll(Calendar.WEEK_OF_YEAR, true);
                            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        }
                        cal.set(Calendar.HOUR_OF_DAY, 9);
                        cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createWithdrawTransaction() {
        try {

            BusinessData businessData = new BusinessData();
            Business business = businessData.getBusinessByCode("codigo1");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < weeks; i++) {
                cal.roll(Calendar.WEEK_OF_YEAR, false);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 10);
            cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
            if (businessData.getBusinessTransactionsNumber(business, cal.getTime(), new Date(), OperationType.WITHDRAW) <= 0) {
                List<Operator> operators = new OperatorData().getOperatorList(business);
                while (cal.getTimeInMillis() < System.currentTimeMillis()) {
                    long transactionId = (long) (Math.random() * 2500);
                    BPUser operator = null;
                    if (operators.size() <= 0) {
                        operator = business;
                    } else {
                        operator = operators.get((int) (Math.random() * operators.size()));
                    }
                    float totalCharge = getRoundRandomAmount();

                    Date rechargeDate = cal.getTime();

                    float businessFee = (float) (0.05 * totalCharge);
                    saveWithdrae(business, operator, businessFee, totalCharge, transactionId, rechargeDate);
                    transactionId += (long) (Math.random() * 10);

                    cal.setTimeInMillis(cal.getTimeInMillis() + ((int) ((Math.random() + 1) * 12 * 30 * 60000)));
                    if (cal.get(Calendar.HOUR_OF_DAY) > 16) {
                        cal.roll(Calendar.DAY_OF_WEEK, true);
                        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                            cal.roll(Calendar.WEEK_OF_YEAR, true);
                            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                        }
                        cal.set(Calendar.HOUR_OF_DAY, 10);
                        cal.set(Calendar.MINUTE, (int) (Math.random() * 29) + 30);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCloses() {
        try {

            BusinessCloseData closeData = new BusinessCloseData();
            BusinessData businessData = new BusinessData();
            Business business = businessData.getBusinessByCode("codigo1");
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < weeks; i++) {
                cal.roll(Calendar.WEEK_OF_YEAR, false);
            }
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            cal.set(Calendar.HOUR_OF_DAY, 22);
            cal.set(Calendar.MINUTE, 0);

            try {
                closeData.getBusinessCloseReport(business, cal.getTime(), new Date());
            } catch (EmptyListException e) {
                while (cal.getTimeInMillis() < System.currentTimeMillis()) {

                    Date closeDate = cal.getTime();

                    closeData.closeBusiness(business, closeDate);

                    cal.setTimeInMillis(cal.getTimeInMillis() + 86400000L);
                    if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                        cal.roll(Calendar.WEEK_OF_YEAR, true);
                        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveRecharge(Business business, BPUser operator, float businessFee, float totalCharge, long transactionId, Date dateRecharge) throws NullParameterException, GeneralException {
        int businessCommission = (int) (totalCharge * businessCommissionPercentage / 100) * 100;
        float businessCommissionFloat = businessCommission / 100;
        BusinessBalanceIncoming log = new BusinessBalanceIncoming();
        log.setBusiness(business);
        log.setUser(operator);
        log.setBusinessFee(businessFee);
        log.setBusinessCommission(businessCommissionFloat);
        log.setAmountWithoutFee(totalCharge);
        log.setTotalAmount(totalCharge - businessFee);
        log.setDateTransaction(dateRecharge);
        log.setTransactionId(transactionId);
        log.setTransactionStatus(BPTransactionStatus.COMPLETED);
        log.setType(OperationType.RECHARGE);
        BusinessData businessData = new BusinessData();
        businessData.saveIncomingBalance(log);
    }

    private void saveCardRecharge(Business business, BPUser operator, float businessFee, float totalCharge, long transactionId, Date dateRecharge) throws NullParameterException, GeneralException {
        int businessCommission = (int) (totalCharge * businessCommissionPercentage / 100) * 100;
        float businessCommissionFloat = businessCommission / 100;
        BusinessBalanceIncoming log = new BusinessBalanceIncoming();
        log.setBusiness(business);
        log.setUser(operator);
        log.setBusinessFee(businessFee);
        log.setBusinessCommission(businessCommissionFloat);
        log.setAmountWithoutFee(totalCharge);
        log.setTotalAmount(totalCharge - businessFee);
        log.setDateTransaction(dateRecharge);
        log.setTransactionId(transactionId);
        log.setTransactionStatus(BPTransactionStatus.COMPLETED);
        log.setType(OperationType.CARD_RECHARGE);
        BusinessData businessData = new BusinessData();
        businessData.saveIncomingBalance(log);
    }

    private void saveWithdrae(Business business, BPUser operator, float businessFee, float totalCharge, long transactionId, Date dateRecharge) throws NullParameterException, GeneralException {
        int businessCommission = (int) (totalCharge * businessCommissionPercentage / 100) * 100;
        float businessCommissionFloat = businessCommission / 100;
        BusinessBalanceOutgoing log = new BusinessBalanceOutgoing();
        log.setBusiness(business);
        log.setUser(operator);
        log.setBusinessFee(businessFee);
        log.setBusinessCommission(businessCommissionFloat);
        log.setAmountWithoutFee(totalCharge);
        log.setTotalAmount(totalCharge - businessFee);
        log.setDateTransaction(dateRecharge);
        log.setTransactionId(transactionId);
        log.setTransactionStatus(BPTransactionStatus.COMPLETED);
        log.setType(OperationType.WITHDRAW);
        BusinessData businessData = new BusinessData();
        businessData.saveOutgoinBalance(log);
    }

    private long addBusinessSellTransaction(Business business, Store store, Pos pos, long idWalletTransaction,
            Date date, String origin, float amount) {
        int businessCommission = (int) (amount * DiscountRatePercentage / 100) * 100;
        float businessCommissionFloat = businessCommission / 100;
        BusinessSell sell = new BusinessSell();
        sell.setBusiness(business);
        sell.setStore(store);
        sell.setPos(pos);
        sell.setAmount(amount - businessCommissionFloat);
        sell.setDateSell(date);
        sell.setAmountWithoutFee(amount);
        sell.setDiscountRate(businessCommissionFloat);
        sell.setIdWalletTransaction(idWalletTransaction);
        sell.setOrigin(origin);
        sell.setTransactionStatus(BPTransactionStatus.COMPLETED);
        BusinessSellData businessSellData = new BusinessSellData();

        try {
            businessSellData.saveBusinessSell(sell);
        } catch (NullParameterException ex) {
        } catch (GeneralException ex) {
        }
        return sell.getId();
    }

}
