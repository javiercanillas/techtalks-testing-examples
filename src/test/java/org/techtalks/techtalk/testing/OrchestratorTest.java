package org.techtalks.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrchestratorTest {
    Orchestrator orchestrator;

    @Mock
    Adapter adapter;
    @Mock
    RestTemplate restTemplate;
    @Mock
    Request request;
    @Mock
    ProviderRequest providerRequest;
    @Mock
    ProviderResponse providerResponse;

    @BeforeEach
    void setup() {
        this.orchestrator = new Orchestrator(adapter, restTemplate);
    }

    @Test
    void ok() {
        Mockito.doReturn(providerRequest).when(adapter).adapt(request);

        var expectedResponse = Mockito.mock(Response.class);

        Mockito.doReturn(providerResponse).when(restTemplate).execute(providerRequest);
        Mockito.doReturn(expectedResponse).when(adapter).adapt(providerResponse);

        var response = this.orchestrator.process(request);
        Assertions.assertEquals(expectedResponse, response);

        Mockito.verify(adapter, Mockito.times(1)).adapt(request);
        Mockito.verify(restTemplate, Mockito.times(1)).execute(providerRequest);
        Mockito.verify(adapter, Mockito.times(1)).adapt(providerResponse);
    }

    @Test
    void fail() {
        Mockito.doReturn(providerRequest).when(adapter).adapt(request);

        var providerException = Mockito.mock(ProviderException.class);
        Mockito.doThrow(providerException).when(restTemplate).execute(providerRequest);

        var runtimeException = new RuntimeException(providerException);
        Mockito.doReturn(runtimeException).when(adapter).adapt(providerException);

        var ex = Assertions.assertThrows(RuntimeException.class,
                () -> this.orchestrator.process(request));
        Assertions.assertSame(runtimeException, ex);
    }
}