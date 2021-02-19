package matrix_immutable;

import java.util.stream.IntStream;

/**
 * 
 * @invar | nbOfElements() == getNbOfRows() * getNbOfCollumns()
 *
 */
public class matrix_imm {
	private int nb_of_rows;
	private int nb_of_collumns;
	private double[] elements_array;
	
	/**
	 * Geeft het aantal rijen in de matrix.
	 * 
	 * @inspects | this
	 */
	public int getNbOfRows() {
		return nb_of_rows;
	}
	
	/**
	 * Geeft het aantal kolommen in de matrix.
	 * 
	 * @inspects | this
	 */
	public int getNbOfCollumns() {
		return nb_of_collumns;
	}
	
	/**
	 * Geeft het aantal elementen in de matrix.
	 * 
	 * @inspects | this
	 */
	public int nbOfElements() {
		return nb_of_rows * nb_of_collumns;
	}
	
	/**
	 * Geeft de elementen van de matrix in een rij per rij.
	 * 
	 * @creates | result
	 * @inspects | this
	 * @post de elementen komen overeen met die van de matrix in de juiste volgorde.
	 */
	public double[] getElementsRowmajor() {
		double[] copy_array = new double[this.nbOfElements()];
		System.arraycopy(this.elements_array, 0, copy_array, 0, this.nbOfElements());
		return copy_array;
	}
	
	/**
	 * Geeft de elementen van de matrix in een rij per kolom.
	 * 
	 * @creates | result
	 * @inspects | this
	 * @post de elementen komen overeen met die van de matrix in de juiste volgorde.
	 */
	public double[] getElementsColmajor() {
		int index = 0;
		double[] return_array = new double[this.nbOfElements()];
		for(int i = 0; i < nb_of_collumns; i++) {
			for(int j = 0; j < nb_of_rows; j++) {
				return_array[index] = this.getElementAt(j + 1, i + 1);
				index++;
			}
		}
		return return_array;
	}
	
	/**
	 * Geeft de elementen van de matrix in een rij per rij in een rij.
	 * 
	 * @creates | result
	 * @inspects | this	
	 * @post de rijen komen overeen met die van de matrix.
	 */
	public double[][] getElementsRowArray() {
		double[][] return_array = new double[nb_of_rows][nb_of_collumns];
		double[] all_elements = this.getElementsRowmajor();
		int k = 0;
		for(int i=0;i<nb_of_rows;i++) {
			for(int j=0; j<nb_of_collumns; j++) {
				return_array[i][j] = all_elements[k];
				k++;
			}
		}
		
		return return_array;
	}
	
	
	/**
	 * Geeft het element op index (rij, kolom).
	 * 
	 * @pre index moet geldig zijn binnen dimensie van matrix.
	 * 	  |row_nb <= getNbOfRows() && col_nb <= getNbOfCollumns()
	 */
	public double getElementAt(int row_nb, int col_nb) {
		int index = ((row_nb - 1) * this.nb_of_collumns + col_nb) - 1;
		return (this.getElementsRowmajor())[index];
	}

	/**
	 * Geeft een nieuwe matrix waarbij elke term maal factor wordt gedaan.
	 * 
	 * @creates | result
	 * @inspects | this
	 * @post bij elk element is de term vermenigvuldigd.
	 * 	  | IntStream.range(0, nbOfElements()).allMatch(i ->
	 * 	  |		(getElementsRowmajor()[i] * factor) == result.getElementsRowmajor()[i])	 
	 */
	public matrix_imm scaled(double factor) {
		double[] all_elements = this.getElementsRowmajor();
		double[] new_elements = new double[all_elements.length];
		for(int i = 0; i < all_elements.length; i++) {
			new_elements[i] = all_elements[i] * factor;
		}
		return new matrix_imm(this.nb_of_rows, this.nb_of_collumns, new_elements);
	}
	
	/**
	 * Geeft een nieuwe matrix waarbij bij elk element een term wordt bijgeteld.
	 * 
	 * @creates | result
	 * @inspects | this
	 * @post bij elk element is de term toegevoegd.
	 * 	  | IntStream.range(0, nbOfElements()).allMatch(i ->
	 * 	  |		(getElementsRowmajor()[i] + term) == result.getElementsRowmajor()[i])	 
	 */
	public matrix_imm plus(double term) {
		double[] all_elements = this.getElementsRowmajor();
		double[] new_elements = new double[all_elements.length];
		for(int i = 0; i < all_elements.length; i++) {
			new_elements[i] = all_elements[i] + term;
		}
		return new matrix_imm(this.nb_of_rows, this.nb_of_collumns, new_elements);
	}
	
	/**
	 * Initialiseert het matrix-object.
	 * @creates |result
	 * @throws IllegalArgumentException als de dimensies niet positief zijn.
	 * 	  |nb_of_rows < 1 || nb_of_collumns < 1
	 * @throws IllegalArgumentException het aantal elementen niet exact in de matrix past.
	 * 	  |nb_of_rows * nb_of_collumns != initialElements.length
	 */
	public matrix_imm(int nb_of_rows, int nb_of_collumns, double[] initialElements){
		if(nb_of_rows < 1 || nb_of_collumns < 1)
			throw new IllegalArgumentException("dimensies moeten positief zijn");
		if(nb_of_rows * nb_of_collumns != initialElements.length)
			throw new IllegalArgumentException("aantal elementen komt niet overeen met dimensie");

		
		this.nb_of_rows = nb_of_rows;
		this.nb_of_collumns = nb_of_collumns;
		this.elements_array = initialElements;
	}
	
		
}
