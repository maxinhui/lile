package top.builbu.business.wechat.dto;  

import java.util.Date;      
  
public class MemberDrawDTO {  
	    /**
	     *
	     *
	    **/
        private Long id;  
	    /**
	     *
	     *
	    **/
        private String openId;  
	    /**
	     *
	     *
	    **/
        private Date createTime;  
	    /**
	     *
	     *
	    **/
        private String riteCode;  
          
          
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
         
        public Date getCreateTime(){  
            return this.createTime;  
        }  
       
        public void setCreateTime(Date createTime){  
            this.createTime = createTime;
        } 
         
        public String getRiteCode(){  
            return this.riteCode;  
        }  
       
        public void setRiteCode(String riteCode){  
            this.riteCode = riteCode == "" ? null : riteCode.trim();
        } 
         
}  