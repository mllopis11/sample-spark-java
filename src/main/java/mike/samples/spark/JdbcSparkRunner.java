package mike.samples.spark;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import mike.samples.spark.service.EmployeeService;

@Component
class JdbcSparkRunner implements CommandLineRunner, ExitCodeGenerator{

    private static final Logger log = LoggerFactory.getLogger(JdbcSparkRunner.class);

    private final EmployeeService employeeService;
    
    public JdbcSparkRunner(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    private int exitCode = 9;

    @Override
    public int getExitCode() {
        return exitCode;
    }

    @Override
    public void run(String... args) throws Exception {
        
        log.info("Runner: started ...");
        
        this.employeeService.getById(1).ifPresentOrElse(
                u -> log.info("GetById: {}", u.toString()), 
                () -> log.info("GetById: Employee not found (id: 1)"));
        
        this.employeeService.getAll().forEach(u -> log.info("GetAll: {}", u.toString()));
        
        
    }
}
