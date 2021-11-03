package org.techtalks.testing;

public class ErrorMapping {

    public enum Reason {
        ERROR01,
        ERROR02,
        ERROR03,
        GENERAL_FAILURE
    }

    public Reason mapError(final ProviderError error) {
        switch (error.getCode()) {
            case 1:
            case 10:
                return Reason.ERROR01;
            case 2:
            case 20:
                return Reason.ERROR02;
            case 3:
                return Reason.ERROR03;
            default:
                return Reason.GENERAL_FAILURE;
        }
    }
}
