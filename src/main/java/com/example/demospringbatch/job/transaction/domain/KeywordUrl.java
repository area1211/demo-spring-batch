package com.example.demospringbatch.job.transaction.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class KeywordUrl {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long keywordId;
    private String url;
//    private String txName;
//    private LocalDateTime txDateTime;

    public KeywordUrl(Long keywordId, String url) {
        this.keywordId = keywordId;
        this.url = url;
    }


//    public KeywordUrl(Long keywordId, String url, String txDateTime) {
//        this.keywordId = keywordId;
//        this.url = url;
//        this.txDateTime = LocalDateTime.parse(txDateTime, FORMATTER);
//    }
//    public KeywordUrl(Long keywordId, String url, LocalDateTime txDateTime) {
//        this.keywordId = keywordId;
//        this.url = url;
//        this.txDateTime = txDateTime;
//    }
//    public KeywordUrl(Long id, Long keywordId, String url, String txDateTime) {
//        this.id = id;
//        this.keywordId = keywordId;
//        this.url = url;
//        this.txDateTime = LocalDateTime.parse(txDateTime, FORMATTER);
//    }


}
