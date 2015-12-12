/**
 *
 */
package com.gsoft.weixin.wxmessage.entity;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 微信消息
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_MESSAGE")
public class WxMessage implements Domain{
	
	private static final long serialVersionUID = 5343040227731998408L;
	

	@Column(name = "MESSAGE_CONTENT")
	private String messageContent;//消息内容

	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键

	@Column(name = "MESSAGE_TITLE")
	@Length(max=100)
	private String messageTitle;//消息标题
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "MESSAGE_ID")
	@Length(max=36)
	private String messageId;//消息ID

	@Column(name = "MESSAGE_TYPE")
	@Length(max=20)
	private String messageType;//消息类型
	
	public String getMessageContent(){
		return this.messageContent;
	}
	
	public void setMessageContent(String messageContent){
		this.messageContent = messageContent;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getMessageTitle(){
		return this.messageTitle;
	}
	
	public void setMessageTitle(String messageTitle){
		this.messageTitle = messageTitle;
	}
	public String getMessageId(){
		return this.messageId;
	}
	
	public void setMessageId(String messageId){
		this.messageId = messageId;
	}
	public String getMessageType(){
		return this.messageType;
	}
	
	public void setMessageType(String messageType){
		this.messageType = messageType;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messageContent == null) ? 0 : messageContent.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((messageTitle == null) ? 0 : messageTitle.hashCode());
		result = prime * result + ((messageId == null) ? 0 : messageId.hashCode());
		result = prime * result + ((messageType == null) ? 0 : messageType.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final WxMessage other = (WxMessage) obj;
		if (messageContent == null) {
			if (other.messageContent != null)
				return false;
		} else if (!messageContent.equals(other.messageContent))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (messageTitle == null) {
			if (other.messageTitle != null)
				return false;
		} else if (!messageTitle.equals(other.messageTitle))
			return false;
		if (messageId == null) {
			if (other.messageId != null)
				return false;
		} else if (!messageId.equals(other.messageId))
			return false;
		if (messageType == null) {
			if (other.messageType != null)
				return false;
		} else if (!messageType.equals(other.messageType))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}