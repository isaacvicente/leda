package produto;

public interface RepositorioProdutos <T extends Produto> {
    public int procurarIndice(int codigo);
    public boolean existe(int codigo);
    public void inserir(T produto);
    public void atualizar(T produto);
    public void remover(int codigo);
    public T procurar(int codigo);
}
