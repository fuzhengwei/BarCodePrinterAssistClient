package com.icss.bp.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.bp.agreement.MsgBarCodePrinterEnum;
import com.icss.bp.agreement.MsgCategoryEnum;
import com.icss.bp.bean.BPOrderInfoBean;
import com.icss.bp.control.BPBusinessControl;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyJsonUtil;
import com.icss.util.mybatis.MybatisSqlSession;

/**
 * 基础数据抽取线程
 * 
 * @author Administrator
 * 
 * @version 1.0
 * 
 */
public class BaseBPDateExtractionService implements Runnable {

	// SqlSession
	private SqlSession session = null;
	// OrderDao
	private BPOrderInfoDao orderInfoDao = null;
	// BPBusinessControl
	private BPBusinessControl businessControl = null;
	// sleep count
	private int sleepCount = 0;
	
	public BaseBPDateExtractionService(BPBusinessControl businessControl) {

		// 获得session
		session = MybatisSqlSession.getSqlSession();
		// 获得dao操作
		orderInfoDao = session.getMapper(BPOrderInfoDao.class);
		// 获得业务控制
		this.businessControl = businessControl;
	}

	@Override
	public void run() {

		while (true) {

			// 从数据库抽取订单基础数据
			List<BPOrderInfoBean> orderInfoList = orderInfoDao
					.selectBaseOrderInfoList();

			// 空轮循休眠控制
			if(orderInfoList.size() < 1){
				
				sleepCount ++;
				
				try {
					if(sleepCount >= 5){
						Thread.sleep(50000);
						sleepCount = 0;
					}else{
						Thread.sleep(5000);
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				continue;
			}
			
			// 按照协议封装
			MsgAgreement msgAgreement = new MsgAgreement();
			msgAgreement.setMsgCategoryEnum(MsgCategoryEnum.BARCODEPRINTER);
			msgAgreement
					.setMsgBarcodePrinterEnum(MsgBarCodePrinterEnum.C2S_ORDERBASEINFO);
			msgAgreement.setMsgBodyBPOrderBaseInfoList(orderInfoList);

			// 对象转json字符串
			String jsonStr = MyJsonUtil.entity2Json(msgAgreement);

			if(null != UserSocketChannel.userChannel){
				//通过socket发送数据到服务端
				UserSocketChannel.userChannel.writeAndFlush(jsonStr);
			}else{
				
				businessControl.doIntelligenceReconnection();
			}

			System.out.println("BaseBPDateExtractionService jsonStr 长度：");
			System.out.println(jsonStr.length());

			try {
				System.out.println("休眠3秒钟");
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
