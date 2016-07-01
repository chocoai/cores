package com.lanen.service.clinicaltest;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.base.MapResultTransformer;
import com.lanen.model.clinicaltest.TblClinicalTestData;
import com.lanen.model.clinicaltest.TblDataSource;
import com.lanen.model.clinicaltest.TblES;
import com.lanen.model.clinicaltest.TblESLink;
import com.lanen.model.clinicaltest.TblPassageway;
import com.lanen.model.clinicaltest.TblSpecimen;
import com.lanen.model.clinicaltest.TblTrace;
import com.lanen.model.studyplan.TblClinicalTestReq;
import com.lanen.model.studyplan.TblStudyPlan;
import com.lanen.service.studyplan.DictBioChemService;
import com.lanen.service.studyplan.DictBloodCoagService;
import com.lanen.service.studyplan.DictHematService;
import com.lanen.service.studyplan.DictUrineService;
import com.lanen.service.studyplan.TblStudyPlanService;
import com.lanen.util.DateUtil;
import com.lanen.util.NumberValidationUtils;

@Service
public class TblClinicalTestDataServiceImpl extends BaseDaoImpl<TblClinicalTestData> implements TblClinicalTestDataService {

	@Resource
	private PassagewayService passagewayService;
	@Resource
	private TblSpecimenService tblSpecimenService;
	@Resource
	private GetIdService getIdService;
	@Resource
	private TblESService tblESService;
	@Resource
	private TblESLinkService tblESLinkService;
//	@Resource
//	private TblClinicalTestReqIndexService tblClinicalTestReqIndexService;
	@Resource
	private DictBioChemService dictBioChemService;
	@Resource
	private DictHematService dictHematService;
	@Resource
	private DictBloodCoagService dictBloodCoagService;
	@Resource
	private DictUrineService dictUrineService;
	@Resource
	private TblDataSourceService tblDataSourceService;
	@Resource
	private TblStudyPlanService tblStudyPlanService;
	@Resource
	private TblTraceService tblTraceService;
	@SuppressWarnings("unchecked")
	public List<String> getStudyPlanByDate(String date) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<String> list=getSession().createQuery("select distinct t.studyNo FROM TblClinicalTestData t WHERE t.acceptTime BETWEEN ? AND ? ORDER BY  t.studyNo ")//
		.setParameter(0, minDate)
		.setParameter(1, maxDate)
		.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByDate(String date) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.acceptTime BETWEEN ? AND ? ORDER BY  t.acceptTime ASC ")//
		.setParameter(0, minDate)
		.setParameter(1, maxDate)
		.list();
		if(null!=list && list.size()>0){
			list = sortDateList(list);
		}
		return list;
	}

