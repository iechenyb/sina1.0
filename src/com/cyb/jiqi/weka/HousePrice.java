package com.cyb.jiqi.weka;
import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cyb.file.FileUtils;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年9月12日
 */
public class HousePrice {
	Log log = LogFactory.getLog(HousePrice.class);
	public static void main(String[] args) throws Exception {
        final String arffTrainData = "house2.arff";
     	String path = FileUtils.getAbsolutePathAtClass(WekaTest.class);
        LinearRegression classifier = trainModel(path+arffTrainData, 5);

        Instance ins = new weka.core.SparseInstance(5);
        ins.setValue(0, 90);
        ins.setValue(1, 2);
        ins.setValue(2, 1);
        ins.setValue(3, 1);
        ins.setValue(4, 1);

        double price = classifier.classifyInstance(ins);
        System.out.println("Price: " + price);
        
        for (double coef : classifier.coefficients()) {
            System.out.println(coef);
        }
    }
	static LinearRegression trainModel(String arffFile, int classIndex) throws Exception {
        File inputFile = new File(arffFile); //训练文件
        ArffLoader loader = new ArffLoader();
        loader.setFile(inputFile);
        Instances insTrain = loader.getDataSet(); // 读入训练文件
        insTrain.setClassIndex(classIndex);
        LinearRegression linear = new LinearRegression();
        linear.buildClassifier(insTrain);//根据训练数据构造分类器
        return linear;
    }
}
