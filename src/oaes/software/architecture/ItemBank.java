package oaes.software.architecture;

import oaes.software.architecture.QuestionTypes.Desc;
import oaes.software.architecture.QuestionTypes.Mcq;
import oaes.software.architecture.QuestionTypes.Msq;

import java.util.Arrays;

public class ItemBank {
   private Mcq[] mcq;
   private Msq[] msq;
   private Desc[] desc;

   public ItemBank() {
   }

   public ItemBank(Mcq[] mcq, Msq[] msq, Desc[] desc) {
      this.mcq = mcq;
      this.msq = msq;
      this.desc = desc;
   }

   public Mcq[] getMcq() {
      return mcq;
   }

   public void setMcq(Mcq[] mcq) {
      this.mcq = mcq;
   }

   public Msq[] getMsq() {
      return msq;
   }

   public void setMsq(Msq[] msq) {
      this.msq = msq;
   }

   public Desc[] getDesc() {
      return desc;
   }

   public void setDesc(Desc[] desc) {
      this.desc = desc;
   }
}
