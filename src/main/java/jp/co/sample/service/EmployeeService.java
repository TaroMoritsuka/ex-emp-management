package jp.co.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

/**
 * @author taro
 *従業員の業務処理を表すクラス
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;
	
	/**
	 * @return
	 * 従業員のリポジトリを使用し、従業員の全件検索を行う。
	 */
	public List<Employee> findAll(){
		List<Employee> employeeList = repository.findAll();
		return employeeList;
	}
	
	/**
	 * @param id
	 * @return
	 * 従業員のリポジトリを使用し、従業員のid検索を行う。
	 */
	public Employee load(Integer id) {
		Employee employee = repository.load(id);
		return employee;
	}
	
	/**
	 * @param employee
	 * 従業員のリポジトリを使用し、従業員の扶養人数を更新する。
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}
}
