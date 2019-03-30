package com.example.demospringbatch.job.transaction.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
//    private String url;

    @Builder
    public Keyword(String name) {
        this.name = name;
    }

}
