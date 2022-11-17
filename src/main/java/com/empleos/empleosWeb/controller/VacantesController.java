package com.empleos.empleosWeb.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.empleos.empleosWeb.model.Vacante;
import com.empleos.empleosWeb.service.IntVacantesService;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IntVacantesService vacantesService;
	@PostMapping("/guardar")
	public String guardar(Vacante vacante,BindingResult br, RedirectAttributes atributo) {
		
		if(br.hasErrors()) {
			for(ObjectError error: br.getAllErrors()) {
				System.out.print("Error :" + error.getDefaultMessage());
				
			}
			return "vacantes/formVacante";
		}
		System.out.print(vacante);
		vacante.setId(vacantesService.obtenerTodas().size()+1);
		System.out.print(vacante);
		vacantesService.agregar(vacante);
		atributo.addFlashAttribute("msg","Vacante Registrado");
		return "redirect:/vacantes/index";
	}
	@GetMapping("/detalle")
	public String detalle(@RequestParam("id") int idVacante, Model model) {
		
		Vacante vacante =vacantesService.buscarPorId(idVacante);
		model.addAttribute("vacante", vacante);
		return "vacantes/detalle";
	}
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport(){
			@Override
			public void setAsText(String text) throws IllegalArgumentException{
				setValue(LocalDate.parse(text,DateTimeFormatter.ofPattern("dd-MM-yyyy")));
				
			}
			@Override
			
			public String getAsText()throws IllegalArgumentException{
				return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate)getValue());
			}
			
			
		});
	}
	
	
	@GetMapping("/index")
	public String mostrarIndex(Model model){
		List<Vacante> lista = vacantesService.obtenerTodas();
		/*for(Vacante v:lista) {
			System.out.print(v);
		}
		System.out.print(lista);*/
		model.addAttribute("vacantes", lista);
		return "vacantes/listVacantes";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idVacante,RedirectAttributes atributo) {
		vacantesService.eliminar(idVacante);
		atributo.addFlashAttribute("msg","Vacante eliminada");
		return "redirect:/vacantes/index";
	}
	
	@GetMapping("/crear")
	public String crearCategoria(Vacante vacante) {
		return "vacantes/formVacante";
	}
		
}
