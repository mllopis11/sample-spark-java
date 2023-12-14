package mike.samples.spark.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import mike.samples.spark.domain.Employee;

@Repository
class EmployeeRepositoryJdbcImpl implements EmployeeRepository {

    private final JdbcTemplate jdbc;
    
    private final String SEL_ALL = """
        SELECT id, firstname, lastname, hire_date, active FROM employee
        """;
    
    private final String SEL_BY_ID = SEL_ALL + " WHERE id = ?";
    
    public EmployeeRepositoryJdbcImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @Override
    public Optional<Employee> findById(int id) {
        return Optional.ofNullable(this.jdbc.queryForObject(SEL_BY_ID, new EmployeeMapper(), id));
    }
    
    @Override
    public List<Employee> findAll() {
        return this.jdbc.query(SEL_ALL, new EmployeeMapper());
    }
    
    private static class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            var employee = new Employee();
            
            employee.setId(rs.getInt(1));
            employee.setFirstName(rs.getString(2));
            employee.setLastName(rs.getString(3));
            employee.setHireDate(rs.getDate(4).toLocalDate());
            employee.setActive(rs.getBoolean(5));
            
            return employee;
        }
        
    }
}
