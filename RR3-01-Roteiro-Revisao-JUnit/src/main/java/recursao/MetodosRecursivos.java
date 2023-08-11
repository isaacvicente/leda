package recursao;

public class MetodosRecursivos {

	public int calcularSomaArray(int[] array){
		int result = 0;
		// TODO ESCREVA AQUI O CÓDIGO (USANDO RECURSAO) PARA CALCULAR A SOMA
		// DOS EMENTOS DE UM ARRAY
		return result;
	}
	public long calcularFatorial(int n) {
		if (n == 0 || n == 1) {
			return 1;
		}
		return n * calcularFatorial(n - 1);
	}

	public int calcularFibonacci(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		return calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
	}

	public int countNotNull(Object[] array) {
		int result = 0;
		// TODO IMPLEMENTE AQUI O CODIGO QUE CONTA (USANDO RECURSAO) A
		// QUANTIDADE DE ELEMENTOS NAO NULOS
		// DE UM ARRAY DE OBJETOS RECEBIDO COMO PARAMETRO
		return result;
	}

	public long potenciaDe2(int expoente) {
		if (expoente == 0) {
			return 1;
		}
		if (expoente == 1) {
			return 2;
		}

		return 2 * potenciaDe2(expoente - 1);
	}

	public double progressaoAritmetica(double termoInicial, double razao, int n) {
		if (n == 1)
			return termoInicial;

		return progressaoAritmetica(termoInicial, razao, n - 1) + razao;
	}

	public double progressaoGeometrica(double termoInicial, double razao, int n) {
		if (n == 1)
			return termoInicial;

		return progressaoAritmetica(termoInicial, razao, n - 1) * razao;
	}
	
	
}
