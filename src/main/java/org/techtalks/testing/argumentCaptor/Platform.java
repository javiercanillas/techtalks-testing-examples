package org.techtalks.testing.argumentCaptor;

import lombok.Data;

public interface Platform {

    boolean deliver(Message message);

    enum BodyFormat {
        PLAIN,
        HTML
    }

    @Data
    class Message {
        private String to;
        private String subject;
        private String body;
        private BodyFormat bodyFormat;
    }
}
