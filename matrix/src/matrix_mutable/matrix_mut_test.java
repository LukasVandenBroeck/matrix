package matrix_mutable;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class matrix_mut_test {

	@Test
	void test() {
		int a = 2;
		double[] elements = {1.2, 3.4, 5.6, 3.8, 6.3, 0.3};
		matrix_mut test = new matrix_mut(a,3,elements);
		assert test.getNbOfRows() == 2;
		a = 3;
		assert test.getNbOfRows() == 2;
		
		assert test.getElementAt(1, 1) == 1.2;
		assert test.getElementAt(1, 2) == 3.4;
		assert test.getElementAt(1, 3) == 5.6;
		assert test.getElementAt(2, 1) == 3.8;
		assert test.getElementAt(2, 2) == 6.3;
		assert test.getElementAt(2, 3) == 0.3;
		
		assertArrayEquals(test.getElementsRowmajor(), elements);
		assert test.nbOfElements() == 6;
		
		double[] rowMaj = new double[] {1.2, 3.8, 3.4, 6.3, 5.6, 0.3};
		assertArrayEquals(test.getElementsColmajor(), rowMaj);
		
		double[][] rowAr = new double[][] {{1.2, 3.4, 5.6}, {3.8, 6.3, 0.3}};
		assertArrayEquals(test.getElementsRowArray(), rowAr);

		double[] factorElements = new double[] {6, 17, 28, 19, 31.5, 1.5};
		double[] plusElements = new double[] {2.4, 4.6, 6.8, 5, 7.5, 1.5};
		
		test.scale(5);
		assertArrayEquals(test.getElementsRowmajor(), factorElements);
		double[] elements2 = {1.2, 3.4, 5.6, 3.8, 6.3, 0.3};
		test = new matrix_mut(2,3,elements2);
		
		test.add(1.2);
		assertArrayEquals(test.getElementsRowmajor(), plusElements);

		
		//testen van ongeldige argumenten
		double[] elements_fout = new double[] {1, 2, 3, 4};
		matrix_mut test2 = new matrix_mut(2, 2, elements_fout);

	}

}