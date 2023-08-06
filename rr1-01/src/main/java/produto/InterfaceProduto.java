package produto;

public interface InterfaceProduto {

    public abstract boolean existe(int codigo);

    public abstract void inserir(Produto produto);

    public abstract void atualizar(Produto produto);

    public abstract void remover(int codigo);

    public abstract Produto procurar(int codigo);

}
