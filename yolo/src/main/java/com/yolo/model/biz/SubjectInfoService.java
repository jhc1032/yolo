package com.yolo.model.biz;

import java.util.List;

import com.yolo.model.domain.ClassInfo;
import com.yolo.model.domain.PageBean;
import com.yolo.model.domain.SubjectInfo;
import com.yolo.model.domain.SubjectMlist;

public interface SubjectInfoService {
	public List<SubjectInfo> searchSubject(String id) ;
	public List<SubjectMlist> searchMlist(int createcode) ;
}
