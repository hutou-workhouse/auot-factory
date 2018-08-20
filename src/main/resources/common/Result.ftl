package ${local.userDefines.basePackagePath}.base;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 返回请求信息
 * 
 * @author autoFactory
 *
 * @param <T>
 */
public class Result<T> implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 返回码  
	 */
	private int code;
	
	/**
	 * 返回提示信息
	 */
	private String msg;
	
	/**
	 * 数据详情
	 */
	private T data;
	
	public Result(){
		super();
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getMsg();
	}
	
	public Result(T data){
		super();
		this.code = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getMsg();
		this.data = data;
	}
	
	public Result(int code,String msg,T data){
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(int code,String msg){
		super();
		this.code = code;
		this.msg = msg;
	}
	
	public Result(ResultCode resultCode){
		super();
		this.code = resultCode.getCode();
		this.msg = resultCode.getMsg();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean success() {
		if(code == ResultCode.SUCCESS.getCode()){
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Result [code=" + code + ", msg=" + msg + ", data=" + JSON.toJSONString(data) + "]";
	}
}
