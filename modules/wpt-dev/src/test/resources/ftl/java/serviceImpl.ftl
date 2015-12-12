/**
 * 代码声明
 */
package ${package}.impl;

import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gsoft.framework.core.exception.BusException;
import com.gsoft.framework.core.orm.Condition;
//import com.gsoft.framework.core.orm.ConditionFactory;
import com.gsoft.framework.core.orm.Order;
import com.gsoft.framework.core.orm.Pager;
import com.gsoft.framework.core.orm.PagerRecords;

import com.gsoft.framework.esb.annotation.*;

import com.gsoft.framework.core.service.impl.BaseManagerImpl;

import ${modelPath}.${modelClassName};
import ${daoClassPath};
import ${serviceClassPath};

@Service("${modelName}Manager")
@Transactional
public class ${modelClassName}ManagerImpl extends <#if supportWorkflow==true>Workflow</#if>BaseManagerImpl<#if supportWorkflow==true><${modelClassName}></#if> implements ${modelClassName}Manager{
	@Autowired
	private ${daoClassName} ${daoName};
	
    /**
     * 查询列表
     */
    public List<${modelClassName}> get${modelClassName}s() throws BusException{
    	return ${daoName}.getAll();
    }
     /**
     * 条件查询列表
     */
    @EsbServiceMapping
    public List<${modelClassName}> get${modelClassName}s(
    	@ConditionCollection(domainClazz=${modelClassName}.class) Collection<Condition> conditions,
    	@OrderCollection Collection<Order> orders) throws BusException{
    	return ${daoName}.commonQuery(conditions, orders);
    }
    /**
     * 根据主键查询
     */
    @EsbServiceMapping
    public ${modelClassName} get${modelClassName}(@ServiceParam(name="${idAttribute.name}") ${idType} id)  throws BusException{
    	return ${daoName}.get(id);
    }
    <#if hasSet??>
    public ${modelClassName} getInitialize${modelClassName}(${idType} id,String[] cAttributes){
    	return ${daoName}.getInitializeObject(id,cAttributes);
    }
	
	<#list setAttributes as setAttribute>
	public List get${modelClassName}${setAttribute.FUName}(${idType} id)  throws BusException{
		List list = new ArrayList();
		${modelClassName} model = (${modelClassName})${daoName}.getInitializeObject(id,new String[]{"${setAttribute.name}"});
		list.addAll(model.get${setAttribute.FUName}());
		return list;
	}
	
	</#list>
	</#if>
	
	@EsbServiceMapping
	public PagerRecords getPager${modelClassName}s(Pager pager,//分页条件
			@ConditionCollection(domainClazz=${modelClassName}.class) Collection<Condition> conditions,//查询条件
			@OrderCollection Collection<Order> orders)  throws BusException{
		PagerRecords pagerRecords = ${daoName}.findByPager(pager, conditions, orders);
		return pagerRecords;
	}
    /**
     * 保存对象
     */
    @EsbServiceMapping
    public ${modelClassName} save${modelClassName}(${modelClassName} o) throws BusException{
//    	String ${modelName}Id = o.get${modelClassName}Id();
//    	boolean isUpdate = StringUtils.isNotEmpty(${modelName}Id);
//    	if(isUpdate){//修改
//    	
//    	}else{//新增
//    		
//    	}
    	return ${daoName}.save(o);
    }

    /**
     * 删除对象
     */
    @EsbServiceMapping
    public void remove${modelClassName}(@ServiceParam(name="${idAttribute.name}") ${idType} id) throws BusException{
    	${daoName}.remove(id);
    }
    /**
     * 根据主键集合删除对象
     * @param ids
     */
    public void remove${modelClassName}s(@ServiceParam(name="${idAttribute.name}") String[] ids)  throws BusException{
   		for(String id:ids){
    		remove${modelClassName}(id);
    	}
    }
    
    @EsbServiceMapping
    public boolean exsit${modelClassName}(@ServiceParam(name="${idAttribute.name}") String id)  throws BusException{
		return ${daoName}.exists(id);
	}
    
    public boolean exsit${modelClassName}(String propertyName,Object value) throws BusException{
		return ${daoName}.exists(propertyName,value);
	}
	<#if supportWorkflow==true>
	/**
	 * 获得工作流相关实体
	 */
	public ${modelClassName} getWorkflowDomain(String workflowId) {
		return ${daoName}.getObjectByUniqueProperty("workflowId", workflowId);
	}
	/**
	 * 保存工作流相关实体
	 */
	protected ${modelClassName} saveWorkflowDomain(${modelClassName} ${modelName}) {
		return save${modelClassName}(${modelName});
	}</#if>
}
