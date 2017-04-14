package top.builbu.business.wechat.dto;  

import java.util.Date;      
  
public class RiteDTO {  
	    /**
	     *
	     *主键id
	    **/
        private Long riteId;  
	    /**
	     *
	     *识别码
	    **/
        private String riteCode;  
	    /**
	     *
	     *所在区域
	    **/
        private String address;  
	    /**
	     *
	     *访问流量
	    **/
        private Long count;  
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
          
        public Long getRiteId(){  
            return this.riteId;  
        }  
       
        public void setRiteId(Long riteId){  
            this.riteId = riteId;
        } 
         
        public String getRiteCode(){  
            return this.riteCode;  
        }  
       
        public void setRiteCode(String riteCode){  
            this.riteCode = riteCode == "" ? null : riteCode.trim();
        } 
         
        public String getAddress(){  
            return this.address;  
        }  
       
        public void setAddress(String address){  
            this.address = address == "" ? null : address.trim();
        } 
         
        public Long getCount(){  
            return this.count;  
        }  
       
        public void setCount(Long count){  
            this.count = count;
        }

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public Date getSancTime() {
			return sancTime;
		}

		public void setSancTime(Date sancTime) {
			this.sancTime = sancTime;
		} 
         
}  