package com.icss.bp.dao;

import com.icss.bp.bean.BPBatchBean;

public interface BPBatchDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long bId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    int insert(BPBatchBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    int insertSelective(BPBatchBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    BPBatchBean selectByPrimaryKey(Long bId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(BPBatchBean record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BP_BATCH
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(BPBatchBean record);
}