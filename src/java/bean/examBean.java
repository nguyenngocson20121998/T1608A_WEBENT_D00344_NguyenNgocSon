/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import da.DatabaseOperation;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Duc Hero
 */
@ManagedBean
@RequestScoped
public class examBean {

    /**
     * Creates a new instance of feventBean
     */
    public examBean() {
    }

    private String subject;
    private String start_time;
    private String exam_date;
    private String duration;
    private String class_room;
    private String faculty;
    private String status;

    public String getSubject() {
        return subject;
    }

    public ArrayList<examBean> fexam;
    public ArrayList<examBean> feventlist;

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getExam_date() {
        return exam_date;
    }

    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getClass_room() {
        return class_room;
    }

    public void setClass_room(String class_room) {
        this.class_room = class_room;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<examBean> getFexam() {
        return fexam;
    }

    public void setFexam(ArrayList<examBean> fexam) {
        this.fexam = fexam;
    }

    public ArrayList<examBean> getFeventlist() {
        return feventlist;
    }

    public void setFeventlist(ArrayList<examBean> feventlist) {
        this.feventlist = feventlist;
    }

   

   
    @PostConstruct
    public void init() {
        fexam = DatabaseOperation.getfeventlist();
    }

    public ArrayList<examBean> eventList() {
        return fexam;
    }

    public String saveEvent(examBean feventBeanObj) {
        return DatabaseOperation.saveEvent(feventBeanObj);
    }

}
