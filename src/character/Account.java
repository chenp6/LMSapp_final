package character;


import java.io.FileNotFoundException;
import java.io.IOException;


public abstract class Account {
	public String account;
	public String name;
	public String password;
	public char character;// 學生:'s' 教師:'p' 管理員:'m'

	public Account(String account, String password, String name,char character) {
		this.account = account;
		this.name = name;
		this.password = password;
		this.character = character;
	}
	public abstract boolean changePassword(String oldPassword, String newPassword) throws FileNotFoundException, IOException;


}
