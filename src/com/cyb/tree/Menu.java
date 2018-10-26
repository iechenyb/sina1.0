package com.cyb.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @作者:iechenyb</br>
	 * @功能描述：</br>
	 * @创建时间：2016年11月3日下午1:24:22</br>
	 */
	private Integer id;
	private String menuName;//类型名称
	private String url;//类型编号
	private Integer parentId;//父类型编号
	private Boolean isLeaf;//是否叶子节点
	private String menuDesc;//类型描述
	private Long createTime;//创建时间
	private String cretatePerson;//创建人员
	private Long modifyTime;//修改时间
	private String modifyPerson;//修改人员
	private Integer ordor ;//菜单排序
	private List<Menu> child;
	public Menu(Integer id,String menuName,Integer parentId,Boolean isLeaf){
		this.id = id;
		this.menuName = menuName;
		this.parentId = parentId;
		this.isLeaf = isLeaf;
		child = new ArrayList<Menu>();
	}
	public Menu() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getMenuDesc() {
		return menuDesc;
	}
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getCretatePerson() {
		return cretatePerson;
	}
	public void setCretatePerson(String cretatePerson) {
		this.cretatePerson = cretatePerson;
	}
	public Long getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyPerson() {
		return modifyPerson;
	}
	public void setModifyPerson(String modifyPerson) {
		this.modifyPerson = modifyPerson;
	}
	public Integer getOrdor() {
		return ordor;
	}
	public void setOrdor(Integer ordor) {
		this.ordor = ordor;
	}
	public String toString(){
		return menuName;
	}
	public List<Menu> getChild() {
		return child;
	}
	public void setChild(List<Menu> child) {
		this.child = child;
	}
	
}
