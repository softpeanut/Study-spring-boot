package com.example.fcmserver.firebase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class FcmMessage {
    private final boolean validate_only;
    private final AppMessage message;

    @Builder
    @AllArgsConstructor
    @Getter
    public static class AppMessage {
        private final Notification notification;
        private final FcmData data;
        private final String token;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class Notification {
        private final String title;
        private final String body;
        private final String image;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class FcmData {
        private final String path;
    }
}
