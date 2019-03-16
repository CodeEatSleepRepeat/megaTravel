package megatravel.bezbednost.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.CertificateViabilityModel;
import megatravel.bezbednost.model.StatusCertifikata;
import megatravel.bezbednost.repository.CertificateViabilityRepository;

@Service
public class CertificateViabilityService {
	
	@Autowired
	CertificateViabilityRepository certificateViabilityRepository;
	
	public String getStatus(BigInteger serijskiBroj) {
		List<CertificateViabilityModel> lista= certificateViabilityRepository.findAll();
		StatusCertifikata status = null;
		for (CertificateViabilityModel cvm : lista) {
			if(cvm.getSerijskiBroj() == serijskiBroj) {
				status = cvm.getStatus();
			}
		}
		if(status == StatusCertifikata.ISTEKAO) {
			return "Sertifikat je istekao";
		}else if(status == StatusCertifikata.POVUCEN){
			return "Sertifikat je povucen";
		}else if(status == StatusCertifikata.VALIDAN) {
			return "Sertifikat je validan";
		}else {
			return "Doslo je do problema";
		}
	}
}
