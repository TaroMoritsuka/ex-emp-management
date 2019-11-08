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
 *従業員情報を操作するリポジトリ.
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
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};
	
	/**
	 * 入社日順に従業員の全件検索を行う.
	 * 
	 * @return 従業員のリストを返す。(従業員が存在しない場合はサイズ0件の従業員一覧を返す。)
	 */
	
	public List<Employee> findAll(){
			String sql = "SELECT id,name,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count,image FROM employees ORDER BY hire_date ASC;";
			List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
			return employeeList;
	}
	
	//ページネーション実装
	public List<Employee> findAllPageNum(Integer pageNum){
		String sql = "SELECT id,name,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count,image FROM employees ORDER BY id ASC LIMIT 10 OFFSET :page;";
		//String page = Integer.toString(pageNum);
		SqlParameterSource param = new MapSqlParameterSource().addValue("page", pageNum);
		return template.query(sql, param, EMPLOYEE_ROW_MAPPER);
	}
	
	public Integer findAllCount() {
		String sql = "SELECT COUNT(*) FROM employees;";
		SqlParameterSource param = new MapSqlParameterSource();
		return template.queryForObject(sql, param, Integer.class);
	}
	
	
	/**
	 * 主キーから従業員情報を取得する.
	 * @param id ID
	 * @return 従業員を返す。（従業員が存在しない場合はspringが自動的に例外を発生する。）
	 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count,image FROM employees WHERE id = :id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
	
	/**
	 * 従業員の扶養人数を更新する.
	 * 
	 * @param employee　従業員情報
	 */
	public void update(Employee employee) {
		String sql = "UPDATE employees SET dependents_count = :dependentsCount WHERE id = :id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		template.update(sql, param);
	}
	
	
	
	
	
	
	
	
	

}
