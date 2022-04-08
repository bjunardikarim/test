package id.apps.app.app001.dao;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import id.apps.app.model.EntryData;

public interface EntryDataDao extends JpaRepository<EntryData, Integer>, JpaSpecificationExecutor<EntryData>{
	
	@Query(value = "SELECT max(id) FROM EntryData")
	public BigDecimal max();

}
