package com.portal.business.commons.utils;

public class EjbConstants {

    public static String SERVER = "payment01.vpn.alodiga.com";
    public static String PORT = "3700";
    public static boolean AUTHORIZE_NET_TEST_ACCOUNT = false;
    public static boolean AUTHORIZE_NET_TEST_MODE = true;  //true (desarrollo)
    public static boolean AUTHORIZE_NET_USE_SSL_CONNECTION = true;
    public static boolean DEVELOPMENT_BILLING = false;
    public static final String ACCESS_CONTROL_EJB = "ejb/payment/AccessControlEJB";
    public static final String AUDITORY_EJB = "ejb/payment/AuditoryEJB";
    public static final String CUSTOMER_EJB = "ejb/payment/CustomerEJB";
    public static final String COMMISSION_TIMER_EJB = "ejb/payment/CommissionTimerEJB";
    public static final String CLOSURE_PROCESS_TIMER_EJB = "ejb/payment/ClosureProcessTimerEJB";
    public static final String PROMOTION_TIMER_EJB = "ejb/payment/PromotionTimerEJB";
    public static final String NOTIFICATION_EJB = "ejb/payment/NotificationEJB";
    public static final String BANNER_EJB = "ejb/payment/BannerEJB";
    public static final String PREFERENCES_EJB = "ejb/payment/PreferencesEJB";
    public static final String PRODUCT_EJB = "ejb/payment/ProductEJB";
    public static final String PROMOTION_EJB = "ejb/payment/PromotionEJB";
    public static final String REPORT_EJB = "ejb/payment/ReportEJB";
    public static final String SMS_INTEGRATION_EJB = "ejb/payment/SMSIntegrationEJB";
    public static final String SOLICITUDE_EJB = "ejb/payment/SolicitudeEJB";
    public static final String TRANSACTION_EJB = "ejb/payment/TransactionEJB";
    public static final String TROUBLE_TICKET_EJB = "ejb/payment/TroubleTicketEJB";
    public static final String STORE_CLOSURE_TIMER_EJB = "ejb/services/provider/BillingTimerEJB";
    public static final String USER_EJB = "ejb/payment/UserEJB";
    public static final String UTILS_EJB = "ejb/payment/UtilsEJB";
    public static final String TOP_UP_EJB = "ejb/payment/TopUpProductEJB";
    public static final String WS_EJB = "ejb/payment/WSEJB";
    public static final String TOP_UP_TIMER_EJB = "ejb/payment/TopUpTimerEJB";
    public static final String PIN_EJB = "ejb/payment/PinEJB";
    public static final String BILLPAYMENT_EJB = "ejb/payment/BillPaymentEJB";
    public static final String KDDIINTEGRATION_EJB = "ejb/kddi/IntegrationTopEJB";
    public static final String MLAT_TOP_UP_EJB = "ejb/MLATIntegration/TopUpEJB";
    public static final String MLAT_PREFERENCE_EJB= "ejb/MLATIntegration/PreferenceEJB";
    public static final String MPI_TOP_UP_EJB = "ejb/MobilePinInventoryIntegration/TopUpEJB";
    public static final String MPI_PREFERENCE_EJB= "ejb/MobilePinInventoryIntegration/DenominationEJB";
    public static final String MPI_CARD_EJB= "ejb/MobilePinInventoryIntegration/CardEJB";
    public static final String MPI_ACCESS_CONTROL_EJB= "ejb/MobilePinInventoryIntegration/AccessControlEJB";

