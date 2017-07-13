package com.cyb.bitcompute.matrix;

import java.math.BigDecimal;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.SparseMatrix;
import org.ujmp.core.calculation.Calculation;

public class UJMPStudy {
	public static void main(String[] args) {
		testUJMP();
	}

	public static void testUJMP() {

		// 初始化一个4X4的矩阵
		Matrix dense = DenseMatrix.Factory.zeros(4, 4);
		Matrix dense2 = DenseMatrix.Factory.zeros(4, 4);
		// 输出矩阵的行和列的长度
		System.out.println("dense rowcount colcount " + dense.getRowCount()
				+ "  " + dense.getColumnCount());
		;
		// 利用行和列进行矩阵的赋值
		for (int i = 0; i < dense.getRowCount(); ++i) {
			for (int j = 0; j < dense.getColumnCount(); ++j) {
				// 可以使用setXXX来进行矩阵的赋值，其中第一个参数是值，第二个参数是行，第三个参数是列
				dense.setAsInt((i * j + (int) (Math.pow(i + 1, j))), i, j);
				dense2.setAsInt(i + j, i, j);
			}
		}
		Math.pow(1, 2);
		// 输出矩阵
		System.out.println(dense);
		System.out.println("dense2 \n" + dense2);

		// 初始化一个稀疏矩阵
		Matrix spares = SparseMatrix.Factory.zeros(400, 500);
		// 这里使用另一种方法获取其行和列
		// long[] getSize() 是一个维度为2的矩阵，第一个是行，第二个数是列
		for (int i = 0; i < spares.getSize()[0]; ++i) {
			for (int j = 0; j < spares.getSize()[1]; ++j) {
				spares.setAsBigDecimal(BigDecimal.valueOf(i * j), i, j);
			}
		}
		System.out.println(spares.getSize()[0] + "   " + spares.getSize()[1]);
		// System.out.println("spares Matrix : \n" + spares);

		/*****************************************
		 * 矩阵的运算
		 *****************************************/

		// 转置
		Matrix transpose = dense.transpose();
		System.out.println(transpose);
		// 两个矩阵求和

		Matrix sum = dense.plus(dense2);
		System.out.println("sum \n" + sum);

		// 两个矩阵相减
		Matrix difference = dense.minus(dense2);
		System.out.println("difference \n" + difference);

		// 矩阵相乘
		Matrix matrixProduct = dense.mtimes(dense2);
		System.out.println("matrixProduct\n" + matrixProduct);

		// 矩阵 k*M (K 为常数, M为矩阵)
		Matrix scaled = dense.times(2.0);
		System.out.println("scaled \n" + scaled);

		// 矩阵的逆
		Matrix inverse = dense.inv();
		System.out.println(inverse);

		// 伪逆矩阵 广义逆矩阵
		Matrix pesudoInv = dense.pinv();
		System.out.println(pesudoInv);

		// 求矩阵的行列式
		double determiant = dense.det();
		System.out.println("determiant = " + determiant);

		// 矩阵的奇异值分解
		Matrix[] sigularValueDecompostion = dense.svd();
		for (int i = 0; i < sigularValueDecompostion.length; ++i) {
			System.out.println("sigularValueDecompostion " + i + "= \n"
					+ sigularValueDecompostion[i]);
		}

		// 求矩阵的特征值
		Matrix[] eigenValueDecompostion = dense.eig();
		for (int i = 0; i < eigenValueDecompostion.length; ++i) {
			System.out.println("eigenValueDecompostion " + i + "= \n"
					+ eigenValueDecompostion[i]);
		}

		// 矩阵的LU分解，将矩阵分解成一个上三角矩阵和下三角矩阵的乘积
		Matrix[] luValueDecompostion = dense.lu();
		for (int i = 0; i < luValueDecompostion.length; ++i) {
			System.out.println("luValueDecompostion " + i + "= \n"
					+ luValueDecompostion[i]);
		}

		// qr分解 半正交矩阵与一个上三角矩阵的积，常用来求解线性最小二乘问题
		Matrix[] qrDecomposition = dense.qr();
		for (int i = 0; i < qrDecomposition.length; ++i) {
			System.out.println("qrDecomposition " + i + "= \n"
					+ qrDecomposition[i]);
		}

		// Cholesky分解 对于每一个正定矩阵 Cholesky分解都存在
		Matrix choleskyDecomposition = dense.chol();
		System.out.println("choleskyDecomposition \n" + choleskyDecomposition);
		choleskyDecomposition.showGUI();
		int x=0;
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			choleskyDecomposition.setAsInt(x++, 1,1);
			choleskyDecomposition.shuffle(Calculation.ORIG);
		}

	}
}