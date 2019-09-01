package bankAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class BankDAO extends BankAccess{
	static Scanner scan = new Scanner(System.in);
	public static void loginAccess() throws Exception{
		System.out.println("Enter the existing customer's account name: ");
		String name= scan.next();
		System.out.println("Enter the pin: ");
		int pin = scan.nextInt();
		try {
			BankAccess bankaccess = new BankAccess();
			bankaccess = findLoginId(name,pin);
			String Name = bankaccess.getName();
			int pinNo = bankaccess.getPinNo();
			System.out.println("/////////////////////////////////////////////////");
			System.out.println("The account is LOGGED-IN");
			System.out.println("YOUR ACCOUNT NO IS :" +bankaccess.getAccNo());
			System.out.println("YOUR NATIONAL NUMBER IS :" +bankaccess.getAadharNo());
			System.out.println("YOUR MOBILE NUMBER IS :" + bankaccess.getMobileNo());
			System.out.println("/////////////////////////////////////////////////");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("User does not exist");
		}}
	public static void insert(BankAccess bankaccess) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into create_account (name,account_no,mob_no,aadhar_no,pin_no,cur_bal) values ( ?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, bankaccess.getName());
		pst.setInt(2, bankaccess.getAccNo());
		pst.setLong(3, bankaccess.getMobileNo());
		pst.setLong(4, bankaccess.getAadharNo());
		pst.setInt(5, bankaccess.getPinNo());
		pst.setFloat(6, bankaccess.getBalance());
		int rows = pst.executeUpdate();
		System.out.println("No of rows inserted :" + rows);
		System.out.println("Account created successfully");
	}
	

	public static BankAccess findLoginId(String name,int pinNo) throws SQLException{
		Connection con = ConnectionUtil.getConnection();
		String sql = "select name,account_no,mob_no,aadhar_no,pin_no,cur_bal from create_account where name = ? and pin_no = ?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, name);
		pst.setInt(2, pinNo);
		ResultSet rs = pst.executeQuery();
		BankAccess bankaccess = null;
		if (rs.next()) {
			bankaccess = toRow(rs);
		}
		return bankaccess;
	}
	
	public static void operations(BankAccess bankaccess) throws SQLException {
		System.out.println("//////////////////////////////");
		System.out.println("OPTIONS FOR ONLINE TRANSACTION: \n 1.Deposit \n 2.Withdraw \n 3.Update balance \n 4.close account");
		System.out.println("//////////////////////////////");
		int ch = scan.nextInt();
		switch(ch) {
		case 1:
			depositTransaction();
			insert1(bankaccess);
			deposit(bankaccess,balance);
			System.out.println("Amount deposited successfully");
			operations(bankaccess);
			break;
		case 2:
			withdrawalTransaction();
			insert2(bankaccess);
			withdraw(bankaccess,balance);
			System.out.println("Amount withdrawn successfully");
			operations(bankaccess);
			break;
		case 3:
			current_balance(bankaccess,balance);
			System.out.println("Account updated successfully");
			operations(bankaccess);
			break;
		case 4:
			System.out.println("SUCCESSFULLY LOGOUT");
			break;
		}
	}
	
	public static void depositTransaction() {

		float amount;
		System.out.println("Enter Amount to be Deposited: ");
		amount = scan.nextInt();
		balance = balance + amount;
		System.out.println("Deposited! Account Balance is " + balance);

	}

	public static float withdrawalTransaction() {
		float amount;
		System.out.println("Enter Amount to be Withdrawn:");
		amount = scan.nextInt();
		if (amount < balance) {
			balance = balance - amount;
			System.out.println("Amount Withdrawn!! Available Balance: " + balance);
		} else {
			System.out.println("Less Balance..Transaction Failed..");
		}
		return amount;
	}
	
	public static void insert1(BankAccess bankaccess) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into transaction_dewr (name,balance_cr) values ( ?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, bankaccess.getName());
		pst.setFloat(2, bankaccess.getBalance());
		int rows = pst.executeUpdate();
		System.out.println("No of rows inserted :" + rows);
	}
	
	
	public static void deposit(BankAccess bankaccess, float balance) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
			String sql = "update transaction_dewr set balance_cr = ? where name = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			balance = balance+amount;
			pst.setFloat(1, balance);
			pst.setString(2, bankaccess.getName());	
			System.out.println(balance);
			int rows = pst.executeUpdate();
			System.out.println("Number of rows updated:" + rows);
			System.out.println("SUCCESSFULLY DEPOSITED");
		
	}
	
	public static void insert2(BankAccess bankaccess) throws SQLException {
		Connection con = ConnectionUtil.getConnection();
		String sql = "insert into transaction_dewr (name,balance_dr) values ( ?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, bankaccess.getUserId());
		pst.setFloat(2, bankaccess.getBalance());
		int rows = pst.executeUpdate();
		System.out.println("No of rows inserted :" + rows);
	}
	
	public static void withdraw(BankAccess bankaccess, float balance) throws SQLException {
			Connection con = ConnectionUtil.getConnection();
			String sql = "update transaction_dewr set balance_dr = ? where name = ?";	
			PreparedStatement pst = con.prepareStatement(sql);
			try {
			balance = balance-amount;
			pst.setFloat(1, balance);
			pst.setString(2, bankaccess.getName());		
			System.out.println(balance);
			int rows = pst.executeUpdate();
			System.out.println("Number of rows updated:" + rows);
			System.out.println("SUCCESSFULLY WITHDRAWN");
			}catch(Exception e) {
				e.printStackTrace();
			}
	}
	public static void current_balance(BankAccess bankaccess,float balance) throws SQLException{
		Connection con = ConnectionUtil.getConnection();
		String sql = "update create_account set cur_bal = ? where name = ?"; 
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setFloat(1, balance);
		pst.setString(2, bankaccess.getName());	
		System.out.println(name);
		System.out.println(balance);
		int rows = pst.executeUpdate();
		System.out.println("Number of rows updated:" + rows);
		System.out.println("SUCCESSFULLY UPDATED");
	}
	

	private static BankAccess toRow(ResultSet rs) throws SQLException {
		String name = rs.getString("name");
		Integer accNo = rs.getInt("account_no");
		Integer mobileNo = rs.getInt("mob_no");
		Long aadharNo = rs.getLong("aadhar_no");
		Integer pinNo = rs.getInt("pin_no");
		BankAccess bankaccess = new BankAccess();
		bankaccess.setName(name);
		bankaccess.setAccNo(accNo);
		bankaccess.setMobileNo(mobileNo);
		bankaccess.setAadharNo(aadharNo);
		bankaccess.setPinNo(pinNo);
		bankaccess.setBalance(balance);
	return bankaccess;
	}


}
