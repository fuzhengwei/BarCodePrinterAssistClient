package com.icss.socket.netty;

import org.apache.ibatis.session.SqlSession;

import com.icss.bp.agreement.MsgAgreement;
import com.icss.bp.agreement.MsgBarCodePrinterEnum;
import com.icss.bp.agreement.MsgCategoryEnum;
import com.icss.bp.dao.BPOrderInfoDao;
import com.icss.socket.user.UserSocketChannel;
import com.icss.util.json.MyJsonUtil;
import com.icss.util.mybatis.MybatisSqlSession;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class AssistClientHanlder extends ChannelHandlerAdapter {

	// SqlSession
	private SqlSession session = null;
	// OrderDao
	private BPOrderInfoDao orderInfoDao = null;

	// 构造函数
	public AssistClientHanlder(){
		// 获得session
		session = MybatisSqlSession.getSqlSession();
		// 获得dao操作
		orderInfoDao = session.getMapper(BPOrderInfoDao.class);
	}
	
	/*
	 * channelAction 
	 * 
	 * channel 通道
	 * action  活跃的
	 * 
	 * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
	 * 
	 */
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		
		System.out.println("客户端与服务端通道-开启："+ctx.channel().localAddress()+"channelActive");
		
		//绑定channel
		UserSocketChannel.userChannel = ctx;
		
	}

	/*
	 * channelInactive
	 * 
	 * channel 	通道
	 * Inactive 不活跃的
	 * 
	 * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
	 * 
	 */
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		
		System.out.println("客户端与服务端通道-关闭："+ctx.channel().localAddress()+"channelInactive");
		
		//捕获关闭
		ctx.close();
		
		UserSocketChannel.userChannel = null;
	}

	/*
	 * channelRead
	 * 
	 * channel 通道
	 * Read    读
	 * 
	 * 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据
	 * 但是这个数据在不进行解码时它是ByteBuf类型的后面例子我们在介绍
	 * 
	 */
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		
		try {

			//字符串反序列化
			MsgAgreement msgAgreement = MyJsonUtil.json2Entity(msg.toString());
			
			// 消息处理
			if(msgAgreement.getMsgCategoryEnum() == MsgCategoryEnum.BARCODEPRINTER){
				
				// 基础数据反馈
				if(msgAgreement.getMsgBarcodePrinterEnum() == MsgBarCodePrinterEnum.S2C_ORDERBASEFEED){
					orderInfoDao.updateBaseFeedStateByOrderUUID(msgAgreement.getMsgFeedBackList());
				}
				// 打码数据反馈
				else if(msgAgreement.getMsgBarcodePrinterEnum() == MsgBarCodePrinterEnum.S2C_ORDERCODEFEED){
					orderInfoDao.updateCodeFeedStateByOrderUUID(msgAgreement.getMsgFeedBackList());
				}
				
			}else{
				System.out.println("异常消息");
			}
			
		} catch (Exception e) {
		}
		
	}

	/*
	 * channelReadComplete
	 * 
	 * channel  通道
	 * Read     读取
	 * Complete 完成
	 * 
	 * 在通道读取完成后会在这个方法里通知，对应可以做刷新操作
	 * ctx.flush()
	 * 
	 */
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	/*
	 * exceptionCaught
	 * 
	 * exception	异常
	 * Caught		抓住
	 * 
	 * 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
	 * 
	 */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		
		UserSocketChannel.userChannel = null;
		
		ctx.close();
		System.out.println("异常退出:"+cause.getMessage());
	}
	
}
