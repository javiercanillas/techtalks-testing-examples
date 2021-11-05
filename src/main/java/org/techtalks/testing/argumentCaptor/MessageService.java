package org.techtalks.testing.argumentCaptor;

public class MessageService {

    private final Platform platform;

    public MessageService(Platform aPlatform) {
        this.platform = aPlatform;
    }

    public boolean send(String to, String subject, String body, boolean isHtml) {
        var message = new Platform.Message();
        message.setTo(to);
        message.setSubject(subject);
        message.setBody(body);
        if (isHtml) {
            message.setBodyFormat(Platform.BodyFormat.HTML);
        } else {
            message.setBodyFormat(Platform.BodyFormat.PLAIN);
        }
        return platform.deliver(message);
    }
}
