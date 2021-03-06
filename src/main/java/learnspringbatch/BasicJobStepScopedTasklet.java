package learnspringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

// @EnableBatchProcessing
// @Configuration
class BasicJobStepScopedTasklet {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step() {
		return this.stepBuilderFactory.get("step1")
		.tasklet(helloWorldTasklet()).build();
	}

	@Bean
	@StepScope
	public Tasklet helloWorldTasklet() {
		return new HelloWorldTasklet();
	}

	@Bean
	public Job job() {
		return this.jobBuilderFactory.get("BasicJobStepScopedTasklet ")
			.listener(new JobLoggerListener())
			.start((Step) step())
			.build();
	}

}
