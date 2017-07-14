package com.cyb.comconection;

import java.sql.Connection;

public class ChildConnection extends ConnectionExUtils<Bean>{

	public ChildConnection(Connection conn) {
		super(conn);
	}
}
