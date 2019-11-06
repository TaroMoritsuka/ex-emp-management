package jp.co.sample.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;

/**
 * @author taro
 *管理者のリポジトリを表すクラス
 */
@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 管理者のRowMapper
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs,i) ->{
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	/**
	 * @param administrator
	 *管理者を新規登録する。
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mail_address,:password); ";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
	}
	
	/**
	 * @param mailAddress
	 * @param password
	 * @return Administrator
	 * パスワードとメールアドレスから管理者情報を取得。(存在しない場合はnullを返す。) 
	 */
	public Administrator findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,passwrd FROM administrators WHERE mail_address = :mailAddress AND password = :password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		if(template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER) == null) {
			return null;
		} else {
			Administrator administrator = template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
			return administrator;
		}
	}

}
