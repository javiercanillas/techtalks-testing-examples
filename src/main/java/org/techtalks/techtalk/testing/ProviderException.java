package org.techtalks.testing;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProviderException extends RuntimeException {
    private ProviderError error;
}
