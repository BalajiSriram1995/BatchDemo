package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class BatchConfig2 {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    private Resource outputResource = new FileSystemResource("new.csv");


    @Bean
    public FlatFileItemReader<NBA> reader() {
        return new FlatFileItemReaderBuilder<NBA>()
                .name("personItemReader")
                .resource(new ClassPathResource("sample-dt1.csv"))
                .delimited()
                .names(new String[]
                        {"Name", "Team","Position",
                                "Height","Weight","Age"}




                )
                .fieldSetMapper(new BeanWrapperFieldSetMapper<NBA>() {{
                    setTargetType(NBA.class);
                }})
                .build();
    }

    @Bean
    public NBAProcessor processor() {
        System.out.println("Inside Processor");
        return new NBAProcessor();
    }



    /*
    @Bean
    public FlatFileItemWriter<Person> writer() {
        FlatFileItemWriter<Person> writer = new FlatFileItemWriter<>();

        //Set output file location
        writer.setResource(outputResource);

        //All job repetitions should "append" to same output file
        writer.setAppendAllowed(true);

        //Name field values sequence based on object properties
        writer.setLineAggregator(new DelimitedLineAggregator<Person>() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
                    {
                        setNames(new String[] { "Name"});
                    }
                });
            }
        });
        return writer;
    }
*/
    @Bean
    public JsonFileItemWriter<NBADTO> writer(){
        return new JsonFileItemWriterBuilder<NBADTO>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
                .resource(new FileSystemResource("bala2.json"))

                .name("dataWriter")
                .build();
    }
    @Bean
    public Job importUserJob() {
        System.out.println("Inside Job");
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        System.out.println("Inside step1");
        return stepBuilderFactory.get("step1")
                .<NBA, NBADTO> chunk(200)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
