package xie.yuan.chun.common;
import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer code = 200;
	private String message = "success";
	private Object data;


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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Response errorResponse(String message) {
		Response response = new Response();
		response.setCode(ResponseEnmus.FAIL.getCode());
		response.setMessage(message);
		return response;
	}

	public static Response errorResponseWithData(String message,Object data) {
		Response response = new Response();
		response.setCode(ResponseEnmus.FAIL.getCode());
		response.setMessage(message);
		response.setData(data);
		return response;
	}

	public static Response successResponseWithData(Object data) {
		Response response = new Response();
		response.setCode(ResponseEnmus.SUCCESS.getCode());
		response.setMessage(ResponseEnmus.SUCCESS.getMessage());
		response.setData(data);
		return response;
	}

	public static Response build(Integer code, String message) {
		return new Response(code, message, null);
	}
	public static Response build(Integer code, String message, Object data) {
		return new Response(code, message, data);
	}
	public Response(ResponseEnmus responseResultEnum, Object data) {
		this(responseResultEnum.getCode(),responseResultEnum.getMessage(),data);
	}
	public Response() {

	}
	public Response(Integer code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

}
