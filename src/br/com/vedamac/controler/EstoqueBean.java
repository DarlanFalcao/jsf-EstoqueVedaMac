package br.com.vedamac.controler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.event.PreRenderViewEvent;
import javax.faces.view.ViewScoped;

import br.com.vedamac.model.Produto;
import br.com.vedamac.model.ProdutoDAO;
import br.com.vedamac.model.Unidade;

@ManagedBean
@ViewScoped
public class EstoqueBean {
	private Produto produto;
	private List<Produto>produtos = new ArrayList<Produto>();
	private List<Unidade> unidades = new ArrayList<Unidade>();
	private Integer idUnidade;
	public EstoqueBean() {

	}

	public void carregarUnidades(PreRenderViewEvent event) {

		ProdutoDAO pDao = new ProdutoDAO();
		unidades = pDao.retornaListaUnidade();
		produtos = pDao.listarProdutos();

	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Unidade> getUnidades() {
		return unidades;
	}

	public void setUnidades(List<Unidade> unidades) {
		this.unidades = unidades;
	}
	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
