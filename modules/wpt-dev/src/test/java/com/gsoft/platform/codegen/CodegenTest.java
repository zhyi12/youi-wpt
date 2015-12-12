/**
 * 
 */
package com.gsoft.platform.codegen;

import org.dom4j.Document;

import com.gsoft.framework.util.Dom4jUtils;
import com.gsoft.platform.codegen.model.ModelFactory;
import com.gsoft.platform.codegen.pdm.Pdm2ModelXml;

/**
 * @author zhyi_12
 *
 */
public class CodegenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String tablePrefix = "youi";
		Document doc = Dom4jUtils.saxParse("E:\\PROJECT\\workspace-cas\\gsoft-framework\\doc\\db\\design\\weixin.pdm");
		// TODO Auto-generated method stub
		Pdm2ModelXml pdm2ModelXml = new Pdm2ModelXml(doc,tablePrefix);
		Document modelXml = pdm2ModelXml.getModelDoc();
		
//		ByteArrayOutputStream os = new ByteArrayOutputStream();
//		Dom4jUtils.formatXml(modelXml, os, "UTF-8");
//		try {
//			System.out.println(new String(os.toByteArray(),"UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		
		ModelFactory.getInstance().registerModels(modelXml);
		
		GenerateEngine generateEngine = new GenerateEngine();
		
		GenerateConfig config = new GenerateConfig();
		config.setSourceHome("E://PROJECT//workspace-weixin//youi-wpt//wars//wpt-web");
		config.setPackagePrefix("com.gsoft.weixin");
		generateEngine.generate("wxmessage", config );
	}

}
