package com.example.festora.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.festora.model.Evento;
import com.example.festora.model.Requisito;
import com.example.festora.model.Usuario;
import com.example.festora.model.dtos.RequisitoResponseDTO;
import com.example.festora.repository.EventoRepository;
import com.example.festora.repository.RequisitoRepository;

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
			throw new RuntimeException("Requisito não encontrado.");
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
			throw new RuntimeException("Você não está participando do evento.");
		}
		
		buscarRequisito.adicionarResponsavel(buscarUsuario.get());
		
		return converterDTO(repository.save(buscarRequisito));
	}
	
}





