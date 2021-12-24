package com.energizeglobal.atm.services;

import com.energizeglobal.atm.clients.ATMClient;
import com.energizeglobal.atm.controllers.models.requests.AuthorizationRequest;
import com.energizeglobal.atm.controllers.models.requests.BalanceRequest;
import com.energizeglobal.atm.controllers.models.requests.DepositRequest;
import com.energizeglobal.atm.controllers.models.requests.WithdrawRequest;
import com.energizeglobal.atm.controllers.models.responses.BaseResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
public class ATMService {
    private final ATMClient atmClient;

    public ATMService(ATMClient atmClient) {
        this.atmClient = atmClient;
    }

    public BaseResponse authorize(AuthorizationRequest request) {
        String hashCode = DigestUtils.sha1Hex(request.getPlainPassword());

        com.energizeglobal.atm.clients.models.AuthorizationRequest request1 = new com.energizeglobal.atm.clients.models.AuthorizationRequest();
        request1.setHashCode(hashCode);
        request1.setCardNumber(request.getCardNumber());
        request1.setAuthMethod(request.getAuthMethod());
        return BaseResponse.fromClientResponse(atmClient.authorize(request1));
    }

    public BaseResponse balance(BalanceRequest request) {
        return BaseResponse.fromClientResponse(atmClient.balance(request.getCardNumber(), request.getSessionId()));
    }

    public BaseResponse deposit(DepositRequest request) {
        com.energizeglobal.atm.clients.models.DepositRequest request1 = new com.energizeglobal.atm.clients.models.DepositRequest();
        request1.setCardNumber(request.getCardNumber());
        request1.setSessionId(request.getSessionId());
        request1.setAmount(request.getAmount());
        return BaseResponse.fromClientResponse(atmClient.deposit(request1));
    }

    public BaseResponse withdraw(WithdrawRequest request) {
        com.energizeglobal.atm.clients.models.WithdrawRequest request1 = new com.energizeglobal.atm.clients.models.WithdrawRequest();
        request1.setCardNumber(request.getCardNumber());
        request1.setSessionId(request.getSessionId());
        request1.setAmount(request.getAmount());
        return BaseResponse.fromClientResponse(atmClient.withdraw(request1));
    }
}
