package mike.samples.spark.repository;

import java.util.List;
import java.util.Optional;

import mike.samples.spark.domain.Employee;

public interface EmployeeRepository {

    Optional<Employee> findById(int id);

    List<Employee> findAll();
}
