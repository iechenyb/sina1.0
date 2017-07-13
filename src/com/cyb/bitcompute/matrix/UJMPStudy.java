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

		// ��ʼ��һ��4X4�ľ���
		Matrix dense = DenseMatrix.Factory.zeros(4, 4);
		Matrix dense2 = DenseMatrix.Factory.zeros(4, 4);
		// ���������к��еĳ���
		System.out.println("dense rowcount colcount " + dense.getRowCount()
				+ "  " + dense.getColumnCount());
		;
		// �����к��н��о���ĸ�ֵ
		for (int i = 0; i < dense.getRowCount(); ++i) {
			for (int j = 0; j < dense.getColumnCount(); ++j) {
				// ����ʹ��setXXX�����о���ĸ�ֵ�����е�һ��������ֵ���ڶ����������У���������������
				dense.setAsInt((i * j + (int) (Math.pow(i + 1, j))), i, j);
				dense2.setAsInt(i + j, i, j);
			}
		}
		Math.pow(1, 2);
		// �������
		System.out.println(dense);
		System.out.println("dense2 \n" + dense2);

		// ��ʼ��һ��ϡ�����
		Matrix spares = SparseMatrix.Factory.zeros(400, 500);
		// ����ʹ����һ�ַ�����ȡ���к���
		// long[] getSize() ��һ��ά��Ϊ2�ľ��󣬵�һ�����У��ڶ���������
		for (int i = 0; i < spares.getSize()[0]; ++i) {
			for (int j = 0; j < spares.getSize()[1]; ++j) {
				spares.setAsBigDecimal(BigDecimal.valueOf(i * j), i, j);
			}
		}
		System.out.println(spares.getSize()[0] + "   " + spares.getSize()[1]);
		// System.out.println("spares Matrix : \n" + spares);

		/*****************************************
		 * ���������
		 *****************************************/

		// ת��
		Matrix transpose = dense.transpose();
		System.out.println(transpose);
		// �����������

		Matrix sum = dense.plus(dense2);
		System.out.println("sum \n" + sum);

		// �����������
		Matrix difference = dense.minus(dense2);
		System.out.println("difference \n" + difference);

		// �������
		Matrix matrixProduct = dense.mtimes(dense2);
		System.out.println("matrixProduct\n" + matrixProduct);

		// ���� k*M (K Ϊ����, MΪ����)
		Matrix scaled = dense.times(2.0);
		System.out.println("scaled \n" + scaled);

		// �������
		Matrix inverse = dense.inv();
		System.out.println(inverse);

		// α����� ���������
		Matrix pesudoInv = dense.pinv();
		System.out.println(pesudoInv);

		// ����������ʽ
		double determiant = dense.det();
		System.out.println("determiant = " + determiant);

		// ���������ֵ�ֽ�
		Matrix[] sigularValueDecompostion = dense.svd();
		for (int i = 0; i < sigularValueDecompostion.length; ++i) {
			System.out.println("sigularValueDecompostion " + i + "= \n"
					+ sigularValueDecompostion[i]);
		}

		// ����������ֵ
		Matrix[] eigenValueDecompostion = dense.eig();
		for (int i = 0; i < eigenValueDecompostion.length; ++i) {
			System.out.println("eigenValueDecompostion " + i + "= \n"
					+ eigenValueDecompostion[i]);
		}

		// �����LU�ֽ⣬������ֽ��һ�������Ǿ���������Ǿ���ĳ˻�
		Matrix[] luValueDecompostion = dense.lu();
		for (int i = 0; i < luValueDecompostion.length; ++i) {
			System.out.println("luValueDecompostion " + i + "= \n"
					+ luValueDecompostion[i]);
		}

		// qr�ֽ� ������������һ�������Ǿ���Ļ������������������С��������
		Matrix[] qrDecomposition = dense.qr();
		for (int i = 0; i < qrDecomposition.length; ++i) {
			System.out.println("qrDecomposition " + i + "= \n"
					+ qrDecomposition[i]);
		}

		// Cholesky�ֽ� ����ÿһ���������� Cholesky�ֽⶼ����
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