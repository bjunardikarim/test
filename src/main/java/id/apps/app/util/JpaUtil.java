package id.apps.app.util;

import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

public class JpaUtil {
	
	public static Sort buildSort(Map<String, String> sortMap, String order, String sort, String defaultOrd) {
		if (sort != null && !StringUtils.isEmpty(sort)) {
			if (order != null && !StringUtils.isEmpty(order)) {
				if (order.equalsIgnoreCase(CommonConstant.ASC)) {
					return Sort.by(sortMap.get(sort)).ascending();
				} else if (order.equalsIgnoreCase(CommonConstant.DESC)) {
					return Sort.by(sortMap.get(sort)).descending();
				}
			}
		}
		return Sort.by(defaultOrd).ascending();
	}
	
}
