package jp.co.sample.form;

/**
 * 管理者情報登録に使用されるフォーム.
 * @author taro
 *
 */
public class InsertAdministratorForm {
	/**登録する管理者の名前*/
	private String name;
	/**登録する管理者のメールアドレス*/
	private String mailAddress;
	/**登録する管理者のパスワード*/
	private String password;
	
	public String getName() {
		
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", getName()=" + getName() + ", getMailAddress()=" + getMailAddress() + ", getPassword()="
				+ getPassword() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
