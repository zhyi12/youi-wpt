/**
 * 
 */
package ${package}.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gsoft.framework.core.web.annotation.DataInfo;
import com.gsoft.framework.core.web.controller.BaseDataController;
import com.gsoft.framework.core.web.controller.RequestDataIn;
import com.gsoft.framework.core.web.view.DataModelAndView;
import com.gsoft.framework.core.web.view.Message;

import ${modelClassPath};
import ${serviceClassPath};
/**
 * @author 
 *
 */

@Controller
@RequestMapping("/${moduleName}/${modelName}")
public class ${modelClassName}Data extends BaseDataController{

	@Autowired
	private ${modelClassName}Manager ${modelName}Manager;
	
	
	/**
	 * 主键查询${modelDescription}
	 */
	@RequestMapping(value="/get${modelClassName}.json")
	@DataInfo(functionId="${modelId}01",text="主键查询${modelDescription}")
	public DataModelAndView get${modelClassName}(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("${modelName}Id") String ${modelName}Id){
		return new DataModelAndView(${modelName}Manager.get${modelClassName}(${modelName}Id));
	}
	
	/**
	 * 通用${modelDescription}查询
	 */
	@RequestMapping(value="/get${modelClassName}s.json")
	@DataInfo(functionId="${modelId}02",text="通用${modelDescription}查询")
	public DataModelAndView get${modelClassName}s(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		DataIn<${modelClassName}> dataIn,
    		@ModelAttribute ${modelClassName} ${modelName},
    		BindingResult result){
		//返回并包装数据
		return new DataModelAndView(${modelName}Manager.get${modelClassName}s(
				dataIn.getConditions(${modelName}, result), 
				dataIn.getOrders()));
	}
	
	/**
	 * 分页查询${modelDescription}
	 */
	@RequestMapping(value="/getPager${modelClassName}s.json")
	@DataInfo(functionId="${modelId}03",text="分页查询${modelDescription}")
	public DataModelAndView getPager${modelClassName}s(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		DataIn<${modelClassName}> dataIn,
    		@ModelAttribute ${modelClassName} ${modelName},
    		BindingResult result){
		//返回并包装数据
		return new DataModelAndView(${modelName}Manager.getPager${modelClassName}s(
				dataIn.getPager(),
				dataIn.getConditions(${modelName}, result), 
				dataIn.getOrders()));
	}
	
	/**
	 * 保存${modelDescription}
	 */
	@RequestMapping(value="/save${modelClassName}.json")
	@DataInfo(functionId="${modelId}04",text="保存${modelDescription}",log=true)
	public DataModelAndView save${modelClassName}(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		DataIn<${modelClassName}> dataIn,
    		@ModelAttribute ${modelClassName} ${modelName},
    		BindingResult result){
		${modelClassName} web${modelClassName} =dataIn.getDomain(${modelName}, result);//TODO 使用泛型
		${modelClassName} save${modelClassName} = null;
		if(web${modelClassName}!=null){
			save${modelClassName} = ${modelName}Manager.save${modelClassName}((${modelClassName})web${modelClassName});
		}
		return new DataModelAndView(save${modelClassName});
	}
	
	/**
	 * 主键删除${modelDescription}
	 */
	@RequestMapping(value="/remove${modelClassName}.json")
	@DataInfo(functionId="${modelId}05",text="主键删除${modelDescription}",log=true)
	public DataModelAndView remove${modelClassName}(
    		HttpServletRequest request,
    		HttpServletResponse response,
    		@RequestParam("${modelName}Id") String ${modelName}Id){
		${modelName}Manager.remove${modelClassName}(${modelName}Id);
		return new DataModelAndView(new Message(SUCCESS_CODE,"删除成功"));
	}
	
	/**
	 * 主键集合删除${modelDescription}
	 */
	@RequestMapping(value="/remove${modelClassName}s.json")
	@DataInfo(functionId="${modelId}06",text="主键集合删除${modelDescription}",log=true)
	public DataModelAndView remove${modelClassName}s(
    		HttpServletRequest request,
    		HttpServletResponse response){
		${modelName}Manager.remove${modelClassName}s(request.getParameterValues("${modelName}Id"));
		return new DataModelAndView(new Message(SUCCESS_CODE,"集合删除成功"));
	}
}
