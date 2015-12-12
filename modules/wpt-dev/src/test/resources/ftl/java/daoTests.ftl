/**
 * 
 */
package ${package};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.youi.common.test.DaoJUnit4Test;
import org.junit.Test;

import ${daoClassPath};

/**
 * dao测试类
 * @author
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
public class ${modelClassName}DaoTests extends DaoJUnit4Test {
	private ${modelClassName}Dao ${modelName}Dao;

	@Autowired
	public void set${modelClassName}Dao(${modelClassName}Dao ${modelName}Dao) {
		this.${modelName}Dao = ${modelName}Dao;
	}
	
	//@Test
}

