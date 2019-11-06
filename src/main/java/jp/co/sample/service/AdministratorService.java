package jp.co.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

/**
 * @author taro
 *管理者の業務処理を表すクラス
 */
@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository repository;
	
	/**
	 * @param administrator
	 * 管理者のリポジトリを使用し、管理者情報の登録を行う。
	 */
	public void insert(Administrator administrator) {
		repository.insert(administrator);
	}
	
	/**
	 * @param mailAddress
	 * @param password
	 * @return
	 * 管理者のリポジトリを使用し、メールアドレスとパスワードから管理者情報を取得する。
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		Administrator administrator = repository.findByMailAddressAndPassword(mailAddress, password);
		return administrator;
	}
	
	/**
	 * @param mailAddress
	 * @param password
	 * @return
	 * 管理者のリポジトリを使用し、ログインに使用するメールアドレスとパスワードで管理者情報を取得する。
	 */
	public Administrator login(String mailAddress, String password) {
		return repository.findByMailAddressAndPassword(mailAddress, password);
	}
	
	
	

}
