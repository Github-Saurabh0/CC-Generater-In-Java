/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.wearl.ccvalidator;
import java.util.Random;

/**
 *
 * @author Saurabh
 */

public class CCValidator {

    private static final Random random = new Random();

    public static String generate(String cardType) {
        String prefix;
        int length;

        switch (cardType.toLowerCase()) {
            case "visa":
                prefix = "4";
                length = 16;
                break;
            case "mastercard":
                prefix = "5";
                length = 16;
                break;
            case "amex":
            case "americanexpress":
                prefix = "34";
                length = 15;
                break;
            case "discover":
                prefix = "6011";
                length = 16;
                break;
            default:
                throw new IllegalArgumentException("Unsupported card type: " + cardType);
        }

        StringBuilder number = new StringBuilder(prefix);
        while (number.length() < length - 1) {
            number.append(random.nextInt(10));
        }

        int checkDigit = computeCheckDigit(number.toString());
        number.append(checkDigit);
        return number.toString();
    }

    public static boolean validate(String cardNumber) {
        String digitsOnly = cardNumber.replaceAll("\\D", "");
        if (digitsOnly.isEmpty()) return false;
        return luhnCheck(digitsOnly);
    }

    private static int computeCheckDigit(String number) {
        for (int i = 0; i < 10; i++) {
            if (luhnCheck(number + i)) return i;
        }
        throw new IllegalStateException("Unable to compute check digit");
    }

    private static boolean luhnCheck(String number) {
        int sum = 0;
        boolean alternate = false;
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(number.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CCValidator <generate|validate> <cardType|cardNumber>");
            return;
        }

        String command = args[0];
        String param = args[1];

        if ("generate".equalsIgnoreCase(command)) {
            try {
                String cc = generate(param);
                System.out.println("Generated " + param + " number: " + cc);
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        } else if ("validate".equalsIgnoreCase(command)) {
            boolean valid = validate(param);
            System.out.println(param + " is " + (valid ? "valid" : "invalid"));
        } else {
            System.err.println("Unknown command: " + command);
        }
    }

 }

