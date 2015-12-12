/**
 *
 */
package com.gsoft.weixin.wxmessage.entity;

import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.core.dataobj.Domain;
/**
 * 实体: 图文素材
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "YOUI_WX_MEDIA")
public class WxMedia implements Domain{
	
	private static final long serialVersionUID = -5088449325667475509L;
	

	@Column(name = "MEDIA_URL")
	@Length(max=255)
	private String mediaUrl;//素材URL

	@Column(name = "MEDIA_TYPE")
	@Length(max=20)
	private String mediaType;//素材类型
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")
	@Column(name = "MEDIA_ID")
	@Length(max=36)
	private String mediaId;//素材ID

	@Column(name = "USER_ID")
	@Length(max=36)
	private String userId;//公众号主键

	@Column(name = "MEDIA_CAPTION")
	@Length(max=100)
	private String mediaCaption;//素材名称
	
	public String getMediaUrl(){
		return this.mediaUrl;
	}
	
	public void setMediaUrl(String mediaUrl){
		this.mediaUrl = mediaUrl;
	}
	public String getMediaType(){
		return this.mediaType;
	}
	
	public void setMediaType(String mediaType){
		this.mediaType = mediaType;
	}
	public String getMediaId(){
		return this.mediaId;
	}
	
	public void setMediaId(String mediaId){
		this.mediaId = mediaId;
	}
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}
	public String getMediaCaption(){
		return this.mediaCaption;
	}
	
	public void setMediaCaption(String mediaCaption){
		this.mediaCaption = mediaCaption;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mediaUrl == null) ? 0 : mediaUrl.hashCode());
		result = prime * result + ((mediaType == null) ? 0 : mediaType.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((mediaCaption == null) ? 0 : mediaCaption.hashCode());
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
		final WxMedia other = (WxMedia) obj;
		if (mediaUrl == null) {
			if (other.mediaUrl != null)
				return false;
		} else if (!mediaUrl.equals(other.mediaUrl))
			return false;
		if (mediaType == null) {
			if (other.mediaType != null)
				return false;
		} else if (!mediaType.equals(other.mediaType))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (mediaCaption == null) {
			if (other.mediaCaption != null)
				return false;
		} else if (!mediaCaption.equals(other.mediaCaption))
			return false;
		return true;
	}
	
	public String toString(){
		return super.toString();
	}
}