package com.example.should_i_wash_hair_today.services;

import com.example.should_i_wash_hair_today.models.LineMessage;
import com.example.should_i_wash_hair_today.models.LinePushMessage;
import com.example.should_i_wash_hair_today.models.LineReplyMessage;
import com.example.should_i_wash_hair_today.models.LineUser;
import com.example.should_i_wash_hair_today.repository.LineUserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class MessageService {

    final String replyUrl = "https://api.line.me/v2/bot/message/reply";
    final String pushUrl = "https://api.line.me/v2/bot/message/push";

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    LineUserRepository lineUserRepository;

    public void replyMessage(String lineId, String replyToken, String fromText) {

        int length = fromText.length();
        LineUser lineUser = lineUserRepository.findByLineId(lineId);
        String toText = "";
        switch (length) {
            //設定上次洗頭日
            case 8:
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    Date date = sdf.parse(fromText);
                    lineUserRepository.updateLastWashDate(fromText, lineUser.getId());

                    sdf = new SimpleDateFormat("yyyy年MM月dd日");
                    String dateStr = sdf.format(date);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    String dayOfWeek = calculateWeekOfDay(cal.get(Calendar.DAY_OF_WEEK));
                    dateStr += dayOfWeek;
                    toText = "已設定完成: 上次洗頭是" + dateStr;
                } catch (ParseException e) {
                    //not a date
                    toText = "日期格式錯誤";
                }
                break;
            //設定洗頭頻率
            case 1:
                try {
                    int frequency = Integer.parseInt(fromText);
                    lineUserRepository.updateFrequency(frequency, lineUser.getId());
                    toText = "已設定完成: 每" + fromText + "天洗頭";
                } catch (NumberFormatException e) {
                    //reply　default
                    toText = replyDefault(lineUser);
                }
                break;
            default:
                toText = replyDefault(lineUser);
                break;
        }

        toText = formatMessage(replyToken, toText);
        RequestHelper.post(replyUrl, toText);
    }

    private String replyDefault(LineUser lineUser) {
        String dateStr = lineUser.getLastWashDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Date date = sdf.parse(dateStr);
            sdf = new SimpleDateFormat("yyyy年MM月dd日");
            dateStr = sdf.format(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            String dayOfWeek = calculateWeekOfDay(cal.get(Calendar.DAY_OF_WEEK));
            dateStr += dayOfWeek;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String toText = "上次洗頭是" + dateStr + "~\n\n" +
                "[設定]上次洗頭日期,2021年1月5號請輸入 20210105\n" +
                "[設定]洗頭頻率,每2天洗一次頭請輸入 2";

        return toText;

    }

    private String formatMessage(String replyToken, String toText) {
        String payload = "";

        try {
            LineMessage lineMessage = new LineMessage();
            lineMessage.setType("text");
            lineMessage.setText(toText);

            List<LineMessage> msgs = new ArrayList<>();
            msgs.add(lineMessage);

            LineReplyMessage lineReplyMessage = new LineReplyMessage();
            lineReplyMessage.setReplyToken(replyToken);
            lineReplyMessage.setMessages(msgs);

            payload = objectMapper.writeValueAsString(lineReplyMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }

    public void pushMessage(String toText) {
        try {
            List<LineUser> lineUsers = lineUserRepository.getByFollowTrue();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance(Locale.TAIWAN);
            Date today = cal.getTime();
            for (LineUser lineUser : lineUsers){
                String lastWashDateStr = lineUser.getLastWashDate();
                Date lastWashDate = sdf.parse(lastWashDateStr);
                int frequency = lineUser.getFrequency();

                long diffInMillies = Math.abs(today.getTime() - lastWashDate.getTime());
                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if(diff == frequency){
                    //push
                    LineMessage lineMessage = new LineMessage();
                    lineMessage.setType("text");
                    lineMessage.setText(toText);

                    List<LineMessage> msgs = new ArrayList<>();
                    msgs.add(lineMessage);

                    LinePushMessage linePushMessage = new LinePushMessage();
                    linePushMessage.setTo(lineUser.getLineId());
                    linePushMessage.setMessages(msgs);

                    String payload = objectMapper.writeValueAsString(linePushMessage);
                    RequestHelper.post(pushUrl, payload);

                    //update last wash date
                    lineUserRepository.updateLastWashDate(sdf.format(today), lineUser.getId());
                }
            }
        } catch (JsonProcessingException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void follow(String lineId) {
        LineUser lineUser = lineUserRepository.findByLineId(lineId);
        if (lineUser == null) {
            lineUser = new LineUser();
            lineUser.setLineId(lineId);
            lineUser.setFollow(true);
            lineUser.setFrequency(2);
            lineUser.setLastWashDate("20210101");
            lineUserRepository.save(lineUser);
        } else {
            lineUserRepository.updateFollow(true, lineUser.getId());
        }
    }

    public void unFollow(String lineId) {
        LineUser lineUser = lineUserRepository.findByLineId(lineId);
        if (lineUser != null) {
            lineUserRepository.updateFollow(false, lineUser.getId());
        }
    }

    private String calculateWeekOfDay(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return ("(日)");
            case 2:
                return "(一)";
            case 3:
                return "(二)";
            case 4:
                return "(三)";
            case 5:
                return "(四)";
            case 6:
                return "(五)";
            case 7:
                return "(六)";
            default:
                return "impossible week of day";
        }
    }

}
