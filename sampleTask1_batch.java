package com.kcar.batch.jobs.sample.tasks;

import com.kcar.batch.sample.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Component
@Slf4j
@StepScope
public class SampleTask1 implements Tasklet, StepExecutionListener {

    SampleService sampleService;

    public SampleTask1(SampleService sampleService){
        this.sampleService = sampleService;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        sampleService.getSampleCode()
                .stream().map(code -> code.getGrCode())
                .forEach(log::info);
        return RepeatStatus.FINISHED;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("beforeStep");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("afterStep");
        return ExitStatus.COMPLETED;
    }
}
