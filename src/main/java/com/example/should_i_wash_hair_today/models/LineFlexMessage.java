package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

@Component
public class LineFlexMessage {
    private static String flex = "{\n" +
            "  \"type\": \"bubble\",\n" +
            "  \"hero\": {\n" +
            "    \"type\": \"image\",\n" +
            "    \"url\": \"https://wowlavie-aws.hmgcdn.com/file/article_all/%E5%A4%A9%E7%AB%BA%E9%BC%A0%E8%BB%8A%E8%BB%8A003.webp\",\n" +
            "    \"size\": \"full\",\n" +
            "    \"aspectRatio\": \"20:13\",\n" +
            "    \"aspectMode\": \"cover\",\n" +
            "    \"action\": {\n" +
            "      \"type\": \"uri\",\n" +
            "      \"uri\": \"http://linecorp.com/\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"body\": {\n" +
            "    \"type\": \"box\",\n" +
            "    \"layout\": \"vertical\",\n" +
            "    \"contents\": [\n" +
            "      {\n" +
            "        \"type\": \"text\",\n" +
            "        \"text\": \"今天要洗頭喔！\",\n" +
            "        \"weight\": \"bold\",\n" +
            "        \"size\": \"xl\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"box\",\n" +
            "        \"layout\": \"vertical\",\n" +
            "        \"margin\": \"lg\",\n" +
            "        \"spacing\": \"sm\",\n" +
            "        \"contents\": []\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"footer\": {\n" +
            "    \"type\": \"box\",\n" +
            "    \"layout\": \"horizontal\",\n" +
            "    \"spacing\": \"sm\",\n" +
            "    \"contents\": [\n" +
            "      {\n" +
            "        \"type\": \"button\",\n" +
            "        \"style\": \"link\",\n" +
            "        \"height\": \"sm\",\n" +
            "        \"action\": {\n" +
            "          \"type\": \"uri\",\n" +
            "          \"label\": \"好好好好好\",\n" +
            "          \"uri\": \"https://linecorp.com\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"button\",\n" +
            "        \"style\": \"link\",\n" +
            "        \"height\": \"sm\",\n" +
            "        \"action\": {\n" +
            "          \"type\": \"uri\",\n" +
            "          \"label\": \"明天再洗\",\n" +
            "          \"uri\": \"https://linecorp.com\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"type\": \"spacer\",\n" +
            "        \"size\": \"sm\"\n" +
            "      }\n" +
            "    ],\n" +
            "    \"flex\": 0\n" +
            "  }\n" +
            "}";

    public static String getFlex() {
        return flex;
    }
}
