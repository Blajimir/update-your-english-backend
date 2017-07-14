package ru.updateyoureng.service;

import ru.updateyoureng.model.Word;

public interface YaTranslate {
    String getLang();
    void setLang(String lang);
    String getTranslate(Word word);
}
