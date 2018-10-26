package com.cyb.tree;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年10月22日
 */
class ProductGroupModel{
    Long id;
	String groupName;
	Long parentId;
	boolean isTable;
	ProductGroupModel nodes;
	int level;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public boolean isTable() {
		return isTable;
	}
	public void setTable(boolean isTable) {
		this.isTable = isTable;
	}
	public ProductGroupModel getNodes() {
		return nodes;
	}
	public void setNodes(ProductGroupModel nodes) {
		this.nodes = nodes;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}

public class FanXiang {
	Log log = LogFactory.getLog(FanXiang.class);
	/**
	 * 传入表对应的tableList，返回这些表的直接上级组对应的parentList。
	 * @param tableModelList, 即tableList
	 * @param allProductGroupModel, 即groupList对应的哈希Map形式结构
	 */
	    @SuppressWarnings("unused")
		private List<ProductGroupModel> getParentsOfTable(List<ProductGroupModel> tableModelList, 
	                    Map<Long, ProductGroupModel> allProductGroupMap) {
	        List<ProductGroupModel> parentsModelOfTableList = new ArrayList<ProductGroupModel>();
	        Long parentId;
	        for (ProductGroupModel pgmTable : tableModelList) {
	            parentId = pgmTable.getParentId();
	            ProductGroupModel parentModel = allProductGroupMap.get(parentId);
	            if (parentModel != null) {
	                parentsModelOfTableList.add(parentModel);
	                allProductGroupMap.remove(parentId); //避免重复
	            }
	        }
	        return parentsModelOfTableList;
	    }
	    /**
	     * 过滤所有的空组group，将所有存在table的group挑出来并返回，摈弃所有底下没有table的group(无论是直接还是间接下面)。
	     * @param parentModelList 要显示的table对应的List<ProductGroupModel>
	     * @param resultList 返回过滤出来的List<group>
	     * @param resultMap  已经过滤出来的HashMap<Long, ProductGroupModel>
	     * @param allProductGroupModelMap 所有的group节点
	    * @return 返回resultList
	     */
	    @SuppressWarnings("unused")
		private List<ProductGroupModel> filterParentGroupModel(List<ProductGroupModel> parentModelList,
	                ArrayList<ProductGroupModel> resultList, HashMap<Long, ProductGroupModel> resultMap,
	                Map<Long, ProductGroupModel> allProductGroupModelMap) {
	            if (parentModelList == null || parentModelList.size() == 0) { // 当没有父节点时，就返回结果集
	                return resultList;
	            }
	            
	            // 重新创建父节点集合
	            List<ProductGroupModel> newParentModelList = new ArrayList<ProductGroupModel>();
	            // 遍历parentModelList
	            for (ProductGroupModel pgm : parentModelList) {
	                Long id = pgm.getId();
	                Long parent_id = pgm.getParentId();
	                
	                //已经过滤出来的group节点不存在，则添加
	                if (!resultMap.containsKey(id)) { //只处理组
	                    newParentModelList.add(pgm); //添加到父节点
	                    resultMap.put(id, pgm); //添加到已被过滤的map中
	                    allProductGroupModelMap.remove(id); // 溢出总集合中的元素
	                    resultList.add(pgm);
	                }
	                
	                // 找出本节点的父节点并添加到newParentModelList父节点集合中，并移除集合中相应的元素
	                if (parent_id != null && !"".equals(parent_id)) {
	                    ProductGroupModel parentModel = allProductGroupModelMap.get(parent_id);
	                    if (parentModel != null) {
	                        newParentModelList.add(parentModel);
	                        allProductGroupModelMap.remove(parent_id);
	                    }
	                }
	            }
	            //递归调用
	            filterParentGroupModel(newParentModelList, resultList, resultMap, allProductGroupModelMap);
	            
	            return resultList;
	        }
	    /**
	     * 传入表对应的List<ProductGroupModel>和组对应的List<ProductGroupModel>，将其转化为树结构，并返回。其中组已经经过处理，不含有空组。
	     * @param groupModelList 组对应的List<ProductGroupModel>
	     * @param tableModelList 表对应的List<ProductGroupModel>
	     * @return List<ProductGroupModel>树结构，其中应当只有一个根节点
	     */
	       private List<ProductGroupModel> buildUpToTree(List<ProductGroupModel> groupModelList, List<ProductGroupModel> tableModelList, Long tableId) {
	           groupModelList.addAll(tableModelList); //将表和组放到一起
	           List<ProductGroupModel> rootNodes = new ArrayList<ProductGroupModel>(); // 存放当前allProductGroupModelList中的根节点
	           List<ProductGroupModel> notRootNodes = new ArrayList<ProductGroupModel>(); //存放非根节点
	           // 找出根节点
	           if (groupModelList != null && groupModelList.size() > 0) {
	               for (ProductGroupModel pgm : groupModelList) {
	                   if (pgm == null) continue;
	                   /*if (!pgm.isTable() && pgm.getId() == Long.valueOf(pgm.getCpId())) { // 判断是否为根节点
	                       rootNodes.add(pgm);
	                   } else {
	                       notRootNodes.add(pgm);
	                   }*/
	               }
	           }
	           //递归获取所有子节点
	           if (rootNodes.size() > 0) {
	               for (ProductGroupModel pgm : rootNodes) { // 遍历根节点。size应该要为1
	                   pgm.setTable(false);
	                   pgm.setLevel(0);
	                   //pgm.setNodes(getChildTreeData(notRootNodes, pgm.getId(), 0, tableId));
	               }
	           }
	           return rootNodes;
	       }
	       /**
	        * 迭代生成树方法。传入一个List和id，从List中找到id对应组的直接下层，并迭代调用
	        * @param childList
	        * @param id
	        * @return
	        */
	       private List<ProductGroupModel> getChildTreeData(List<ProductGroupModel> childList, Long id, int level, Long tableId) {
	           List<ProductGroupModel> parentNodes = new ArrayList<ProductGroupModel>();
	           List<ProductGroupModel> childNodes = new ArrayList<ProductGroupModel>();
	           if (childList != null && childList.size() > 0) { //找出当前的根节点和非根节点
	               for (ProductGroupModel pgm : childList) {
	                   // 找出当前childList中的根节点
	                   if (pgm.getParentId().toString().equals(id.toString())) {
	                       parentNodes.add(pgm);
	                   } else {
	                       childNodes.add(pgm);
	                   }
	               }
	           }
	           
	           // 给parentNodes赋予子节点
	           if (parentNodes.size() > 0) {
	               //排序
	               //parentNodes.sort(modelComparator);
	               int levelTemp = ++level;
	               for (ProductGroupModel pgm : parentNodes) {
	                   List<ProductGroupModel> nodes;
	                   pgm.setLevel(levelTemp);
	                   // 定位table
	                   if (pgm.isTable()) { //如果是表
	                       nodes = null;
	                   } else {
	                       nodes = getChildTreeData(childNodes, pgm.getId(), levelTemp, tableId);
	                       pgm.setTable(false);
	                   }
	                   // 递归查询子节点
	                   //pgm.setNodes(nodes);
	                   
	               }
	           }
	           
	           return parentNodes;
	       }
}
