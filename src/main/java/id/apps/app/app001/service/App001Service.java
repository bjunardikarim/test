package id.apps.app.app001.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import id.apps.app.util.DtoParamPaging;
import id.apps.app.util.DtoResponse;

public interface App001Service {

	DtoResponse upload(MultipartFile file, String userId, String userNm, String poc) throws Exception;

	DtoResponse getAll(DtoParamPaging voParamPaging);
	
}
