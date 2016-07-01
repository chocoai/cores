package com.lanen.util;

import java.util.Date;

import net.sf.jasperreports.engine.JRAbstractScriptlet;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.fill.JRFillField;

/**
 * 临检  检测数据  报表 用的  变量
 * @author Administrator
 *
 */
public class ClinicalTestDataReportScriptlet extends JRAbstractScriptlet{

	@Override
	public void afterColumnInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterDetailEval() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterGroupInit(String arg0) throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterPageInit() throws JRScriptletException {
		
		String title ="检测结果";
		
		setVariableValue("title", title);
		
		
	}

	@Override
	public void afterReportInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeColumnInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeDetailEval() throws JRScriptletException {
		String myauthor="小鱼";
		Date   mydate= new Date();
		setFieldValue("myauthor", myauthor);
		setFieldValue("mydate", mydate);
		
	}

	@Override
	public void beforeGroupInit(String arg0) throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforePageInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeReportInit() throws JRScriptletException {
		// TODO Auto-generated method stub
		
	}
	public void setFieldValue(String fieldName, Object value) throws JRScriptletException
    {
        JRFillField field = (JRFillField)this.fieldsMap.get(fieldName);
        if (field == null)
        {
            throw new JRScriptletException("FieldName not found : " + fieldName);
        }
        
        field.setValue(value);
    }
}
