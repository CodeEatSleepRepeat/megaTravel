package megatravel.bezbednost.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import megatravel.bezbednost.dto.CertificateViabilityDTO;
import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.model.CertificateViabilityModel;
import megatravel.bezbednost.model.StatusCertifikata;
import megatravel.bezbednost.service.AdminService;
import megatravel.bezbednost.service.CertificateViabilityService;
import megatravel.bezbednost.token.JwtTokenUtils;

@RestController
public class CertificateViabilityController {
	
	@Autowired
	CertificateViabilityService certificateViabilityService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/api/certificate/viability/{serijskiBroj}", method = RequestMethod.GET)
	public ResponseEntity<String> getStatus(@PathVariable("serijskiBroj") BigInteger serijskiBroj, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		String status = certificateViabilityService.getStatus(serijskiBroj);
		return new ResponseEntity<String>(status, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability/all", method = RequestMethod.GET)
public ResponseEntity<List<CertificateViabilityModel>> getAllStatus(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<CertificateViabilityModel> statusi = certificateViabilityService.findAll();
		return new ResponseEntity<List<CertificateViabilityModel>>(statusi, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateViabilityModel> newStatus(@RequestBody CertificateViabilityDTO commDTO, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		BigInteger sn = commDTO.getSerijskiBroj();
		StatusCertifikata sc = commDTO.getStatus();
		CertificateViabilityModel noviStatus = certificateViabilityService.newStatus(new CertificateViabilityModel(sn, sc));
		return new ResponseEntity<CertificateViabilityModel>(noviStatus, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateViabilityModel> editStatus(@RequestBody CertificateViabilityDTO commDTO, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		BigInteger sn = commDTO.getSerijskiBroj();
		StatusCertifikata sc = commDTO.getStatus();
		CertificateViabilityModel promenjenStatus = certificateViabilityService.editStatus(sn, sc);
		return new ResponseEntity<CertificateViabilityModel>(promenjenStatus, HttpStatus.OK);
		
	}
	
	
	
}
