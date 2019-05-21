package xie.yuan.chun.common;

public enum ResponseEnmus {

	SUCCESS(200,"操作成功"),
	FAIL(400,"操作失败");

	ResponseEnmus(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private Integer code;
	private String message;
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
