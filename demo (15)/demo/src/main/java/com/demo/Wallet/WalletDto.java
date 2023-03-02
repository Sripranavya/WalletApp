package com.demo.Wallet;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WalletDto {
    //data members
    @Id
    private Integer id;
    private String name;
    private Double balance;
    private String email;
    private int pwd;


   public WalletDto(){
       // default constructor
   }

   public WalletDto(Integer id, String name, Double balance, String email, int pwd)
   {
       // parameterized constructor
       this.id=id;
       this.name=name;
       this.balance=balance;
       this.email=email;
       this.pwd=pwd;
   }

   // getters and setters
   public int getId()
   {
       return id;
   }
   public void setId(Integer id)
   {
       this.id=id;
   }
   public String getName()
   {
       return name;
   }
   public void setName(String name)
   {
       this.name=name;
   }
   public double getBalance()
   {
       return balance;
   }
   public void setBalance(Double balance)
   {
       this.balance=balance;
   }
   public String getEmail()
   {
       return email;
   }
   public void setEmail(String email)
   {
       this.email=email;
   }
   public int getPwd()
   {
       return pwd;
   }
   public void setPwd(int pwd)
   {
       this.pwd=pwd;

   }


   //toString method
    @Override
    public String toString() {
        return "Wallet [" +
                "id =" + id +
                ", name ='" + name + '\'' +
                ", balance =" + balance +
                ", email =" + email +
                ", password =" +pwd +
                ']';
    }
}
