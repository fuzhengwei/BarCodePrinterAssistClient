package com.icss.bp.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.icss.util.date.DateSerializer;

public class VOrderCodeStateBean {

	private String oiUuid;

	private Integer oiPrintNum;

	private Integer oiReselOrderNum;

	private Date oiStartTime;

	private Date oiEndTime;

	public String getOiUuid() {
		return oiUuid;
	}

	public void setOiUuid(String oiUuid) {
		this.oiUuid = oiUuid;
	}

	public Integer getOiPrintNum() {
		return oiPrintNum;
	}

	public void setOiPrintNum(Integer oiPrintNum) {
		this.oiPrintNum = oiPrintNum;
	}

	public Integer getOiReselOrderNum() {
		return oiReselOrderNum;
	}

	public void setOiReselOrderNum(Integer oiReselOrderNum) {
		this.oiReselOrderNum = oiReselOrderNum;
	}

	public Date getOiStartTime() {
		return oiStartTime;
	}

	public void setOiStartTime(Date oiStartTime) {
		this.oiStartTime = oiStartTime;
	}

	@JsonSerialize(using = DateSerializer.class)
	public Date getOiEndTime() {
		return oiEndTime;
	}

	@JsonSerialize(using = DateSerializer.class)
	public void setOiEndTime(Date oiEndTime) {
		this.oiEndTime = oiEndTime;
	}

	@Override
	public String toString() {
		return "VOrderCodeStateBean [oiUuid=" + oiUuid + ", oiPrintNum="
				+ oiPrintNum + ", oiReselOrderNum=" + oiReselOrderNum
				+ ", oiStartTime=" + oiStartTime + ", oiEndTime=" + oiEndTime
				+ "]";
	}

}
