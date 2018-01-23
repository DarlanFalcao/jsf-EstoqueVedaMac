package br.com.vedamac.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

	private Produto produto;
	private ConexaoFabrica conexao;
	private List<Unidade> listaUnidade;
	

	public ProdutoDAO() {
		conexao = new ConexaoFabrica("localhost", "5432", "nfefacil", "postgres", "iza123");
		conexao.configUser("postgres", "iza123");
		conexao.configLocal("nfefacil");

		conexao.conect();

	}

	public void inserirProduto(Produto produto) {

		produto.setGarantia(produto.getGarantia().replaceAll(",", "."));
		produto.setPrecoCompra(produto.getPrecoCompra().replaceAll(",", "."));
		produto.setPreco(produto.getPreco().replaceAll(",", "."));
		produto.setPeso(produto.getPeso().replaceAll(",", "."));
		produto.setIcms_perc(produto.getIcms_perc().replaceAll(",", "."));
		produto.setIcms_pred(produto.getIcms_pred().replaceAll(",", "."));
		produto.setIpi_perc(produto.getIpi_perc().replaceAll(",", "."));
		produto.setPis_perc(produto.getPis_perc().replaceAll(",", "."));
		produto.setConfins_perc(produto.getConfins_perc().replaceAll(",", "."));

		String sql = "INSERT INTO produto("
				+ "codigo, nome_reduzido, seg_name, unidade, ean, ncm, cest, origem, preco_compra, preco, peso, "
				+ "codigo_fornec, fornecedor, marca, link, images, source_fat, estoque, garantia, "
				+ "icms_cst, icms_perc, icms_pred, ipi_cst, ipi_perc, pis_cst, pis_perc, "
				+ "cofins_cst, cofins_perc, trib_st_perc, descnovo, descricao, cnpj_fornecedor)" + "VALUES ("
				+ produto.getCodigo() + "," + produto.getNomeReduzido() + "," + produto.getSegNome() + ","
				+ produto.getUnidade() + "," + produto.getEan() + "," + produto.getNcm() + "," + produto.getCest() + ","
				+ produto.getOrigem() + "," + produto.getPrecoCompra() + "," + produto.getPreco() + produto.getPeso()
				+ "," + produto.getCodigoForn() + "," + produto.getFornecedor() + "," + produto.getMarca() + ","
				+ produto.getLink() + "," + produto.getImages() + "," + produto.getSouceFat() + "," + 1 + ","
				+ produto.getGarantia() + "," + produto.getIcms_cst() + "," + produto.getIcms_perc() + ","
				+ produto.getIcms_pred() + "," + produto.getIpi_cst() + "," + produto.getIpi_perc() + ","
				+ produto.getPis_cst() + "," + produto.getPis_perc() + "," + produto.getConfins_cst() + ","
				+ produto.getConfins_perc() + "," + produto.getTrib_st_perc() + "," + produto.getDesc_novo() + ","
				+ produto.getDescricao() + "," + produto.getCnpjForn() + ");";

		System.out.println("Unidade Metodo Salvar: " + produto.getUnidade());
		String sql2 = "insert into produto ( " + "codigo, " + "codigo_fornec, " + "fornecedor, " + "descricao, "
				+ "unidade, " + "nome_reduzido, " + "seg_name, " + "marca, " + "garantia, " + "ean, " + "ncm, "
				+ "cest, " + "origem, " + "preco_compra, " + "preco, " + "peso, " + "icms_cst, " + "icms_perc, "
				+ "icms_pred, " + "ipi_cst, " + "ipi_perc, " + "pis_cst, " + "pis_perc, " + "cofins_cst, "
				+ "cofins_perc, " + "images " + ")" + " values (" + produto.getCodigo() + ", " + "'"
				+ produto.getCodigoForn() + "', " + "'" + produto.getFornecedor() + "', " + "'" + produto.getDescricao()
				+ "', " + "'" + produto.getUnidade() + "', " + "'" + produto.getNomeReduzido() + "', " + "'"
				+ produto.getSegNome() + "', " + "'" + produto.getMarca() + "', " + "" + produto.getGarantia() + ", "
				+ "'" + produto.getEan() + "', " + "'" + produto.getNcm() + "', " + "'" + produto.getCest() + "', "
				+ "'" + produto.getOrigem() + "', " + "" + produto.getPrecoCompra() + ", " + "" + produto.getPreco()
				+ ", " + "" + produto.getPeso() + ", " + "'" + produto.getIcms_cst() + "', " + ""
				+ produto.getIcms_perc() + ", " + "" + produto.getIcms_pred() + ", " + "'" + produto.getIpi_cst()
				+ "', " + "" + produto.getIpi_perc() + ", " + "'" + produto.getPis_cst() + "', " + ""
				+ produto.getPis_perc() + ", " + "'" + produto.getConfins_cst() + "', " + "" + produto.getConfins_perc()
				+ ", " + "'" + produto.getImages() + "' " + ")";

		try {
			conexao.getStatment().executeUpdate(sql2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deletarProduto() {
	}

	public int geraCodigo() {
		Integer codigo = new Integer(0);
		String sql = "SELECT MAX(codigo) from produto";
		conexao.conect();
		ResultSet rs = conexao.query(sql);
		try {
			while (rs.next()) {
				codigo = rs.getInt(1) + 1;
				System.out.println("Novo Código: " + codigo);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return codigo;
	}

	public List<Unidade> retornaListaUnidade() {
		listaUnidade = new ArrayList<Unidade>();
		listaUnidade.clear();

		String sql = "SELECT * FROM unidade ";

		ResultSet rs = conexao.query(sql);
		try {
			while (rs.next()) {
				Unidade unid = new Unidade();
				unid.setIdUnidade(rs.getString(1));
				unid.setUnidade(rs.getString(2));
				
				listaUnidade.add(unid);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaUnidade;
	}

	
	public List<Produto> listarProdutos() {
		List<Produto>listaProdutos = new ArrayList<Produto>();
		listaProdutos.clear();

		String sql = "select codigo,nome_reduzido,COUNT(nome_reduzido)as qtde from produto group by codigo";

		ResultSet rs = conexao.query(sql);
		try {
			while (rs.next()) {
				Produto prod = new Produto();
				prod.setCodigo(rs.getInt(1));
				prod.setNomeReduzido(rs.getString(2));
				prod.setQtde(rs.getInt(3));
				listaProdutos.add(prod);

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return listaProdutos;
	}

	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Unidade> getListaUnidade() {
		return listaUnidade;
	}

	public void setListaUnidade(List<Unidade> listaUnidade) {
		this.listaUnidade = listaUnidade;
	}

	

}
