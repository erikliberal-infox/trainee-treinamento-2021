package br.com.infox.treinamento.trainee.pessoafisica.cdi;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;

import br.com.infox.treinamento.trainee.interceptors.MethodAccessLog;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisica;
import br.com.infox.treinamento.trainee.pessoafisica.PessoaFisicaService;

@MethodAccessLog
public class PessoaFisicaServiceAdapterDefault implements PessoaFisicaServiceAdapter, Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger("trainee.cdi.default");

	private int quantidadeAcessos = 0;

	@EJB
	private PessoaFisicaService pessoaFisicaService;

	@PostConstruct
	public void init() {
		LOG.info("PostConstruct " + getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy() {
		LOG.info("PreDestroy " + getClass().getSimpleName());
	}

	@Override
	public void registrar(PessoaFisica novaPessoa) {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		pessoaFisicaService.registrar(novaPessoa);
	}

	@Override
	public List<PessoaFisica> recuperarPessoas() {
		this.quantidadeAcessos++;
		LOG.info("QUANTIDADES DE ACESSO A " + getClass().getSimpleName() + " => " + this.quantidadeAcessos);
		return pessoaFisicaService.recuperarPessoas();
	}

	@Override
	public void remover(PessoaFisica pessoa) {
		pessoaFisicaService.remover(pessoa);
	}

}
