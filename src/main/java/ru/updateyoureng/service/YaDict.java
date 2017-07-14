package ru.updateyoureng.service;

import ru.updateyoureng.model.Word;

import java.util.List;

public interface YaDict {
    final static byte YA_FLAG_FAMILY = 0x01;
    final static byte YA_FLAG_SHORT_POS = 0x02;
    final static byte YA_FLAG_MORPHO = 0x04;
    final static byte YA_FLAG_POS_FILTER  = 0x08;

    final static String YA_UI_RU    = "ru";
    final static String YA_UI_UKR   = "uk";
    final static String YA_UI_EN    = "en";
    final static String YA_UI_TR    = "tr";

    String getLang();
    void setLang(String lang);
    byte getFlags();
    void setFlags(byte Flags);
    String getUI();
    void setUI(String ui);
    List<String> getTranslate(Word word);
}
