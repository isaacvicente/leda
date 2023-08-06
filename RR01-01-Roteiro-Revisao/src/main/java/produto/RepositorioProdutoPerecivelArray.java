package produto;

public class RepositorioProdutoPerecivelArray extends RepositorioProdutosArray {

	public RepositorioProdutoPerecivelArray(int size) {
		super(size);
	}

	@Override
	public void inserir(Produto produto) {
		if (produto instanceof ProdutoPerecivel) {
			super.inserir(produto);
		}
	}

	@Override
	public void atualizar(Produto produto) {
		if (produto instanceof ProdutoPerecivel) {
			super.atualizar(produto);
		}
	}
}
