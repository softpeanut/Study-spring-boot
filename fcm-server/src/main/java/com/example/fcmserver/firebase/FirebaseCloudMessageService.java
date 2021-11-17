package com.example.fcmserver.firebase;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class FirebaseCloudMessageService {
    private final String API_URL = "https://fcm.googleapis.com/v1/practice/messages:send";

    private final ObjectMapper objectMapper;

    public void sendMessageTo(String targetToken, String title, String body, String path) throws Exception {
        String message = makeMessage(targetToken, title, body, path);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response  = client.newCall(request).execute();

        log.info(response.body().toString());
    }

    private String makeMessage(String targetToken, String title, String body, String path) throws JsonProcessingException {
        FcmMessage fcmMessage = FcmMessage.builder()
                .message(FcmMessage.AppMessage.builder()
                        .token(targetToken)
                        .notification(
                                FcmMessage.Notification.builder()
                                        .title(title)
                                        .body(body)
                                        .image(null)
                                        .build()
                        )
                        .data(
                                FcmMessage.FcmData.builder()
                                        .path(path)
                                        .build()
                        )
                        .build()
                ).validate_only(false)
                .build();

        String message = objectMapper.writeValueAsString(fcmMessage);

        log.info(message);
        return message;
    }

    private String getAccessToken() throws Exception {
        String firebaseConfigPath = "firebase/practice-868da-firebase-adminsdk-n3tqj-4b039fb237.json";

        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource(firebaseConfigPath).getInputStream())
                .createScoped(List.of("https://www.googleapis.com/auth/cloud-platform"));

        // accessToken 생성
        googleCredentials.refreshIfExpired();

        // GoogleCredential의 getAccessToken으로 토큰 받아온 뒤, getTokenValue로 최종적으로 받음
        // REST API로 FCM에 push 요청 보낼 때 Header에 설정하여 인증을 위해 사용
        return googleCredentials.getAccessToken().getTokenValue();
    }
}
