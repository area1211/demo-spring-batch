package com.example.demospringbatch.job;

import com.example.demospringbatch.job.tasklet.SimpleJobTasklet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j // log 사용을 위한 lombok 어노테이션
@RequiredArgsConstructor // 생성자 DI를 위한 lombok 어노테이션
@Configuration // Spring Batch의 모든 Job은 @Configuration으로 등록해서 사용한다.
public class SimpleJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory; // 생성자 DI 받음
    private final StepBuilderFactory stepBuilderFactory; // 생성자 DI 받음

    @Bean
    public Job simpleJob() {
        return jobBuilderFactory.get("simpleJob") // simpleJob 이라는 이름으로 Batch Job을 생성한다.
                .start(simpleStep1(null)) // Spring Batch에서 Job은 하나의 배치 작업 단위를 말한다.
                .next(simpleStep2(null))
                .next(simpleStep3())
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep1(@Value("#{jobParameters[requestDate]}")String requestDate) {
        return stepBuilderFactory.get("simpleStep1") // BatchStep 생성
                .tasklet((contribution, chunkContext) -> { // tasklet은 Step 안에서 단일로 수행될 커스텀한 기능들을 선언할 때 사용한다.
//                    throw new IllegalArgumentException("step1에서 실패합니다.");
                    log.info(">>>>> This is Step1");
                    log.info(">>>>> requestDate = {}", requestDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestDate]}")String requestDate) {
        return stepBuilderFactory.get("simpleStep2") // BatchStep 생성
                .tasklet((contribution, chunkContext) -> { // tasklet은 Step 안에서 단일로 수행될 커스텀한 기능들을 선언할 때 사용한다.
                    log.info(">>>>> This is Step2");
                    log.info(">>>>> requestDate = {}", requestDate);
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    private final SimpleJobTasklet tasklet1;
//    @Bean
//    @JobScope
    public Step simpleStep3() {
        return stepBuilderFactory.get("simpleStep3") // BatchStep 생성
                .tasklet(tasklet1)
                .build();
    }
}
