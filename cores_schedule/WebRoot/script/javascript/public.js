 //比较时间大小
    function dateCompare(startdate,enddate){   
         var arr=startdate.split("-");    
         var starttime=new Date(arr[0],arr[1],arr[2]);    
         var starttimes=starttime.getTime();   
         var arrs=enddate.split("-");    
         var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
         var lktimes=lktime.getTime();   
         if(starttimes>lktimes){   
            return false;   
         }else  {
            return true;   
         }  
    }  
    