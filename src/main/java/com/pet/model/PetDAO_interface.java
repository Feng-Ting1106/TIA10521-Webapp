package com.pet.model;

import java.util.*;

public interface PetDAO_interface {
          public void insert(PetVO petVO);
          public void update(PetVO petVO);
          public void delete(PetVO petVO);
          public PetVO findByPrimaryKey(Integer pet_id);
          public List<PetVO> getAll();
          public List<PetVO> findeByMemberId(Integer member_id);
          //萬用複合查詢(傳入參數型態Map)(回傳 List)
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
