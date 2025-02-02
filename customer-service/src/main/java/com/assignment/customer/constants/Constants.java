package com.assignment.customer.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct; // Required for Spring Boot 3+

@Component
public class Constants {

    //@Value("${app.account-management.host}")
    private String accountManagementHost;

   // @Value("${app.account-management.port}")
    private int accountManagementPort;

    public static final String SAVE_CUSTOMER = "/saveCustomer";
    public static final String CUSTOMER_URL = "/customer";
    public static final String ALL_CUSTOMERS = "/allCustomers";
    public static final String FETCH_CUSTOMER_BY_ID = "/getCustomer/{id}";
    public static final String UPDATE_CUSTOMER = "/updateCustomer/{id}";
    public static final String DELETE_CUSTOMER = "/deleteCustomer/{id}";
    public static final String FIND_BY_ID_EMAIL = "/findByIdAndEmail";
    public static final String FIND_BY_ACCOUNT_ID = "/findByAccountId/{accountId}";

    private static String ACCOUNT_SERVICE_URL;

    @PostConstruct
    public void init() {
        ACCOUNT_SERVICE_URL = "http://" + accountManagementHost + ":" + accountManagementPort + "/account";
    }

    public static String getACCOUNT_SERVICE_URL() {
        return ACCOUNT_SERVICE_URL;
    }
}
