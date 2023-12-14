package mike.samples.spark.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import mike.samples.spark.domain.Employee;
import mike.samples.spark.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    public Optional<Employee> getById(int id) {
        return this.employeeRepository.findById(id);
    }
    
    public List<Employee> getAll() {
        return this.employeeRepository.findAll();
    }
}
