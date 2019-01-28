package learnspringbatch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
// Listing 4-17
public class HelloWorldTasklet implements Tasklet { 
    private static final String HELLO_WORLD = "Hello, %s"; 

    public RepeatStatus execute( StepContribution step, 
                                 ChunkContext context ) throws Exception {
        String name = (String) context.getStepContext()
                            .getJobParameters()
                            .get("name");

        ExecutionContext jobContext = context.getStepContext()
                                             .getStepExecution()
                                             .getJobExecution()
                                             .getExecutionContext();    
        jobContext.put("user.name", name);

        System.out.println( String.format(HELLO_WORLD, name) );
        return RepeatStatus.FINISHED; 
     } 
}


/*
 * Listing 4-6
public class HelloWorld implements Tasklet {

@Value("#{jobParameters['name']}")
private String name;

@Value("#{jobParameters['executionDate']}")
private Date executionDate;

private static String MESSAGE = "Hello %s, this was executed on %2$tB %2$td, %2$tY";

@Override
public RepeatStatus execute(StepContribution contribution, 
                            ChunkContext chunkContext) throws Exception {

            System.out.println(String.format(MESSAGE, name, executionDate));
            return RepeatStatus.FINISHED;
    }
}
*/