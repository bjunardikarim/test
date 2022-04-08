package id.apps.app.app001.service.impl;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.function.Function;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import id.apps.app.ConfigProperties;
import id.apps.app.app001.dao.EntryDataDao;
import id.apps.app.app001.service.App001Service;
import id.apps.app.model.EntryData;
import id.apps.app.util.DtoHelper;
import id.apps.app.util.DtoParamPaging;
import id.apps.app.util.DtoResponse;
import id.apps.app.util.JpaUtil;
import id.apps.app.util.OffsetBasedPageRequest;
import id.apps.app.util.StatusMsgEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Transactional(readOnly = true)
@Service(value = "app001Service")
public class App001ServiceImpl implements App001Service {

	@Autowired
	@Qualifier(value = "entryDataDao")
	private EntryDataDao entryDataDao;

	@Override
	@Transactional(readOnly = false)
	public DtoResponse upload(MultipartFile file, String userId, String userNm, String poc) throws Exception {
		List<String> lVal = new ArrayList<String>();
		try {
			String locFile = ConfigProperties.FILEUPLOAD;
			File f = multipartToFile(file, locFile);
			if (f != null) {
				String ext = FilenameUtils.getExtension(file.getOriginalFilename());
				final BufferedImage image = ImageIO.read(f);
				Graphics g = image.getGraphics();
				g.setColor(Color.BLACK);
				Graphics2D g2 = (Graphics2D) g;
			    int defaultWidth = 1440;
			    int  width	= image.getWidth();
			    float duacm = 75.59f;
			    float pixelPembagi = 1f;
			    if(width > defaultWidth) {
			    	pixelPembagi = (width / defaultWidth) + 0.55f;
			    	g2.setStroke(new BasicStroke(5));
			    	g.setFont(g.getFont().deriveFont(40f * pixelPembagi));
			    }else {
			    	g2.setStroke(new BasicStroke(3));
			    	g.setFont(g.getFont().deriveFont(40f));
			    }
			    

				int w = (int) (400*pixelPembagi);
				int h = (int) (335*pixelPembagi);
				int x = (int) (duacm * pixelPembagi);
				
				System.out.println(width);	
			    System.out.println(pixelPembagi);
			    System.out.println(x);
			    
				g2.drawLine(x, x, w, x);// titik - , titik |, panjang line -, titik |
				g2.drawLine(x, x, x, h);
				g2.drawLine(w, x, w, h);
				
				int render = (int) ((x + 65*pixelPembagi));
				g2.drawLine(x, render, w, render);
				render = (int) (render + (65*pixelPembagi));
				g2.drawLine(x, render, w, render);
				render = (int) (render + (65*pixelPembagi));
				g2.drawLine(x, render, w, render);
				render = (int) (render + (65*pixelPembagi));
				g2.drawLine(x, render, w, render);
				
				g2.drawString(poc, (135*pixelPembagi), (125*pixelPembagi));// first
				String dateInput = dateToString(new Date(), "dd-MMM-yyyy");
				g2.drawString(dateInput, (120*pixelPembagi), (190*pixelPembagi));// +65
				g2.drawString(userId, (200*pixelPembagi), (255*pixelPembagi));
				if (userNm.length() == 5) {
					g2.drawString(userNm, (190*pixelPembagi), (320*pixelPembagi));
				} else {
					g2.drawString(userNm, (200*pixelPembagi), (320*pixelPembagi));
				}
				
				g2.dispose();
				String newFileNM = "Edit-"+file.getOriginalFilename();

				ImageIO.write(image, ext, new File(locFile + newFileNM));
				File finalFile = new File(locFile + newFileNM);
				if (finalFile != null) {
					EntryData e = new EntryData();
					BigDecimal max = entryDataDao.max();

					e.setId(max != null ? max.intValue() + 1 : 1);
					e.setUserNm(userNm);
					e.setUserId(userId);
					e.setPoc(poc);
					e.setDateipt(dateInput);
					e.setFileNm(newFileNM);
					e.setFileLoc(locFile);
					e.setFileNmOld(file.getOriginalFilename());
					entryDataDao.save(e);
					return DtoHelper.constructResponse(StatusMsgEnum.SUKSES, null, null);
				}


			}else {
				lVal.add("Upload File * Required");
			}
			
			return DtoHelper.constructResponse(StatusMsgEnum.GAGAL, null, null);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static File multipartToFile(MultipartFile multipart, String locFile)
			throws IllegalStateException, IOException {
		File convFile = new File(locFile + multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	public static String dateToString(Date date, String format) {
		if (date == null) {
			return "-";
		}
		return new SimpleDateFormat(format, Locale.ENGLISH).format(date);
	}

	@Override
	public DtoResponse getAll(DtoParamPaging voParamPaging) {
		Map<String, String> sortMap = new HashMap<>();
		sortMap.put("id", "id");
		sortMap.put("userId", "userId");
		sortMap.put("userNm", "userNm");
		sortMap.put("poc", "poc");
		sortMap.put("dateipt", "dateipt");
		sortMap.put("fileNm", "fileNm");
		sortMap.put("fileLoc", "fileLoc");
		sortMap.put("fileNmOld", "fileNmOld");
		Pageable pageable = new OffsetBasedPageRequest(voParamPaging.getLimit(), voParamPaging.getOffset(),
				JpaUtil.buildSort(sortMap, voParamPaging.getOrder(), voParamPaging.getSort(), "id"));
		Specification<EntryData> spec = filter(voParamPaging.getSearch());
		Page<EntryData> lst = entryDataDao.findAll(spec, pageable);
		return DtoHelper.constructResponsePaging(StatusMsgEnum.SUKSES, null, lst.toList(), (int) lst.getTotalElements());
	}
	
	public static Specification<EntryData> filter(Map<String, Object> filters) {
		return new Specification<EntryData>() {
			@Override
			public Predicate toPredicate(Root<EntryData> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				if (filters != null) {
					for (Map.Entry<String, Object> filter : filters.entrySet()) {
						String valueStr = filter.getValue().toString();
						if (filter.getKey().equalsIgnoreCase("any")) {
							//predicates.add(builder.like(root.get("vmenuid"), "%" + valueStr + "%"));
						} 
					}
				}
				return builder.and(predicates.toArray(new Predicate[] {}));
			}
		};
	}

	

}
