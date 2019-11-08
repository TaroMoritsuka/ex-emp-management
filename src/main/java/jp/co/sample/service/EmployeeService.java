package jp.co.sample.service;

import java.util.ArrayList;
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
	
//	/**
//	 * @return
//	 * 従業員のリポジトリを操作するクラス.
//	 */
//	public List<Employee> findAll(){
//		List<Employee> employeeList = repository.findAll();
//		return employeeList;
//	}
	
//	/**
//	 * @param id
//	 * @return
//	 * 従業員のリポジトリを操作するクラス.
//	 */
//	public Employee load(Integer id) {
//		Employee employee = repository.load(id);
//		return employee;
//	}
	
	/**
	 * 従業員のリポジトリを使用し、従業員の扶養人数を更新する。
	 * @param employee　更新する従業員
	 */
	public void update(Employee employee) {
		repository.update(employee);
	}
	
	/**
	 * 従業員のリポジトリを使用し、従業員一覧を取得
	 * @return　従業員一覧
	 */
	public List<Employee> showList(){
		return repository.findAll();
	}
	
	/**
	 * @param id 従業員のid
	 * @return	従業員情報
	 */
	public Employee showDetail(Integer id) {
		return repository.load(id);
	}
	
	/**
	 * 従業員一覧のデータの件数を取得する.
	 * @return 従業員一覧のデータの件数を返す。
	 */
	public int countData() {
		return repository.findAllCount();
	}
	
	public List<Integer> pageList(){
		Integer countData = repository.findAllCount();
		List<Integer> pageNum = new ArrayList<>();
		if( countData / 10 == 0) {
			for(int i = 1; i<=countData; i++) {
				pageNum.add(i);
			} 
		} else {
			for(int i = 1; i <= countData/10+1; i++) {
				pageNum.add(i);
			}
		}
		return pageNum;
	}
	
	public List<Employee> findAllPageNum(Integer pageNum){
		return repository.findAllPageNum(pageNum);
	}
	
	
	

}
