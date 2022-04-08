package id.apps.app.app001.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import id.apps.app.app001.constant.Constant;
import id.apps.app.app001.service.App001Service;
import id.apps.app.util.DtoParamPaging;
import id.apps.app.util.DtoResponse;

@RestController
@RequestMapping("app001")
public class App001 {
	
	@Autowired
	@Qualifier(value = "app001Service")
	private App001Service app001Service;
	
	@PostMapping("/upload")
	public DtoResponse upload(@RequestParam(name = "file", required = false) MultipartFile file,
			@RequestParam(name = "userId", required = false) String userId,
			@RequestParam(name = "userNm", required = false) String userNm,
			@RequestParam(name = "poc", required = false) String poc) throws Exception{
		return app001Service.upload(file, userId, userNm, poc);
		
	}
	
	@PostMapping("/get-all")
	public @ResponseBody DtoResponse getAllHql(@RequestBody DtoParamPaging voParamPaging) {
		return app001Service.getAll(voParamPaging);
	}
	
	@GetMapping("/get-file-image")
	public HttpEntity<byte[]> getPhoto(
			@RequestHeader(value = "token", defaultValue = "") String token,
			@RequestParam(name = "file_name", required = false) String fileName,
			@RequestParam(name = "file_location", required = false) String fileLoc) throws IOException{
		if(token.equals(Constant.TOKEN)) {
			File f = null;
			Path path = Paths.get(fileLoc+fileName);
			if(Files.exists(path)){ 
				f = new File(fileLoc+fileName);
				byte[] image = org.apache.commons.io.FileUtils.readFileToByteArray(f);
			    HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.IMAGE_PNG); 
			    headers.setContentLength(image.length);
			    return new HttpEntity<byte[]>(image);
			}	    
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
