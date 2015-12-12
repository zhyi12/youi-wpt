/**
 * 代码声明
 */
package ${package};

import java.util.List;
import java.util.Collection;

import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;
import com.gsoft.framework.core.service.BaseManager;

import ${modelPath}.${modelClassName};

public interface ${modelClassName}Manager extends <#if supportWorkflow==true>Workflow</#if>BaseManager<#if supportWorkflow==true><${modelClassName}></#if>{

    /**
     * 查询列表
     */
    public List<${modelClassName}> get${modelClassName}s() throws BusException;
    
    /**
     * 条件查询列表
     */
    public List<${modelClassName}> get${modelClassName}s(Collection<Condition> conditions,Collection<Order> orders) throws BusException;
    /**
     * 根据主键查询
     */
    public ${modelClassName} get${modelClassName}(${idType} id) throws BusException;
    <#if hasSet??>
    /**
	 * 根据主键查询对象，并且加载参数数组中描述的延迟加载对象
	 */
	public ${modelClassName} getInitialize${modelClassName}(String id,String[] cAttributes) throws BusException;
	
	<#list setAttributes as setAttribute>
	/**
	 * 根据主键查询对象，并且加载集合属性${setAttribute.name}集合
	 */
	public List get${modelClassName}${setAttribute.FUName}(${idType} id) throws BusException;
	
	</#list>
	
	</#if>
	/**
	 * 分页查询用户
	 * @return 分页对象
	 */
	public PagerRecords getPager${modelClassName}s(Pager pager,//分页条件
			Collection<Condition> conditions,//查询条件
			Collection<Order> orders) throws BusException;
    /**
     * 保存并返回对象
     */
    public ${modelClassName} save${modelClassName}(${modelClassName} o) throws BusException;

    /**
     * 删除对象
     */
    public void remove${modelClassName}(${idType} id) throws BusException;
    
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void remove${modelClassName}s(String[] ids) throws BusException;
    
     /**
     * 主键是否已经使用
     * @param ids
     */
    public boolean exsit${modelClassName}(String id) throws BusException;
	/**
     * 属性值是否已经使用
     * @param ids
     */
	public boolean exsit${modelClassName}(String propertyName,Object value) throws BusException;
}
