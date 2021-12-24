package com.energizeglobal.atm.clients;

import com.energizeglobal.atm.clients.models.AuthorizationRequest;
import com.energizeglobal.atm.clients.models.BaseResponse;
import com.energizeglobal.atm.clients.models.DepositRequest;
import com.energizeglobal.atm.clients.models.WithdrawRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "atmClient", url = "http://localhost:8082/api/v1/atm",
    fallback = ATMClientFallback.class)
public interface ATMClient {
    @PostMapping("/authorize")
    BaseResponse authorize(@RequestBody AuthorizationRequest request);

    @GetMapping("/balance")
    BaseResponse balance(@RequestParam String cardNumber, @RequestParam Long sessionId);

    @PostMapping("/deposit")
    BaseResponse deposit(@RequestBody DepositRequest request);

    @PostMapping("/withdraw")
    BaseResponse withdraw(@RequestBody WithdrawRequest request);

}
