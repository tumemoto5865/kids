package com.kdc.mybatis.mapper.web;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kdc.common.entity.db.AdministratorMasterEntity;

/**
 * ���O�C�����Mapper�N���X
 *
 */
@Mapper
public interface WebLoginMapper {

	/**
	 * �Ǘ��҃}�X�^���擾
	 * @param rec
	 * @return
	 */
	AdministratorMasterEntity selectAdmin(@Param("rec") AdministratorMasterEntity rec);

}

