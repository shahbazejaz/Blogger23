package com.myblog1.myblogapp1.PayLoad;


public class SmsRequest {
    private String toPhoneNumber;

    public String getToPhoneNumber() {
        return toPhoneNumber;
    }

    public String getMessageBody() {
        return messageBody;
    }

    private String messageBody;

    public void setToPhoneNumber(String toPhoneNumber) {
        this.toPhoneNumber = toPhoneNumber;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
// Getters and setters
}

