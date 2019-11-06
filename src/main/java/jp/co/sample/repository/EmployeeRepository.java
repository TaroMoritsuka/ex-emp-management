package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * @author taro
 *従業員のリポジトリを表すクラス
 */
@Repository
public class EmployeeRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 従業員のRowMapper
	 */
	private final static RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("email_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * @return 従業員のリストを返す。(従業員が存在しない場合はサイズ0件の従業員一覧を返す。)
	 * 入社日順に従業員の全件検索を行う。
	 */
	public List<Employee> findAll(){
		String sql = "SELECT id,name,gender,hire_date,email_address,zip_code,address,telephone,alary,characteristics,dependents_count" + 
				"FROM employees ORDER BY hire_date ASC;";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		return employeeList;
	}
	
	/**
	 * @param id
	 * @return 従業員を返す。
	 * 主キーから従業員情報を取得する。（従業員が存在しない場合はspringが自動的に例外を発生する。）
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,gender,hire_date,email_address,zip_code,address,telephone,alary,characteristics,dependents_count" + 
				"FROM employees WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/**
	 * @param employee
	 * 従業員の扶養人数を更新する。
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
	}
	
	
	
	
	
	
	

}
