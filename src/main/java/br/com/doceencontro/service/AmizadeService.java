package br.com.doceencontro.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.AmizadeExistenteException;
import br.com.doceencontro.exception.exceptions.AmizadeNotFoundException;
import br.com.doceencontro.exception.exceptions.ConflictException;
import br.com.doceencontro.exception.exceptions.ForbiddenException;
import br.com.doceencontro.exception.exceptions.PedidoExistenteException;
import br.com.doceencontro.model.Amizade;
import br.com.doceencontro.model.StatusAmizade;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.AmigoDTO;
import br.com.doceencontro.model.dtos.AmizadeResponseDTO;
import br.com.doceencontro.repository.AmizadeRepository;
import br.com.doceencontro.utils.ConversorDTO;

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
			throw new AmizadeNotFoundException();
		}

		return buscarAmizade.get();
	}
	
	private void validarParticipacaoAmizade(String usuarioId, Amizade amizade) {
		if (!amizade.getUsuario().getId().equals(usuarioId) && !amizade.getAmigo().getId().equals(usuarioId)) {
			throw new ForbiddenException("Você não faz parte dessa amizade.");
		}
	}

	public AmigoDTO adicionarAmigo(String usuarioId, String amigoEmail) {
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

		return ConversorDTO.amigo(amizadeRepository.save(novaAmizade));

	}

	public AmigoDTO aceitarPedido(String usuarioId, String amizadeId) {
		Amizade amizade = findById(amizadeId);
		
		validarParticipacaoAmizade(usuarioId, amizade);
		
		if (amizade.getStatus().equals(StatusAmizade.ACEITO)) {
			throw new ConflictException("Pedido já aceito.");
		}

		amizade.setStatus(StatusAmizade.ACEITO);

		return ConversorDTO.amigo(amizadeRepository.save(amizade));
	}

	public String excluirAmigo(String usuarioId, String amizadeId) {
		Amizade amizade = findById(amizadeId);
		
		validarParticipacaoAmizade(usuarioId, amizade);

		amizadeRepository.excluirAmizade(amizadeId);

		if (amizade.getStatus().equals(StatusAmizade.ACEITO)) {
			return "Amizade excluída com sucesso.";
		}
		return "Pedido excluído com sucesso.";

	}

	public List<AmigoDTO> buscarPendentes(String usuarioId) {
		List<Amizade> amizades = amizadeRepository.buscarPendentes(usuarioId);

		return ConversorDTO.amigos(amizades);
	}

	public List<AmigoDTO> buscarAceitos(String usuarioId) {
		List<Amizade> amizades = amizadeRepository.buscarAmizades(usuarioId);

		return ConversorDTO.amigos(amizades);
	}
	
	public List<AmigoDTO> buscarRecebidos(String usuarioId) {
		List<Amizade> amizades = amizadeRepository.buscarRecebidos(usuarioId);

		return ConversorDTO.amigos(amizades);
	}
}
