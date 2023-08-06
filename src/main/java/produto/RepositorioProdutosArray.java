package produto;

/**
 * Classe que representa um repositório de produtos usando arrays como estrutura
 * sobrejacente. Alguns métodos (atualizar, remover e procurar) ou executam com
 * sucesso ou retornam um erro. Para o caso desde exercício, o erro será
 * representado por uma RuntimeException que não precisa ser declarada na
 * clausula "throws" do mos metodos.
 * 
 * Obs: Note que você deve utilizar a estrutura de dados array e não
 * implementações de array prontas da API Collections de Java (como ArrayList,
 * por exemplo).
 * 
 * @author Adalberto
 *
 */
public class RepositorioProdutosArray implements InterfaceProduto {
  /**
   * A estrutura (array) onde os produtos sao mantidos.
   */
  private Produto[] produtos;

  /**
   * A posicao do ultimo elemento inserido no array de produtos. o valor
   * inicial é -1 para indicar que nenhum produto foi ainda guardado no array.
   */
  private int index = -1;

  public RepositorioProdutosArray(int size) {
    super();
    this.produtos = new Produto[size];
  }

  /**
   * Recebe o codigo do produto e devolve o indice desse produto no array ou
   * -1 caso ele nao se encontre no array. Esse método é util apenas na
   * implementacao com arrays por questoes de localizacao. Outras classes que
   * utilizam outras estruturas internas podem nao precisar desse método.
   * 
   * @param codigo
   * @return o indice do produto, caso encontrado
   */
  private int procurarIndice(int codigo) {
    for (int i = 0; i < this.produtos.length; i++) {
      if (codigo == this.produtos[i].getCodigo()) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Recebe o codigo e diz se tem produto com esse codigo armazenado
   * 
   * @param codigo
   * @return
   */
  @Override
  public boolean existe(int codigo) {
    return procurarIndice(codigo) != -1;
  }

  /**
   * Insere um novo produto (sem se preocupar com duplicatas)
   */
  @Override
  public void inserir(Produto produto) {
    if (index + 1 <= this.produtos.length) {
      this.produtos[++index] = produto;
    }
  }

  /**
   * Atualiza um produto armazenado ou retorna um erro caso o produto nao
   * esteja no array. Note que, para localizacao, o código do produto será
   * utilizado.
   */
  public void atualizar(Produto produto) {
    this.produtos[procurarIndice(produto.getCodigo())] = produto;
  }

  /**
   * Remove produto com determinado codigo, se existir, ou entao retorna um
   * erro, caso contrário. Note que a remoção NÃO pode deixar "buracos" no
   * array.
   * 
   * @param codigo
   */
  public void remover(int codigo) {
    if (existe(codigo)) {
      int indice = procurarIndice(codigo);
      Produto[] prods = new Produto[this.produtos.length - 1];

      for (int i = indice - 1; i < indice; i++) {
        prods[i] = this.produtos[i];
      }

      for (int j = indice + 1; j < this.produtos.length; j++) {
        prods[j] = this.produtos[j];
      }

      this.produtos = prods;
    }
  }

  /**
   * Retorna um produto com determinado codigo ou entao um erro, caso o
   * produto nao esteja armazenado
   * 
   * @param codigo
   * @return
   */
  public Produto procurar(int codigo) {
    int indice = procurarIndice(codigo);
    if (indice != -1) {
      return this.produtos[procurarIndice(codigo)];
    }
    return null;
  }
}
