package com.pet.model;

import java.util.List;

public class PetService {

	private PetDAO_interface dao;

	public PetService() {
//		dao = new PetDAO();
		dao = new PetJDBCDAO(); //不想用連線池就這樣
	}

	public PetVO addPet(Integer member_id, String pet_type, String pet_name,
			Integer pet_gender, Integer pet_weight, Integer status) {

		PetVO petVO = new PetVO();

		petVO.setMember_id(member_id);
		petVO.setPet_type(pet_type);
		petVO.setPet_name(pet_name);	
		petVO.setPet_gender(pet_gender);
		petVO.setPet_weight(pet_weight);
		petVO.setStatus(status);
		dao.insert(petVO);

		return petVO;
	}

	public PetVO updatePet(Integer pet_id, Integer member_id, String pet_type, String pet_name,
			Integer pet_gender, Integer pet_weight, Integer status) {

		PetVO petVO = new PetVO();

		petVO.setPet_id(pet_id);
		petVO.setMember_id(member_id);
		petVO.setPet_type(pet_type);
		petVO.setPet_name(pet_name);	
		petVO.setPet_gender(pet_gender);
		petVO.setPet_weight(pet_weight);
		petVO.setStatus(status);	
		dao.update(petVO);

		return petVO;
	}

	public PetVO deletePet(Integer pet_id) {
		
		PetVO petVO = new PetVO();
		
		petVO.setPet_id(pet_id);
		petVO.setStatus(0);
		dao.delete(petVO);
		
		return petVO;
	}

	public PetVO getOnePet(Integer pet_id) {
		return dao.findByPrimaryKey(pet_id);
	}

	public List<PetVO> getAll() {
		return dao.getAll();
	}
	
	public List<PetVO> getPetFindByMemberId(Integer memberId) {
		return dao.findeByMemberId(memberId);
	}
}
