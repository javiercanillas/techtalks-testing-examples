package org.techtalks.testing.orchestrator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

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
        doReturn(providerRequest).when(adapter).adapt(request);

        var expectedResponse = mock(Response.class);

        doReturn(providerResponse).when(restTemplate).execute(providerRequest);
        doReturn(expectedResponse).when(adapter).adapt(providerResponse);

        var response = this.orchestrator.process(request);
        assertEquals(expectedResponse, response);

        verify(adapter, times(1)).adapt(request);
        verify(restTemplate, times(1)).execute(providerRequest);
        verify(adapter, times(1)).adapt(providerResponse);
        
    }

    @Test
    void fail() {
        doReturn(providerRequest).when(adapter).adapt(request);

        var providerException = mock(ProviderException.class);
        doThrow(providerException).when(restTemplate).execute(providerRequest);

        var runtimeException = new RuntimeException(providerException);
        doReturn(runtimeException).when(adapter).adapt(providerException);

        var ex = assertThrows(RuntimeException.class,
                () -> this.orchestrator.process(request));
        assertSame(runtimeException, ex);
    }
}