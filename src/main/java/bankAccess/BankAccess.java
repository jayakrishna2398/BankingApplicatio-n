package bankAccess;

public class BankAccess {
	protected static int accNo;
	protected static String name;
	protected long aadharNo;
	protected static float balance;
	protected String userId;
	protected int pswd;
	protected static long mobileNo;
	protected static int pinNo;
	protected static int amount;

	@Override
	public String toString() {
		return "BankAccess [accNo=" + accNo + ", name=" + name + ", aadharNo=" + aadharNo + ", balance=" + balance
				+ ", userId=" + userId + ", pswd=" + pswd + ", mobileNo=" + mobileNo + ", pinNo=" + pinNo + ", amount="
				+ amount + "]";
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo(int accNo) {
		BankAccess.accNo = accNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		BankAccess.name = name;
	}

	public long getAadharNo() {
		return aadharNo;
	}

	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		BankAccess.balance = balance;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getPswd() {
		return pswd;
	}

	public void setPswd(int pswd) {
		this.pswd = pswd;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		BankAccess.mobileNo = mobileNo;
	}

	public int getPinNo() {
		return pinNo;
	}

	public void setPinNo(int pinNo) {
		BankAccess.pinNo = pinNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		BankAccess.amount = amount;
	}

}