package org.techtalks.testing.argumentCaptor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    Platform platform;

    @Captor
    ArgumentCaptor<Platform.Message> messageArgumentCaptor;

    @Test
    void messageDelivered() {
        doReturn(true).when(platform).deliver(any());
        var service = new MessageService(platform);
        var to = "John";
        var subject = "Hello there!";
        var body = "When do you have time for a barbacue?";

        assertTrue(service.send(to, subject, body, false));
        verify(platform, times(1)).deliver(any());

        verifyNoMoreInteractions(platform);
    }

    @Test
    void messageDeliveredAssertInternals() {
        doReturn(true).when(platform).deliver(any());
        var service = new MessageService(platform);
        var to = "John";
        var subject = "Hello there!";
        var body = "When do you have time for a barbacue?";

        assertTrue(service.send(to, subject, body, false));

        verify(platform, times(1)).deliver(messageArgumentCaptor.capture());

        var capturedMessage = messageArgumentCaptor.getValue();
        assertEquals(to, capturedMessage.getTo());
        assertEquals(subject, capturedMessage.getSubject());
        assertEquals(body, capturedMessage.getBody());
        assertEquals(Platform.BodyFormat.PLAIN, capturedMessage.getBodyFormat());

        verifyNoMoreInteractions(platform);
    }

    @Test
    void messageDeliveredVerifyInternals() {
        var service = new MessageService(platform);
        final var to = "John";
        final var subject = "Hello there!";
        final var body = "When do you have time for a barbacue?";

        var messageMatcher = new ArgumentMatcher<Platform.Message>() {

            @Override
            public boolean matches(Platform.Message argument) {
                return to.equals(argument.getTo())
                        && subject.equals(argument.getSubject())
                        && body.equals(argument.getBody());
            }
        };
        doReturn(true).when(platform).deliver(ArgumentMatchers.argThat(messageMatcher));

        assertTrue(service.send(to, subject, body, false));

        verify(platform, times(1)).deliver(messageArgumentCaptor.capture());

        var capturedMessage = messageArgumentCaptor.getValue();
        assertEquals(Platform.BodyFormat.PLAIN, capturedMessage.getBodyFormat());

        verifyNoMoreInteractions(platform);
    }

}