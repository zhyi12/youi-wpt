/**
 *
 */
package com.gsoft.weixin.wxmessage.entity;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 群发记录
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_MASS_LOG")
public class WxMassLog implements Domain{
	
	private static final long serialVersionUID = 972728731470130925L;
	

	@Column(name = "MASS_STATUS")
	@Length(max=3)
	private String massStatus;//群发状态

	@Column(name = "MASS_DATE")
	@Length(max=20)
	private String massDate;//群发日期

	@Column(name = "MASS_TIME")
	@Length(max=20)
	private String massTime;//群发时间
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "MASS_ID")
	@Length(max=36)
	private String massId;//群发ID
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MESSAGE_ID")
	private com.gsoft.weixin.wxmessage.entity.WxMessage wxMessage;//消息ID
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="YOU_MASS_ID")
	private com.gsoft.weixin.wxmessage.entity.WxMass wxMass;//700_群发主键
	
	public String getMassStatus(){
		return this.massStatus;
	}
	
	public void setMassStatus(String massStatus){
		this.massStatus = massStatus;
	}
	public String getMassDate(){
		return this.massDate;
	}
	
	public void setMassDate(String massDate){
		this.massDate = massDate;
	}
	public String getMassTime(){
		return this.massTime;
	}
	
	public void setMassTime(String massTime){
		this.massTime = massTime;
	}
	public String getMassId(){
		return this.massId;
	}
	
	public void setMassId(String massId){
		this.massId = massId;
	}
	
	public void setWxMessage(com.gsoft.weixin.wxmessage.entity.WxMessage wxMessage){
		this.wxMessage = wxMessage;
	}
	
	public com.gsoft.weixin.wxmessage.entity.WxMessage getWxMessage(){
		return this.wxMessage;
	}
	public void setWxMass(com.gsoft.weixin.wxmessage.entity.WxMass wxMass){
		this.wxMass = wxMass;
	}
	
	public com.gsoft.weixin.wxmessage.entity.WxMass getWxMass(){
		return this.wxMass;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((massStatus == null) ? 0 : massStatus.hashCode());
		result = prime * result + ((massDate == null) ? 0 : massDate.hashCode());
		result = prime * result + ((massTime == null) ? 0 : massTime.hashCode());
		result = prime * result + ((massId == null) ? 0 : massId.hashCode());
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
		final WxMassLog other = (WxMassLog) obj;
		if (massStatus == null) {
			if (other.massStatus != null)
				return false;
		} else if (!massStatus.equals(other.massStatus))
			return false;
		if (massDate == null) {
			if (other.massDate != null)
				return false;
		} else if (!massDate.equals(other.massDate))
			return false;
		if (massTime == null) {
			if (other.massTime != null)
				return false;
		} else if (!massTime.equals(other.massTime))
			return false;
		if (massId == null) {
			if (other.massId != null)
				return false;
		} else if (!massId.equals(other.massId))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}