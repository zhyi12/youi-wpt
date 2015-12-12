/**
 * 
 */
package com.gsoft.platform.codegen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.VisitorSupport;

import com.gsoft.framework.util.Dom4jUtils;

/**
 * @author zhyi_12
 *
 */
public class PdmConverterTest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		FileInputStream input = new FileInputStream("E:\\project\\workspace-gsoft\\gsoft-framework\\doc\\db\\design\\esb.pdm");
		Document document = Dom4jUtils.saxParse(input);
		
		document.accept(new VisitorSupport(){
			public void visit(Element node) {
				System.out.println("---:"+node.getName());
		    }
		});
	}

}
