package com.cyb.collection.sync;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年2月12日
 */
public class SyncCollectionStudy {
	Log log = LogFactory.getLog(SyncCollectionStudy.class);
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public static void main(String[] args) {
		 Collection c=Collections.synchronizedCollection(new ArrayList());
		 List list=Collections.synchronizedList(new ArrayList());
		 Set s=Collections.synchronizedSet(new HashSet());
		 Map m=Collections.synchronizedMap(new HashMap());
		 Collections.synchronizedSortedMap(null);
		 Collections.synchronizedSortedSet(null);
	}
}
