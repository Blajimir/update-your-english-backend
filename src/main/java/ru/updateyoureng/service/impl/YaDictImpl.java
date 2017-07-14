package ru.updateyoureng.service.impl;

import jdk.internal.org.objectweb.asm.TypeReference;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import ru.updateyoureng.model.Word;
import ru.updateyoureng.service.YaDict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("YaDict")
public class YaDictImpl implements YaDict {
    private RestTemplate rest;
    private HttpHeaders header;
    private Map<String, String> responseMap;

    public YaDictImpl(String key) {
        responseMap = new HashMap<>();
        responseMap.put("key", key);
        responseMap.put("lang", "en-ru");
        responseMap.put("text", "");
        responseMap.put("flags", String.valueOf(YaDict.YA_FLAG_FAMILY | YaDict.YA_FLAG_MORPHO | YaDict.YA_FLAG_POS_FILTER));
        responseMap.put("ui", YaDict.YA_UI_RU);
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON_UTF8);
        this.rest = new RestTemplate();
    }

    @Override
    public String getLang() {
        return this.responseMap.get("lang");
    }

    @Override
    public void setLang(String lang) {
        responseMap.put("lang", lang);
    }

    @Override
    public byte getFlags() {
        return Byte.parseByte(responseMap.get("flags"));
    }

    @Override
    public void setFlags(byte flags) {
        this.responseMap.put("flags",String.valueOf(flags));
    }

    @Override
    public String getUI() {
        return null;
    }

    @Override
    public void setUI(String ui) {

    }

    @Override
    public List<String> getTranslate(Word word) {
        this.responseMap.put("text", word.getValue());
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(this.responseMap, header);
        Map<String, Object> result = null;
        //@SuppressWarnings("unchecked")
        result = this.rest.exchange(
                "https://dictionary.yandex.net/api/v1/dicservice.json/lookup",
                HttpMethod.GET,
                httpEntity,
                Map.class
        ).getBody();
        Assert.isNull(result, "return body is null");
        String[] arrDef = (String[]) result.get("def");
        if (arrDef.length == 0) {
            return null;
        }
        List<String> stringList = ;
        return stringList;
    }
}
