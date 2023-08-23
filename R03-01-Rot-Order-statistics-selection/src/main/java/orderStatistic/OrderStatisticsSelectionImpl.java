package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

	/**
	 * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia 
	 * de usar o selection sem modificar o array original. Note que seu algoritmo vai 
	 * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem 
	 * desejada sem modificar o array original. 
	 * 
	 * Restricoes:
	 * - Voce NÃO pode modificar o aray original
	 * - Voce NÃO pode usar memória extra: nenhum array auxiliar deve ser criado e utilizado.
	 * - Você NÃO pode usar métodos já prontos de manipulação de arrays
	 * - Voce NÃO pode encontrar a k-esima estatistica de ordem por contagem de
	 *   elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
	 *   usando a ideia do selection sort).
	 * - Considere que k varia de 1 a N 
	 * - Voce DEVE usar recursão para resolver o problema. Nenhum laço poderá ser utilizado.
	 * - Você pode implementar métodos auxiliares, desde que seja apenas nesta classe.
	 * - Os métodos auxiliares NÃO PODEM ter mais que três parâmetros.
	 *
	 * Dica: procure pensar como usar a ideia do selection sort para resolver esse problema,
     *       identifique que métodos você precisará para resolver o problema 
	 *       e pense no template da recursão em cada método que voce vai implementar.
	 */
	@Override
	public T getOrderStatistics(T[] array, int k) {
		int smallInd = 0;
		for (int i = 0; i < k; i++)
			smallInd = kthSmallest(array, 0, smallInd);

		return array[smallInd];
	}

	private int kthSmallest(T[] array, int k, int s) {
		if (k == 1)
			return getMenor(array, 0, 1);

		return menorMaiorQue(array, kthSmallest(array, k - 1, s), 0);
	}

	private int getMenor(T[] array, int menorInd, int i) {
		if (i == array.length - 1) {
			if (array[i].compareTo(array[menorInd]) < 0) {
				return i;
			} else return menorInd;
		} else {
			if (array[i].compareTo(array[menorInd]) < 0)
				menorInd = i;

			return getMenor(array, menorInd, ++i);
		}
	}

	private int menorMaiorQue(T[] array, int k, int i) {
		return 0;
	}

}
