package com.energizeglobal.bank.utils;

import com.energizeglobal.bank.exceptions.BankException;

import java.util.Objects;

public class Assert {
    public static void isTrue(Boolean predicate, String message) {
        if (!predicate) {
            throw new BankException(message);
        }
    }

    public static void isEquals(Object obj1, Object obj2, String message) {
        if (!Objects.equals(obj1, obj2)) {
            throw new BankException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BankException(message);
        }
    }
}
