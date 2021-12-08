package com.example.should_i_wash_hair_today.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class PushTimer {
    @Autowired
    MessageService messageService;

    @Scheduled(cron = "${cron.push.message}")
    public void start() {
        messageService.pushMessage(calculateWeekOfDay());
    }

    private String calculateWeekOfDay() {
        Calendar cal = Calendar.getInstance(Locale.TAIWAN);
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String today = sdf.format(date);

        StringBuilder sb = new StringBuilder();
        sb.append("今天是");
        sb.append(today);
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        switch (dayOfWeek) {
            case 1:
                sb.append("(日)\n");
                break;
            case 2:
                sb.append("(一)\n");
                break;
            case 3:
                sb.append("(二)\n");
                break;
            case 4:
                sb.append("(三)\n");
                break;
            case 5:
                sb.append("(四)\n");
                break;
            case 6:
                sb.append("(五)\n");
                break;
            case 7:
                sb.append("(六)\n");
                break;
        }
        sb.append("要洗頭喔～");

        return sb.toString();
    }
}
