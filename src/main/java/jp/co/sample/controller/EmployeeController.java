package jp.co.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Employee;
import jp.co.sample.form.UpdateEmployeeForm;
import jp.co.sample.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラ.
 * @author taro
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@ModelAttribute
	public UpdateEmployeeForm setUpUpdateEmployeeForm() {
		return new UpdateEmployeeForm();
	}
	/**
	 * 従業員一覧を表示する。
	 * @param model
	 * @return 従業員一覧へ画面遷移
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		model.addAttribute("employeeList", employeeService.showList());
		return "employee/list.html";
	}
	
	@RequestMapping("/showDetail")
	public String showDetail(String id,Model model) {
		model.addAttribute("employee",employeeService.showDetail(Integer.parseInt(id)));
		return "employee/detail.html";
		
	}
	
	/**
	 * 従業員の扶養人数を更新する
	 * @param form フォーム
	 * @return 扶養人数更新後の従業員一覧画面を返す
	 */
	@RequestMapping("/update")
	public String update(UpdateEmployeeForm form) {
		System.out.println(form);
		Employee employee = new Employee();
		employee.setId(Integer.parseInt(form.getId()));
		employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
		System.out.println(1);
		employeeService.update(employee);
		return "redirect:/employee/showList";
		
	}
	
	
	
}
