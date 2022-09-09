package com.jenn.eventsinkorea.domain.email;

public enum MailOption {
    REQUESTED(0), ACCEPTED(1), REJECTED(2);

    private final int value;

    MailOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
