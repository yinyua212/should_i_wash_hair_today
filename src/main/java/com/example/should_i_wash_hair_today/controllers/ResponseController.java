package com.example.should_i_wash_hair_today.controllers;

import com.example.should_i_wash_hair_today.models.LineRequestBody;
import com.example.should_i_wash_hair_today.services.MessageService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/")
public class ResponseController {

    @Value("${channel.secret.token}")
    private String channelSecretToken;

    @Autowired
    MessageService messageService;

    ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/wash")
    public void wash(@RequestHeader Map<String, String> headers, @RequestBody String body) throws Exception {

        verify(body, headers.get("x-line-signature"));

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        LineRequestBody lineRequestBody = objectMapper.readValue(body, LineRequestBody.class);

        String type = lineRequestBody.getEvents().get(0).getType();
        String lineId = lineRequestBody.getEvents().get(0).getSource().getUserId();

        switch (type) {
            case "follow":
                messageService.follow(lineId);
                //select exist
                //insert or update follow
                break;
            case "unfollow":
                //set follow false
                messageService.unFollow(lineId);
                break;

//            case "message":
//                //determine text length and format
//                // update frequency & last_wash_date
//                String replyToken = lineRequestBody.getEvents().get(0).getReplyToken();
//                String fromText = lineRequestBody.getEvents().get(0).getMessage().getText();
//                messageService.replyMessage(lineId, replyToken, fromText);
//                break;

            default:
                //determine text length and format
                // update frequency & last_wash_date
                String replyToken = lineRequestBody.getEvents().get(0).getReplyToken();
                String fromText = lineRequestBody.getEvents().get(0).getMessage().getText();
                messageService.replyMessage(lineId, replyToken, fromText);
                break;
        }
    }

    @GetMapping("/wakeup")
    public String wakeUp() {
        Date now = Calendar.getInstance(Locale.TAIWAN).getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(now);
    }


    private void verify(String body, String xLineSignature) throws Exception {

        SecretKeySpec key = new SecretKeySpec(channelSecretToken.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(key);
        byte[] source = body.getBytes("UTF-8");
        String signature = Base64.encodeBase64String(mac.doFinal(source));

        if (xLineSignature.isEmpty() || !signature.equals(xLineSignature)) {
            throw new Exception("hell no hackers");
        }
    }
}
