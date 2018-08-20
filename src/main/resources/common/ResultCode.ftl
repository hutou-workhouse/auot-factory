package ${local.userDefines.basePackagePath}.base;

public enum ResultCode {
	SUCCESS(0, "成功"), 

	;

	private int code;
	
	private String msg;
	
	private ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
}