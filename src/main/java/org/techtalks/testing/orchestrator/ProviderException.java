package org.techtalks.testing.orchestrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.techtalks.testing.parameterizedTest.ProviderError;

@AllArgsConstructor
@Data
public class ProviderException extends RuntimeException {
    private ProviderError error;
}
