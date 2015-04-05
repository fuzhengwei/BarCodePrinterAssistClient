package com.icss.bp.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.icss.bp.service.BaseBPDateExtractionService;
import com.icss.bp.service.CodeBPDateExtractionService;
import com.icss.socket.netty.NettyClient;

/**
 * BPBusinessControl
 * 
 * @author fuzhengwei
 * 
 *         2015年4月3日 12:14:31
 * 
 *         打码业务控制
 */
public class BPBusinessControl {

	private ExecutorService es;

	// NettySocketClient
	private NettyClient nettyClient = null;
	// 基础数据线程
	private BaseBPDateExtractionService baseBPDateExtractionService = null;
	// 打码数据线程
	private CodeBPDateExtractionService codeBPDateExtractionService = null;

	public BPBusinessControl(){
		//初始化
		doInitBaseBusiness();
	}
	
	/**
	 * 
	 * doInitBaseBusiness
	 * 
	 * 初始化基础业务数据
	 * 
	 */
	public void doInitBaseBusiness() {
		
		//实例化NettyClient
		doInitNettyClient();
		//实例化数据抽取
		doBPDateExtractionService();
		
	}

	
	/**
	 * doInitNettyClient
	 */
	public void doInitNettyClient() {
		try {
			if(null == nettyClient){
				// 实例化NettyClient
				nettyClient = new NettyClient();
			}
		} catch (Exception e) {
			System.out.println("初始化 NettyClient 失败");
			System.out.println(e.getMessage());
		}
	}

	/**
	 * doBPDateExtractionService
	 */
	public void doBPDateExtractionService() {

		try {
			if(null == baseBPDateExtractionService){
				// 实例化抽取基础数据线程
				baseBPDateExtractionService = new BaseBPDateExtractionService(this);
			}
			
			if(null == codeBPDateExtractionService){
				// 实例化抽取打码数据线程
				codeBPDateExtractionService = new CodeBPDateExtractionService(this);
			}
			
		} catch (Exception e) {
			System.out.println("初始化 baseBPDateExtractionService and codeBPDateExtractionService 失败");
			System.out.println(e.getMessage());
		}

	}

	/**
	 * 
	 * doOnlyOpenNettyToAddThreadPool
	 * 
	 * 重新初始化Netty添加到现有线程池
	 * 
	 */
	public void doIntelligenceReconnection(){
		
		// es 不为空，非Shutdown
		if(null != es && !es.isShutdown()){
			
			//如果为空重新实例化
			if(null == nettyClient){
				nettyClient = new NettyClient();
			}
			
			// 校验是否开启socket连接
			while(!nettyClient.isActiveSocketClient()){
				
				try {
					System.out.println("5 秒钟断线重连 ... ...");
					
					// NettyClient启动线程
					es.execute(nettyClient);
					
					Thread.sleep(5000);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}else{
			
			// 初始化基础业务
			doInitBaseBusiness();
			
			//如果线程池已关闭，那么重新初始化
			doOpenThreadPool();
			
		}
		
	}
	
	/**
	 * 
	 * doOpenThreadPool
	 * 
	 * 开启线程池
	 * 
	 */
	public void doOpenThreadPool() {

		es = Executors.newCachedThreadPool();

		// NettyClient启动线程
		es.execute(nettyClient);
		
		// 休眠5秒，连续等待
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		// 校验是否开启socket连接
		while(!nettyClient.isActiveSocketClient()){
			
			try {
				System.out.println("5 分钟断线重连 ... ...");
				
				// NettyClient启动线程
				es.execute(nettyClient);
				Thread.sleep(5000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		// 基础数据抽取线程
		es.execute(baseBPDateExtractionService);

		// 打码数据抽取线程
		es.execute(codeBPDateExtractionService);
	}
	
	/**
	 * doCloseThreadPool
	 * 
	 * 关闭线程池
	 * 
	 */
	public void doCloseThreadPool(){
		
		try {
			// 退出Executor
			es.shutdown();
			
		} catch (Exception e) {
			System.out.println("doCloseThreadPool");
			System.out.println(e.getMessage());
		}
		
	}

}
