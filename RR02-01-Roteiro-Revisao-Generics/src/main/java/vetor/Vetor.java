package vetor;

import java.util.Comparator;

/**
 * Implementação de um vetor de objetos simples para exercitar os conceitos de
 * Generics.
 * 
 * @author Adalberto
 *
 */
public class Vetor<T> {

	// O array interno onde os objetos manipulados são guardados
	private T[] arrayInterno;

	// O tamanho que o array interno terá
	private int tamanho;

	// Indice que guarda a proxima posição vazia do array interno
	private int indice;

	// O Comparators a serem utilizados
	private Comparator<T> comparadorMaximo;
	private Comparator<T> comparadorMinimo;

	public Vetor(int tamanho) {
		super();
		this.tamanho = tamanho;
		this.indice = -1;
		this.arrayInterno = (T[]) new Object[tamanho];
	}

	public void setComparadorMaximo(Comparator<T> comparadorMaximo) {
		this.comparadorMaximo = comparadorMaximo;
	}

	public void setComparadorMinimo(Comparator<T> comparadorMinimo) {
		this.comparadorMinimo = comparadorMinimo;
	}

	// Insere um objeto no vetor
	public void inserir(T obj) {
		if (this.indice + 1 < this.tamanho) {
			this.arrayInterno[++this.indice] = obj;
		}
	}

	// Remove um objeto do vetor
	public T remover(T obj) {
		if (this.isVazio()) {
			throw new RuntimeException("Vetor está vazio");
		}
		boolean achado = false;

		for (int i = 0; i < arrayInterno.length; i++) {
			if (arrayInterno[i].equals(obj)) {
				achado = true;
				for (int j = i; j < this.indice; j++) {
					this.arrayInterno[j] = this.arrayInterno[j+1];
				}
				this.arrayInterno[indice] = null;
				this.indice--;
			}
		}

		return achado ? obj : null;
	}

	// Procura um elemento no vetor
	public T procurar(T obj) {
		if (this.isVazio()) {
			throw new RuntimeException("O vetor está vazio");
		}

		for (int i = 0; i <= this.indice; i++) {
			if (obj.equals(this.arrayInterno[i])) {
				return obj;
			}
		}

		return null;
	}

	// Diz se o vetor está vazio
	public boolean isVazio() {
		return this.indice == -1;
	}

	// Diz se o vetor está cheio
	public boolean isCheio() {
		return this.indice == this.tamanho - 1;
	}

}
