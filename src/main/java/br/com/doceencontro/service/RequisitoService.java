package br.com.doceencontro.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import br.com.doceencontro.exception.exceptions.NotParticipandoException;
import br.com.doceencontro.exception.exceptions.RequisitoNotFoundException;
import br.com.doceencontro.model.Evento;
import br.com.doceencontro.model.Requisito;
import br.com.doceencontro.model.Usuario;
import br.com.doceencontro.model.dtos.RequisitoResponseDTO;
import br.com.doceencontro.repository.EventoRepository;
import br.com.doceencontro.repository.RequisitoRepository;

@Service
public class RequisitoService {

	private RequisitoRepository repository;
	
	private EventoService eventoService;
	
	private EventoRepository eventoRepository;

	public RequisitoService(RequisitoRepository repository, EventoService eventoService,
			EventoRepository eventoRepository) {
		this.eventoService = eventoService;
		this.repository = repository;
		this.eventoRepository = eventoRepository;
	}
	
	public Requisito findById(String id) {
		Optional<Requisito> buscarRequisito = repository.findById(id);
		
		if (buscarRequisito.isEmpty()) {
			throw new RequisitoNotFoundException();
		}
		
		return buscarRequisito.get();
	}
	
	private RequisitoResponseDTO converterDTO(Requisito requisito) {
		return new RequisitoResponseDTO(requisito);
	}
	
	public Requisito criarRequisito(Requisito requisito, String eventoId) {
		Evento buscarEvento = eventoService.findById(eventoId);
		
		buscarEvento.addRequisito(requisito);
		
		return repository.save(requisito);
	}
	
	public Requisito editarRequisito(Requisito requisitoEditado, String requisitoId) {
		Requisito buscarRequisito = findById(requisitoId);
		
		buscarRequisito.setTitulo(requisitoEditado.getTitulo());
		buscarRequisito.setDescricao(requisitoEditado.getDescricao());

		return repository.save(buscarRequisito);
	}
	
	public String excluirRequisito(String requisitoId) {
		Requisito buscarRequisito = findById(requisitoId);

		repository.excluir(requisitoId);
		
		return "Requisito excluído com sucesso!";
	}
	
	public RequisitoResponseDTO addResponsavel(String requisitoId, String usuarioId) {
		Requisito buscarRequisito = findById(requisitoId);
		String eventoId = buscarRequisito.getEvento().getId();
		Optional<Usuario> buscarUsuario = eventoRepository.verificarParticipacao(usuarioId, eventoId);
		
		if (buscarUsuario.isEmpty()) {
			throw new NotParticipandoException();
		}
		
		buscarRequisito.adicionarResponsavel(buscarUsuario.get());
		
		return converterDTO(repository.save(buscarRequisito));
	}
	
	public RequisitoResponseDTO removerResponsavel(String requisitoId, String usuarioId) {
		Requisito buscarRequisito = findById(requisitoId);
		String eventoId = buscarRequisito.getEvento().getId();
		Optional<Usuario> buscarUsuario = eventoRepository.verificarParticipacao(usuarioId, eventoId);
		
		if (buscarUsuario.isEmpty()) {
			throw new NotParticipandoException();
		}
		
		buscarRequisito.removerResponsavel(buscarUsuario.get());
		
		return converterDTO(repository.save(buscarRequisito));
	}
	
}





