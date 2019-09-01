package bankAccess;

import java.util.Scanner;


public class Bank {
	BankAccess bankaccess = new BankAccess();
	static int phNo;
	static int accNo;
	static String name;
	static float bal;
	static String userId;
	static int pswd;
	static long mobileNo;
	static long aadharNo;
	static int pinNo;
	static float previousTransaction;
	static float balance;
	BankDAO dao = new BankDAO();
	 Scanner sc = new Scanner(System.in);
	public void Banking() throws Exception {
		int ch=0 ;
		System.out.println("==========================");
		System.out.println("WELCOME TO AMERICAN BANK \n 1.Create Account \n 2.Login \n 3.Operations for transaction \n 4. close services");
		System.out.println("=========================");
		try {
			ch = sc.nextInt();
		} catch (Exception e) {
			System.out.println("invalid expression");
		}
			
		switch(ch){
		case 1:
			System.out.println("Enter your name  : " );
			name = sc.next();
			System.out.println("Enter your account number  : " );
			accNo = sc.nextInt();
			System.out.println("Enter your mobile no :");
			mobileNo = sc.nextLong();
			System.out.println("Enter your National ID :");
			aadharNo = sc.nextLong();
			System.out.println("Enter your pin no :");
			pinNo= sc.nextInt();
			System.out.println("Enter your current balance:");
			balance = sc.nextFloat();
			bankaccess.setName(name);
			bankaccess.setAccNo(accNo);
			bankaccess.setMobileNo(mobileNo);
			bankaccess.setAadharNo(aadharNo);
			bankaccess.setPinNo(pinNo);
			bankaccess.setBalance(balance);
			BankDAO.insert(bankaccess);
			Banking();
			break;
		case 2:
			
			bankaccess.setName(name);
			bankaccess.setPinNo(pinNo);
			BankDAO.findLoginId(name,pinNo);
			BankDAO.loginAccess();
			Banking();
			break;
			
		case 3:
			BankDAO.operations(bankaccess);
			Banking();
			break;
		case 4:
			System.out.println("THANK YOU");
			break;
		}
	}
	
}