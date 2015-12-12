/**
 *
 */
package com.gsoft.weixin.wxmessage.entity;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 群发管理
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_MASS")
public class WxMass implements Domain{
	
	private static final long serialVersionUID = 2206004269804345972L;
	

	@Column(name = "TOTAL_COUNT")
	@Length(max=20)
	private String totalCount;//群发数量
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "MASS_ID")
	@Length(max=36)
	private String massId;//群发主键

	@Column(name = "LEFT_COUNT")
	@Length(max=20)
	private String leftCount;//当日剩余数量

	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键
	
	public String getTotalCount(){
		return this.totalCount;
	}
	
	public void setTotalCount(String totalCount){
		this.totalCount = totalCount;
	}
	public String getMassId(){
		return this.massId;
	}
	
	public void setMassId(String massId){
		this.massId = massId;
	}
	public String getLeftCount(){
		return this.leftCount;
	}
	
	public void setLeftCount(String leftCount){
		this.leftCount = leftCount;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
		result = prime * result + ((massId == null) ? 0 : massId.hashCode());
		result = prime * result + ((leftCount == null) ? 0 : leftCount.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		final WxMass other = (WxMass) obj;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		if (massId == null) {
			if (other.massId != null)
				return false;
		} else if (!massId.equals(other.massId))
			return false;
		if (leftCount == null) {
			if (other.leftCount != null)
				return false;
		} else if (!leftCount.equals(other.leftCount))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}