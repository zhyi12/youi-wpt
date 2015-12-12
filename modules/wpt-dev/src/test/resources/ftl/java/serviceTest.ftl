/**
 * 代码声明
 */
package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import org.youi.common.test.ServiceJUnit4Test;
import org.junit.Test;

import ${serviceClassPath};

@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
public class ${modelClassName}ManagerTests extends ServiceJUnit4Test{
	private ${modelClassName}Manager ${modelName}Manager;
	
	@Autowired
	public void set${modelClassName}Manager(${modelClassName}Manager ${modelName}Manager){
		this.${modelName}Manager = ${modelName}Manager;
	}
    
    /**
     * 查询列表
     */
    @Test
    public void testGetAll${modelClassName}s(){
    	
    }
     /**
     * 条件查询列表
     */
    @Test
    public void testGet${modelClassName}s(){
    	
    }
    /**
     * 根据主键查询
     */
    @Test
    public void testGet${modelClassName}(){
    	
    }
    <#if hasSet??>
    @Test
    public void testGetInitialize${modelClassName}(){
    	
    }
	
	<#list setAttributes as setAttribute>
	@Test
	public void testGet${modelClassName}${setAttribute.FUName}(){
		
	}
	
	</#list>
	</#if>
	@Test
	public void testGetPager${modelClassName}s(){
		
	}
    /**
     * 保存对象
     */
    @Test
    public void testSave${modelClassName}(){
    	
    }

    /**
     * 删除对象
     */
    @Test
    public void testRemove${modelClassName}(){
    	
    }
    /**
     * 根据主键集合删除对象
     */
    @Test
    public void testRemove${modelClassName}s(){
   	
    }
    @Test
    public void testExsit${modelClassName}(){
	
	}
}
