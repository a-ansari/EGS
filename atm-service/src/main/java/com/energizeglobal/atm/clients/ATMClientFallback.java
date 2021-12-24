package com.energizeglobal.atm.clients;

import com.energizeglobal.atm.clients.models.AuthorizationRequest;
import com.energizeglobal.atm.clients.models.BaseResponse;
import com.energizeglobal.atm.clients.models.DepositRequest;
import com.energizeglobal.atm.clients.models.WithdrawRequest;

public class ATMClientFallback implements ATMClient {
    @Override
    public BaseResponse authorize(AuthorizationRequest request) {
        return BaseResponse.TIMEOUT;
    }

    @Override
    public BaseResponse balance(String cardNumber, Long sessionId) {
        return BaseResponse.TIMEOUT;
    }

    @Override
    public BaseResponse deposit(DepositRequest request) {
        return BaseResponse.TIMEOUT;
    }

    @Override
    public BaseResponse withdraw(WithdrawRequest request) {
        return BaseResponse.TIMEOUT;
    }
}
