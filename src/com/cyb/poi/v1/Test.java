package com.cyb.poi.v1;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 作者 : iechenyb<br>
 * 类描述: 说点啥<br>
 * 创建时间: 2018年10月26日
 */
public class Test {
	Log log = LogFactory.getLog(Test.class);

	public static void main(String[] args) throws Exception {

		List<Column> listColumn = new ArrayList<Column>();// 用于存放第一行单元格

		List<Column> list2 = new ArrayList<Column>();// 用于存放第一列第二行的单元格
		list2.add(new Column("标题1", "col1"));// 创建一列，value1 表示这一列需要导出字段的值
		list2.add(new Column("标题2", "col2"));
		list2.add(new Column("标题3", "col3"));
		list2.add(new Column("标题4", "col4"));
		list2.add(new Column("标题5", "col5"));

		List<Column> list3 = new ArrayList<Column>();// 用于存放第二列第二行的单元格
		list3.add(new Column("标题6", "col6"));
		list3.add(new Column("标题7", "col7"));
		list3.add(new Column("标题8", "col8"));
		list3.add(new Column("标题9", "col9"));

		Column c1 = new Column("大标题1", null);// 创建第一行大标题,大标题的fieldName 为 null
		c1.setListColumn(list2);// 所属c1的单元格都赋值给c1
		
		Column c2 = new Column("大标题2", null);
		c2.setListColumn(list3);
		
		Column c3 = new Column("普通标题3", "ccol3");
		Column c0 = new Column("普通标题0", "ccol0");
		
		listColumn.add(c0);
		listColumn.add(c1);
		listColumn.add(c2);
		listColumn.add(c3);

		List<ValueObj> valueList = new ArrayList<ValueObj>();// 需要导出的数据
		for(int i=0;i<30;i++){
			ValueObj obj = new ValueObj();
			obj.setCcol0("ccol0-"+i);
			obj.setCcol3("ccol3-"+i);
			obj.setCol1("col1"+i);
			obj.setCol2("col2"+i);
			obj.setCol3("col3"+i);
			obj.setCol4("col4"+i);
			obj.setCol5("col5"+i);
			obj.setCol6("col6"+i);
			obj.setCol7("col7"+i);
			obj.setCol8("col8"+i);
			obj.setCol9("col9"+i);
			valueList.add(obj);
		}
		TableExcel<ValueObj> ta = new TableExcel<ValueObj>("表格", 15, 20);
		ta.exportExcel(listColumn, valueList, "D://data/poi/outExcel.xls");

	}
	
}
