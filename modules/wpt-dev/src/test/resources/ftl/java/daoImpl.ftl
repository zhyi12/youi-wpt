/**
 * 代码声明
 */
package ${package}.hibernate;

import org.springframework.stereotype.Repository;
import com.gsoft.framework.core.dao.hibernate.BaseDaoHibernate;

import ${modelPath}.${modelClassName};
import ${daoClassPath};

@Repository("${modelClassName}Dao")
public class ${modelClassName}DaoHibernate extends 
		BaseDaoHibernate<${modelClassName}, String> implements ${modelClassName}Dao{
	public Class<${modelClassName}> getModelClazz(){
		return ${modelClassName}.class;
	}
}