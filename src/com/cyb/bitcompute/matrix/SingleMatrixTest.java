package com.cyb.bitcompute.matrix;

import org.ujmp.core.DenseMatrix;
import org.ujmp.core.Matrix;
import org.ujmp.core.calculation.Calculation;

public class SingleMatrixTest {
	static Matrix dense = DenseMatrix.Factory.zeros(4, 4);
	public static void main(String[] args) {
		dense.setAsInt(1,0,0);
		System.out.println(dense);
		int x=0;
		dense.showGUI();
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dense.setAsInt(x++, 1,1);
			dense.shuffle(Calculation.ORIG);
		}
	}
}
