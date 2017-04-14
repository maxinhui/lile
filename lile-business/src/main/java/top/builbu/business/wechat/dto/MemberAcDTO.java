package top.builbu.business.wechat.dto;  

import java.util.Date;      
  
public class MemberAcDTO {  
	    /**
	     *
	     *主键id
	    **/
        private Long id;  
	    /**
	     *
	     *微信openId
	    **/
        private String openId;  
	    /**
	     *
	     *扫码时间
	    **/
        private Date sancTime;  
	    /**
	     *
	     *小姐识别id
	    **/
        private Long riteId;  
          
          
        public Long getId(){  
            return this.id;  
        }  
       
        public void setId(Long id){  
            this.id = id;
        } 
         
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){  
            this.openId = openId == "" ? null : openId.trim();
        } 
         
        public Date getSancTime(){  
            return this.sancTime;  
        }  
       
        public void setSancTime(Date sancTime){  
            this.sancTime = sancTime;
        } 
         
        public Long getRiteId(){  
            return this.riteId;  
        }  
       
        public void setRiteId(Long riteId){  
            this.riteId = riteId;
        } 
         
}  