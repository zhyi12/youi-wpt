/**
 *
 */
package ${package};
<#if hasSet??>
import java.util.Set;</#if>
import javax.persistence.*;
import org.hibernate.validator.*;

import org.hibernate.annotations.GenericGenerator;

import com.gsoft.framework.<#if supportWorkflow==false>core.dataobj.Domain</#if><#if supportWorkflow==true>workflow.domain.WorkflowDomain</#if>;
/**
 * 实体: ${modelDescription}
 * @author
 * @version
 * 
 */
@Entity
@Table(name = "${tableName}")
public class ${className} implements <#if supportWorkflow==true>Workflow</#if>Domain{
	
	//private static final long serialVersionUID = 1L;
	
	<#list attributes as attribute>
	<#if attribute.id=="true">
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid.hex")</#if><#if attribute.column??>
	@Column(name = "${attribute.column}")</#if><#if attribute.filterOperator??>
	@FilterOperator(operator = "${attribute.filterOperator}")</#if><#if attribute.length??>
	@Length(max=${attribute.length})</#if>
	private ${attribute.type} ${attribute.name};<#if attribute.description??>//${attribute.description}</#if>
	</#list>
	
	<#list foreignAttributes as foreignAttribute>
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="${foreignAttribute.column}")
	private ${foreignAttribute.type} ${foreignAttribute.name};<#if foreignAttribute.description??>//${foreignAttribute.description}</#if>
	
	</#list>
	<#list setAttributes as setAttribute>
	<#if setAttribute.setType=="many-to-many">
	@ManyToMany(
		targetEntity=${setAttribute.targetEntity},
        cascade=${setAttribute.cascade}
        <#if setAttribute.mappedBy??>,mappedBy="${setAttribute.mappedBy}"</#if>
	)
    <#if setAttribute.owern??>@JoinTable(
        name="${setAttribute.joinTableName}",
        joinColumns = { @JoinColumn( name="${setAttribute.joinColumns}") },
        inverseJoinColumns = @JoinColumn( name="${setAttribute.inverseJoinColumns}")
    )</#if></#if><#if setAttribute.setType=="one-to-many">
	@OneToMany(targetEntity=${setAttribute.targetEntity})
	@JoinColumn(name="${setAttribute.joinColumnName}")</#if>
	private Set ${setAttribute.name};<#if setAttribute.description??>//${setAttribute.description}</#if>
	
	</#list>
	<#list attributes as attribute>
	public ${attribute.type} get${attribute.FUName}(){
		return this.${attribute.name};
	}
	
	public void set${attribute.FUName}(${attribute.type} ${attribute.name}){
		this.${attribute.name} = ${attribute.name};
	}
	</#list>
	
	<#list foreignAttributes as foreignAttribute>
	public void set${foreignAttribute.FUName}(${foreignAttribute.type} ${foreignAttribute.name}){
		this.${foreignAttribute.name} = ${foreignAttribute.name};
	}
	
	public ${foreignAttribute.type} get${foreignAttribute.FUName}(){
		return this.${foreignAttribute.name};
	}
	</#list>
	
	<#list setAttributes as setAttribute>
	public Set get${setAttribute.FUName}(){
		return this.${setAttribute.name};
	}
	
	public void set${setAttribute.FUName}(Set ${setAttribute.name}){
		this.${setAttribute.name} = ${setAttribute.name};
	}
	</#list>
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		<#list attributes as attribute>
		result = prime * result + ((${attribute.name} == null) ? 0 : ${attribute.name}.hashCode());
		</#list>
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
		final ${className} other = (${className}) obj;
		<#list attributes as attribute>
		if (${attribute.name} == null) {
			if (other.${attribute.name} != null)
				return false;
		} else if (!${attribute.name}.equals(other.${attribute.name}))
			return false;
		</#list>
		return true;
	}
	
	public String toString(){
		return ${toString};
	}
}