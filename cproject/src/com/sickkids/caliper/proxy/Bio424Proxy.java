package com.sickkids.caliper.proxy;

import java.util.List;
import java.util.logging.Logger;

import com.sickkids.caliper.common.Utils;



public class Bio424Proxy
{
	protected static Logger log = Utils.logger;
	//private Bio424Mp1HuffmanDao bio424HuffmanDao=new Bio424Mp1HuffmanDao();
	//private Bio424Mp1GeneticAlgoDao bio424GeneticAlgoDao=new Bio424Mp1GeneticAlgoDao();
	
	public Bio424Proxy()
	{	
		log.info("Bio424Proxy() is called in Bio424Proxy.java");	
	}
	/*
	public List<Bio424Mp1HuffmanBean> getBio424HuffmanList(String filePath)
	{
		return bio424HuffmanDao.getBio424HuffmanList(filePath);
	}
	public String getBio424HuffmanEncryption(String strToEncrypt) throws DAOException
	{
		return bio424HuffmanDao.getBio424HuffmanEncryption(strToEncrypt);
	}
	public String getBio424HuffmanDecryption(String strToDecrypt) throws DAOException
	{
		return bio424HuffmanDao.getBio424HuffmanDecryption(strToDecrypt);
	}
	public List<Bio424Mp1GeneticAlgoBean> getBio424AncestorList(boolean isSomething)
	{
		return bio424GeneticAlgoDao.getBio424AncestorList(isSomething);
	}
	public List<Bio424Mp1GeneticAlgoBean> generateDecendents(int reqestedGeneration, boolean mutation) throws DAOException
	{
		return bio424GeneticAlgoDao.generateDecendents(reqestedGeneration,mutation);
	}
	public List<AvgNode> getAvgList(boolean mutation) throws DAOException
	{
		return bio424GeneticAlgoDao.getAvgList(mutation);
	}
	*/
}
