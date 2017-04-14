package top.builbu.business.wechat.entity;  

import java.util.Date;      
  
public class Rite {  
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
           
            this.riteCode = riteCode;
                    
        }  
          
                 
        
        public String getAddress(){  
            return this.address;  
        }  
       
        public void setAddress(String address){            
           
            this.address = address;
                    
        }  
          
                 
        
        public Long getCount(){  
            return this.count;  
        }  
       
        public void setCount(Long count){            
           
            this.count = count;
                    
        }  
          
                 
}  

