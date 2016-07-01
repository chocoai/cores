package com.lanen.service.contract;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.lanen.base.BaseDaoImpl;
import com.lanen.model.contract.TblContract;
@Service
public class TblIntegratedInformServiceImpl extends BaseDaoImpl<TblContract> implements TblIntegratedInformService{

	public List<?> getByStartimeAndEndtimeAndTiCodeCollectList(
			Date startime, Date endtime, String tiCode,String operator) {
      String sql = "    SELECT * FROM  ( SELECT count(distinct tblc0.sponsorId) as com  "+
      "    FROM [CoresContract].[dbo].[tblContract] as tblc0 left join  "+ 
      "    [CoresContract].[dbo].[tblTestItem]  as tblt  "+
      "    ON tblc0.contractCode = tblt.contractCode  "+
      "    WHERE tblc0.signingDate between :startime and :endtime and tblc0.[contractState] != 0   ";
      if(operator != null  && !operator.equals("")){
          sql = sql +" and tblc0.operator= :operator ";
      }
      if(tiCode != null  && !tiCode.equals("")){
    	  sql = sql +" and tblt.tiCode = :tiCode ";
      }  
      sql = sql + " ) as com ,  "+
      "    (SELECT count(distinct tblc1.id) as coa  "+
      "    FROM [CoresContract].[dbo].[tblContract] as tblc1  left join   "+
      "    [CoresContract].[dbo].[tblTestItem]  as tblt  "+
      "    ON tblc1.contractCode = tblt.contractCode  "+
      "    WHERE tblc1.signingDate between :startime and :endtime  and tblc1.[contractState] != 0  ";
      if(operator != null  && !operator.equals("")){
          sql = sql + " and tblc1.operator= :operator ";
      }
      if(tiCode != null  && !tiCode.equals("")){	  
          sql = sql + " and tblt.tiCode = :tiCode  ";
      }
      sql = sql + " )as coa ,(  "+
      "    SELECT count(tblt.id) as coti  "+
      "    FROM [CoresContract].[dbo].[tblContract] as tblc2 left join   "+
      "    [CoresContract].[dbo].[tblTestItem]  as tblt  "+
      "    ON tblc2.contractCode = tblt.contractCode  "+
      "    WHERE tblc2.signingDate between :startime and :endtime and tblt.state != 0  "+
      "    and tblc2.[contractState] != 0   ";
      if(operator != null  && !operator.equals("")){
          sql = sql + " and tblc2.operator= :operator ";
      }
      if(tiCode != null  && !tiCode.equals("")){	  
          sql = sql + "   and tblt.tiCode = :tiCode  ";
      }
      sql = sql + "  ) as coti , (  "+
      "      SELECT count(tbls.id) as cots  "+
      "      FROM [CoresContract].[dbo].[tblContract] as tblc3 left join   "+
      "      ( SELECT  ts.id, ts.contractCode FROM [CoresContract].[dbo].[tblStudyItem] as ts left join   "+
      "      [CoresContract].[dbo].[tblTestItem] as tt  "+
      "      ON ts.tiNo = tt.tiNo WHERE tt.state != 0   ";
    
      if(tiCode != null  && !tiCode.equals("")){	  
          sql = sql + "    and tt.tiCode  = :tiCode   ";
      }
      sql = sql + " ) as tbls  "+
      "     ON tblc3.contractCode = tbls.contractCode  "+
      "      WHERE tblc3.signingDate between :startime and :endtime   "+
      "     and  tblc3.[contractState] != 0   ";
	  if(operator != null  && !operator.equals("")){
	      sql = sql + " and tblc3.operator= :operator ";
	  }
      sql = sql + " ) as cots ,(  "+
      "    SELECT sum(Convert(float, tblc7.contractPrice)  ) as sump ,tblc7.priceUnit FROM  "+
      "      (SELECT distinct tblc4.contractCode,tblc4.contractPrice ,tblc4.priceUnit  "+
      "      FROM [CoresContract].[dbo].[tblContract] as tblc4 left join    "+
      "   [CoresContract].[dbo].[tblTestItem]  as tblt   "+
      "     ON tblc4.contractCode = tblt.contractCode   "+
      "     WHERE tblc4.signingDate between :startime and :endtime   ";
      if(operator != null  && !operator.equals("")){
          sql = sql + " and tblc4.operator= :operator ";
      }
      if(tiCode != null  && !tiCode.equals("")){	  
          sql = sql + "    and tblt.tiCode  = :tiCode  ";
      }
      sql = sql +"  and tblc4.[contractState] != 0 ) as tblc7  GROUP BY  tblc7.priceUnit ) as pri  ";
		Query query = getSession().createSQLQuery(sql);
		query.setParameter("startime", startime);
		query.setParameter("endtime", endtime);
		 if(tiCode != null  && !tiCode.equals("")){
		  query.setParameter("tiCode", tiCode);
		 }
		 if(operator != null  && !operator.equals("")){
			 query.setParameter("operator", operator);
	      }
		List<?> list = query.list();
		return list;
	}
      
}
