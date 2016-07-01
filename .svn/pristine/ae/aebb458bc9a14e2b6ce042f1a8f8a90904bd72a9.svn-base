package com.lanen.util;

public class QAStatusLevels {
	public static int DictChkItemStudyGroupReg_chkFreqFlag_single = 1;//检查频率。1：单次；2：重复
	public static int DictChkItemStudyGroupReg_chkFreqFlag_repeat = 2;
	public static int DictQACheckItem_chkItemType_study = 1; //检查项分类。1：方案；2：报告；3：变更；4：基于研究的检查项
	public static int DictQACheckItem_chkItemType_report = 2;
	public static int DictQACheckItem_chkItemType_change = 3;
	public static int DictQACheckItem_chkItemType_research = 4;
	public static int TblStudyFileIndex_fileType_study = 1; //	fileType：1：方案；2：报告；
	public static int TblStudyFileIndex_fileType_report = 2;
	public static int TblStudyFileIndex_fileState_draft=0;//fileState：0：草稿；1：提交审批中；2：结束
	public static int TblStudyFileIndex_fileState_commit=1;
	public static int TblStudyFileIndex_fileState_end=2;
	
	//专题QA检查索引表
	public static int QAStudyChkIndex_inspectorState_unappoint = 0; //inspectorState：QA检查员任命状态。0：未任命；1：已任命（代码中没有用“常量”表示吗？？）
	public static int QAStudyChkIndex_inspectorState_appointed = 1; 
	public static int QAStudyChkIndex_studyPlanState_unfinish = 0;//studyPlanState：方案审批状态。0：未完成；1：已完成
	public static int QAStudyChkIndex_studyPlanState_finish = 1;
	public static int QAStudyChkIndex_studyPlanChangeState_nochange = 0;//studyPlanChangeState：方案变更申请状态。0：无变更；1：提交变更；-1：否决；2：通过
	public static int QAStudyChkIndex_studyPlanChangeState_changecommit = 1;
	public static int QAStudyChkIndex_studyPlanChangeState_reback = -1;
	public static int QAStudyChkIndex_studyPlanChangeState_approval = 2;
	public static int QAStudyChkIndex_scheduleState_noCommit = 0; //scheduleState：方案日程状态。0：未提交；1：已提交
	public static int QAStudyChkIndex_scheduleState_commit = 1;
	public static int QAStudyChkIndex_reportState_noFinish = 0;//：专题报告状态。0：未完成；1：已完成
	public static int QAStudyChkIndex_reportState_finish = 1;
	public static int QAStudyChkIndex_chkPlanState_draft = 0;//：检查计划状态。0：草稿；1：提交；-1：QAM否决；2：通过
	public static int QAStudyChkIndex_chkPlanState_commit = 1;
	public static int QAStudyChkIndex_chkPlanState_reBack = -1;
	public static int QAStudyChkIndex_chkPlanState_approval = 2;
	public static int QAStudyChkIndex_chkPlanFinishFlag_noFinish = 0;//：检查计划完成标识。0：未完成；1：完成
	public static int QAStudyChkIndex_chkPlanFinishFlag_finish = 1;
	public static int QAStudyChkIndex_scheduleChangedFlag_noChange = 0;//：日程变更通知。（动态标志，由SD提交日程所触发）0：无变更，1：变更，2：变更处理完毕
	public static int QAStudyChkIndex_scheduleChangedFlag_change = 1;
	public static int QAStudyChkIndex_scheduleChangedFlag_changeFinish = 2;
	//QA检查计划变更申请索引表
	public static int QAChkPlanChangeIndex_changeState_draft = 0;//：变更审批状态。0：原始；1：提交；-1：否决；2：通过,-2:撤销。
	public static int QAChkPlanChangeIndex_changeState_commit = 1;
	public static int QAChkPlanChangeIndex_changeState_reBack = -1;
	public static int QAChkPlanChangeIndex_changeState_approval = 2;
	public static int QAChkPlanChangeIndex_changeState_revoke = -2;
	//QA检查计划表
	public static int QAChkPlan_chkPlanType_research = 1; //：计划类型。1:研究；2：过程；3：设施
	public static int QAChkPlan_chkPlanType_process = 2;
	public static int QAChkPlan_chkPlanType_instrument = 3;
	public static int QAChkPlan_chkFinishedFlag_noFinish = 0;//：检查完毕标志。0：未完成；1：已完成；-1取消
	public static int QAChkPlan_chkFinishedFlag_finish = 1;
	public static int QAChkPlan_chkFinishedFlag_cancel = -1;
	public static int QAChkPlan_SOPFlag_SOP = 1;//：检查依据。1：SOP；2：SOP+方案；3：方案
	public static int QAChkPlan_SOPFlag_SOPAndStudy = 2;
	public static int QAChkPlan_SOPFlag_Study = 3;
	public static int QAChkPlan_tempChkOperatorFlag_default = 0;//：临时检查员申请。0：默认，1：提申请，2：QAM批准，-1：不批准
	public static int QAChkPlan_tempChkOperatorFlag_commit = 1;
	public static int QAChkPlan_tempChkOperatorFlag_approval = 2;
	public static int QAChkPlan_tempChkOperatorFlag_reBack = -1;
	//检查索引表
	public static int QAChkIndex_chkType_research = 1;//：类型。1:研究；2：过程；3：设施；4方案；5：报告
	public static int QAChkIndex_chkType_process = 2;
	public static int QAChkIndex_chkType_instrument = 3;
	public static int QAChkIndex_chkType_study = 4;
	public static int QAChkIndex_chkType_report = 5;
	public static int QAChkIndex_chkState_draft = 0;//：检查状态。0：草稿；1：检查中（启动）2：完成
	public static int QAChkIndex_chkState_checking = 1;
	public static int QAChkIndex_chkState_finish = 2;
	//检查记录表
	public static int QAChkRecord_chkResultFlag_right = 1;//：状态（是否需回复）。1：符合；-1：不符合；0：不适用。
	public static int QAChkRecord_chkResultFlag_fault = -1;
	public static int QAChkRecord_chkResultFlag_notApplicable = 0;
	//QA检查报告
	public static int QAChkReport_rptState_draft = 0;//：报告状态。0：草稿；1：提交；2：QAM通过；-2：QAM未通过；3：回复中；4：进入再检查；5：进入延期整改；9：完成。
	public static int QAChkReport_rptState_commit = 1;
	public static int QAChkReport_rptState_approval = 2;
	public static int QAChkReport_rptState_reBack = -2;
	public static int QAChkReport_rptState_replying = 3;
	public static int QAChkReport_rptState_reChk = 4;
	public static int QAChkReport_rptState_delay = 5;
	public static int QAChkReport_rptState_finish = 9;
	public static int QAChkReport_needReply_no = 0;//：0 否;  1:是
	public static int QAChkReport_needReply_yes = 1;
	public static int QAChkReport_replyState_draft = 0;//：报告回复状态。0：草稿；1：提交FM；-1；FM退回；2：提交QA检查员；3：QA检查员确认接收。
	public static int QAChkReport_replyState_commitToFM = 1;
	public static int QAChkReport_replyState_reBack = -1;
	public static int QAChkReport_replyState_commitToQA = 2;
	public static int QAChkReport_replyState_QAReceive = 3;
	public static int QAChkReport_needReChk_no = 0 ;//：是否需要再检查。0：不需要；1：需要
	public static int QAChkReport_needReChk_yes = 1 ;
	public static int QAChkReport_reChkState_problemNotSolved = 0;//：再检查状态。0：问题未解决；1：问题已解决
	public static int QAChkReport_reChkState_problemSolved = 1;
	public static int QAChkReport_needDelay_noApply = 0;//：是否需要延迟整改。0：未申请；1：提交FM；-1：FM退回；2：提交QA检查员；3：qa检查员确认接收
	public static int QAChkReport_needDelay_commitToFM = 1;
	public static int QAChkReport_needDelay_FMReBack = -1;
	public static int QAChkReport_needDelay_commitToQA = 2;
	public static int QAChkReport_needDelay_QAReceive = 3;
	public static int QAChkReport_delayState_noFinish = 0;//：延迟整改整改状态。0：未完成；1：已完成
	public static int QAChkReport_delayState_finish = 1;
	/*
		QAApprovalOpinion：审批意见记录表。
		approvalType：审批类型。1：报告；2：回复；3：延迟整改；4：检查计划。
		operatorType：审批人类型。1：FM；2：QAM；3：QA检查员。
		approvalResultFlag：审批结果标志位。1：过；-1：未过。

	
		QAChkReportRecord：QA检查报告记录。
			chkResultFlag：状态（是否需回复）。1：符合；-1：不符合；0：不适用
			reChkFlag：状态（是否需再检查）。0：不需；1：需要；
			reChkResult：再检查结果。0：未检查；-1：问题未解决；1：问题已解决
			needDelay：延迟整改申请状态。0：未申请；1：已申请
		QAReChkRecord：再检查记录表。
			reChkType：再检查类型。1：回复的再检查记录；2：延迟整改再检查记录
			reChkResult：再检查结果。0：未确认；1：问题已解决；-1：未解决

	2.	文件管理相关的表和状态位列表：
	QAFileType：文件管理类别表。
		fileType：文件类型。1：法规；2：指导原则；3：SOP。
	QAFileReg：文件管理登记表。
	fileType：文件类型。1：法规；2：指导原则；3：SOP。
	isVersionUpdate：是否版本更新。0:否  1:是
		QAFileAttachment：文件附件。
		QALearnTask：学习任务。
			learnState：状态。0：未提交；1：学习中（已提交）；2：完成 -1撤销
		QALearnTaskFile：学习任务文件列表。
	fileType：文件类型。1：法规；2：指导原则；3：SOP。
	isVersionUpdate：是否版本更新。0:否  1:是
	QAFileRegReader：文件管理读者记录表。
		learnState：学习状态。0：未生效；1：要求学，还未学；2：学完。
	QALearnTaskFileReadRecord：文件阅读记录。
		readFinishFlag：阅读完成标识。0：否；1：是

*/
	public static int systemSet_reportCode_QAChkReport = 1;
	public static int systemSet_reportCode_QAQualityReport = 2;
	
	
	
	

}
