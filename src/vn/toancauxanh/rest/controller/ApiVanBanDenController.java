package vn.toancauxanh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.toancauxanh.model.VanBan;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.service.VanBanDenService;

@RestController
@RequestMapping("/vanBanDens")
public class ApiVanBanDenController {
	
	@Autowired
	private VanBanDenService vanBanDenSv;
	
	
	@GetMapping("/all")
	public ResponseEntity<PagingObject<VanBan>> fillAll() throws Exception{
		PageRequest pageable = new PageRequest(0, 3, Direction.DESC, "id");
		return new ResponseEntity<>(vanBanDenSv.saveAllVanBanDen(pageable), HttpStatus.OK);
	}
}
