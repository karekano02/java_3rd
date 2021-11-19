
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Samplejob {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    SampleService sampleService;

    String jobName = "SampleJobName";

    String stepName1 = "stepName11";
    String stepName2 = "stepName12";
    String stepName3 = "stepName13";

    @Autowired
    SampleTask1 sampleTask1;

    @Bean
    public Job sampleJob(){
        return jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer())
                .flow(sampleStep1())
                .next(sampleStep2())
                .next(sampleStep3())
                .end()
                .build();
    }

    @Bean
    @JobScope
    public Step sampleStep1(){
        return stepBuilderFactory.get(stepName1)
                .tasklet(sampleTask1)
//                .tasklet((contribution, chunkContext) -> {
//                    sampleService.getSampleCode()
//                            .stream().map(code -> code.getGrCode())
//                            .forEach(log::info);
//                    return RepeatStatus.FINISHED;
//                })
                .build();
    }

    @Bean
    @JobScope
    public Step sampleStep2(){
        return stepBuilderFactory.get(stepName2)
                .tasklet((contribution, chunkContext) -> {
                    for(int idx = 0 ; idx < 10 ; idx++) {
                        log.info("[sampleStep2] : " + idx);
                    }
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step sampleStep3(){
        return stepBuilderFactory.get(stepName3)
                .<SampleModel,String>chunk(10)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .build();

    }

    @Bean
    public MyBatisCursorItemReader<SampleModel> itemReader(){
        log.info("itemReader");
        return new MyBatisCursorItemReaderBuilder<SampleModel>()
                .sqlSessionFactory(sqlSessionFactory)
                .queryId("com.kcar.batch.sample.mapper.mysql.SampleMysqlMapper.getSampleCode")
                .build();
    }

    @Bean
    public ItemProcessor<SampleModel,String> processor(){
        log.info("processor");
        return sample -> {
           return sample.getGrCode();
        };
    }

    @Bean
    public ItemWriter<String> itemWriter(){
        log.info("writer");
        return items -> {
            for(String item : items){
                log.info("ItemWriter item :"+item);
            }
        };
    }
}