//	@Transactional(isolation=Isolation.READ_UNCOMMITTED)
	public TblClinicalTestData saveBySpecimenCodeTestItemPassageway(
			String specimenCode, int testItem,String instrumentId, String passageway,
			String testData, String testIndexUnit, String collectionTime,TblDataSource tblDataSource) {
		
		//用于最终保存
		List<TblClinicalTestData> tblClinicalTestDataListForSave = new ArrayList<TblClinicalTestData>();
		
		String currentDateStr22 = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		specimenCode = specimenCode.trim();
		    //1.判断数据是否有问题        //&&testData.matches("[0-9]{1,9}\\.{0,1}[0-9]{0,5}")&&!"".equals(testIndexUnit)
		if(!"".equals(instrumentId)&&0!=testItem&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
			//2.根据通道号查询通道实体
			TblPassageway passagewayEntity =passagewayService.getByTestItemPassagewayInstrumentId(testItem, passageway, instrumentId);
			if(null!=passagewayEntity){
				String indexAbbr=passagewayEntity.getTestIndex();
				
				//2.1.通过指标缩写（indexAbbr）   查询指标   设置单位（indexUnit）
				String indexUnit = "";
				switch (testItem) {
				case 1:	indexUnit=dictBioChemService.getIndexUnit(indexAbbr);break;
				case 2:	indexUnit=dictHematService.getIndexUnit(indexAbbr);break;
				case 3:	indexUnit=dictBloodCoagService.getIndexUnit(indexAbbr);break;
				case 4:	indexUnit=dictUrineService.getIndexUnit(indexAbbr);break;
				default:
					break;
				}
				indexUnit =indexUnit == null ? "": indexUnit.trim();
				//2.2.如果检测项目是血常规（2），且是需要  换算值得指标，进行值的换算
				if(testItem == 2 ){
					testIndexUnit = testIndexUnit == null ? "":testIndexUnit.trim();
					testData = testData == null ?"":testData.trim();
					if(!testData.equals("") && !testIndexUnit.equals("") ){
						if(indexAbbr.equalsIgnoreCase("HGB") && indexUnit.equalsIgnoreCase("g/L")
								&& testIndexUnit.equalsIgnoreCase("g/dL")){
							//    *10  ,保留整数
							if(NumberValidationUtils.isPositiveRealNumber(testData)){//是正数
								float testDateF = Float.valueOf(testData);
								testDateF = testDateF*10;
								DecimalFormat df1 = new DecimalFormat("0");
								testData =df1.format(testDateF);
							}
							
						}else if(indexAbbr.equalsIgnoreCase("MCHC") && indexUnit.equalsIgnoreCase("g/L")
								&& testIndexUnit.equalsIgnoreCase("g/dL")){
							//    *10  ,保留整数
							if(NumberValidationUtils.isPositiveRealNumber(testData)){
								float testDateF = Float.valueOf(testData);
								testDateF = testDateF*10;
								DecimalFormat df2 = new DecimalFormat("0");
								testData =df2.format(testDateF);
							}
							
						}else if(indexAbbr.equalsIgnoreCase("RET#") && indexUnit.equalsIgnoreCase("10^9/L")
								&& testIndexUnit.equalsIgnoreCase("10*6/uL")){
							//    *1000  ,保留一位小数
							if(NumberValidationUtils.isPositiveRealNumber(testData)){
								float testDateF = Float.valueOf(testData);
								testDateF = testDateF*1000;
								DecimalFormat df3 = new DecimalFormat("0.0");
								testData =df3.format(testDateF);
							}
						}
					}
				}
				
			    //3.判断数据是否已经传输
				if(isExist(specimenCode,testItem,indexAbbr,collectionTime)){//数据已经存在
					System.out.println(specimenCode+" "+testItem+indexAbbr+collectionTime+currentDateStr22+"数据已存在");
					return null;
				}
				//4.查询    得到指标               未查询  缩写  单位  精度 
				String index2="";
				switch (testItem) {
				case 1:	index2=dictBioChemService.getIndex2ByAbbr(indexAbbr);break;
				case 2:	index2=dictHematService.getIndex2ByAbbr(indexAbbr);break;
				case 3:	index2=dictBloodCoagService.getIndex2ByAbbr(indexAbbr);break;
				case 4:	index2=dictUrineService.getIndex2ByAbbr(indexAbbr);break;
				default:
					break;
				}
				
				//5.查询标本接收登记   ，  得到  临检申请  和    动物信息
				TblSpecimen tblSpecimen =tblSpecimenService.getBySpecimenCodeTestItemTime(specimenCode,testItem,collectionTime);
				if (null != tblSpecimen) {
					// 6.根据标本接收 表 主键(标本Id)与指标缩写 查询 数据是否存在
					
					List<TblClinicalTestData> tblClinicalTestDataList = this
							.getListByTblSpecimenAbbr(tblSpecimen, indexAbbr);
					int validFlag =1; //有效标志
					int confirmFlag = 1;//第几次检测
					if(null != tblClinicalTestDataList){
						confirmFlag = tblClinicalTestDataList.size()+1;
					}
					//之前是只有一个有效，现在（20140711）是全有效
//					if (null != tblClinicalTestDataList && tblClinicalTestDataList.size()>0) {// 检测数据已存在
//						// 检测数据未签字
//						boolean isES = false;
//						for( TblClinicalTestData tblClinicalTestData2:tblClinicalTestDataList){
//							confirmFlag++;
//							if (tblClinicalTestData2.getEs() == 1) {
//								isES = true;
//							} 
//							
//						}
//						if(isES){
//							validFlag =0;
//						}else{
//							validFlag =1;
//							for( TblClinicalTestData tblClinicalTestData2:tblClinicalTestDataList){
//								tblClinicalTestData2.setValidFlag(0);
//								getSession().update(tblClinicalTestData2);
//								
//							}
//						}
//					}

					TblClinicalTestData tblClinicalTestData = new TblClinicalTestData();
					//TODO 
					String dataId = getIdService.getKey("TblClinicalTestData"+testItem);
					dataId = dataId+testItem;
					
					tblClinicalTestData.setDataId(dataId);
					tblClinicalTestData.setTblSpecimen(tblSpecimen);
//					tblClinicalTestData.setStudyNo(tblSpecimen
//							.getTblClinicalTestReq().getTblStudyPlan()
//							.getStudyNo());
					tblClinicalTestData.setStudyNo(tblSpecimen.getTblStudyPlan().getStudyNo());
					
//					tblClinicalTestData.setReqNo(tblSpecimen
//							.getTblClinicalTestReq().getReqNo()
//							+ "");
					tblClinicalTestData.setReqNo(tblSpecimen.getReqNo()+"");
					
					tblClinicalTestData.setAnimalId(tblSpecimen.getAnimalId());
					tblClinicalTestData.setAnimalCode(tblSpecimen
							.getAnimalCode());
					tblClinicalTestData.setAniSerialNum(tblSpecimen
							.getAniSerialNum());// 动物序号
					tblClinicalTestData.setSpecimenCode(specimenCode);
					tblClinicalTestData.setTestItem(testItem);
					// 指标
					tblClinicalTestData.setTestIndex(index2);
					//检验指标缩写
					tblClinicalTestData.setTestIndexAbbr(indexAbbr);
					tblClinicalTestData.setTestData(testData);
					
					//检测单位
					tblClinicalTestData
							.setTestIndexUnit(indexUnit );
					tblClinicalTestData.setCollectionMode(1);// 自动
					tblClinicalTestData.setCollectionTime(DateUtil
							.stringToDate(collectionTime, "yyyyMMddHHmmss"));
					tblClinicalTestData.setAcceptTime(new Date());
					tblClinicalTestData.setEs(0);
					tblClinicalTestData.setConfirmFlag(confirmFlag);
					
					tblClinicalTestData.setValidFlag(validFlag);
					TblDataSource entity = tblDataSourceService
							.saveOrSelect(tblDataSource);

					// ,TblDataSource tblDataSource
					tblClinicalTestData.setTblDataSource(entity);
//					getSession().save(tblClinicalTestData);
					tblClinicalTestDataListForSave.add(tblClinicalTestData);
					//7.如果检测项目是生化（1），且是通过其计算结果的指标，进行指标计算并保存
					if(testItem ==1 && (indexAbbr.equalsIgnoreCase("TP") ||indexAbbr.equalsIgnoreCase("ALB") ||
							indexAbbr.equalsIgnoreCase("TBIL") ||indexAbbr.equalsIgnoreCase("DBIL") )){
						
						if(indexAbbr.equalsIgnoreCase("TP")){
							List<TblClinicalTestData> thisTblClinicalTestDataList = this
							.getListByTblSpecimenAbbr(tblSpecimen, "ALB");
							if(null !=thisTblClinicalTestDataList && !thisTblClinicalTestDataList.isEmpty()){
								for(TblClinicalTestData obj:thisTblClinicalTestDataList){
									String albData = obj.getTestData();
									if(NumberValidationUtils.isPositiveDecimal(albData)&&
											NumberValidationUtils.isPositiveDecimal(testData)) {// 是正数
										float testDataF = Float
												.valueOf(testData);
										float albDataF = Float.valueOf(albData);
										float globDataF = testDataF - albDataF;
//										float agDataF = testDataF / albDataF;  //错的
										float agDataF = albDataF / globDataF;
										DecimalFormat df1 = new DecimalFormat(
												"0.0");
										String globData = df1.format(globDataF);
										String agData = df1.format(agDataF);
										String remark = "";
										remark = confirmFlag + ","
												+ obj.getConfirmFlag() + "@"
												+ "TP:" + testData + "、ALB:"
												+ albData;
									//  保存GLOB
										TblClinicalTestData thisTblClinicalTestData = new TblClinicalTestData();
										//TODO 
										String thisDataId = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId = thisDataId+testItem;
										
										thisTblClinicalTestData
												.setDataId(thisDataId);
										thisTblClinicalTestData
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData
												.setTestItem(testItem);
										// 指标
										String indexName=dictBioChemService.getIndex2ByAbbr("GLOB");
										thisTblClinicalTestData
												.setTestIndex(indexName);
										// 检验指标缩写
										thisTblClinicalTestData
												.setTestIndexAbbr("GLOB");
										thisTblClinicalTestData
												.setTestData(globData);

										// 检测单位
										thisTblClinicalTestData
												.setTestIndexUnit(indexUnit);
										thisTblClinicalTestData
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData
												.setAcceptTime(new Date());
										thisTblClinicalTestData.setEs(0);
										thisTblClinicalTestData
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData
												.setTblDataSource(entity);
										thisTblClinicalTestData
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData);
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData);
										//  保存A/G
										TblClinicalTestData thisTblClinicalTestData2 = new TblClinicalTestData();
										//TODO 
										String thisDataId2 = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId2 = thisDataId2+testItem;
										
										thisTblClinicalTestData2
												.setDataId(thisDataId2);
										thisTblClinicalTestData2
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData2
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData2
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData2
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData2
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData2
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData2
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData2
												.setTestItem(testItem);
										// 指标
										String indexName2=dictBioChemService.getIndex2ByAbbr("A/G");
										thisTblClinicalTestData2
												.setTestIndex(indexName2);
										// 检验指标缩写
										thisTblClinicalTestData2
												.setTestIndexAbbr("A/G");
										thisTblClinicalTestData2
												.setTestData(agData);

										// 检测单位
										thisTblClinicalTestData2
												.setTestIndexUnit("");
										thisTblClinicalTestData2
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData2
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData2
												.setAcceptTime(new Date());
										thisTblClinicalTestData2.setEs(0);
										thisTblClinicalTestData2
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData2
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData2
												.setTblDataSource(entity);
										thisTblClinicalTestData2
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData2);
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData2);
									}
								}
							}
						}else if(indexAbbr.equalsIgnoreCase("ALB")){
							List<TblClinicalTestData> thisTblClinicalTestDataList = this
							.getListByTblSpecimenAbbr(tblSpecimen, "TP");
							if(null !=thisTblClinicalTestDataList && !thisTblClinicalTestDataList.isEmpty()){
								for(TblClinicalTestData obj:thisTblClinicalTestDataList){
									String tpData = obj.getTestData();
									if(NumberValidationUtils.isPositiveDecimal(tpData)&& NumberValidationUtils
													.isPositiveDecimal(testData)) {// 是正数
										float testDataF = Float
												.valueOf(testData);
										float tpDataF = Float.valueOf(tpData);
										float globDataF = tpDataF-testDataF ;
//										float agDataF = tpDataF/testDataF ;
										float agDataF = testDataF/globDataF ;
										DecimalFormat df1 = new DecimalFormat(
												"0.0");
										String globData = df1.format(globDataF);
										String agData = df1.format(agDataF);
										String remark = "";
										remark = obj.getConfirmFlag() + ","
												+confirmFlag  + "@"
												+ "TP:" + tpData + "、ALB:"
												+ testData + "";
										// 保存GLOB
										TblClinicalTestData thisTblClinicalTestData = new TblClinicalTestData();
										//TODO
										String thisDataId = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId = thisDataId+testItem;
										thisTblClinicalTestData
												.setDataId(thisDataId);
										thisTblClinicalTestData
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData
												.setTestItem(testItem);
										// 指标
										String indexName = dictBioChemService
												.getIndex2ByAbbr("GLOB");
										thisTblClinicalTestData
												.setTestIndex(indexName);
										// 检验指标缩写
										thisTblClinicalTestData
												.setTestIndexAbbr("GLOB");
										thisTblClinicalTestData
												.setTestData(globData);

										// 检测单位
										thisTblClinicalTestData
												.setTestIndexUnit(indexUnit);
										thisTblClinicalTestData
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData
												.setAcceptTime(new Date());
										thisTblClinicalTestData.setEs(0);
										thisTblClinicalTestData
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData
												.setTblDataSource(entity);
										thisTblClinicalTestData
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData);
										
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData);
										
										// 保存A/G
										TblClinicalTestData thisTblClinicalTestData2 = new TblClinicalTestData();
										//TODO 
										String thisDataId2 = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId2 = thisDataId2+testItem ;
										thisTblClinicalTestData2
												.setDataId(thisDataId2);
										thisTblClinicalTestData2
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData2
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData2
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData2
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData2
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData2
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData2
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData2
												.setTestItem(testItem);
										// 指标
										String indexName2 = dictBioChemService
												.getIndex2ByAbbr("A/G");
										thisTblClinicalTestData2
												.setTestIndex(indexName2);
										// 检验指标缩写
										thisTblClinicalTestData2
												.setTestIndexAbbr("A/G");
										thisTblClinicalTestData2
												.setTestData(agData);

										// 检测单位
										thisTblClinicalTestData2
												.setTestIndexUnit("");
										thisTblClinicalTestData2
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData2
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData2
												.setAcceptTime(new Date());
										thisTblClinicalTestData2.setEs(0);
										thisTblClinicalTestData2
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData2
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData2
												.setTblDataSource(entity);
										thisTblClinicalTestData2
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData2);
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData2);
									}
								}
							}
						}else if(indexAbbr.equalsIgnoreCase("TBIL")){
							List<TblClinicalTestData> thisTblClinicalTestDataList = this
							.getListByTblSpecimenAbbr(tblSpecimen, "DBIL");
							if(null !=thisTblClinicalTestDataList && !thisTblClinicalTestDataList.isEmpty()){
								for(TblClinicalTestData obj:thisTblClinicalTestDataList){
									String dbilData = obj.getTestData();
									if(NumberValidationUtils.isPositiveDecimal(dbilData)&&
											NumberValidationUtils.isPositiveDecimal(testData)) {// 是正数
										float testDataF = Float
												.valueOf(testData);
										float dbilDataF = Float.valueOf(dbilData);
										float ibilDataF = testDataF -dbilDataF;
										DecimalFormat df1 = new DecimalFormat(
												"0.0");
										String ibilData = df1.format(ibilDataF);
										String remark = "";
										remark = confirmFlag + ","
												+ obj.getConfirmFlag() + "@" + "TBIL:"
												+ testData + "、DBIL:" + dbilData
												+ "";
										// 保存IBIL
										TblClinicalTestData thisTblClinicalTestData = new TblClinicalTestData();
										//TODO 
										String thisDataId = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId = thisDataId+testItem;
										
										thisTblClinicalTestData
												.setDataId(thisDataId);
										thisTblClinicalTestData
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData
												.setTestItem(testItem);
										// 指标
										String indexName = dictBioChemService
												.getIndex2ByAbbr("IBIL");
										thisTblClinicalTestData
												.setTestIndex(indexName);
										// 检验指标缩写
										thisTblClinicalTestData
												.setTestIndexAbbr("IBIL");
										thisTblClinicalTestData
												.setTestData(ibilData);

										// 检测单位
										thisTblClinicalTestData
												.setTestIndexUnit(indexUnit);
										thisTblClinicalTestData
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData
												.setAcceptTime(new Date());
										thisTblClinicalTestData.setEs(0);
										thisTblClinicalTestData
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData
												.setTblDataSource(entity);
										thisTblClinicalTestData
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData);
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData);
									}
								}
							}
							
						}else if(indexAbbr.equalsIgnoreCase("DBIL")){
							List<TblClinicalTestData> thisTblClinicalTestDataList = this
							.getListByTblSpecimenAbbr(tblSpecimen, "TBIL");
							if(null !=thisTblClinicalTestDataList && !thisTblClinicalTestDataList.isEmpty()){
								for(TblClinicalTestData obj:thisTblClinicalTestDataList){
									String tbilData = obj.getTestData();
									if(NumberValidationUtils.isPositiveDecimal(tbilData)&&
											NumberValidationUtils.isPositiveDecimal(testData)) {// 是正数
										float testDataF = Float
												.valueOf(testData);
										float tbilDataF = Float
												.valueOf(tbilData);
										float ibilDataF = tbilDataF-testDataF ;
										DecimalFormat df1 = new DecimalFormat(
												"0.0");
										String ibilData = df1.format(ibilDataF);
										String remark = "";
										remark = obj.getConfirmFlag() + ","
												+ confirmFlag + "@"
												+ "TBIL:" + tbilData + "、DBIL:"
												+ testData + "";
										// 保存IBIL
										TblClinicalTestData thisTblClinicalTestData = new TblClinicalTestData();
										//TODO
										String thisDataId = getIdService
												.getKey("TblClinicalTestData"+testItem);
										thisDataId = thisDataId+testItem;
										thisTblClinicalTestData
												.setDataId(thisDataId);
										thisTblClinicalTestData
												.setTblSpecimen(tblSpecimen);
										thisTblClinicalTestData
												.setStudyNo(tblSpecimen
														.getTblStudyPlan()
														.getStudyNo());
										thisTblClinicalTestData
												.setReqNo(tblSpecimen
														.getReqNo()
														+ "");

										thisTblClinicalTestData
												.setAnimalId(tblSpecimen
														.getAnimalId());
										thisTblClinicalTestData
												.setAnimalCode(tblSpecimen
														.getAnimalCode());
										thisTblClinicalTestData
												.setAniSerialNum(tblSpecimen
														.getAniSerialNum());// 动物序号
										thisTblClinicalTestData
												.setSpecimenCode(specimenCode);
										thisTblClinicalTestData
												.setTestItem(testItem);
										// 指标
										String indexName = dictBioChemService
												.getIndex2ByAbbr("IBIL");
										thisTblClinicalTestData
												.setTestIndex(indexName);
										// 检验指标缩写
										thisTblClinicalTestData
												.setTestIndexAbbr("IBIL");
										thisTblClinicalTestData
												.setTestData(ibilData);

										// 检测单位
										thisTblClinicalTestData
												.setTestIndexUnit(indexUnit);
										thisTblClinicalTestData
												.setCollectionMode(1);// 自动
										thisTblClinicalTestData
												.setCollectionTime(DateUtil
														.stringToDate(
																collectionTime,
																"yyyyMMddHHmmss"));
										thisTblClinicalTestData
												.setAcceptTime(new Date());
										thisTblClinicalTestData.setEs(0);
										thisTblClinicalTestData
												.setConfirmFlag(confirmFlag);

										thisTblClinicalTestData
												.setValidFlag(validFlag);
										// ,TblDataSource tblDataSource
										thisTblClinicalTestData
												.setTblDataSource(entity);
										thisTblClinicalTestData
												.setRemark(remark);// 备注
//										getSession().save(
//												thisTblClinicalTestData);
										tblClinicalTestDataListForSave.add(thisTblClinicalTestData);
									}
								}
							}
						}
					}
					
					if(tblClinicalTestDataListForSave.size() > 0){
						for(TblClinicalTestData obj : tblClinicalTestDataListForSave){
							getSession().save(obj);
						}
					}
					return tblClinicalTestData;
				} else {
					System.out.println(currentDateStr22+"检验编号：" + specimenCode + "标本登记为空");
				}
			}else{
				System.out.println(currentDateStr22+passageway+"通道号未查询到，通道号设置问题");
			}
		}else{
//			if(!"".equals(instrumentId)&&0!=testItem&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
			System.out.println(currentDateStr22+"设备Id:"+instrumentId+",检测项目："+testItem+",检验编号："+specimenCode+"，通道号："+passageway+",检测完成时间："+collectionTime+"参数有问题");
		}
		return null;
	}

	

	/**
	 * 根据接收标本，指标缩写，查询临检数据列表
	 * @param tblSpecimen
	 * @param indexAbbr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> getListByTblSpecimenAbbr(
			TblSpecimen tblSpecimen, String indexAbbr) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.tblSpecimen =  ? AND t.testIndexAbbr = ?    ")//
		.setParameter(0, tblSpecimen)
		.setParameter(1, indexAbbr)
		.list();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public boolean isExist(String specimenCode, int testItem,
			String indexAbbr, String collectionTime) {
		Date date =DateUtil.stringToDate(collectionTime, "yyyyMMddHHmmss");
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.specimenCode=?   AND t.testItem=?   AND t.testIndexAbbr=?  AND  t.collectionTime =?")//
		.setParameter(0, specimenCode)
		.setParameter(1, testItem)
		.setParameter(2, indexAbbr)
		.setParameter(3, date)
		.list();
		if(null!=list&&list.size()>0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByDateES(String date, int es) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.acceptTime BETWEEN ? AND ?   AND t.es =? ")//
		.setParameter(0, minDate)
		.setParameter(1, maxDate)
		.setParameter(2, es)
		.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByDateTestItemES(String date,
			int testItem, int es) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE  t.acceptTime BETWEEN ? AND ?  AND t.es =?  AND t.testItem=?  ")//
		.setParameter(0, minDate)
		.setParameter(1, maxDate)
		.setParameter(2, es)
		.setParameter(3, testItem)
		.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}
		return null;
	}

	public void es(List<String> list, String realName) {
		Date date =new Date();
		TblES tblES=new TblES();
		String esId=getIdService.getKey("TblES");
		tblES.setEsId(esId);
		tblES.setSigner(realName);
		tblES.setEsType(3);
		tblES.setEsTypeDesc("数据接收");
		tblES.setDateTime(date);
		tblESService.save(tblES);//保存  电子签名
		
		if(null!=list&&list.size()>0){
			for(String str:list){
				
				TblClinicalTestData entity=getById(str);
				entity.setEs(1);//1 表示已签字
				//签名链接表
				TblESLink tblESLink =new TblESLink();
				tblESLink.setLinkId(getIdService.getKey("TblESLink"));
				tblESLink.setDataId(str);
				tblESLink.setTableName("TblClinicalTestData");
				tblESLink.setEsType(3);
				tblESLink.setEsTypeDesc("数据接收");
				tblESLink.setRecordTime(date);
				tblESLink.setTblES(tblES);
				tblESLinkService.save(tblESLink);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByStudyNoReqNoTestItem(String studyNo,
			int reqNo, int testItem) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  AND t.testItem=? ORDER BY t.animalCode , animalId ")//
		.setParameter(0, studyNo)
		.setParameter(1, reqNo+"")
		.setParameter(2, testItem)
		.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByStudyNoReqNoTestItemAnimalCode(
			String studyNo, int reqNo, int testItem, String animalId) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  AND t.testItem=? AND t.animalId = ? ORDER BY t.animalCode , animalId ")//
		.setParameter(0, studyNo)
		.setParameter(1, reqNo+"")
		.setParameter(2, testItem)
		.setParameter(3, animalId)
		.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}else{
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByStudyNoTestItemAnimalCode(String studyNo, int testItem,String animalId) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ?  AND t.testItem=? AND t.animalId = ? ORDER BY t.animalCode , animalId ")//
		.setParameter(0, studyNo)
		.setParameter(1, testItem)
		.setParameter(2, animalId)
		.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}else{
			return null;
		}
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public TblClinicalTestData getByTblSpecimen(TblSpecimen tblSpecimen,
			String indexAbbr) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.tblSpecimen =  ? AND t.testIndexAbbr = ?    ")//
		.setParameter(0, tblSpecimen)
		.setParameter(1, indexAbbr)
		.list();
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMapbyClinicalTestReqAndTestItem(
			TblClinicalTestReq tblClinicalTestReq, int testItem) {
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("testDate", "");
		map.put("testUser", "");
		if(null!=tblClinicalTestReq){
			String studyNo=tblClinicalTestReq.getTblStudyPlan().getStudyNo();
			String reqNo = tblClinicalTestReq.getReqNo()+"";
			List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  AND t.testItem=?  ")//
			.setParameter(0, studyNo)
			.setParameter(1, reqNo)
			.setParameter(2, testItem)
			.list();
			
			Date minDate=null;
			Date maxDate=null;
			String testUser;
			List<String> userList = new ArrayList<String>();
			if(null!=list && list.size()>0){
				for(TblClinicalTestData obj:list){
					if(null==minDate){
						minDate=obj.getCollectionTime();
						maxDate=obj.getCollectionTime();
					}else{
						if(obj.getCollectionTime().before(minDate)){
							minDate=obj.getCollectionTime();
						}else if(obj.getCollectionTime().after(maxDate)){
							maxDate=obj.getCollectionTime();
						}
					}
					
					if(obj.getEs()==1){
						TblESLink tblESLink=	tblESLinkService.getByEntityNameAndDataId("TblClinicalTestData",obj.getDataId());
						if(null!=tblESLink){
							testUser=tblESLink.getTblES().getSigner();
							if(!userList.contains(testUser)){
								userList.add(testUser);
							}
						}
					}
				}
				String minDateStr=DateUtil.dateToString(minDate, "yyyy-MM-dd");
				String maxDateStr=DateUtil.dateToString(maxDate, "yyyy-MM-dd");
				String testDate="";
				if(minDateStr.equals(maxDateStr)){
					testDate=minDateStr;
				}else{
					testDate=minDateStr+"--"+maxDateStr;
				}
				if(userList.size()>0){
					testUser="( "+userList.get(0);
					for(int i=1;i< userList.size();i++){
						testUser+=" , "+userList.get(i);
					}
					testUser+=" )";
					map.put("testUser", testUser);
				}
				map.put("testDate", testDate);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getMapbyClinicalTestReqAndTestItemAndAnimalId(
			TblClinicalTestReq tblClinicalTestReq, int testItem, String animalId) {
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("testDate", "");
		map.put("testUser", "");
		if(null!=tblClinicalTestReq){
			String studyNo=tblClinicalTestReq.getTblStudyPlan().getStudyNo();
			String reqNo = tblClinicalTestReq.getReqNo()+"";
			List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  AND t.testItem=? AND t.animalId =?  ")//
			.setParameter(0, studyNo)
			.setParameter(1, reqNo)
			.setParameter(2, testItem)
			.setParameter(3, animalId)
			.list();
			
			Date minDate=null;
			Date maxDate=null;
			String testUser;
			List<String> userList = new ArrayList<String>();
			if(null!=list && list.size()>0){
				for(TblClinicalTestData obj:list){
					if(null==minDate){
						minDate=obj.getCollectionTime();
						maxDate=obj.getCollectionTime();
					}else{
						if(obj.getCollectionTime().before(minDate)){
							minDate=obj.getCollectionTime();
						}else if(obj.getCollectionTime().after(maxDate)){
							maxDate=obj.getCollectionTime();
						}
					}
					
					if(obj.getEs()==1){
						TblESLink tblESLink=	tblESLinkService.getByEntityNameAndDataId("TblClinicalTestData",obj.getDataId());
						if(null!=tblESLink){
							testUser=tblESLink.getTblES().getSigner();
							if(!userList.contains(testUser)){
								userList.add(testUser);
							}
						}
					}
				}
				String minDateStr=DateUtil.dateToString(minDate, "yyyy-MM-dd");
				String maxDateStr=DateUtil.dateToString(maxDate, "yyyy-MM-dd");
				String testDate="";
				if(minDateStr.equals(maxDateStr)){
					testDate=minDateStr;
				}else{
					testDate=minDateStr+"--"+maxDateStr;
				}
				if(userList.size()>0){
					testUser="( "+userList.get(0);
					for(int i=1;i< userList.size();i++){
						testUser+=" , "+userList.get(i);
					}
					testUser+=" )";
					map.put("testUser", testUser);
				}
				map.put("testDate", testDate);
			}
		}
		return map;
	}

//	public Map<String,Object>  saveAllBySpecimenCodeTestItem(
//			String specimenCode, int testItem, String instrumentId,
//			String collectionTime, TblDataSource tblDataSource,
//			List<String[]> list) {
//		Map<String,Object> map = new HashMap<String,Object>();
//		//保存错误信息
//		List<String> msgList =new ArrayList<String>();
//		List<TblClinicalTestData> tblClinicalTestDataList = new ArrayList<TblClinicalTestData>();
//		TblClinicalTestData tblClinicalTestData=null;
//		if(list.size()>0){
//			for(String[] str :list){
//				if(!"".equals(str[0])){
//					tblClinicalTestData = null;
//					int i = 0;
//					while(i < 3){
//						try {
//							tblClinicalTestData=saveBySpecimenCodeTestItemPassageway(specimenCode, testItem, instrumentId, str[0], str[1], str[2], collectionTime, tblDataSource);
//						} catch (Exception e) {
//							System.out.println("数据："+specimenCode+", "+str[0]+", "+ str[1] +" 未接收成功！");
//							e.printStackTrace();
//							i++;
//							if(i < 3){
//								continue;
//							}else{
//								//提示未保存成功数据的 检验编号 通道号
//								msgList.add("数据："+specimenCode+", "+str[0]+", "+ str[1] +" 未接收成功！");
//								e.printStackTrace();
//							}
//						}
//						
//						i = 3;
//					}
//					if(null != tblClinicalTestData){
//						tblClinicalTestDataList.add(tblClinicalTestData);
//					}
//				}
//			}
//		}
//		if(null!=tblClinicalTestDataList && tblClinicalTestDataList.size()>0){
//			tblClinicalTestDataList = sortDateList(tblClinicalTestDataList);
//		}
//		map.put("msgList", msgList);
//		map.put("tblClinicalTestDataList", tblClinicalTestDataList);
//		return map;
//	}
	public List<TblClinicalTestData>  saveAllBySpecimenCodeTestItem(
			String specimenCode, int testItem, String instrumentId,
			String collectionTime, TblDataSource tblDataSource,
			List<String[]> list) {
		
		List<TblClinicalTestData> tblClinicalTestDataListForSave = new ArrayList<TblClinicalTestData>();
		TblClinicalTestData tblClinicalTestData=null;
//		/当前日期
		String currentDateStr22 = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		String passageway = "";
		String testData = "";
		String testIndexUnit = "";
		if(list.size()>0){
			for(String[] str :list){
				if(!"".equals(str[0])){
					tblClinicalTestData = null;
				//-准备一行数据开始
					specimenCode = specimenCode.trim();
					passageway = str[0];
					testData = str[1];
					testIndexUnit = str[2];
					    //1.判断数据是否有问题        //&&testData.matches("[0-9]{1,9}\\.{0,1}[0-9]{0,5}")&&!"".equals(testIndexUnit)
					if(!"".equals(instrumentId)&&0!=testItem&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
						//2.根据通道号查询通道实体
						TblPassageway passagewayEntity =passagewayService.getByTestItemPassagewayInstrumentId(testItem, passageway, instrumentId);
						if(null!=passagewayEntity){
							String indexAbbr=passagewayEntity.getTestIndex();
							
							//2.1.通过指标缩写（indexAbbr）   查询指标   设置单位（indexUnit）
							String indexUnit = "";
							switch (testItem) {
//							case 1:	indexUnit=dictBioChemService.getIndexUnit(indexAbbr);break;
							case 2:	indexUnit=dictHematService.getIndexUnit(indexAbbr);break;
							case 3:	indexUnit=dictBloodCoagService.getIndexUnit(indexAbbr);break;
							case 4:	indexUnit=dictUrineService.getIndexUnit(indexAbbr);break;
							default:
								break;
							}
							indexUnit =indexUnit == null ? "": indexUnit.trim();
							//2.2.如果检测项目是血常规（2），且是需要  换算值得指标，进行值的换算
							if(testItem == 2 ){
								testIndexUnit = testIndexUnit == null ? "":testIndexUnit.trim();
								testData = testData == null ?"":testData.trim();
								if(!testData.equals("") && !testIndexUnit.equals("") ){
									if(indexAbbr.equalsIgnoreCase("HGB") && indexUnit.equalsIgnoreCase("g/L")
											&& testIndexUnit.equalsIgnoreCase("g/dL")){
										//    *10  ,保留整数
										if(NumberValidationUtils.isPositiveRealNumber(testData)){//是正数
											float testDateF = Float.valueOf(testData);
											testDateF = testDateF*10;
											DecimalFormat df1 = new DecimalFormat("0");
											testData =df1.format(testDateF);
										}
										
									}else if(indexAbbr.equalsIgnoreCase("MCHC") && indexUnit.equalsIgnoreCase("g/L")
											&& testIndexUnit.equalsIgnoreCase("g/dL")){
										//    *10  ,保留整数
										if(NumberValidationUtils.isPositiveRealNumber(testData)){
											float testDateF = Float.valueOf(testData);
											testDateF = testDateF*10;
											DecimalFormat df2 = new DecimalFormat("0");
											testData =df2.format(testDateF);
										}
										
									}else if(indexAbbr.equalsIgnoreCase("RET#") && indexUnit.equalsIgnoreCase("10^9/L")
											&& testIndexUnit.equalsIgnoreCase("10*6/uL")){
										//    *1000  ,保留一位小数
										if(NumberValidationUtils.isPositiveRealNumber(testData)){
											float testDateF = Float.valueOf(testData);
											testDateF = testDateF*1000;
											DecimalFormat df3 = new DecimalFormat("0.0");
											testData =df3.format(testDateF);
										}
									}
								}
							}
							
						    //3.判断数据是否已经传输
							if(isExist(specimenCode,testItem,indexAbbr,collectionTime)){//数据已经存在
								System.out.println(specimenCode+" "+testItem+indexAbbr+collectionTime+currentDateStr22+"数据已存在");
								continue;
							}else{
								boolean isExist = false;
								if(tblClinicalTestDataListForSave.size() > 0){
									for(TblClinicalTestData obj : tblClinicalTestDataListForSave){
										if(obj.getTestIndexAbbr().equals(indexAbbr)){
											isExist = true;
											break;
										}
									}
								}
								if(isExist){
									continue;
								}
							}
							//4.查询    得到指标               未查询  缩写  单位  精度 
							String index2="";
							switch (testItem) {
//							case 1:	index2=dictBioChemService.getIndex2ByAbbr(indexAbbr);break;
							case 2:	index2=dictHematService.getIndex2ByAbbr(indexAbbr);break;
							case 3:	index2=dictBloodCoagService.getIndex2ByAbbr(indexAbbr);break;
							case 4:	index2=dictUrineService.getIndex2ByAbbr(indexAbbr);break;
							default:
								break;
							}
							
							//5.查询标本接收登记   ，  得到  临检申请  和    动物信息
							TblSpecimen tblSpecimen =tblSpecimenService.getBySpecimenCodeTestItemTime(specimenCode,testItem,collectionTime);
							if (null != tblSpecimen) {
								// 6.根据标本接收 表 主键(标本Id)与指标缩写 查询 数据是否存在
								
								List<TblClinicalTestData> tblClinicalTestDataList = this
										.getListByTblSpecimenAbbr(tblSpecimen, indexAbbr);
								int validFlag =1; //有效标志
								int confirmFlag = 1;//第几次检测
								if(null != tblClinicalTestDataList){
									confirmFlag = tblClinicalTestDataList.size()+1;
								}

								 tblClinicalTestData = new TblClinicalTestData();
								//TODO 
								String dataId = getIdService.getKey("TblClinicalTestData"+testItem);
								dataId = dataId+testItem;
								
								tblClinicalTestData.setDataId(dataId);
								tblClinicalTestData.setTblSpecimen(tblSpecimen);
//								tblClinicalTestData.setStudyNo(tblSpecimen
//										.getTblClinicalTestReq().getTblStudyPlan()
//										.getStudyNo());
								tblClinicalTestData.setStudyNo(tblSpecimen.getTblStudyPlan().getStudyNo());
								
//								tblClinicalTestData.setReqNo(tblSpecimen
//										.getTblClinicalTestReq().getReqNo()
//										+ "");
								tblClinicalTestData.setReqNo(tblSpecimen.getReqNo()+"");
								
								tblClinicalTestData.setAnimalId(tblSpecimen.getAnimalId());
								tblClinicalTestData.setAnimalCode(tblSpecimen
										.getAnimalCode());
								tblClinicalTestData.setAniSerialNum(tblSpecimen
										.getAniSerialNum());// 动物序号
								tblClinicalTestData.setSpecimenCode(specimenCode);
								tblClinicalTestData.setTestItem(testItem);
								// 指标
								tblClinicalTestData.setTestIndex(index2);
								//检验指标缩写
								tblClinicalTestData.setTestIndexAbbr(indexAbbr);
								tblClinicalTestData.setTestData(testData);
								
								//检测单位
								tblClinicalTestData
										.setTestIndexUnit(indexUnit );
								tblClinicalTestData.setCollectionMode(1);// 自动
								tblClinicalTestData.setCollectionTime(DateUtil
										.stringToDate(collectionTime, "yyyyMMddHHmmss"));
								tblClinicalTestData.setAcceptTime(new Date());
								tblClinicalTestData.setEs(0);
								tblClinicalTestData.setConfirmFlag(confirmFlag);
								
								tblClinicalTestData.setValidFlag(validFlag);
								TblDataSource entity = tblDataSourceService
										.saveOrSelect(tblDataSource);

								// ,TblDataSource tblDataSource
								tblClinicalTestData.setTblDataSource(entity);
//								getSession().save(tblClinicalTestData);
								tblClinicalTestDataListForSave.add(tblClinicalTestData);
								
							} else {
								System.out.println(currentDateStr22+"检验编号：" + specimenCode + "标本登记为空");
							}
						}else{
							System.out.println(currentDateStr22+passageway+"通道号未查询到，通道号设置问题");
						}
					}else{
//						if(!"".equals(instrumentId)&&0!=testItem&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
						System.out.println(currentDateStr22+"设备Id:"+instrumentId+",检测项目："+testItem+",检验编号："+specimenCode+"，通道号："+passageway+",检测完成时间："+collectionTime+"参数有问题");
					}
					
				//准备一行数据结束
				}
			}
		}
		if(tblClinicalTestDataListForSave.size() > 0){
			for(TblClinicalTestData obj : tblClinicalTestDataListForSave){
				getSession().save(obj);
			}
		}
		return tblClinicalTestDataListForSave;
	}

	public List<TblClinicalTestData> saveAllBySpecimenCodeTestItem_3(
			String specimenCode, String instrumentId,
			TblDataSource tblDataSource, List<String[]> list) {
		List<TblClinicalTestData> tblClinicalTestDataListForSave = new ArrayList<TblClinicalTestData>();
		TblClinicalTestData tblClinicalTestData=null;
//		/当前日期
		String currentDateStr22 = DateUtil.dateToString(new Date(), "yyyy-MM-dd");
		String passageway = "";
		String testData = "";
		String testIndexUnit = "";
		String collectionTime = "";
		if(list.size()>0){
			for(String[] str :list){
				if(!"".equals(str[0])){
					tblClinicalTestData = null;
				//-准备一行数据开始
					specimenCode = specimenCode.trim();
					passageway = str[0];
					testData = str[1];
					testIndexUnit = str[2];
					collectionTime = str[3];
					    //1.判断数据是否有问题        //&&testData.matches("[0-9]{1,9}\\.{0,1}[0-9]{0,5}")&&!"".equals(testIndexUnit)
					if(!"".equals(instrumentId)&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
						//2.根据通道号查询通道实体
						TblPassageway passagewayEntity =passagewayService.getByTestItemPassagewayInstrumentId(3, passageway, instrumentId);
						if(null!=passagewayEntity){
							String indexAbbr=passagewayEntity.getTestIndex();
							
							//2.1.通过指标缩写（indexAbbr）   查询指标   设置单位（indexUnit）
							String indexUnit = dictBloodCoagService.getIndexUnit(indexAbbr);
							indexUnit =indexUnit == null ? "": indexUnit.trim();
							//2.2.如果检测项目是血常规（2），且是需要  换算值得指标，进行值的换算
							
						    //3.判断数据是否已经传输
							if(isExist(specimenCode,3,indexAbbr,collectionTime)){//数据已经存在
								System.out.println(specimenCode+" "+indexAbbr+collectionTime+currentDateStr22+"数据已存在");
								continue;
							}else{
								boolean isExist = false;
								if(tblClinicalTestDataListForSave.size() > 0){
									for(TblClinicalTestData obj : tblClinicalTestDataListForSave){
										if(obj.getTestIndexAbbr().equals(indexAbbr)){
											isExist = true;
											break;
										}
									}
								}
								if(isExist){
									continue;
								}
							}
							//4.查询    得到指标               未查询  缩写  单位  精度 
							String index2 = dictBloodCoagService.getIndex2ByAbbr(indexAbbr);
							
							//5.查询标本接收登记   ，  得到  临检申请  和    动物信息
							TblSpecimen tblSpecimen =tblSpecimenService.getBySpecimenCodeTestItemTime(specimenCode,3,collectionTime);
							if (null != tblSpecimen) {
								// 6.根据标本接收 表 主键(标本Id)与指标缩写 查询 数据是否存在
								
								List<TblClinicalTestData> tblClinicalTestDataList = this
										.getListByTblSpecimenAbbr(tblSpecimen, indexAbbr);
								int validFlag =1; //有效标志
								int confirmFlag = 1;//第几次检测
								if(null != tblClinicalTestDataList){
									confirmFlag = tblClinicalTestDataList.size()+1;
								}

								 tblClinicalTestData = new TblClinicalTestData();
								//TODO 
								String dataId = getIdService.getKey("TblClinicalTestData"+3);
								dataId = dataId+3;
								
								tblClinicalTestData.setDataId(dataId);
								tblClinicalTestData.setTblSpecimen(tblSpecimen);
//								tblClinicalTestData.setStudyNo(tblSpecimen
//										.getTblClinicalTestReq().getTblStudyPlan()
//										.getStudyNo());
								tblClinicalTestData.setStudyNo(tblSpecimen.getTblStudyPlan().getStudyNo());
								
//								tblClinicalTestData.setReqNo(tblSpecimen
//										.getTblClinicalTestReq().getReqNo()
//										+ "");
								tblClinicalTestData.setReqNo(tblSpecimen.getReqNo()+"");
								
								tblClinicalTestData.setAnimalId(tblSpecimen.getAnimalId());
								tblClinicalTestData.setAnimalCode(tblSpecimen
										.getAnimalCode());
								tblClinicalTestData.setAniSerialNum(tblSpecimen
										.getAniSerialNum());// 动物序号
								tblClinicalTestData.setSpecimenCode(specimenCode);
								tblClinicalTestData.setTestItem(3);
								// 指标
								tblClinicalTestData.setTestIndex(index2);
								//检验指标缩写
								tblClinicalTestData.setTestIndexAbbr(indexAbbr);
								tblClinicalTestData.setTestData(testData);
								
								//检测单位
								tblClinicalTestData
										.setTestIndexUnit(indexUnit );
								tblClinicalTestData.setCollectionMode(1);// 自动
								tblClinicalTestData.setCollectionTime(DateUtil
										.stringToDate(collectionTime, "yyyyMMddHHmmss"));
								tblClinicalTestData.setAcceptTime(new Date());
								tblClinicalTestData.setEs(0);
								tblClinicalTestData.setConfirmFlag(confirmFlag);
								
								tblClinicalTestData.setValidFlag(validFlag);
								TblDataSource entity = tblDataSourceService
										.saveOrSelect(tblDataSource);

								// ,TblDataSource tblDataSource
								tblClinicalTestData.setTblDataSource(entity);
//								getSession().save(tblClinicalTestData);
								tblClinicalTestDataListForSave.add(tblClinicalTestData);
								
							} else {
								System.out.println(currentDateStr22+"检验编号：" + specimenCode + "标本登记为空");
							}
						}else{
							System.out.println(currentDateStr22+passageway+"通道号未查询到，通道号设置问题");
						}
					}else{
//						if(!"".equals(instrumentId)&&0!=testItem&&specimenCode.matches("[0-9]{4,11}")&&!passageway.equals("")&&collectionTime.matches("[0-9]{14}")){
						System.out.println(currentDateStr22+"设备Id:"+instrumentId+",检测项目："+3+",检验编号："+specimenCode+"，通道号："+passageway+",检测完成时间："+collectionTime+"参数有问题");
					}
					
				//准备一行数据结束
				}
			}
		}
		if(tblClinicalTestDataListForSave.size() > 0){
			for(TblClinicalTestData obj : tblClinicalTestDataListForSave){
				getSession().save(obj);
			}
		}
		return tblClinicalTestDataListForSave;
	}
	
	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByStudyNoReqNo(String studyNo,
			int reqNo) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  ORDER BY t.animalCode , animalId ")//
		.setParameter(0, studyNo)
		.setParameter(1, reqNo+"")
		.list();
		if(null!=list&&list.size()>0){
			list =sortDateList(list);
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByStudyNoReqNoTestItem(
			String studyNoPara, int reqNoPara, int testItemPara, int es) {
		List<TblClinicalTestData> list=getSession().createQuery("FROM TblClinicalTestData t WHERE t.studyNo=  ? AND t.reqNo= ?  AND t.testItem=?   AND t.es=? ORDER BY t.animalCode , animalId ")//
		.setParameter(0, studyNoPara)
		.setParameter(1, reqNoPara+"")
		.setParameter(2, testItemPara)
		.setParameter(3, es)
		.list();
		if(null!=list&&list.size()>0){
			list =sortDateList(list);
			return list;
		}
		return null;
	}

	public Map<String, List<String>> getMapByDate(String dateStr) {
		Map<String,List<String>> map =new HashMap<String,List<String>>();
		List<TblClinicalTestData> dataList= findByDateES(dateStr,0);
		if(null!=dataList && dataList.size()>0){
			List<String> studyNoList =new ArrayList<String>(); 
			for(TblClinicalTestData obj:dataList){
				String studyNo = obj.getStudyNo();
				if(!studyNoList.contains(studyNo)){
					studyNoList.add(studyNo);
					TblStudyPlan tblStudyPlan = tblStudyPlanService.getById(studyNo);
					if(null!=tblStudyPlan){
					 Set<TblClinicalTestReq> set=tblStudyPlan.getTblClinicalTestReqs();
					 if(null!=set && set.size()>0){
						 Iterator<TblClinicalTestReq>  it = set.iterator();
						 List<String> reqList = new ArrayList<String>();
						 while(it.hasNext()){
							 TblClinicalTestReq req=it.next(); 
							 if(req.getEs() == 1){
								 reqList.add(req.getReqNo()+":"
										 +DateUtil.dateToString(req.getCreateDate(), "yyyy-MM-dd"));
							 }
						 }
						 map.put(studyNo, reqList);
					 }
					}
				}
			}
		}
		
		return map;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> findByDateTestItemESStudyNOReqNoCode(
			String date, int testItem, int es, String studyNo2, int reqNo2,
			String beginCodeStr2, String endCodeStr2) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<TblClinicalTestData> list=null;
		
		String sql ="FROM TblClinicalTestData t WHERE  t.acceptTime BETWEEN :minDate AND :maxDate  AND t.es =:es";
		if(testItem!=0){
			sql+=" AND t.testItem = :testItem ";
		}
		if(null!=studyNo2 && !studyNo2.equals("")){
			sql+=" AND t.studyNo = :studyNo ";
		}
		if(reqNo2 != 0){
			sql+=" AND t.reqNo = :reqNo ";
		}
		if(null != beginCodeStr2 && !"".equals(beginCodeStr2) && null != endCodeStr2 && !"".equals(endCodeStr2) ){
			sql+=" AND t.specimenCode >= :beginCodeStr AND t.specimenCode <= :endCodeStr ";
		}else if(null != beginCodeStr2 && !"".equals(beginCodeStr2)){
			sql+=" AND t.specimenCode >= :beginCodeStr  ";
		}else if(null != endCodeStr2 && !"".equals(endCodeStr2)){
			sql+="  AND t.specimenCode <= :endCodeStr ";
		}
		Query query = getSession().createQuery(sql);
		
		query.setParameter("minDate", minDate);
		query.setParameter("maxDate", maxDate);
		query.setParameter("es", es);
		
		if(testItem!=0){
//			sql+=" AND t.testItem = :testItem ";
			query.setParameter("testItem", testItem);
		}
		if(null!=studyNo2 && !studyNo2.equals("")){
//			sql+=" AND t.studyNo = :studyNo ";
			query.setParameter("studyNo", studyNo2);
		}
		if(reqNo2 != 0){
//			sql+=" AND t.reqNo = :reqNo ";
			query.setParameter("reqNo", reqNo2+"");
		}
		if(null != beginCodeStr2 && !"".equals(beginCodeStr2) && null != endCodeStr2 && !"".equals(endCodeStr2) ){
//			sql+=" AND t.specimenCode >= :beginCodeStr AND t.specimenCode <= :endCodeStr ";
			query.setParameter("beginCodeStr", beginCodeStr2);
			query.setParameter("endCodeStr", endCodeStr2);
		}else if(null != beginCodeStr2 && !"".equals(beginCodeStr2)){
//			sql+=" AND t.specimenCode >= :beginCodeStr  ";
			query.setParameter("beginCodeStr", beginCodeStr2);
		}else if(null != endCodeStr2 && !"".equals(endCodeStr2)){
//			sql+="  AND t.specimenCode <= :endCodeStr ";
			query.setParameter("endCodeStr", endCodeStr2);
		}
		list=query.list();
		if(null!=list&&list.size()>0){
			list = sortDateList(list);
			return list;
		}
		return null;
	}
	//排序：检测项目  检验编号  指标
	private List<TblClinicalTestData> sortDateList(
			List<TblClinicalTestData> list) {
		final Map<String,Integer> bioChemMap = dictBioChemService.getMap();
		final Map<String,Integer> bloodCoagMap = dictBloodCoagService.getMap();
		final Map<String,Integer> hematMap = dictHematService.getMap();
		final Map<String,Integer> urineMap = dictUrineService.getMap();
		Collections.sort(list,new Comparator<TblClinicalTestData>(){
			//检测项目     检验编号    检验指标
			public int compare(TblClinicalTestData obj1,
					TblClinicalTestData obj2) {
				if(obj1.getTestItem() != obj2.getTestItem()){
					return obj1.getTestItem() - obj2.getTestItem();
				}else{
					if(!obj1.getSpecimenCode().equals(obj2.getSpecimenCode())){
						return obj1.getSpecimenCode().compareTo(obj2.getSpecimenCode());
					}else{
						int testItem=obj1.getTestItem();
						if(null != obj1.getTestIndexAbbr() && !obj1.getTestIndexAbbr().equals(obj2.getTestIndexAbbr())){
							switch (testItem) {
							case 1:if(null == bioChemMap.get(obj1.getTestIndexAbbr())){
								     return 1;
									}
								  if(null == bioChemMap.get(obj2.getTestIndexAbbr())){
									  return -1;
								  	}
								  return bioChemMap.get(obj1.getTestIndexAbbr())-bioChemMap.get(obj2.getTestIndexAbbr());
							case 2:
									if(null == hematMap.get(obj1.getTestIndexAbbr())){
								     return 1;
									}
									if(null == hematMap.get(obj2.getTestIndexAbbr())){
									  return -1;
								  	}
									return hematMap.get(obj1.getTestIndexAbbr())-hematMap.get(obj2.getTestIndexAbbr());
							case 3:if(null == bloodCoagMap.get(obj1.getTestIndexAbbr())){
									     return 1;
									}
								  if(null == bloodCoagMap.get(obj2.getTestIndexAbbr())){
									  return -1;
								  	}
								  return bloodCoagMap.get(obj1.getTestIndexAbbr())-bloodCoagMap.get(obj2.getTestIndexAbbr());
							case 4:if(null == urineMap.get(obj1.getTestIndexAbbr())){
										return 1;
									}
								  if(null == urineMap.get(obj2.getTestIndexAbbr())){
									  return -1;
								  	}
								  return urineMap.get(obj1.getTestIndexAbbr())-urineMap.get(obj2.getTestIndexAbbr());
							default:
									break;
							}
						}else{
							return 0;
						}
					}
				}
				return 0;
			}
			
		});
		return list;
	}

	public void esAndSetValid(List<String> list, String realName,
			List<String> validDataIdList, List<String> inValidDataIdList) {
		Date date =new Date();
		TblES tblES=new TblES();
		String esId=getKey("TblES");
		tblES.setEsId(esId);
		tblES.setSigner(realName);
		tblES.setEsType(3);
		tblES.setEsTypeDesc("数据接收");
		tblES.setDateTime(date);
		
		if(null!=list&&list.size()>0){
			
			tblESService.save(tblES);//保存  电子签名
			
			for(String dataId:list){
//				TblClinicalTestData entity=getById(dataId);
//				entity.setEs(1);//1 表示已签字
				//签名链接表
				TblESLink tblESLink =new TblESLink();
				tblESLink.setLinkId(getKey("TblESLink"));
				tblESLink.setDataId(dataId);
				tblESLink.setTableName("TblClinicalTestData");
				tblESLink.setEsType(3);
				tblESLink.setEsTypeDesc("数据接收");
				tblESLink.setRecordTime(date);
				tblESLink.setTblES(tblES);
				tblESLinkService.save(tblESLink);
			}
		}
		
		//更新数据签字
		List<List<String>> dataIdListList = new ArrayList<List<String>>();
		int  i = 0;
		List<String> dataIdList = new ArrayList<String>();
		for(String dataId:list){
			i++;
			dataIdList.add(dataId);
			if(i%1000 == 0){
				dataIdListList.add(dataIdList);
				dataIdList = new ArrayList<String>();
			}
		}
		if(i%1000 != 0){
			dataIdListList.add(dataIdList);
		}
		
		for(List<String> dataList: dataIdListList){
			String sql = "update CoresStudy.dbo.tblClinicalTestData set es  = 1"+
						" from CoresStudy.dbo.tblClinicalTestData as data1"+
						" where data1.dataId in (:dataIdList) and data1.es = 0";
			getSession().createSQLQuery(sql).setParameterList("dataIdList", dataList).executeUpdate();
		}
		
		
		if(null!=validDataIdList&&validDataIdList.size()>0){
			for(String dataId:validDataIdList){
				TblClinicalTestData tblClinicalTestData=getById(dataId);
				tblClinicalTestData.setValidFlag(1);
				if(tblClinicalTestData.getEs() == 1){
					tblClinicalTestData.setSelected(1);
				}
				getSession().update(tblClinicalTestData);
			}
		}
		if(null!=inValidDataIdList&&inValidDataIdList.size()>0){
			for(String dataId:inValidDataIdList){
				TblClinicalTestData tblClinicalTestData=getById(dataId);
				tblClinicalTestData.setValidFlag(0);
				if(tblClinicalTestData.getEs() == 1){
					tblClinicalTestData.setSelected(0);
				}
				getSession().update(tblClinicalTestData);
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> getListByStudyNoReqNoTestItemDataIdList(
			String studyNo, int reqNo, int testItem, List<String> dataIdList) {
		String sql="From TblClinicalTestData t where  1 =1 and t.studyNo =:studyNo ";
		if(reqNo!=0){
			sql=sql+" and t.reqNo = :reqNo ";
		}
		if(testItem!=0){
			sql=sql+" and t.testItem = :testItem ";
		}
		if(null!=dataIdList &&dataIdList.size()>0){
			sql=sql+" and t.dataId in ( :dataIds )";
		}
		
		
		Query query = getSession().createQuery(sql);
		query.setParameter("studyNo", studyNo+"");
		if(reqNo!=0){
			query.setParameter("reqNo", reqNo+"");
		}
		if(testItem!=0){
			query.setParameter("testItem", testItem);
		}
		if(null!=dataIdList &&dataIdList.size()>0){
			query.setParameterList("dataIds", dataIdList);
		}
		
		return query.list();
	}

	public void deleteAndRecordTrace(List<String> list, String realName,
			String reason,String hostName) {
		
		if(null != list && list.size()>0){
			Date date = new Date();
			TblTrace trace = null;
			for(String dataId:list){
				TblClinicalTestData tblClinicalTestData = getById(dataId);
				String oldValue = 	tblClinicalTestData.getTestData();
				String newValue = 	tblClinicalTestData.getStudyNo()+","+
								  	tblClinicalTestData.getReqNo()+","+
								  	tblClinicalTestData.getTestItem()+","+
								  	tblClinicalTestData.getSpecimenCode()+","+
								  	tblClinicalTestData.getTestIndexAbbr()+","+
								  	DateUtil.dateToString(tblClinicalTestData.getCollectionTime()
								  			, "yyyyMMdd HH:mm:ss");
				trace = new TblTrace();
				trace.setId(getKey("TblTrace"));
				trace.setTableName("TblClinicalTestData");
				trace.setDataId(dataId);
				trace.setHost(hostName);
				trace.setModifyReason(reason);
				trace.setModifyTime(date);
				trace.setOperateMode(2);//删除
				trace.setOperator(realName);
				trace.setNewValue(newValue);//新数据里存放的是：课题编号，申请编号，检测项目，检验编号，检验指标缩写，检测完成时间
				trace.setOldValue(oldValue);//原数据
				//保存修改痕迹
				tblTraceService.save(trace);
				//删除临检数据
				getSession().delete(tblClinicalTestData);
			}
			
			
		}
		
	}

	public boolean isHaveValidData(String specimenCode, int testItem,
			String testIndexAbbr,String sutdyNo,String reqNo) {
		List<?> list = getSession().createQuery("From TblClinicalTestData where es =1 and validFlag =1 and " +
				" specimenCode = ? and testItem = ? and testIndexAbbr =? and studyNo = ? and reqNo = ?  ")
		.setParameter(0, specimenCode)
		.setParameter(1, testItem)
		.setParameter(2, testIndexAbbr)
		.setParameter(3, sutdyNo)
		.setParameter(4, reqNo)
		.list();
		return (null!=list && list.size()>0);
	}

	public void updateAllTblClinicalTestData(List<TblClinicalTestData> list) {
		for(TblClinicalTestData obj : list){
		   update(obj);
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<String> getDateStrListByStudyNoReqNo(String studyNo,
			String reqNo, int testItem) {
		String sql ="select collectionTime from tblClinicalTestData where " +
				" studyNo = :studyNo and reqNo = :reqNo and  testItem = :testItem" +
				"  and es = 1 order by collectionTime ";
		List<Date> dateList = getSession().createSQLQuery(sql)
			.setParameter("studyNo", studyNo)
			.setParameter("reqNo", reqNo)
			.setParameter("testItem", testItem)
			.list();
		List<String> dateStrList = new ArrayList<String>();
		if(null != dateList && dateList.size()>0){
			String dateStr ="";
			for(Date date :dateList){
				dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
				if(!dateStrList.contains(dateStr)){
					dateStrList.add(dateStr);
				}
			}
		}
		return dateStrList;
	}
	/** 查询动物animalId,animalCode,根据  专题、申请编号，检测项目、日期列表
	 */
	public List<?> getAnimalList(String studyNo, String reqNo, int testItem,
			List<String> selectedDateStrList) {
		
		if(selectedDateStrList != null && selectedDateStrList.size()>0){
			String sql ="select distinct b.animalId ,b.animalCode from tblClinicalTestData b where " +
					"  b.studyNo = :studyNo and b.reqNo = :reqNo and  b.testItem = :testItem " +
					" and( es = 1  " ;
			for(int i = 0 ;i<selectedDateStrList.size();i++){
				sql =sql+" or b.collectionTime BETWEEN :minDate"+i +" and :maxDate"+i ;
			}
			sql = sql +" ) ";
			Query query = getSession().createSQLQuery(sql);
			query.setParameter("studyNo", studyNo);
			query.setParameter("reqNo", reqNo);
			query.setParameter("testItem", testItem);
			for(int i = 0 ;i<selectedDateStrList.size();i++){
				Date minDate= DateUtil.stringToDate(selectedDateStrList.get(i)+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
				Date maxDate= DateUtil.stringToDate(selectedDateStrList.get(i)+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
				query.setParameter("minDate"+i, minDate);
				query.setParameter("maxDate"+i, maxDate);
			}
			List<?> list = query.list();
			return list;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> getListByStudyNoReqNoTestItemAnimalIdList(
			String studyNo, String reqNo, int testItem,
			List<String> animalIdList, List<String> dateStrList) {
		if (animalIdList == null || animalIdList.size() < 1
				|| null == dateStrList || dateStrList.size() < 1) {
			return null;
		}
		String hql = "From TblClinicalTestData b where b.animalId in (:animalIdList) and  "
				+ "  b.studyNo = :studyNo and b.reqNo = :reqNo and  b.testItem = :testItem "
				+ " and( es = 1  ";
		for (int i = 0; i < dateStrList.size(); i++) {
			hql = hql + " or b.collectionTime BETWEEN :minDate" + i
					+ " and :maxDate" + i;
		}
		hql = hql + " ) ";
		Query query = getSession().createQuery(hql);
		query.setParameterList("animalIdList", animalIdList);
		query.setParameter("studyNo", studyNo);
		query.setParameter("reqNo", reqNo);
		query.setParameter("testItem", testItem);
		for (int i = 0; i < dateStrList.size(); i++) {
			Date minDate = DateUtil.stringToDate(dateStrList.get(i)
					+ " 00:00:00", "yyyy-MM-dd HH:mm:ss");
			Date maxDate = DateUtil.stringToDate(dateStrList.get(i)
					+ " 23:59:59", "yyyy-MM-dd HH:mm:ss");
			query.setParameter("minDate" + i, minDate);
			query.setParameter("maxDate" + i, maxDate);
		}
		List<TblClinicalTestData> list = query.list();
		return list;
	}

	public boolean isHasESData(String studyNo, String reqNo) {
		String sql = "select animalId from tblClinicalTestData where "
				+ " studyNo = :studyNo and reqNo = :reqNo "
				+ "  and es = 1 order by collectionTime ";
		List<?> list =getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("reqNo", reqNo)
				.list();
		if(null == list || list.size()<1){
			return false;
		}else{
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getSpecimenRecDateStrList(String studyNo, String reqNo,
			int testItem, String testDate) {
		String sql = "select  s.recDate from tblClinicalTestData AS data LEFT OUTER JOIN" +
				"  tblSpecimen AS s ON data.specimenId = s.specimenId where "
				+ " data.studyNo = :studyNo and data.reqNo = :reqNo and  data.testItem = :testItem"
				+ "  and data.es = 1  and data.collectionTime between '"+testDate+" 00:00:00'"+" and '"+
						testDate+" 23:59:59' order by s.recDate ";
		List<Date> dateList = getSession().createSQLQuery(sql).setParameter(
				"studyNo", studyNo).setParameter("reqNo", reqNo).setParameter(
				"testItem", testItem).list();
		List<String> dateStrList = new ArrayList<String>();
		if (null != dateList && dateList.size() > 0) {
			String dateStr = "";
			for (Date date : dateList) {
				dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
				if (!dateStrList.contains(dateStr)) {
					dateStrList.add(dateStr);
				}
			}
		}
		return dateStrList;
	}

	@SuppressWarnings("unchecked")
	public List<String> getTestDateStrList(String studyNo, String reqNo,
			int testItem, String specimenRecDate) {
		String sql = "select  data.collectionTime from tblClinicalTestData AS data LEFT OUTER JOIN"
				+ "  tblSpecimen AS s ON data.specimenId = s.specimenId where "
				+ " data.studyNo = :studyNo and data.reqNo = :reqNo and  data.testItem = :testItem"
				+ "  and data.es = 1  and s.recDate between '"
				+ specimenRecDate+ " 00:00:00'"
				+ " and '"+ specimenRecDate
				+ " 23:59:59' order by s.recDate ";
		List<Date> dateList = getSession().createSQLQuery(sql).setParameter(
				"studyNo", studyNo).setParameter("reqNo", reqNo).setParameter(
				"testItem", testItem).list();
		List<String> dateStrList = new ArrayList<String>();
		if (null != dateList && dateList.size() > 0) {
			String dateStr = "";
			for (Date date : dateList) {
				dateStr = DateUtil.dateToString(date, "yyyy-MM-dd");
				if (!dateStrList.contains(dateStr)) {
					dateStrList.add(dateStr);
				}
			}
		}
		return dateStrList;
	}

	public List<?> get4StrList(String studyNo, String reqNo, int testItem,
			String specimenRecDate, String testDate) {
		if(null == studyNo || studyNo.isEmpty()){
			return null;
		}
		if(0 ==testItem){
			return null;
		}
		if(null == studyNo || studyNo.isEmpty()){
			return null;
		}
		if(null == specimenRecDate || specimenRecDate.isEmpty()){
			return null;
		}
		if(null == testDate || testDate.isEmpty()){
			return null;
		}
		String sql ="SELECT DISTINCT data.specimenCode, data.animalCode, i2.gender, data.animalId"+
					" from tblClinicalTestData AS data LEFT OUTER JOIN"+
		            " tblSpecimen AS s ON data.specimenId = s.specimenId LEFT OUTER JOIN"+
		            " tblClinicalTestReqIndex2 AS i2 ON s.tblClinicalTestReqIndex2Id = i2.id"+
		            " WHERE data.es = 1 and   (data.studyNo = :studyNo) AND (data.reqNo = :reqNo) AND (data.testItem = :testItem) " +
		            " AND (s.recDate BETWEEN  '"+specimenRecDate+" 00:00:00' AND '"+specimenRecDate+" 23:59:59') " +
		            " and (data.collectionTime BETWEEN  '"+testDate+" 00:00:00' AND '"+testDate+" 23:59:59' )";
		List<?> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo", studyNo)
				.setParameter("reqNo", reqNo)
				.setParameter("testItem", testItem)
				.list();
		
		
		return list;
	}

	public List<?> get9StrListByStudyNoReqNoTestItemAnimalIdList(
			String studyNo, String reqNo, int testItem, String specimenRecDate,
			String testDate, List<String> animalIdList) {
		if (null == studyNo || studyNo.isEmpty()) {
			return null;
		}
		if (0 == testItem) {
			return null;
		}
		if (null == studyNo || studyNo.isEmpty()) {
			return null;
		}
		if (null == specimenRecDate || specimenRecDate.isEmpty()) {
			return null;
		}
		if (null == testDate || testDate.isEmpty()) {
			return null;
		}
		if (null == animalIdList || animalIdList.size() < 1) {
			return null;
		}
		String sql = "SELECT DISTINCT data.specimenCode, data.animalCode, i2.gender, data.animalId " +
				  " ,data.testIndexAbbr,data.testData,data.aniSerialNum,s.specimenKind,data.testIndexUnit"
				+ " from tblClinicalTestData AS data LEFT OUTER JOIN"
				+ " tblSpecimen AS s ON data.specimenId = s.specimenId LEFT OUTER JOIN"
				+ " tblClinicalTestReqIndex2 AS i2 ON s.tblClinicalTestReqIndex2Id = i2.id"
				+ " WHERE data.es = 1 and   (data.studyNo = :studyNo) AND (data.reqNo = :reqNo) AND (data.testItem = :testItem) "
				+ " AND (s.recDate BETWEEN  '"+ specimenRecDate+ " 00:00:00' AND '"+ specimenRecDate+ " 23:59:59') "
				+ " and (data.collectionTime BETWEEN  '"+ testDate+ " 00:00:00' AND '" + testDate + " 23:59:59' )" 
				+ " and data.animalId in (:animalIdList)";
		List<?> list = getSession().createSQLQuery(sql)
				.setParameter("studyNo",studyNo)
				.setParameter("reqNo", reqNo)
				.setParameter("testItem",testItem)
				.setParameterList("animalIdList", animalIdList)
				.list();

		return list;
	}

	public List<?> findListByDateTestItemESStudyNOReqNoCode(String date,
			int testItem, int es, String studyNo2, int reqNo2,
			String beginCodeStr2, String endCodeStr2) {
		Date minDate= DateUtil.stringToDate(date+" 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date maxDate= DateUtil.stringToDate(date+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
		List<?> list=null;
		
		String sql ="select distinct t.specimenCode,t.animalCode,t.animalId,t.testItem,t.studyNo from tblClinicalTestData t WHERE  t.acceptTime BETWEEN :minDate AND :maxDate  AND t.es =:es";
		if(testItem!=0){
			sql+=" AND t.testItem = :testItem ";
		}
		if(null!=studyNo2 && !studyNo2.equals("")){
			sql+=" AND t.studyNo = :studyNo ";
		}
		if(reqNo2 != 0){
			sql+=" AND t.reqNo = :reqNo ";
		}
		if(null != beginCodeStr2 && !"".equals(beginCodeStr2) && null != endCodeStr2 && !"".equals(endCodeStr2) ){
			sql+=" AND t.specimenCode >= :beginCodeStr AND t.specimenCode <= :endCodeStr ";
		}else if(null != beginCodeStr2 && !"".equals(beginCodeStr2)){
			sql+=" AND t.specimenCode >= :beginCodeStr  ";
		}else if(null != endCodeStr2 && !"".equals(endCodeStr2)){
			sql+="  AND t.specimenCode <= :endCodeStr ";
		}
		
		sql = sql +" order by t.testItem ,t.specimenCode ";
		Query query = getSession().createSQLQuery(sql);
		
		query.setParameter("minDate", minDate);
		query.setParameter("maxDate", maxDate);
		query.setParameter("es", es);
		
		if(testItem!=0){
//			sql+=" AND t.testItem = :testItem ";
			query.setParameter("testItem", testItem);
		}
		if(null!=studyNo2 && !studyNo2.equals("")){
//			sql+=" AND t.studyNo = :studyNo ";
			query.setParameter("studyNo", studyNo2);
		}
		if(reqNo2 != 0){
//			sql+=" AND t.reqNo = :reqNo ";
			query.setParameter("reqNo", reqNo2+"");
		}
		if(null != beginCodeStr2 && !"".equals(beginCodeStr2) && null != endCodeStr2 && !"".equals(endCodeStr2) ){
//			sql+=" AND t.specimenCode >= :beginCodeStr AND t.specimenCode <= :endCodeStr ";
			query.setParameter("beginCodeStr", beginCodeStr2);
			query.setParameter("endCodeStr", endCodeStr2);
		}else if(null != beginCodeStr2 && !"".equals(beginCodeStr2)){
//			sql+=" AND t.specimenCode >= :beginCodeStr  ";
			query.setParameter("beginCodeStr", beginCodeStr2);
		}else if(null != endCodeStr2 && !"".equals(endCodeStr2)){
//			sql+="  AND t.specimenCode <= :endCodeStr ";
			query.setParameter("endCodeStr", endCodeStr2);
		}
		list=query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public String getConfirmFlag(String thisSpecimenCode, int thisTestItem,
			String testIndexAbbr, String thisStudyNo, String thisReqNo) {
		List<TblClinicalTestData> list = getSession()
				.createQuery(
						"From TblClinicalTestData where es =1 and validFlag =1 and "
								+ " specimenCode = ? and testItem = ? and testIndexAbbr =? and studyNo = ? and reqNo = ?  ")
				.setParameter(0, thisSpecimenCode).setParameter(1, thisTestItem)
				.setParameter(2, testIndexAbbr).setParameter(3, thisStudyNo)
				.setParameter(4, thisReqNo).list();
		if(null != list && list.size()>0){
			return list.get(0).getConfirmFlag()+"";
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public String getTestData(String thisSpecimenCode, int thisTestItem,
			String testIndexAbbr, String thisStudyNo, String thisReqNo) {
		List<TblClinicalTestData> list = getSession()
				.createQuery(
						"From TblClinicalTestData where es =1 and validFlag =1 and "
								+ " specimenCode = ? and testItem = ? and testIndexAbbr =? and studyNo = ? and reqNo = ?  ")
				.setParameter(0, thisSpecimenCode)
				.setParameter(1, thisTestItem).setParameter(2, testIndexAbbr)
				.setParameter(3, thisStudyNo).setParameter(4, thisReqNo).list();
		if (null != list && list.size() > 0) {
			return list.get(0).getTestData();
		}
		return "";
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> getStudyNoReqNoUserNameByIds(List<String> list) {
		if(null != list && list.size()>0){
			String sql = "select distinct data.studyNo,data.reqNo,tu.userName"+
						" from CoresStudy.dbo.tblClinicalTestData as data left join CoresStudy.dbo.tblStudyPlan as study"+
						" on data.studyNo = study.studyNo left join CoresUserPrivilege.dbo.tbluser as tu"+
						" on study.studydirector = tu.realName"+
						" where data.dataId in (:idList)";
			List<Map<String,Object>> studynoReqnoUsernameList = getSession().createSQLQuery(sql)
															.setParameterList("idList", list)
															.setResultTransformer(new MapResultTransformer())
															.list();
			return studynoReqnoUsernameList;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> isHaveValidData(List<String> list) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("exist", false);
		if(null != list){
				List<List<String>> dataIdListList = new ArrayList<List<String>>();
				int  i = 0;
				List<String> dataIdList = new ArrayList<String>();
				for(String dataId:list){
					i++;
					dataIdList.add(dataId);
					if(i%1000 == 0){
						dataIdListList.add(dataIdList);
						dataIdList = new ArrayList<String>();
					}
				}
				if(i%1000 != 0){
					dataIdListList.add(dataIdList);
				}
				
				for(List<String> dataList: dataIdListList){
					String sql = "select data1.dataId,data2.specimenCode,data2.testIndexAbbr"+
					" from CoresStudy.dbo.tblClinicalTestData as data1 left join CoresStudy.dbo.tblClinicalTestData as data2"+
					" 	on data1.dataId != data2.dataId and data2.es = 1 and data2.validFlag = 1 and data1.studyNo = data2.studyNo"+
					" 	and data1.reqNo = data2.reqNo and data1.testItem = data2.testItem and data1.specimenCode = data2.specimenCode"+
					" 	and data1.testIndexAbbr = data2.testIndexAbbr and data1.animalId = data2.animalId "+
					" where data1.dataId in (:dataIdList) and data2.specimenCode is not null";
					List<Map<String,Object>> mapList = getSession().createSQLQuery(sql)
																	.setParameterList("dataIdList", dataList)
																	.setResultTransformer(new MapResultTransformer())
																	.list();
					if(null != mapList&& mapList.size() > 0){
						Map<String,Object> map = mapList.get(0);
						String dataId = (String) map.get("dataId");
						String specimenCode = (String) map.get("specimenCode");
						String testIndexAbbr = (String) map.get("testIndexAbbr");
						resultMap.put("exist", true);
						resultMap.put("msg", specimenCode+ ":"+testIndexAbbr + " 存在已签字数据！");
						resultMap.put("dataId", dataId);
						return resultMap;
					}
				}
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	public List<String> getNoeditDataIdList(List<String> retDataIdList) {
		String sql = "select d.dataId"+
					" from CoresStudy.dbo.tblClinicalTestData as d left join CoresUserPrivilege.dbo.tblTrace as t"+
					" 	on t.tableName = 'TblClinicalTestData'  and t.dataId = d.dataId and t.operateMode = 1"+
					" where d.dataId in (:retDataIdList) and t.id is null "+
					" 	and d.testData = '999.9' and d.testIndexAbbr = 'RET#'";
		List<String> list = getSession().createSQLQuery(sql).setParameterList("retDataIdList", retDataIdList).list();
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TblClinicalTestData> getByIdList(List<String> dataIdList) {
		String hql = "from TblClinicalTestData where dataId in (:dataIdList)";
		List<TblClinicalTestData> list = getSession().createQuery(hql).setParameterList("dataIdList", dataIdList).list();
		return list;
	}

	public void editSave(List<TblTrace> tblTraceList) {
		Date date = new Date();
		for(TblTrace obj : tblTraceList){
			TblClinicalTestData tblClinicalTestData = this.getById(obj.getDataId());
			obj.setId(tblTraceService.getKey());
			obj.setOldValue(tblClinicalTestData.getTestData());
			obj.setModifyTime(date);
			
			tblClinicalTestData.setTestData(obj.getNewValue());
			
			getSession().update(tblClinicalTestData);
			tblTraceService.save(obj);
		}
		
	}

}
