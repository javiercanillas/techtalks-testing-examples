package org.techtalks.testing;

public class Orchestrator {

    private final Adapter adapter;
    private final RestTemplate restTemplate;

    public Orchestrator(final Adapter adapter,
                        final RestTemplate restTemplate) {
        this.adapter = adapter;
        this.restTemplate = restTemplate;
    }

    public Response process(final Request request) {
        var req = this.adapter.adapt(request);
        try {
            var res = this.restTemplate
                    .execute(req);
            return this.adapter.adapt(res);
        } catch (ProviderException e) {
            throw this.adapter.adapt(e);
        }
    }
}
