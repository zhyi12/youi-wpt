/**
 * 
 */
package ${package}.data;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.youi.common.test.DataJUnit4Tests;

/**
 * @author 
 *
 */
@ContextConfiguration(locations={"classpath:applicationContext-test.xml"})
public class ${modelClassName}DataTests extends DataJUnit4Tests{
	@Autowired
	private ${modelClassName}Data ${modelName}Data;
	/**
	 * 测试主键查询${modelDescription}
	 * 输入：主键
	 * 输出：对象
	 */
	@Test
	public void get${modelClassName}(){
		
	}
	
	/**
	 * 测试查询${modelDescription}列表
	 * 输入：
	 * 输出：无过滤的数据列表
	 */
	@Test
	public void get${modelClassName}s(){
		//TODO
	}
	/**
	 * 测试分页查询${modelDescription}
	 * 输入：分页条件、查询条件
	 * 输出：数据列表
	 */
	@Test
	public void getPager${modelClassName}s(){
		
	}
	/**
	 * 测试保存${modelDescription}
	 * 输入：实体值对象
	 * 输出：持久化实体
	 */
	@Test
	public void save${modelClassName}(){
		
	}
	/**
	 * 测试主键删除${modelDescription}
	 * 输入：主键
	 * 输出：删除提示信息
	 */
	@Test
	public void remove${modelClassName}(){
		
	}
	/**
	 * 测试主键集删除${modelDescription}
	 * 输入：主键集合
	 * 输出：删除提示信息
	 */
	@Test
	public void remove${modelClassName}s(){
		
	}
	<#list attributes as attribute>
	<#if attribute.unique??>
	/**
	 * 测试是否已经有使用了参数${attribute.name}值的对象
	 * 输入：${attribute.name}值
	 * 输出：是否存在的信息
	 */
	@Test
	public void exsit${modelClassName}By${attribute.FUName}(){
		
	}
	</#if></#list>
}
