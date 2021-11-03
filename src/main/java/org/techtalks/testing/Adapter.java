package org.techtalks.testing;

public class Adapter {

    public Response adapt(final ProviderResponse response) {
        return new Response();
    }

    public ProviderRequest adapt(final Request model) {
        return new ProviderRequest();
    }

    public RuntimeException adapt(final ProviderException ex) {
        return new RuntimeException(ex);
    }
}
