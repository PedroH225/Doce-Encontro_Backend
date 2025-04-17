package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.AmizadeExistenteException;
import br.com.doceencontro.exception.exceptions.PedidoExistenteException;
import br.com.doceencontro.exception.exceptions.UsuarioNotFoundException;
import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.model.StatusAmizade;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.AmigoDTO;
import br.com.doceencontro.model.dtos.AmizadeResponseDTO;
import br.com.doceencontro.repository.AmizadeRepository;

@Service
public class AmizadeService {

	private AmizadeRepository amizadeRepository;

	private UsuarioService usuarioService;

	public AmizadeService(AmizadeRepository amizadeRepository, UsuarioService usuarioService) {
		this.amizadeRepository = amizadeRepository;
		this.usuarioService = usuarioService;
	}

	public List<Amizade> findAll() {
		return amizadeRepository.findAll();
	}

	public Amizade findById(String amizadeId) {
		Optional<Amizade> buscarAmizade = amizadeRepository.findById(amizadeId);

		if (buscarAmizade.isEmpty()) {
			throw new RuntimeException("Amizade não encontrada.");
		}

		return buscarAmizade.get();
	}

	private AmizadeResponseDTO converterDto(Amizade amizade) {
		return new AmizadeResponseDTO(amizade);
	}

	private List<AmigoDTO> converterDtos(List<Amizade> amizades, String usuarioId) {
		return amizades.stream().map(a -> new AmigoDTO(a, usuarioId)).collect(Collectors.toList());
	}

	public AmizadeResponseDTO adicionarAmigo(String usuarioId, String amigoEmail) {
		Usuario amigo = usuarioService.buscarPorEmailExcetoId(amigoEmail, usuarioId);

		Usuario usuario = usuarioService.findById(usuarioId);

		Optional<Amizade> buscarAmizade = amizadeRepository.buscarAmizade(usuarioId, amigo.getId());
		
		if (buscarAmizade.isPresent()) {
			Amizade amizadeExistente = buscarAmizade.get();

			if (amizadeExistente.getStatus().equals(StatusAmizade.ACEITO)) {
				throw new AmizadeExistenteException();
			}
			throw new PedidoExistenteException();
		}

		Amizade novaAmizade = new Amizade(null, usuario, amigo, StatusAmizade.PENDENTE);

		novaAmizade.addAmigo(usuario, amigo);

		return converterDto(amizadeRepository.save(novaAmizade));

	}

	public AmizadeResponseDTO aceitarPedido(String amizadeId) {
		Amizade amizade = findById(amizadeId);

		amizade.setStatus(StatusAmizade.ACEITO);

		return converterDto(amizadeRepository.save(amizade));
	}

	public String excluirAmigo(String amizadeId) {
		Amizade amizade = findById(amizadeId);

		amizadeRepository.excluirAmizade(amizadeId);

		if (amizade.getStatus().equals(StatusAmizade.ACEITO)) {
			return "Amizade excluída com sucesso.";
		}
		return "Pedido excluído com sucesso.";
		
	}

	public List<AmigoDTO> buscarPendentes(String usuarioId) {
		List<Amizade> amizades = amizadeRepository.buscarAmizades(usuarioId, StatusAmizade.PENDENTE);

		return converterDtos(amizades, usuarioId);
	}

	public List<AmigoDTO> buscarAceitos(String usuarioId) {
		List<Amizade> amizades = amizadeRepository.buscarAmizades(usuarioId, StatusAmizade.ACEITO);

		return converterDtos(amizades, usuarioId);
	}
}
