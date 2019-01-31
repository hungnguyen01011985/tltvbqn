package vn.toancauxanh.rest.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vnpt.xml.ed.Ed;
import com.vnpt.xml.ed.header.MessageHeader;
import com.vnpt.xml.ed.parser.EdXmlParser;
import com.vpcp.services.KnobstickServiceImp;
import com.vpcp.services.model.GetEdocResult;
import com.vpcp.services.model.GetReceivedEdocResult;
import com.vpcp.services.model.Knobstick;
import com.vpcp.services.model.MessageType;

import vn.toancauxanh.model.VanBan;
import vn.toancauxanh.rest.model.PagingObject;
import vn.toancauxanh.rest.repository.VanBanRepository;

@Service
public class VanBanDenService {

	private VanBanRepository vanBanRepo;
	
	@Autowired
	public VanBanDenService(VanBanRepository vanBanRepository) {
		this.vanBanRepo = vanBanRepository;
	}
	
	public PagingObject<VanBan> saveAllVanBanDen(Pageable pageable) throws Exception {
		File filetest = new File("/resources/edoc_new.edxml"); 
		 System.out.println(filetest.getPath());
		    InputStream input = new FileInputStream(filetest);
		    Ed edtest =  EdXmlParser.getInstance().parse(input);
		    System.out.println(edtest.getHeader());
		     MessageHeader messageHeader = (MessageHeader) edtest.getHeader().getMessageHeader();
		     System.out.println(messageHeader.getDocumentId()+"hung");
		System.out.println(123);
		KnobstickServiceImp knobstickService = new KnobstickServiceImp();
		System.out.println("hung123");
		GetReceivedEdocResult getReceivedEdoc = knobstickService.getReceivedEdocList(createJsonHeaderGetReceivedEdoc());
		System.out.println("hung");
		if (getReceivedEdoc != null) {
			for(Knobstick item : getReceivedEdoc.getKnobsticks()) {
					GetEdocResult getEdoc = knobstickService.getEdoc(createJsonHeaderGetEdoc(item.getId()));
					File file = new File(getEdoc.getFilePath());
					InputStream inputStream;
					MessageHeader mesageHeader = new MessageHeader();
					Ed fileEd = new Ed();
					String fileUrl="";
					String checkSum="";
					try {
						inputStream = new FileInputStream(file);
						fileEd = new EdXmlParser().parse(inputStream);
						mesageHeader = (MessageHeader) fileEd.getHeader().getMessageHeader();
						checkSum = checkSum(file);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("code : " + mesageHeader.getCode().getCodeNumber());
					System.out.println("subject : " + mesageHeader.getSubject());
					System.out.println("documentId : " + mesageHeader.getDocumentId());
					System.out.println("docId : " + item.getId());
					System.out.println("noi goi : " + item.getFrom());
					System.out.println("noi goi : " + mesageHeader.getFrom());
					System.out.println("loaiLienThong : " + item.getServiceType());
					System.out.println("fileUrl : " + fileUrl );
					System.out.println("ngay ban hanh : " + mesageHeader.getPromulgationInfo().getPromulgationDate().toString());
					System.out.println("so ky hieu : ");
					System.out.println("check sum : " + checkSum);
					
					VanBan vanBan = new VanBan();
					vanBan.setCode(mesageHeader.getCode().getCodeNumber());
					vanBan.setSubject(mesageHeader.getSubject());
					vanBan.setDocumentId(mesageHeader.getDocumentId());
					vanBan.setDocId(item.getId());
					vanBan.setLoaiLienThong(item.getServiceType());
					vanBan.setFileUrl(fileUrl);
					vanBan.setNgayPhatHanh(mesageHeader.getPromulgationInfo().getPromulgationDate());
					vanBan.setCheckSum(checkSum);
					vanBan.save();
				}
		}
		PagingObject<VanBan> rs = new PagingObject<>();
		org.springframework.data.domain.Page<VanBan> pageVanban;
		pageVanban = vanBanRepo.findAll(pageable);
		rs.setTotalPage(pageable.getPageSize());
		rs.setTotal(pageVanban.getTotalElements());
		rs.setData(pageVanban.getContent());
		return rs;
	}
	
	public static String createJsonHeaderGetReceivedEdoc() {
		StringBuffer stringGetReceivedDoc = new StringBuffer();
		String servicetype = "eDoc";
		stringGetReceivedDoc.append("{"); 
		stringGetReceivedDoc.append("\"servicetype\":\""+servicetype+"\"");
		stringGetReceivedDoc.append(",\"messagetype\":\""+ MessageType.edoc +"\"");
		stringGetReceivedDoc.append("}");  
		return stringGetReceivedDoc.toString();
	}
	public static String createJsonHeaderGetEdoc(String docId) {
		StringBuffer stringGetDoc = new StringBuffer();
		String filePath = "nhan";
		stringGetDoc.append("{"); 
		stringGetDoc.append("\"filePath\":\""+filePath+"\"");
		stringGetDoc.append(",\"docId\":\""+ docId +"\"");
		stringGetDoc.append("}"); 
		return stringGetDoc.toString();
	}
	public static String checkSum(File file) throws NoSuchAlgorithmException,IOException {

	      MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	      messageDigest.update(Files.readAllBytes(file.toPath()));
	      byte[] hash = messageDigest.digest();

	      return DatatypeConverter.printHexBinary(hash).toUpperCase();
	  }
	
}
