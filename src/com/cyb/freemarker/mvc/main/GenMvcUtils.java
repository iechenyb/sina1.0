package com.cyb.freemarker.mvc.main;

import com.cyb.freemarker.mvc.controller.ControllerGenerator;
import com.cyb.freemarker.mvc.dao.DaoGenerator;
import com.cyb.freemarker.mvc.po.PoGenerator;
import com.cyb.freemarker.mvc.service.ServiceGenerator;

public class GenMvcUtils {
	public static void main(String[] args) throws Exception {
		PoGenerator.main(new String[]{});
		DaoGenerator.main(new String[]{});
		ServiceGenerator.main(new String[]{});
		ControllerGenerator.main(new String[]{});
	}
}
