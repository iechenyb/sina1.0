package com.cyb.tree;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONArray;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月29日
 */
public class DiGuiTree {
	Log log = LogFactory.getLog(DiGuiTree.class);
    public static void main(String[] args) 
    {
        TreeNode root=new TreeNode("01","name1","root");
        TreeNode t2=new TreeNode("02","name2","01");
        TreeNode t3=new TreeNode("03","name3","01");
        TreeNode t4=new TreeNode("04","name4","02");
        TreeNode t5=new TreeNode("05","name5","02");
        TreeNode t6=new TreeNode("06","name6","05");
        TreeNode t7=new TreeNode("07","name7","06");
        TreeNode t8=new TreeNode("08","name8","06");
        TreeNode t9=new TreeNode("09","name9","08");
        List<TreeNode> list=new ArrayList<TreeNode>();
        //跟顺序没有关系
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t7);
        list.add(t8);
        list.add(t9);
        list.add(root);
        //传递根
       // showTree(list,root,0);
        madeTree(list,root);
        System.out.println(JSONArray.fromObject(root));
    }
    //判断字母还是数字
    public static void showTree(List<TreeNode> list,TreeNode tree,int deep)
    {
        String str="";
        for(int i=0;i<deep;i++)
        {
            str+="\t";
        }
        System.out.println(str+""+tree.getName());
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).getPid().equals(tree.getId()))
            {
                deep++;
                showTree(list,list.get(i),deep);
                deep--;
            }
             
        }
    }
    //判断字母还是数字
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 首次传递根节点和所有的节点数据
     *第二次传递 当前节点和所有的节点数据<br>
     *创建时间: 2017年7月15日
     *@param list
     *@param tree
     */
    public static void madeTree(List<TreeNode> list,TreeNode tree)
    {
        Iterator<TreeNode> ter = list.iterator();
        TreeNode cur = null;
        while(ter.hasNext())
        {
        	cur = ter.next();
            if(cur.getPid().equals(tree.getId()))
            {
                tree.getChildren().add(cur);
                ter.remove();//提高效率，移除已处理节点
                System.out.println("根节点剩余："+list.size());
            }
        }
        madeTree(list,tree.getChildren());
    }
    /**
     * 
     *作者 : iechenyb<br>
     *方法描述: 说点啥<br>
     *创建时间: 2017年7月15日
     *@param list 所有节点
     *@param listChild //第n层节点
     */
	public static void madeTree(List<TreeNode> list,List<TreeNode> listChild){
		Iterator<TreeNode> ter = listChild.iterator();//第n层节点
		TreeNode cur = null;
		TreeNode allCur = null;
		Iterator<TreeNode> all = null;
        while(ter.hasNext())//遍历当前层次节点
        {
        	cur = ter.next();
        	all = list.iterator();//第n层节点
        	while(all.hasNext()){
        		allCur = all.next();
        		if(allCur.getPid().equals(cur.getId())){
        			 cur.getChildren().add(allCur);
        			 all.remove();
        			 System.out.println("根节点剩余："+list.size());
        		 }
        	}
        	/*for(int i=0;i<list.size();i++){
        		if(list.get(i).getPid().equals(cur.getId())){
        		  cur.getChildren().add(list.get(i));
        		}
        	}*/
        	madeTree(list,cur.getChildren());
        }
	}
}