    //CAMBIAR
    public static final String PROPERTIES_PACKAGE = "com.remettence.commons.utils.";
    public static final String ERROR_FILE_NAME = PROPERTIES_PACKAGE + "system_error";
    public static final String MESSAGE_FILE_NAME = PROPERTIES_PACKAGE + "system_message";
    //ERROR
    public static final String ERR_GENERAL_EXCEPTION = "E000";
    public static final String ERR_NULL_PARAMETER = "E001";
    public static final String ERR_INCORRECT_PARAMETER = "E002";
    public static final String ERR_EMPTY_LIST_EXCEPTION = "E003";
    public static final String ERR_REGISTER_NOT_FOUND_EXCEPTION = "E004";
    public static final String ERR_INVALID_FORTMAT_EXCEPTION = "E005";
    public static final String ERR_INVALID_REFERENCE_EXCEPTION = "E006";
    public static final String ERR_PERSISTENCE_LAYER = "E020";
    public static final String ERR_CRUD_ENTITY = "E021";
    public static final String MSG_INIT_INVOCATION_METHOD = "M001";
    public static final String ERR_MAX_AMOUNT_BALANCE = "E007";
    public static final String ERR_MIN_AMOUNT_BALANCE = "E008";
    public static final String ERR_NOT_PIN_PROVISION = "E030";
    public static final String ERR_NOT_PIN_PROVISION_ANI = "E031";
    public static final String ERR_ERROR_RECHARGE = "E032";
    public static final String ERR_NOT_PROCESS_RECHARGE = "E033";
    public static final String ERR_PURCHASE_DENIED = "E034";
    public static final String ERR_REMITTENCE_NOT_AVAILABLE = "E035";
    public static final String ERR_MAX_AMOUNT_PER_REMITTANCE = "E036";
    public static final String ERR_MAX_AMOUNT_DAILY = "E037";
    public static final String ERR_MAX_AMOUNT_MOUNTHLY = "E038";
    public static final String ERR_MAX_AMOUNT_YEARLY = "E039";
    public static final String ERR_CANNOT_REVERSE_BALANCE_ALOPOINTS = "E040";
    public static final String ERR_NORESULTEXCEPTION = "E041";
    public static final String ERR_PIN_DISABLED_EXCEPTION = "E042";
    public static final String ERR_PIN_FREE_NOT_FOUND_EXCEPTION = "E043";
    public static final String ERR_CREDIT_LIMIT_EXCEDED = "E044";
    public static final int ENTERPRISE_ID_USA = 1;
    // Correos
    public static String SMTP_SERVER = "smtp1.vpn.alodiga.com";
    public static String DEV_REPORT = "dev-reports@interaxmedia.com";
    //  Host
    public static final String PRODUCTION_HOST = "http://64.211.208.218/";//APP1
    public static final String TEST_PHONE_NUMBER = "584245398150";


    public static final String ERR_WS_SUCCESSFUL_OPERATION = "0";
    public static final String ERR_WS_AUTHENTICATION_FAILED = "1";
    public static final String ERR_WS_MISSING_PARAMETERS = "2";
    public static final String ERR_WS_DISABLED_ACCOUNT = "3";
    public static final String ERR_WS_INVALID_PHONE_NUMBER = "4";
    public static final String ERR_WS_PIN_ALREADY_EXIST = "5";
    public static final String ERR_WS_INVALID_AMOUNT = "6";
    public static final String ERR_WS_BILLPAYMENTCODE_NOT_FOUND = "10";
    public static final String ERR_WS_CARRIER_SYSTEM_UN_AVAILABLE_EXCEPTION = "13";
    public static final String ERR_WS_TRANSACTION_COULD_BE_DUPLICATED = "18";
    public static final String ERR_WS_INVALID_CREDIT_CARD_NUMBER = "23";
    public static final String ERR_WS_INVALID_PAYMENT_INFO = "24";
    public static final String ERR_WS_INVALID_CREDIT_CARD_DATE = "25";
    public static final String ERR_WS_RECORDS_NOT_FOUND = "28";
    public static final String ERR_WS_DISABLED_PIN = "29";
    public static final String ERR_WS_TOPUP_TRANSACTION_EXCEPTION = "30";
    public static final String ERR_WS_TOPUP_PRODUCT_NOT_AVAILABLE_EXCEPTION = "31";
    public static final String ERR_WS_INVALID_FORMAT_EXCEPTION = "32";
    public static final String ERR_WS_NOT_AVAILABLE_SERVICE_EXCEPTION = "33";
    public static final String ERR_WS_DESTINATION_NOT_PREPAID_EXCEPTION = "34";
    public static final String ERR_WS_AMOUNT_CONSUMED = "35";
    public static final String ERR_WS_PAYMENT_DECLINED_EXCEPCION = "36";
    public static final String ERR_WS_DUPLICATED_EXTERNAL_ID_EXCEPCION = "37";
    public static final String ERR_WS_PIN_FREE_NOT_FOUND_EXCEPCION = "38";
    public static final String ERR_WS_GENERAL_ERROR = "999";
}
