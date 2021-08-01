package research.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import research.dao.ResearchDao;
import research.dto.Survey;

@Service
public class ResearchServiceImpl implements ResearchService{

	@Autowired
	ResearchDao researchDao;

	@Override
	public List<Survey> getList() {
		return researchDao.selectAll();
	}

	
	
	
	
}
