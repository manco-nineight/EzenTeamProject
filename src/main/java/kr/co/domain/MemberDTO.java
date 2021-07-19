package kr.co.domain;

import java.io.Serializable;

public class MemberDTO implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   
   private String userId;
   private String userPw;
   private String userName;
   private String email;
   private String regDate;
   private String updateDate;
   
   public MemberDTO() {
      // TODO Auto-generated constructor stub
   }

   public MemberDTO(String userId, String userPw, String userName, String email, String regDate, String updateDate) {
      super();
      this.userId = userId;
      this.userPw = userPw;
      this.userName = userName;
      this.email = email;
      this.regDate = regDate;
      this.updateDate = updateDate;
   }

   public String getUserId() {
      return userId;
   }

   public void setUserId(String userId) {
      this.userId = userId;
   }

   public String getUserPw() {
      return userPw;
   }

   public void setUserPw(String userPw) {
      this.userPw = userPw;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getRegDate() {
      return regDate;
   }

   public void setRegDate(String regDate) {
      this.regDate = regDate;
   }

   public String getUpdateDate() {
      return updateDate;
   }

   public void setUpdateDate(String updateDate) {
      this.updateDate = updateDate;
   }

   public static long getSerialversionuid() {
      return serialVersionUID;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((userId == null) ? 0 : userId.hashCode());
      result = prime * result + ((userPw == null) ? 0 : userPw.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj)
         return true;
      if (obj == null)
         return false;
      if (getClass() != obj.getClass())
         return false;
      MemberDTO other = (MemberDTO) obj;
      if (userId == null) {
         if (other.userId != null)
            return false;
      } else if (!userId.equals(other.userId))
         return false;
      if (userPw == null) {
         if (other.userPw != null)
            return false;
      } else if (!userPw.equals(other.userPw))
         return false;
      return true;
   }

   @Override
   public String toString() {
      return userId + " : " + userName;
   }
   
   
   

}