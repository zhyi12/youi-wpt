/**
 *
 */
package com.gsoft.weixin.wxmessage.entity;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 客服消息记录
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_CUSTOM_MESSAGE")
public class WxCustomMessage implements Domain{
	
	private static final long serialVersionUID = -7524487285105857249L;
	

	@Column(name = "MSG_SEND_TIME")
	@Length(max=20)
	private String msgSendTime;//发送时间

	@Column(name = "MSG_STATUS")
	@Length(max=3)
	private String msgStatus;//消息状态

	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键

	@Column(name = "OPENID")
	@Length(max=36)
	private String openid;//接收者微信号
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "CUSTOME_MSG_ID")
	@Length(max=36)
	private String customeMsgId;//客服消息ID
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MESSAGE_ID")
	private com.gsoft.weixin.wxmessage.entity.WxMessage wxMessage;//消息ID
	
	public String getMsgSendTime(){
		return this.msgSendTime;
	}
	
	public void setMsgSendTime(String msgSendTime){
		this.msgSendTime = msgSendTime;
	}
	public String getMsgStatus(){
		return this.msgStatus;
	}
	
	public void setMsgStatus(String msgStatus){
		this.msgStatus = msgStatus;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getOpenid(){
		return this.openid;
	}
	
	public void setOpenid(String openid){
		this.openid = openid;
	}
	public String getCustomeMsgId(){
		return this.customeMsgId;
	}
	
	public void setCustomeMsgId(String customeMsgId){
		this.customeMsgId = customeMsgId;
	}
	
	public void setWxMessage(com.gsoft.weixin.wxmessage.entity.WxMessage wxMessage){
		this.wxMessage = wxMessage;
	}
	
	public com.gsoft.weixin.wxmessage.entity.WxMessage getWxMessage(){
		return this.wxMessage;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msgSendTime == null) ? 0 : msgSendTime.hashCode());
		result = prime * result + ((msgStatus == null) ? 0 : msgStatus.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result + ((customeMsgId == null) ? 0 : customeMsgId.hashCode());
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
		final WxCustomMessage other = (WxCustomMessage) obj;
		if (msgSendTime == null) {
			if (other.msgSendTime != null)
				return false;
		} else if (!msgSendTime.equals(other.msgSendTime))
			return false;
		if (msgStatus == null) {
			if (other.msgStatus != null)
				return false;
		} else if (!msgStatus.equals(other.msgStatus))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (customeMsgId == null) {
			if (other.customeMsgId != null)
				return false;
		} else if (!customeMsgId.equals(other.customeMsgId))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}