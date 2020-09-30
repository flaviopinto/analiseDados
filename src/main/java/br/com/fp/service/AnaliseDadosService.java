package br.com.fp.service;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.fp.constantes.Constantes;
import br.com.fp.exception.AplicacaoException;
import br.com.fp.model.Resultado;
import br.com.fp.model.Venda;
import br.com.fp.util.Util;

@Component
public class AnaliseDadosService {

	@Deprecated
	public Resultado processarLinhas(List<String> linhas) throws AplicacaoException {
		Resultado resultado = new Resultado();
		
		try {
			for (String linha : linhas) {
				String[] vetorDados = Util.identificarTipoDadosPorCaractere(linha, Constantes.CTE_LINHA);
				Util.verificarLinhaInvalida(vetorDados[0], vetorDados[1]);
				
				if(Constantes.IDENTIFICADOR_VENDEDOR.equals(vetorDados[0])) {
					resultado.incrementarVendedor();
				} else if (Constantes.IDENTIFICADOR_CLIENTE.equals(vetorDados[0])) {
					resultado.incrementarCliente();
				} else if (Constantes.IDENTIFICADOR_VENDA.equals(vetorDados[0])) {
					Venda venda = Util.preencherVenda(vetorDados[1], Constantes.CTE_LINHA, Constantes.CTE_LISTA, Constantes.CTE_ITEM);
					resultado.processarVenda(venda);				}
			}
			
			return resultado;
			
		} catch (RuntimeException e) {
			throw new AplicacaoException("Erro ao processar linhas do arquivo: ");
		}
		
	}
	
	public Resultado processarLinha(String linha, Resultado retorno) throws AplicacaoException {
		
		try {
			String[] vetorDados = Util.identificarTipoDadosPorCaractere(linha, Constantes.CTE_LINHA);
			Util.verificarLinhaInvalida(vetorDados[0], vetorDados[1]);
			
			if(Constantes.IDENTIFICADOR_VENDEDOR.equals(vetorDados[0])) {
				retorno.incrementarVendedor();
			} else if (Constantes.IDENTIFICADOR_CLIENTE.equals(vetorDados[0])) {
				retorno.incrementarCliente();
			} else if (Constantes.IDENTIFICADOR_VENDA.equals(vetorDados[0])) {
				Venda venda = Util.preencherVenda(vetorDados[1], Constantes.CTE_LINHA, Constantes.CTE_LISTA, Constantes.CTE_ITEM);
				retorno.processarVenda(venda);
			}
			
			return retorno;
			
		} catch (RuntimeException e) {
			throw new AplicacaoException("Erro ao processar linhas do arquivo: ");
		}
		
	}

}
