package top.builbu.business.wechat.dto;  

import java.util.Date;      
  
public class MemberDTO {  
	    /**
	     *
	     *
	    **/
        private Long memberId;  
	    /**
	     *
	     *
	    **/
        private String openId;  
	    /**
	     *
	     *
	    **/
        private String isLi;  
	    /**
	     *
	     *
	    **/
        private String isLe;  
	    /**
	     *
	     *
	    **/
        private String isAll;  
        
        private String address;
        
        private String forLoad;
          
        private String code;
          
        public Long getMemberId(){  
            return this.memberId;  
        }  
       
        public void setMemberId(Long memberId){  
            this.memberId = memberId;
        } 
         
        public String getOpenId(){  
            return this.openId;  
        }  
       
        public void setOpenId(String openId){  
            this.openId = openId == "" ? null : openId.trim();
        } 
         
        public String getIsLi(){  
            return this.isLi;  
        }  
       
        public void setIsLi(String isLi){  
            this.isLi = isLi == "" ? null : isLi.trim();
        } 
         
        public String getIsLe(){  
            return this.isLe;  
        }  
       
        public void setIsLe(String isLe){  
            this.isLe = isLe == "" ? null : isLe.trim();
        } 
         
        public String getIsAll(){  
            return this.isAll;  
        }  
       
        public void setIsAll(String isAll){  
            this.isAll = isAll == "" ? null : isAll.trim();
        }

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getForLoad() {
			return forLoad;
		}

		public void setForLoad(String forLoad) {
			this.forLoad = forLoad;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		} 
         
}  