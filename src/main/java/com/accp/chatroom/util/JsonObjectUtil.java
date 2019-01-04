package com.accp.chatroom.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
/**
 * 
* @ClassName: JsonObjectUtil 
* @Description: JsonObject信息处理工具 
* @author 筠颜
* @date 2019年1月2日 上午7:58:41 
*
 */
public class JsonObjectUtil {
	
	private String type;//数据类型
	private Object content;//数据内容
	
	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public Object getContent() {
		return content;
	}



	public void setContent(Object content) {
		this.content = content;
	}
	/**
	 * 
	    * @Title: removeContent
	    * @Description: 清空content存储的内容
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	public void removeContent() {
		this.content = null;
	}
	/**
	 * 
	    * @Title: pageJsonObject
	    * @Description: 封装JsonObject过程
	    * @param @return    参数
	    * @return JSONObject    返回类型
	    * @throws
	 */
	public JSONObject pageJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("type",this.type);
		obj.put("content",this.content);
		return obj;
	}
	/**
	 * 
	    * @Title: toJSONString
	    * @Description: 转换JSON格式String类型
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	 public String toJSONString() {
	        SerializeWriter out = new SerializeWriter();
	        try {
	            new JSONSerializer(out).write(this);
	            return out.toString();
	        } finally {
	            out.close();
	        }
	    }
}
