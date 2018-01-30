package com.cyb.tree;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2018年1月29日
 */
public class DiGuiTree {
	Log log = LogFactory.getLog(DiGuiTree.class);
    public static void main(String[] args) 
    {
        Tree t1=new Tree("01","name1","");
        Tree t2=new Tree("02","name2","01");
        Tree t3=new Tree("03","name3","01");
        Tree t4=new Tree("04","name4","02");
        Tree t5=new Tree("05","name5","02");
        Tree t6=new Tree("06","name6","05");
         
        List<Tree> list=new ArrayList<Tree>();
        //跟顺序没有关系
        list.add(t2);
        list.add(t3);
        list.add(t4);
        list.add(t5);
        list.add(t6);
        list.add(t1);
        //传递根
        showTree(list,t1,0);
    }
    //判断字母还是数字
    public static void showTree(List<Tree> list,Tree tree,int deep)
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
}

class Tree
{
   private String id;
   private String name;
   private String pid;
   public Tree(String id,String name,String pid)
   {
       this.id=id;
       this.name=name;
       this.pid=pid;
   }
   public String getId() {
       return id;
   }
   public String getName() {
       return name;
   }
   public String getPid() {
       return pid;
   }
    
}
