package com.example.workflow; // ระบุแพ็กเกจที่คลาสนี้อยู่ เพื่อจัดระเบียบโค้ดให้อยู่ในกลุ่มเดียวกัน

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component; // นำเข้า Component annotation จาก Spring Framework เพื่อระบุว่าเป็น Spring Bean

@Component("reserveSeatOnBoat") // ระบุชื่อ Bean เป็น "reserveSeatOnBoat" เพื่อให้ง่ายต่อการเรียกใช้ในกระบวนการ BPMN
public class ReserveSeatOnBoat implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception { // เมธอดนี้จะถูกเรียกเมื่อโปรเซส BPMN ถึงจุดที่ใช้ Delegate
        // TODO Auto-generated method stub
        String money = "0.0"; //ป้องกัน null pointer
        String ticketType = "Coach";
    
        money = (String) execution.getVariable("money"); // ดึงค่าตัวแปร money จากกระบวนการ BPMN
        double moneyDouble = Double.parseDouble(money);
    
        if (moneyDouble >= 10000) {
            ticketType = "First Class";
        } else if (moneyDouble >= 5000) {
            ticketType = "Business Class";
        } else if (moneyDouble <= 10){
            ticketType = "Stowaway Class";
            throw new BpmnError("Fall_Overboard", "A Cheap ticket has led to a small wave throwing you overboard.");
        }
    
        execution.setVariable("ticketType", ticketType); // ตั้งค่าตัวแปร ticketType ในกระบวนการ BPMN
    
    }
    

}
